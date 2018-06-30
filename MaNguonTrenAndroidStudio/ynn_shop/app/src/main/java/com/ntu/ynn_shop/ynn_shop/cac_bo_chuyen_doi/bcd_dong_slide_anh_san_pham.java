package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.chuan_hoa_duong_dan_url;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.sp_ds.san_pham_tds;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.mang_hinh_san_pham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class bcd_dong_slide_anh_san_pham extends PagerAdapter{
    ArrayList<String> danh_sach_anh;
    Context context;
    ma_hoa_base64 base64 = new ma_hoa_base64();
    chuan_hoa_duong_dan_url chuan_hoa_url = new chuan_hoa_duong_dan_url();
    public bcd_dong_slide_anh_san_pham(ArrayList<String> danh_sach_anh, Context context) {
        this.danh_sach_anh = danh_sach_anh;
        this.context = context;
    }

    @Override
    public int getCount() {

        return danh_sach_anh.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        Log.d("1231321231",base64.giai_ma(danh_sach_anh.get(position )));
        View view = LayoutInflater.from(context).inflate(R.layout.dong_slide_anh_san_pham, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_dong_slide_anh_san_pham);
        Picasso.with(context).load(chuan_hoa_url.chuan_hoa(base64.giai_ma(danh_sach_anh.get(position).toString())) + "_1200x1200q80.jpg")
                .fit().into(imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mang_hinh_san_pham.hien_thi_hinh_anh(position);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
