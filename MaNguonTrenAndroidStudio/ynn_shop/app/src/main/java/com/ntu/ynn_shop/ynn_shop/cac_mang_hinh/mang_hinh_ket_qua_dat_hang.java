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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gui_don_hang.don_hang;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham.tra_ve_tim;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.van_chuyen;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Arrays;

public class mang_hinh_ket_qua_dat_hang extends AppCompatActivity {
    public static don_hang DONHANG;
    Dialog dialog;
    SharedPreferences Luu_tru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_ket_qua_dat_hang);
        Luu_tru =getSharedPreferences("ynn_shop",MODE_PRIVATE);
        TextView tv = (TextView) findViewById(R.id.tv_load);
        Animation a =  AnimationUtils.loadAnimation(this, R.anim.texview);
        tv.startAnimation(a);
        new dat_hang("dat_hang","json").execute(new Gson().toJson(DONHANG));
    }
    @Override
    public void onBackPressed() {
        //getFragmentManager().popBackStack();
    }
    private void dat_hang_that_bai(String chi_tiet) {
        dialog = new Dialog(mang_hinh_ket_qua_dat_hang.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.popup_dat_hang_that_bai);
        dialog.setCancelable(false);
        ((TextView) dialog.findViewById(R.id.tv_ly_do_that_bai)).setText(chi_tiet);
        ((TextView) dialog.findViewById(R.id.tv_chinh_sua_don_hang)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DONHANG = null;
                Intent intent = new Intent(getApplicationContext(), man_hinh_gio_hang.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        lWindowParams.copyFrom(dialog.getWindow().getAttributes());
        lWindowParams.width = WindowManager.LayoutParams.FILL_PARENT; // this is where the magic happens
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lWindowParams);
        dialog.show();
    }
    private void dat_hang_thanh_cong(int ma_don_hang) {
        dialog = new Dialog(mang_hinh_ket_qua_dat_hang.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.popup_dat_hang_thanh_cong);
        dialog.setCancelable(false);
        ((TextView) dialog.findViewById(R.id.tv_mdh)).setText("Mã đơn hàng của bạn là " + ma_don_hang);
        ((TextView) dialog.findViewById(R.id.tv_ttmh)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DONHANG = null;
                man_hinh_gio_hang.ds_san_pham_gh.clear();
                Intent intent = new Intent(getApplicationContext(), mang_hinh_trang_chu.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        lWindowParams.copyFrom(dialog.getWindow().getAttributes());
        lWindowParams.width = WindowManager.LayoutParams.FILL_PARENT; // this is where the magic happens
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lWindowParams);
        dialog.show();
        SharedPreferences.Editor sua=Luu_tru.edit();
        sua.putString("gio_hang", null);
        sua.commit();
    }
    public class dat_hang extends AsyncTask<String, Void, String> {
        private String Method_DN;
        private String PARAM_DN = null;

        public dat_hang(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected String doInBackground(String... strings) {
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
                return data.toString();
            }
            catch (Exception ex)
            {
                Log.e("loi",ex.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(!isConnected()||isFinishing()) cancel(true);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(s.toString());
                if(jsonObject.getBoolean("tinh_trang")==true)
                    dat_hang_thanh_cong(jsonObject.getInt("chi_tiet"));
                else
                    dat_hang_that_bai(jsonObject.getString("chi_tiet"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }
    }
    private  boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(!(networkInfo!=null&&networkInfo.isConnected()))
            Toast.makeText(getApplicationContext(),"Không có mạng!",Toast.LENGTH_SHORT).show();
        return networkInfo!=null&&networkInfo.isConnected();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
        dialog = null;
    }
}
