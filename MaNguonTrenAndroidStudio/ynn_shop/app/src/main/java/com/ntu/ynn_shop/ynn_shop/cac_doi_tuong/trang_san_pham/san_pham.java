package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham;

import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thuong_hieu;

import java.util.ArrayList;

public class san_pham {
    public int id_user;
    public boolean tha_tim;
    public int id_san_pham;
    public String ten_san_pham;
    public thuong_hieu thuong_hieuObject;
    public ArrayList< lsp > danh_sach_loai_san_pham = new ArrayList < lsp > ();
    public ArrayList <String > danh_sach_anh = new ArrayList < String > ();
    public long gia_cao;
    public long gia_ban_ra;
    public String mo_ta_san_pham_html;
    public String dac_tinh_san_pham;
    public float khoi_luong_san_pham_gram;
    public boolean da_co_vat;
    public int so_tim;
    public ArrayList < thong_tin_so_luong > thong_tin_so_luong = new ArrayList < thong_tin_so_luong > ();
    public ArrayList <danh_gia> danh_sach_danh_gia = new ArrayList < danh_gia > ();

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public boolean isTha_tim() {
        return tha_tim;
    }

    public void setTha_tim(boolean tha_tim) {
        this.tha_tim = tha_tim;
    }

    public int getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(int id_san_pham) {
        this.id_san_pham = id_san_pham;
    }

    public String getTen_san_pham() {
        return ten_san_pham;
    }

    public void setTen_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }

    public thuong_hieu getThuong_hieuObject() {
        return thuong_hieuObject;
    }

    public void setThuong_hieuObject(thuong_hieu thuong_hieuObject) {
        this.thuong_hieuObject = thuong_hieuObject;
    }

    public ArrayList<lsp> getDanh_sach_loai_san_pham() {
        return danh_sach_loai_san_pham;
    }

    public void setDanh_sach_loai_san_pham(ArrayList<lsp> danh_sach_loai_san_pham) {
        this.danh_sach_loai_san_pham = danh_sach_loai_san_pham;
    }

    public ArrayList<String> getDanh_sach_anh() {
        return danh_sach_anh;
    }

    public void setDanh_sach_anh(ArrayList<String> danh_sach_anh) {
        this.danh_sach_anh = danh_sach_anh;
    }

    public long getGia_cao() {
        return gia_cao;
    }

    public void setGia_cao(long gia_cao) {
        this.gia_cao = gia_cao;
    }

    public long getGia_ban_ra() {
        return gia_ban_ra;
    }

    public void setGia_ban_ra(long gia_ban_ra) {
        this.gia_ban_ra = gia_ban_ra;
    }

    public String getMo_ta_san_pham_html() {
        return mo_ta_san_pham_html;
    }

    public void setMo_ta_san_pham_html(String mo_ta_san_pham_html) {
        this.mo_ta_san_pham_html = mo_ta_san_pham_html;
    }

    public String getDac_tinh_san_pham() {
        return dac_tinh_san_pham;
    }

    public void setDac_tinh_san_pham(String dac_tinh_san_pham) {
        this.dac_tinh_san_pham = dac_tinh_san_pham;
    }

    public float getKhoi_luong_san_pham_gram() {
        return khoi_luong_san_pham_gram;
    }

    public void setKhoi_luong_san_pham_gram(float khoi_luong_san_pham_gram) {
        this.khoi_luong_san_pham_gram = khoi_luong_san_pham_gram;
    }

    public boolean isDa_co_vat() {
        return da_co_vat;
    }

    public void setDa_co_vat(boolean da_co_vat) {
        this.da_co_vat = da_co_vat;
    }

    public int getSo_tim() {
        return so_tim;
    }

    public void setSo_tim(int so_tim) {
        this.so_tim = so_tim;
    }

    public ArrayList<com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham.thong_tin_so_luong> getThong_tin_so_luong() {
        return thong_tin_so_luong;
    }

    public void setThong_tin_so_luong(ArrayList<com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham.thong_tin_so_luong> thong_tin_so_luong) {
        this.thong_tin_so_luong = thong_tin_so_luong;
    }

    public ArrayList<danh_gia> getDanh_sach_danh_gia() {
        return danh_sach_danh_gia;
    }

    public void setDanh_sach_danh_gia(ArrayList<danh_gia> danh_sach_danh_gia) {
        this.danh_sach_danh_gia = danh_sach_danh_gia;
    }

    public san_pham(int id_user, boolean tha_tim, int id_san_pham, String ten_san_pham, thuong_hieu thuong_hieuObject, ArrayList<lsp> danh_sach_loai_san_pham, ArrayList<String> danh_sach_anh, long gia_cao, long gia_ban_ra, String mo_ta_san_pham_html, String dac_tinh_san_pham, float khoi_luong_san_pham_gram, boolean da_co_vat, int so_tim, ArrayList<com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham.thong_tin_so_luong> thong_tin_so_luong, ArrayList<danh_gia> danh_sach_danh_gia) {
        this.id_user = id_user;
        this.tha_tim = tha_tim;
        this.id_san_pham = id_san_pham;
        this.ten_san_pham = ten_san_pham;
        this.thuong_hieuObject = thuong_hieuObject;
        this.danh_sach_loai_san_pham = danh_sach_loai_san_pham;
        this.danh_sach_anh = danh_sach_anh;
        this.gia_cao = gia_cao;
        this.gia_ban_ra = gia_ban_ra;
        this.mo_ta_san_pham_html = mo_ta_san_pham_html;
        this.dac_tinh_san_pham = dac_tinh_san_pham;
        this.khoi_luong_san_pham_gram = khoi_luong_san_pham_gram;
        this.da_co_vat = da_co_vat;
        this.so_tim = so_tim;
        this.thong_tin_so_luong = thong_tin_so_luong;
        this.danh_sach_danh_gia = danh_sach_danh_gia;
    }
}
