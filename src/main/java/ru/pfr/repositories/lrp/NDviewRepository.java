package ru.pfr.repositories.lrp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pfr.model.ND.NDview;

import java.util.List;

public interface NDviewRepository extends JpaRepository<NDview, Long> {

    public List<NDview> findAll();

    @Query(
            value = "select * " +
                    "from n_d ",
            nativeQuery = true)
    public List<NDview> findAll2();
}
