package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.chuan_hoa_duong_dan_url;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.lam_tron;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gio_hang.san_pham_gh;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gio_hang.tra_ve_giam_gia;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.topnamsao;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.mang_hinh_san_pham;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;
import com.squareup.picasso.Picasso;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;
import static com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.man_hinh_gio_hang.ds_san_pham_gh;
import static com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.man_hinh_gio_hang.tinh_tong_tien;
import static com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.man_hinh_gio_hang.xu_ly_ma_giam_gia;

public class bcd_dong_gio_hang extends RecyclerView.Adapter<bcd_dong_gio_hang.viewholder>{
    Context context;
    ArrayList<san_pham_gh> ds_sp_dh;
    ma_hoa_base64 base64;
    chuan_hoa_duong_dan_url chuan_hoa_url;
    dinh_dang_tien dinh_dang ;
    Gson gson;
    SharedPreferences Luu_tru;
    int posittion_tmp;
    public bcd_dong_gio_hang(Context context, ArrayList<san_pham_gh> ds_sp_dh) {
        this.context = context;
        this.ds_sp_dh = ds_sp_dh;
        base64 = new ma_hoa_base64();
        chuan_hoa_url = new chuan_hoa_duong_dan_url();
        dinh_dang = new dinh_dang_tien();
        gson = new Gson();
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_gio_hang, parent, false);
        return new bcd_dong_gio_hang.viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Picasso.with(context).load(chuan_hoa_url.chuan_hoa(base64.giai_ma(ds_sp_dh.get(position).getAnh_san_pham())))
                .fit().into(holder.IV_anh_sp);
        holder.TV_ten_sp.setText(base64.giai_ma(ds_sp_dh.get(position).ten_san_pham));
        holder.TV_gia_da_giam.setText(dinh_dang.dinh_dang(ds_sp_dh.get(position).getGia_da_giam()+""));
        float pt = 1 - (float) (ds_sp_dh.get(position).getGia_da_giam()*1.0/ds_sp_dh.get(position).getGia_san_pham());

        holder.TV_gia_ban_ra.setText(dinh_dang.dinh_dang(ds_sp_dh.get(position).getGia_san_pham()+""));
        holder.TV_gia_ban_ra.setPaintFlags(holder.TV_gia_ban_ra.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.TV_pt.setText(" -" + new lam_tron().tron(pt*100, "#.##") + "%");
        holder.TV_sl.setText(ds_sp_dh.get(position).getSo_luong()+"");
        if(ds_sp_dh.get(position).getMa_giam_gia()==null){
            holder.TV_nhap_ma_gg.setTextColor(Color.parseColor("#9e9e9e"));
        }
        else
        {
            holder.TV_nhap_ma_gg.setTextColor(Color.parseColor("#f57224"));
        }
    }

    @Override
    public int getItemCount() {
        return ds_sp_dh.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView IV_anh_sp;
        TextView TV_ten_sp, TV_gia_da_giam,
                TV_xoa_ma_gg, TV_nhap_ma_gg, TV_gia_ban_ra, TV_pt,
                xoa_san_pham, TV_tru_sl, TV_sl, TV_tang_sl;
        public viewholder(View itemView) {
            super(itemView);
            IV_anh_sp = (ImageView) itemView.findViewById(R.id.IV_anh_sp) ;

            TV_ten_sp = (TextView) itemView.findViewById(R.id.TV_ten_sp);
            TV_gia_da_giam = (TextView) itemView.findViewById(R.id.TV_gia_da_giam);
            TV_xoa_ma_gg = (TextView) itemView.findViewById(R.id.TV_xoa_ma_gg);

            TV_nhap_ma_gg = (TextView) itemView.findViewById(R.id.TV_nhap_ma_gg);
            TV_gia_ban_ra = (TextView) itemView.findViewById(R.id.TV_gia_ban_ra);
            TV_pt = (TextView) itemView.findViewById(R.id.TV_pt);
            xoa_san_pham = (TextView) itemView.findViewById(R.id.xoa_san_pham);

            TV_tru_sl = (TextView) itemView.findViewById(R.id.TV_tru_sl);
            TV_sl = (TextView) itemView.findViewById(R.id.TV_sl);
            TV_tang_sl = (TextView) itemView.findViewById(R.id.TV_tang_sl);

            xoa_san_pham.setOnClickListener(this);
            TV_tru_sl.setOnClickListener(this);
            TV_tang_sl.setOnClickListener(this);

            TV_nhap_ma_gg.setOnClickListener(this);
            TV_xoa_ma_gg.setOnClickListener(this);

            IV_anh_sp.setOnClickListener(this);
            TV_ten_sp.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int posittion = getAdapterPosition();
            int sl= ds_sp_dh.get(posittion).getSo_luong();
            switch(v.getId()){
                case R.id.TV_tru_sl:
                    if(sl>1)
                        ds_sp_dh.get(posittion).setSo_luong(sl-1);
                    break;
                case R.id.TV_tang_sl:
                    if(ds_sp_dh.get(posittion).getMa_giam_gia()!=null)
                        Toast.makeText(context,"Đã áp dụng mã giảm giá thì số lượng phải bằng 1!", Toast.LENGTH_SHORT).show();
                    else
                    if(sl<ds_sp_dh.get(posittion).getSo_luong_max())
                        ds_sp_dh.get(posittion).setSo_luong(sl+1);
                    break;
                case R.id.xoa_san_pham:
                    ds_sp_dh.remove(posittion);
                    break;
                case R.id.TV_nhap_ma_gg:
                    xu_ly_ma_giam_gia(posittion);
                    break;
                case R.id.TV_xoa_ma_gg:
                    ds_sp_dh.get(posittion).setMa_giam_gia(null);
                    ds_sp_dh.get(posittion).setGia_da_giam(ds_sp_dh.get(posittion).getGia_san_pham());
                    break;
                case R.id.IV_anh_sp:
                case R.id.TV_ten_sp:
                    Intent intent = new Intent(context, mang_hinh_san_pham.class);
                    intent.putExtra("id_san_pham", ds_sp_dh.get(posittion).getId_san_pham());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    break;
            }
            notifyDataSetChanged();
            tinh_tong_tien();
            Luu_tru =context.getSharedPreferences("ynn_shop",MODE_PRIVATE);
            SharedPreferences.Editor sua=Luu_tru.edit();
            sua.putString("gio_hang", gson.toJson(ds_sp_dh));
            sua.commit();
        }

    }
}
