package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.chuan_hoa_duong_dan_url;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.sp_ds.san_pham_tds;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.mang_hinh_san_pham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class bcd_dong_dssp_recyclerview extends RecyclerView.Adapter<bcd_dong_dssp_recyclerview.viewholder>{
    Context context;
    ArrayList<san_pham_tds> san_pham_tds;
    ma_hoa_base64  base64;
    chuan_hoa_duong_dan_url chuan_hoa_url;
    dinh_dang_tien dinh_dang ;
    public bcd_dong_dssp_recyclerview(Context context, ArrayList<com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.sp_ds.san_pham_tds> san_pham_tds) {
        base64 = new ma_hoa_base64();
        chuan_hoa_url = new chuan_hoa_duong_dan_url();
        this.context = context;
        this.san_pham_tds = san_pham_tds;
        dinh_dang = new dinh_dang_tien();
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_dssp_recyclerview, parent, false);
        return new viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {
        Picasso.with(context).load(chuan_hoa_url.chuan_hoa(base64.giai_ma(san_pham_tds.get(position).getAnh_san_pham())))
                .fit().into(holder.iv_dong_dssp_rv_anh_sp);
        holder.tv_dong_dssp_rv_ten_sp.setText(base64.giai_ma(san_pham_tds.get(position).getTen_san_pham()));

        holder.tv_dong_dssp_rv_gia_ban_ra.setText(dinh_dang.dinh_dang(san_pham_tds.get(position).getGia_ban_ra() + ""));
        holder.tv_dong_dssp_rv_gia_cao.setText(dinh_dang.dinh_dang(san_pham_tds.get(position).getGia_cao()+""));
        holder.tv_dong_dssp_rv_gia_cao.setPaintFlags(holder.tv_dong_dssp_rv_gia_cao.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        //(gia cao - gia thap) * 100 / gia cao
        Log.d("xxxxxxxxxxxxxxxxxxx", "cao="+san_pham_tds.get(position).getGia_cao() + "");
        Log.d("xxxxxxxxxxxxxxxxxxx", "ra="+san_pham_tds.get(position).getGia_ban_ra() + "");
        holder.tv_dong_dssp_rv_phan_tram.setText("-" + Math.round(((san_pham_tds.get(position).getGia_cao() - san_pham_tds.get(position).getGia_ban_ra())*100*1.0/san_pham_tds.get(position).getGia_cao())) + "%");
        holder.tv_dong_dssp_rv_so_tim.setText(san_pham_tds.get(position).so_tim + "");
        holder.RB_dong_dssp_rv_sao.setRating(san_pham_tds.get(position).getSo_sao_tb());
        holder.tv_dong_dssp_rv_so_sao.setText(san_pham_tds.get(position).getSo_sao_tb() + "");
    }

    @Override
    public int getItemCount() {
        return san_pham_tds.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_dong_dssp_rv_anh_sp;
        TextView tv_dong_dssp_rv_ten_sp,
                tv_dong_dssp_rv_gia_ban_ra,
                tv_dong_dssp_rv_gia_cao,
                tv_dong_dssp_rv_phan_tram,
                tv_dong_dssp_rv_so_tim,
                tv_dong_dssp_rv_so_sao;
        MaterialRatingBar RB_dong_dssp_rv_sao;
        public viewholder(View itemView) {
            super(itemView);
            iv_dong_dssp_rv_anh_sp = (ImageView) itemView.findViewById(R.id.iv_dong_dssp_rv_anh_sp);
            tv_dong_dssp_rv_ten_sp = (TextView) itemView.findViewById(R.id.tv_dong_dssp_rv_ten_sp);
            tv_dong_dssp_rv_gia_ban_ra = (TextView) itemView.findViewById(R.id.tv_dong_dssp_rv_gia_ban_ra);
            tv_dong_dssp_rv_gia_cao = (TextView) itemView.findViewById(R.id.tv_dong_dssp_rv_gia_cao);
            tv_dong_dssp_rv_phan_tram = (TextView) itemView.findViewById(R.id.tv_dong_dssp_rv_phan_tram);
            tv_dong_dssp_rv_so_tim = (TextView) itemView.findViewById(R.id.tv_dong_dssp_rv_so_tim);
            RB_dong_dssp_rv_sao = (MaterialRatingBar) itemView.findViewById(R.id.RB_dong_dssp_rv_sao);
            tv_dong_dssp_rv_so_sao =  (TextView) itemView.findViewById(R.id.tv_dong_dssp_rv_so_sao);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context.getApplicationContext(), mang_hinh_san_pham.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("id_san_pham", san_pham_tds.get(getAdapterPosition()).getId_san_pham());
            context.getApplicationContext().startActivity(intent);
        }
    }
}
