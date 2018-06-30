package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.loai_san_pham_nhom_cap_2_phan_tu;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.loai_sp;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.mang_hinh_ds_san_pham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class bcd_dong_menu_cdcb extends BaseAdapter{
    Context context;
    ArrayList<loai_san_pham_nhom_cap_2_phan_tu> ds_cdcb;
    Gson gson;
    public bcd_dong_menu_cdcb(Context context, ArrayList<loai_san_pham_nhom_cap_2_phan_tu> ds_cdcb) {
        this.context = context;
        this.ds_cdcb = ds_cdcb;
        gson = new Gson();
    }

    @Override
    public int getCount() {
        return ds_cdcb.size();
    }

    @Override
    public Object getItem(int position) {
        return ds_cdcb.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class luu_tru_bcd_dong_menu_cdcb{
        ImageView iv_dong_menu_cdcb_anh;
        TextView tv_dong_menu_cdcb_ten;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        luu_tru_bcd_dong_menu_cdcb ltbcd_mcdcb = new luu_tru_bcd_dong_menu_cdcb();
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.dong_menu_cdcb, null);
            ltbcd_mcdcb.iv_dong_menu_cdcb_anh = convertView.findViewById(R.id.iv_dong_menu_cdcb_anh);
            ltbcd_mcdcb.tv_dong_menu_cdcb_ten = convertView.findViewById(R.id.tv_dong_menu_cdcb_ten);
            convertView.setTag(ltbcd_mcdcb);
        } else ltbcd_mcdcb = (luu_tru_bcd_dong_menu_cdcb) convertView.getTag();
        Log.d("dataonline","anh" + ds_cdcb.get(position).getAnh_loai_san_pham());
        Picasso.with(context).load(ds_cdcb.get(position).getAnh_loai_san_pham())
                .fit()
                .into(ltbcd_mcdcb.iv_dong_menu_cdcb_anh);
        ltbcd_mcdcb.tv_dong_menu_cdcb_ten.setText(ds_cdcb.get(position).getTen_loai_san_pham());
        ltbcd_mcdcb.tv_dong_menu_cdcb_ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), mang_hinh_ds_san_pham.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                ArrayList<loai_sp> ds_lsp = new ArrayList<loai_sp>();
                ds_lsp.add(new loai_sp(
                        ds_cdcb.get(position).getId_loai_san_pham(),
                        ds_cdcb.get(position).getTen_loai_san_pham()
                ));
                yeu_cau.getBo_loc().setDs_loai_sp(ds_lsp);
                intent.putExtra("yeu_cau", gson.toJson(yeu_cau));
                context.getApplicationContext().startActivity(intent);
            }
        });
        ltbcd_mcdcb.iv_dong_menu_cdcb_anh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), mang_hinh_ds_san_pham.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                ArrayList<loai_sp> ds_lsp = new ArrayList<loai_sp>();
                ds_lsp.add(new loai_sp(
                        ds_cdcb.get(position).getId_loai_san_pham(),
                        ds_cdcb.get(position).getTen_loai_san_pham()
                ));
                yeu_cau.getBo_loc().setDs_loai_sp(ds_lsp);
                intent.putExtra("yeu_cau", gson.toJson(yeu_cau));
                context.getApplicationContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
