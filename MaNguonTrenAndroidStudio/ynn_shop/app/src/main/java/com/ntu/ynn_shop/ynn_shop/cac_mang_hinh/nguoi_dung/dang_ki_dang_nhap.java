package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.nguoi_dung;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeNoticeDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeWarningDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.luu_yeu_cau_chuoi;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.dang_ki.dang_nhap;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.dang_ki.nguoi;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gio_hang.san_pham_gh;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.loai_sp;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.man_hinh_gio_hang;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.mang_hinh_ds_san_pham;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.mang_hinh_menu;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.mang_hinh_trang_chu;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.quen_mat_khau;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kotlin.jvm.internal.PropertyReference0Impl;

public class dang_ki_dang_nhap extends AppCompatActivity {
    SharedPreferences Luu_tru;
    private ma_hoa_base64 ma_hoa;
    LinearLayout LL_Dang_Nhap;
    RelativeLayout RL_TAB_DANG_KI, RL_TAB_DANG_NHAP;
    LinearLayout LL_Dang_Ki;
    TextView TV_DN;
    TextView TV_DN_Line;
    TextView TV_DK;
    TextView TV_DK_Line;
    Dialog awesomeInfoDialog;
    Gson  gson;
    //đăng kí
    EditText et_ho_ten,et_dia_chi_mail, et_mat_khau, et_mat_khau_2,et_dia_chi,et_sdt, et_ngay_sinh;
    RadioButton rb_gioi_tinh_nam, rb_gioi_tinh_nu;
    TextView bt_dang_ki;
    //đăng nhập
    EditText et_email_dn, et_mk_dn;
    TextView tv_quen_mat_khau, tv_dang_nhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki_dang_nhap);
        Luu_tru =getSharedPreferences("ynn_shop",MODE_PRIVATE);
        String gio_hang = Luu_tru.getString("gio_hang", null);
        if(gio_hang!=null)
        {
            gson=new Gson();
            san_pham_gh[] sp = new san_pham_gh[0];
            sp = gson.fromJson(gio_hang,san_pham_gh[].class);
            ArrayList<san_pham_gh> splist = new ArrayList<san_pham_gh>(Arrays.asList(sp));
            man_hinh_gio_hang.ds_san_pham_gh = splist;
        }
        int dang_nhap  = Luu_tru.getInt("id_nguoi", 0);
        if(dang_nhap!=0)
        {
            Intent intent = new Intent(dang_ki_dang_nhap.this,mang_hinh_trang_chu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        thiet_lap_thong_bao();
        thiet_lap();
        Anh_Xa();
        thiet_lap_nut_ngay_sinh();
        demo_du_lieu_nhap();
        //Đăng nhập
    }
    private void dang_nhap()
    {
        if(kiem_tra_dang_nhap()==true)
        {
            dang_nhap dang_nhap = new dang_nhap(
                    et_email_dn.getText().toString(),
                   ma_hoa.ma_hoa(et_mk_dn.getText().toString())
            );
            new asynctask_dang_nhap("dang_nhap_nguoi_dung","json")
                    .execute(gson.toJson(dang_nhap));
        }
    }

    private boolean kiem_tra_dang_nhap(){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(et_email_dn.getText());
        if(matcher.matches()==false) {
            Toast.makeText(getApplicationContext(), "Email sai định dạng!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        if(et_mk_dn.getText().length() < 8) {
            Toast.makeText(getApplicationContext(), "Mật khẩu phải hơn 7 kí tự!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        return true;
    }
    private void demo_du_lieu_nhap() {
        et_ho_ten.setText("Nguyễn Như Ý");
        et_dia_chi_mail.setText("nhuyk56@gmaiul.com");
        et_mat_khau.setText("123456789");
        et_mat_khau_2.setText("123456789");
        et_ngay_sinh.setText("2018-05-11");
        et_dia_chi.setText("Thôn Cửu Lợi - Xã Cam Hòa - Huyện Cam Lâm - Tỉnh Khánh Hòa");
        et_sdt.setText("01672191286");
    }
    private void thiet_lap_thong_bao(){
        awesomeInfoDialog = new AwesomeProgressDialog(this)
                .setTitle("ĐANG XỬ LÝ")
                .setMessage("Xin chờ trong giây lát!")
                .setCancelable(false)
                .show();
        awesomeInfoDialog.dismiss();
    }

    private void thiet_lap_dang_ki() {

        if(kiem_tra_du_lieu()==true)
        {
            boolean gioi_tinh;
            if(rb_gioi_tinh_nam.isChecked()==true) gioi_tinh=true;
            else gioi_tinh=false;
            nguoi nguoi = new nguoi(
              et_dia_chi_mail.getText().toString(),
              ma_hoa.ma_hoa(et_mat_khau.getText().toString()),
              et_ho_ten.getText().toString(),
              et_ngay_sinh.getText().toString(),
              et_dia_chi.getText().toString(),
              et_sdt.getText().toString(),
              gioi_tinh
            );
            new asynctask_dang_ki("dang_ki_nguoi_dung", "json")
                    .execute(gson.toJson(nguoi));
        }
    }

    @Override
    public void onBackPressed() {
        //getFragmentManager().popBackStack();
    }
    private boolean kiem_tra_du_lieu(){
        if(et_ho_ten.getText().length() < 7) {
            Toast.makeText(getApplicationContext(), "Họ và tên phải lớn hơn 7 kí tự!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(et_dia_chi_mail.getText());
        if(matcher.matches()==false) {
            Toast.makeText(getApplicationContext(), "Email sai định dạng!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        if(et_mat_khau.getText().length() < 8) {
            Toast.makeText(getApplicationContext(), "Mật khẩu phải hơn 7 kí tự!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        if(Objects.equals(et_mat_khau.getText().toString().trim(),et_mat_khau_2.getText().toString().trim() ) == false) {
            Toast.makeText(getApplicationContext(), "Mật khẩu không khớp!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        if(et_ngay_sinh.getText().length() != 10) {
            Toast.makeText(getApplicationContext(), "Sai ngày sinh!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        if(et_dia_chi.getText().length() < 10) {
            Toast.makeText(getApplicationContext(), "Địa chỉ phải lớn hơn 9 kí tự!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        if(et_sdt.getText().length() < 10){
            Toast.makeText(getApplicationContext(), "Số điện thoại phải hơn 9 kí tự!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        return true;
    }


    private void Anh_Xa() {
        gson = new Gson();
        ma_hoa = new ma_hoa_base64();
//        EditText et_ho_ten,et_dia_chi_mail, et_mat_khau, et_mat_khau_2,et_dia_chi,et_sdt;
//        RadioButton rb_gioi_tinh_nam, rb_gioi_tinh_nu;
//        Button bt_ngay_sinh, bt_dang_ki;
//        TextView tv_ngay_sinh;
        et_ho_ten = (EditText) findViewById(R.id.et_ho_ten);
        et_dia_chi_mail = (EditText) findViewById(R.id.et_dia_chi_mail);
        et_mat_khau = (EditText) findViewById(R.id.et_mat_khau);
        et_mat_khau_2 = (EditText) findViewById(R.id.et_mat_khau_2);
        et_dia_chi = (EditText) findViewById(R.id.et_dia_chi);
        et_sdt = (EditText) findViewById(R.id.et_sdt);
        rb_gioi_tinh_nam = (RadioButton) findViewById(R.id.rb_gioi_tinh_nam);
        rb_gioi_tinh_nu = (RadioButton) findViewById(R.id.rb_gioi_tinh_nu);
        et_ngay_sinh = (EditText) findViewById(R.id.et_ngay_sinh);
        et_ngay_sinh.setKeyListener(null);
        bt_dang_ki = (TextView) findViewById(R.id.bt_dang_ki);
        bt_dang_ki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thiet_lap_dang_ki();
            }
        });

        //dn
//        EditText et_email_dn, et_mk_dn;
//        TextView tv_quen_mat_khau, tv_dang_nhap;
        et_email_dn = (EditText) findViewById(R.id.et_email_dn);
        et_mk_dn = (EditText) findViewById(R.id.et_mk_dn);
        tv_quen_mat_khau = (TextView) findViewById(R.id.tv_quen_mat_khau);
        tv_dang_nhap = (TextView) findViewById(R.id.tv_dang_nhap);
        tv_dang_nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dang_nhap();
            }
        });
        tv_quen_mat_khau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dang_ki_dang_nhap.this,quen_mat_khau.class);
                startActivity(intent);
            }
        });
    }
    private void thiet_lap_nut_ngay_sinh(){
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        final DatePickerDialog DPD = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                et_ngay_sinh.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);

        et_ngay_sinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DPD.show();
            }
        });

    }
    private void thiet_lap(){
         RL_TAB_DANG_NHAP = (RelativeLayout) findViewById(R.id.RL_TAB_DANG_NHAP);
         RL_TAB_DANG_KI = (RelativeLayout) findViewById(R.id.RL_TAB_DANG_KI);
         LL_Dang_Nhap = (LinearLayout) findViewById(R.id.LL_Dang_Nhap);
         LL_Dang_Ki = (LinearLayout) findViewById(R.id.LL_Dang_Ki);
         TV_DN = (TextView) findViewById(R.id.TV_DN) ;
         TV_DN_Line = (TextView) findViewById(R.id.TV_DN_Line) ;
         TV_DK = (TextView) findViewById(R.id.TV_DK) ;
         TV_DK_Line = (TextView) findViewById(R.id.TV_DK_Line) ;
         //Thiết lập ban đầu
        {
            dis(TV_DN, TV_DN_Line);
            dis(TV_DK, TV_DK_Line);
            en(TV_DN, TV_DN_Line);
            RL_TAB_DANG_NHAP.setVisibility(View.INVISIBLE);
            RL_TAB_DANG_KI.setVisibility(View.INVISIBLE);
            RL_TAB_DANG_NHAP.setVisibility(View.VISIBLE);
        }
         LL_Dang_Nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dis(TV_DN, TV_DN_Line);
                dis(TV_DK, TV_DK_Line);
                en(TV_DN, TV_DN_Line);
                RL_TAB_DANG_NHAP.setVisibility(View.INVISIBLE);
                RL_TAB_DANG_KI.setVisibility(View.INVISIBLE);
                RL_TAB_DANG_NHAP.setVisibility(View.VISIBLE);
            }
         });
         LL_Dang_Ki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dis(TV_DN, TV_DN_Line);
                dis(TV_DK, TV_DK_Line);
                en(TV_DK, TV_DK_Line);
                RL_TAB_DANG_NHAP.setVisibility(View.INVISIBLE);
                RL_TAB_DANG_KI.setVisibility(View.INVISIBLE);
                RL_TAB_DANG_KI.setVisibility(View.VISIBLE);
            }
         });
    }
    //thiết lập tab
    private void en(TextView a, TextView b){
        a.setTextColor(Color.parseColor("#f57224"));
        b.setBackgroundColor(Color.parseColor("#f57224"));
    }
    private void dis(TextView a, TextView b){
        a.setTextColor(Color.parseColor("#9e9e9e"));
        b.setBackgroundColor(Color.parseColor("#ffffff"));
    }
    private void thong_bao(int mode, String Message ){
        if(mode==2)//thanh cong
        {
            new AwesomeSuccessDialog(this)
                    .setTitle("Thành Công")
                    .setMessage(Message)
                    .setColoredCircle(R.color.dialogSuccessBackgroundColor)
                    .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                    .setCancelable(true)
                    .setPositiveButtonText("Ok!")
                    .setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
                    .setPositiveButtonTextColor(R.color.white)
                    .setPositiveButtonClick(new Closure() {
                        @Override
                        public void exec() {
                            dis(TV_DN, TV_DN_Line);
                            dis(TV_DK, TV_DK_Line);
                            en(TV_DN, TV_DN_Line);
                            RL_TAB_DANG_NHAP.setVisibility(View.INVISIBLE);
                            RL_TAB_DANG_KI.setVisibility(View.INVISIBLE);
                            RL_TAB_DANG_NHAP.setVisibility(View.VISIBLE);
                            et_email_dn.setText(et_dia_chi_mail.getText().toString());
                            et_ho_ten.setText("");
                            et_dia_chi_mail.setText("");
                            et_mat_khau.setText("");
                            et_mat_khau_2.setText("");
                            et_ngay_sinh.setText("");
                            et_dia_chi.setText("");
                            et_sdt.setText("");
                        }
                    })
                    .show();
        }
        if(mode==1)//canh bao
        {
            new AwesomeWarningDialog(this)
                    .setTitle("Cảnh Báo")
                    .setMessage(Message)
                    .setColoredCircle(R.color.dialogNoticeBackgroundColor)
                    .setDialogIconAndColor(R.drawable.ic_notice, R.color.white)
                    .setCancelable(true)
                    .setButtonText("Thử lại!")
                    .setButtonBackgroundColor(R.color.dialogNoticeBackgroundColor)
                    .setButtonText(getString(R.string.dialog_ok_button))
                    .setWarningButtonClick(new Closure() {
                        @Override
                        public void exec() {
                            // click
                        }
                    })
                    .show();
        }
        if(mode==0){
            new AwesomeErrorDialog(this)
                    .setTitle("Đã Xảy Ra Lỗi")
                    .setMessage(Message)
                    .setColoredCircle(R.color.dialogErrorBackgroundColor)
                    .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                    .setCancelable(true).setButtonText(getString(R.string.dialog_ok_button))
                    .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                    .setButtonText("Thử lại!")
                    .setErrorButtonClick(new Closure() {
                        @Override
                        public void exec() {
                        }
                    })
                    .show();
        }
    }
    public class asynctask_dang_ki extends AsyncTask<String, Void, String> {
        private String Method_DN;
        private String PARAM_DN = null;

        public asynctask_dang_ki(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }

        @Override
        protected void onPreExecute() {
            if(!isConnected()||isFinishing()) cancel(true);
            awesomeInfoDialog.show();
            super.onPreExecute();
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
        protected void onPostExecute(String kq) {
            if(!isConnected()||isFinishing()) cancel(true);
            awesomeInfoDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(kq);
                if(jsonObject.getBoolean("tinh_trang")==false)
                    thong_bao(0,jsonObject.getString("chi_tiet"));
                else  thong_bao(2,jsonObject.getString("chi_tiet"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(kq);
        }
    }
    public class asynctask_dang_nhap extends AsyncTask<String, Void, String> {
        private String Method_DN;
        private String PARAM_DN = null;

        public asynctask_dang_nhap(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }

        @Override
        protected void onPreExecute() {
            if(!isConnected()||isFinishing()) cancel(true);
            awesomeInfoDialog.show();
            super.onPreExecute();
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
        protected void onPostExecute(String kq) {
            if(!isConnected()||isFinishing()) cancel(true);
            Log.d("du_lieudu_lieu", kq);
            awesomeInfoDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(kq);
                if(jsonObject.getBoolean("tinh_trang")==false)
                    thong_bao(0,jsonObject.getString("chi_tiet"));
                else
                {
                    Log.d("du_lieudu_lieu",jsonObject.getInt("du_lieu")+"" );
                    SharedPreferences.Editor sua=Luu_tru.edit();
                    sua.putInt("id_nguoi", jsonObject.getInt("du_lieu"));
                    sua.commit();
                    new AwesomeSuccessDialog(dang_ki_dang_nhap.this)
                            .setTitle("Thành Công")
                            .setMessage(jsonObject.getString("chi_tiet"))
                            .setColoredCircle(R.color.dialogSuccessBackgroundColor)
                            .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                            .setCancelable(true)
                            .setPositiveButtonText("Ok!")
                            .setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
                            .setPositiveButtonTextColor(R.color.white)
                            .setPositiveButtonClick(new Closure() {
                                @Override
                                public void exec() {
                                    Intent intent = new Intent(dang_ki_dang_nhap.this,mang_hinh_trang_chu.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .show();
                }

            } catch (JSONException e) {
                Log.d("loi", e.toString());
                e.printStackTrace();
            }

            super.onPostExecute(kq);
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
