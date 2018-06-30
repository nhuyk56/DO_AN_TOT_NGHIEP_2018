package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.dang_ki;

public class doi_mat_khau
{
    public String email_nguoi;
    public String mat_khau_nguoi;
    public String mat_khau_moi;

    public doi_mat_khau(String email_nguoi, String mat_khau_nguoi, String mat_khau_moi) {
        this.email_nguoi = email_nguoi;
        this.mat_khau_nguoi = mat_khau_nguoi;
        this.mat_khau_moi = mat_khau_moi;
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

    public String getMat_khau_moi() {
        return mat_khau_moi;
    }

    public void setMat_khau_moi(String mat_khau_moi) {
        this.mat_khau_moi = mat_khau_moi;
    }
}
