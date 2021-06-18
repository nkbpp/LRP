package ru.pfr.service.lrp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pfr.model.ZP.ZPdoc;
import ru.pfr.repositories.lrp.ZPdocRepository;


import java.util.List;

@Service
public class ZPdocService {

    @Autowired
    ZPdocRepository zPdocRepository;

    public List<ZPdoc> findAll() {
        return zPdocRepository.findAll();
    }

    public void save(ZPdoc zPdoc) {
        zPdocRepository.save(zPdoc);
    }


}
