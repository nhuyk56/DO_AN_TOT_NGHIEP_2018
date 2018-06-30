package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.lay_thoi_gian;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham.danh_gia;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class bcd_dong_mhsp_danh_gia extends RecyclerView.Adapter<bcd_dong_mhsp_danh_gia.viewholder>{
    Context context;
    ArrayList <danh_gia> danh_sach_danh_gia;
    lay_thoi_gian layThoiGian = new lay_thoi_gian();

    public ArrayList<danh_gia> getDanh_sach_danh_gia() {
        return danh_sach_danh_gia;
    }

    public void setDanh_sach_danh_gia(ArrayList<danh_gia> danh_sach_danh_gia) {
        this.danh_sach_danh_gia.clear();
        this.danh_sach_danh_gia = danh_sach_danh_gia;
        notifyDataSetChanged();
    }

    private ma_hoa_base64 ma_hoa= new ma_hoa_base64();
    public bcd_dong_mhsp_danh_gia(Context context, ArrayList<danh_gia> danh_sach_danh_gia) {
        this.context = context;
        this.danh_sach_danh_gia = danh_sach_danh_gia;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_mang_hinh_san_pham_danh_gia,parent, false);
        return new viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
         holder.TV_dong_mh_sp_dg_Tieu_de.setText(ma_hoa.giai_ma(danh_sach_danh_gia.get(position).getTieu_de_danh_gia())+"");
         holder.TV_dong_mh_sp_dg_ten_nguoi_va_thoi_gian.setText(WordUtils.capitalizeFully(danh_sach_danh_gia.get(position).getTen_nguoi()) + ". " +layThoiGian.lay(danh_sach_danh_gia.get(position).getNgay_viet_danh_gia()));
         holder.TV_dong_mh_sp_dg_Noi_dung.setText(ma_hoa.giai_ma(danh_sach_danh_gia.get(position).getNoi_dung())+"");
         holder.MRB_dong_mh_sp_dg_so_sao_danh_gia.setRating(danh_sach_danh_gia.get(position).getSo_sao_danh_gia());
        if(!danh_sach_danh_gia.get(position).isDa_mua())
        {
            holder.cn_dmh.setVisibility(View.INVISIBLE);
            holder.d_chung_nhan_da_mua_hang.setVisibility(View.INVISIBLE);
//            ((ViewManager)holder.cn_dmh.getParent()).removeView(holder.cn_dmh);
            //((ViewManager)holder.d_chung_nhan_da_mua_hang.getParent()).removeView(holder.d_chung_nhan_da_mua_hang);
        }
        else {
            holder.cn_dmh.setVisibility(View.VISIBLE);
            holder.d_chung_nhan_da_mua_hang.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return danh_sach_danh_gia.size();
    }

    public class  viewholder extends RecyclerView.ViewHolder{
        TextView TV_dong_mh_sp_dg_Tieu_de, TV_dong_mh_sp_dg_ten_nguoi_va_thoi_gian,
        cn_dmh,TV_dong_mh_sp_dg_Noi_dung;
        ImageView d_chung_nhan_da_mua_hang;
        MaterialRatingBar MRB_dong_mh_sp_dg_so_sao_danh_gia;
        public viewholder(View itemView) {
            super(itemView);
            TV_dong_mh_sp_dg_Tieu_de = (TextView) itemView.findViewById(R.id.TV_dong_mh_sp_dg_Tieu_de) ;
            TV_dong_mh_sp_dg_ten_nguoi_va_thoi_gian = (TextView) itemView.findViewById(R.id.TV_dong_mh_sp_dg_ten_nguoi_va_thoi_gian) ;
            cn_dmh = (TextView) itemView.findViewById(R.id.cn_dmh) ;
            TV_dong_mh_sp_dg_Noi_dung = (TextView) itemView.findViewById(R.id.TV_dong_mh_sp_dg_Noi_dung);
            d_chung_nhan_da_mua_hang = (ImageView) itemView.findViewById(R.id.d_chung_nhan_da_mua_hang) ;
            MRB_dong_mh_sp_dg_so_sao_danh_gia = (MaterialRatingBar) itemView.findViewById(R.id.MRB_dong_mh_sp_dg_so_sao_danh_gia) ;
        }
    }
}
