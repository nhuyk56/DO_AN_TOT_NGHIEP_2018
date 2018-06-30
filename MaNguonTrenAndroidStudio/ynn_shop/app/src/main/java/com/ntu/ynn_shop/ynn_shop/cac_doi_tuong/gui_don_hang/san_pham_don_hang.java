package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gui_don_hang;

public class san_pham_don_hang {
    public int id_san_pham  ;
    public String ma_giam_gia  ;
    public int so_luong ;
    public long gia_san_pham ;
    public long gia_da_giam ;

    public san_pham_don_hang(int id_san_pham, String ma_giam_gia, int so_luong, long gia_san_pham, long gia_da_giam) {
        this.id_san_pham = id_san_pham;
        this.ma_giam_gia = ma_giam_gia;
        this.so_luong = so_luong;
        this.gia_san_pham = gia_san_pham;
        this.gia_da_giam = gia_da_giam;
    }

    public int getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(int id_san_pham) {
        this.id_san_pham = id_san_pham;
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
