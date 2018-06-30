package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.dang_ki.doi_mat_khau;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.dang_ki.nguoi;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thong_tin_nguoi_dung_thanh_toan;
import com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.nguoi_dung.dang_ki_dang_nhap;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;

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

import static android.content.Context.MODE_PRIVATE;

public class Fragment_thong_tin_tai_khoan extends Fragment {
    SharedPreferences Luu_tru;
    EditText et_ho_ten,et_dia_chi_mail, et_mat_khau,et_dia_chi,et_sdt, et_ngay_sinh;
    RadioButton rb_gioi_tinh_nam, rb_gioi_tinh_nu;
    Button bt_dang_ki, bt_thoat;
    View view;
    nguoi nguoi_dung;
    RelativeLayout RL_sua;
    private ma_hoa_base64 ma_hoa;
    Dialog dialog;
    RelativeLayout RL_QL;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_fragment_thong_tin_tai_khoan, container,false);
        anh_xa();
        thiet_lap_doi_mat_khau();
        thiet_lap_nut_ngay_sinh();
        new tai_tt_nguoi("nguoi_xem","id_nguoi").execute(Luu_tru.getInt("id_nguoi", 12001459)+"");
        return view;
    }
    void thiet_lap_doi_mat_khau(){
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.popup_sua_mat_khau);
        dialog.setCancelable(false);
        et_mat_khau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        ((Button) dialog.findViewById(R.id.bt_thoi)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) dialog.findViewById(R.id.et_mat_khau_cu)).setText("");
                ((EditText) dialog.findViewById(R.id.et_mat_khau)).setText("");
                ((EditText) dialog.findViewById(R.id.et_mat_khau_2)).setText("");
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.bt_ap_dung)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String mkc=((EditText) dialog.findViewById(R.id.et_mat_khau_cu)).getText().toString().trim();
                 String mkm=((EditText) dialog.findViewById(R.id.et_mat_khau)).getText().toString().trim();
                 String mkm2=((EditText) dialog.findViewById(R.id.et_mat_khau_2)).getText().toString().trim();
                Log.d("aaaaaaaaaaaaadf",new Gson().toJson(new doi_mat_khau(
                        nguoi_dung.getEmail_nguoi(),
                        ma_hoa.ma_hoa(mkc),
                        ma_hoa.ma_hoa(mkm)
                ) ));
                if(kiem_tra_mat_khau(mkm, mkm2))
                new doi_mat_khau_nguoi("nguoi_doi_mat_khau", "json")
                        .execute(new Gson().toJson(new doi_mat_khau(
                                nguoi_dung.getEmail_nguoi(),
                                ma_hoa.ma_hoa(mkc),
                                ma_hoa.ma_hoa(mkm)
                        )));
            }
        });

    }

    public boolean kiem_tra_mat_khau(String mkm, String mkm2)
    {
        if(mkm.length() < 8) {
            Toast.makeText(getActivity(), "Mật khẩu phải hơn 7 kí tự!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        if(Objects.equals(mkm,mkm2) == false) {
            Toast.makeText(getActivity(),"Mật khẩu mới và mật khẩu mới nhập lại không trùng nhau", Toast.LENGTH_SHORT).show();
            return  false;
        }
       if(mkm.contains("\\"))
       {
           Toast.makeText(getActivity(),"Mật khẩu không được chứa dấu \\", Toast.LENGTH_SHORT).show();
           return  false;
       }
        if(mkm.contains("'"))
        {
            Toast.makeText(getActivity(),"Mật khẩu không được chứa dấu '", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(mkm.contains("/"))  {
            {
                Toast.makeText(getActivity(),"Mật khẩu không được chứa dấu /", Toast.LENGTH_SHORT).show();
                return  false;
            }
        }
       return true;
    }
    private void thiet_lap_xem() {
        et_ho_ten.setEnabled(false);
        et_dia_chi_mail.setEnabled(false);
        et_mat_khau.setEnabled(false);
        et_dia_chi.setEnabled(false);
        et_sdt.setEnabled(false);
        rb_gioi_tinh_nam.setEnabled(false);
        rb_gioi_tinh_nu.setEnabled(false);
        et_ngay_sinh.setEnabled(false);
        //et_ngay_sinh.setOnClickListener(null);
        et_ngay_sinh.setKeyListener(null);
        bt_dang_ki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thiet_lap_sua();
            }
        });
        et_ho_ten.setText(nguoi_dung.getTen_nguoi());
        et_dia_chi_mail.setText(nguoi_dung.getEmail_nguoi());
        et_mat_khau.setText(nguoi_dung.getMat_khau_nguoi());
        et_dia_chi.setText(nguoi_dung.getDia_chi_nguoi());
        et_sdt.setText(nguoi_dung.getSdt_nguoi());
        if(nguoi_dung.gioi_tinh_nguoi)
        {
            rb_gioi_tinh_nam.setChecked(true);
            rb_gioi_tinh_nu.setChecked(false);
        }
        else {
            rb_gioi_tinh_nam.setChecked(false);
            rb_gioi_tinh_nu.setChecked(true);
        }
        rb_gioi_tinh_nam.setEnabled(false);
        rb_gioi_tinh_nu.setEnabled(false);
        et_ngay_sinh.setText(nguoi_dung.getNgay_sinh());
        et_ngay_sinh.setKeyListener(null);
        RL_sua.setVisibility(View.GONE);
        RL_QL.setVisibility(View.VISIBLE);
    }

    private void thiet_lap_sua() {
        RL_QL.setVisibility(View.GONE);
        RL_sua.setVisibility(View.VISIBLE);
        et_ho_ten.setEnabled(true);
        et_dia_chi_mail.setEnabled(false);
        et_mat_khau.setEnabled(true);
        et_mat_khau.setKeyListener(null);
        et_ngay_sinh.setKeyListener(null);
        et_dia_chi.setEnabled(true);
        et_sdt.setEnabled(true);
        rb_gioi_tinh_nam.setEnabled(true);
        rb_gioi_tinh_nu.setEnabled(true);
        et_ngay_sinh.setEnabled(true);
        et_ngay_sinh.setKeyListener(null);
        ((Button) view.findViewById(R.id.bt_thoi)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thiet_lap_xem();
            }
        });
        ((Button) view.findViewById(R.id.bt_ap_dung)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sua();
            }
        });
    }
    private void sua() {
        if (kiem_tra_du_lieu() == true) {
            boolean gioi_tinh;
            if (rb_gioi_tinh_nam.isChecked() == true) gioi_tinh = true;
            else gioi_tinh = false;
            nguoi nguoi = new nguoi(
                    et_dia_chi_mail.getText().toString(),
                    "******",
                    et_ho_ten.getText().toString(),
                    et_ngay_sinh.getText().toString(),
                    et_dia_chi.getText().toString(),
                    et_sdt.getText().toString(),
                    gioi_tinh
            );
             new sua_tt_nguoi("nguoi_sua", "json").execute(new Gson().toJson(nguoi));
        }
    }
    private boolean kiem_tra_du_lieu(){
        if(et_ho_ten.getText().length() < 7) {
            Toast.makeText(getActivity(), "Họ và tên phải lớn hơn 7 kí tự!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        if(et_ngay_sinh.getText().length() != 10) {
            Toast.makeText(getActivity(), "Sai ngày sinh!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        if(et_dia_chi.getText().length() < 10) {
            Toast.makeText(getActivity(), "Địa chỉ phải lớn hơn 9 kí tự!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        if(et_sdt.getText().length() < 10){
            Toast.makeText(getActivity(), "Số điện thoại phải hơn 9 kí tự!",
                    Toast.LENGTH_LONG).show();
            return  false;
        }
        return true;
    }
    private void thiet_lap_nut_ngay_sinh(){
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        final DatePickerDialog DPD = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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

    void anh_xa(){
        ma_hoa = new ma_hoa_base64();
        Luu_tru =getActivity().getSharedPreferences("ynn_shop",MODE_PRIVATE);
        et_ho_ten = (EditText) view.findViewById(R.id.et_ho_ten);
        et_dia_chi_mail = (EditText) view.findViewById(R.id.et_dia_chi_mail);
        et_mat_khau = (EditText) view.findViewById(R.id.et_mat_khau);
        et_dia_chi = (EditText) view.findViewById(R.id.et_dia_chi);
        et_sdt = (EditText) view.findViewById(R.id.et_sdt);
        rb_gioi_tinh_nam = (RadioButton) view.findViewById(R.id.rb_gioi_tinh_nam);
        rb_gioi_tinh_nu = (RadioButton) view.findViewById(R.id.rb_gioi_tinh_nu);
        et_ngay_sinh = (EditText) view.findViewById(R.id.et_ngay_sinh);
        et_ngay_sinh.setKeyListener(null);
        bt_dang_ki = (Button) view.findViewById(R.id.bt_dang_ki);
        bt_thoat = (Button) view.findViewById(R.id.bt_thoat);
        RL_sua = (RelativeLayout) view.findViewById(R.id.RL_sua);
        RL_QL = (RelativeLayout) view.findViewById(R.id.RL_QL);
        bt_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor sua=Luu_tru.edit();
                sua.remove("gio_hang");
                sua.remove("id_nguoi");
                sua.apply();
                Intent intent = new Intent(getActivity(), dang_ki_dang_nhap.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
    }
    public class tai_tt_nguoi extends AsyncTask<String, Void, nguoi> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_tt_nguoi(String method_DN, String PARAM_DN) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected nguoi doInBackground(String... strings) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            try{
                if(!isConnected()||getActivity().isFinishing()) cancel(true);
                String input=strings[0];
                SoapObject requests = new SoapObject(thiet_lap_server.NAME_SPACE, Method_DN);
                if(PARAM_DN != null)
                    requests.addProperty(PARAM_DN, input);
                SoapSerializationEnvelope envelope  = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=true;
                envelope.setOutputSoapObject(requests);
                HttpTransportSE httpTransportSE = new HttpTransportSE(thiet_lap_server.SERVER_URL, 800000);
                httpTransportSE.call(thiet_lap_server.NAME_SPACE+Method_DN, envelope);
                SoapPrimitive data = (SoapPrimitive) envelope.getResponse();
//                thong_tin_nguoi_dung_thanh_toan
                nguoi nguoi_tmp = new Gson().fromJson(data.toString(),nguoi.class);
                if(!isConnected()||getActivity().isFinishing()) cancel(true);
                return nguoi_tmp;
            }
            catch (Exception ex)
            {
                Log.e("loi",ex.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(nguoi s) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            if(s!=null)
            {
                nguoi_dung=s;
                thiet_lap_xem();
                ((TextView) getActivity().findViewById(R.id.toolbar).findViewById(R.id.tv_tieu_de)).setText("Xin chào " + s.getTen_nguoi());
            }
            super.onPostExecute(s);
        }
    }
    public class sua_tt_nguoi extends AsyncTask<String, Void, String> {
        private String Method_DN;
        private String PARAM_DN = null;

        public sua_tt_nguoi(String method_DN, String PARAM_DN) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected String doInBackground(String... strings) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            try{
                if(!isConnected()||getActivity().isFinishing()) cancel(true);
                String input=strings[0];
                SoapObject requests = new SoapObject(thiet_lap_server.NAME_SPACE, Method_DN);
                if(PARAM_DN != null)
                    requests.addProperty(PARAM_DN, input);
                SoapSerializationEnvelope envelope  = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=true;
                envelope.setOutputSoapObject(requests);
                HttpTransportSE httpTransportSE = new HttpTransportSE(thiet_lap_server.SERVER_URL, 800000);
                httpTransportSE.call(thiet_lap_server.NAME_SPACE+Method_DN, envelope);
                SoapPrimitive data = (SoapPrimitive) envelope.getResponse();
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
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(s);
                if(jsonObject.getBoolean("tinh_trang")==true)
                {
                    Toast.makeText(getActivity(),jsonObject.getString("chi_tiet"), Toast.LENGTH_SHORT).show();
                    new tai_tt_nguoi("nguoi_xem","id_nguoi").execute(Luu_tru.getInt("id_nguoi", 12001459)+"");
                }
                else {
                    Toast.makeText(getActivity(),jsonObject.getString("chi_tiet"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(s);
        }
    }
    public class doi_mat_khau_nguoi extends AsyncTask<String, Void, String> {
        private String Method_DN;
        private String PARAM_DN = null;

        public doi_mat_khau_nguoi(String method_DN, String PARAM_DN) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected String doInBackground(String... strings) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            try{
                if(!isConnected()||getActivity().isFinishing()) cancel(true);
                String input=strings[0];
                SoapObject requests = new SoapObject(thiet_lap_server.NAME_SPACE, Method_DN);
                if(PARAM_DN != null)
                    requests.addProperty(PARAM_DN, input);
                SoapSerializationEnvelope envelope  = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=true;
                envelope.setOutputSoapObject(requests);
                HttpTransportSE httpTransportSE = new HttpTransportSE(thiet_lap_server.SERVER_URL, 800000);
                httpTransportSE.call(thiet_lap_server.NAME_SPACE+Method_DN, envelope);
                SoapPrimitive data = (SoapPrimitive) envelope.getResponse();
                if(!isConnected()||getActivity().isFinishing()) cancel(true);
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
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(s);
                if(jsonObject.getBoolean("tinh_trang")==true)
                {
                    Toast.makeText(getActivity(),jsonObject.getString("chi_tiet"), Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor sua=Luu_tru.edit();
                    sua.remove("id_nguoi");
                    sua.apply();
                    dialog.dismiss();
                    Intent intent = new Intent(getActivity(), dang_ki_dang_nhap.class);
                    getActivity().startActivity(intent);
                    getActivity().finish();
                }
                else {
                    Toast.makeText(getActivity(),jsonObject.getString("chi_tiet"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(s);
        }
    }
    private  boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(!(networkInfo!=null&&networkInfo.isConnected()))
            Toast.makeText(getActivity(),"Không có mạng!",Toast.LENGTH_SHORT).show();
        return networkInfo!=null&&networkInfo.isConnected();
    }
}
