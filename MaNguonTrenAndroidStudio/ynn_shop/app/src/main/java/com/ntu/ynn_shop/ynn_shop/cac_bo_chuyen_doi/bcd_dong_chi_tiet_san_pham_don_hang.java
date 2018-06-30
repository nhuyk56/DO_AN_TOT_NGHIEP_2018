package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.Interface.CustomItemClickListener;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.chuan_hoa_duong_dan_url;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.lam_tron;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.chi_tiet_san_pham_don_hang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class bcd_dong_chi_tiet_san_pham_don_hang extends  RecyclerView.Adapter<bcd_dong_chi_tiet_san_pham_don_hang.viewholder>{
    Context context;
    ArrayList<chi_tiet_san_pham_don_hang> ds_san_pham_chi_tiet;
    CustomItemClickListener listener;
    ma_hoa_base64 base64;
    chuan_hoa_duong_dan_url chuan_hoa_url;
    dinh_dang_tien dinh_dang ;
    Gson gson;
    public bcd_dong_chi_tiet_san_pham_don_hang(Context context, ArrayList<chi_tiet_san_pham_don_hang> ds_san_pham_chi_tiet, CustomItemClickListener listener) {
        this.context = context;
        this.ds_san_pham_chi_tiet = ds_san_pham_chi_tiet;
        this.listener = listener;
        base64 = new ma_hoa_base64();
        chuan_hoa_url = new chuan_hoa_duong_dan_url();
        dinh_dang = new dinh_dang_tien();
        gson = new Gson();
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_chi_tiet_san_pham_don_hang, parent, false);
        return new bcd_dong_chi_tiet_san_pham_don_hang.viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Picasso.with(context).load(chuan_hoa_url.chuan_hoa(base64.giai_ma(ds_san_pham_chi_tiet.get(position).getAnh_san_pham())))
                .fit().into(holder.IV_anh_sp);
        holder.TV_ten_sp.setText(base64.giai_ma(ds_san_pham_chi_tiet.get(position).ten_san_pham));
        holder.TV_gia_da_giam.setText(dinh_dang.dinh_dang(ds_san_pham_chi_tiet.get(position).getGia_da_giam()+""));
        float pt = 1 - (float) (ds_san_pham_chi_tiet.get(position).getGia_da_giam()*1.0/ds_san_pham_chi_tiet.get(position).getGia_san_pham());

        holder.TV_gia_ban_ra.setText(dinh_dang.dinh_dang(ds_san_pham_chi_tiet.get(position).getGia_san_pham()+""));
        holder.TV_gia_ban_ra.setPaintFlags(holder.TV_gia_ban_ra.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.TV_pt.setText(" -" + new lam_tron().tron(pt*100, "#.##") + "%");
        holder.TV_sl.setText(ds_san_pham_chi_tiet.get(position).getSo_luong()+"");
        if(ds_san_pham_chi_tiet.get(position).getMa_giam_gia()==null){
            holder.TV_nhap_ma_gg.setTextColor(Color.parseColor("#9e9e9e"));
        }
        else
        {
            holder.TV_nhap_ma_gg.setTextColor(Color.parseColor("#f57224"));
        }
    }

    @Override
    public int getItemCount() {
        return ds_san_pham_chi_tiet.size();
    }

    public  class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView IV_anh_sp;
        TextView TV_ten_sp, TV_gia_da_giam, TV_gia_ban_ra, TV_pt, TV_sl, TV_nhap_ma_gg;
        public viewholder(View itemView) {
            super(itemView);
            IV_anh_sp = (ImageView) itemView.findViewById(R.id.IV_anh_sp);
            TV_ten_sp = (TextView) itemView.findViewById(R.id.TV_ten_sp);
            TV_gia_da_giam = (TextView) itemView.findViewById(R.id.TV_gia_da_giam);
            TV_gia_ban_ra = (TextView) itemView.findViewById(R.id.TV_gia_ban_ra);
            TV_pt = (TextView) itemView.findViewById(R.id.TV_pt);
            TV_sl = (TextView) itemView.findViewById(R.id.TV_sl);
            TV_nhap_ma_gg = (TextView)itemView.findViewById(R.id.TV_nhap_ma_gg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}
