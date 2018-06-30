package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.dang_ki;

public class nguoi {
    public  String email_nguoi ;

    public  String mat_khau_nguoi ;

    public  String ten_nguoi ;

    public  String ngay_sinh ;

    public  String dia_chi_nguoi ;

    public  String sdt_nguoi ;

    public Boolean gioi_tinh_nguoi ;

    public nguoi(String email_nguoi, String mat_khau_nguoi, String ten_nguoi, String ngay_sinh, String dia_chi_nguoi, String sdt_nguoi, Boolean gioi_tinh_nguoi) {
        this.email_nguoi = email_nguoi;
        this.mat_khau_nguoi = mat_khau_nguoi;
        this.ten_nguoi = ten_nguoi;
        this.ngay_sinh = ngay_sinh;
        this.dia_chi_nguoi = dia_chi_nguoi;
        this.sdt_nguoi = sdt_nguoi;
        this.gioi_tinh_nguoi = gioi_tinh_nguoi;
    }

    public String getEmail_nguoi() {
        return email_nguoi;
    }

    public void setEmail_nguoi(String email_nguoi) {
        this.email_nguoi = email_nguoi;
    }

    public String getMat_khau_nguoi() {
        return mat_khau_nguoi;
    }

    public void setMat_khau_nguoi(String mat_khau_nguoi) {
        this.mat_khau_nguoi = mat_khau_nguoi;
    }

    public String getTen_nguoi() {
        return ten_nguoi;
    }

    public void setTen_nguoi(String ten_nguoi) {
        this.ten_nguoi = ten_nguoi;
    }

    public String getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(String ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public String getDia_chi_nguoi() {
        return dia_chi_nguoi;
    }

    public void setDia_chi_nguoi(String dia_chi_nguoi) {
        this.dia_chi_nguoi = dia_chi_nguoi;
    }

    public String getSdt_nguoi() {
        return sdt_nguoi;
    }

    public void setSdt_nguoi(String sdt_nguoi) {
        this.sdt_nguoi = sdt_nguoi;
    }

    public Boolean getGioi_tinh_nguoi() {
        return gioi_tinh_nguoi;
    }

    public void setGioi_tinh_nguoi(Boolean gioi_tinh_nguoi) {
        this.gioi_tinh_nguoi = gioi_tinh_nguoi;
    }
}
