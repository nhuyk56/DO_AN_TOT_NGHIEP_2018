package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.khoi_phuc_mat_khau;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class quen_mat_khau extends AppCompatActivity {
    Toolbar toolbar;
    EditText et_email, et_code, et_mkm, et_mkm_nl;
    TextView TV_khoi_phuc, TV_xac_nhan, TV_thoi;
    Dialog Popup_khoi_phuc_mat_khau;
    khoi_phuc_mat_khau khoiPhucMatKhau;
    ma_hoa_base64 ma_hoa;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        anh_xa();
        thiet_lap_toolbar();
    }

    private void thiet_lap_toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void anh_xa() {
        gson = new Gson();
        ma_hoa = new ma_hoa_base64();
        khoiPhucMatKhau = new khoi_phuc_mat_khau();
        et_email = (EditText) findViewById(R.id.et_email) ;
        TV_khoi_phuc = (TextView) findViewById(R.id.TV_khoi_phuc) ;
        TV_khoi_phuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kiem_tra_email())
                {
                    new gui_mail("tao_code","email")
                            .execute(et_email.getText().toString().trim());
                }
            }
        });
        Popup_khoi_phuc_mat_khau = new Dialog(this);
        Popup_khoi_phuc_mat_khau.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Popup_khoi_phuc_mat_khau.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Popup_khoi_phuc_mat_khau.setContentView(R.layout.popup_khoi_phuc_mat_khau);
        Popup_khoi_phuc_mat_khau.setCancelable(false);
        // , et_mkm, et_mkm_nl
        et_code = (EditText)  Popup_khoi_phuc_mat_khau.findViewById(R.id.et_code);
        et_mkm = (EditText)  Popup_khoi_phuc_mat_khau.findViewById(R.id.et_mkm);
        et_mkm_nl = (EditText)  Popup_khoi_phuc_mat_khau.findViewById(R.id.et_mkm_nl);
        TV_xac_nhan = (TextView)  Popup_khoi_phuc_mat_khau.findViewById(R.id.TV_xac_nhan);
        TV_thoi = (TextView ) Popup_khoi_phuc_mat_khau.findViewById(R.id.TV_thoi);
        TV_xac_nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kiem_tra_mat_khau_moi()==true)
                {
                    khoiPhucMatKhau.setKey(et_code.getText().toString().trim());
                    khoiPhucMatKhau.setMat_khau_moi(ma_hoa.ma_hoa(et_mkm.getText().toString().trim()));
                    Log.d("564564", gson.toJson(khoiPhucMatKhau));
                    new khoi_phuc("khoi_phuc_mat_khau", "json")
                            .execute(gson.toJson(khoiPhucMatKhau));
                }
            }
        });
        TV_thoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popup_khoi_phuc_mat_khau.dismiss();
            }
        });
    }
    private boolean kiem_tra_mat_khau_moi(){
        if(et_mkm.getText().length() < 8) {
            Toast.makeText(getApplicationContext(), "Mật khẩu phải hơn 7 kí tự!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        if(Objects.equals(et_mkm.getText().toString().trim(),et_mkm_nl.getText().toString().trim() ) == false) {
            Toast.makeText(getApplicationContext(), "Mật khẩu không khớp!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        return true;
    }
    private boolean kiem_tra_email() {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(et_email.getText().toString().trim());
        if (matcher.matches() == false) {
            Toast.makeText(getApplicationContext(), "Email sai định dạng!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    public class gui_mail extends AsyncTask<String, Void, String> {
        private String Method_DN;
        private String PARAM_DN = null;

        public gui_mail(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }

        protected String doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            try{
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
                Toast.makeText(quen_mat_khau.this,jsonObject.getString("chi_tiet"),
                        Toast.LENGTH_SHORT).show();
                if(jsonObject.getBoolean("tinh_trang")==true) {
                    khoiPhucMatKhau.setEmail(et_email.getText().toString().trim());
                    Popup_khoi_phuc_mat_khau.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(s);
        }
    }
    public class khoi_phuc extends AsyncTask<String, Void, String> {
        private String Method_DN;
        private String PARAM_DN = null;

        public khoi_phuc(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }

        protected String doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            try{
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
                Toast.makeText(quen_mat_khau.this,jsonObject.getString("chi_tiet"),
                        Toast.LENGTH_SHORT).show();
                if(jsonObject.getBoolean("tinh_trang")==true) {
                    Popup_khoi_phuc_mat_khau.dismiss();
                    finish();
                }
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
    public void onBackPressed() {
        //getFragmentManager().popBackStack();
    }
}
