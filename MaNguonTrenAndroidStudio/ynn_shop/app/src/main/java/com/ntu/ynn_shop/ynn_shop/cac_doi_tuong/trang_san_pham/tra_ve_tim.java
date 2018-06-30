package com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham;

public class tra_ve_tim {
    public boolean tha_tim;
    public int sl;

    public tra_ve_tim(boolean tha_tim, int sl) {
        this.tha_tim = tha_tim;
        this.sl = sl;
    }

    public boolean isTha_tim() {
        return tha_tim;
    }

    public void setTha_tim(boolean tha_tim) {
        this.tha_tim = tha_tim;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
