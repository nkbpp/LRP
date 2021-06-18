package ru.pfr.controller.r_p_change;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.pfr.model.RP.RPchild;
import ru.pfr.model.RP.RPdoc;
import ru.pfr.model.RP.RProd;
import ru.pfr.model.RP.RPview;
import ru.pfr.model.lrp.LrpFiles;
import ru.pfr.service.lrp.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/lrp/r_p_change/"})
public class R_P_changeController {

    @Autowired
    RProdService rProdService;

    @Autowired
    RPdocService rPdocService;

    @Autowired
    RPchildService rPchildService;

    @Autowired
    RPviewService rPviewService;

    @Autowired
    LrpFilesService lrpFilesService;

    final int SIZE_LIST = 500;
    //******************************ИЗМЕНЕНИЕ************************
    @GetMapping(
            value = "files"
    )
    public String r_p_change_r_p_files(
            //@RequestParam(value = "id") Long id,
            Model model) {

        List<LrpFiles> lrpFiles = lrpFilesService.findAll();

        model.addAttribute("files", lrpFiles);

        return "r_p_change/r_p_files";
    }

    private List<RPview> rPviewList(int list, Long id){

        List<RPview> rPviews = new ArrayList<>();
        List<RPview> rPviews1 = new ArrayList<>();
        if(id<0){
            rPviews = rPviewService.findAll2();
        } else{
            rPviews = rPviewService.findByFiles(id);
        }

        if(rPviews.size()>=(list+1)*SIZE_LIST){
            for (int i = list*SIZE_LIST; i <= (list+1)*SIZE_LIST && i<rPviews.size(); i++) {
                rPviews1.add(rPviews.get(i));
            }
        }else{
            if(list==0){
                rPviews1 = rPviews;
            } else {
                for (int i = list*SIZE_LIST; i <= (list+1)*SIZE_LIST && i<rPviews.size(); i++) {
                    rPviews1.add(rPviews.get(i));
                }
            }

        }
        return rPviews1;
    }

    @GetMapping(
            value = "change"
    )
    public String r_p_change_change(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "list", defaultValue = "0") int list,
            Model model) {

        if(list<0)list=0;

        model.addAttribute("rpalls", rPviewList(list,id));

        model.addAttribute("idfiles", id);

        model.addAttribute("list", list+1);

        model.addAttribute("listback", list-1);

        return "r_p_change/r_p_viev";
    }

    @RequestMapping("setview")
    public String setview(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "list", defaultValue = "0") int list,
            @RequestParam(value = "idfiles") Long idfiles,
            @RequestParam(value = "fam_rod") String fam_rod,
            @RequestParam(value = "name_rod") String name_rod,
            @RequestParam(value = "otch_rod") String otch_rod,
            @RequestParam(value = "data_rod") String data_rod,
            @RequestParam(value = "snils_rod") String snils_rod,
            @RequestParam(value = "pasport") String pasport,
            @RequestParam(value = "adres") String adres,

            @RequestParam(value = "namedoc") String namedoc,
            @RequestParam(value = "data_resh") String data_resh,
            @RequestParam(value = "data_vst") String data_vst,
            @RequestParam(value = "result") String result,

            @RequestParam(value = "fam_child") String fam_child,
            @RequestParam(value = "name_child") String name_child,
            @RequestParam(value = "otch_child") String otch_child,
            @RequestParam(value = "data_roj") String data_roj,
            @RequestParam(value = "snils_child") String snils_child,
            @RequestParam(value = "doc_osn") String doc_osn,
            @RequestParam(value = "data_resh_vos") String data_resh_vos,
            @RequestParam(value = "data_vst_vos") String data_vst_vos,

            Model model) {
        if(list<0)list=0;
        RPview rPview = rPviewService.findByID(id);

        RProd rProd = rProdService.findById(rPview.getId_r_p_rod());
        rProd.setName(name_rod);
        rProd.setFam(fam_rod);
        rProd.setOtch(otch_rod);
        rProd.setData_rod(data_rod);
        rProd.setSnils_rod(snils_rod);
        rProd.setPasport(pasport);
        rProd.setAdres(adres);
        rProdService.save(rProd);

        RPdoc rPdoc = rPdocService.findById(rPview.getId_r_p_doc());
        rPdoc.setNamedoc(namedoc);
        rPdoc.setData_resh(data_resh);
        rPdoc.setData_vst(data_vst);
        rPdoc.setResult(result);
        rPdocService.save(rPdoc);

        RPchild rPchild = rPchildService.findById(id);
        rPchild.setName_child(name_child);
        rPchild.setFam_child(fam_child);
        rPchild.setOtch_child(otch_child);
        rPchild.setData_roj(data_roj);
        rPchild.setSnils_child(snils_child);
        rPchild.setDoc_osn(doc_osn);
        rPchild.setData_resh_vos(data_resh_vos);
        rPchild.setData_vst_vos(data_vst_vos);
        rPchildService.save(rPchild);

/*        RProd rProd1 = rProdService.findById(rProd.getId_rod());

        List<RPview> rPviews = null;
        if(id<0){
            rPviews = rPviewService.findAll2();
        } else{
            rPviews = rPviewService.findByFiles(id);
        }
        model.addAttribute("rpalls", rPviews);*/
        List<RPview> rPviews = rPviewList(list,id);
        model.addAttribute("rpalls", rPviews);
        model.addAttribute("idfiles", idfiles);
        return "r_p_change/frag/r_p_all :: r_p_all2";
    }

    //КОСТЫЛЬ! В идеале все должно работать в setview, но там представление не успевает обновиться(
    @GetMapping(
            value = "view"
    )
    public String view(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "list", defaultValue = "0") int list,
            Model model) {
/*        List<RPview> rPviews = null;
        if(id<0){
            rPviews = rPviewService.findAll2();
        } else{
            rPviews = rPviewService.findByFiles(id);
        }
        model.addAttribute("rpalls", rPviews);*/
        if(list<0)list=0;
        List<RPview> rPviews = rPviewList(list,id);
        model.addAttribute("rpalls", rPviews);
        //model.addAttribute("rpalls", rPviewList(list,id));
        model.addAttribute("idfiles", id);
        return "r_p_change/frag/r_p_all :: r_p_all2";
    }





    /*    @GetMapping(
            value = "change"
    )
    public String r_p_change_change(
            @RequestParam(value = "id") Long id,
            Model model) {

        R_P_alls r_p_alls = new R_P_alls(id, rProdService, rPdocService, rPchildService);

        model.addAttribute("rpalls", r_p_alls);

        return "r_p_change/r_p_alls";
    }*/

/*    @GetMapping(
            value = "r_p_rd"
    )
    public String r_p_rd(
            @RequestParam(value = "id") Long id,
            Model model) {

        R_P_alls r_p_alls = new R_P_alls(id, rProdService, rPdocService, rPchildService);

        model.addAttribute("rpalls", r_p_alls.getR_p_rd());

        return "r_p_change/r_p_rd";

    }*/



/*    @GetMapping("insertrod")
    public String insertrod(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "fam") String fam,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "otch") String otch,
            @RequestParam(value = "data_rod") String data_rod,
            @RequestParam(value = "snils_rod") String snils_rod,
            @RequestParam(value = "pasport") String pasport,
            @RequestParam(value = "adres") String adres,
            Model model) {

        RProd rProd = rProdService.findById(Long.valueOf(id));
        rProd.setFam(fam);
        rProd.setName(name);
        rProd.setOtch(otch);
        rProd.setData_rod(data_rod);
        rProd.setSnils_rod(snils_rod);
        rProd.setPasport(pasport);
        rProd.setAdres(adres);
        rProdService.save(rProd);

        //вернуть что то другое
        return "fragmentupfr/opfrfrag :: opfrhistory";
    }

    @GetMapping("insertdoc")
    public String insertdoc(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "vid") String vid,
            @RequestParam(value = "namedoc") String namedoc,
            @RequestParam(value = "data_resh") String data_resh,
            @RequestParam(value = "data_vst") String data_vst,
            @RequestParam(value = "result") String result,
            Model model) {

        RPdoc rPdoc = rPdocService.findById(Long.valueOf(id));
        rPdoc.setVid(vid);
        rPdoc.setNamedoc(namedoc);
        rPdoc.setData_resh(data_resh);
        rPdoc.setData_vst(data_vst);
        rPdoc.setResult(result);
        rPdocService.save(rPdoc);

        //вернуть что то другое
        return "fragmentupfr/opfrfrag :: opfrhistory";
    }

    @GetMapping("insertchild")
    public String insertchild(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "fam_child") String fam_child,
            @RequestParam(value = "name_child") String name_child,
            @RequestParam(value = "otch_child") String otch_child,
            @RequestParam(value = "data_roj") String data_roj,
            @RequestParam(value = "snils_child") String snils_child,
            @RequestParam(value = "doc_osn") String doc_osn,
            @RequestParam(value = "data_resh_vos") String data_resh_vos,
            @RequestParam(value = "data_vst_vos") String data_vst_vos,
            @RequestParam(value = "primechanie") String primechanie,
            Model model) {

        RPchild rPchild = rPchildService.findById(Long.valueOf(id));
        rPchild.setFam_child(fam_child);
        rPchild.setName_child(name_child);
        rPchild.setOtch_child(otch_child);
        rPchild.setData_roj(data_roj);
        rPchild.setSnils_child(snils_child);
        rPchild.setDoc_osn(doc_osn);
        rPchild.setData_resh_vos(data_resh_vos);
        rPchild.setData_vst_vos(data_vst_vos);
        rPchild.setPrimechanie(primechanie);
        rPchildService.save(rPchild);

        //вернуть что то другое
        return "fragmentupfr/opfrfrag :: opfrhistory";
    }*/

}
