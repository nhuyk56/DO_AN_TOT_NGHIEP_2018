package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham;

public class lsp {
    public int id_loai_san_pham;
    public String ten_loai_san_pham;

    public lsp(int id_loai_san_pham, String ten_loai_san_pham) {
        this.id_loai_san_pham = id_loai_san_pham;
        this.ten_loai_san_pham = ten_loai_san_pham;
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
}
