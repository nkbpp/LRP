package ru.pfr.poi;

import org.apache.poi.hssf.usermodel.*;
import ru.pfr.model.lrp.LrpFiles;
import ru.pfr.model.RP.RPchild;
import ru.pfr.model.RP.RPdoc;
import ru.pfr.model.RP.RProd;

import java.io.FileInputStream;
import java.io.IOException;

public class Poixls_rp extends Poixls {

    public Poixls_rp(FileInputStream fileContent) throws IOException {
        row_checkCorrect1 = 7;
        row_checkCorrect2 = 8;
        cell_checkCorrect = 0;

        StartRow = 8;
        cell_Child = 13;
        cell_Rod = 1;

        this.workbook = new HSSFWorkbook(fileContent);
        this.sheet = workbook.getSheetAt(0);
    }

/*    @Override
    public <T> T getRod(int row, LrpFiles lrpFiles) {
        return (T) new RProd(getValue(row,1),getValue(row,2),getValue(row,3),getValue(row,4),getValue(row,5),lrpFiles);
    }*/

    public RProd getRProd(int row, LrpFiles lrpFiles, RProd old){
        if(
                old!=null &&
                old.getFam().equals(getValue(row,1)) &&
                old.getName().equals(getValue(row,2)) &&
                old.getOtch().equals(getValue(row,3)) &&
                old.getData_rod().equals(getValue(row,4)) &&
                old.getSnils_rod().equals(getValue(row,5))
        ) return old;
        return new RProd(getValue(row,1),
                         getValue(row,2),
                         getValue(row,3),//отчество
                         getValue(row,4),
                         getValue(row,5),
                         getValue(row,6),
                         getValue(row,7),
                         lrpFiles);
    }

    public RPdoc getRPDoc(int row, RProd rProd){
        return new RPdoc(getValue(row,8),getValue(row,9),getValue(row,10),getValue(row,11),
                getValue(row,12),rProd);
    }

    public RPchild getRPchild(int row, RPdoc rPdoc){
        return new RPchild(
                getValue(row,13),
                getValue(row,14),
                getValue(row,15),
                getValue(row,16),
                getValue(row,17),
                getValue(row,18),
                getValue(row,19),
                getValue(row,20),
                getValue(row,21),
                rPdoc);
    }

}
