package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds;

import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thuong_hieu;

import java.util.ArrayList;

public class bo_loc {
    ArrayList<loai_sp> ds_loai_sp;
    ArrayList<thuong_hieu> ds_thuong_hieu;
    gia_sp gia_sp;
    danh_gia danh_gia;
    String tim_kiem;
    phan_trang phan_trang;

    public bo_loc(ArrayList<loai_sp> ds_loai_sp, ArrayList<thuong_hieu> ds_thuong_hieu, com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.gia_sp gia_sp, com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.danh_gia danh_gia, String tim_kiem, com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.phan_trang phan_trang) {
        this.ds_loai_sp = ds_loai_sp;
        this.ds_thuong_hieu = ds_thuong_hieu;
        this.gia_sp = gia_sp;
        this.danh_gia = danh_gia;
        this.tim_kiem = tim_kiem;
        this.phan_trang = phan_trang;
    }

    public ArrayList<loai_sp> getDs_loai_sp() {
        return ds_loai_sp;
    }

    public void setDs_loai_sp(ArrayList<loai_sp> ds_loai_sp) {
        this.ds_loai_sp = ds_loai_sp;
    }

    public ArrayList<thuong_hieu> getDs_thuong_hieu() {
        return ds_thuong_hieu;
    }

    public void setDs_thuong_hieu(ArrayList<thuong_hieu> ds_thuong_hieu) {
        this.ds_thuong_hieu = ds_thuong_hieu;
    }

    public com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.gia_sp getGia_sp() {
        return gia_sp;
    }

    public void setGia_sp(com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.gia_sp gia_sp) {
        this.gia_sp = gia_sp;
    }

    public com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.danh_gia getDanh_gia() {
        return danh_gia;
    }

    public void setDanh_gia(com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.danh_gia danh_gia) {
        this.danh_gia = danh_gia;
    }

    public String getTim_kiem() {
        return tim_kiem;
    }

    public void setTim_kiem(String tim_kiem) {
        this.tim_kiem = tim_kiem;
    }

    public com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.phan_trang getPhan_trang() {
        return phan_trang;
    }

    public void setPhan_trang(com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.phan_trang phan_trang) {
        this.phan_trang = phan_trang;
    }
}

