package ru.pfr.repositories.lrp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pfr.model.RP.RPchild;
import ru.pfr.model.RP.RProd;
import ru.pfr.model.RP.RPview;
import ru.pfr.model.lrp.LrpFiles;

import java.util.List;
import java.util.Optional;

public interface RProdRepository extends JpaRepository<RProd, Long> {

    public List<RProd> findAll();

    public Optional<RProd> findById(Long l);

    @Query(
            value = "select * " +
                    "from r_p_rod " +
                    "where snils_rod = ?1",
            nativeQuery = true)
    public List<RProd> findByParentsSnils(String snils);

    @Query(
            value = "select * " +
                    "from r_p_rod " +
                    "where snils_rod = ?1 and id_files <> ?2",
            nativeQuery = true)
    public List<RProd> findByParentsSnilsFile(String snils, Long i);

    @Query(
            value = "select * " +
                    "from r_p_rod " +
                    "where fam_rod = ?1 and name_rod = ?2 and otch_rod = ?3",
            nativeQuery = true)
    public List<RProd> findByParentsFio(String f, String i, String o);

    @Query(
            value = "select * " +
                    "from r_p_rod where id_files = ?1",
            nativeQuery = true)
    public List<RProd> findByFiles(Long i);
}
