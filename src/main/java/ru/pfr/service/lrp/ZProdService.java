package ru.pfr.service.lrp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pfr.model.ZP.ZProd;
import ru.pfr.repositories.lrp.ZProdRepository;

import java.util.List;

@Service
public class ZProdService {

    @Autowired
    ZProdRepository zProdRepository;

    public List<ZProd> findAll() {
        return zProdRepository.findAll();
    }

    public void save(ZProd zProd) {
        zProdRepository.save(zProd);
    }

}
