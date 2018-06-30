package com.ntu.ynn_shop.ynn_shop.cac_cong_cu;
import android.util.Log;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.loai_san_pham_cap_0;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.loai_san_pham_cap_1;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.loai_san_pham_nhom_cap_2;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.loai_san_pham_nhom_cap_2_phan_tu;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thuong_hieu;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
public class json2_loai_san_pham_tat_ca {
    ma_hoa_base64 base64 = new ma_hoa_base64();
    public ArrayList<loai_san_pham_cap_0> lay_c0(String json_string) throws JSONException {
        ArrayList<loai_san_pham_cap_0> danh_sach_lsp_c0 = new ArrayList<loai_san_pham_cap_0>();
        string2_bieu_tuong str2_bt = new string2_bieu_tuong();
        if (json_string == "null" || json_string=="") return danh_sach_lsp_c0;
        //code lay du lieu c0
        JSONArray js_c0 = new JSONArray(json_string);
        int max_c0 = js_c0.length();
        for(int i_c0 = 0; i_c0 < max_c0; i_c0++)
        {
            JSONObject object = js_c0.getJSONObject(i_c0);
            int id_loai_san_pham = Integer.parseInt(object.getString("id_loai_san_pham"));
            String ten_loai_san_pham = base64.giai_ma(object.getString("ten_loai_san_pham"));
            String anh_loai_san_pham = object.getString("anh_loai_san_pham");
            String bieu_tuong_loai_san_pham =  str2_bt.string2_bieu_tuong(object.getString("bieu_tuong_loai_san_pham"));
            int cap_do_loai_san_pham  = Integer.parseInt(object.getString("cap_do_loai_san_pham"));
            int id_cha_loai_san_pham = Integer.parseInt(object.getString("id_cha_loai_san_pham"));
            ArrayList<loai_san_pham_cap_1> danh_sach_loai_san_pham_cap_1 = lay_lsp_c1(object.getString("list_c1_item"));
            ArrayList<thuong_hieu> danh_sach_thuong_hieu = lay_thuong_hieu(object.getString("list_thuong_hieu"));
            ArrayList<loai_san_pham_nhom_cap_2_phan_tu> danh_sach_chi_danh_cho_ban = lay_chi_danh_cho_ban(object.getString("list_chi_danh_cho_ban"));
            danh_sach_lsp_c0.add(new loai_san_pham_cap_0(
                    id_loai_san_pham,
                    ten_loai_san_pham,
                    anh_loai_san_pham,
                    bieu_tuong_loai_san_pham,
                    cap_do_loai_san_pham,
                    id_cha_loai_san_pham,
                    danh_sach_loai_san_pham_cap_1,
                    danh_sach_thuong_hieu,
                    danh_sach_chi_danh_cho_ban
            ));
        }
        return danh_sach_lsp_c0;
    }
    public ArrayList<loai_san_pham_nhom_cap_2_phan_tu> lay_chi_danh_cho_ban(String json_string) throws JSONException{
        ArrayList<loai_san_pham_nhom_cap_2_phan_tu> danh_sach_chi_danh_cho_ban = new ArrayList<loai_san_pham_nhom_cap_2_phan_tu>();
        if (json_string == "null" || json_string=="") return danh_sach_chi_danh_cho_ban;
        JSONArray js_cdcb = new JSONArray(json_string);
        int max_cdcb = js_cdcb.length();
        for(int i_cdcb = 0; i_cdcb < max_cdcb; i_cdcb++)
        {
            JSONObject object = js_cdcb.getJSONObject(i_cdcb);
            int id_loai_san_pham = Integer.parseInt(object.getString("id_loai_san_pham"));
            String ten_loai_san_pham = base64.giai_ma(object.getString("ten_loai_san_pham"));
            String anh_loai_san_pham = object.getString("anh_loai_san_pham");
            int cap_do_loai_san_pham = Integer.parseInt(object.getString("cap_do_loai_san_pham"));
            int id_cha_loai_san_pham =  Integer.parseInt(object.getString("id_cha_loai_san_pham"));
            danh_sach_chi_danh_cho_ban.add(new loai_san_pham_nhom_cap_2_phan_tu(
                    id_loai_san_pham,
                    ten_loai_san_pham,
                    anh_loai_san_pham,
                    cap_do_loai_san_pham,
                    id_cha_loai_san_pham
            ));
        }
        return danh_sach_chi_danh_cho_ban;
    }
    public ArrayList<thuong_hieu> lay_thuong_hieu(String json_string) throws JSONException {
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
    public ArrayList<loai_san_pham_cap_1> lay_lsp_c1(String json_string) throws JSONException{
        ArrayList<loai_san_pham_cap_1> danh_sach_lsp_c1 = new ArrayList<loai_san_pham_cap_1>();
        if (json_string == "null" || json_string=="") return danh_sach_lsp_c1;
        JSONArray js_c1 = new JSONArray(json_string);
        int max_c1 = js_c1.length();
        for (int i_c1 = 0; i_c1 < max_c1; i_c1++)
        {
            JSONObject object = js_c1.getJSONObject(i_c1);
            int id_loai_san_pham = Integer.parseInt(object.getString("id_loai_san_pham"));
            String ten_loai_san_pham = base64.giai_ma(object.getString("ten_loai_san_pham"));
            int cap_do_loai_san_pham = Integer.parseInt(object.getString("cap_do_loai_san_pham"));
            int id_cha_loai_san_pham = Integer.parseInt(object.getString("id_cha_loai_san_pham"));
            ArrayList<loai_san_pham_nhom_cap_2> danh_sach_loai_san_pham_nhom_cap_2 = lay_lspn_c2(object.getString("list_c2_group"));
            danh_sach_lsp_c1.add(new loai_san_pham_cap_1(
                    id_loai_san_pham,
                    ten_loai_san_pham,
                    cap_do_loai_san_pham,
                    id_cha_loai_san_pham,
                    danh_sach_loai_san_pham_nhom_cap_2
            ));
        }
        return danh_sach_lsp_c1;
    }
    public ArrayList<loai_san_pham_nhom_cap_2> lay_lspn_c2(String json_string) throws JSONException{
        ArrayList<loai_san_pham_nhom_cap_2> danh_sach_lspn_c2 = new ArrayList<loai_san_pham_nhom_cap_2>();
        if (json_string == "null" || json_string=="") return danh_sach_lspn_c2;
        JSONArray js_nc2 = new JSONArray(json_string);
        int max_nc2 = js_nc2.length();
        for(int i_nc2 = 0; i_nc2 < max_nc2; i_nc2++)
        {
            JSONObject object = js_nc2.getJSONObject(i_nc2);
            ArrayList<loai_san_pham_nhom_cap_2_phan_tu> danh_sach_loai_san_pham_nhom_cap_2_phan_tu = lay_lspn_c2_pt(object.getString("c2_group_3i"));
            danh_sach_lspn_c2.add(new loai_san_pham_nhom_cap_2(danh_sach_loai_san_pham_nhom_cap_2_phan_tu));
        }
        return  danh_sach_lspn_c2;
    }
    public ArrayList<loai_san_pham_nhom_cap_2_phan_tu> lay_lspn_c2_pt(String json_string) throws JSONException{
        ArrayList<loai_san_pham_nhom_cap_2_phan_tu> danh_sach_lspn_c2_pt = new ArrayList<loai_san_pham_nhom_cap_2_phan_tu>();
        if (json_string == "null" || json_string=="") return danh_sach_lspn_c2_pt;
        JSONArray js_nc2_pt = new JSONArray(json_string);
        int max_nc2_pt = js_nc2_pt.length();
        for(int i_nc2_pt = 0; i_nc2_pt < max_nc2_pt; i_nc2_pt++)
        {
            JSONObject object = js_nc2_pt.getJSONObject(i_nc2_pt);
            int id_loai_san_pham = Integer.parseInt(object.getString("id_loai_san_pham"));
            String ten_loai_san_pham = base64.giai_ma(object.getString("ten_loai_san_pham"));
            String anh_loai_san_pham = object.getString("anh_loai_san_pham");
            int cap_do_loai_san_pham = Integer.parseInt(object.getString("cap_do_loai_san_pham"));
            int id_cha_loai_san_pham =  Integer.parseInt(object.getString("id_cha_loai_san_pham"));
            danh_sach_lspn_c2_pt.add(new loai_san_pham_nhom_cap_2_phan_tu(
                    id_loai_san_pham,
                    ten_loai_san_pham,
                    anh_loai_san_pham,
                    cap_do_loai_san_pham,
                    id_cha_loai_san_pham
            ));
        }
        return danh_sach_lspn_c2_pt;
    }
}
