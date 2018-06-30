package com.ntu.ynn_shop.ynn_shop.cac_cong_cu;

public class string2_bieu_tuong {
    public String string2_bieu_tuong(String iconText){
        iconText = iconText.replace("\\","");
        String[] arr = iconText.split("u");
        String text = "";
        for(int ix = 1; ix < arr.length; ix++){
            int hexVal = Integer.parseInt(arr[ix], 16);
            text += (char)hexVal;
        }
        iconText =  text;
        return iconText;
    }
}
