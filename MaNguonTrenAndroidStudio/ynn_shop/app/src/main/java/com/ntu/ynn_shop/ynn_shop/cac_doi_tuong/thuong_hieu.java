package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong;

public class thuong_hieu {
    public int id_thuong_hieu;
    public String ten_thuong_hieu;
    public String anh_thuong_hieu;

    public thuong_hieu(int id_thuong_hieu, String ten_thuong_hieu, String anh_thuong_hieu) {
        this.id_thuong_hieu = id_thuong_hieu;
        this.ten_thuong_hieu = ten_thuong_hieu;
        this.anh_thuong_hieu = anh_thuong_hieu;
    }

    public int getId_thuong_hieu() {
        return id_thuong_hieu;
    }

    public void setId_thuong_hieu(int id_thuong_hieu) {
        this.id_thuong_hieu = id_thuong_hieu;
    }

    public String getTen_thuong_hieu() {
        return ten_thuong_hieu;
    }

    public void setTen_thuong_hieu(String ten_thuong_hieu) {
        this.ten_thuong_hieu = ten_thuong_hieu;
    }

    public String getAnh_thuong_hieu() {
        return anh_thuong_hieu;
    }

    public void setAnh_thuong_hieu(String anh_thuong_hieu) {
        this.anh_thuong_hieu = anh_thuong_hieu;
    }
}
