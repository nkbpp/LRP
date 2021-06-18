package ru.pfr.model.RP;

import ru.pfr.model.Views;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Entity
@Table(name = "r_p")
public class RPview extends Views {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_r_p_child")
    private Long id_child;

    @Column(name = "id_r_p_rod")
    private Long id_r_p_rod;

    @Column(name = "fam_rod")
    private String fam_rod;

    @Column(name = "name_rod")
    private String name_rod;

    @Column(name = "otch_rod")
    private String otch_rod;

    @Column(name = "data_rod")
    private String data_rod;

    @Column(name = "snils_rod")
    private String snils_rod;

    @Column(name = "pasport")
    private String pasport;

    @Column(name = "adres")
    private String adres;

    @Column(name = "id_files")
    private Long id_files;

    @Column(name = "id_r_p_doc")
    private Long id_r_p_doc;

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

    //для проверки родителя
    private boolean check_text_fio(String s) {
        return s.length() <= 50;
    }

    public boolean check_fam() {
        return fam_rod != null && fam_rod.length() > 0 && check_text_fio(fam_rod);
    }

    public boolean check_name() {
        return name_rod != null && name_rod.length() > 0 && check_text_fio(name_rod);
    }

    public boolean check_otch() {
        return check_text_fio(otch_rod);
    }

    public boolean check_data_rod() {
        return Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_rod);
    }

    public boolean check_snils_rod() {
        return Pattern.matches(
                "^\\d{3}-\\d{3}-\\d{3} \\d{2}$", snils_rod) &&
                !snils_rod.equals("000-000-000 00");
    }

    //для проверки документов
    public boolean check_data_resh() {
        return Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_resh);
    }

    public boolean check_data_vst() {
        return Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_vst);
    }

    public boolean check_result() {
        return result.length() <= 150;
    }

    public boolean check_namedoc() {
        return namedoc.length() > 0 && namedoc.length() <= 150;
    }

    //для проверки ребенка
    public boolean check_fam_child() {
        return fam_child != null && fam_child.length() > 0 && check_text_fio(fam_child);
    }

    public boolean check_name_child() {
        return name_child != null && name_child.length() > 0 && check_text_fio(name_child);
    }

    public boolean check_otch_child() {
        return check_text_fio(otch_child);
    }

    public boolean check_data_roj() {
        return Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_roj);
    }

    public boolean check_snils_child() {
        return Pattern.matches(
                "^\\d{3}-\\d{3}-\\d{3} \\d{2}$", snils_child) &&
                !snils_child.equals("000-000-000 00");
    }

    public boolean check_doc_osn() {
        return doc_osn.length() <= 150;
    }

    public boolean check_data_resh_child() {
        return Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_resh_vos);
    }

    public boolean check_data_vst_child() {
        return Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_vst_vos);
    }

    public RPview() {
    }

    public RPview(Long id_child, Long id_r_p_rod, String fam_rod, String name_rod, String otch_rod, String data_rod, String snils_rod, String pasport, String adres, Long id_files, Long id_r_p_doc, String vid, String namedoc, String data_resh, String data_vst, String result, String data_roj, String snils_child, String fam_child, String name_child, String otch_child, String doc_osn, String data_resh_vos, String data_vst_vos, String primechanie) {
        this.id_child = id_child;
        this.id_r_p_rod = id_r_p_rod;
        this.fam_rod = fam_rod;
        this.name_rod = name_rod;
        this.otch_rod = otch_rod;
        this.data_rod = data_rod;
        this.snils_rod = snils_rod;
        this.pasport = pasport;
        this.adres = adres;
        this.id_files = id_files;
        this.id_r_p_doc = id_r_p_doc;
        this.vid = vid;
        this.namedoc = namedoc;
        this.data_resh = data_resh;
        this.data_vst = data_vst;
        this.result = result;
        this.data_roj = data_roj;
        this.snils_child = snils_child;
        this.fam_child = fam_child;
        this.name_child = name_child;
        this.otch_child = otch_child;
        this.doc_osn = doc_osn;
        this.data_resh_vos = data_resh_vos;
        this.data_vst_vos = data_vst_vos;
        this.primechanie = primechanie;
    }

    public Long getId_child() {
        return id_child;
    }

    public void setId_child(Long id_child) {
        this.id_child = id_child;
    }

    public Long getId_r_p_rod() {
        return id_r_p_rod;
    }

    public void setId_r_p_rod(Long id_r_p_rod) {
        this.id_r_p_rod = id_r_p_rod;
    }

    public String getFam_rod() {
        return fam_rod;
    }

    public void setFam_rod(String fam_rod) {
        this.fam_rod = fam_rod;
    }

    public String getName_rod() {
        return name_rod;
    }

    public void setName_rod(String name_rod) {
        this.name_rod = name_rod;
    }

    public String getOtch_rod() {
        return otch_rod;
    }

    public void setOtch_rod(String otch_rod) {
        this.otch_rod = otch_rod;
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

    public Long getId_r_p_doc() {
        return id_r_p_doc;
    }

    public void setId_r_p_doc(Long id_r_p_doc) {
        this.id_r_p_doc = id_r_p_doc;
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

    public String getFam_child() {
        return fam_child;
    }

    public void setFam_child(String fam_child) {
        this.fam_child = fam_child;
    }

    public String getName_child() {
        return name_child;
    }

    public void setName_child(String name_child) {
        this.name_child = name_child;
    }

    public String getOtch_child() {
        return otch_child;
    }

    public void setOtch_child(String otch_child) {
        this.otch_child = otch_child;
    }

    public String getDoc_osn() {
        return doc_osn;
    }

    public void setDoc_osn(String doc_osn) {
        this.doc_osn = doc_osn;
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

    public String getPrimechanie() {
        return primechanie;
    }

    public void setPrimechanie(String primechanie) {
        this.primechanie = primechanie;
    }

    public Long getId_files() {
        return id_files;
    }

    public void setId_files(Long id_files) {
        this.id_files = id_files;
    }

    private String getRod() {
        return snils_rod + ';'
                + fam_rod + ';'
                + name_rod + ';'
                + otch_rod + ';'
                + data_rod + ';'
                + pasport + ';'
                + adres;
    }

    private String getOtob() {
        return namedoc + ';' + data_resh + ';' + data_vst + ';' + result;
    }

    private String getChild() {
        return fam_child + ';'
                + name_child + ';'
                + otch_child + ';'
                + data_roj + ';'
                + snils_child;
    }

    private String getVosst() {
        return doc_osn + ';' + data_resh_vos + ';' + data_vst_vos;
    }


    public String getCSV() {
        return getRod() + ';' + getOtob() + ';' + getChild() + ';' + getVosst();
    }


    public boolean checkRPviewRodCorrect() {
        boolean b = true;
        //проверка родителя
        try {
            if ((!Pattern.matches("^\\d{3}-\\d{3}-\\d{3} \\d{2}$", snils_rod)
                    || snils_rod.equals("000-000-000 00")) &&
                    Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_rod)) {
                b = false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return b;
    }

    public boolean checkRPviewChildCorrect() {
        boolean b = true;
        if ((!Pattern.matches("^\\d{3}-\\d{3}-\\d{3} \\d{2}$", snils_child)
                || snils_child.equals("000-000-000 00")) &&
                Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_roj)) {
            b = false;
        }
        return b;
    }

    public boolean checkRPviewCorrect() {
        boolean b = true;
        //проверка родителя
        if (!Pattern.matches("^\\d{3}-\\d{3}-\\d{3} \\d{2}$", snils_rod) ||
                snils_rod.equals("000-000-000 00")) {
            b = false;
        }
        if (fam_rod.length() == 0) b = false;
        if (name_rod.length() == 0) b = false;
        if (!Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_rod)) {
            b = false;
        }
        if (pasport.length() == 0) b = false;
        //проверка документа
        if (data_resh.length() != 0) {
            if (!Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_resh)) {
                b = false;
            }
        }
        if (data_vst.length() != 0) {
            if (!Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_vst)) {
                b = false;
            }
        }
        //проверка ребенка
        if (fam_child.length() == 0) b = false;
        if (name_child.length() == 0) b = false;
        if (!Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_roj)) {
            b = false;
        }
        if (!Pattern.matches("^\\d{3}-\\d{3}-\\d{3} \\d{2}$", snils_child)
                || snils_child.equals("000-000-000 00")) {
            b = false;
        }
        if (data_resh_vos.length() != 0) {
            if (!Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_resh_vos)) {
                b = false;
            }
        }
        if (data_vst_vos.length() != 0) {
            if (!Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", data_vst_vos)) {
                b = false;
            }
        }

        if (data_resh_vos.length() == 0 && data_resh.length() == 0) b = false;
        return b;
    }


    public boolean datePast2004() {
        Date date2004 = null;
        Date tekDate = null;
        try {
            String stringDate = "11.05.2004";
            date2004 = new SimpleDateFormat("dd.MM.yyyy").parse(stringDate);
            if(data_roj.length()>5){
            tekDate = new SimpleDateFormat("dd.MM.yyyy").parse(data_roj);
            }else return Integer.valueOf(data_roj)>=2004;

            //сравнить даты
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return tekDate.after(date2004);
    }

}
