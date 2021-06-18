package ru.pfr.repositories.lrp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pfr.model.RP.RPview;
import ru.pfr.model.ZP.ZPview;

import java.util.List;

public interface ZPviewRepository extends JpaRepository<ZPview, Long> {

    public List<ZPview> findAll();

    @Query(
            value = "select * " +
                    "from z_p ",
            nativeQuery = true)
    public List<ZPview> findAll2();
}
