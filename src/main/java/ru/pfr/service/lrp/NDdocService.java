package ru.pfr.service.lrp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pfr.model.ND.NDdoc;
import ru.pfr.repositories.lrp.NDdocRepository;

import java.util.List;

@Service
public class NDdocService {

    @Autowired
    NDdocRepository nDdocRepository;

    public List<NDdoc> findAll() {
        return nDdocRepository.findAll();
    }

    public void save(NDdoc nDdoc) {
        nDdocRepository.save(nDdoc);
    }


}
