package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gio_hang;

public class yeu_cau_giam_gia {
    public int id_san_pham ;
    public String ma_giam_gia;

    public yeu_cau_giam_gia(int id_san_pham, String ma_giam_gia) {
        this.id_san_pham = id_san_pham;
        this.ma_giam_gia = ma_giam_gia;
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
}
