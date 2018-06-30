package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham;

public class truy_van_san_pham {
   public int id_nguoi;
   public int id_san_pham;

    public truy_van_san_pham(int id_nguoi, int id_san_pham) {
        this.id_nguoi = id_nguoi;
        this.id_san_pham = id_san_pham;
    }

    public int getId_nguoi() {
        return id_nguoi;
    }

    public void setId_nguoi(int id_nguoi) {
        this.id_nguoi = id_nguoi;
    }

    public int getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(int id_san_pham) {
        this.id_san_pham = id_san_pham;
    }
}
