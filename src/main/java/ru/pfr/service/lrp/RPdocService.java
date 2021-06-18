package ru.pfr.service.lrp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pfr.model.RP.RPdoc;
import ru.pfr.model.RP.RProd;
import ru.pfr.repositories.lrp.RPdocRepository;

import java.util.List;

@Transactional
@Service
public class RPdocService {

    @Autowired
    RPdocRepository rPdocRepository;

    public List<RPdoc> findAll() {
        return rPdocRepository.findAll();
    }

    public List<RPdoc> findByRProd(Long i) {
        return rPdocRepository.findByRProd(i);
    }

    @Transactional
    public void save(RPdoc rPdoc) {
        rPdocRepository.save(rPdoc);
    }

    public RPdoc findById(Long id) {
        return rPdocRepository.findById(id).get();
    }
}
