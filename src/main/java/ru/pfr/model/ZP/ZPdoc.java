package ru.pfr.model.ZP;

import ru.pfr.model.RP.RPdoc;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name = "z_p_doc")
public class ZPdoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_z_p_doc")
    private Long id_doc;

    @Column(name = "name_doc")
    private String namedoc;

    @Column(name = "data_resh")
    private String data_resh;

    @Column(name = "data_vst")
    private String data_vst;

    @Column(name = "recvisit")
    private String recvisit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_z_p_rod")
    private ZProd zProd;

    public List<String> checksErr(List<String> err) {

        return err;
    }


    public ZPdoc ZPdocSlijanie(ZPdoc zPdoc) {
        if (this.namedoc.equals("") && this.data_resh.equals("") && this.data_vst.equals("") && this.recvisit.equals("")){
            return zPdoc;
        } else {
            this.namedoc = this.namedoc.equals("")?zPdoc.namedoc:this.namedoc;
            this.data_resh = this.data_resh.equals("")?zPdoc.data_resh:this.data_resh;
            this.data_vst = this.data_vst.equals("")?zPdoc.data_vst:this.data_vst;
            this.recvisit = this.recvisit.equals("")?zPdoc.recvisit:this.recvisit;
            return this;
        }
    }

    public ZPdoc() {
    }

    public ZPdoc(String namedoc, String data_resh, String data_vst, String recvisit, ZProd zProd) {
        this.namedoc = namedoc;
        this.data_resh = data_resh;
        this.data_vst = data_vst;
        this.recvisit = recvisit;
        this.zProd = zProd;
    }

    public ZPdoc(Long id_doc, String namedoc, String data_resh, String data_vst, String recvisit, ZProd zProd) {
        this.id_doc = id_doc;
        this.namedoc = namedoc;
        this.data_resh = data_resh;
        this.data_vst = data_vst;
        this.recvisit = recvisit;
        this.zProd = zProd;
    }

    public Long getId_doc() {
        return id_doc;
    }

    public void setId_doc(Long id_doc) {
        this.id_doc = id_doc;
    }

    public String getNamedoc() {
        return namedoc;
    }

    public void setNamedoc(String namedoc) {
        this.namedoc = namedoc;
    }

    public String getData_resh() {
        return data_resh;
    }

    public void setData_resh(String data_resh) {
        this.data_resh = data_resh;
    }

    public String getData_vst() {
        return data_vst;
    }

    public void setData_vst(String data_vst) {
        this.data_vst = data_vst;
    }

    public String getRecvisit() {
        return recvisit;
    }

    public void setRecvisit(String recvisit) {
        this.recvisit = recvisit;
    }

    public ZProd getzProd() {
        return zProd;
    }

    public void setzProd(ZProd zProd) {
        this.zProd = zProd;
    }
}
