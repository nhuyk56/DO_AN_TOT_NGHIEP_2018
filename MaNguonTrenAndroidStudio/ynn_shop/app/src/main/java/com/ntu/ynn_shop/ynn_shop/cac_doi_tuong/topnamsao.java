package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong;

public class topnamsao {
    public int id_san_pham;
    public String ten_san_pham;
    public String anh_san_pham;
    public long gia_cao;
    public long gia_ban_ra;
    public int so_tim;

    public topnamsao(int id_san_pham, String ten_san_pham, String anh_san_pham, long gia_cao, long gia_ban_ra, int so_tim) {
        this.id_san_pham = id_san_pham;
        this.ten_san_pham = ten_san_pham;
        this.anh_san_pham = anh_san_pham;
        this.gia_cao = gia_cao;
        this.gia_ban_ra = gia_ban_ra;
        this.so_tim = so_tim;
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

    public String getAnh_san_pham() {
        return anh_san_pham;
    }

    public void setAnh_san_pham(String anh_san_pham) {
        this.anh_san_pham = anh_san_pham;
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

    public int getSo_tim() {
        return so_tim;
    }

    public void setSo_tim(int so_tim) {
        this.so_tim = so_tim;
    }
}