package ru.pfr.repositories.lrp;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pfr.model.lrp.LrpFiles;

import java.util.List;

public interface LrpFilesRepository extends JpaRepository<LrpFiles, Long> {
    public List<LrpFiles> findAll();

}