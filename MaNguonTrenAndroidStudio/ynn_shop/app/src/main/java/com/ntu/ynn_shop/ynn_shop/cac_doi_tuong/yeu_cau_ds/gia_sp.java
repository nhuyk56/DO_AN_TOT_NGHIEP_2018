package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds;

public class gia_sp{
    public long gia_thap_nhat;
    public long gia_cao_nhat;

    public gia_sp(long gia_thap_nhat, long gia_cao_nhat) {
        this.gia_thap_nhat = gia_thap_nhat;
        this.gia_cao_nhat = gia_cao_nhat;
    }

    public long getGia_thap_nhat() {
        return gia_thap_nhat;
    }

    public void setGia_thap_nhat(long gia_thap_nhat) {
        this.gia_thap_nhat = gia_thap_nhat;
    }

    public long getGia_cao_nhat() {
        return gia_cao_nhat;
    }

    public void setGia_cao_nhat(long gia_cao_nhat) {
        this.gia_cao_nhat = gia_cao_nhat;
    }
}