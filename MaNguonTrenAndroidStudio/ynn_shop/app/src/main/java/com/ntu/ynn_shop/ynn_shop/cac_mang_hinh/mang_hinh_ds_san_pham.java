package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.Interface.ItemTouchListenner;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_dssp_recyclerview;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_popup_sap_xep_mhdssp;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.SimpleItemTouchHelperCallback;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.luu_yeu_cau_chuoi;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_khoang_trong;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.sp_ds.san_pham_tds;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thuong_hieu;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.loai_sp;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.phan_trang;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.sap_xep;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Arrays;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

import static android.support.v4.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED;

public class mang_hinh_ds_san_pham extends AppCompatActivity {
    boolean da_het_du_lieu = false;
    boolean ds_van_chay = false;
    boolean yc_van_chay = false;
    boolean da_load_chua = false;
    RecyclerView RV_dong_ds_san_pham;
    bcd_dong_dssp_recyclerview bcd_d_dssp_rv ;
    Gson gson;
    yeu_cau yeu_cau;
    Dialog awesomeInfoDialog;
    ArrayList<san_pham_tds> danh_sach_san_pham;
    Toolbar toolbar;
    DrawerLayout dl_root;
    Dialog dialog;
    RecyclerView rv_popup_sx_mhdssp;
    bcd_dong_popup_sap_xep_mhdssp bcd__popup_sap_xep_mhdssp;
    ArrayList<sap_xep> sap_xep;
    luu_yeu_cau_chuoi ycchuoi;
    yeu_cau yeu_cau_phu;
    ma_hoa_base64 maHoaBase64;
    FloatingActionButton FAB_cuon_len;
    //bo loc
    TagContainerLayout TCL_lsp, TCL_th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_ds_san_pham);
        System.gc();
        thiet_lap_thong_bao();
        thiet_lap_toolbar();
        anhxa();
        nhan_yeu_cau_truyen_mang_hinh();
        thiet_lap_sap_xep();
        new asynctask_tai_xuly_load("khoi_tao_trang_danh_sach","json_yeu_cau_ds", 1)
                .execute(gson.toJson(yeu_cau));
        new asynctask_tai_yeu_cau("khoi_tao_yeu_cau_tra_ve_cho_trang_danh_sach","json_ds_sp_yc")
                .execute(gson.toJson(yeu_cau));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.menu_tim_kiem, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        //getFragmentManager().popBackStack();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.trang_chu) {
            Intent intent = new Intent(mang_hinh_ds_san_pham.this, mang_hinh_trang_chu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.trang_giam_gia) {
            Intent intent = new Intent(mang_hinh_ds_san_pham.this, mang_hinh_giam_gia.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.trang_danh_muc) {
            Intent intent = new Intent(mang_hinh_ds_san_pham.this, mang_hinh_menu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.trang_tai_khoan) {
            Intent intent = new Intent(mang_hinh_ds_san_pham.this, man_hinh_tai_khoan.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }else if (id == android.R.id.home) {
            finish();
        return true;
    }
        return super.onOptionsItemSelected(item);
    }
    private void thiet_lap_toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((TextView) toolbar.findViewById(R.id.tv_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mang_hinh_ds_san_pham.this, mang_hinh_tim_kiem.class);
                startActivity(intent);
            }
        });
        ((TextView) toolbar.findViewById(R.id.tv_cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mang_hinh_ds_san_pham.this, man_hinh_gio_hang.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void thiet_lap_sap_xep() {
        //Lấy dữ liệu đã update
        sap_xep = yeu_cau.getSap_xep();
        dialog = new Dialog(mang_hinh_ds_san_pham.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.poup_sap_xep_mang_hinh_ds_san_pham);
        dialog.setCancelable(false);
        rv_popup_sx_mhdssp = (RecyclerView) dialog.findViewById(R.id.rv_popup_sx_mhdssp);
        bcd__popup_sap_xep_mhdssp = new bcd_dong_popup_sap_xep_mhdssp(mang_hinh_ds_san_pham.this,sap_xep);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mang_hinh_ds_san_pham.this);
        rv_popup_sx_mhdssp.setLayoutManager(layoutManager);
        rv_popup_sx_mhdssp.setAdapter(bcd__popup_sap_xep_mhdssp);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(new ItemTouchListenner() {
            @Override
            public void onMove(int oldPosition, int newPosition) {
                bcd__popup_sap_xep_mhdssp.onMove(oldPosition, newPosition);
            }

            @Override
            public void swipe(int position, int direction) {
                bcd__popup_sap_xep_mhdssp.swipe(position, direction);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rv_popup_sx_mhdssp);
        ((Button) dialog.findViewById(R.id.bt_popup_sx_mhdssp_ap_dung)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                RV_dong_ds_san_pham.getLayoutManager().scrollToPosition(0);
                yeu_cau.setSap_xep(bcd__popup_sap_xep_mhdssp.getDanh_sach_sap_xep());
                yeu_cau.getBo_loc().setPhan_trang(new phan_trang(0,20));
                // Ngắt đuôi
                ycchuoi=null;
                ycchuoi=new luu_yeu_cau_chuoi(gson.toJson(yeu_cau.getBo_loc()), gson.toJson(yeu_cau.getSap_xep()));
                //
                da_het_du_lieu=false;
                new asynctask_tai_xuly_load("khoi_tao_trang_danh_sach","json_yeu_cau_ds", 3)
                        .execute(gson.toJson(yeu_cau));
            }
        });
        ((Button) dialog.findViewById(R.id.bt_popup_sx_mhdssp_thoi)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sap_xep.clear();
                sap_xep.addAll(ycchuoi.getYeuCau().getSap_xep());
                bcd__popup_sap_xep_mhdssp.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        ((LinearLayout)  findViewById(R.id.ll_sap_xep)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


    }
    private void thiet_lap_bo_loc(){
        if(yeu_cau_phu.getBo_loc().getDs_loai_sp()!=null && yeu_cau_phu.getBo_loc().getDs_loai_sp().size()!=0){
            ArrayList<String> mang_lsp  = new ArrayList<>();
            int max_lsp_phu = yeu_cau_phu.getBo_loc().getDs_loai_sp().size();
            for(int i = 0; i< max_lsp_phu; i++)
                mang_lsp.add(maHoaBase64.giai_ma(yeu_cau_phu.getBo_loc().getDs_loai_sp().get(i).getTen_loai_san_pham()));
            TCL_lsp.removeAllTags();
            TCL_lsp.setTags(mang_lsp);
            //setflagmau
            for(int i = 0; i< max_lsp_phu; i++)
                xoa_mau(TCL_lsp.getTagView(i));
            //Lấy dữ liệu đã chọn
            if(yeu_cau.getBo_loc().getDs_loai_sp()!=null&&yeu_cau.getBo_loc().getDs_loai_sp().size()!=0)
            {
                int max_lsp = yeu_cau.getBo_loc().getDs_loai_sp().size();
                for(int i=0; i < max_lsp_phu; i++)
                    for(int j = 0; j < max_lsp; j++)
                    {
                        if(yeu_cau_phu.getBo_loc().getDs_loai_sp().get(i).getId_loai_san_pham()
                                == yeu_cau.getBo_loc().getDs_loai_sp().get(j).getId_loai_san_pham())
                            to_mau(TCL_lsp.getTagView(i));

                    }
            }
        }
        TCL_lsp.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                Log.d("crosssss", position + "");
                if(TCL_lsp.getTagView(position).getCrossColor()==Color.parseColor("#ffffff"))
                    to_mau(TCL_lsp.getTagView(position));
                else xoa_mau(TCL_lsp.getTagView(position));
            }

            @Override
            public void onTagLongClick(int position, String text) {
                Log.d("crosssss", position + "");
                if(TCL_lsp.getTagView(position).getCrossColor()==Color.parseColor("#ffffff"))
                    to_mau(TCL_lsp.getTagView(position));
                else xoa_mau(TCL_lsp.getTagView(position));
            }

            @Override
            public void onTagCrossClick(int position) {
                Log.d("crosssss", position + "");
                if(TCL_lsp.getTagView(position).getCrossColor()==Color.parseColor("#ffffff"))
                    to_mau(TCL_lsp.getTagView(position));
                else xoa_mau(TCL_lsp.getTagView(position));
            }
        });
        /**/
        if(yeu_cau_phu.getBo_loc().getDs_thuong_hieu()!=null && yeu_cau_phu.getBo_loc().getDs_thuong_hieu().size()!=0){
            ArrayList<String> mang_th  = new ArrayList<>();
            int max_th_phu = yeu_cau_phu.getBo_loc().getDs_thuong_hieu().size();
            for(int i = 0; i< max_th_phu; i++)
                mang_th.add(maHoaBase64.giai_ma(yeu_cau_phu.getBo_loc().getDs_thuong_hieu().get(i).getTen_thuong_hieu()));
            TCL_th.removeAllTags();
            TCL_th.setTags(mang_th);
            //làm sạch và set flag
            for(int i = 0; i< max_th_phu; i++) xoa_mau(TCL_th.getTagView(i));
            //Lấy dữ liệu đã chọn
            if(yeu_cau.getBo_loc().getDs_thuong_hieu()!=null&&yeu_cau.getBo_loc().getDs_thuong_hieu().size()!=0)
            {
                int max_th = yeu_cau.getBo_loc().getDs_thuong_hieu().size();
                for(int i=0; i < max_th_phu; i++)
                    for(int j = 0; j < max_th; j++)
                    {
                        if(yeu_cau_phu.getBo_loc().getDs_thuong_hieu().get(i).getId_thuong_hieu()
                                == yeu_cau.getBo_loc().getDs_thuong_hieu().get(j).getId_thuong_hieu())
                            to_mau(TCL_th.getTagView(i));

                    }
            }
        }
        TCL_th.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                if(TCL_th.getTagView(position).getCrossColor()==Color.parseColor("#ffffff"))
                    to_mau(TCL_th.getTagView(position));
                else
                if(TCL_th.getTagView(position).getCrossColor()!=Color.parseColor("#ffffff"))
                    xoa_mau(TCL_th.getTagView(position));
            }
            @Override
            public void onTagLongClick(int position, String text) {
                if(TCL_th.getTagView(position).getCrossColor()==Color.parseColor("#ffffff"))
                    to_mau(TCL_th.getTagView(position));
                else
                if(TCL_th.getTagView(position).getCrossColor()!=Color.parseColor("#ffffff"))
                    xoa_mau(TCL_th.getTagView(position));
            }
            @Override
            public void onTagCrossClick(int position) {
                if(TCL_th.getTagView(position).getCrossColor()==Color.parseColor("#ffffff"))
                    to_mau(TCL_th.getTagView(position));
                else
                if(TCL_th.getTagView(position).getCrossColor()!=Color.parseColor("#ffffff"))
                    xoa_mau(TCL_th.getTagView(position));
            }
        });
        ////giá
        if(yeu_cau_phu.getBo_loc().getGia_sp() != null
                && yeu_cau_phu.getBo_loc().getGia_sp().gia_thap_nhat != -1)
            ((EditText) findViewById(R.id.et_gia_thap)).setText(yeu_cau_phu.getBo_loc().getGia_sp().gia_thap_nhat+"");
        else ((EditText) findViewById(R.id.et_gia_thap)).setText("");

        if(yeu_cau_phu.getBo_loc().getGia_sp() != null
                && yeu_cau_phu.getBo_loc().getGia_sp().gia_cao_nhat != -1)
            ((EditText) findViewById(R.id.et_gia_cao)).setText(yeu_cau_phu.getBo_loc().getGia_sp().gia_cao_nhat+"");
        else ((EditText) findViewById(R.id.et_gia_cao)).setText("");
        ///Đánh giá
        if(yeu_cau_phu.getBo_loc().getDanh_gia() != null
                && yeu_cau_phu.getBo_loc().getDanh_gia().so_sao_thap_nhat != -1)
            ((RatingBar) findViewById(R.id.RB_danh_gia)).setRating(yeu_cau_phu.getBo_loc().getDanh_gia().so_sao_thap_nhat);
        else ((RatingBar) findViewById(R.id.RB_danh_gia)).setRating((float) 0);
        ///Tìm kiếm
        if(yeu_cau_phu.getBo_loc().getTim_kiem() != null
                && yeu_cau_phu.getBo_loc().getTim_kiem() != "")
            ((EditText) findViewById(R.id.et_tim_kiem)).setText(yeu_cau_phu.getBo_loc().getTim_kiem());
        else ((EditText) findViewById(R.id.et_tim_kiem)).setText("");
        ((Button) findViewById(R.id.bt_ap_dung)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cập nhật dữ liệu
                yeu_cau.getBo_loc().setDs_loai_sp(from_tag_view_to_loai_san_pham());
                yeu_cau.getBo_loc().setDs_thuong_hieu(from_tag_view_to_thuong_hieu());

                if(!((EditText) findViewById(R.id.et_gia_thap)).getText().toString().equals (""))
                    yeu_cau_phu.getBo_loc().getGia_sp().setGia_thap_nhat(Integer.parseInt(((EditText) findViewById(R.id.et_gia_thap)).getText().toString()));
                else yeu_cau_phu.getBo_loc().getGia_sp().setGia_thap_nhat(-1);

                if(!((EditText) findViewById(R.id.et_gia_cao)).getText().toString().equals (""))
                    yeu_cau_phu.getBo_loc().getGia_sp().setGia_cao_nhat(Integer.parseInt(((EditText) findViewById(R.id.et_gia_cao)).getText().toString()));
                else  yeu_cau_phu.getBo_loc().getGia_sp().setGia_cao_nhat(-1);

                if(((RatingBar) findViewById(R.id.RB_danh_gia)).getRating() != 0)
                    yeu_cau_phu.getBo_loc().getDanh_gia().setSo_sao_thap_nhat(((RatingBar) findViewById(R.id.RB_danh_gia)).getRating());
                else yeu_cau_phu.getBo_loc().getDanh_gia().setSo_sao_thap_nhat(-1);
                if(!((EditText) findViewById(R.id.et_tim_kiem)).getText().toString().equals (""))
                    yeu_cau_phu.getBo_loc().setTim_kiem(((EditText) findViewById(R.id.et_tim_kiem)).getText().toString());
                else yeu_cau_phu.getBo_loc().setTim_kiem(null);
                //xuất dữ liệu
                yeu_cau.getBo_loc().setGia_sp(yeu_cau_phu.getBo_loc().getGia_sp());
                yeu_cau.getBo_loc().setDanh_gia(yeu_cau_phu.getBo_loc().getDanh_gia());
                yeu_cau.getBo_loc().setTim_kiem(yeu_cau_phu.getBo_loc().getTim_kiem());
                RV_dong_ds_san_pham.getLayoutManager().scrollToPosition(0);
                yeu_cau.getBo_loc().setPhan_trang(new phan_trang(0,20));
                // Ngắt đuôi
                ycchuoi=null;
                ycchuoi=new luu_yeu_cau_chuoi(gson.toJson(yeu_cau.getBo_loc()), gson.toJson(yeu_cau.getSap_xep()));
                //
                dl_root.closeDrawer((RelativeLayout) findViewById(R.id.rl_b));
                da_het_du_lieu=false;
                new asynctask_tai_xuly_load("khoi_tao_trang_danh_sach","json_yeu_cau_ds", 3)
                        .execute(gson.toJson(yeu_cau));
            }
        });
        ((Button) findViewById(R.id.bt_thoi)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl_root.closeDrawer((RelativeLayout) findViewById(R.id.rl_b));
            }
        });
    }
    private ArrayList<loai_sp> from_tag_view_to_loai_san_pham(){
        ArrayList<loai_sp> ds_loai_sp = new ArrayList<>();
        int max_lsp = yeu_cau_phu.getBo_loc().getDs_loai_sp().size();
        Log.d("yeu_cau_phu_ff0000->", Color.parseColor("#ff0000")+"");
        for(int i = 0; i < max_lsp; i++)
        {
            Log.d("yeu_cau_phu_ff0000", TCL_lsp.getTagView(i).getCrossColor()+"");
            if(TCL_lsp.getTagView(i).getCrossColor()==-65536
                    && TCL_lsp.getTagView(i).getCrossColor()!= -1 )
                ds_loai_sp.add(yeu_cau_phu.getBo_loc().getDs_loai_sp().get(i));
        }
        return ds_loai_sp;
    }
    private ArrayList<thuong_hieu> from_tag_view_to_thuong_hieu(){
        ArrayList<thuong_hieu> ds_thuong_hieu = new ArrayList<>();
        int max_th = yeu_cau_phu.getBo_loc().getDs_thuong_hieu().size();
        for(int i = 0; i < max_th; i++)
        {
            if(TCL_th.getTagView(i).getCrossColor()==-65536
                    && TCL_th.getTagView(i).getCrossColor()!= -1 )
                ds_thuong_hieu.add(yeu_cau_phu.getBo_loc().getDs_thuong_hieu().get(i));
        }
        return ds_thuong_hieu;
    }
    private void to_mau(TagView tagView)
    {
        tagView.setTagBackgroundColor(Color.parseColor("#f9f4f1"));
        tagView.setTagBorderColor(Color.parseColor("#f57224"));
        tagView.setRippleColor(Color.parseColor("#f57224"));
        tagView.setTagTextColor(Color.parseColor("#f37022"));
        tagView.setCrossColor(Color.parseColor("#ff0000"));
    }

    private void xoa_mau(TagView tagView)
    {
        tagView.setTagBackgroundColor(Color.parseColor("#eff0f5"));
        tagView.setTagBorderColor(Color.parseColor("#ffffff"));
        tagView.setRippleColor(Color.parseColor("#212121"));
        tagView.setTagTextColor(Color.parseColor("#212121"));
        tagView.setCrossColor(Color.parseColor("#ffffff"));
    }

    private void nhan_yeu_cau_truyen_mang_hinh() {
        Intent intent = getIntent();
        String json = intent.getStringExtra("yeu_cau");
        yeu_cau = gson.fromJson(json,yeu_cau.class);
        Log.d("dataonline_js", gson.toJson(yeu_cau));
    }
    private void thiet_lap_thong_bao(){
        awesomeInfoDialog = new AwesomeProgressDialog(this)
                .setTitle("ĐANG XỬ LÝ")
                .setMessage("Xin chờ trong giây lát!")
                .setCancelable(false)
                .show();
        awesomeInfoDialog.dismiss();
    }
    private void khoitao_manghinh_danh_sach_sp(ArrayList<san_pham_tds> splist, int mode) {
        /*
        Các mode:
        1 : khởi tạo
        2 : thêm
        3 : reset
         */
        if(splist!=null&&splist.size()!=0)
        {
            if (mode == 1) {
                danh_sach_san_pham.addAll(splist);
                RV_dong_ds_san_pham.setHasFixedSize(true);
                RV_dong_ds_san_pham.addItemDecoration(new tao_khoang_trong(7));
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
                RV_dong_ds_san_pham.setLayoutManager(staggeredGridLayoutManager); // set LayoutManager to RecyclerView
                bcd_d_dssp_rv = new bcd_dong_dssp_recyclerview(getApplicationContext(),danh_sach_san_pham) ;
                RV_dong_ds_san_pham.setAdapter(bcd_d_dssp_rv);
                RV_dong_ds_san_pham.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if(recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN) == false && da_het_du_lieu==false){
                            phan_trang phan_trang =  yeu_cau.getBo_loc().getPhan_trang();
                            int d = phan_trang.getVi_tri_ket_thuc();
                            int c = d+20;
                            //Log.d("dataonline_siZe", gson.toJson(yeu_cau) );
                            yeu_cau.getBo_loc().setPhan_trang(new phan_trang(d, c));
                            new asynctask_tai_xuly_load("khoi_tao_trang_danh_sach","json_yeu_cau_ds", 2)
                                    .execute(gson.toJson(yeu_cau));
                        }
                    }
                });
            }
            if(mode==2){
                danh_sach_san_pham.addAll(splist);
                bcd_d_dssp_rv.notifyDataSetChanged();
            }
            if(mode==3)
            {
                danh_sach_san_pham.clear();
                for(int i = 0; i < splist.size(); i++)
                    danh_sach_san_pham.add(splist.get(i));
                bcd_d_dssp_rv.notifyDataSetChanged();
            }
        }
        else {
            Toast.makeText(this, "Đã hết dữ liệu!", Toast.LENGTH_SHORT).show();
            da_het_du_lieu=true;
            if(mode==3) {
                danh_sach_san_pham.clear();
                bcd_d_dssp_rv.notifyDataSetChanged();
            }
        }
    }

    private void anhxa() {
        FAB_cuon_len = (FloatingActionButton) findViewById(R.id.FAB_cuon_len);
        maHoaBase64 = new ma_hoa_base64();
        da_het_du_lieu = false;
        RV_dong_ds_san_pham = (RecyclerView) findViewById(R.id.RV_dong_ds_san_pham);
        gson = new Gson();
        danh_sach_san_pham = new ArrayList<san_pham_tds>();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dl_root = (DrawerLayout) findViewById(R.id.dl_root);
        dl_root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ((LinearLayout)  findViewById(R.id.ll_bo_loc)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*point*/
                thiet_lap_bo_loc();
                dl_root.openDrawer((RelativeLayout) findViewById(R.id.rl_b));
            }
        });
        TCL_lsp = (TagContainerLayout) findViewById(R.id.TCL_lsp);
        TCL_th = (TagContainerLayout) findViewById(R.id.TCL_th);
        FAB_cuon_len.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RV_dong_ds_san_pham.getLayoutManager().scrollToPosition(0);
            }
        });
    }


    //ASYN-TASK FOR DANH SACH
    public class asynctask_tai_xuly_load extends AsyncTask<String, Void,  ArrayList<san_pham_tds>> {
        private String Method_DN;
        private String PARAM_DN = null;
        private int MODE=0;

        public asynctask_tai_xuly_load(String method_DN, String PARAM_DN, int MODE) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
            this.MODE = MODE;
        }

        @Override
        protected void onPreExecute() {
            if(!isConnected()||isFinishing()) cancel(true);
            if(yc_van_chay==false)
                awesomeInfoDialog.show();
            ds_van_chay=true;
            super.onPreExecute();
        }

        protected  ArrayList<san_pham_tds> doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            try{
                if(!isConnected()||isFinishing()) cancel(true);
                String input=strings[0];
                SoapObject requests = new SoapObject(thiet_lap_server.NAME_SPACE, Method_DN);
                if(PARAM_DN != null)
                    requests.addProperty(PARAM_DN, input);
                SoapSerializationEnvelope envelope  = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=true;
                envelope.setOutputSoapObject(requests);
                HttpTransportSE httpTransportSE = new HttpTransportSE(thiet_lap_server.SERVER_URL);
                httpTransportSE.call(thiet_lap_server.NAME_SPACE+Method_DN, envelope);
                SoapPrimitive data = (SoapPrimitive) envelope.getResponse();
                san_pham_tds[] sp = new san_pham_tds[0];
                sp = gson.fromJson(data.toString(),san_pham_tds[].class);
                ArrayList<san_pham_tds> splist = new ArrayList<san_pham_tds>(Arrays.asList(sp));
                if(!isConnected()||isFinishing()) cancel(true);
                return splist;
            }
            catch (Exception ex)
            {
                Log.e("loi",ex.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<san_pham_tds> san_pham_tds) {
            if(!isConnected()||isFinishing()) cancel(true);
            khoitao_manghinh_danh_sach_sp(san_pham_tds, MODE);
            ds_van_chay=false;
            if(yc_van_chay==false)
                awesomeInfoDialog.dismiss();
            super.onPostExecute(san_pham_tds);
        }
    }

    //ASYN-TASK FOR YEU CAU

    public class asynctask_tai_yeu_cau extends AsyncTask<String, Void, yeu_cau> {
        private String Method_DN;
        private String PARAM_DN = null;

        public asynctask_tai_yeu_cau(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }

        @Override
        protected void onPreExecute() {
            if(!isConnected()||isFinishing()) cancel(true);
            yc_van_chay=true;
            if(ds_van_chay==false)
                awesomeInfoDialog.show();
            super.onPreExecute();
        }

        protected yeu_cau doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            try{
                if(!isConnected()||isFinishing()) cancel(true);
                String input=strings[0];
                SoapObject requests = new SoapObject(thiet_lap_server.NAME_SPACE, Method_DN);
                if(PARAM_DN != null)
                    requests.addProperty(PARAM_DN, input);
                SoapSerializationEnvelope envelope  = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=true;
                envelope.setOutputSoapObject(requests);
                HttpTransportSE httpTransportSE = new HttpTransportSE(thiet_lap_server.SERVER_URL);
                httpTransportSE.call(thiet_lap_server.NAME_SPACE+Method_DN, envelope);
                SoapPrimitive data = (SoapPrimitive) envelope.getResponse();
                if(!isConnected()||isFinishing()) cancel(true);
                return gson.fromJson(data.toString(), yeu_cau.class);
                //return data.toString();
            }
            catch (Exception ex)
            {
                Log.e("loi",ex.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(yeu_cau yc) {
            if(!isConnected()||isFinishing()) cancel(true);
            /* kiểm tra và thêm*/
            {
                int max_lsp_yc_p = yc.bo_loc.getDs_loai_sp().size();
                int max_lsp_yc = yeu_cau.bo_loc.getDs_loai_sp().size();
                for(int i = 0; i < max_lsp_yc; i++) {
                    boolean flag = false;
                    for (int j = 0; j < max_lsp_yc_p; j++) {
                        if (yeu_cau.getBo_loc().getDs_loai_sp().get(i).getId_loai_san_pham() ==
                                yc.getBo_loc().getDs_loai_sp().get(j).getId_loai_san_pham()) {
                            flag = true;
                            break;
                        }
                    }
                    if(flag==false) {
                        yeu_cau.getBo_loc().getDs_loai_sp().get(i).setTen_loai_san_pham(
                                maHoaBase64.ma_hoa(yeu_cau.getBo_loc().getDs_loai_sp().get(i).getTen_loai_san_pham())
                        );
                        yc.getBo_loc().getDs_loai_sp().add(yeu_cau.getBo_loc().getDs_loai_sp().get(i));
                    }
                }
            }
            {
                int max_th_yc_p = yc.bo_loc.getDs_thuong_hieu().size();
                int max_th_yc = yeu_cau.bo_loc.getDs_thuong_hieu().size();
                for(int i = 0; i < max_th_yc; i++) {
                    boolean flag = false;
                    for (int j = 0; j < max_th_yc_p; j++) {
                        if (yeu_cau.getBo_loc().getDs_thuong_hieu().get(i).getId_thuong_hieu() ==
                                yc.getBo_loc().getDs_thuong_hieu().get(j).getId_thuong_hieu()) {
                            flag = true;
                            break;
                        }
                    }
                    if(flag==false)
                    {
                        yeu_cau.getBo_loc().getDs_thuong_hieu().get(i).setTen_thuong_hieu(
                                maHoaBase64.ma_hoa(yeu_cau.getBo_loc().getDs_thuong_hieu().get(i).getTen_thuong_hieu())
                        );
                        yc.getBo_loc().getDs_thuong_hieu().add(yeu_cau.getBo_loc().getDs_thuong_hieu().get(i));
                    }
                }
            }
            /* end kiểm tra và thêm*/
            ycchuoi=new luu_yeu_cau_chuoi(gson.toJson(yc.getBo_loc()), gson.toJson(yc.getSap_xep()));
            yc_van_chay=false;
            yeu_cau_phu=yc;
            if(ds_van_chay==false)
                awesomeInfoDialog.dismiss();
            super.onPostExecute(yc);
        }
    }
    private  boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(!(networkInfo!=null&&networkInfo.isConnected()))
            Toast.makeText(getApplicationContext(),"Không có mạng!",Toast.LENGTH_SHORT).show();
        return networkInfo!=null&&networkInfo.isConnected();
    }
}
