package ru.pfr.model.RP;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name = "r_p_child")
public class RPchild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_r_p_child")
    private Long id_child;

    @Column(name = "data_roj")
    private String data_roj;

    @Column(name = "snils_child")
    private String snils_child;

    @Column(name = "fam_child")
    private String fam_child;

    @Column(name = "name_child")
    private String name_child;

    @Column(name = "otch_child")
    private String otch_child;

    @Column(name = "doc_osn")
    private String doc_osn;

    @Column(name = "data_resh_vos")
    private String data_resh_vos;

    @Column(name = "data_vst_vos")
    private String data_vst_vos;

    @Column(name = "primechanie")
    private String primechanie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_r_p_doc")
    private RPdoc rPdoc;


    public List<String> checksErr(List<String> err) {
        if(!Pattern.matches("^\\d{3}-\\d{3}-\\d{3} \\d{2}$",snils_child)){
            err.add("Неверный формат СНИЛСРебенка");
        }
        if(!Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$",data_roj)){
            err.add("Неверный формат ДатарождРебенка");
        }
        if(!Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$",data_resh_vos)){
            err.add("Неверный формат Дата Решения восстановления");
        }
        if(!Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$",data_vst_vos)){
            err.add("Неверный формат Дата вступления восстановления");
        }

        //при появлении ф и о добавить и к ним ограничения
        //...
        return err;
    }

    public RPchild() {
    }

    public RPchild(String fam_child, String name_child, String otch_child, String data_roj, String snils_child, String doc_osn,
                   String data_resh_vos, String data_vst_vos, String primechanie, RPdoc rPdoc) {
        this.fam_child = fam_child;
        this.otch_child = otch_child;
        this.data_roj = data_roj;
        this.snils_child = snils_child;
        this.name_child = name_child;
        this.doc_osn = doc_osn;
        this.data_resh_vos = data_resh_vos;
        this.data_vst_vos = data_vst_vos;
        this.primechanie = primechanie;
        this.rPdoc = rPdoc;
    }

    public RPchild(Long id_child, String fam_child, String name_child, String otch_child, String data_roj, String snils_child, String doc_osn, String data_resh_vos, String data_vst_vos, String primechanie, RPdoc rPdoc) {
        this.fam_child = fam_child;
        this.otch_child = otch_child;
        this.id_child = id_child;
        this.data_roj = data_roj;
        this.snils_child = snils_child;
        this.name_child = name_child;
        this.doc_osn = doc_osn;
        this.data_resh_vos = data_resh_vos;
        this.data_vst_vos = data_vst_vos;
        this.primechanie = primechanie;
        this.rPdoc = rPdoc;
    }

    public Long getId_child() {
        return id_child;
    }

    public void setId_child(Long id_child) {
        this.id_child = id_child;
    }

    public String getData_roj() {
        return data_roj;
    }

    public void setData_roj(String data_roj) {
        this.data_roj = data_roj;
    }

    public String getSnils_child() {
        return snils_child;
    }

    public void setSnils_child(String snils_child) {
        this.snils_child = snils_child;
    }

    public String getName_child() {
        return name_child;
    }

    public void setName_child(String name_child) {
        this.name_child = name_child;
    }

    public String getDoc_osn() {
        return doc_osn;
    }

    public void setDoc_osn(String doc_osn) {
        this.doc_osn = doc_osn;
    }

    public String getPrimechanie() {
        return primechanie;
    }

    public void setPrimechanie(String primechanie) {
        this.primechanie = primechanie;
    }

    public RPdoc getrPdoc() {
        return rPdoc;
    }

    public void setrPdoc(RPdoc rPdoc) {
        this.rPdoc = rPdoc;
    }

    public String getData_resh_vos() {
        return data_resh_vos;
    }

    public void setData_resh_vos(String data_resh_vos) {
        this.data_resh_vos = data_resh_vos;
    }

    public String getData_vst_vos() {
        return data_vst_vos;
    }

    public void setData_vst_vos(String data_vst_vos) {
        this.data_vst_vos = data_vst_vos;
    }

    public String getFam_child() {
        return fam_child;
    }

    public void setFam_child(String fam_child) {
        this.fam_child = fam_child;
    }

    public String getOtch_child() {
        return otch_child;
    }

    public void setOtch_child(String otch_child) {
        this.otch_child = otch_child;
    }
}
