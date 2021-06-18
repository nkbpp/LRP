package ru.pfr.repositories.lrp;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pfr.model.ND.NDrod;

import java.util.List;

public interface NDrodRepository extends JpaRepository<NDrod, Long> {

    public List<NDrod> findAll();

}
