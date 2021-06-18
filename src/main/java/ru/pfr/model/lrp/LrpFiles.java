package ru.pfr.model.lrp;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class LrpFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_file")
    private Long id_file;

    @Column(name = "name_f")
    private String name_f;

    @Column(name = "type_f")
    private Integer type_f;

    @Column(name = "name_type_f")
    private String name_type_f;

    public LrpFiles() {
    }

    public LrpFiles(Long id_file, String name_f, Integer type_f, String name_type_f) {
        this.id_file = id_file;
        this.name_f = name_f;
        this.type_f = type_f;
        this.name_type_f = name_type_f;
    }

    public LrpFiles(String name_f, Integer type_f, String name_type_f) {
        this.name_f = name_f;
        this.type_f = type_f;
        this.name_type_f = name_type_f;
    }

    public Long getId_file() {
        return id_file;
    }

    public void setId_file(Long id_file) {
        this.id_file = id_file;
    }

    public String getName_f() {
        return name_f;
    }

    public void setName_f(String name_f) {
        this.name_f = name_f;
    }

    public Integer getType_f() {
        return type_f;
    }

    public void setType_f(Integer type_f) {
        this.type_f = type_f;
    }

    public String getName_type_f() {
        return name_type_f;
    }

    public void setName_type_f(String name_type_f) {
        this.name_type_f = name_type_f;
    }
}
