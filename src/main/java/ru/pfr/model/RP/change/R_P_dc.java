package ru.pfr.model.RP.change;

import ru.pfr.model.RP.RPchild;
import ru.pfr.model.RP.RPdoc;
import ru.pfr.service.lrp.RPchildService;
import ru.pfr.service.lrp.RPdocService;

import java.util.List;

public class R_P_dc {

    RPdoc rPdoc;
    List<RPchild> rPchildren;

    public R_P_dc(Long i, RPdocService rPdocService, RPchildService rPchildService) {
        rPdoc = rPdocService.findById(i);
        rPchildren = rPchildService.findByRPdoc(rPdoc.getId_doc());
    }

    public RPdoc getrPdoc() {
        return rPdoc;
    }

    public void setrPdoc(RPdoc rPdoc) {
        this.rPdoc = rPdoc;
    }

    public List<RPchild> getrPchildren() {
        return rPchildren;
    }

    public void setrPchildren(List<RPchild> rPchildren) {
        this.rPchildren = rPchildren;
    }
}
