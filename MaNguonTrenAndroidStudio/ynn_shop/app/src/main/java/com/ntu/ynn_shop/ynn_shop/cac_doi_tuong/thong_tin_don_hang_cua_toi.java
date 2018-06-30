package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong;

public class thong_tin_don_hang_cua_toi {
    public int id_don_hang;
    public int id_ttdh;	;
    public String ten_ttdh	;
    public String ten_ptvc	;
    public String ma_van_chuyen	;
    public long phi_van_chuyen	;
    public long phi_thu_ho	;
    public long tien_thu_ho	;
    public long tong_tien	;
    public String ngay_tao_don_hang	;
    public String ngay_xac_nhan_don_hang_thanh_cong	;
    public int so_ngay_giao_gan_nhat	;
    public int so_ngay_giao_xa_nhat	;
    public String dia_chi_nhan_hang_chi_tiet;

    public thong_tin_don_hang_cua_toi(int id_don_hang, int id_ttdh, String ten_ttdh, String ten_ptvc, String ma_van_chuyen, long phi_van_chuyen, long phi_thu_ho, long tien_thu_ho, long tong_tien, String ngay_tao_don_hang, String ngay_xac_nhan_don_hang_thanh_cong, int so_ngay_giao_gan_nhat, int so_ngay_giao_xa_nhat, String dia_chi_nhan_hang_chi_tiet) {
        this.id_don_hang = id_don_hang;
        this.id_ttdh = id_ttdh;
        this.ten_ttdh = ten_ttdh;
        this.ten_ptvc = ten_ptvc;
        this.ma_van_chuyen = ma_van_chuyen;
        this.phi_van_chuyen = phi_van_chuyen;
        this.phi_thu_ho = phi_thu_ho;
        this.tien_thu_ho = tien_thu_ho;
        this.tong_tien = tong_tien;
        this.ngay_tao_don_hang = ngay_tao_don_hang;
        this.ngay_xac_nhan_don_hang_thanh_cong = ngay_xac_nhan_don_hang_thanh_cong;
        this.so_ngay_giao_gan_nhat = so_ngay_giao_gan_nhat;
        this.so_ngay_giao_xa_nhat = so_ngay_giao_xa_nhat;
        this.dia_chi_nhan_hang_chi_tiet = dia_chi_nhan_hang_chi_tiet;
    }

    public int getId_don_hang() {
        return id_don_hang;
    }

    public void setId_don_hang(int id_don_hang) {
        this.id_don_hang = id_don_hang;
    }

    public int getId_ttdh() {
        return id_ttdh;
    }

    public void setId_ttdh(int id_ttdh) {
        this.id_ttdh = id_ttdh;
    }

    public String getTen_ttdh() {
        return ten_ttdh;
    }

    public void setTen_ttdh(String ten_ttdh) {
        this.ten_ttdh = ten_ttdh;
    }

    public String getTen_ptvc() {
        return ten_ptvc;
    }

    public void setTen_ptvc(String ten_ptvc) {
        this.ten_ptvc = ten_ptvc;
    }

    public String getMa_van_chuyen() {
        return ma_van_chuyen;
    }

    public void setMa_van_chuyen(String ma_van_chuyen) {
        this.ma_van_chuyen = ma_van_chuyen;
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

    public long getTien_thu_ho() {
        return tien_thu_ho;
    }

    public void setTien_thu_ho(long tien_thu_ho) {
        this.tien_thu_ho = tien_thu_ho;
    }

    public long getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(long tong_tien) {
        this.tong_tien = tong_tien;
    }

    public String getNgay_tao_don_hang() {
        return ngay_tao_don_hang;
    }

    public void setNgay_tao_don_hang(String ngay_tao_don_hang) {
        this.ngay_tao_don_hang = ngay_tao_don_hang;
    }

    public String getNgay_xac_nhan_don_hang_thanh_cong() {
        return ngay_xac_nhan_don_hang_thanh_cong;
    }

    public void setNgay_xac_nhan_don_hang_thanh_cong(String ngay_xac_nhan_don_hang_thanh_cong) {
        this.ngay_xac_nhan_don_hang_thanh_cong = ngay_xac_nhan_don_hang_thanh_cong;
    }

    public int getSo_ngay_giao_gan_nhat() {
        return so_ngay_giao_gan_nhat;
    }

    public void setSo_ngay_giao_gan_nhat(int so_ngay_giao_gan_nhat) {
        this.so_ngay_giao_gan_nhat = so_ngay_giao_gan_nhat;
    }

    public int getSo_ngay_giao_xa_nhat() {
        return so_ngay_giao_xa_nhat;
    }

    public void setSo_ngay_giao_xa_nhat(int so_ngay_giao_xa_nhat) {
        this.so_ngay_giao_xa_nhat = so_ngay_giao_xa_nhat;
    }

    public String getDia_chi_nhan_hang_chi_tiet() {
        return dia_chi_nhan_hang_chi_tiet;
    }

    public void setDia_chi_nhan_hang_chi_tiet(String dia_chi_nhan_hang_chi_tiet) {
        this.dia_chi_nhan_hang_chi_tiet = dia_chi_nhan_hang_chi_tiet;
    }
}
