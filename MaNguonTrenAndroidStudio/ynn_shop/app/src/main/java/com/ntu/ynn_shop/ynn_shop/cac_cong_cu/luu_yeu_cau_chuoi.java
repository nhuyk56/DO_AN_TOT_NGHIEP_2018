package com.ntu.ynn_shop.ynn_shop.cac_cong_cu;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.sap_xep;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.yeu_cau;

import java.util.ArrayList;
import java.util.Arrays;

public class luu_yeu_cau_chuoi {
    String bo_loc;
    String sap_xep;
    Gson gson;

    public luu_yeu_cau_chuoi(String bo_loc, String sap_xep) {
        this.bo_loc = bo_loc;
        this.sap_xep = sap_xep;
        gson = new Gson();
    }

    public String getBo_loc() {
        return bo_loc;
    }

    public void setBo_loc(String bo_loc) {
        this.bo_loc = bo_loc;
    }

    public String getSap_xep() {
        return sap_xep;
    }

    public void setSap_xep(String sap_xep) {
        this.sap_xep = sap_xep;
    }

    public yeu_cau getYeuCau(){
        String tmp = sap_xep;
        com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.sap_xep[] sx = new sap_xep[0];
        sx = gson.fromJson(tmp,sap_xep[].class);
        ArrayList<sap_xep> sxlist = new ArrayList<sap_xep>(Arrays.asList(sx));
        tmp = bo_loc;
        com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.bo_loc bl;
        bl  = gson.fromJson(bo_loc, com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.bo_loc.class);
        return new yeu_cau(bl,sxlist);
    }


}
