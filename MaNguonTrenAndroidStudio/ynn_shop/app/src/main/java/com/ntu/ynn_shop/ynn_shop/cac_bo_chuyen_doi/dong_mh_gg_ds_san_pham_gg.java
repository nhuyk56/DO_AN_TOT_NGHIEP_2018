package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.chuan_hoa_duong_dan_url;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.sp_ds.san_pham_tds;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_giam_gia.sp_ap_dung;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.mang_hinh_san_pham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class dong_mh_gg_ds_san_pham_gg extends RecyclerView.Adapter<dong_mh_gg_ds_san_pham_gg.viewholder>{
    Context context;
    ArrayList<sp_ap_dung> ds_ctgg;
    ma_hoa_base64 base64;
    chuan_hoa_duong_dan_url chuan_hoa_url;
    dinh_dang_tien dinh_dang ;

    public dong_mh_gg_ds_san_pham_gg(Context context, ArrayList<sp_ap_dung> ds_ctgg) {
        this.context = context;
        this.ds_ctgg = ds_ctgg;
        base64 = new ma_hoa_base64();
        chuan_hoa_url = new chuan_hoa_duong_dan_url();
        dinh_dang = new dinh_dang_tien();
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_mh_gg_ds_san_pham_gg, parent, false);
        return new dong_mh_gg_ds_san_pham_gg.viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Picasso.with(context).load(chuan_hoa_url.chuan_hoa(base64.giai_ma(ds_ctgg.get(position).getAnh_san_pham())))
                .fit().into(holder.IV_anh_sp_gg);
        holder.TV_tensp_gg.setText(base64.giai_ma(ds_ctgg.get(position).getTen_san_pham()));
        long gia_ban_ra = ds_ctgg.get(position).getGia_ban_ra();
        float pt_con_lai = (100 - ds_ctgg.get(position).getPhan_tram_giam())/100;
        long gia_da_giam = (long) (gia_ban_ra*pt_con_lai );
        long giam_toi_da = ds_ctgg.get(position).getGiam_toi_da();
        if(gia_ban_ra - gia_da_giam >  giam_toi_da)
            gia_da_giam = gia_ban_ra - giam_toi_da;
        holder.TV_gia_dg_sp_gg.setText(dinh_dang.dinh_dang(gia_da_giam+""));
        int ptgg= (int) ((gia_ban_ra - gia_da_giam)*1.0/gia_ban_ra*100);
        holder.TV_pt_sp_gg.setText("Giảm thêm"+ ptgg+"%");
        holder.TV_gia_gc_sp_gg.setText(dinh_dang.dinh_dang(gia_ban_ra+""));
        holder.TV_gia_gc_sp_gg.setPaintFlags(holder.TV_gia_gc_sp_gg.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

    }

    @Override
    public int getItemCount() {
        return ds_ctgg.size();
    }


    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
       ImageView IV_anh_sp_gg;
       TextView TV_tensp_gg, TV_gia_gc_sp_gg, TV_pt_sp_gg, TV_gia_dg_sp_gg;
        public viewholder(View itemView) {
            super(itemView);
            IV_anh_sp_gg = (ImageView) itemView.findViewById(R.id.IV_anh_sp_gg);
            TV_tensp_gg = (TextView) itemView.findViewById(R.id.TV_tensp_gg);
            TV_gia_gc_sp_gg = (TextView) itemView.findViewById(R.id.TV_gia_gc_sp_gg);
            TV_pt_sp_gg = (TextView) itemView.findViewById(R.id.TV_pt_sp_gg);
            TV_gia_dg_sp_gg = (TextView) itemView.findViewById(R.id.TV_gia_dg_sp_gg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context.getApplicationContext(), mang_hinh_san_pham.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("id_san_pham", ds_ctgg.get(getAdapterPosition()).getId_san_pham());
            context.getApplicationContext().startActivity(intent);
        }
    }
}
