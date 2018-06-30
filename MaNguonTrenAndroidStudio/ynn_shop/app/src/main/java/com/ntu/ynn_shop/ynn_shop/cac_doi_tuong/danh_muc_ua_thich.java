package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong;

public class danh_muc_ua_thich {
    public int id_loai_san_pham;
    public String ten_loai_san_pham;
    public String anh_loai_san_pham;

    public danh_muc_ua_thich(int id_loai_san_pham, String ten_loai_san_pham, String anh_loai_san_pham) {
        this.id_loai_san_pham = id_loai_san_pham;
        this.ten_loai_san_pham = ten_loai_san_pham;
        this.anh_loai_san_pham = anh_loai_san_pham;
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
}
