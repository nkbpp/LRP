package ru.pfr.model.ND;

import ru.pfr.model.Views;

import javax.persistence.*;

@Entity
@Table(name = "n_d")
public class NDview extends Views {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_n_d_doc")
    private Long id_doc;

    @Column(name = "fam")
    private String fam;

    @Column(name = "data_rod")
    private String data_rod;

    @Column(name = "snils_rod")
    private String snils_rod;

    @Column(name = "pasport")
    private String pasport;

    @Column(name = "adres")
    private String adres;

    @Column(name = "name_doc")
    private String namedoc;

    @Column(name = "data_resh")
    private String data_resh;

    @Column(name = "data_vst")
    private String data_vst;

    @Column(name = "primechanie")
    private String primechanie;

    public NDview() {
    }

    public NDview(String fam, String data_rod, String snils_rod, String pasport, String adres, String namedoc, String data_resh, String data_vst, String primechanie) {
        this.fam = fam;
        this.data_rod = data_rod;
        this.snils_rod = snils_rod;
        this.pasport = pasport;
        this.adres = adres;
        this.namedoc = namedoc;
        this.data_resh = data_resh;
        this.data_vst = data_vst;
        this.primechanie = primechanie;
    }

    public NDview(Long id_doc, String fam, String data_rod, String snils_rod, String pasport, String adres, String namedoc, String data_resh, String data_vst, String primechanie) {
        this.id_doc = id_doc;
        this.fam = fam;
        this.data_rod = data_rod;
        this.snils_rod = snils_rod;
        this.pasport = pasport;
        this.adres = adres;
        this.namedoc = namedoc;
        this.data_resh = data_resh;
        this.data_vst = data_vst;
        this.primechanie = primechanie;
    }

    public Long getId_doc() {
        return id_doc;
    }

    public void setId_doc(Long id_doc) {
        this.id_doc = id_doc;
    }

    public String getFam() {
        return fam;
    }

    public void setFam(String fam) {
        this.fam = fam;
    }

    public String getData_rod() {
        return data_rod;
    }

    public void setData_rod(String data_rod) {
        this.data_rod = data_rod;
    }

    public String getSnils_rod() {
        return snils_rod;
    }

    public void setSnils_rod(String snils_rod) {
        this.snils_rod = snils_rod;
    }

    public String getPasport() {
        return pasport;
    }

    public void setPasport(String pasport) {
        this.pasport = pasport;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
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

    private String getRod() {
        return splitFio(fam) + ';' + data_rod + ';' + snils_rod + ';' + pasport + ';' + adres;
    }

    private String getOtob() {
        return namedoc + ';' + data_resh + ';' + data_vst + ';' + primechanie;
    }


    public String getCSV() {
        return getRod() + ';' + getOtob();
    }

}
