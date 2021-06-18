package ru.pfr.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import ru.pfr.model.RP.RPchild;
import ru.pfr.model.RP.RPdoc;
import ru.pfr.model.RP.RProd;
import ru.pfr.model.ZP.ZPchild;
import ru.pfr.model.ZP.ZPdoc;
import ru.pfr.model.ZP.ZProd;
import ru.pfr.model.lrp.LrpFiles;

import java.io.FileInputStream;
import java.io.IOException;

public class Poixls_zp extends Poixls {

    public Poixls_zp(FileInputStream fileContent) throws IOException {
        row_checkCorrect1 = 7;
        row_checkCorrect2 = 8;
        cell_checkCorrect = 0;

        StartRow = 8; //row отчет с 0
        cell_Child = 10;
        cell_Rod = 1;

        this.workbook = new HSSFWorkbook(fileContent);
        this.sheet = workbook.getSheetAt(0);
    }

    public ZProd getZProd(int row, LrpFiles lrpFiles){
        return new ZProd(getValue(row,1),getValue(row,2),getValue(row,3),getValue(row,4),getValue(row,5),lrpFiles);
    }

    public ZPdoc getZPDoc(int row, ZProd zProd){
        return new ZPdoc(getValue(row,6),getValue(row,7),getValue(row,8),getValue(row,9),zProd);
    }

    public ZPchild getZPchild(int row, ZPdoc zPdoc){
        return new ZPchild(getValue(row,10),getValue(row,11),getValue(row,12),getValue(row,13),getValue(row,14),zPdoc);
    }

}
