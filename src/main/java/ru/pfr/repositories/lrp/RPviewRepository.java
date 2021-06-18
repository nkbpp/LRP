package ru.pfr.repositories.lrp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pfr.model.RP.RPview;

import java.util.Date;
import java.util.List;

public interface RPviewRepository extends JpaRepository<RPview, Long> {

    public List<RPview> findAll();

    @Query(
            value = "select * from r_p ",
            nativeQuery = true)
    public List<RPview> findAll2();

    @Query(
            value = "select * " +
                    "from r_p " +
                        "where snils_rod = ?1",
            nativeQuery = true)
    public List<RPview> findByParentsSnils(String snils);

    @Query(
            value = "select * " +
                    "from r_p " +
                    "where upper(fam_rod) LIKE %?1% and upper(name_rod) LIKE %?2% and upper(otch_rod) LIKE %?3% ",
            nativeQuery = true)
    public List<RPview> findByParentsFio(String f, String i, String o);

    @Query(
            value = "select * " +
                    "from r_p " +
                    "where fam_rod LIKE %?1%",
            nativeQuery = true)
    public List<RPview> findByParentsFio(String f);

    @Query(
            value = "select * " +
                    "from r_p " +
                    "where name_rod LIKE %?1% and otch_rod LIKE %?2%",
            nativeQuery = true)
    public List<RPview> findByParentsFio(String i, String o);

    @Query(
            value = "select * " +
                    "from r_p " +
                    "where snils_child = ?1",
            nativeQuery = true)
    public List<RPview> findByChildSnils(String snils);

    @Query(
            value = "select * " +
                    "from r_p " +
                    "where id_files = ?1",
            nativeQuery = true)
    public List<RPview> findByFiles(Long i);

    @Query(
            value = "select * " +
                    "from r_p " +
                    "where  fam_child LIKE %?1%  and name_child LIKE %?2% and otch_child LIKE %?3%",
            nativeQuery = true)
    public List<RPview> findByChildFio(String f, String i, String o);

    @Query(
            value = "select * " +
                    "from r_p " +
                    "where id_r_p_child = ?1",
            nativeQuery = true)
    public List<RPview> findByID(Long i);

    @Query(
            value = "select * " +
                    "from r_p where " +
                    "(length(?1) = 0 or fam_rod like %?1%) and " +
                    "(length(?2) = 0 or snils_rod like %?2%)",
            nativeQuery = true)
    public List<RPview> findByView2(String fam_rod, String snils_rod);

    @Query(
            value = "select * " +
                    "from r_p where " +
                    "(length(?1) = 0 or fam_rod like %?1%) and " +
                    "(length(?2) = 0 or name_rod like %?2%) and " +
                    "(length(?3) = 0 or otch_rod like %?3%) and " +
                    "(length(?4) = 0 or data_rod like %?4%) and " +
                    "(length(?5) = 0 or snils_rod like %?5%) and " +
                    "(length(?6) = 0 or pasport like %?6%) and " +
                    "(length(?7) = 0 or adres like %?7%) and " +

                    "(length(?8) = 0 or name_doc like %?8%) and " +
                    "(length(?9) = 0 or data_resh like %?9%) and " +
                    "(length(?10) = 0 or data_vst like %?10%) and " +
                    "(length(?11) = 0 or result like %?11%) and " +

                    "(length(?12) = 0 or fam_child like %?12%) and " +
                    "(length(?13) = 0 or name_child like %?13%) and " +
                    "(length(?14) = 0 or otch_child like %?14%) and " +
                    "(length(?15) = 0 or data_roj like %?15%) and " +
                    "(length(?16) = 0 or snils_child like %?16%) and " +
                    "(length(?17) = 0 or doc_osn like %?17%) and " +
                    "(length(?18) = 0 or data_resh_vos like %?18%) and " +
                    "(length(?19) = 0 or data_vst_vos like %?19%)",
            nativeQuery = true)
    public List<RPview> findByView(String fam_rod, String name_rod, String otch_rod, String data_rod,
                                   String snils_rod, String pasport, String adres,

                                   String namedoc, String data_resh, String data_vst, String result,

                                   String fam_child, String name_child, String otch_child, String data_roj,
                                   String snils_child, String doc_osn, String data_resh_vos, String data_vst_vos);

}
