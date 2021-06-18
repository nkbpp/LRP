package ru.pfr.controller;


import com.opencsv.CSVWriter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pfr.CreateFile.CreateFile;
import ru.pfr.model.RP.RPchild;
import ru.pfr.model.RP.RPdoc;
import ru.pfr.model.RP.RProd;
import ru.pfr.model.RP.RPview;
import ru.pfr.model.SnilsXls.SnilsXls;
import ru.pfr.model.lrp.LrpFiles;
import ru.pfr.poi.Poixls_rp;
import ru.pfr.poi.Poixls_snils;
import ru.pfr.service.lrp.*;
import ru.pfr.xmlparsertemp.Xml10kParentModel;
import ru.pfr.xmlparsertemp.Xml10kRequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping(value = {"/", "/LRP", "/lrp"})
public class MainController {

    @Autowired
    RProdService rProdService;

    @Autowired
    RPdocService rPdocService;

    @Autowired
    RPchildService rPchildService;

    @Autowired
    RPviewService rPviewService;

    @Autowired
    LrpFilesService lrpFilesService;

    @RequestMapping
    public String mains(
            Model model) {
        return "main";
    }

    //ЗАГРУЗКА
    @GetMapping("/download_r_p")
    public String download_r_p(
            Model model) {
        return "download_r_p";
    }

    @Transactional
    boolean rpaddall(RProd rProd, RPdoc rPdoc, RPchild rPchild) {
        try {
            rProdService.save(rProd);
            rPdocService.save(rPdoc);
            rPchildService.save(rPchild);
        } catch (Exception e) {
            System.out.println("Exception e = " + e);
            return false;
        }
        return true;
    }


    //ЗАГРУЗКА добавить
    @PostMapping(value = "/download_r_p/dokumentinsert", produces = "text/plain")
    public @ResponseBody
    String r_p_dokumentinsert(
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

        Map<String, Map<String, String>> mapFiles2 = new LinkedHashMap<>(); //для отправки ошибки

        for (Part fp :
                filePart) {

            boolean b = true;
            LrpFiles lrpFiles = null;
            List<String> err;
            Map<String, String> map3 = new LinkedHashMap<>();
            try {
                FileInputStream fileContent =
                        fileContent = (FileInputStream) fp.getInputStream();

                lrpFiles = new LrpFiles(fp.getSubmittedFileName(), 1, "Родительские права");
                lrpFilesService.save(lrpFiles);

                Poixls_rp poixls = new Poixls_rp(fileContent);

                if (poixls.checkCorrectFile()) {
                    RProd rProd = null;
                    RPdoc rPdoc = null;
                    RPchild rPchild = null;
                    RPdoc rPdocNew;
                    RPchild rPchildNew;
                    for (int i = 8; i <= poixls.getEndRowChild(); i++) {
                        if (poixls.rowRodCorrect(i)) { //есть строка с родителями
                            //просто получаем данные
                            rProd = poixls.getRProd(i, lrpFiles, rProd);
                            rPdoc = poixls.getRPDoc(i, rProd);
                            rPchild = poixls.getRPchild(i, rPdoc);
                            //проверяем данные на наличие ошибок
                            err = new ArrayList<>();
                            err = rProd.checksErr(err);
                            err = rPdoc.checksErr(err);
                            err = rPchild.checksErr(err);
                            if (err.size() == 0) {
                                //rpaddall(rProd, rPdoc, rPchild);
                            } else {
                                map3.put("cтрока[" + i + "]", err.toString());
                                //mapFiles.put(fp.getSubmittedFileName() + " cтрока = " + i, err.toString());
                            }
/*                          //удалить данные если уже есть в других файлах по снилс
                            //УДАЛИТЬ ДАННЫЕ ЕСЛИ УЖЕ ЕСТЬ
                            List<RProd> rProds = rProdService.findByParentsSnilsFile(rProd.getSnils_rod(),rProd.getLrpFiles());
                            for (RProd r:
                                rProds) {
                                rProdService.delete(r);
                            }*/
                            //добавить данные даже если они ошибочны
                            rpaddall(rProd, rPdoc, rPchild);

                        } else {
                            if (poixls.rowChildCorrect(i)) { //есть строка с детьми
                                rPdocNew = poixls.getRPDoc(i, rProd).RPdocSlijanie(rPdoc);
                                rPchildNew = poixls.getRPchild(i, rPdocNew);
                                //проверяем данные на наличие ошибок
                                err = new ArrayList<>();
                                err = rProd.checksErr(err);
                                err = rPdocNew.checksErr(err);
                                err = rPchildNew.checksErr(err);
                                if (err.size() == 0) {
                                    //rpaddall(rProd, rPdocNew, rPchildNew);
                                } else {
                                    map3.put("cтрока[" + i + "]", err.toString());
                                }
                                //добавить данные даже если они ошибочны
                                rpaddall(rProd, rPdocNew, rPchildNew);
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
            if (b) {
                mapFiles2.put(fp.getSubmittedFileName(), map3);
                //mapFiles.put(fp.getSubmittedFileName(), "Загружено успешно");
            } else {
                map3.put("Не удалось загрузить", "Неверный формат файла");
                mapFiles2.put(fp.getSubmittedFileName(), map3);
                //mapFiles.put(fp.getSubmittedFileName(), "Не удалось загрузить");
            }
        }

        return mapFiles2.toString();
    }

    //ВЫГРУЗКА
    @GetMapping(
            value = "/save_r_p",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody
    byte[] r_p_getDocumentPechat(
            HttpServletResponse resp,
            Model model) throws IOException {

/*        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        String path = "src/main/resources/csv/";
        String csvName = "41_opeka_" + dateFormat.format(date) + ".csv";
        String csv = path + csvName;

        for (File myFile : new File(path).listFiles()) //удалить старые файлы
            if (myFile.isFile()) myFile.delete();
//        FileWriter outputfile = new FileWriter(csv);

        FileOutputStream fos = new FileOutputStream(csv);
        OutputStreamWriter outputfile = new OutputStreamWriter(fos,
                "Windows-1251");*/

        CreateFile createFile = new CreateFile(1);

        CSVWriter writer = new CSVWriter(createFile.getOutputfile(), ';', CSVWriter.NO_QUOTE_CHARACTER);

        List<RPview> list = rPviewService.findAll2(); //полученные данные
        for (RPview rPview :
                list) {
            if (rPview.checkRPviewCorrect()) {//подходит для фармата выгрузки
                String s = rPview.getCSV().replaceAll("\\t", "").replaceAll("\\n", "").replaceAll("\\r", "");
                while (s.contains("  ")) {
                    s = s.replaceAll("  ", " ");
                }
                String[] record = s.split(";",19);
                //Write the record to file
                writer.writeNext(record);
            }
        }
        //close the writer
        writer.close();

        resp = createFile.getResp(resp);

        return IOUtils.toByteArray(createFile.getInputStream());
    }

    //ВЫГРУЗКА
    @GetMapping(
            value = "/rostzap_rod_r_p",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody
    byte[] r_p_getRostov_Parent(
            HttpServletResponse resp,
            Model model) throws IOException {

        // формируем из файла экземпляр HSSFWorkbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        // создание листа с названием "Просто лист"
        HSSFSheet sheet = workbook.createSheet("list1");

        List<RPview> list = rPviewService.findAll2(); //полученные данные

        // счетчик для строк
        int rowNum = 0;

        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("Id");
        row.createCell(1).setCellValue("Фамилия");
        row.createCell(2).setCellValue("Имя");
        row.createCell(3).setCellValue("Отчество");
        row.createCell(4).setCellValue("ДатаРождения");


        for (RPview rPview : list) {
            if (!rPview.checkRPviewRodCorrect()) {//подходит для фармата выгрузки NULL!!!!

                    row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(rPview.getId_r_p_rod());
                    row.createCell(1).setCellValue(rPview.getFam_rod());
                    row.createCell(2).setCellValue(rPview.getName_rod());
                    row.createCell(3).setCellValue(rPview.getOtch_rod());
                    row.createCell(4).setCellValue(rPview.getData_rod());

            }
        }


        ByteArrayOutputStream b = new ByteArrayOutputStream();
        workbook.write(b);

        resp.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "rod_prava_parents.xls");
        resp.setHeader(headerKey, headerValue);
        resp.setContentLength(b.size());
        resp.getOutputStream().write(b.toByteArray());
        InputStream in = new ByteArrayInputStream(b.toByteArray());
        return IOUtils.toByteArray(in);
    }

    @GetMapping(
            value = "/rostzap_child_r_p",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody
    byte[] r_p_getRostov_Child(
            HttpServletResponse resp,
            Model model) throws IOException {

        // формируем из файла экземпляр HSSFWorkbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        // создание листа с названием "Просто лист"
        HSSFSheet sheet = workbook.createSheet("list1");

        List<RPview> list = rPviewService.findAll2(); //полученные данные

        // счетчик для строк
        int rowNum = 0;

        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("Id");
        row.createCell(1).setCellValue("Фамилия");
        row.createCell(2).setCellValue("Имя");
        row.createCell(3).setCellValue("Отчество");
        row.createCell(4).setCellValue("ДатаРождения");

        for (RPview rPview : list) {
            if (!rPview.checkRPviewChildCorrect()) {//подходит для фармата выгрузки
                row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rPview.getId_child());
                row.createCell(1).setCellValue(rPview.getFam_child());
                row.createCell(2).setCellValue(rPview.getName_child());
                row.createCell(3).setCellValue(rPview.getOtch_child());
                row.createCell(4).setCellValue(rPview.getData_roj());
            }
        }

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        workbook.write(b);

        resp.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "rod_prava_child.xls");
        resp.setHeader(headerKey, headerValue);
        resp.setContentLength(b.size());
        resp.getOutputStream().write(b.toByteArray());
        InputStream in = new ByteArrayInputStream(b.toByteArray());
        return IOUtils.toByteArray(in);
    }

    //ПОДГРУЗИТЬ СНИЛС
    @GetMapping("/set_snils_r_p")
    public String set_snils_r_p(
            Model model) {
        return "set_snils_r_p";
    }

    //ПОДГРУЗИТЬ СНИЛС Добавить родителей
    @PostMapping(value = "/download_r_p/set_parent_snils", produces = "text/plain")
    public @ResponseBody
    String r_p_set_parent_snils(
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

        Map<String, Map<String, String>> mapFiles2 = new LinkedHashMap<>(); //для отправки ошибки

        for (Part fp :
                filePart) {

            FileInputStream fileContent =
                    fileContent = (FileInputStream) fp.getInputStream();

            Poixls_snils poixls = new Poixls_snils(fileContent);

            for (int i = 1; i < poixls.getEndRowParent(0); i++) {
                SnilsXls snilsXls = poixls.getSnilsXls(i);
                RProd rProd = rProdService.findById(Long.valueOf(snilsXls.getId()));
                rProd.setSnils_rod(snilsXls.getSnils());
                rProdService.save(rProd);
            }

        }

        return mapFiles2.toString();
    }

    //ПОДГРУЗИТЬ СНИЛС Добавить детей
    @PostMapping(value = "/download_r_p/set_child_snils", produces = "text/plain")
    public @ResponseBody
    String r_p_set_child_snils(
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

        Map<String, Map<String, String>> mapFiles2 = new LinkedHashMap<>(); //для отправки ошибки

        for (Part fp :
                filePart) {

            FileInputStream fileContent =
                    fileContent = (FileInputStream) fp.getInputStream();

            Poixls_snils poixls = new Poixls_snils(fileContent);

            for (int i = 1; i < poixls.getEndRowParent(0); i++) {
                try{
                    SnilsXls snilsXls = poixls.getSnilsXls(i);
                    RPchild rPchild = rPchildService.findById(Long.valueOf(snilsXls.getId()));
                    rPchild.setSnils_child(snilsXls.getSnils());
                    rPchildService.save(rPchild);
                }
                catch (Exception e){
                    System.out.println("ошибка: " + e);
                }

            }

        }

        return mapFiles2.toString();
    }


    //Сверка с xml
    @GetMapping(
            value = "/protocol_r_p"
            //produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody
    String protocol_r_p(
            HttpServletResponse resp,
            Model model) throws IOException, Exception {

        // формируем из файла экземпляр HSSFWorkbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        // создание листа с названием "Просто лист"
        HSSFSheet sheet = workbook.createSheet("list1");
        Row row;
        // счетчик для строк
        int rowNum = 0;
        row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("Имя файла");
        row.createCell(1).setCellValue("Фамилия");
        row.createCell(2).setCellValue("Имя");
        row.createCell(3).setCellValue("Отчество");
        row.createCell(4).setCellValue("Дата рождения");
        row.createCell(5).setCellValue("СНИЛС");
        row.createCell(6).setCellValue("Паспорт");
        row.createCell(7).setCellValue("Адрес регистрации");

        row.createCell(8).setCellValue("Наименование судебного органа");
        row.createCell(9).setCellValue("Дата решения");
        row.createCell(10).setCellValue("Дата вступления в законную силу");
        row.createCell(11).setCellValue("Результат");

        row.createCell(12).setCellValue("Дата рождения");
        row.createCell(13).setCellValue("СНИЛС");
        row.createCell(14).setCellValue("Фамилия");
        row.createCell(15).setCellValue("Имя");
        row.createCell(16).setCellValue("Отчество");
        row.createCell(17).setCellValue("Документ-основание");
        row.createCell(18).setCellValue("Дата решения восстановления");
        row.createCell(19).setCellValue("Дата вступления в законную силу восстановления");

        // Создать файловый объект
        File f = new File("U:");
        //File f = new File("U:/1/");

        // Создать FileFilter

        FileFilter filter = new FileFilter() {
            public boolean accept(File f)
            {
                return f.getName().endsWith("xml") || f.getName().endsWith("XML");
            }
        };

        File[] files = f.listFiles(filter); //получить только xml
        if(files==null || files.length<=0 ){
            return "Файлы отсутствуют!";
        }

        Xml10kParentModel xml10kParentModel;

        List<String> stringErr = new ArrayList<>();

        for (File file:
            files) {
            try {
                Xml10kRequestParser xml10kRequestParser = new Xml10kRequestParser();
                xml10kParentModel = xml10kRequestParser.parse(file);

                String s = "Данные отсутствуют";

                List<RPview> rPviews = null;
                if(!rPviewService.findByParentsFio(
                        xml10kParentModel.getSurname(),
                        xml10kParentModel.getName(),
                        xml10kParentModel.getMiddlename()).isEmpty()){
                    rPviews = rPviewService.findByParentsFio(xml10kParentModel.getSurname(),
                            xml10kParentModel.getName(),
                            xml10kParentModel.getMiddlename());
                } else if (!rPviewService.findByParentsSnils(xml10kParentModel.getSnils()).isEmpty()){
                    rPviews = rPviewService.findByParentsSnils(xml10kParentModel.getSnils());
                }

                if(rPviews!=null) {
                    if (rPviews.size() > 0) {
                        //boolean check_parents = true;
                        for (RPview rPview :
                                rPviews) {
                            if(rPview.datePast2004()) {//дата после 11.05.2004
                                //if (check_parents) {
                                row = sheet.createRow(rowNum++);
                                row.createCell(0).setCellValue(file.getName());
                                row.createCell(1).setCellValue(rPview.getFam_rod());
                                row.createCell(2).setCellValue(rPview.getName_rod());
                                row.createCell(3).setCellValue(rPview.getOtch_rod());
                                row.createCell(4).setCellValue(rPview.getData_rod());
                                row.createCell(5).setCellValue(rPview.getSnils_rod());
                                row.createCell(6).setCellValue(rPview.getPasport());
                                row.createCell(7).setCellValue(rPview.getAdres());

                                row.createCell(8).setCellValue(rPview.getNamedoc());
                                row.createCell(9).setCellValue(rPview.getData_resh());
                                row.createCell(10).setCellValue(rPview.getData_vst());
                                row.createCell(11).setCellValue(rPview.getResult());

                                row.createCell(12).setCellValue(rPview.getData_roj());
                                row.createCell(13).setCellValue(rPview.getSnils_child());
                                row.createCell(14).setCellValue(rPview.getFam_child());
                                row.createCell(15).setCellValue(rPview.getName_child());
                                row.createCell(16).setCellValue(rPview.getOtch_child());
                                row.createCell(17).setCellValue(rPview.getDoc_osn());
                                row.createCell(18).setCellValue(rPview.getData_resh_vos());
                                row.createCell(19).setCellValue(rPview.getData_vst_vos());
                            }
                        }
                    }
                }
            } catch (Exception e){
                stringErr.add(file.getName());
            }

        }

        //лист с непроверенными xml
        sheet = workbook.createSheet("listErr");
        int row1=0;
        for (String s:
             stringErr) {
            row = sheet.createRow(row1++);
            row.createCell(0).setCellValue(s);
        }



        String fileName = new Date().toString().replaceAll(" ","").replaceAll(":","")+".xls";
        try (FileOutputStream out = new FileOutputStream(new File("U:/LRP/" +
                //new Date().getTime()+".xls"))) {
                fileName))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
            return "Не удалось создать файл " + fileName;
        }

        String ret;
        ret = "Создан протокол с именем - " + fileName;
        if(stringErr.size()>1){ret += ("\n" + "Удалось проверить не все файлы обратитесь к системному администратору");}
        return ret ;
    }


    //ПОИСК
    @GetMapping(
            value = "r_p_view_find"
    )
    public String r_p_view_find(
            Model model) {
        return "r_p_change/r_p_view_find";
    }

    @GetMapping("findview")
    public String findview(
            @RequestParam(value = "fam_rod", defaultValue = "") String fam_rod,
            @RequestParam(value = "name_rod", defaultValue = "") String name_rod,
            @RequestParam(value = "otch_rod", defaultValue = "") String otch_rod,
            @RequestParam(value = "data_rod", defaultValue = "") String data_rod,
            @RequestParam(value = "snils_rod", defaultValue = "") String snils_rod,
            @RequestParam(value = "pasport", defaultValue = "") String pasport,
            @RequestParam(value = "adres", defaultValue = "") String adres,

            @RequestParam(value = "namedoc", defaultValue = "") String namedoc,
            @RequestParam(value = "data_resh", defaultValue = "") String data_resh,
            @RequestParam(value = "data_vst", defaultValue = "") String data_vst,
            @RequestParam(value = "result", defaultValue = "") String result,

            @RequestParam(value = "fam_child", defaultValue = "") String fam_child,
            @RequestParam(value = "name_child", defaultValue = "") String name_child,
            @RequestParam(value = "otch_child", defaultValue = "") String otch_child,
            @RequestParam(value = "data_roj", defaultValue = "") String data_roj,
            @RequestParam(value = "snils_child", defaultValue = "") String snils_child,
            @RequestParam(value = "doc_osn", defaultValue = "") String doc_osn,
            @RequestParam(value = "data_resh_vos", defaultValue = "") String data_resh_vos,
            @RequestParam(value = "data_vst_vos", defaultValue = "") String data_vst_vos,

            Model model) {
        Long idfiles = -1l;

        List<RPview> rPviews1 = rPviewService.findByView(fam_rod, name_rod, otch_rod, data_rod, snils_rod, pasport, adres,
                namedoc, data_resh, data_vst, result,
                fam_child, name_child, otch_child, data_roj, snils_child, doc_osn, data_resh_vos, data_vst_vos);

        //List<RPview> rPviews = rPviewService.findByView2(fam_rod, snils_rod);

        String message = "Найдено " + rPviews1.size() + " совпадений";
        List<RPview> rPviews = new ArrayList<>();
        if(rPviews1.size()>500){
            for (int i = 0; i < 500; i++) {
                rPviews.add(rPviews1.get(i));
            }
            message = "Показаны первые " + rPviews.size() + " совпадений";
        }else {
            rPviews = rPviews1;
        };
        if(rPviews.size()==0){
            message = "Совпадений не найдено";
        }
        model.addAttribute("rpalls", rPviews);
        model.addAttribute("idfiles", idfiles);
        model.addAttribute("message", message);
        return "r_p_change/frag/r_p_all :: r_p_all2";
    }

}
