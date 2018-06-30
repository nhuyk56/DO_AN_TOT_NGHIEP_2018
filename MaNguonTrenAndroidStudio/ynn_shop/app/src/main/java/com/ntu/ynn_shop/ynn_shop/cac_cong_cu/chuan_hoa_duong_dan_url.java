package com.ntu.ynn_shop.ynn_shop.cac_cong_cu;

public class chuan_hoa_duong_dan_url {
    public String chuan_hoa(String link_can_chuan_hoa){
        link_can_chuan_hoa = link_can_chuan_hoa.replace("https://","");
        link_can_chuan_hoa = link_can_chuan_hoa.replace("http://","");
        link_can_chuan_hoa = link_can_chuan_hoa.replace("//","");
        link_can_chuan_hoa = "http://" + link_can_chuan_hoa;
        link_can_chuan_hoa = link_can_chuan_hoa.replace(".jpg","");
        link_can_chuan_hoa = link_can_chuan_hoa.replace(".jp","");
        link_can_chuan_hoa = link_can_chuan_hoa.replace(".j","");
        link_can_chuan_hoa = link_can_chuan_hoa + ".jpg";
        return link_can_chuan_hoa;
    }
}
