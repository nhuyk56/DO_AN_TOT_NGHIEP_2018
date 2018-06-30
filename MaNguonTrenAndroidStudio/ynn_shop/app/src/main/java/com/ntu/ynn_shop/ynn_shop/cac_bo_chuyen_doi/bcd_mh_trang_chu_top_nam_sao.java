package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.graphics.Paint;
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
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.sp_ds.san_pham_tds;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.topnamsao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class bcd_mh_trang_chu_top_nam_sao  extends  RecyclerView.Adapter<bcd_mh_trang_chu_top_nam_sao.viewholder>{
    Context context;
    ArrayList<topnamsao> ds_topnamsao;
    ma_hoa_base64 base64;
    chuan_hoa_duong_dan_url chuan_hoa_url;
    dinh_dang_tien dinh_dang ;
    CustomItemClickListener listener;
    public bcd_mh_trang_chu_top_nam_sao(Context context, ArrayList<topnamsao> ds_topnamsao, CustomItemClickListener listener) {
        this.context = context;
        this.ds_topnamsao = ds_topnamsao;
        this.listener = listener;
        base64 = new ma_hoa_base64();
        chuan_hoa_url = new chuan_hoa_duong_dan_url();
        dinh_dang = new dinh_dang_tien();
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_dssp_recyclerview, parent, false);
        return new bcd_mh_trang_chu_top_nam_sao.viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Log.d("xxxxxxxxxxxxxxxxxxx", chuan_hoa_url.chuan_hoa(base64.giai_ma(ds_topnamsao.get(position).getAnh_san_pham())));
        Picasso.with(context).load(chuan_hoa_url.chuan_hoa(base64.giai_ma(ds_topnamsao.get(position).getAnh_san_pham())))//+"_233x233q50.jpg")
                .fit()
                .into(holder.iv_dong_dssp_rv_anh_sp);
        holder.tv_dong_dssp_rv_ten_sp.setText(base64.giai_ma(ds_topnamsao.get(position).getTen_san_pham()));

        holder.tv_dong_dssp_rv_gia_ban_ra.setText(dinh_dang.dinh_dang(ds_topnamsao.get(position).getGia_ban_ra() + ""));
        holder.tv_dong_dssp_rv_gia_cao.setText(dinh_dang.dinh_dang(ds_topnamsao.get(position).getGia_cao()+""));
        holder.tv_dong_dssp_rv_gia_cao.setPaintFlags(holder.tv_dong_dssp_rv_gia_cao.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        //(gia cao - gia thap) * 100 / gia cao
        Log.d("xxxxxxxxxxxxxxxxxxx", "cao="+ds_topnamsao.get(position).getGia_cao() + "");
        Log.d("xxxxxxxxxxxxxxxxxxx", "ra="+ds_topnamsao.get(position).getGia_ban_ra() + "");
        holder.tv_dong_dssp_rv_phan_tram.setText("-" + Math.round(((ds_topnamsao.get(position).getGia_cao() - ds_topnamsao.get(position).getGia_ban_ra())*100*1.0/ds_topnamsao.get(position).getGia_cao())) + "%");
        holder.tv_dong_dssp_rv_so_tim.setText(ds_topnamsao.get(position).so_tim + "");
        holder.RB_dong_dssp_rv_sao.setRating(5);
        holder.tv_dong_dssp_rv_so_sao.setText(5.0+"");
    }

    @Override
    public int getItemCount() {
        return ds_topnamsao.size();
    }

    public  class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}

