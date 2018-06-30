package com.ntu.ynn_shop.ynn_shop.cac_cong_cu;

import android.util.Log;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thuong_hieu;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.bo_loc;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.danh_gia;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.gia_sp;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.loai_sp;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.phan_trang;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.sap_xep;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.yeu_cau;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class tao_yeu_cau {
    yeu_cau yeu_cau;
    ma_hoa_base64 base64 = new ma_hoa_base64();
    public tao_yeu_cau() {
        ArrayList<sap_xep> ds_sx = new ArrayList<sap_xep>();
        ds_sx.add(new sap_xep("sx_dg", "Số sao đánh giá", false));
        ds_sx.add(new sap_xep("sx_gs", "Giá sản phẩm",false));
        ds_sx.add(new sap_xep("sx_ut", "Độ ưa thích", false));

        ArrayList<loai_sp> ds_lsp = new ArrayList<loai_sp>();
        //ds_lsp.add(new loai_sp(123,"tenloai"));
        //ds_lsp.add(new loai_sp(123, "tenloai"));
        ArrayList<thuong_hieu> ds_th = new ArrayList<thuong_hieu>();
        //ds_th.add(new thuong_hieu(123, "th", "anh"));
        //ds_th.add(new thuong_hieu(123, "th", "anh"));
        yeu_cau yeu_cau_ds = new yeu_cau(
                new bo_loc(ds_lsp, ds_th, new gia_sp(-1, -1), new danh_gia((float)-1), null, new phan_trang(0, 20)),
                ds_sx
        );
        this.yeu_cau=yeu_cau_ds;
    }
    public yeu_cau json2yeu_cau(String json_yc) throws JSONException {
//            JSONObject js_yc = new JSONObject(json_yc);
//                JSONObject js_bo_loc = new JSONObject(js_yc.getString("bo_loc"));
////                    JSONArray js_ds_loai_sp = new JSONArray(js_bo_loc.getString("ds_loai_sp"));
////                    JSONArray js_ds_thuong_hieu = new JSONArray(js_bo_loc.getString("ds_thuong_hieu"));
////                    JSONObject js_gia_sp = new JSONObject(js_bo_loc.getString("gia_sp"));
////                    JSONObject js_danh_gia = new JSONObject(js_bo_loc.getString("danh_gia"));
////                    JSONObject js_tim_kiem = new JSONObject(js_bo_loc.getString("tim_kiem"));
////                    JSONObject js_phan_trang = new JSONObject(js_bo_loc.getString("phan_trang"));
////
////                JSONArray js_sap_xep = new JSONArray(js_yc.getString("sap_xep"));
//            yeu_cau yeu_cau;
//            bo_loc bo_loc = new bo_loc(
//                    kt_ds_lsp(js_bo_loc.getString("ds_loai_sp")),
//                    kt_ds_th(js_bo_loc.getString("ds_thuong_hieu")),
//                    kt_gsp(js_bo_loc.getString("gia_sp")),
//                    kt_dg(js_bo_loc.getString("danh_gia")),
//                    js_bo_loc.getString("tim_kiem"),
//                    kt_pt(js_bo_loc.getString("phan_trang"))
//            );
//            ArrayList<sap_xep> ds_sx = kt_sx(js_yc.getString("sap_xep"));
//            yeu_cau = new yeu_cau(bo_loc, ds_sx);
        Log.d("dataonline-<","here");
        Gson js  = new Gson();
        yeu_cau = js.fromJson(json_yc, yeu_cau.class);
            this.yeu_cau = yeu_cau;
         return yeu_cau;
    }

    public ArrayList<loai_sp> kt_ds_lsp(String json_string) throws JSONException {
        ArrayList<loai_sp> ds_lsp = new ArrayList<loai_sp>();
        if (json_string == "null" || json_string=="") return ds_lsp;
        JSONArray js_ds_loai_sp = new JSONArray(json_string);
        int max_ds_lsp = js_ds_loai_sp.length();
        for(int i_ds_lsp = 0; i_ds_lsp < max_ds_lsp; i_ds_lsp++)
        {
            JSONObject object = js_ds_loai_sp.getJSONObject(i_ds_lsp);
             int  id_loai_san_pham =  Integer.parseInt(object.getString("id_loai_san_pham"));
             String ten_loai_san_pham = base64.giai_ma(object.getString("ten_loai_san_pham"));
            ds_lsp.add(new loai_sp(id_loai_san_pham, ten_loai_san_pham));
        }
        return ds_lsp;
    }
    public ArrayList<thuong_hieu> kt_ds_th(String json_string) throws JSONException {
        ArrayList<thuong_hieu> danh_sach_thuong_hieu = new ArrayList<thuong_hieu>();
        if (json_string == "null" || json_string=="") return danh_sach_thuong_hieu;
        JSONArray js_thuong_hieu = new JSONArray(json_string);
        int max_thuong_hieu = js_thuong_hieu.length();
        for(int i_th = 0; i_th < max_thuong_hieu; i_th++)
        {

            JSONObject object = js_thuong_hieu.getJSONObject(i_th);
            int id_thuong_hieu =Integer.parseInt(object.getString("id_thuong_hieu"));
            String ten_thuong_hieu = base64.giai_ma(object.getString("ten_thuong_hieu"));
            String anh_thuong_hieu = base64.giai_ma(object.getString("anh_thuong_hieu"));
            danh_sach_thuong_hieu.add(new thuong_hieu(
                    id_thuong_hieu,
                    ten_thuong_hieu,
                    anh_thuong_hieu
            ));
        }
        return danh_sach_thuong_hieu;
    }
    public gia_sp kt_gsp(String json_string) throws JSONException {
        JSONObject js_gsp = new JSONObject(json_string);
        long gia_thap_nhat = Long.parseLong(js_gsp.getString("gia_thap_nhat"));
        long gia_cao_nhat = Long.parseLong(js_gsp.getString("gia_cao_nhat"));
        gia_sp gia_sp = new gia_sp(gia_thap_nhat, gia_cao_nhat);
        return gia_sp;
    }
    public danh_gia kt_dg(String json_string) throws JSONException {
        JSONObject js_dg = new JSONObject(json_string);
        float so_sao_thap_nhat = Float.parseFloat(js_dg.getString("so_sao_thap_nhat"));
        danh_gia gia_sp = new danh_gia(so_sao_thap_nhat);
        return gia_sp;
    }
    public phan_trang kt_pt(String json_string) throws JSONException {
        JSONObject js_pt = new JSONObject(json_string);
        int vi_tri_bat_dau = Integer.parseInt(js_pt.getString("vi_tri_bat_dau"));
        int vi_tri_ket_thuc = Integer.parseInt(js_pt.getString("vi_tri_ket_thuc"));
        phan_trang phan_trang = new phan_trang(vi_tri_bat_dau, vi_tri_ket_thuc);
        return phan_trang;
    }
    public ArrayList<sap_xep> kt_sx(String json_string) throws JSONException {
        ArrayList<sap_xep> ds_sx = new ArrayList<sap_xep>();
        if (json_string == "null" || json_string=="") return ds_sx;
        JSONArray js_ds_sx = new JSONArray(json_string);
        int max_ds_sx = js_ds_sx.length();
        for(int i_ds_sx = 0; i_ds_sx < max_ds_sx; i_ds_sx++)
        {
            JSONObject object = js_ds_sx.getJSONObject(i_ds_sx);
             String id_sap_xep = object.getString("id_sap_xep");
             String ten_sap_xep = object.getString("ten_sap_xep");
             Boolean loai_sap_xep = Boolean.parseBoolean(object.getString("loai_sap_xep"));

            ds_sx.add(new sap_xep(id_sap_xep, ten_sap_xep, loai_sap_xep));
        }
        return ds_sx;
    }
    public com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.yeu_cau getYeu_cau() {
        return yeu_cau;
    }

    public void setYeu_cau(com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.yeu_cau yeu_cau) {
        this.yeu_cau = yeu_cau;
    }

    public void set_thuong_hieu(ArrayList<thuong_hieu> thuong_hieu)
    {
        this.yeu_cau.getBo_loc().setDs_thuong_hieu(thuong_hieu);
    }

    public void set_loai_sp(ArrayList<loai_sp> loai_sp)
    {
        this.yeu_cau.getBo_loc().setDs_loai_sp(loai_sp);
    }

    public void setPhanTrang(int vitridau, int vitrisau)
    {
        this.yeu_cau.getBo_loc().getPhan_trang().setVi_tri_bat_dau(vitridau);
        this.yeu_cau.getBo_loc().getPhan_trang().setVi_tri_ket_thuc(vitrisau);
    }


}
