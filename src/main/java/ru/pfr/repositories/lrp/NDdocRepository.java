package ru.pfr.repositories.lrp;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pfr.model.ND.NDdoc;

import java.util.List;

public interface NDdocRepository extends JpaRepository<NDdoc, Long> {

    public List<NDdoc> findAll();

}
