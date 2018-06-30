package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_gio_hang;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_khoang_trong_doc;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gio_hang.san_pham_gh;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gio_hang.tra_ve_giam_gia;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gio_hang.yeu_cau_giam_gia;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Arrays;

public class man_hinh_gio_hang extends AppCompatActivity {
    static SharedPreferences Luu_tru;
    public static ArrayList<san_pham_gh> ds_san_pham_gh;
    RecyclerView RV_gio_hang;
    static bcd_dong_gio_hang bcdDongGioHang;
    static Gson gson;
    static EditText et_popup_nmgg_nhap;
    static Button bt_popup_nmgg_ap_dung;
    static Context context;
    boolean _blnKeyboard=false;
    static man_hinh_gio_hang mhgh;
    static InputMethodManager inputMethodManager;
    static Dialog dialog;
    static TextView tv_tong_tien;
    static dinh_dang_tien dinhDangTien;
    TextView TV_tien_hanh_thanh_toan;
    Toolbar toolbar;
    TextView tv_tieu_de;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_gio_hang);
        System.gc();
        anh_xa();
        thiet_lap_nhap_ma_giam_gia();
        thiet_lap_tab();
        thiet_lap_thanh_toan();
       if(ds_san_pham_gh!=null)
       {
           bcdDongGioHang  = new bcd_dong_gio_hang(this, ds_san_pham_gh);
           RV_gio_hang.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
           RV_gio_hang.setHasFixedSize(true);
           RV_gio_hang.addItemDecoration(new tao_khoang_trong_doc(1));
           RV_gio_hang.setNestedScrollingEnabled(false);
           RV_gio_hang.setAdapter(bcdDongGioHang);
           tinh_tong_tien();
       }
    }

    private void thiet_lap_thanh_toan() {
        TV_tien_hanh_thanh_toan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ds_san_pham_gh!=null&&ds_san_pham_gh.size()!=0)
                {
                    Intent intent = new Intent(getApplicationContext(), mang_hinh_thanh_toan.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void thiet_lap_nhap_ma_giam_gia(){
        dialog = new Dialog(man_hinh_gio_hang.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.popup_nhap_ma_giam_gia);
        dialog.setCancelable(false);
        et_popup_nmgg_nhap = (EditText) dialog.findViewById(R.id.et_popup_nmgg_nhap);
        bt_popup_nmgg_ap_dung = (Button) dialog.findViewById(R.id.bt_popup_nmgg_ap_dung);
        ((Button) dialog.findViewById(R.id.bt_popup_nmgg_thoi)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
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
//        ((LinearLayout) findViewById(R.id.LL_tab_Gio_hang)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), man_hinh_gio_hang.class);
//                startActivity(intent);
//                finish();
//            }
//        });
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
    @Override
    public void onBackPressed() {
        //getFragmentManager().popBackStack();
    }
    private void anh_xa() {
        if(ds_san_pham_gh==null) ds_san_pham_gh=new ArrayList<>();
        RV_gio_hang = (RecyclerView) findViewById(R.id.RV_gio_hang);
        gson = new Gson();
        context = man_hinh_gio_hang.this;
        mhgh = this;
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        tv_tong_tien = (TextView) findViewById(R.id.tv_tong_tien);
        dinhDangTien = new dinh_dang_tien();
        TV_tien_hanh_thanh_toan = (TextView) findViewById(R.id.TV_tien_hanh_thanh_toan);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tv_tieu_de = (TextView) toolbar.findViewById(R.id.tv_tieu_de);
    }
    public static void tinh_tong_tien(){
        if(ds_san_pham_gh != null && ds_san_pham_gh.size()!=0){
            long tong = 0;
            for(int i = 0; i < ds_san_pham_gh.size(); i++)
                tong+=ds_san_pham_gh.get(i).getGia_da_giam()*ds_san_pham_gh.get(i).getSo_luong();
            tv_tong_tien.setText(dinhDangTien.dinh_dang(tong+""));
        }
        else tv_tong_tien.setText("0 vnđ");

    }
    public static void xu_ly_ma_giam_gia(final int posittion){
        boolean f = false;
        int vt = -1;
        for(int i = 0; i < ds_san_pham_gh.size(); i++)
            if(ds_san_pham_gh.get(i).getMa_giam_gia()!=null)
            {vt = i; f=true; break;}

        if(f==true){
            Toast.makeText(mhgh, "Bạn đã áp dụng mã giảm giá cho sản phẩm nào đó rồi!", Toast.LENGTH_SHORT).show();
        }
        else
        if(ds_san_pham_gh.get(posittion).getSo_luong()!=1) {
                Toast.makeText(mhgh, "Số lượng phải bằng 1 trước khi áp dụng mã giảm giá!", Toast.LENGTH_SHORT).show();
         }
         else {
                    et_popup_nmgg_nhap.setText("");
                    dialog.show();
                    et_popup_nmgg_nhap.requestFocus();
                    inputMethodManager.showSoftInputFromInputMethod(mhgh.getCurrentFocus().getWindowToken(), 0);
                    bt_popup_nmgg_ap_dung.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new kiem_tra_giam_gia("kiem_tra_giam_gia","json", posittion).
                                    execute(new Gson().toJson(new yeu_cau_giam_gia(ds_san_pham_gh.get(posittion).getId_san_pham(),
                                            et_popup_nmgg_nhap.getText().toString().trim())));
                        }
                    });
        }


    }
    public static class kiem_tra_giam_gia extends AsyncTask<String, Void,  ArrayList<tra_ve_giam_gia>> {
        private String Method_DN;
        private String PARAM_DN = null;
        private int posittion_tmp;

        public kiem_tra_giam_gia(String method_DN, String PARAM_DN, int posittion_tmp) {
            if(!isConnected()||mhgh.isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
            this.posittion_tmp = posittion_tmp;
        }

        protected  ArrayList<tra_ve_giam_gia> doInBackground(String... strings) {
            if(!isConnected()||mhgh.isFinishing()) cancel(true);
            boolean geted = false;
            int recall = 0;
            while (geted==false && recall < 5)
            {
                if(!isConnected()||mhgh.isFinishing()) cancel(true);
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
                    tra_ve_giam_gia[] ds_tvgg_tmp = gson.fromJson(data.toString(),tra_ve_giam_gia[].class);
                    ArrayList<tra_ve_giam_gia> ds_tvgg_kq = new ArrayList<>(Arrays.asList(ds_tvgg_tmp));
                    if(!isConnected()||mhgh.isFinishing()) cancel(true);
                    return ds_tvgg_kq;
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
        protected void onPostExecute(ArrayList<tra_ve_giam_gia> tmp) {
            if(!isConnected()||mhgh.isFinishing()) cancel(true);
            if(tmp!=null&&tmp.size()!=0){
                Log.d("adfadsfasdf", new Gson().toJson(tmp));
                dialog.dismiss();
                long tam_tinh_giam = (long) (ds_san_pham_gh.get(posittion_tmp).getGia_san_pham()*(1.0*tmp.get(0).getPhan_tram_giam()/100));
                if(tam_tinh_giam > tmp.get(0).getGiam_toi_da())
                    tam_tinh_giam = tmp.get(0).getGiam_toi_da();
                ds_san_pham_gh.get(posittion_tmp).setGia_da_giam(ds_san_pham_gh.get(posittion_tmp).getGia_san_pham() - tam_tinh_giam);
                ds_san_pham_gh.get(posittion_tmp).setMa_giam_gia(tmp.get(0).getMa_giam_gia());
                Toast.makeText(context, "Chúc mừng! Nhưng bạn phải đặt hàng trưóc " +tmp.get(0).getThoi_diem_ket_thuc_giam_gia(),
                        Toast.LENGTH_SHORT).show();
                inputMethodManager.hideSoftInputFromWindow(mhgh.getCurrentFocus().getWindowToken(), 0);
                bcdDongGioHang.notifyDataSetChanged();
                tinh_tong_tien();
                Luu_tru =context.getSharedPreferences("ynn_shop",MODE_PRIVATE);
                SharedPreferences.Editor sua=Luu_tru.edit();
                sua.putString("gio_hang", gson.toJson(ds_san_pham_gh));
                sua.commit();
            }
            else {
                Toast.makeText(context, "Mã giảm giá đã hết hạn hoặc không đúng!",
                        Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(tmp);
        }
    }
    private static boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) mhgh.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(!(networkInfo!=null&&networkInfo.isConnected()))
            Toast.makeText(mhgh.getApplicationContext(),"Không có mạng!",Toast.LENGTH_SHORT).show();
        return networkInfo!=null&&networkInfo.isConnected();
    }

}
