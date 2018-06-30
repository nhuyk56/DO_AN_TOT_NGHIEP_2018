package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.chuan_hoa_duong_dan_url;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_giam_gia.sp_ap_dung;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class bcd_dong_mh_trang_chu_ntkh extends RecyclerView.Adapter<bcd_dong_mh_trang_chu_ntkh.viewHoler>{
    Context context;
    ArrayList<sp_ap_dung> ds_ctgg;
    ma_hoa_base64 base64;
    chuan_hoa_duong_dan_url chuan_hoa_url;
    dinh_dang_tien dinh_dang ;

    public bcd_dong_mh_trang_chu_ntkh(Context context, ArrayList<sp_ap_dung> ds_ctgg) {
        this.context = context;
        this.ds_ctgg = ds_ctgg;
        base64 = new ma_hoa_base64();
        chuan_hoa_url = new chuan_hoa_duong_dan_url();
        dinh_dang = new dinh_dang_tien();
    }
    @NonNull
    @Override
    public viewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_mh_trang_chu_nhanh_tay_keo_het, parent, false);
        return new bcd_dong_mh_trang_chu_ntkh.viewHoler(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHoler holder, int position) {
        Picasso.with(context).load(chuan_hoa_url.chuan_hoa(base64.giai_ma(ds_ctgg.get(position).getAnh_san_pham())))
                .fit().into(holder.dmh_tc_ntkh_anh_sp);
        long gia_ban_ra = ds_ctgg.get(position).getGia_ban_ra();
        float pt_con_lai = (100 - ds_ctgg.get(position).getPhan_tram_giam())/100;
        long gia_da_giam = (long) (gia_ban_ra*pt_con_lai );
        long giam_toi_da = ds_ctgg.get(position).getGiam_toi_da();
        if(gia_ban_ra - gia_da_giam >  giam_toi_da)
            gia_da_giam = gia_ban_ra - giam_toi_da;
        holder.dmh_tc_ntkh_gia_da_giam.setText(dinh_dang.dinh_dang(gia_da_giam+""));
        int ptgg= (int) ((gia_ban_ra - gia_da_giam)*1.0/gia_ban_ra*100);
        holder.dmh_tc_ntkh_pt_giam.setText("-"+ ptgg+"%");
        holder.dmh_tc_ntkh_gia_ban_ra.setText(dinh_dang.dinh_dang(gia_ban_ra+""));
        holder.dmh_tc_ntkh_gia_ban_ra.setPaintFlags(holder.dmh_tc_ntkh_gia_ban_ra.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return ds_ctgg.size();
    }

    public class viewHoler extends RecyclerView.ViewHolder{
        ImageView dmh_tc_ntkh_anh_sp;
        TextView dmh_tc_ntkh_gia_da_giam, dmh_tc_ntkh_gia_ban_ra, dmh_tc_ntkh_pt_giam;
        public viewHoler(View itemView) {
            super(itemView);
            dmh_tc_ntkh_anh_sp = (ImageView) itemView.findViewById(R.id.dmh_tc_ntkh_anh_sp);
            dmh_tc_ntkh_gia_da_giam = (TextView) itemView.findViewById(R.id.dmh_tc_ntkh_gia_da_giam);
            dmh_tc_ntkh_gia_ban_ra = (TextView) itemView.findViewById(R.id.dmh_tc_ntkh_gia_ban_ra);
            dmh_tc_ntkh_pt_giam = (TextView) itemView.findViewById(R.id.dmh_tc_ntkh_pt_giam);
        }
    }
}
