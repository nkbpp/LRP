package ru.pfr.repositories.lrp;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pfr.model.RP.RPchild;
import ru.pfr.model.ZP.ZPchild;

import java.util.List;

public interface ZPchildRepository extends JpaRepository<ZPchild, Long> {

    public List<ZPchild> findAll();

}