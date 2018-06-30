package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong;

public class thong_tin_nguoi_dung_thanh_toan {
   public String ten_nguoi, email_nguoi, sdt_nguoi;

    public thong_tin_nguoi_dung_thanh_toan(String ten_nguoi, String email_nguoi, String sdt_nguoi) {
        this.ten_nguoi = ten_nguoi;
        this.email_nguoi = email_nguoi;
        this.sdt_nguoi = sdt_nguoi;
    }

    public String getTen_nguoi() {
        return ten_nguoi;
    }

    public void setTen_nguoi(String ten_nguoi) {
        this.ten_nguoi = ten_nguoi;
    }

    public String getEmail_nguoi() {
        return email_nguoi;
    }

    public void setEmail_nguoi(String email_nguoi) {
        this.email_nguoi = email_nguoi;
    }

    public String getSdt_nguoi() {
        return sdt_nguoi;
    }

    public void setSdt_nguoi(String sdt_nguoi) {
        this.sdt_nguoi = sdt_nguoi;
    }
}
