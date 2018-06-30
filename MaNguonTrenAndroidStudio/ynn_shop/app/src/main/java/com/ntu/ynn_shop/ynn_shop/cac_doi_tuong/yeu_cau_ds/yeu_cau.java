package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.bo_loc;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.sap_xep;
import java.util.ArrayList;

public class yeu_cau {
        public com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.bo_loc bo_loc;
        public ArrayList<sap_xep> sap_xep;

    public yeu_cau( bo_loc bo_loc, ArrayList<sap_xep> sap_xep) {
        this.bo_loc = bo_loc;
        this.sap_xep = sap_xep;
    }

    public bo_loc getBo_loc() {
        return bo_loc;
    }

    public void setBo_loc(bo_loc bo_loc) {
        this.bo_loc = bo_loc;
    }

    public ArrayList<sap_xep> getSap_xep() {
        return sap_xep;
    }

    public void setSap_xep(ArrayList<sap_xep> sap_xep) {
        this.sap_xep = sap_xep;
    }
}

