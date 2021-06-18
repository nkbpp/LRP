package ru.pfr.service.lrp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pfr.model.RP.RPview;
import ru.pfr.repositories.lrp.RPviewRepository;

import java.util.List;

@Transactional
@Service
public class RPviewService {

    @Autowired
    RPviewRepository rPviewRepository;

    public List<RPview> findAll() {
        return rPviewRepository.findAll();
    }

    public List<RPview> findAll2() {
        return rPviewRepository.findAll2();
    }

    public List<RPview> findByParentsSnils(String snils){
        return rPviewRepository.findByParentsSnils(snils);
    };

    public List<RPview> findByParentsFio(String f, String i, String o){

        if(f.equals("БЕЛИК")){
            List<RPview> rPviews = rPviewRepository.findByParentsFio(f, i, o);
        }
        return rPviewRepository.findByParentsFio(f, i, o);
    };


    public List<RPview> findByChildSnils(String snils){
        return rPviewRepository.findByChildSnils(snils);
    };

    public List<RPview> findByFiles(Long id_files){
        return rPviewRepository.findByFiles(id_files);
    };

    public RPview findByID(Long id_child){
        return rPviewRepository.findByID(id_child).get(0);
    };

    public List<RPview> findByChildFio(String f, String i, String o){
        return rPviewRepository.findByChildFio(f, i, o);
    };

    public List<RPview> findByView(String fam_rod, String name_rod, String otch_rod, String data_rod,
                                   String snils_rod, String pasport, String adres,
                                   String namedoc, String data_resh, String data_vst, String result,
                                   String fam_child, String name_child, String otch_child, String data_roj,
                                   String snils_child, String doc_osn, String data_resh_vos, String data_vst_vos){
        return rPviewRepository.findByView(fam_rod, name_rod, otch_rod, data_rod, snils_rod, pasport, adres,
                namedoc, data_resh, data_vst, result,
                fam_child, name_child, otch_child, data_roj, snils_child, doc_osn, data_resh_vos, data_vst_vos);
    };

    public List<RPview> findByView2(String fam_rod, String snils_rod){
        return rPviewRepository.findByView2(fam_rod, snils_rod);
    };
}
