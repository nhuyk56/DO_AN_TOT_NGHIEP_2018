package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong;

public class loai_san_pham_nhom_cap_2_phan_tu {
    public int id_loai_san_pham ;
    public String ten_loai_san_pham ;
    public String anh_loai_san_pham ;
    public int cap_do_loai_san_pham ;
    public int id_cha_loai_san_pham ;

    public loai_san_pham_nhom_cap_2_phan_tu(int id_loai_san_pham, String ten_loai_san_pham, String anh_loai_san_pham, int cap_do_loai_san_pham, int id_cha_loai_san_pham) {

        this.id_loai_san_pham = id_loai_san_pham;
        this.ten_loai_san_pham = ten_loai_san_pham;
        this.anh_loai_san_pham = anh_loai_san_pham;
        this.cap_do_loai_san_pham = cap_do_loai_san_pham;
        this.id_cha_loai_san_pham = id_cha_loai_san_pham;
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

    public int getCap_do_loai_san_pham() {
        return cap_do_loai_san_pham;
    }

    public void setCap_do_loai_san_pham(int cap_do_loai_san_pham) {
        this.cap_do_loai_san_pham = cap_do_loai_san_pham;
    }

    public int getId_cha_loai_san_pham() {
        return id_cha_loai_san_pham;
    }

    public void setId_cha_loai_san_pham(int id_cha_loai_san_pham) {
        this.id_cha_loai_san_pham = id_cha_loai_san_pham;
    }
}
