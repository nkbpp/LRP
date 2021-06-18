package ru.pfr.controller;

import com.opencsv.CSVWriter;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.pfr.CreateFile.CreateFile;
import ru.pfr.model.ND.NDdoc;
import ru.pfr.model.ND.NDrod;
import ru.pfr.model.ND.NDview;
import ru.pfr.model.ZP.ZPchild;
import ru.pfr.model.ZP.ZPdoc;
import ru.pfr.model.ZP.ZProd;
import ru.pfr.model.ZP.ZPview;
import ru.pfr.model.lrp.LrpFiles;
import ru.pfr.poi.Poixls_nd;
import ru.pfr.poi.Poixls_zp;
import ru.pfr.service.lrp.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/", "/LRP", "/lrp"})
public class NDController {

    @Autowired
    NDrodService nDrodService;

    @Autowired
    NDdocService nDdocService;

    @Autowired
    NDviewService nDviewService;

    @Autowired
    LrpFilesService lrpFilesService;

    @GetMapping("/download_n_d")
    public String download_n_d(
            Model model) {
        return "download_n_d";
    }

    @PostMapping(value = "/download_n_d/dokumentinsert", produces = "text/plain")
    public @ResponseBody
    String n_d_dokumentinsert(
            HttpServletResponse resp,
            HttpServletRequest req,
            Model model) throws IOException {

        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Collection<Part> filePart = null;
        try {
            //filePart = req.getPart("file");
            filePart = req.getParts();
        } catch (Exception e) {
        }

        int s = filePart.size();

        Map<String, String> mapFiles = new HashMap<>();//для отправки ошибки
        for (Part fp :
                filePart) {

            boolean b = true;//была ли исключительная ситуация
            LrpFiles lrpFiles = null;
            try {
                FileInputStream fileContent =
                        fileContent = (FileInputStream) fp.getInputStream();

                lrpFiles = new LrpFiles(fp.getSubmittedFileName(), 3, "Недееспособные");
                lrpFilesService.save(lrpFiles); //сохраняем название файла

                Poixls_nd poixls = new Poixls_nd(fileContent);

                if (poixls.checkCorrectFile()) {
                    NDrod nDrod = null;
                    NDdoc nDdoc = null;
                    NDdoc nDdocNew;
                    for (int i = 8; i <= poixls.getEndRowParent(); i++) {
                        if (poixls.rowRodCorrect(i)) { //есть строка с родителями
                            nDrod = poixls.getNDrod(i, lrpFiles);
                            nDdoc = poixls.getNDdoc(i, nDrod);
                            ndaddall(nDrod, nDdoc);
                        } else {
                        }
                    }
                } else {
                    b = false;
                    throw new Exception("Не подходит по формату");
                }
            } catch (Exception e) {
                lrpFilesService.delete(lrpFiles.getId_file());
                b = false;
            }
            if (b)
                mapFiles.put(fp.getSubmittedFileName(), "Загружено успешно");
            else mapFiles.put(fp.getSubmittedFileName(), "Не удалось загрузить");
        }

        return mapFiles.toString();
    }


    @GetMapping(
            value = "/save_n_d",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody
    byte[] n_d_getDocumentPechat(
            HttpServletResponse resp,
            Model model) throws IOException {

        CreateFile createFile = new CreateFile(3);

        CSVWriter writer = new CSVWriter(createFile.getOutputfile(), ';', CSVWriter.NO_QUOTE_CHARACTER);

        List<NDview> list = nDviewService.findAll2(); //полученные данные
        for (NDview nDview :
                list) {

            String s = nDview.getCSV().replaceAll("\\t", "").replaceAll("\\n", "").replaceAll("\\r", "");

            while (s.contains("  ")) {
                s = s.replaceAll("  ", " ");
            }

            String[] record = s.split(";");
            //Write the record to file
            writer.writeNext(record);
        }
        //close the writer
        writer.close();

        resp = createFile.getResp(resp);

        return IOUtils.toByteArray(createFile.getInputStream());
    }

    @Transactional
    boolean ndaddall(NDrod nDrod, NDdoc nDdoc) {
        try {
            nDrodService.save(nDrod);
            nDdocService.save(nDdoc);
        } catch (Exception e) {
            System.out.println("Exception e = " + e);
            return false;
        }
        return true;
    }
}
