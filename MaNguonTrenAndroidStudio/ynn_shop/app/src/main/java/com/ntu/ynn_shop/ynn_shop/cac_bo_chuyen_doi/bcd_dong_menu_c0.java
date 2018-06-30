package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.loai_san_pham_cap_0;

import java.util.ArrayList;

public class bcd_dong_menu_c0 extends BaseAdapter{
    private int mSelectedPosition = 0;
    Context context;
    ArrayList<loai_san_pham_cap_0> danh_sach_lsp_c0;

    public bcd_dong_menu_c0(Context context, ArrayList<loai_san_pham_cap_0> danh_sach_lsp_c0) {
        this.context = context;
        this.danh_sach_lsp_c0 = danh_sach_lsp_c0;
    }

    @Override
    public int getCount() {
        return danh_sach_lsp_c0.size();
    }

    @Override
    public Object getItem(int position) {
        return danh_sach_lsp_c0.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class luu_tru_bcd_dong_menu_c0{
        TextView bt, ten;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        luu_tru_bcd_dong_menu_c0 ltbcd_dmnc0 = new luu_tru_bcd_dong_menu_c0();
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_menu_c0, null);
            ltbcd_dmnc0.bt = convertView.findViewById(R.id.tv_mn_c0_bieu_tuong);
            ltbcd_dmnc0.ten = convertView.findViewById(R.id.tv_mn_c0_ten);
            convertView.setTag(ltbcd_dmnc0);
        }
        else ltbcd_dmnc0 = (luu_tru_bcd_dong_menu_c0) convertView.getTag();
        ltbcd_dmnc0.bt.setText(danh_sach_lsp_c0.get(position).getBieu_tuong_loai_san_pham());
        ltbcd_dmnc0.ten.setText(danh_sach_lsp_c0.get(position).getTen_loai_san_pham());
        if (mSelectedPosition == position) {
            ltbcd_dmnc0.ten.setTextColor(Color.parseColor("#f89b64"));
            ltbcd_dmnc0.bt.setTextColor(Color.parseColor("#f89b64"));
            convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            ltbcd_dmnc0.ten.setTextColor(Color.parseColor("#9e9e9e"));
            ltbcd_dmnc0.bt.setTextColor(Color.parseColor("#9e9e9e"));
            convertView.setBackgroundColor(Color.parseColor("#EFF0F5"));
        }
        return convertView;
    }

    public void setposition(int i)
    {
        this.mSelectedPosition = i;
    }
}
