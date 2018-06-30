package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.chuan_hoa_duong_dan_url;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thuong_hieu;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.loai_sp;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.mang_hinh_ds_san_pham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class bcd_dong_menu_c1_dong_thuong_hieu extends BaseAdapter{
    Context context;
    ArrayList<thuong_hieu> danh_sach_thuong_hieu;
Gson gson;
    public bcd_dong_menu_c1_dong_thuong_hieu(Context context, ArrayList<thuong_hieu> danh_sach_thuong_hieu) {
        this.context = context;
        this.danh_sach_thuong_hieu = danh_sach_thuong_hieu;
        gson = new Gson();
    }

    @Override
    public int getCount() {
        return danh_sach_thuong_hieu.size();
    }

    @Override
    public Object getItem(int position) {
        return danh_sach_thuong_hieu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class luu_tru_bcd_dong_menu_c1_thanh_anh_dai_dien_va_thuong_hieu_dong_thuong_hieu{
        ImageView iv_dong_menu_c1_dong_thuong_hieu;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        luu_tru_bcd_dong_menu_c1_thanh_anh_dai_dien_va_thuong_hieu_dong_thuong_hieu lt_bcd_dmn_c1_d_thuong_hieu = new luu_tru_bcd_dong_menu_c1_thanh_anh_dai_dien_va_thuong_hieu_dong_thuong_hieu();
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_menu_c1_thanh_anh_dai_dien_thuong_hieu_dong_thuong_hieu, null);
            lt_bcd_dmn_c1_d_thuong_hieu.iv_dong_menu_c1_dong_thuong_hieu = (com.makeramen.roundedimageview.RoundedImageView)
                    convertView.findViewById(R.id.iv_dong_menu_c1_dong_thuong_hieu);
            convertView.setTag(lt_bcd_dmn_c1_d_thuong_hieu);
        }
        else lt_bcd_dmn_c1_d_thuong_hieu = (luu_tru_bcd_dong_menu_c1_thanh_anh_dai_dien_va_thuong_hieu_dong_thuong_hieu) convertView.getTag();
        chuan_hoa_duong_dan_url chuan_hoa_duong_dan_url = new chuan_hoa_duong_dan_url();
        Picasso.with(context).load(chuan_hoa_duong_dan_url.chuan_hoa(danh_sach_thuong_hieu.get(position).getAnh_thuong_hieu()))
                .fit().into(lt_bcd_dmn_c1_d_thuong_hieu.iv_dong_menu_c1_dong_thuong_hieu);
        lt_bcd_dmn_c1_d_thuong_hieu.iv_dong_menu_c1_dong_thuong_hieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), mang_hinh_ds_san_pham.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                ArrayList<thuong_hieu> ds_th = new ArrayList<thuong_hieu>();
                ds_th.add(new thuong_hieu(
                        danh_sach_thuong_hieu.get(position).getId_thuong_hieu(),
                        danh_sach_thuong_hieu.get(position).getTen_thuong_hieu(),
                        danh_sach_thuong_hieu.get(position).getAnh_thuong_hieu())
                );
                yeu_cau.getBo_loc().setDs_thuong_hieu(ds_th);
                intent.putExtra("yeu_cau",gson.toJson(yeu_cau));
                context.getApplicationContext().startActivity(intent);
            }
        });
        Log.d("get_anh"+position, danh_sach_thuong_hieu.get(position).anh_thuong_hieu);
        return convertView;
    }
}
