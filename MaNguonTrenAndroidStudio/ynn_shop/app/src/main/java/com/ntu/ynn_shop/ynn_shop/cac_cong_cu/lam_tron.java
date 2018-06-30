package com.ntu.ynn_shop.ynn_shop.cac_cong_cu;

import java.text.DecimalFormat;

public class lam_tron {
   public String  tron(double d, String pattern)
    {
        DecimalFormat twoDForm = new DecimalFormat(pattern);
        return twoDForm.format(d);
    }
}
