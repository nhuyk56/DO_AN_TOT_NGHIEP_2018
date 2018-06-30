package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gui_don_hang;

import java.util.ArrayList;

public class don_hang {
    public int id_nguoi;
    public int id_ptvc;
    public int id_khu_vuc;
    public long phi_van_chuyen;
    public long phi_thu_ho;
    public long tong_tien;
    public float so_ngay_giao_gan_nhat;
    public float so_ngay_giao_xa_nhat;
    public String dia_chi_nhan_hang_chi_tiet;
    public ArrayList<san_pham_don_hang> chi_tiet_don_hang;

    public don_hang() {
    }
    public void  set_mang_hinh_thanh_toan(int id_nguoi, int id_khu_vuc, String dia_chi_nhan_hang_chi_tiet, ArrayList<san_pham_don_hang> chi_tiet_don_hang) {
        this.id_nguoi = id_nguoi;
        this.id_khu_vuc = id_khu_vuc;
        this.dia_chi_nhan_hang_chi_tiet = dia_chi_nhan_hang_chi_tiet;
        this.chi_tiet_don_hang = chi_tiet_don_hang;
    }
    public void  set_mang_hinh_phi_van_chuyen(int id_ptvc, long phi_van_chuyen, long phi_thu_ho, long tong_tien, float so_ngay_giao_gan_nhat, float so_ngay_giao_xa_nhat) {
        this.id_ptvc = id_ptvc;
        this.phi_van_chuyen = phi_van_chuyen;
        this.phi_thu_ho = phi_thu_ho;
        this.tong_tien = tong_tien;
        this.so_ngay_giao_gan_nhat = so_ngay_giao_gan_nhat;
        this.so_ngay_giao_xa_nhat = so_ngay_giao_xa_nhat;
    }

    public int getId_nguoi() {
        return id_nguoi;
    }

    public void setId_nguoi(int id_nguoi) {
        this.id_nguoi = id_nguoi;
    }

    public int getId_ptvc() {
        return id_ptvc;
    }

    public void setId_ptvc(int id_ptvc) {
        this.id_ptvc = id_ptvc;
    }

    public int getId_khu_vuc() {
        return id_khu_vuc;
    }

    public void setId_khu_vuc(int id_khu_vuc) {
        this.id_khu_vuc = id_khu_vuc;
    }

    public long getPhi_van_chuyen() {
        return phi_van_chuyen;
    }

    public void setPhi_van_chuyen(long phi_van_chuyen) {
        this.phi_van_chuyen = phi_van_chuyen;
    }

    public long getPhi_thu_ho() {
        return phi_thu_ho;
    }

    public void setPhi_thu_ho(long phi_thu_ho) {
        this.phi_thu_ho = phi_thu_ho;
    }

    public long getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(long tong_tien) {
        this.tong_tien = tong_tien;
    }

    public float getSo_ngay_giao_gan_nhat() {
        return so_ngay_giao_gan_nhat;
    }

    public void setSo_ngay_giao_gan_nhat(float so_ngay_giao_gan_nhat) {
        this.so_ngay_giao_gan_nhat = so_ngay_giao_gan_nhat;
    }

    public float getSo_ngay_giao_xa_nhat() {
        return so_ngay_giao_xa_nhat;
    }

    public void setSo_ngay_giao_xa_nhat(float so_ngay_giao_xa_nhat) {
        this.so_ngay_giao_xa_nhat = so_ngay_giao_xa_nhat;
    }

    public String getDia_chi_nhan_hang_chi_tiet() {
        return dia_chi_nhan_hang_chi_tiet;
    }

    public void setDia_chi_nhan_hang_chi_tiet(String dia_chi_nhan_hang_chi_tiet) {
        this.dia_chi_nhan_hang_chi_tiet = dia_chi_nhan_hang_chi_tiet;
    }

    public ArrayList<san_pham_don_hang> getChi_tiet_don_hang() {
        return chi_tiet_don_hang;
    }

    public void setChi_tiet_don_hang(ArrayList<san_pham_don_hang> chi_tiet_don_hang) {
        this.chi_tiet_don_hang = chi_tiet_don_hang;
    }
}
