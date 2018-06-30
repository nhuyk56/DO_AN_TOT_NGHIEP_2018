package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham;

public class thong_tin_so_luong {
    public int id_kho_hang;
    public String ten_kho_hang;
    public String dia_chi_kho_hang;
    public int so_luong;
    public int id_san_pham;

    public thong_tin_so_luong(int id_kho_hang, String ten_kho_hang, String dia_chi_kho_hang, int so_luong, int id_san_pham) {
        this.id_kho_hang = id_kho_hang;
        this.ten_kho_hang = ten_kho_hang;
        this.dia_chi_kho_hang = dia_chi_kho_hang;
        this.so_luong = so_luong;
        this.id_san_pham = id_san_pham;
    }

    public int getId_kho_hang() {
        return id_kho_hang;
    }

    public void setId_kho_hang(int id_kho_hang) {
        this.id_kho_hang = id_kho_hang;
    }

    public String getTen_kho_hang() {
        return ten_kho_hang;
    }

    public void setTen_kho_hang(String ten_kho_hang) {
        this.ten_kho_hang = ten_kho_hang;
    }

    public String getDia_chi_kho_hang() {
        return dia_chi_kho_hang;
    }

    public void setDia_chi_kho_hang(String dia_chi_kho_hang) {
        this.dia_chi_kho_hang = dia_chi_kho_hang;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public int getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(int id_san_pham) {
        this.id_san_pham = id_san_pham;
    }
}
