package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.ntu.ynn_shop.ynn_shop.Interface.CustomItemClickListener;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.chuan_hoa_duong_dan_url;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thuong_hieu_tot_nhat;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_giam_gia.sp_ap_dung;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class bcd_mh_trang_chu_thuong_hieu_tot_nhat extends  RecyclerView.Adapter<bcd_mh_trang_chu_thuong_hieu_tot_nhat.ViewHolder>{
    Context context;
    ArrayList<thuong_hieu_tot_nhat> ds_thtn;
    ma_hoa_base64 base64;
    chuan_hoa_duong_dan_url chuan_hoa_url;
    dinh_dang_tien dinh_dang ;
    CustomItemClickListener listener;
    public bcd_mh_trang_chu_thuong_hieu_tot_nhat(Context context, ArrayList<thuong_hieu_tot_nhat> ds_thtn, CustomItemClickListener listener) {
        this.context = context;
        this.ds_thtn = ds_thtn;
        this.listener = listener;
        base64 = new ma_hoa_base64();
        chuan_hoa_url = new chuan_hoa_duong_dan_url();
        dinh_dang = new dinh_dang_tien();
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_mh_trang_chu_thuong_hieu_tot_nnhat, parent, false);
        return new bcd_mh_trang_chu_thuong_hieu_tot_nhat.ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("aaaaaaaaaaaaaaaaa", new Gson().toJson(ds_thtn.get(position)));
        Log.d("aaaaaaaaaaaaaaaaa",chuan_hoa_url.chuan_hoa(base64.giai_ma(ds_thtn.get(position).getAnh_thuong_hieu())));
        Picasso.with(context).load(chuan_hoa_url.chuan_hoa(base64.giai_ma(ds_thtn.get(position).getAnh_thuong_hieu())))
                .fit().into(holder.IV_thtn_anh);
        holder.TV_thtn_ten_th.setText(base64.giai_ma(ds_thtn.get(position).getTen_thuong_hieu()));
        holder.TV_thtn_sl_sp.setText(ds_thtn.get(position).getSl()+" sản phẩm");
    }

    @Override
    public int getItemCount() {
        return ds_thtn.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RoundedImageView IV_thtn_anh;
        TextView TV_thtn_ten_th, TV_thtn_sl_sp;
        public ViewHolder(View itemView) {
            super(itemView);
            IV_thtn_anh = (RoundedImageView) itemView.findViewById(R.id.IV_thtn_anh);
            TV_thtn_ten_th = (TextView) itemView.findViewById(R.id.TV_thtn_ten_th);
            TV_thtn_sl_sp = (TextView) itemView.findViewById(R.id.TV_thtn_sl_sp);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}
