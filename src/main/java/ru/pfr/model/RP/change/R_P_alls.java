package ru.pfr.model.RP.change;

import ru.pfr.model.RP.RProd;
import ru.pfr.service.lrp.RPchildService;
import ru.pfr.service.lrp.RPdocService;
import ru.pfr.service.lrp.RProdService;

import java.util.ArrayList;
import java.util.List;


public class R_P_alls {

    private List<R_P_rd> r_p_rd;

    public R_P_alls(Long i, RProdService rProdService, RPdocService rPdocService, RPchildService rPchildService) {

        List<RProd> rProds = rProdService.findByFiles(i);

        r_p_rd = new ArrayList<>();

        for (RProd rProd:
             rProds) {
            r_p_rd.add(new R_P_rd(rProd.getId_rod(), rProdService, rPdocService, rPchildService));
        }
    }

    public List<R_P_rd> getR_p_rd() {
        return r_p_rd;
    }

    public void setR_p_rd(List<R_P_rd> r_p_rd) {
        this.r_p_rd = r_p_rd;
    }
}
