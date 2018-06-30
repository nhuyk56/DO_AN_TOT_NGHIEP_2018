package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ntu.ynn_shop.ynn_shop.Interface.CustomItemClickListener;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.chuan_hoa_duong_dan_url;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.sp_ds.san_pham_tds;
import java.util.ArrayList;


public class bcd_dong_dsctgg extends RecyclerView.Adapter<bcd_dong_dsctgg.viewholder>{
    Context context;
    ArrayList<String> ds_ctgg;
    ma_hoa_base64 base64;
    chuan_hoa_duong_dan_url chuan_hoa_url;
    dinh_dang_tien dinh_dang ;
    int row_index=0;
    CustomItemClickListener listener;
    public bcd_dong_dsctgg(Context context, ArrayList<String> ds_ctgg, CustomItemClickListener listener) {
        this.context = context;
        this.ds_ctgg = ds_ctgg;
        this.listener = listener;
        base64 = new ma_hoa_base64();
        chuan_hoa_url = new chuan_hoa_duong_dan_url();
        dinh_dang = new dinh_dang_tien();
    }

    public int getRow_index() {
        return row_index;
    }

    public void setRow_index(int row_index) {
        this.row_index = row_index;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_mh_gg_ds_ctgg, parent, false);
        return new bcd_dong_dsctgg.viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {
        holder.TV_tieu_de_ctgg.setText(ds_ctgg.get(position));
        if(row_index==position){
            holder.TV_tieu_de_ctgg.setTextColor(Color.parseColor("#f57224"));
            holder.LL_line_ctgg.setBackgroundColor(Color.parseColor("#f57224"));
        }
        else
        {
            holder.TV_tieu_de_ctgg.setTextColor(Color.parseColor("#9e9e9e"));
            holder.LL_line_ctgg.setBackgroundColor(Color.parseColor("#faf6ef"));
        }
    }

    @Override
    public int getItemCount() {
        return ds_ctgg.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView TV_tieu_de_ctgg;
        LinearLayout LL_line_ctgg;
        public viewholder(View itemView) {
            super(itemView);
            LL_line_ctgg = (LinearLayout) itemView.findViewById(R.id.LL_line_ctgg);
            TV_tieu_de_ctgg = (TextView) itemView.findViewById(R.id.TV_tieu_de_ctgg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            row_index=getAdapterPosition();
            listener.onItemClick(v, row_index);
            notifyDataSetChanged();
        }
    }
}
