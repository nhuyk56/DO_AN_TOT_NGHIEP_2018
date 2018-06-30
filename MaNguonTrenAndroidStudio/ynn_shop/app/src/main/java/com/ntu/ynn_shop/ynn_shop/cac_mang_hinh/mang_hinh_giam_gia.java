package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.Interface.CustomItemClickListener;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_dsctgg;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.dong_mh_gg_ds_san_pham_gg;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_khoang_trong;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_khoang_trong_doc;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_khoang_trong_ngang;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.sp_ds.san_pham_tds;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_giam_gia.chuong_trinh_giam_gia;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_giam_gia.sp_ap_dung;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;

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
import java.util.concurrent.TimeUnit;

public class mang_hinh_giam_gia extends AppCompatActivity {
    Toolbar toolbar;
     Gson gson;
     ArrayList<chuong_trinh_giam_gia> ds_ct_gg;
     RecyclerView RV_ds_sp_ad ;
     ArrayList<sp_ap_dung> ds_ctgg;
     dong_mh_gg_ds_san_pham_gg dongMhGgDsSanPhamGg;
     TextView TV_thong_bao_thoi_gian;
     TextView TV_gio;
     TextView TV_phut;
     TextView TV_giay;
     TextView TV_ma_gg;
     TextView TV_lydogiamgia;
    RecyclerView RV_ds_ctgg;
     CountDownTimer CDT;
    bcd_dong_dsctgg DSCTGG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_giam_gia);
        System.gc();
        gson = new Gson();
        anh_xa();
        thiet_lap_toolbar();
        thiet_lap_tab();
        new tai_chuong_trinh_giam_gia("Lay_du_lieu_giam_gia",null).execute("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.menu_tim_kiem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.trang_chu) {
            Intent intent = new Intent(mang_hinh_giam_gia.this, mang_hinh_trang_chu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.trang_giam_gia) {
            Toast.makeText(mang_hinh_giam_gia.this,"Bạn đang ở trang giảm giá!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.trang_danh_muc) {
            Intent intent = new Intent(mang_hinh_giam_gia.this, mang_hinh_menu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.trang_tai_khoan) {
            Intent intent = new Intent(mang_hinh_giam_gia.this, man_hinh_tai_khoan.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
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
        ((TextView) toolbar.findViewById(R.id.tv_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mang_hinh_giam_gia.this, mang_hinh_tim_kiem.class);
                startActivity(intent);
            }
        });
        ((TextView) toolbar.findViewById(R.id.tv_cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mang_hinh_giam_gia.this, man_hinh_gio_hang.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        //getFragmentManager().popBackStack();
    }
    private void thiet_lap_tab() {
        ((LinearLayout) findViewById(R.id.LL_tab_Trang_Chu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mang_hinh_trang_chu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
//        ((LinearLayout) findViewById(R.id.LL_tab_Giam_gia)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), mang_hinh_giam_gia.class);
//                startActivity(intent);
//                finish();
//            }
//        });
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
    private void anh_xa() {
        ds_ctgg = new ArrayList<>();
        RV_ds_sp_ad = (RecyclerView) findViewById(R.id.RV_ds_sp_ad);
        TV_thong_bao_thoi_gian = (TextView) findViewById(R.id.TV_thong_bao_thoi_gian);
        TV_gio = (TextView) findViewById(R.id.TV_gio);
        TV_phut = (TextView) findViewById(R.id.TV_phut);
        TV_giay = (TextView) findViewById(R.id.TV_giay);
        TV_ma_gg = (TextView) findViewById(R.id.TV_ma_gg);
        TV_lydogiamgia = (TextView) findViewById(R.id.TV_lydogiamgia);
        RV_ds_ctgg = (RecyclerView) findViewById(R.id.RV_ds_ctgg);
    }

    private void run(){
        ArrayList<String> ds_ten_ctgg = new ArrayList<>();
        for(int i = 0; i < ds_ct_gg.size(); i++)
            ds_ten_ctgg.add(ds_ct_gg.get(i).getTen_giam_gia());
          DSCTGG = new bcd_dong_dsctgg(this, ds_ten_ctgg, new CustomItemClickListener() {
           @Override
           public void onItemClick(View v, int position) {
               if(DSCTGG.getRow_index()==position)
                ds_sp_ad(position);
           }
       });

        RV_ds_ctgg.addItemDecoration(new tao_khoang_trong_ngang(1));
        RV_ds_ctgg.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        RV_ds_ctgg.setAdapter(DSCTGG);
        if(ds_ct_gg!=null &&ds_ct_gg.size()>0 )
        {
            sp_ap_dung[] tmp1 = gson.fromJson(gson.toJson(ds_ct_gg.get(0).getDs_sp_ap_dung()),sp_ap_dung[].class);
            ArrayList<sp_ap_dung> tmp2 = new ArrayList<sp_ap_dung>(Arrays.asList(tmp1));
            ds_ctgg=tmp2;
            RV_ds_sp_ad.addItemDecoration(new tao_khoang_trong_doc(1));
            RV_ds_sp_ad.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            dongMhGgDsSanPhamGg = new dong_mh_gg_ds_san_pham_gg(mang_hinh_giam_gia.this,ds_ctgg);
            RV_ds_sp_ad.setAdapter(dongMhGgDsSanPhamGg);
            RV_ds_sp_ad.setNestedScrollingEnabled(false);
            ds_sp_ad(0);
        }


    }

    public  void ds_sp_ad(int position)
    {
        TV_ma_gg.setText("Nhập mã: " + ds_ct_gg.get(position).getMa_giam_gia());
        TV_lydogiamgia.setText("("+ds_ct_gg.get(position).getLy_do_giam_gia()+")");
        ds_ctgg.clear();
        sp_ap_dung[] tmp1 = gson.fromJson(gson.toJson(ds_ct_gg.get(position).getDs_sp_ap_dung()),sp_ap_dung[].class);
        ArrayList<sp_ap_dung> tmp2 = new ArrayList<sp_ap_dung>(Arrays.asList(tmp1));
        ds_ctgg.addAll(tmp2);
        dongMhGgDsSanPhamGg.notifyDataSetChanged();
        thiet_lap_thoi_gian(ds_ct_gg.get(position).getThoi_diem_bat_dau_giam_gia(), ds_ct_gg.get(position).getThoi_diem_ket_thuc_giam_gia(), position);
    }
    public  void thiet_lap_thoi_gian(final String str_bat_dau, final String str_ket_thuc, final int position){
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
                thiet_lap_thoi_gian(str_bat_dau,  str_ket_thuc, position);
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
                        long giay = (long) (millisUntilFinished / 1000) % 60;
                        long phut = (long) ((millisUntilFinished / (1000 * 60)) % 60);
                        long gio = (long) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                        if (gio != 10 && gio / 10 == 0) TV_gio.setText("0" + gio + "");
                        else TV_gio.setText(gio + "");
                        if (phut != 10 && phut / 10 == 0) TV_phut.setText("0" + phut + "");
                        else TV_phut.setText(phut + "");
                        if (giay != 10 && giay / 10 == 0) TV_giay.setText("0" + giay + "");
                        else TV_giay.setText(giay + "");
                }

                @Override
                public void onFinish() {
                    thiet_lap_thoi_gian(str_bat_dau,  str_ket_thuc, position);
                }
            }.start();
        }
        else if( hien_tai.getTimeInMillis() >   ket_thuc.getTimeInMillis()){
            CDT.cancel();
            TV_thong_bao_thoi_gian.setText("Đã kết thúc");
            ds_ct_gg.get(position).getDs_sp_ap_dung().clear();
            CDT.cancel();
            TV_gio.setText("00");
            TV_phut.setText("00");
            TV_giay.setText("00");
           dongMhGgDsSanPhamGg.notifyDataSetChanged();
        }


    }
    public class tai_chuong_trinh_giam_gia extends AsyncTask<String, Void, ArrayList<chuong_trinh_giam_gia>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_chuong_trinh_giam_gia(String method_DN, String PARAM_DN) {
            if(!isConnected()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected ArrayList<chuong_trinh_giam_gia> doInBackground(String... strings) {
            if(!isConnected()) cancel(true);
            boolean geted = false;
            int recall = 0;
            while (geted==false && recall < 5)
            {
                if(!isConnected()) cancel(true);
                recall++;
                try{
                    if(!isConnected()||isFinishing()) cancel(true);
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
            if(!isConnected()) cancel(true);
            Log.d("sssssssssssssss",gson.toJson(dsctgg));
            ds_ct_gg = dsctgg;
            run();
            super.onPostExecute(dsctgg);
        }
    }

    private  boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(!(networkInfo!=null&&networkInfo.isConnected()))
            Toast.makeText(mang_hinh_giam_gia.this,"Không có mạng!",Toast.LENGTH_SHORT).show();
        return networkInfo!=null&&networkInfo.isConnected();
    }
}
