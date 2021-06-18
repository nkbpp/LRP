package ru.pfr.service.lrp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pfr.model.ZP.ZPchild;
import ru.pfr.repositories.lrp.ZPchildRepository;


import java.util.List;

@Service
public class ZPchildService {

    @Autowired
    ZPchildRepository zPchildRepository;

    public List<ZPchild> findAll() {
        return zPchildRepository.findAll();
    }

    public void save(ZPchild zPchild) {
        zPchildRepository.save(zPchild);
    }

}
