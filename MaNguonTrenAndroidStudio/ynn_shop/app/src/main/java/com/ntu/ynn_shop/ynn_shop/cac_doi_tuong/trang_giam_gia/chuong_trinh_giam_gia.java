package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_giam_gia;

import java.util.ArrayList;

public class chuong_trinh_giam_gia {
    public int id_giam_gia;
    public String ma_giam_gia;
    public String ten_giam_gia;
    public String ly_do_giam_gia;
    public String thoi_diem_bat_dau_giam_gia;
    public String thoi_diem_ket_thuc_giam_gia;
    public ArrayList<sp_ap_dung> ds_sp_ap_dung;

    public chuong_trinh_giam_gia(int id_giam_gia, String ma_giam_gia, String ten_giam_gia, String ly_do_giam_gia, String thoi_diem_bat_dau_giam_gia, String thoi_diem_ket_thuc_giam_gia, ArrayList<sp_ap_dung> ds_sp_ap_dung) {
        this.id_giam_gia = id_giam_gia;
        this.ma_giam_gia = ma_giam_gia;
        this.ten_giam_gia = ten_giam_gia;
        this.ly_do_giam_gia = ly_do_giam_gia;
        this.thoi_diem_bat_dau_giam_gia = thoi_diem_bat_dau_giam_gia;
        this.thoi_diem_ket_thuc_giam_gia = thoi_diem_ket_thuc_giam_gia;
        this.ds_sp_ap_dung = ds_sp_ap_dung;
    }

    public int getId_giam_gia() {
        return id_giam_gia;
    }

    public void setId_giam_gia(int id_giam_gia) {
        this.id_giam_gia = id_giam_gia;
    }

    public String getMa_giam_gia() {
        return ma_giam_gia;
    }

    public void setMa_giam_gia(String ma_giam_gia) {
        this.ma_giam_gia = ma_giam_gia;
    }

    public String getTen_giam_gia() {
        return ten_giam_gia;
    }

    public void setTen_giam_gia(String ten_giam_gia) {
        this.ten_giam_gia = ten_giam_gia;
    }

    public String getLy_do_giam_gia() {
        return ly_do_giam_gia;
    }

    public void setLy_do_giam_gia(String ly_do_giam_gia) {
        this.ly_do_giam_gia = ly_do_giam_gia;
    }

    public String getThoi_diem_bat_dau_giam_gia() {
        return thoi_diem_bat_dau_giam_gia;
    }

    public void setThoi_diem_bat_dau_giam_gia(String thoi_diem_bat_dau_giam_gia) {
        this.thoi_diem_bat_dau_giam_gia = thoi_diem_bat_dau_giam_gia;
    }

    public String getThoi_diem_ket_thuc_giam_gia() {
        return thoi_diem_ket_thuc_giam_gia;
    }

    public void setThoi_diem_ket_thuc_giam_gia(String thoi_diem_ket_thuc_giam_gia) {
        this.thoi_diem_ket_thuc_giam_gia = thoi_diem_ket_thuc_giam_gia;
    }

    public ArrayList<sp_ap_dung> getDs_sp_ap_dung() {
        return ds_sp_ap_dung;
    }

    public void setDs_sp_ap_dung(ArrayList<sp_ap_dung> ds_sp_ap_dung) {
        this.ds_sp_ap_dung = ds_sp_ap_dung;
    }
}
