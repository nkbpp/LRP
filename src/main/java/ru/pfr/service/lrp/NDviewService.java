package ru.pfr.service.lrp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pfr.model.ND.NDview;
import ru.pfr.repositories.lrp.NDviewRepository;

import java.util.List;

@Service
public class NDviewService {

    @Autowired
    NDviewRepository nDviewRepository;

    public List<NDview> findAll() {
        return nDviewRepository.findAll();
    }

    public List<NDview> findAll2() {
        return nDviewRepository.findAll2();
    }

}
