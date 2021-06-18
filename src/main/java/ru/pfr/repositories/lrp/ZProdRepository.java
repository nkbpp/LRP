package ru.pfr.repositories.lrp;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pfr.model.ZP.ZProd;

import java.util.List;

public interface ZProdRepository extends JpaRepository<ZProd, Long> {

    public List<ZProd> findAll();

}
