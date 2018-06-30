package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong;

public class don_hang_cua_toi {
    public int ma_don_hang;
    public String tinh_trang_don_hang;
    public long tong_tien_thanh_toan;
    public String ngay_dat_hang;

    public don_hang_cua_toi(int ma_don_hang, String tinh_trang_don_hang, long tong_tien_thanh_toan, String ngay_dat_hang) {
        this.ma_don_hang = ma_don_hang;
        this.tinh_trang_don_hang = tinh_trang_don_hang;
        this.tong_tien_thanh_toan = tong_tien_thanh_toan;
        this.ngay_dat_hang = ngay_dat_hang;
    }

    public int getMa_don_hang() {
        return ma_don_hang;
    }

    public void setMa_don_hang(int ma_don_hang) {
        this.ma_don_hang = ma_don_hang;
    }

    public String getTinh_trang_don_hang() {
        return tinh_trang_don_hang;
    }

    public void setTinh_trang_don_hang(String tinh_trang_don_hang) {
        this.tinh_trang_don_hang = tinh_trang_don_hang;
    }

    public long getTong_tien_thanh_toan() {
        return tong_tien_thanh_toan;
    }

    public void setTong_tien_thanh_toan(long tong_tien_thanh_toan) {
        this.tong_tien_thanh_toan = tong_tien_thanh_toan;
    }

    public String getNgay_dat_hang() {
        return ngay_dat_hang;
    }

    public void setNgay_dat_hang(String ngay_dat_hang) {
        this.ngay_dat_hang = ngay_dat_hang;
    }
}
