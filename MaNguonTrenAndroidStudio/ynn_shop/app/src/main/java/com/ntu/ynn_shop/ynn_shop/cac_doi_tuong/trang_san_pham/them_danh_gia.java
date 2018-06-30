package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham;

public class them_danh_gia {
    public  int id_san_pham;
    public  int id_nguoi;
    public   boolean da_mua;
    public  String tieu_de_danh_gia;
    public    int so_sao_danh_gia;
    public   String noi_dung;
    public    String ngay_viet_danh_gia;

    public them_danh_gia(int id_san_pham, int id_nguoi, boolean da_mua, String tieu_de_danh_gia, int so_sao_danh_gia, String noi_dung, String ngay_viet_danh_gia) {
        this.id_san_pham = id_san_pham;
        this.id_nguoi = id_nguoi;
        this.da_mua = da_mua;
        this.tieu_de_danh_gia = tieu_de_danh_gia;
        this.so_sao_danh_gia = so_sao_danh_gia;
        this.noi_dung = noi_dung;
        this.ngay_viet_danh_gia = ngay_viet_danh_gia;
    }

    public int getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(int id_san_pham) {
        this.id_san_pham = id_san_pham;
    }

    public int getId_nguoi() {
        return id_nguoi;
    }

    public void setId_nguoi(int id_nguoi) {
        this.id_nguoi = id_nguoi;
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
}
