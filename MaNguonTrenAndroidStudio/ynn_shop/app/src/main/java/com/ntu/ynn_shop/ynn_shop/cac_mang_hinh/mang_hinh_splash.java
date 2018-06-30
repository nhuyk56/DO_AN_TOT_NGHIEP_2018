package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BlurMaskFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.json2_loai_san_pham_tat_ca;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.loai_san_pham_cap_0;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.nguoi_dung.dang_ki_dang_nhap;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class mang_hinh_splash extends AppCompatActivity {
    SharedPreferences Luu_tru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_splash);
        ImageView tv = (ImageView) findViewById(R.id.icon_splash);
        Animation a =  AnimationUtils.loadAnimation(this, R.anim.texview);
        tv.startAnimation(a);
        Luu_tru =getSharedPreferences("ynn_shop",MODE_PRIVATE);
        new asynctask_tai_xuly_load("Lay_Du_Lieu_Loai_San_Pham",null).execute("");
    }
    public class asynctask_tai_xuly_load extends AsyncTask<String, Void, String> {
        private String Method_DN;
        private String PARAM_DN = null;

        public asynctask_tai_xuly_load(String method_DN, String PARAM_DN) {
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
            if(!isConnected()||isFinishing()) cancel(true);
        }

        protected String doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            boolean geted = false;
            int recall = 0;
            while (geted==false && recall < 5)
            {
                if(!isConnected()||isFinishing()) cancel(true);
                recall++;
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
                    return data.toString();
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
        protected void onPostExecute(String s) {
            if(!isConnected()||isFinishing()) cancel(true);
            SharedPreferences.Editor sua=Luu_tru.edit();
            sua.putString("menu", s);
            sua.commit();
            Intent intent = new Intent(mang_hinh_splash.this,dang_ki_dang_nhap.class);
            startActivity(intent);
            finish();
//            danh_sach_lsp_c0=loai_san_pham_cap_0s;
//            khoi_tao_menu_c0();
//            cai_dat_mang_hinh(true,false);
//            khoi_tao_menu_chi_danh_cho_ban();
//            awesomeInfoDialog.dismiss();
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
