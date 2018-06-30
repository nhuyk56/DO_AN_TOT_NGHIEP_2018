package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gio_hang;

public class san_pham_gh {
    public int id_san_pham;
    public String ten_san_pham;
    public String anh_san_pham;
    public float khoi_luong_san_pham_gram;
    public String ma_giam_gia;
    public int so_luong;
    public int so_luong_max;
    public long gia_san_pham;
    public long gia_da_giam;

    public void set_san_pham_gh(int id_san_pham, String ten_san_pham, String anh_san_pham, float khoi_luong_san_pham_gram, String ma_giam_gia, int so_luong, int so_luong_max, long gia_san_pham, long gia_da_giam) {
        this.id_san_pham = id_san_pham;
        this.ten_san_pham = ten_san_pham;
        this.anh_san_pham = anh_san_pham;
        this.khoi_luong_san_pham_gram = khoi_luong_san_pham_gram;
        this.ma_giam_gia = ma_giam_gia;
        this.so_luong = so_luong;
        this.so_luong_max = so_luong_max;
        this.gia_san_pham = gia_san_pham;
        this.gia_da_giam = gia_da_giam;
    }

    public san_pham_gh(int id_san_pham, String ten_san_pham, int so_luong_max, String anh_san_pham, float khoi_luong_san_pham_gram, long gia_san_pham) {

        this.ten_san_pham = ten_san_pham;
        this.anh_san_pham = anh_san_pham;
        this.khoi_luong_san_pham_gram = khoi_luong_san_pham_gram;
        this.gia_san_pham = gia_san_pham;
        this.id_san_pham = id_san_pham;
        this.ma_giam_gia = null;
        this.so_luong = 1;
        this.so_luong_max=so_luong_max;
        this.gia_da_giam = gia_san_pham;
    }
    public int getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(int id_san_pham) {
        this.id_san_pham = id_san_pham;
    }

    public String getTen_san_pham() {
        return ten_san_pham;
    }

    public void setTen_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }

    public String getAnh_san_pham() {
        return anh_san_pham;
    }

    public void setAnh_san_pham(String anh_san_pham) {
        this.anh_san_pham = anh_san_pham;
    }

    public float getKhoi_luong_san_pham_gram() {
        return khoi_luong_san_pham_gram;
    }

    public void setKhoi_luong_san_pham_gram(float khoi_luong_san_pham_gram) {
        this.khoi_luong_san_pham_gram = khoi_luong_san_pham_gram;
    }

    public String getMa_giam_gia() {
        return ma_giam_gia;
    }

    public void setMa_giam_gia(String ma_giam_gia) {
        this.ma_giam_gia = ma_giam_gia;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public int getSo_luong_max() {
        return so_luong_max;
    }

    public void setSo_luong_max(int so_luong_max) {
        this.so_luong_max = so_luong_max;
    }

    public long getGia_san_pham() {
        return gia_san_pham;
    }

    public void setGia_san_pham(long gia_san_pham) {
        this.gia_san_pham = gia_san_pham;
    }

    public long getGia_da_giam() {
        return gia_da_giam;
    }

    public void setGia_da_giam(long gia_da_giam) {
        this.gia_da_giam = gia_da_giam;
    }
}
