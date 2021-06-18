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
import ru.pfr.model.ZP.ZPchild;
import ru.pfr.model.ZP.ZPdoc;
import ru.pfr.model.ZP.ZProd;
import ru.pfr.model.ZP.ZPview;
import ru.pfr.model.lrp.LrpFiles;
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
public class ZPController {

    @Autowired
    ZProdService zProdService;

    @Autowired
    ZPdocService zPdocService;

    @Autowired
    ZPchildService zPchildService;

    @Autowired
    ZPviewService zPviewService;

    @Autowired
    LrpFilesService lrpFilesService;

    @GetMapping("/download_z_p")
    public String download_z_p(
            Model model) {
        return "download_z_p";
    }

    @PostMapping(value = "/download_z_p/dokumentinsert", produces = "text/plain")
    public @ResponseBody
    String z_p_dokumentinsert(
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
            filePart = req.getParts();
        } catch (Exception e) {
        }

        int s = filePart.size();

        Map<String, String> mapFiles = new HashMap<>(); //для отправки ошибки

        //проходим по файлам
        for (Part fp :
                filePart) {

            boolean b = true; //была ли исключительная ситуация
            LrpFiles lrpFiles = null;

            try {
                FileInputStream fileContent =
                        fileContent = (FileInputStream) fp.getInputStream();

                lrpFiles = new LrpFiles(fp.getSubmittedFileName(), 2, "Законные представители");
                lrpFilesService.save(lrpFiles); //сохраняем название файла

                Poixls_zp poixls = new Poixls_zp(fileContent);

                if (poixls.checkCorrectFile()) {
                    ZProd zProd = null;
                    ZPdoc zPdoc = null;
                    ZPchild zPchild = null;
                    ZPdoc zPdocNew;
                    ZPchild zPchildNew;
                    for (int i = 8; i <= poixls.getEndRowChild(); i++) {
                        if (poixls.rowRodCorrect(i)) { //есть строка с родителями
                            //просто получаем данные
                            zProd = poixls.getZProd(i, lrpFiles);
                            zPdoc = poixls.getZPDoc(i, zProd);
                            zPchild = poixls.getZPchild(i, zPdoc);

                            zpaddall(zProd, zPdoc, zPchild);
                        } else {
                            if (poixls.rowChildCorrect(i)) { //есть строка с детьми
                                zPdocNew = poixls.getZPDoc(i, zProd).ZPdocSlijanie(zPdoc);
                                zPchildNew = poixls.getZPchild(i, zPdocNew);
                                zpaddall(zProd, zPdocNew, zPchildNew);
                            }
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
            value = "/save_z_p",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody
    byte[] z_p_getDocumentPechat(
            HttpServletResponse resp,
            Model model) throws IOException {

        CreateFile createFile = new CreateFile(2);

        CSVWriter writer = new CSVWriter(createFile.getOutputfile(), ';', CSVWriter.NO_QUOTE_CHARACTER);

        List<ZPview> list = zPviewService.findAll2(); //полученные данные
        for (ZPview zPview :
                list) {

            String s = zPview.getCSV().replaceAll("\\t", "").replaceAll("\\n", "").replaceAll("\\r", "");

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
    boolean zpaddall(ZProd zProd, ZPdoc zPdoc, ZPchild zPchild) {
        try {
            zProdService.save(zProd);
            zPdocService.save(zPdoc);
            zPchildService.save(zPchild);
        } catch (Exception e) {
            System.out.println("Exception e = " + e);
            return false;
        }
        return true;
    }
}
