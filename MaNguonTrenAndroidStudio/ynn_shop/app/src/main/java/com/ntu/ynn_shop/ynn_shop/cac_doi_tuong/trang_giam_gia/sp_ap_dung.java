package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_giam_gia;

public class sp_ap_dung {
    public int id_san_pham ;
    public String ten_san_pham ;
    public String anh_san_pham ;
    public long gia_ban_ra ;
    public float phan_tram_giam ;
    public long giam_toi_da ;

    public sp_ap_dung(int id_san_pham, String ten_san_pham, String anh_san_pham, long gia_ban_ra, float phan_tram_giam, long giam_toi_da) {
        this.id_san_pham = id_san_pham;
        this.ten_san_pham = ten_san_pham;
        this.anh_san_pham = anh_san_pham;
        this.gia_ban_ra = gia_ban_ra;
        this.phan_tram_giam = phan_tram_giam;
        this.giam_toi_da = giam_toi_da;
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

    public long getGia_ban_ra() {
        return gia_ban_ra;
    }

    public void setGia_ban_ra(long gia_ban_ra) {
        this.gia_ban_ra = gia_ban_ra;
    }

    public float getPhan_tram_giam() {
        return phan_tram_giam;
    }

    public void setPhan_tram_giam(float phan_tram_giam) {
        this.phan_tram_giam = phan_tram_giam;
    }

    public long getGiam_toi_da() {
        return giam_toi_da;
    }

    public void setGiam_toi_da(long giam_toi_da) {
        this.giam_toi_da = giam_toi_da;
    }
}
