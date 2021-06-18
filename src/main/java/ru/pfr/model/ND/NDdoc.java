package ru.pfr.model.ND;

import javax.persistence.*;

@Entity
@Table(name = "n_d_doc")
public class NDdoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_n_d_doc")
    private Long id_doc;

    @Column(name = "name_doc")
    private String namedoc;

    @Column(name = "data_resh")
    private String data_resh;

    @Column(name = "data_vst")
    private String data_vst;

    @Column(name = "primechanie")
    private String primechanie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_n_d_rod")
    private NDrod nDrod;

    public NDdoc() {
    }

    public NDdoc(String namedoc, String data_resh, String data_vst, String primechanie, NDrod nDrod) {
        this.namedoc = namedoc;
        this.data_resh = data_resh;
        this.data_vst = data_vst;
        this.primechanie = primechanie;
        this.nDrod = nDrod;
    }

    public NDdoc(Long id_doc, String namedoc, String data_resh, String data_vst, String primechanie, NDrod nDrod) {
        this.id_doc = id_doc;
        this.namedoc = namedoc;
        this.data_resh = data_resh;
        this.data_vst = data_vst;
        this.primechanie = primechanie;
        this.nDrod = nDrod;
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

    public String getPrimechanie() {
        return primechanie;
    }

    public void setPrimechanie(String primechanie) {
        this.primechanie = primechanie;
    }

    public NDrod getnDdoc() {
        return nDrod;
    }

    public void setnDdoc(NDrod nDrod) {
        this.nDrod = nDrod;
    }
}
