package ru.pfr.poi;

import org.apache.poi.hssf.usermodel.*;
import ru.pfr.model.RP.RPdoc;
import ru.pfr.model.RP.RProd;
import ru.pfr.model.lrp.LrpFiles;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class Poixls {
    protected HSSFWorkbook workbook;
    protected HSSFSheet sheet;

    //для проверки индексов 1==1
    protected int row_checkCorrect1;
    protected int row_checkCorrect2;
    protected int cell_checkCorrect;

    protected int StartRow; //с какой строки начинаются данные
    protected int cell_Child; // имя ребенка
    protected int cell_Rod; //имя родителя


    public String getValue (int row, int cell){
        HSSFRow my_row = sheet.getRow(row);
        if(my_row == null){return "";}
        HSSFCell myCell = my_row.getCell(cell);
        if(myCell == null){return "";}
        else if(HSSFCell.CELL_TYPE_STRING == myCell.getCellType())return  del_spec_symbol(myCell.getStringCellValue());
        else if(HSSFCell.CELL_TYPE_NUMERIC == myCell.getCellType()){
            if (HSSFDateUtil.isCellDateFormatted(myCell)) { //нормально считывать дату
                String pattern = "dd.MM.yyyy";
                DateFormat df = new SimpleDateFormat(pattern);
                return del_spec_symbol(df.format(myCell.getDateCellValue()));
            }
            return  del_spec_symbol(String.valueOf((int)myCell.getNumericCellValue()));
        }

        else if(HSSFCell.CELL_TYPE_BLANK == myCell.getCellType())return  "";
        else {
            return "";
            //return "!!!!!!!!!!!!!!!!!!!!!!!!!!!" + HSSFCell.CELL_TYPE_NUMERIC;
        } // проверьть вдруг неизвестный тип
    }

    private String del_spec_symbol(String s){
        while (s.contains("  ")) { //удалить посторяющиеся пробелы
            s = s.replaceAll("  ", " ");
        }
        //затем спец символы
        return s.replaceAll("\\t","").trim().replaceAll("\\n","").replaceAll("\\r","");
    }


    public boolean checkCorrectFile(){ // возможно лучше через getValue() тянуть
        double n,n2;
        try {
            HSSFRow my_row = sheet.getRow(row_checkCorrect1);
            HSSFCell myCell = my_row.getCell(cell_checkCorrect);
            try{
                n = myCell.getNumericCellValue();
            }catch (Exception e){
                n = Double.valueOf(myCell.getStringCellValue());
            }
            my_row = sheet.getRow(row_checkCorrect2);
            myCell = my_row.getCell(cell_checkCorrect);
            try{
                n2 = myCell.getNumericCellValue();
            }catch (Exception e){
                n2 = Double.valueOf(myCell.getStringCellValue());
            }
        }catch (Exception e){
            return false;
        }
        return n == n2 && n2 == 1;
    }

    public int getEndRowParent(){ //для nd
        return getEndRowParent(1);
    }

    public int getEndRowParent(int cell){ //для nd
        int row = StartRow;
        int row2 = row;

        boolean b = true;
        while(b){
            if(!getValue(++row,cell).equals("")){
                row2 = row+1;
            }else{
                b=false;
            }
        }
        return row2;
    }

    public int getEndRowChild(){
        int row = 8;
        boolean b = true;
        while(b){
            if(!getValue(++row,1).equals("")){

            }else{
                if(!getValue(row,cell_Child).equals("")){}
                else { b=false;}
            }
        }
        return row;
    }

    public boolean rowRodCorrect(int row){
        return !getValue(row,cell_Rod).equals("");
    }

/*    public boolean rowDocCorrect(int row){
        return !getValue(row,7).equals("");
    }*/
    public boolean rowChildCorrect(int row){
        return !getValue(row,cell_Child).equals("");
    }

    //public abstract <T> T getRod(int row, LrpFiles lrpFiles);

    //public abstract <T> T getDoc(int row, RProd rProd);

}
