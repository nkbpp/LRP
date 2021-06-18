package ru.pfr.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import ru.pfr.model.ND.NDdoc;
import ru.pfr.model.ND.NDrod;
import ru.pfr.model.ZP.ZPchild;
import ru.pfr.model.ZP.ZPdoc;
import ru.pfr.model.ZP.ZProd;
import ru.pfr.model.lrp.LrpFiles;

import java.io.FileInputStream;
import java.io.IOException;

public class Poixls_nd extends Poixls {

    public Poixls_nd(FileInputStream fileContent) throws IOException {
        row_checkCorrect1 = 7;
        row_checkCorrect2 = 8;
        cell_checkCorrect = 0;

        StartRow = 8; //row отчет с 0
        cell_Child = -1;
        cell_Rod = 1;

        this.workbook = new HSSFWorkbook(fileContent);
        this.sheet = workbook.getSheetAt(0);
    }

    public NDrod getNDrod(int row, LrpFiles lrpFiles){
        return new NDrod(getValue(row,1),getValue(row,2),getValue(row,3),getValue(row,4),getValue(row,5),lrpFiles);
    }

    public NDdoc getNDdoc(int row, NDrod nDrod){
        return new NDdoc(getValue(row,6),getValue(row,7),getValue(row,8),getValue(row,9),nDrod);
    }


}
