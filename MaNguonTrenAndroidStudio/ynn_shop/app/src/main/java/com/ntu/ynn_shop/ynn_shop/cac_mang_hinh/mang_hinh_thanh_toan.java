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
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gui_don_hang.don_hang;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gui_don_hang.san_pham_don_hang;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.khu_vuc;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thong_tin_nguoi_dung_thanh_toan;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.loai_sp;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Arrays;

import static com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.man_hinh_gio_hang.ds_san_pham_gh;
import static com.ntu.ynn_shop.ynn_shop.cac_mang_hinh.mang_hinh_ket_qua_dat_hang.DONHANG;

public class mang_hinh_thanh_toan extends AppCompatActivity {
    SharedPreferences Luu_tru;
    Dialog dialog_tinh_thanh_pho;
    Dialog dialog_quan_huyen;
    thong_tin_nguoi_dung_thanh_toan thongTinNguoiDungThanhToan;
    Gson gson;
    EditText et_ho_ten_tt, et_email_tt, et_sdt_thanh_toan, et_tinh_thanh_pho_tt, et_quan_huyen_tt;
    ListView LV_danh_sach_tinh_thanh, LV_danh_sach_quan_huyen;
    ArrayList<khu_vuc> ds_tinh_thanh;
    ArrayList<khu_vuc> ds_quan_huyen;
    int  tinh_thanh=-1;
    int quan_huyen=-1;
    ArrayList<String> str_ds_tinh_thanh;
    ArrayList<String> str_ds_quan_huyen;
    ArrayAdapter adapter_tinh_thanh;
    ArrayAdapter adapter_quan_huyen;
    TextView tv_xac_nhan_tt, tv_dia_chi_chi_tiet;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_thanh_toan);
        anh_xa();
        thiet_lap_khu_vuc();
        new tai_tt_nd_tt("lay_thong_tin_thanh_toan","id_nguoi").execute(Luu_tru.getInt("id_nguoi", 12001459)+"");
    }
    private void khoi_tao_tinh_thanh() {
        for(int i = 0; i< ds_tinh_thanh.size(); i++)
            str_ds_tinh_thanh.add(ds_tinh_thanh.get(i).getTen_khu_vuc());
        adapter_tinh_thanh = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str_ds_tinh_thanh);
        LV_danh_sach_tinh_thanh.setAdapter(adapter_tinh_thanh);
        LV_danh_sach_tinh_thanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tinh_thanh = position;
                et_tinh_thanh_pho_tt.setText(str_ds_tinh_thanh.get(position));
                dialog_tinh_thanh_pho.dismiss();
                new tai_ds_quan_huyen("lay_danh_sach_dia_chi", "id_khu_vuc_cha").execute(ds_tinh_thanh.get(tinh_thanh).getId_khu_vuc()+"");
            }
        });
    }
    private void khoi_tao_quan_huyen() {
        str_ds_quan_huyen.clear();
        for(int i = 0; i< ds_quan_huyen.size(); i++)
            str_ds_quan_huyen.add(ds_quan_huyen.get(i).getTen_khu_vuc());
        adapter_quan_huyen = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str_ds_quan_huyen);
        LV_danh_sach_quan_huyen.setAdapter(adapter_quan_huyen);
        LV_danh_sach_quan_huyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                quan_huyen = position;
                et_quan_huyen_tt.setText(str_ds_quan_huyen.get(position));
                dialog_quan_huyen.dismiss();
            }
        });
        dialog_quan_huyen.show();
    }
    private void thiet_lap_khu_vuc() {
        dialog_tinh_thanh_pho = new Dialog(mang_hinh_thanh_toan.this);
        dialog_tinh_thanh_pho.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_tinh_thanh_pho.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_tinh_thanh_pho.setContentView(R.layout.popup_dia_chi_thanh_toan);
        dialog_tinh_thanh_pho.setCancelable(false);
        ((TextView) dialog_tinh_thanh_pho.findViewById(R.id.TV_ten_poup)).setText("CHỌN TỈNH/THÀNH PHỐ");
        LV_danh_sach_tinh_thanh = (ListView) dialog_tinh_thanh_pho.findViewById(R.id.LV_danh_sach_khu_vuc);
        dialog_quan_huyen = new Dialog(mang_hinh_thanh_toan.this);
        dialog_quan_huyen.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_quan_huyen.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_quan_huyen.setContentView(R.layout.popup_dia_chi_thanh_toan);
        dialog_quan_huyen.setCancelable(false);
        LV_danh_sach_quan_huyen = (ListView) dialog_quan_huyen.findViewById(R.id.LV_danh_sach_khu_vuc);
        ((TextView) dialog_quan_huyen.findViewById(R.id.TV_ten_poup)).setText("CHỌN QUẬN/HUYỆN");
    }

    private void anh_xa() {
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
        Luu_tru =getSharedPreferences("ynn_shop",MODE_PRIVATE);
        gson = new Gson();
        tv_xac_nhan_tt = (TextView) findViewById(R.id.tv_xac_nhan_tt);
        tv_dia_chi_chi_tiet = (TextView) findViewById(R.id.tv_dia_chi_chi_tiet);
        tv_xac_nhan_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xac_nhan_thanh_toan();
            }
        });
        et_ho_ten_tt = (EditText) findViewById(R.id.et_ho_ten_tt);
        et_email_tt = (EditText) findViewById(R.id.et_email_tt);
        et_sdt_thanh_toan = (EditText) findViewById(R.id.et_sdt_thanh_toan);
        et_tinh_thanh_pho_tt = (EditText)  findViewById(R.id.et_tinh_thanh_pho_tt);
        et_tinh_thanh_pho_tt.setKeyListener(null);
        str_ds_tinh_thanh = new ArrayList<>();
        str_ds_quan_huyen = new ArrayList<>();
        et_tinh_thanh_pho_tt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    if(ds_tinh_thanh!=null && ds_tinh_thanh.size()!=0)
                        dialog_tinh_thanh_pho.show();
            }
        });
        et_tinh_thanh_pho_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ds_tinh_thanh!=null && ds_tinh_thanh.size()!=0)
                    dialog_tinh_thanh_pho.show();
            }
        });
        et_quan_huyen_tt = (EditText) findViewById(R.id.et_quan_huyen_tt);
        et_quan_huyen_tt.setKeyListener(null);
        et_quan_huyen_tt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    if(tinh_thanh==-1)
                        Toast.makeText(mang_hinh_thanh_toan.this,"Xin lỗi! Bạn phải chọn Tỉnh/Thành Phố trước!",
                                Toast.LENGTH_SHORT).show();
                    else
                    new tai_ds_quan_huyen("lay_danh_sach_dia_chi", "id_khu_vuc_cha").execute(ds_tinh_thanh.get(tinh_thanh).getId_khu_vuc()+"");
                }
            }
        });
        et_quan_huyen_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tinh_thanh==-1)
                    Toast.makeText(mang_hinh_thanh_toan.this,"Xin lỗi! Bạn phải chọn Tỉnh/Thành Phố trước!",
                            Toast.LENGTH_SHORT).show();
                else
                    new tai_ds_quan_huyen("lay_danh_sach_dia_chi", "id_khu_vuc_cha").execute(ds_tinh_thanh.get(tinh_thanh).getId_khu_vuc()+"");
            }
        });
    }

    private void xac_nhan_thanh_toan() {
        if(tinh_thanh==-1)
            Toast.makeText(mang_hinh_thanh_toan.this,"Xin lỗi! Bạn phải chọn Tỉnh/Thành Phố trước!",
                    Toast.LENGTH_SHORT).show();
        else if(tv_dia_chi_chi_tiet.getText().toString().trim().length()<1)
            Toast.makeText(mang_hinh_thanh_toan.this,"Xin lỗi! Bạn chưa nhập địa chỉ chi tiết!",
                    Toast.LENGTH_SHORT).show();
        else {
            long tong_tien = 0;
            int tongGram = 0;
            ArrayList<san_pham_don_hang> chi_tiet_don_hang = new ArrayList<>();
            for(int i = 0; i < ds_san_pham_gh.size(); i++)
            {
                tong_tien += ds_san_pham_gh.get(i).getGia_da_giam()*ds_san_pham_gh.get(i).getSo_luong();
                tongGram += ds_san_pham_gh.get(i).getKhoi_luong_san_pham_gram()*ds_san_pham_gh.get(i).getSo_luong();
                chi_tiet_don_hang.add(
                        new san_pham_don_hang(
                        ds_san_pham_gh.get(i).getId_san_pham(),
                        ds_san_pham_gh.get(i).getMa_giam_gia(),
                        ds_san_pham_gh.get(i).getSo_luong(),
                        ds_san_pham_gh.get(i).getGia_san_pham(),
                        ds_san_pham_gh.get(i).getGia_da_giam()
                ));
            }
            //
            /* Nhớ sửa id người*/

            DONHANG = new don_hang();
            DONHANG.set_mang_hinh_thanh_toan(
                    Luu_tru.getInt("id_nguoi", 0),
                    ds_quan_huyen.get(quan_huyen).getId_khu_vuc(),
                    tv_dia_chi_chi_tiet.getText().toString(),
                    chi_tiet_don_hang
                    );
            //
            String query = "price="+tong_tien+"&totalCod="+tong_tien+"&weight="+tongGram+"&fromCity={dia_chi_TTP_cua_kho}&fromDistrict={dia_chi_QH_cua_kho}&toCity="+ds_tinh_thanh.get(tinh_thanh).getMa_khu_vuc()+"&toDistrict="+ds_quan_huyen.get(quan_huyen).getMa_khu_vuc()+"&contentId=0&carrierId=0&carrierServiceId=0&storeId=0";
            Intent intent = new Intent(getApplicationContext(), mang_hinh_phi_van_chuyen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("query", query);
            getApplicationContext().startActivity(intent);
        }
    }

    void thiet_lap_tt_nd_tt(){
        et_ho_ten_tt.setText(thongTinNguoiDungThanhToan.getTen_nguoi());
        et_email_tt.setText(thongTinNguoiDungThanhToan.getEmail_nguoi());
        et_sdt_thanh_toan.setText(thongTinNguoiDungThanhToan.getSdt_nguoi());
    }
    public class tai_tt_nd_tt extends AsyncTask<String, Void, ArrayList<thong_tin_nguoi_dung_thanh_toan>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_tt_nd_tt(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected ArrayList<thong_tin_nguoi_dung_thanh_toan> doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            try{
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
                thong_tin_nguoi_dung_thanh_toan[] ttngtt_tmp = gson.fromJson(data.toString(),thong_tin_nguoi_dung_thanh_toan[].class);
                ArrayList<thong_tin_nguoi_dung_thanh_toan> ttngtt_kq = new ArrayList<thong_tin_nguoi_dung_thanh_toan>(Arrays.asList(ttngtt_tmp));
                if(!isConnected()||isFinishing()) cancel(true);
                return ttngtt_kq;
            }
            catch (Exception ex)
            {
                Log.e("loi",ex.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<thong_tin_nguoi_dung_thanh_toan> s) {
            if(!isConnected()||isFinishing()) cancel(true);
            if(s!=null && s.size()!=0)
            {
                thongTinNguoiDungThanhToan=s.get(0);
                thiet_lap_tt_nd_tt();
                new tai_ds_tinh_thanh("lay_danh_sach_dia_chi", "id_khu_vuc_cha").execute("0");
            }
            super.onPostExecute(s);
        }
    }
    public class tai_ds_tinh_thanh extends AsyncTask<String, Void, ArrayList<khu_vuc>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_ds_tinh_thanh(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected ArrayList<khu_vuc> doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            try{
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
                khu_vuc[] kv_tmp = gson.fromJson(data.toString(),khu_vuc[].class);
                ArrayList<khu_vuc> kv_kq = new ArrayList<khu_vuc>(Arrays.asList(kv_tmp));
                if(!isConnected()||isFinishing()) cancel(true);
                return kv_kq;
            }
            catch (Exception ex)
            {
                Log.e("loi",ex.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<khu_vuc> s) {
            if(!isConnected()||isFinishing()) cancel(true);
            ds_tinh_thanh = s;
            if(ds_tinh_thanh!=null)
                khoi_tao_tinh_thanh();
            super.onPostExecute(s);
        }
    }
    public class tai_ds_quan_huyen extends AsyncTask<String, Void, ArrayList<khu_vuc>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_ds_quan_huyen(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected ArrayList<khu_vuc> doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            try{
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
                khu_vuc[] kv_tmp = gson.fromJson(data.toString(),khu_vuc[].class);
                ArrayList<khu_vuc> kv_kq = new ArrayList<khu_vuc>(Arrays.asList(kv_tmp));
                if(!isConnected()||isFinishing()) cancel(true);
                return kv_kq;
            }
            catch (Exception ex)
            {
                Log.e("loi",ex.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<khu_vuc> s) {
            if(!isConnected()||isFinishing()) cancel(true);
            ds_quan_huyen = s;
            if(ds_quan_huyen!=null)
                khoi_tao_quan_huyen();
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
