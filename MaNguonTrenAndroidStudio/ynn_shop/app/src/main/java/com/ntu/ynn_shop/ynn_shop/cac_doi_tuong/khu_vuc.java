package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong;

public class khu_vuc {
    int id_khu_vuc;
    int ma_khu_vuc;
    String ten_khu_vuc;
    String loai_khu_vuc;
    int id_khu_vuc_cha;

    public khu_vuc(int id_khu_vuc, int ma_khu_vuc, String ten_khu_vuc, String loai_khu_vuc, int id_khu_vuc_cha) {
        this.id_khu_vuc = id_khu_vuc;
        this.ma_khu_vuc = ma_khu_vuc;
        this.ten_khu_vuc = ten_khu_vuc;
        this.loai_khu_vuc = loai_khu_vuc;
        this.id_khu_vuc_cha = id_khu_vuc_cha;
    }

    public int getId_khu_vuc() {
        return id_khu_vuc;
    }

    public void setId_khu_vuc(int id_khu_vuc) {
        this.id_khu_vuc = id_khu_vuc;
    }

    public int getMa_khu_vuc() {
        return ma_khu_vuc;
    }

    public void setMa_khu_vuc(int ma_khu_vuc) {
        this.ma_khu_vuc = ma_khu_vuc;
    }

    public String getTen_khu_vuc() {
        return ten_khu_vuc;
    }

    public void setTen_khu_vuc(String ten_khu_vuc) {
        this.ten_khu_vuc = ten_khu_vuc;
    }

    public String getLoai_khu_vuc() {
        return loai_khu_vuc;
    }

    public void setLoai_khu_vuc(String loai_khu_vuc) {
        this.loai_khu_vuc = loai_khu_vuc;
    }

    public int getId_khu_vuc_cha() {
        return id_khu_vuc_cha;
    }

    public void setId_khu_vuc_cha(int id_khu_vuc_cha) {
        this.id_khu_vuc_cha = id_khu_vuc_cha;
    }
}
