package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham;

public class danh_gia {
    public int id_danh_gia;
    public boolean da_mua;
    public String tieu_de_danh_gia;
    public int so_sao_danh_gia;
    public String noi_dung;
    public String ngay_viet_danh_gia;
    public String ten_nguoi;

    public danh_gia(int id_danh_gia, boolean da_mua, String tieu_de_danh_gia, int so_sao_danh_gia, String noi_dung, String ngay_viet_danh_gia, String ten_nguoi) {
        this.id_danh_gia = id_danh_gia;
        this.da_mua = da_mua;
        this.tieu_de_danh_gia = tieu_de_danh_gia;
        this.so_sao_danh_gia = so_sao_danh_gia;
        this.noi_dung = noi_dung;
        this.ngay_viet_danh_gia = ngay_viet_danh_gia;
        this.ten_nguoi = ten_nguoi;
    }

    public int getId_danh_gia() {
        return id_danh_gia;
    }

    public void setId_danh_gia(int id_danh_gia) {
        this.id_danh_gia = id_danh_gia;
    }

    public boolean isDa_mua() {
        return da_mua;
    }

    public void setDa_mua(boolean da_mua) {
        this.da_mua = da_mua;
    }

    public String getTieu_de_danh_gia() {
        return tieu_de_danh_gia;
    }

    public void setTieu_de_danh_gia(String tieu_de_danh_gia) {
        this.tieu_de_danh_gia = tieu_de_danh_gia;
    }

    public int getSo_sao_danh_gia() {
        return so_sao_danh_gia;
    }

    public void setSo_sao_danh_gia(int so_sao_danh_gia) {
        this.so_sao_danh_gia = so_sao_danh_gia;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }

    public String getNgay_viet_danh_gia() {
        return ngay_viet_danh_gia;
    }

    public void setNgay_viet_danh_gia(String ngay_viet_danh_gia) {
        this.ngay_viet_danh_gia = ngay_viet_danh_gia;
    }

    public String getTen_nguoi() {
        return ten_nguoi;
    }

    public void setTen_nguoi(String ten_nguoi) {
        this.ten_nguoi = ten_nguoi;
    }
}
