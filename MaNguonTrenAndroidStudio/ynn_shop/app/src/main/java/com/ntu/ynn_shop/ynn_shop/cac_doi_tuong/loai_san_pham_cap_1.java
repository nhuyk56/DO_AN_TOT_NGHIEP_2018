package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong;
import java.util.ArrayList;
public class loai_san_pham_cap_1 {
    public int id_loai_san_pham ;
    public String ten_loai_san_pham ;
    public int cap_do_loai_san_pham ;
    public int id_cha_loai_san_pham ;
    public ArrayList<loai_san_pham_nhom_cap_2> danh_sach_loai_san_pham_nhom_cap_2 ;

    public loai_san_pham_cap_1(int id_loai_san_pham, String ten_loai_san_pham, int cap_do_loai_san_pham, int id_cha_loai_san_pham, ArrayList<loai_san_pham_nhom_cap_2> danh_sach_loai_san_pham_nhom_cap_2) {
        this.id_loai_san_pham = id_loai_san_pham;
        this.ten_loai_san_pham = ten_loai_san_pham;
        this.cap_do_loai_san_pham = cap_do_loai_san_pham;
        this.id_cha_loai_san_pham = id_cha_loai_san_pham;
        this.danh_sach_loai_san_pham_nhom_cap_2 = danh_sach_loai_san_pham_nhom_cap_2;
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

    public ArrayList<loai_san_pham_nhom_cap_2> getDanh_sach_loai_san_pham_nhom_cap_2() {
        return danh_sach_loai_san_pham_nhom_cap_2;
    }

    public void setDanh_sach_loai_san_pham_nhom_cap_2(ArrayList<loai_san_pham_nhom_cap_2> danh_sach_loai_san_pham_nhom_cap_2) {
        this.danh_sach_loai_san_pham_nhom_cap_2 = danh_sach_loai_san_pham_nhom_cap_2;
    }
}
