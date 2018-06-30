package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds;
public class sap_xep{
    public String id_sap_xep;
    public String ten_sap_xep;
    public Boolean loai_sap_xep;

    public sap_xep(String id_sap_xep, String ten_sap_xep, Boolean loai_sap_xep) {
        this.id_sap_xep = id_sap_xep;
        this.ten_sap_xep = ten_sap_xep;
        this.loai_sap_xep = loai_sap_xep;
    }

    public String getId_sap_xep() {
        return id_sap_xep;
    }

    public void setId_sap_xep(String id_sap_xep) {
        this.id_sap_xep = id_sap_xep;
    }

    public String getTen_sap_xep() {
        return ten_sap_xep;
    }

    public void setTen_sap_xep(String ten_sap_xep) {
        this.ten_sap_xep = ten_sap_xep;
    }

    public Boolean getLoai_sap_xep() {
        return loai_sap_xep;
    }

    public void setLoai_sap_xep(Boolean loai_sap_xep) {
        this.loai_sap_xep = loai_sap_xep;
    }
}