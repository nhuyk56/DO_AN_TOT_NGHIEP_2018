package com.ntu.ynn_shop.ynn_shop.cac_cong_cu;

import android.util.Base64;
import android.util.Log;

public class ma_hoa_base64 {
    public String giai_ma(String stringenc)
    {
        try {
            return new String(android.util.Base64.decode(stringenc, android.util.Base64.NO_WRAP));
        }
        catch (Exception e){
            return new String(android.util.Base64.decode(stringenc+"=", android.util.Base64.NO_WRAP));
        }
    }

    public String ma_hoa(String stringdec)
    {
        return new String(Base64.encode(stringdec.getBytes(),Base64.NO_WRAP));
    }
}
