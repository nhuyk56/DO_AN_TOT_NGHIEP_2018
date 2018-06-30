package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.sap_xep;

import java.util.ArrayList;
import java.util.Collections;

import io.ghyeok.stickyswitch.widget.StickySwitch;

public class bcd_dong_popup_sap_xep_mhdssp extends RecyclerView.Adapter<bcd_dong_popup_sap_xep_mhdssp.ViewHolder>{
    Context context;
    private ArrayList<sap_xep>  danh_sach_sap_xep;

    public bcd_dong_popup_sap_xep_mhdssp(Context context, ArrayList<sap_xep> danh_sach_sap_xep) {
        this.context = context;
        this.danh_sach_sap_xep = danh_sach_sap_xep;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_popup_sap_xep_mhdssp, parent, false);
        return new ViewHolder(itemview);
    }

    public ArrayList<sap_xep> getDanh_sach_sap_xep() {
        return danh_sach_sap_xep;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return danh_sach_sap_xep.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements StickySwitch.OnSelectedChangeListener {
        TextView tv_dong_popup_sp_mhssp_ten_sx;
        StickySwitch ss_dong_popup_sp_mhssp_loai_sx;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_dong_popup_sp_mhssp_ten_sx = (TextView)  itemView.findViewById(R.id.tv_dong_popup_sp_mhssp_ten_sx);
            ss_dong_popup_sp_mhssp_loai_sx = (StickySwitch) itemView.findViewById(R.id.ss_dong_popup_sp_mhssp_loai_sx);
            ss_dong_popup_sp_mhssp_loai_sx.setOnSelectedChangeListener(this);
        }
        void bind(int position) {
            tv_dong_popup_sp_mhssp_ten_sx.setText(danh_sach_sap_xep.get(position).getTen_sap_xep());
            if(danh_sach_sap_xep.get(position).getLoai_sap_xep()==false)
                ss_dong_popup_sp_mhssp_loai_sx.setDirection(StickySwitch.Direction.LEFT);
            else   ss_dong_popup_sp_mhssp_loai_sx.setDirection(StickySwitch.Direction.RIGHT);
        }

        @Override
        public void onSelectedChange(StickySwitch.Direction direction, String s) {
            int position = getAdapterPosition();
            if(direction==StickySwitch.Direction.LEFT)
                danh_sach_sap_xep.get(position).setLoai_sap_xep(false);
            else   danh_sach_sap_xep.get(position).setLoai_sap_xep(true);

        }
    }

    public void onMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(danh_sach_sap_xep, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(danh_sach_sap_xep, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }
    public void swipe(int position, int direction) {
        danh_sach_sap_xep.remove(position);
        notifyItemRemoved(position);
    }
}
