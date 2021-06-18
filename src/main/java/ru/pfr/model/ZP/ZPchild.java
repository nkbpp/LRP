package ru.pfr.model.ZP;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name = "z_p_child")
public class ZPchild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_z_p_child")
    private Long id_child;

    @Column(name = "name_child")
    private String name_child;

    @Column(name = "data_roj")
    private String data_roj;

    @Column(name = "svidetelstvo")
    private String svidetelstvo;

    @Column(name = "snils_child")
    private String snils_child;

    @Column(name = "primechanie")
    private String primechanie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_z_p_doc")
    private ZPdoc zPdoc;

    public List<String> checksErr(List<String> err) {
        return err;
    }

    public ZPchild(ZPdoc zPdoc) {
    }

    public ZPchild(String name_child, String data_roj, String svidetelstvo, String snils_child, String primechanie, ZPdoc zPdoc) {
        this.name_child = name_child;
        this.data_roj = data_roj;
        this.svidetelstvo = svidetelstvo;
        this.snils_child = snils_child;
        this.primechanie = primechanie;
        this.zPdoc = zPdoc;
    }

    public ZPchild(Long id_child, String name_child, String data_roj, String svidetelstvo, String snils_child, String primechanie, ZPdoc zPdoc) {
        this.id_child = id_child;
        this.name_child = name_child;
        this.data_roj = data_roj;
        this.svidetelstvo = svidetelstvo;
        this.snils_child = snils_child;
        this.primechanie = primechanie;
        this.zPdoc = zPdoc;
    }

    public Long getId_child() {
        return id_child;
    }

    public void setId_child(Long id_child) {
        this.id_child = id_child;
    }

    public String getName_child() {
        return name_child;
    }

    public void setName_child(String name_child) {
        this.name_child = name_child;
    }

    public String getData_roj() {
        return data_roj;
    }

    public void setData_roj(String data_roj) {
        this.data_roj = data_roj;
    }

    public String getSvidetelstvo() {
        return svidetelstvo;
    }

    public void setSvidetelstvo(String svidetelstvo) {
        this.svidetelstvo = svidetelstvo;
    }

    public String getSnils_child() {
        return snils_child;
    }

    public void setSnils_child(String snils_child) {
        this.snils_child = snils_child;
    }

    public String getPrimechanie() {
        return primechanie;
    }

    public void setPrimechanie(String primechanie) {
        this.primechanie = primechanie;
    }

    public ZPdoc getzPdoc() {
        return zPdoc;
    }

    public void setzPdoc(ZPdoc zPdoc) {
        this.zPdoc = zPdoc;
    }
}
