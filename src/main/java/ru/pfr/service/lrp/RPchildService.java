package ru.pfr.service.lrp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pfr.model.RP.RPchild;
import ru.pfr.model.RP.RPdoc;
import ru.pfr.model.RP.RProd;
import ru.pfr.repositories.lrp.RPchildRepository;

import java.util.List;

@Transactional
@Service
public class RPchildService {

    @Autowired
    RPchildRepository rPchildRepository;

    public List<RPchild> findAll() {
        return rPchildRepository.findAll();
    }


    public void save(RPchild rPchild) {
        rPchildRepository.save(rPchild);
    }

    public RPchild findById(Long id) {
        return rPchildRepository.findById(id).get();
    }

    public List<RPchild> findByRPdoc(Long i) {
        return rPchildRepository.findByRPdoc(i);
    }
}
