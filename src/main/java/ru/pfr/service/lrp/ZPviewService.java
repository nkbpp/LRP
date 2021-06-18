package ru.pfr.service.lrp;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pfr.model.ZP.ZPview;
import ru.pfr.repositories.lrp.ZPviewRepository;

import java.util.List;

@Service
public class ZPviewService {

    protected HSSFWorkbook workbook;
    protected HSSFSheet sheet;


    @Autowired
    ZPviewRepository zPviewRepository;

    public List<ZPview> findAll() {
        return zPviewRepository.findAll();
    }

    public List<ZPview> findAll2() {
        return zPviewRepository.findAll2();
    }

}
