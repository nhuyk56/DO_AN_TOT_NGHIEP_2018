package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds;

public  class phan_trang{
    int vi_tri_bat_dau;
    int vi_tri_ket_thuc;

    public phan_trang(int vi_tri_bat_dau, int vi_tri_ket_thuc) {
        this.vi_tri_bat_dau = vi_tri_bat_dau;
        this.vi_tri_ket_thuc = vi_tri_ket_thuc;
    }

    public int getVi_tri_bat_dau() {
        return vi_tri_bat_dau;
    }

    public void setVi_tri_bat_dau(int vi_tri_bat_dau) {
        this.vi_tri_bat_dau = vi_tri_bat_dau;
    }

    public int getVi_tri_ket_thuc() {
        return vi_tri_ket_thuc;
    }

    public void setVi_tri_ket_thuc(int vi_tri_ket_thuc) {
        this.vi_tri_ket_thuc = vi_tri_ket_thuc;
    }
}
