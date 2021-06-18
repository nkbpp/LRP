package ru.pfr.model.RP.change;

import ru.pfr.model.RP.RPdoc;
import ru.pfr.model.RP.RProd;
import ru.pfr.service.lrp.RPchildService;
import ru.pfr.service.lrp.RPdocService;
import ru.pfr.service.lrp.RProdService;

import java.util.ArrayList;
import java.util.List;


public class R_P_rd {
    private RProd rProd;
    private List<R_P_dc> r_p_dcs;

    public R_P_rd(Long i, RProdService rProdService, RPdocService rPdocService, RPchildService rPchildService) {

        rProd = rProdService.findById(i);

        List<RPdoc> rPdocs = rPdocService.findByRProd(i);

        r_p_dcs = new ArrayList<>();

        for (RPdoc rPdoc:
                rPdocs) {
            r_p_dcs.add(new R_P_dc(rPdoc.getId_doc(), rPdocService, rPchildService));
        }
    }

    public RProd getrProd() {
        return rProd;
    }

    public void setrProd(RProd rProd) {
        this.rProd = rProd;
    }

    public List<R_P_dc> getR_p_dcs() {
        return r_p_dcs;
    }

    public void setR_p_dcs(List<R_P_dc> r_p_dcs) {
        this.r_p_dcs = r_p_dcs;
    }
}
