package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.van_chuyen;

import java.util.ArrayList;

public class bcd_dong_phuong_thuc_van_chuyen extends BaseAdapter {
    Context context;
    ArrayList<van_chuyen> ds_van_chuyen;
    int mSelectedPosition = -1;
    public bcd_dong_phuong_thuc_van_chuyen(Context context, ArrayList<van_chuyen> ds_van_chuyen) {
        this.context = context;
        this.ds_van_chuyen = ds_van_chuyen;
    }

    @Override
    public int getCount() {
        return ds_van_chuyen.size();
    }

    @Override
    public Object getItem(int position) {
        return ds_van_chuyen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.dong_phuong_thuc_van_chuyen, null);
        TextView tv_ten_ptvc, tv_thoi_gian_giao;
        tv_ten_ptvc = (TextView) convertView.findViewById(R.id.tv_ten_ptvc);
        tv_thoi_gian_giao = (TextView) convertView.findViewById(R.id.tv_thoi_gian_giao);
        tv_ten_ptvc.setText(ds_van_chuyen.get(position).getCarrierName() + " "+ds_van_chuyen.get(position).getServiceTypeName());
        tv_thoi_gian_giao.setText(ds_van_chuyen.get(position).getServiceDescription());
        if (mSelectedPosition == position) {
            tv_ten_ptvc.setTextColor(Color.parseColor("#f57224"));
            tv_thoi_gian_giao.setTextColor(Color.parseColor("#f57224"));
            convertView.setBackgroundResource(R.drawable.vien_chon);
        } else {
            tv_ten_ptvc.setTextColor(Color.parseColor("#9e9e9e"));
            tv_thoi_gian_giao.setTextColor(Color.parseColor("#9e9e9e"));
            convertView.setBackgroundResource(R.drawable.vien_khong_chon);
        }
        return convertView;
}

    public void setposition(int i)
    {
        this.mSelectedPosition = i;
    }
}
