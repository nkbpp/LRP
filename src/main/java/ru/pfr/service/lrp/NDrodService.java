package ru.pfr.service.lrp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pfr.model.ND.NDrod;
import ru.pfr.repositories.lrp.NDrodRepository;

import java.util.List;

@Service
public class NDrodService {

    @Autowired
    NDrodRepository nDrodRepository;

    public List<NDrod> findAll() {
        return nDrodRepository.findAll();
    }

    public void save(NDrod nDrod) {
        nDrodRepository.save(nDrod);
    }

}
