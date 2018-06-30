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

import com.ntu.ynn_shop.ynn_shop.Interface.CustomItemClickListener;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.chuan_hoa_duong_dan_url;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.danh_muc_ua_thich;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class bcd_trang_chu_danh_muc_ua_thich extends RecyclerView.Adapter<bcd_trang_chu_danh_muc_ua_thich.viewholder>{
    Context context;
    ArrayList<danh_muc_ua_thich> ds_danhMucUaThiches;
    ma_hoa_base64 base64;
    chuan_hoa_duong_dan_url chuan_hoa_url;
    dinh_dang_tien dinh_dang ;
    CustomItemClickListener listener;
    public bcd_trang_chu_danh_muc_ua_thich(Context context, ArrayList<danh_muc_ua_thich> ds_danhMucUaThiches, CustomItemClickListener listener) {
        this.context = context;
        this.ds_danhMucUaThiches = ds_danhMucUaThiches;
        this.listener = listener;
        base64 = new ma_hoa_base64();
        chuan_hoa_url = new chuan_hoa_duong_dan_url();
        dinh_dang = new dinh_dang_tien();
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_trang_chu_danh_muc_ua_thich, parent, false);
        return new bcd_trang_chu_danh_muc_ua_thich.viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Log.d("aaaaaaaaa123", chuan_hoa_url.chuan_hoa(ds_danhMucUaThiches.get(position).getAnh_loai_san_pham()));
        Picasso.with(context).load(chuan_hoa_url.chuan_hoa(ds_danhMucUaThiches.get(position).getAnh_loai_san_pham()))
                .fit().into(holder.IV_anh_lsp);
        holder.TV_ten_lsp.setText(base64.giai_ma(ds_danhMucUaThiches.get(position).getTen_loai_san_pham()));
    }

    @Override
    public int getItemCount() {
        return ds_danhMucUaThiches.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView IV_anh_lsp;
        TextView TV_ten_lsp;
        public viewholder(View itemView) {
            super(itemView);
            IV_anh_lsp = (ImageView) itemView.findViewById(R.id.IV_anh_lsp);
            TV_ten_lsp = (TextView) itemView.findViewById(R.id.TV_ten_lsp);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}
