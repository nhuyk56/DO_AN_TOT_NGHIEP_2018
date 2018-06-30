package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong;

public class thuong_hieu_tot_nhat {
    public  int id_thuong_hieu;
    public  String ten_thuong_hieu;
    public  String anh_thuong_hieu;
    public  int sl;

    public thuong_hieu_tot_nhat(int id_thuong_hieu, String ten_thuong_hieu, String anh_thuong_hieu, int sl) {
        this.id_thuong_hieu = id_thuong_hieu;
        this.ten_thuong_hieu = ten_thuong_hieu;
        this.anh_thuong_hieu = anh_thuong_hieu;
        this.sl = sl;
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

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
