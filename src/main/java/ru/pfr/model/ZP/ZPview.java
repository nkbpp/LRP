package ru.pfr.model.ZP;

import ru.pfr.model.Views;

import javax.persistence.*;

@Entity
@Table(name = "z_p")
public class ZPview extends Views {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_z_p_child")
    private Long id_child;

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

    @Column(name = "recvisit")
    private String recvisit;

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

    public ZPview() {
    }

    public ZPview(String fam, String data_rod, String snils_rod, String pasport, String adres, String namedoc, String data_resh, String data_vst, String recvisit, String name_child, String data_roj, String svidetelstvo, String snils_child, String primechanie) {
        this.fam = fam;
        this.data_rod = data_rod;
        this.snils_rod = snils_rod;
        this.pasport = pasport;
        this.adres = adres;
        this.namedoc = namedoc;
        this.data_resh = data_resh;
        this.data_vst = data_vst;
        this.recvisit = recvisit;
        this.name_child = name_child;
        this.data_roj = data_roj;
        this.svidetelstvo = svidetelstvo;
        this.snils_child = snils_child;
        this.primechanie = primechanie;
    }

    public ZPview(Long id_child, String fam, String data_rod, String snils_rod, String pasport, String adres, String namedoc, String data_resh, String data_vst, String recvisit, String name_child, String data_roj, String svidetelstvo, String snils_child, String primechanie) {
        this.id_child = id_child;
        this.fam = fam;
        this.data_rod = data_rod;
        this.snils_rod = snils_rod;
        this.pasport = pasport;
        this.adres = adres;
        this.namedoc = namedoc;
        this.data_resh = data_resh;
        this.data_vst = data_vst;
        this.recvisit = recvisit;
        this.name_child = name_child;
        this.data_roj = data_roj;
        this.svidetelstvo = svidetelstvo;
        this.snils_child = snils_child;
        this.primechanie = primechanie;
    }

    public Long getId_child() {
        return id_child;
    }

    public void setId_child(Long id_child) {
        this.id_child = id_child;
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

    public String getRecvisit() {
        return recvisit;
    }

    public void setRecvisit(String recvisit) {
        this.recvisit = recvisit;
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

    private String getRod() {
        return splitFio(fam) + ';' + data_rod + ';' + snils_rod + ';' + pasport + ';' + adres;
    }

    private String getOtob() {
        return namedoc + ';' + data_resh + ';' + data_vst + ';' + recvisit;
    }

    private String getChild() {
        return splitFio(name_child) + ';' + data_roj + ';' + svidetelstvo + ';' + snils_child + ';' + primechanie;
    }

    public String getCSV() {
        return getRod() + ';' + getOtob() + ';' + getChild() + ';';
    }

    private String splitDate(String recvisit){ //здесь не нужен
        //....
        return recvisit + "; ";
    }

}
