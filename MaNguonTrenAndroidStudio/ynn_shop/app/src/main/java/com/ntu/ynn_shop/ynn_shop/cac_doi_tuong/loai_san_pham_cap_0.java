package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong;

import java.util.ArrayList;

public class loai_san_pham_cap_0 {
    public int id_loai_san_pham ;;
    public String ten_loai_san_pham ;
    public String anh_loai_san_pham ;
    public String bieu_tuong_loai_san_pham ;
    public int cap_do_loai_san_pham ;
    public int id_cha_loai_san_pham ;
    public ArrayList<loai_san_pham_cap_1> danh_sach_loai_san_pham_cap_1 ;
    public ArrayList<thuong_hieu> danh_sach_thuong_hieu ;
    public ArrayList<loai_san_pham_nhom_cap_2_phan_tu> danh_sach_chi_danh_cho_ban;

    public loai_san_pham_cap_0(int id_loai_san_pham, String ten_loai_san_pham, String anh_loai_san_pham, String bieu_tuong_loai_san_pham, int cap_do_loai_san_pham, int id_cha_loai_san_pham, ArrayList<loai_san_pham_cap_1> danh_sach_loai_san_pham_cap_1, ArrayList<thuong_hieu> danh_sach_thuong_hieu, ArrayList<loai_san_pham_nhom_cap_2_phan_tu> danh_sach_chi_danh_cho_ban) {
        this.id_loai_san_pham = id_loai_san_pham;
        this.ten_loai_san_pham = ten_loai_san_pham;
        this.anh_loai_san_pham = anh_loai_san_pham;
        this.bieu_tuong_loai_san_pham = bieu_tuong_loai_san_pham;
        this.cap_do_loai_san_pham = cap_do_loai_san_pham;
        this.id_cha_loai_san_pham = id_cha_loai_san_pham;
        this.danh_sach_loai_san_pham_cap_1 = danh_sach_loai_san_pham_cap_1;
        this.danh_sach_thuong_hieu = danh_sach_thuong_hieu;
        this.danh_sach_chi_danh_cho_ban = danh_sach_chi_danh_cho_ban;
    }

    public int getId_loai_san_pham() {
        return id_loai_san_pham;
    }

    public void setId_loai_san_pham(int id_loai_san_pham) {
        this.id_loai_san_pham = id_loai_san_pham;
    }

    public String getTen_loai_san_pham() {
        return ten_loai_san_pham;
    }

    public void setTen_loai_san_pham(String ten_loai_san_pham) {
        this.ten_loai_san_pham = ten_loai_san_pham;
    }

    public String getAnh_loai_san_pham() {
        return anh_loai_san_pham;
    }

    public void setAnh_loai_san_pham(String anh_loai_san_pham) {
        this.anh_loai_san_pham = anh_loai_san_pham;
    }

    public String getBieu_tuong_loai_san_pham() {
        return bieu_tuong_loai_san_pham;
    }

    public void setBieu_tuong_loai_san_pham(String bieu_tuong_loai_san_pham) {
        this.bieu_tuong_loai_san_pham = bieu_tuong_loai_san_pham;
    }

    public int getCap_do_loai_san_pham() {
        return cap_do_loai_san_pham;
    }

    public void setCap_do_loai_san_pham(int cap_do_loai_san_pham) {
        this.cap_do_loai_san_pham = cap_do_loai_san_pham;
    }

    public int getId_cha_loai_san_pham() {
        return id_cha_loai_san_pham;
    }

    public void setId_cha_loai_san_pham(int id_cha_loai_san_pham) {
        this.id_cha_loai_san_pham = id_cha_loai_san_pham;
    }

    public ArrayList<loai_san_pham_cap_1> getDanh_sach_loai_san_pham_cap_1() {
        return danh_sach_loai_san_pham_cap_1;
    }

    public void setDanh_sach_loai_san_pham_cap_1(ArrayList<loai_san_pham_cap_1> danh_sach_loai_san_pham_cap_1) {
        this.danh_sach_loai_san_pham_cap_1 = danh_sach_loai_san_pham_cap_1;
    }

    public ArrayList<thuong_hieu> getDanh_sach_thuong_hieu() {
        return danh_sach_thuong_hieu;
    }

    public void setDanh_sach_thuong_hieu(ArrayList<thuong_hieu> danh_sach_thuong_hieu) {
        this.danh_sach_thuong_hieu = danh_sach_thuong_hieu;
    }

    public ArrayList<loai_san_pham_nhom_cap_2_phan_tu> getDanh_sach_chi_danh_cho_ban() {
        return danh_sach_chi_danh_cho_ban;
    }

    public void setDanh_sach_chi_danh_cho_ban(ArrayList<loai_san_pham_nhom_cap_2_phan_tu> danh_sach_chi_danh_cho_ban) {
        this.danh_sach_chi_danh_cho_ban = danh_sach_chi_danh_cho_ban;
    }
}
