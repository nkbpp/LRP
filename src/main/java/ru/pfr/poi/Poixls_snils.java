package ru.pfr.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import ru.pfr.model.ND.NDdoc;
import ru.pfr.model.ND.NDrod;
import ru.pfr.model.SnilsXls.SnilsXls;
import ru.pfr.model.lrp.LrpFiles;

import java.io.FileInputStream;
import java.io.IOException;

public class Poixls_snils extends Poixls {

    public Poixls_snils(FileInputStream fileContent) throws IOException {
        row_checkCorrect1 = -1;
        row_checkCorrect2 = -1;
        cell_checkCorrect = -1;

        StartRow = 0; //row отчет с 0
        cell_Child = -1;
        cell_Rod = -1;

        this.workbook = new HSSFWorkbook(fileContent);
        this.sheet = workbook.getSheetAt(0);
    }

    public SnilsXls getSnilsXls(int row){
        return new SnilsXls(getValue(row,0),getValue(row,5));
    }

}
