package ru.pfr.repositories.lrp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pfr.model.RP.RPchild;
import ru.pfr.model.RP.RPdoc;
import ru.pfr.model.RP.RProd;

import java.util.List;
import java.util.Optional;

public interface RPchildRepository extends JpaRepository<RPchild, Long> {

    public List<RPchild> findAll();

    public Optional<RPchild> findById(Long l);

    @Query(
            value = "select * " +
                    "from r_p_child where id_r_p_doc = ?1",
            nativeQuery = true)
    public List<RPchild> findByRPdoc(Long i);
}