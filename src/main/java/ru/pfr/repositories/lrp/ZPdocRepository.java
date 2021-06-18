package ru.pfr.repositories.lrp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pfr.model.RP.RPdoc;
import ru.pfr.model.ZP.ZPdoc;
import ru.pfr.model.ZP.ZPview;

import java.util.List;

public interface ZPdocRepository extends JpaRepository<ZPdoc, Long> {

    public List<ZPdoc> findAll();




}
