package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.Interface.CustomItemClickListener;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_mh_trang_chu_ntkh;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_mh_trang_chu_thuong_hieu_tot_nhat;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_mh_trang_chu_top_nam_sao;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_trang_chu_danh_muc_ua_thich;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.chuan_hoa_duong_dan_url;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_khoang_trong;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_khoang_trong_doc;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.anh_trinh_chieu;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.danh_muc_ua_thich;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thuong_hieu;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thuong_hieu_tot_nhat;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.topnamsao;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_giam_gia.chuong_trinh_giam_gia;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_giam_gia.sp_ap_dung;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.loai_sp;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;
import com.squareup.picasso.Picasso;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class mang_hinh_trang_chu extends AppCompatActivity {

    ViewFlipper viewFlipper;
    Gson gson;
    ArrayList<anh_trinh_chieu> ds_anh_trinh_chieu;
     ArrayList<chuong_trinh_giam_gia> ds_ct_gg;
     RecyclerView RV_ntkh ;
     ArrayList<sp_ap_dung> ds_ctgg;
     ArrayList<thuong_hieu_tot_nhat> ds_thuongHieuTotNhats;
    bcd_dong_mh_trang_chu_ntkh bcdDongMhTrangChuNtkhl;
    ma_hoa_base64 base64;
    chuan_hoa_duong_dan_url chuan_hoa_url;
    dinh_dang_tien dinh_dang ;
    TextView TV_thong_bao_thoi_gian, TV_gio, TV_phut, TV_giay;
    CountDownTimer CDT;
    RecyclerView RV_trang_chu_thtn ;
    bcd_mh_trang_chu_thuong_hieu_tot_nhat bcdMhTrangChuThuongHieuTotNhat;
    ArrayList<danh_muc_ua_thich> ds_danhMucUaThiches;
    RecyclerView RV_trang_chu_dmut;
    bcd_trang_chu_danh_muc_ua_thich bcdTrangChuDanhMucUaThich;
    ArrayList<topnamsao> ds_topnamsaos;
    bcd_mh_trang_chu_top_nam_sao bcdMhTrangChuTopNamSao;
    RecyclerView RV_trang_chu_topnamsao;
    LinearLayout LL_thanh_tim_kiem;
    TextView tv_xem_them;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_trang_chu);

        System.gc();
        anh_xa();
        thiet_lap_banner();
        thiet_lap_tab();
        if(isConnected())
        new tai_anh_trinh_chieu("lay_danh_sach_anh_trinh_chieu",null).execute("");
    }
    private void xu_ly_dmut() {
        bcdTrangChuDanhMucUaThich = new bcd_trang_chu_danh_muc_ua_thich(this, ds_danhMucUaThiches, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), mang_hinh_ds_san_pham.class);
                yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                ArrayList<loai_sp> ds_lsp = new ArrayList<loai_sp>();
                ds_lsp.add(new loai_sp(
                        ds_danhMucUaThiches.get(position).getId_loai_san_pham(),
                        ds_danhMucUaThiches.get(position).getTen_loai_san_pham()
                ));
                yeu_cau.getBo_loc().setDs_loai_sp(ds_lsp);
                intent.putExtra("yeu_cau", gson.toJson(yeu_cau));
                startActivity(intent);
            }
        });
        RV_trang_chu_dmut.addItemDecoration(new tao_khoang_trong(1));
        RV_trang_chu_dmut.setHasFixedSize(true);
        RV_trang_chu_dmut.setLayoutManager(new GridLayoutManager(this,3));
        RV_trang_chu_dmut.setNestedScrollingEnabled(false);
        RV_trang_chu_dmut.setAdapter(bcdTrangChuDanhMucUaThich);
    }
    private void xu_ly_thtn() {
        bcdMhTrangChuThuongHieuTotNhat  =  new bcd_mh_trang_chu_thuong_hieu_tot_nhat(this, ds_thuongHieuTotNhats, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), mang_hinh_ds_san_pham.class);
                yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                ArrayList<thuong_hieu> ds_th = new ArrayList<thuong_hieu>();
                ds_th.add(new thuong_hieu(
                        ds_thuongHieuTotNhats.get(position).getId_thuong_hieu(),
                        ds_thuongHieuTotNhats.get(position).getTen_thuong_hieu(),
                        ds_thuongHieuTotNhats.get(position).getAnh_thuong_hieu())
                );
                yeu_cau.getBo_loc().setDs_thuong_hieu(ds_th);
                intent.putExtra("yeu_cau",gson.toJson(yeu_cau));
                startActivity(intent);
            }
        });
        RV_trang_chu_thtn.addItemDecoration(new tao_khoang_trong(1));
        RV_trang_chu_thtn.setHasFixedSize(true);
        RV_trang_chu_thtn.setLayoutManager(new GridLayoutManager(this,2));
        RV_trang_chu_thtn.setAdapter(bcdMhTrangChuThuongHieuTotNhat);
        RV_trang_chu_thtn.setNestedScrollingEnabled(false);
    }
    private void thiet_lap_tab() {
//        ((LinearLayout) findViewById(R.id.LL_tab_Trang_Chu)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), mang_hinh_trang_chu.class);
//                startActivity(intent);
//                finish();
//            }
//        });
        ((LinearLayout) findViewById(R.id.LL_tab_Giam_gia)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mang_hinh_giam_gia.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        ((LinearLayout) findViewById(R.id.LL_tab_Danh_muc)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mang_hinh_menu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        ((LinearLayout) findViewById(R.id.LL_tab_Gio_hang)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), man_hinh_gio_hang.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        ((LinearLayout) findViewById(R.id.LL_tab_Tai_khoan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), man_hinh_tai_khoan.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
    private void xu_ly_ntkh() {
        ds_ctgg = ds_ct_gg.get(0).getDs_sp_ap_dung();
        //Xử lý pt 0
        long gia_ban_ra = ds_ctgg.get(0).getGia_ban_ra();
        float pt_con_lai = (100 - ds_ctgg.get(0).getPhan_tram_giam())/100;
        long gia_da_giam = (long) (gia_ban_ra*pt_con_lai );
        long giam_toi_da = ds_ctgg.get(0).getGiam_toi_da();
        if(gia_ban_ra - gia_da_giam >  giam_toi_da)
            gia_da_giam = gia_ban_ra - giam_toi_da;
        ImageView dmh_tc_ntkh_anh_sp;
        TextView dmh_tc_ntkh_gia_da_giam, dmh_tc_ntkh_gia_ban_ra, dmh_tc_ntkh_pt_giam;

        dmh_tc_ntkh_anh_sp = (ImageView) findViewById(R.id.dmh_tc_ntkh_anh_sp);
        dmh_tc_ntkh_gia_da_giam = (TextView) findViewById(R.id.dmh_tc_ntkh_gia_da_giam);
        dmh_tc_ntkh_gia_ban_ra = (TextView) findViewById(R.id.dmh_tc_ntkh_gia_ban_ra);
        dmh_tc_ntkh_pt_giam = (TextView) findViewById(R.id.dmh_tc_ntkh_pt_giam);

        Picasso.with(this).load(chuan_hoa_url.chuan_hoa(base64.giai_ma(ds_ctgg.get(0).getAnh_san_pham())))
                .fit()
                .into(dmh_tc_ntkh_anh_sp);
        dmh_tc_ntkh_gia_da_giam.setText(dinh_dang.dinh_dang(gia_da_giam+""));
        int ptgg= (int) ((gia_ban_ra - gia_da_giam)*1.0/gia_ban_ra*100);
        dmh_tc_ntkh_pt_giam.setText("-"+ ptgg+"%");
        dmh_tc_ntkh_gia_ban_ra.setText(dinh_dang.dinh_dang(gia_ban_ra+""));
        dmh_tc_ntkh_gia_ban_ra.setPaintFlags(dmh_tc_ntkh_gia_ban_ra.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        //
        ds_ctgg.remove(0);
        bcdDongMhTrangChuNtkhl = new bcd_dong_mh_trang_chu_ntkh(this,ds_ctgg);
        RV_ntkh.addItemDecoration(new tao_khoang_trong_doc(3));
        RV_ntkh.setHasFixedSize(true);
        RV_ntkh.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RV_ntkh.setAdapter(bcdDongMhTrangChuNtkhl);
        RV_ntkh.setNestedScrollingEnabled(false);
        thiet_lap_thoi_gian(ds_ct_gg.get(0).getThoi_diem_bat_dau_giam_gia(), ds_ct_gg.get(0).getThoi_diem_ket_thuc_giam_gia());

    }
    private void anh_xa() {
        tv_xem_them  =(TextView) findViewById(R.id.tv_xem_them);
        gson = new Gson();
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);
        RV_ntkh = (RecyclerView)  findViewById(R.id.RV_ntkh);
        base64 = new ma_hoa_base64();
        chuan_hoa_url = new chuan_hoa_duong_dan_url();
        dinh_dang = new dinh_dang_tien();
        TV_thong_bao_thoi_gian = (TextView) findViewById(R.id.TV_thong_bao_thoi_gian);
        TV_gio = (TextView) findViewById(R.id.TV_gio);
        TV_phut = (TextView) findViewById(R.id.TV_phut);
        TV_giay = (TextView) findViewById(R.id.TV_giay);
        RV_trang_chu_thtn = (RecyclerView) findViewById(R.id.RV_trang_chu_thtn);
        RV_trang_chu_dmut = (RecyclerView) findViewById(R.id.RV_trang_chu_dmut);
        RV_trang_chu_topnamsao = (RecyclerView) findViewById(R.id.RV_trang_chu_topnamsao);
        LL_thanh_tim_kiem = (LinearLayout) findViewById(R.id.LL_thanh_tim_kiem);
        LL_thanh_tim_kiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mang_hinh_tim_kiem.class);
                startActivity(intent);
            }
        });
        tv_xem_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mang_hinh_giam_gia.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);finish();
            }
        });
        ((LinearLayout) findViewById(R.id.LL_khung_ntkh)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mang_hinh_giam_gia.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        //getFragmentManager().popBackStack();
    }
    private void thiet_lap_banner() {
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }
    public void thiet_lap_thoi_gian(final String str_bat_dau, final String str_ket_thuc){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date d = null;
        Calendar bat_dau = Calendar.getInstance();
        Calendar ket_thuc = Calendar.getInstance();
        Calendar hien_tai = Calendar.getInstance();
        try {
            d = formatter.parse(str_bat_dau);//catch exception
            bat_dau.setTime(d);
            d = null;
            d = formatter.parse(str_ket_thuc);//catch exception
            ket_thuc.setTime(d);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(hien_tai.getTimeInMillis() < bat_dau.getTimeInMillis())
        {
            TV_thong_bao_thoi_gian.setText("Bắt đầu lúc");
            if(CDT!=null)
                CDT.cancel();
            CDT  = new CountDownTimer(bat_dau.getTimeInMillis() - hien_tai.getTimeInMillis(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if(!isConnected()||isFinishing()||isDestroyed())    { CDT.cancel(); return;}
                        long giay = (long) (millisUntilFinished / 1000) % 60 ;
                        long phut = (long) ((millisUntilFinished / (1000*60)) % 60);
                        long gio   = (long) ((millisUntilFinished / (1000*60*60)) % 24);
                        if(gio!= 10 && gio/10==0) TV_gio.setText("0"+gio+"");
                        else        TV_gio.setText(gio+"");
                        if(phut!= 10 && phut/10==0) TV_phut.setText("0"+phut+"");
                        else        TV_phut.setText(phut+"");
                        if(giay!= 10 && giay/10==0) TV_giay.setText("0"+giay+"");
                        else        TV_giay.setText(giay+"");
                }


                @Override
                public void onFinish() {
                    thiet_lap_thoi_gian(str_bat_dau,  str_ket_thuc);
                }
            }.start();
        }
        else if(hien_tai.getTimeInMillis() >=  bat_dau.getTimeInMillis()
                && hien_tai.getTimeInMillis() <=   ket_thuc.getTimeInMillis()){
            TV_thong_bao_thoi_gian.setText("Kết thúc trong");
            //đang diễn ra
            if(CDT!=null)
                CDT.cancel();
            CDT  = new CountDownTimer(ket_thuc.getTimeInMillis() - hien_tai.getTimeInMillis(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if(!isConnected()||isFinishing()||isDestroyed())    { CDT.cancel(); return;}
                            long giay = (long) (millisUntilFinished / 1000) % 60 ;
                            long phut = (long) ((millisUntilFinished / (1000*60)) % 60);
                            long gio   = (long) ((millisUntilFinished / (1000*60*60)) % 24);
                            if(gio!= 10 && gio/10==0) TV_gio.setText("0"+gio+"");
                            else        TV_gio.setText(gio+"");
                            if(phut!= 10 && phut/10==0) TV_phut.setText("0"+phut+"");
                            else        TV_phut.setText(phut+"");
                            if(giay!= 10 && giay/10==0) TV_giay.setText("0"+giay+"");
                            else        TV_giay.setText(giay+"");
                }

                @Override
                public void onFinish() {
                    thiet_lap_thoi_gian(str_bat_dau,  str_ket_thuc);
                }
            }.start();
        }
        else if( hien_tai.getTimeInMillis() >   ket_thuc.getTimeInMillis()){
            CDT.cancel();
            TV_gio.setText("00");
            TV_phut.setText("00");
            TV_giay.setText("00");
            TV_thong_bao_thoi_gian.setText("Đã kết thúc");
        }


    }


    public void xu_ly_top_nam_sao(){
        bcdMhTrangChuTopNamSao = new bcd_mh_trang_chu_top_nam_sao(this, ds_topnamsaos, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), mang_hinh_san_pham.class);
                intent.putExtra("id_san_pham", ds_topnamsaos.get(position).getId_san_pham());
                startActivity(intent);
            }
        });
        RV_trang_chu_topnamsao.setHasFixedSize(true);
        RV_trang_chu_topnamsao.addItemDecoration(new tao_khoang_trong(1));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        RV_trang_chu_topnamsao.setLayoutManager(staggeredGridLayoutManager); // set LayoutManager to RecyclerView
        RV_trang_chu_topnamsao.setAdapter(bcdMhTrangChuTopNamSao);
        RV_trang_chu_topnamsao.setNestedScrollingEnabled(false);
    }
    public class tai_anh_trinh_chieu extends AsyncTask<String, Void, ArrayList<anh_trinh_chieu>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_anh_trinh_chieu(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected ArrayList<anh_trinh_chieu> doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            boolean geted = false;
            int recall = 0;
            while (geted == false && recall < 5) {
                if(!isConnected()||isFinishing()) cancel(true);
                recall++;
                try {
                    geted = true;
                    String input = strings[0];
                    SoapObject requests = new SoapObject(thiet_lap_server.NAME_SPACE, Method_DN);
                    if (PARAM_DN != null)
                        requests.addProperty(PARAM_DN, input);
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.dotNet = true;
                    envelope.setOutputSoapObject(requests);
                    HttpTransportSE httpTransportSE = new HttpTransportSE(thiet_lap_server.SERVER_URL);
                    httpTransportSE.call(thiet_lap_server.NAME_SPACE + Method_DN, envelope);
                    SoapPrimitive data = (SoapPrimitive) envelope.getResponse();
                    anh_trinh_chieu[] ds_ctgg_tmp = gson.fromJson(data.toString(), anh_trinh_chieu[].class);
                    ArrayList<anh_trinh_chieu> dsctgg = new ArrayList<anh_trinh_chieu>(Arrays.asList(ds_ctgg_tmp));
                    if(!isConnected()||isFinishing()) cancel(true);
                    return dsctgg;
                } catch (Exception ex) {
                    geted = false;
                    Log.e("loi", "thử lần " + recall + ", Lý do lỗi: " + ex.toString());
                }
            }

            return null;

        }
            @Override
            protected void onPostExecute(ArrayList<anh_trinh_chieu> dsctgg) {
                if(!isConnected()||isFinishing()) cancel(true);
                ds_anh_trinh_chieu=dsctgg;
                if(dsctgg!=null && dsctgg.size()>0)
                {
                    for(int i = 0; i < dsctgg.size(); i++)
                    {
                        ImageView imageView = new ImageView(getApplicationContext());
                        Picasso.with(getApplicationContext()).load(dsctgg.get(i).getLink()).fit().into(imageView);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        viewFlipper.addView(imageView);
                    }
                }
                new tai_chuong_trinh_giam_gia("Lay_du_lieu_nhanh_tay_keo_het",null).execute("");
                super.onPostExecute(dsctgg);
            }
        }
    public class tai_chuong_trinh_giam_gia extends AsyncTask<String, Void, ArrayList<chuong_trinh_giam_gia>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_chuong_trinh_giam_gia(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected ArrayList<chuong_trinh_giam_gia> doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            boolean geted = false;
            int recall = 0;
            while (geted==false && recall < 5)
            {
                if(!isConnected()||isFinishing()) cancel(true);
                recall++;
                try{
                    geted=true;
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
                    chuong_trinh_giam_gia[] ds_ctgg_tmp = gson.fromJson(data.toString(),chuong_trinh_giam_gia[].class);
                    ArrayList<chuong_trinh_giam_gia> dsctgg = new ArrayList<chuong_trinh_giam_gia>(Arrays.asList(ds_ctgg_tmp));
                    if(!isConnected()||isFinishing()) cancel(true);
                    return dsctgg;
                }
                catch (Exception ex)
                {
                    geted=false;
                    Log.e("loi","thử lần " + recall +", Lý do lỗi: "+ ex.toString());
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<chuong_trinh_giam_gia> dsctgg) {
            if(!isConnected()||isFinishing()) cancel(true);
          new tai_thuong_hieu_tot_nhat("Lay_du_lieu_thuong_hieu_tot_nhat",null).execute("");
            ds_ct_gg=dsctgg;
            if(dsctgg!=null && dsctgg.size()>0)
            xu_ly_ntkh();
            else ((LinearLayout) findViewById(R.id.LL_ntkh)).setVisibility(View.GONE);
            super.onPostExecute(dsctgg);
        }
    }
    public class tai_thuong_hieu_tot_nhat extends AsyncTask<String, Void,  ArrayList<thuong_hieu_tot_nhat>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_thuong_hieu_tot_nhat(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected  ArrayList<thuong_hieu_tot_nhat> doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            boolean geted = false;
            int recall = 0;
            while (geted==false && recall < 5)
            {
                if(!isConnected()||isFinishing()) cancel(true);
                recall++;
                try{
                    geted=true;
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
                    thuong_hieu_tot_nhat[] ds_thtn_tmp = gson.fromJson(data.toString(),thuong_hieu_tot_nhat[].class);
                    ArrayList<thuong_hieu_tot_nhat> ds_thtn_kq = new ArrayList<thuong_hieu_tot_nhat>(Arrays.asList(ds_thtn_tmp));
                    return ds_thtn_kq;
                }
                catch (Exception ex)
                {
                    geted=false;
                    Log.e("loi","thử lần " + recall +", Lý do lỗi: "+ ex.toString());
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute( ArrayList<thuong_hieu_tot_nhat> ds_thtn) {
            if(!isConnected()||isFinishing()) cancel(true);
            ds_thuongHieuTotNhats = ds_thtn;
            if(ds_thuongHieuTotNhats != null)
                xu_ly_thtn();
            new tai_danh_muc_ua_thich("danh_muc_ua_thich",null).execute("");
            super.onPostExecute(ds_thtn);
        }
    }

    public class tai_danh_muc_ua_thich extends AsyncTask<String, Void,  ArrayList<danh_muc_ua_thich>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_danh_muc_ua_thich(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected  ArrayList<danh_muc_ua_thich> doInBackground(String... strings) {
            boolean geted = false;
            int recall = 0;
            while (geted==false && recall < 5)
            {
                if(!isConnected()||isFinishing()) cancel(true);
                recall++;
                try{
                    geted=true;
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
                    danh_muc_ua_thich[] ds_dmut_tmp = gson.fromJson(data.toString(),danh_muc_ua_thich[].class);
                    ArrayList<danh_muc_ua_thich> ds_dmut_kq = new ArrayList<danh_muc_ua_thich>(Arrays.asList(ds_dmut_tmp));
                    if(!isConnected()||isFinishing()) cancel(true);
                    return ds_dmut_kq;
                }
                catch (Exception ex)
                {
                    geted=false;
                    Log.e("loi","thử lần " + recall +", Lý do lỗi: "+ ex.toString());
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<danh_muc_ua_thich> tmp) {
            if(!isConnected()||isFinishing()) cancel(true);
            new tai_top_nam_sao("Lay_danh_sach_san_pham_top_nam_sao", null).execute("");
            ds_danhMucUaThiches=tmp;
            if(tmp!=null)
            xu_ly_dmut();
            super.onPostExecute(tmp);
        }
    }
    public class tai_top_nam_sao extends AsyncTask<String, Void,  ArrayList<topnamsao>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_top_nam_sao(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected  ArrayList<topnamsao> doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            boolean geted = false;
            int recall = 0;
            while (geted==false && recall < 5)
            {
                if(!isConnected()||isFinishing()) cancel(true);
                recall++;
                try{
                    geted=true;
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
                    topnamsao[] ds_t5s_tmp = gson.fromJson(data.toString(),topnamsao[].class);
                    ArrayList<topnamsao> ds_t5s_kq = new ArrayList<>(Arrays.asList(ds_t5s_tmp));
                    if(!isConnected()||isFinishing()) cancel(true);
                    return ds_t5s_kq;
                }
                catch (Exception ex)
                {
                    geted=false;
                    Log.e("loi","thử lần " + recall +", Lý do lỗi: "+ ex.toString());
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<topnamsao> tmp) {
            if(!isConnected()||isFinishing()) cancel(true);
            ds_topnamsaos=tmp;
            if(tmp!=null)
                xu_ly_top_nam_sao();
            super.onPostExecute(tmp);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewFlipper = null;
        RV_ntkh.setAdapter(null);
        RV_trang_chu_thtn.setAdapter(null);
        RV_trang_chu_topnamsao.setAdapter(null);
        RV_trang_chu_dmut.setAdapter(null);
        RV_ntkh = null;
        RV_trang_chu_thtn = null;
        RV_trang_chu_topnamsao = null;
        RV_trang_chu_dmut = null;
        bcdMhTrangChuThuongHieuTotNhat=null;
        bcdTrangChuDanhMucUaThich=null;
        ds_topnamsaos=null;
        bcdMhTrangChuTopNamSao=null;

    }
    private  boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(!(networkInfo!=null&&networkInfo.isConnected()))
            Toast.makeText(getApplicationContext(),"Không có mạng!",Toast.LENGTH_SHORT).show();
        return networkInfo!=null&&networkInfo.isConnected();
    }
}
