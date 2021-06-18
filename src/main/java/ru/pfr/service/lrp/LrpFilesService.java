package ru.pfr.service.lrp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pfr.model.lrp.LrpFiles;
import ru.pfr.repositories.lrp.LrpFilesRepository;

import java.util.List;

@Service
public class LrpFilesService {

    @Autowired
    LrpFilesRepository lrpFilesRepository;

    public List<LrpFiles> findAll() {
        return lrpFilesRepository.findAll();
    }

    @Transactional
    public void save(LrpFiles lrpFiles) {
        lrpFilesRepository.save(lrpFiles);
    }

    @Transactional
    public void delete(Long id) {
        lrpFilesRepository.deleteById(id);
    }

}
