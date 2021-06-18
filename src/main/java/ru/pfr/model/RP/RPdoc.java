package ru.pfr.model.RP;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name = "r_p_doc")
public class RPdoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_r_p_doc")
    private Long id_doc;

    @Column(name = "vid")
    private String vid;

    @Column(name = "name_doc")
    private String namedoc;

    @Column(name = "data_resh")
    private String data_resh;

    @Column(name = "data_vst")
    private String data_vst;

    @Column(name = "result")
    private String result;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_r_p_rod")
    private RProd rProd;

    public boolean check_data_resh(){
        return Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$",data_resh);
    }

    public boolean check_data_vst(){
        return Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$",data_vst);
    }

    public boolean check_result(){
        return result.length()<=150;
    }

    public boolean check_namedoc(){
        return namedoc.length()>0 && namedoc.length()<=150;
    }

    public List<String> checksErr(List<String> err) {
        if(!Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$",data_resh)){
            err.add("Неверный формат ДатаРешения");
        }
        if(!Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$",data_vst)){
            err.add("Неверный формат ДатаВступления");
        }
        if(namedoc.length()>150){
            err.add("Поле СудебныйОрган превышает длинну 150 символов");
        }
        if(result.length()>150){
            err.add("Поле Результат превышает длинну 150 символов");
        }
        return err;
    }

    public RPdoc() {
    }

    public RPdoc( String vid, String namedoc, String data_resh, String data_vst, String result, RProd rProd) {
        this.vid = vid;
        this.namedoc = namedoc;
        this.data_resh = data_resh;
        this.data_vst = data_vst;
        this.result = result;
        this.rProd = rProd;
    }

    public RPdoc(Long id_doc, String vid, String namedoc, String data_resh, String data_vst, String result, RProd rProd) {
        this.id_doc = id_doc;
        this.vid = vid;
        this.namedoc = namedoc;
        this.data_resh = data_resh;
        this.data_vst = data_vst;
        this.result = result;
        this.rProd = rProd;
    }

    public RPdoc RPdocSlijanie(RPdoc rPdoc) {
        if (this.vid.equals("") && this.namedoc.equals("") && this.data_resh.equals("") && this.data_vst.equals("") && this.result.equals("")){
            return rPdoc;
        } else {
            this.vid = this.vid.equals("")?rPdoc.vid:this.vid;
            this.namedoc = this.namedoc.equals("")?rPdoc.namedoc:this.namedoc;
            this.data_resh = this.data_resh.equals("")?rPdoc.data_resh:this.data_resh;
            this.data_vst = this.data_vst.equals("")?rPdoc.data_vst:this.data_vst;
            this.result = this.result.equals("")?rPdoc.result:this.result;
            return this;
        }
    }

    public Long getId_doc() {
        return id_doc;
    }

    public void setId_doc(Long id_doc) {
        this.id_doc = id_doc;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public RProd getrProd() {
        return rProd;
    }

    public void setrProd(RProd rProd) {
        this.rProd = rProd;
    }
}
