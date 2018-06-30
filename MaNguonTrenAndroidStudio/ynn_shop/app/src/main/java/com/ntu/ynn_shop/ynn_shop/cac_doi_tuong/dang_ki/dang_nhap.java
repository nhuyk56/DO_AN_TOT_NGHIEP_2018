package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.dang_ki;

public class dang_nhap {
    String email_nguoi;
    String mat_khau_nguoi;

    public dang_nhap(String email_nguoi, String mat_khau_nguoi) {
        this.email_nguoi = email_nguoi;
        this.mat_khau_nguoi = mat_khau_nguoi;
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
}
