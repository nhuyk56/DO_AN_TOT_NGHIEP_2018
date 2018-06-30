package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gio_hang;

public class tra_ve_giam_gia {
    public  String ma_giam_gia;
    public  String thoi_diem_ket_thuc_giam_gia;
    public  float phan_tram_giam;
    public  long giam_toi_da;

    public tra_ve_giam_gia(String ma_giam_gia, String thoi_diem_ket_thuc_giam_gia, float phan_tram_giam, long giam_toi_da) {
        this.ma_giam_gia = ma_giam_gia;
        this.thoi_diem_ket_thuc_giam_gia = thoi_diem_ket_thuc_giam_gia;
        this.phan_tram_giam = phan_tram_giam;
        this.giam_toi_da = giam_toi_da;
    }

    public String getMa_giam_gia() {
        return ma_giam_gia;
    }

    public void setMa_giam_gia(String ma_giam_gia) {
        this.ma_giam_gia = ma_giam_gia;
    }

    public String getThoi_diem_ket_thuc_giam_gia() {
        return thoi_diem_ket_thuc_giam_gia;
    }

    public void setThoi_diem_ket_thuc_giam_gia(String thoi_diem_ket_thuc_giam_gia) {
        this.thoi_diem_ket_thuc_giam_gia = thoi_diem_ket_thuc_giam_gia;
    }

    public float getPhan_tram_giam() {
        return phan_tram_giam;
    }

    public void setPhan_tram_giam(float phan_tram_giam) {
        this.phan_tram_giam = phan_tram_giam;
    }

    public long getGiam_toi_da() {
        return giam_toi_da;
    }

    public void setGiam_toi_da(long giam_toi_da) {
        this.giam_toi_da = giam_toi_da;
    }
}
