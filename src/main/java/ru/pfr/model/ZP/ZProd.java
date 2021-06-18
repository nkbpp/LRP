package ru.pfr.model.ZP;

import ru.pfr.model.lrp.LrpFiles;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name = "z_p_rod")
public class ZProd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_z_p_rod")
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

    public List<String> checksErr(List<String> err) {
/*        if(!Pattern.matches("^\\d{3}-\\d{3}-\\d{3} \\d{2}$",snils_rod)){
            err.add("Неверный формат СНИЛС");
        }
        if(!Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$",data_rod)){
            err.add("Неверный формат Датарожд");
        }
        if(pasport.length()>255){
            err.add("Поле Паспорт превышает длинну 255 символов");
        }
        if(adres.length()>255){
            err.add("Поле Адрес превышает длинну 255 символов");
        }
        //при появлении ф и о добавить и к ним ограничения
        //...
        return err;*/
        return err;
    }

    public ZProd() {
    }

    public ZProd(String fam, String data_rod, String snils_rod, String pasport, String adres, LrpFiles lrpFiles) {
        this.fam = fam;
        this.data_rod = data_rod;
        this.snils_rod = snils_rod;
        this.pasport = pasport;
        this.adres = adres;
        this.lrpFiles = lrpFiles;
    }

    public ZProd(Long id_rod, String fam, String data_rod, String snils_rod, String pasport, String adres, LrpFiles lrpFiles) {
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
