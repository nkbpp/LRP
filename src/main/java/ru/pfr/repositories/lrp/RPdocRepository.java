package ru.pfr.repositories.lrp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pfr.model.RP.RPdoc;
import ru.pfr.model.RP.RProd;
import ru.pfr.model.ZP.ZPview;

import java.util.List;
import java.util.Optional;

public interface RPdocRepository extends JpaRepository<RPdoc, Long> {

    public List<RPdoc> findAll();

    public Optional<RPdoc> findById(Long l);

    @Query(
            value = "select * " +
                    "from r_p_doc where id_r_p_rod = ?1",
            nativeQuery = true)
    public List<RPdoc> findByRProd(Long i);

}
