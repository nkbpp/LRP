package ru.pfr.model.ND;

import ru.pfr.model.lrp.LrpFiles;

import javax.persistence.*;

@Entity
@Table(name = "n_d_rod")
public class NDrod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_n_d_rod")
    private Long id_rod;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_files")
    private LrpFiles lrpFiles;

    public NDrod() {
    }

    public NDrod(String fam, String data_rod, String snils_rod, String pasport, String adres, LrpFiles lrpFiles) {
        this.fam = fam;
        this.data_rod = data_rod;
        this.snils_rod = snils_rod;
        this.pasport = pasport;
        this.adres = adres;
        this.lrpFiles = lrpFiles;
    }

    public NDrod(Long id_rod, String fam, String data_rod, String snils_rod, String pasport, String adres, LrpFiles lrpFiles) {
        this.id_rod = id_rod;
        this.fam = fam;
        this.data_rod = data_rod;
        this.snils_rod = snils_rod;
        this.pasport = pasport;
        this.adres = adres;
        this.lrpFiles = lrpFiles;
    }

    public Long getId_rod() {
        return id_rod;
    }

    public void setId_rod(Long id_rod) {
        this.id_rod = id_rod;
    }

    public String getFam() {
        return fam;
    }

    public void setFam(String fam) {
        this.fam = fam;
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

    public String getData_rod() {
        return data_rod;
    }

    public void setData_rod(String data_rod) {
        this.data_rod = data_rod;
    }

    public LrpFiles getLrpFiles() {
        return lrpFiles;
    }

    public void setLrpFiles(LrpFiles lrpFiles) {
        this.lrpFiles = lrpFiles;
    }
}
