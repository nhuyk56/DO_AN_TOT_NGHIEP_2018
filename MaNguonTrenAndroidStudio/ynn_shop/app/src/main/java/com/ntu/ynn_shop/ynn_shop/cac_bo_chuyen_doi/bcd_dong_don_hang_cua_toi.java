package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ntu.ynn_shop.ynn_shop.Interface.CustomItemClickListener;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.man_hinh_tai_khoan;
import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.chuan_hoa_duong_dan_url;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.don_hang_cua_toi;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thong_tin_don_hang_cua_toi;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Arrays;

public class bcd_dong_don_hang_cua_toi extends  RecyclerView.Adapter<bcd_dong_don_hang_cua_toi.viewholder>{
    Context context;
    CustomItemClickListener listener;
    ArrayList<don_hang_cua_toi> ds_don_hang;
    ma_hoa_base64 base64;
    chuan_hoa_duong_dan_url chuan_hoa_url;
    dinh_dang_tien dinh_dang ;
    Dialog Chi_tiet_don_hang;
    TextView tv_ma_don_hang, tv_ngay_dat_hang,tv_tinh_trang_don_hang,
            tv_dia_chi, tv_ten_phuong_thuc_van_chuyen,
            tv_ma_van_chuyen, tv_thoi_gian_giao, tv_phi_van_chuyen,
            tv_phi_thu_ho, tv_tien_thu_ho, tv_tong_tien_thanh_toan, tv_huy_don_hang, bt_thoat ;
    RecyclerView RV_chi_tiet_don_hang;
    thong_tin_don_hang_cua_toi thongTinDonHangCuaToi;
    Gson gson;
    public bcd_dong_don_hang_cua_toi( Context context, ArrayList<don_hang_cua_toi> ds_don_hang, CustomItemClickListener listener) {
        this.context = context;
        this.ds_don_hang = ds_don_hang;
        base64 = new ma_hoa_base64();
        chuan_hoa_url = new chuan_hoa_duong_dan_url();
        dinh_dang = new dinh_dang_tien();
        gson = new Gson();
        this.listener = listener;
    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_don_hang_cua_toi, parent, false);
        return new bcd_dong_don_hang_cua_toi.viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.tv_ma_don_hang.setText("#"+ds_don_hang.get(position).getMa_don_hang());
        holder.tv_tinh_trang_don_hang.setText(ds_don_hang.get(position).getTinh_trang_don_hang()+"");
        holder.tv_tong_tien_thanh_toan.setText(dinh_dang.dinh_dang(ds_don_hang.get(position).getTong_tien_thanh_toan()+"")+"");
        holder.tv_ngay_dat_hang.setText(ds_don_hang.get(position).getNgay_dat_hang()+"");
    }

    @Override
    public int getItemCount() {
        return ds_don_hang.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_ma_don_hang, tv_tinh_trang_don_hang, tv_tong_tien_thanh_toan, tv_ngay_dat_hang;
        public viewholder(View itemView) {
            super(itemView);
            tv_ma_don_hang = (TextView) itemView.findViewById(R.id.tv_ma_don_hang);
            tv_tinh_trang_don_hang = (TextView) itemView.findViewById(R.id.tv_tinh_trang_don_hang);
            tv_tong_tien_thanh_toan = (TextView) itemView.findViewById(R.id.tv_tong_tien_thanh_toan);
            tv_ngay_dat_hang = (TextView) itemView.findViewById(R.id.tv_ngay_dat_hang);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
            //lay_chi_tiet(getAdapterPosition());
        }
    }



}
