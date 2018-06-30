package com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.loai_san_pham_cap_1;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thuong_hieu;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.loai_sp;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.mang_hinh_ds_san_pham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class bcd_dong_menu_c1 extends BaseExpandableListAdapter{
    Context context;
    ArrayList<loai_san_pham_cap_1> danh_sach_lsp_c1;
    String anh_dai_dien;
    ArrayList<thuong_hieu> danh_sach_thuong_hieu;
    Gson gson = new Gson();
    public bcd_dong_menu_c1(Context context, ArrayList<loai_san_pham_cap_1> danh_sach_lsp_c1, String anh_dai_dien, ArrayList<thuong_hieu> danh_sach_thuong_hieu) {
        this.context = context;
        this.danh_sach_lsp_c1 = danh_sach_lsp_c1;
        this.anh_dai_dien = anh_dai_dien;
        this.danh_sach_thuong_hieu = danh_sach_thuong_hieu;
    }

    public ArrayList<loai_san_pham_cap_1> getDanh_sach_lsp_c1() {
        return danh_sach_lsp_c1;
    }

    public void setDanh_sach_lsp_c1(ArrayList<loai_san_pham_cap_1> danh_sach_lsp_c1) {
        this.danh_sach_lsp_c1 = danh_sach_lsp_c1;
    }

    public String getAnh_dai_dien() {
        return anh_dai_dien;
    }

    public void setAnh_dai_dien(String anh_dai_dien) {
        this.anh_dai_dien = anh_dai_dien;
    }

    public ArrayList<thuong_hieu> getDanh_sach_thuong_hieu() {
        return danh_sach_thuong_hieu;
    }

    public void setDanh_sach_thuong_hieu(ArrayList<thuong_hieu> danh_sach_thuong_hieu) {
        this.danh_sach_thuong_hieu = danh_sach_thuong_hieu;
    }

    @Override
    public int getGroupCount() {
        return danh_sach_lsp_c1.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return danh_sach_lsp_c1.get(groupPosition).getDanh_sach_loai_san_pham_nhom_cap_2().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return danh_sach_lsp_c1.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return danh_sach_lsp_c1.get(groupPosition).getDanh_sach_loai_san_pham_nhom_cap_2().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return  danh_sach_lsp_c1.get(groupPosition).getId_loai_san_pham();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private class luu_tru_bcd_dong_menu_c1_thanh_tieu_de{
        TextView tv_dong_menu_c1_thanh_tieu_de_ten,
                tv_dong_menu_c1_thanh_tieu_de_doc,
                tv_dong_menu_c1_thanh_tieu_de_mui_ten;
    }

    private class luu_tru_bcd_dong_menu_c1_thanh_anh_dai_dien_va_thuong_hieu{
        ImageView iv_dong_menu_c1_thanh_addvth_anh;
        GridView gv_dong_menu_c1_thanh_addvth_thuong_hieu;
    }
    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {
        if(groupPosition!=0)
        {
            luu_tru_bcd_dong_menu_c1_thanh_tieu_de ltbcd_dmnc1 = new luu_tru_bcd_dong_menu_c1_thanh_tieu_de();
            //if(convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.dong_menu_c1_thanh_tieu_de, null);
                ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_ten = convertView.findViewById(R.id.tv_dong_menu_c1_thanh_tieu_de_ten);
                ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_doc = convertView.findViewById(R.id.tv_dong_menu_c1_thanh_tieu_de_doc);
                ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_mui_ten = convertView.findViewById(R.id.tv_dong_menu_c1_thanh_tieu_de_mui_ten);
                convertView.setTag(ltbcd_dmnc1);
            }
           // else ltbcd_dmnc1 = (luu_tru_bcd_dong_menu_c1_thanh_tieu_de) convertView.getTag();
            ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_ten.setText(danh_sach_lsp_c1.get(groupPosition).getTen_loai_san_pham());

            if(getChildrenCount(groupPosition) == 0)
            {
                ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_mui_ten.setVisibility(View.INVISIBLE);
                ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_doc.setVisibility(View.INVISIBLE);
            }
            else
            {
                ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_mui_ten.setVisibility(View.VISIBLE);
                ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_doc.setVisibility(View.VISIBLE);
            }

            ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_mui_ten.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("dataonline","test");
                    if(isExpanded) ((ExpandableListView) parent).collapseGroup(groupPosition);
                    else ((ExpandableListView) parent).expandGroup(groupPosition, true);
                }
            });

            ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_doc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("dataonline","test");
                    if(isExpanded) ((ExpandableListView) parent).collapseGroup(groupPosition);
                    else ((ExpandableListView) parent).expandGroup(groupPosition, true);
                }
            });
            ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_ten.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("dataonline", "ten");
                }
            });
            if(isExpanded)
                ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_mui_ten.setRotation(180);
            else
                ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_mui_ten.setRotation(0);

            ltbcd_dmnc1.tv_dong_menu_c1_thanh_tieu_de_ten.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), mang_hinh_ds_san_pham.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                    ArrayList<loai_sp> ds_lsp = new ArrayList<loai_sp>();
                    ds_lsp.add(new loai_sp(danh_sach_lsp_c1.get(groupPosition).id_loai_san_pham, danh_sach_lsp_c1.get(groupPosition).ten_loai_san_pham));
                    yeu_cau.getBo_loc().setDs_loai_sp(ds_lsp);
                    intent.putExtra("yeu_cau", gson.toJson(yeu_cau));
                    context.getApplicationContext().startActivity(intent);
                }
            });
        }
        else {
            luu_tru_bcd_dong_menu_c1_thanh_anh_dai_dien_va_thuong_hieu ltbcd_dmnc1 = new luu_tru_bcd_dong_menu_c1_thanh_anh_dai_dien_va_thuong_hieu();
            //if(convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.dong_menu_c1_thanh_anh_dai_dien_va_thuong_hieu, null);
                ltbcd_dmnc1.iv_dong_menu_c1_thanh_addvth_anh = (com.makeramen.roundedimageview.RoundedImageView) convertView.findViewById(R.id.iv_dong_menu_c1_thanh_addvth_anh);
                ltbcd_dmnc1.gv_dong_menu_c1_thanh_addvth_thuong_hieu = (GridView) convertView.findViewById(R.id.gv_dong_menu_c1_thanh_addvth_thuong_hieu);
                convertView.setTag(ltbcd_dmnc1);
            }
           // else ltbcd_dmnc1 = (luu_tru_bcd_dong_menu_c1_thanh_anh_dai_dien_va_thuong_hieu) convertView.getTag();
            //banner và grid
            Picasso.with(context).load(anh_dai_dien)
                    .fit().into(ltbcd_dmnc1.iv_dong_menu_c1_thanh_addvth_anh);
            bcd_dong_menu_c1_dong_thuong_hieu bcd_dmnc1_th = new bcd_dong_menu_c1_dong_thuong_hieu(context, danh_sach_thuong_hieu);
            ltbcd_dmnc1.gv_dong_menu_c1_thanh_addvth_thuong_hieu.setNumColumns(3);
            ltbcd_dmnc1.gv_dong_menu_c1_thanh_addvth_thuong_hieu.setAdapter(bcd_dmnc1_th);
            Log.d("dataonline", danh_sach_thuong_hieu.size() + "");
        }
        return convertView;
    }

    private class   luu_tru_bcd_dong_menu_c1_noi_dung{
        ImageView   iv_dong_menu_c1_noi_dung_anh_0,
                    iv_dong_menu_c1_noi_dung_anh_1,
                    iv_dong_menu_c1_noi_dung_anh_2;
        TextView    tv_dong_menu_c1_noi_dung_ten_0,
                    tv_dong_menu_c1_noi_dung_ten_1,
                    tv_dong_menu_c1_noi_dung_ten_2;
    }
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        luu_tru_bcd_dong_menu_c1_noi_dung ltbcd_dmn_c1_nd = new luu_tru_bcd_dong_menu_c1_noi_dung();
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_menu_c1_noi_dung, null);
            ltbcd_dmn_c1_nd.iv_dong_menu_c1_noi_dung_anh_0 = convertView.findViewById(R.id.iv_dong_menu_c1_noi_dung_anh_0);
            ltbcd_dmn_c1_nd.iv_dong_menu_c1_noi_dung_anh_1 = convertView.findViewById(R.id.iv_dong_menu_c1_noi_dung_anh_1);
            ltbcd_dmn_c1_nd.iv_dong_menu_c1_noi_dung_anh_2 = convertView.findViewById(R.id.iv_dong_menu_c1_noi_dung_anh_2);
            ltbcd_dmn_c1_nd.tv_dong_menu_c1_noi_dung_ten_0 = convertView.findViewById(R.id.tv_dong_menu_c1_noi_dung_ten_0);
            ltbcd_dmn_c1_nd.tv_dong_menu_c1_noi_dung_ten_1 = convertView.findViewById(R.id.tv_dong_menu_c1_noi_dung_ten_1);
            ltbcd_dmn_c1_nd.tv_dong_menu_c1_noi_dung_ten_2 = convertView.findViewById(R.id.tv_dong_menu_c1_noi_dung_ten_2);
            convertView.setTag(ltbcd_dmn_c1_nd);
        }else ltbcd_dmn_c1_nd = (luu_tru_bcd_dong_menu_c1_noi_dung) convertView.getTag();

        if(danh_sach_lsp_c1
                .get(groupPosition)
                .getDanh_sach_loai_san_pham_nhom_cap_2()
                .get(childPosition)
                .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                .size() > 2){
            Picasso.with(context).load(danh_sach_lsp_c1
                    .get(groupPosition)
                    .getDanh_sach_loai_san_pham_nhom_cap_2()
                    .get(childPosition)
                    .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                    .get(2)
                    .getAnh_loai_san_pham())
                    .into(ltbcd_dmn_c1_nd.iv_dong_menu_c1_noi_dung_anh_2);
            ltbcd_dmn_c1_nd.tv_dong_menu_c1_noi_dung_ten_2.setText(danh_sach_lsp_c1
                    .get(groupPosition)
                    .getDanh_sach_loai_san_pham_nhom_cap_2()
                    .get(childPosition)
                    .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                    .get(2)
                    .getTen_loai_san_pham());

            ltbcd_dmn_c1_nd.iv_dong_menu_c1_noi_dung_anh_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), mang_hinh_ds_san_pham.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                    ArrayList<loai_sp> ds_lsp = new ArrayList<loai_sp>();
                    ds_lsp.add(new loai_sp(danh_sach_lsp_c1
                            .get(groupPosition)
                            .getDanh_sach_loai_san_pham_nhom_cap_2()
                            .get(childPosition)
                            .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                            .get(2)
                            .getId_loai_san_pham(), danh_sach_lsp_c1
                            .get(groupPosition)
                            .getDanh_sach_loai_san_pham_nhom_cap_2()
                            .get(childPosition)
                            .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                            .get(2).ten_loai_san_pham));
                    yeu_cau.getBo_loc().setDs_loai_sp(ds_lsp);
                    intent.putExtra("yeu_cau", gson.toJson(yeu_cau));
                    context.getApplicationContext().startActivity(intent);
                }
            });
            ltbcd_dmn_c1_nd.tv_dong_menu_c1_noi_dung_ten_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), mang_hinh_ds_san_pham.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                    ArrayList<loai_sp> ds_lsp = new ArrayList<loai_sp>();
                    ds_lsp.add(new loai_sp(danh_sach_lsp_c1
                            .get(groupPosition)
                            .getDanh_sach_loai_san_pham_nhom_cap_2()
                            .get(childPosition)
                            .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                            .get(2)
                            .getId_loai_san_pham(), danh_sach_lsp_c1
                            .get(groupPosition)
                            .getDanh_sach_loai_san_pham_nhom_cap_2()
                            .get(childPosition)
                            .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                            .get(2).ten_loai_san_pham));
                    yeu_cau.getBo_loc().setDs_loai_sp(ds_lsp);
                    intent.putExtra("yeu_cau", gson.toJson(yeu_cau));
                    context.getApplicationContext().startActivity(intent);
                }
            });
        }
        if(danh_sach_lsp_c1
                .get(groupPosition)
                .getDanh_sach_loai_san_pham_nhom_cap_2()
                .get(childPosition)
                .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                .size() > 1){
            Picasso.with(context).load(danh_sach_lsp_c1
                    .get(groupPosition)
                    .getDanh_sach_loai_san_pham_nhom_cap_2()
                    .get(childPosition)
                    .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                    .get(1)
                    .getAnh_loai_san_pham())
                    .into(ltbcd_dmn_c1_nd.iv_dong_menu_c1_noi_dung_anh_1);
            ltbcd_dmn_c1_nd.tv_dong_menu_c1_noi_dung_ten_1.setText(danh_sach_lsp_c1
                    .get(groupPosition)
                    .getDanh_sach_loai_san_pham_nhom_cap_2()
                    .get(childPosition)
                    .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                    .get(1)
                    .getTen_loai_san_pham());
            ltbcd_dmn_c1_nd.iv_dong_menu_c1_noi_dung_anh_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), mang_hinh_ds_san_pham.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                    ArrayList<loai_sp> ds_lsp = new ArrayList<loai_sp>();
                    ds_lsp.add(new loai_sp(danh_sach_lsp_c1
                            .get(groupPosition)
                            .getDanh_sach_loai_san_pham_nhom_cap_2()
                            .get(childPosition)
                            .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                            .get(1)
                            .getId_loai_san_pham(), danh_sach_lsp_c1
                            .get(groupPosition)
                            .getDanh_sach_loai_san_pham_nhom_cap_2()
                            .get(childPosition)
                            .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                            .get(1).ten_loai_san_pham));
                    yeu_cau.getBo_loc().setDs_loai_sp(ds_lsp);
                    intent.putExtra("yeu_cau", gson.toJson(yeu_cau));
                    context.getApplicationContext().startActivity(intent);
                }
            });
            ltbcd_dmn_c1_nd.tv_dong_menu_c1_noi_dung_ten_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), mang_hinh_ds_san_pham.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                    ArrayList<loai_sp> ds_lsp = new ArrayList<loai_sp>();
                    ds_lsp.add(new loai_sp(danh_sach_lsp_c1
                            .get(groupPosition)
                            .getDanh_sach_loai_san_pham_nhom_cap_2()
                            .get(childPosition)
                            .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                            .get(1)
                            .getId_loai_san_pham(), danh_sach_lsp_c1
                            .get(groupPosition)
                            .getDanh_sach_loai_san_pham_nhom_cap_2()
                            .get(childPosition)
                            .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                            .get(1).ten_loai_san_pham));
                    yeu_cau.getBo_loc().setDs_loai_sp(ds_lsp);
                    intent.putExtra("yeu_cau", gson.toJson(yeu_cau));
                    context.getApplicationContext().startActivity(intent);
                }
            });
        }

        Picasso.with(context).load(danh_sach_lsp_c1
                .get(groupPosition)
                .getDanh_sach_loai_san_pham_nhom_cap_2()
                .get(childPosition)
                .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                .get(0)
                .getAnh_loai_san_pham())
                .into(ltbcd_dmn_c1_nd.iv_dong_menu_c1_noi_dung_anh_0);
        ltbcd_dmn_c1_nd.tv_dong_menu_c1_noi_dung_ten_0.setText(danh_sach_lsp_c1
                .get(groupPosition)
                .getDanh_sach_loai_san_pham_nhom_cap_2()
                .get(childPosition)
                .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                .get(0)
                .getTen_loai_san_pham());

        ltbcd_dmn_c1_nd.iv_dong_menu_c1_noi_dung_anh_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), mang_hinh_ds_san_pham.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                ArrayList<loai_sp> ds_lsp = new ArrayList<loai_sp>();
                ds_lsp.add(new loai_sp(danh_sach_lsp_c1
                        .get(groupPosition)
                        .getDanh_sach_loai_san_pham_nhom_cap_2()
                        .get(childPosition)
                        .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                        .get(0)
                        .getId_loai_san_pham(), danh_sach_lsp_c1
                        .get(groupPosition)
                        .getDanh_sach_loai_san_pham_nhom_cap_2()
                        .get(childPosition)
                        .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                        .get(0).ten_loai_san_pham));
                yeu_cau.getBo_loc().setDs_loai_sp(ds_lsp);
                intent.putExtra("yeu_cau", gson.toJson(yeu_cau));
                context.getApplicationContext().startActivity(intent);
            }
        });
        ltbcd_dmn_c1_nd.tv_dong_menu_c1_noi_dung_ten_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), mang_hinh_ds_san_pham.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                ArrayList<loai_sp> ds_lsp = new ArrayList<loai_sp>();
                ds_lsp.add(new loai_sp(danh_sach_lsp_c1
                        .get(groupPosition)
                        .getDanh_sach_loai_san_pham_nhom_cap_2()
                        .get(childPosition)
                        .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                        .get(0)
                        .getId_loai_san_pham(), danh_sach_lsp_c1
                        .get(groupPosition)
                        .getDanh_sach_loai_san_pham_nhom_cap_2()
                        .get(childPosition)
                        .danh_sach_loai_san_pham_nhom_cap_2_phan_tu
                        .get(0).ten_loai_san_pham));
                yeu_cau.getBo_loc().setDs_loai_sp(ds_lsp);
                intent.putExtra("yeu_cau", gson.toJson(yeu_cau));
                context.getApplicationContext().startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
