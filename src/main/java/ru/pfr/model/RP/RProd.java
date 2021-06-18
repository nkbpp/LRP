package ru.pfr.model.RP;

import ru.pfr.model.lrp.LrpFiles;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name = "r_p_rod")
public class RProd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_r_p_rod")
    private Long id_rod;

    @Column(name = "fam_rod")
    private String fam;

    @Column(name = "name_rod")
    private String name;

    @Column(name = "otch_rod")
    private String otch;

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

    public boolean check_snils_rod(){
        return Pattern.matches("^\\d{3}-\\d{3}-\\d{3} \\d{2}$",snils_rod) && !snils_rod.equals("000-000-000 00");
    }

    public boolean check_data_rod(){
        return Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$",data_rod);
    }

    private boolean check_text_fio(String s){
        return s.length()<=50;
    }

    public boolean check_fam(){
        return fam!=null && fam.length()>0 && check_text_fio(fam);
    }

    public boolean check_name(){
        return name!=null && name.length()>0 && check_text_fio(name);
    }

    public boolean check_otch(){
        return check_text_fio(otch);
    }

    public List<String> checksErr(List<String> err) {
        if(!check_snils_rod()){
            err.add("Неверный формат СНИЛС");
        }
        if(!check_data_rod()){
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
        return err;
    }

    public RProd() {
    }


    public RProd(String fam, String name, String otch, String data_rod, String snils_rod, String pasport, String adres, LrpFiles lrpFiles) {
        this.fam = fam;
        this.name = name;
        this.otch = otch;
        this.data_rod = data_rod;
        this.snils_rod = snils_rod;
        this.pasport = pasport;
        this.adres = adres;
        this.lrpFiles = lrpFiles;
    }

    public RProd(Long id_rod, String fam, String name, String otch, String data_rod, String snils_rod, String pasport, String adres, LrpFiles lrpFiles) {
        this.id_rod = id_rod;
        this.fam = fam;
        this.name = name;
        this.otch = otch;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtch() {
        return otch;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }
}
