package ru.pfr.service.lrp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pfr.model.RP.RPdoc;
import ru.pfr.model.RP.RProd;
import ru.pfr.model.RP.RPview;
import ru.pfr.model.lrp.LrpFiles;
import ru.pfr.repositories.lrp.RProdRepository;

import java.util.List;

@Transactional
@Service
public class RProdService {

    @Autowired
    RProdRepository rProdRepository;

    public List<RProd> findAll() {
        return rProdRepository.findAll();
    }


    public void save(RProd rProd) {
        rProdRepository.save(rProd);
    }

    public void delete(RProd rProd) {
        rProdRepository.delete(rProd);
    }

    public RProd findById(Long id) {
        return rProdRepository.findById(id).get();
    }

    public List<RProd> findByParentsFio(String f, String i, String o){
        return rProdRepository.findByParentsFio(f, i, o);
    };

    public List<RProd> findByParentsSnils(String snils){
        return rProdRepository.findByParentsSnils(snils);
    };

    public List<RProd> findByParentsSnilsFile(String snils, LrpFiles lrpFiles){
        return rProdRepository.findByParentsSnilsFile(snils, lrpFiles.getId_file());
    };

    public List<RProd> findByFiles(Long i) {
        return rProdRepository.findByFiles(i);
    }
}
