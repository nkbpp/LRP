package ru.pfr.CreateFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateFile {

    String path;
    String csvName;
    //String csv;

    FileOutputStream fos;
    OutputStreamWriter outputfile;

    InputStream in;

    File dir;
    File csv;

    public CreateFile(int type) {
        switch (type){
            case 1:{
                path = "./csv/";
                csvName = "41_opeka_" + tekDate() + ".csv";
                dir =new File(path);
                dir.mkdir();
                //csv = path + csvName;
                csv = new File(dir,csvName);
            }break;
        }

        for (File myFile :dir.listFiles() ) //удалить старые файлы
            if (myFile.isFile()) myFile.delete();

        //создаем файл
        try {
            fos = new FileOutputStream(csv);
            outputfile = new OutputStreamWriter(fos,
                    "Windows-1251");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public OutputStreamWriter getOutputfile(){
        return this.outputfile;
    }


    private String tekDate(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }

    public HttpServletResponse getResp(HttpServletResponse resp){
        FileInputStream fis;
        try {
            fis = new FileInputStream(csv);
            //ститать файл в массив байт
            byte[] buffer = new byte[fis.available()];
            // чтение файла в буфер
            fis.read(buffer, 0, fis.available());
            fis.close();
            resp.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", csvName);
            resp.setHeader(headerKey, headerValue);
            resp.getOutputStream().write(buffer);
            in = new ByteArrayInputStream(buffer);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public InputStream getInputStream(){
        return this.in;
    }

}
