package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_phuong_thuc_van_chuyen;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gui_don_hang.don_hang;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thong_tin_nguoi_dung_thanh_toan;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham.truy_van_san_pham;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.van_chuyen;
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

public class mang_hinh_phi_van_chuyen extends AppCompatActivity {
    Gson gson;
    String truy_van;
    ArrayList<van_chuyen> ds_van_chuyen;
    GridView GV_danh_sach_phuong_thuc_van_chuyen;
    bcd_dong_phuong_thuc_van_chuyen bcdDongPhuongThucVanChuyen;
    TextView tv_ten_phuong_thuc_van_chuyen, tv_thoi_gian_giao, tv_phi_van_chuyen, tv_phi_thu_ho, tv_tien_thu_ho, tv_tong_tien_thanh_toan, dong_y_ptvc;
    dinh_dang_tien dinhDangTien;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_phi_van_chuyen);
        nhan_yeu_cau_truyen_mang_hinh();
        anh_xa();
        new tai_ds_ptvc("lay_danh_sach_phuong_thuc_van_chuyen","query")
                .execute(truy_van);
    }
    private void khoi_tao_van_chuyen() {
        bcdDongPhuongThucVanChuyen = new bcd_dong_phuong_thuc_van_chuyen(this, ds_van_chuyen);
        GV_danh_sach_phuong_thuc_van_chuyen.setAdapter(bcdDongPhuongThucVanChuyen);
        chon_phuong_thuc_van_chuyen(0);
        GV_danh_sach_phuong_thuc_van_chuyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chon_phuong_thuc_van_chuyen(position);
            }
        });
        dong_y_ptvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mang_hinh_ket_qua_dat_hang.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
    }
    void chon_phuong_thuc_van_chuyen( int position){
        bcdDongPhuongThucVanChuyen.setposition(position);
        bcdDongPhuongThucVanChuyen.notifyDataSetChanged();
        tv_ten_phuong_thuc_van_chuyen.setText(ds_van_chuyen.get(position).getCarrierName() + " "+ds_van_chuyen.get(position).getServiceTypeName());
        tv_thoi_gian_giao.setText(ds_van_chuyen.get(position).getServiceDescription());
        tv_phi_van_chuyen.setText(dinhDangTien.dinh_dang(ds_van_chuyen.get(position).getShipFee()+""));
        tv_phi_thu_ho.setText(dinhDangTien.dinh_dang(ds_van_chuyen.get(position).getCodFee()+""));
        long tong = 0;
        for(int i = 0; i < ds_san_pham_gh.size(); i++)
            tong+=ds_san_pham_gh.get(i).getGia_da_giam()*ds_san_pham_gh.get(i).getSo_luong();
        tv_tien_thu_ho.setText(dinhDangTien.dinh_dang(tong+""));
        tv_tong_tien_thanh_toan.setText(dinhDangTien.dinh_dang((tong + ds_van_chuyen.get(position).getTotalFee())+""));
        //vcmin.data[i].carrierId+""+vcmin.data[i].serviceType
        float so_ngay_giao_gan_nhat;
        float so_ngay_giao_xa_nhat;
        String thoi_gian_giao  = ds_van_chuyen.get(position).getServiceDescription();
        if(thoi_gian_giao.contains("Qua ngày")) { so_ngay_giao_gan_nhat = 1;  so_ngay_giao_xa_nhat = 1;}
        else
        if(!thoi_gian_giao.contains("-")) {
            thoi_gian_giao = thoi_gian_giao.replace("ngày", "").trim();
            so_ngay_giao_gan_nhat = Float.parseFloat(thoi_gian_giao)  ;
            so_ngay_giao_xa_nhat = so_ngay_giao_gan_nhat;
        }
        else {
            thoi_gian_giao = thoi_gian_giao.replace("ngày", "");
            String[] mang_time=thoi_gian_giao.split("-");
            so_ngay_giao_gan_nhat = Float.parseFloat( mang_time[0].trim())  ;
            so_ngay_giao_xa_nhat = Float.parseFloat( mang_time[1].trim());
        }
        DONHANG.set_mang_hinh_phi_van_chuyen(
                Integer.parseInt(ds_van_chuyen.get(position).getCarrierId() + ds_van_chuyen.get(position).getServiceType()),
                ds_van_chuyen.get(position).getShipFee(),
                ds_van_chuyen.get(position).getCodFee(),
                tong + ds_van_chuyen.get(position).getTotalFee(),
                so_ngay_giao_gan_nhat,
                so_ngay_giao_xa_nhat
        );
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
        gson = new Gson();
        dinhDangTien = new dinh_dang_tien();
        GV_danh_sach_phuong_thuc_van_chuyen = (GridView) findViewById(R.id.GV_danh_sach_phuong_thuc_van_chuyen);
        tv_ten_phuong_thuc_van_chuyen = (TextView) findViewById(R.id.tv_ten_phuong_thuc_van_chuyen);
        tv_thoi_gian_giao = (TextView) findViewById(R.id.tv_thoi_gian_giao);
        tv_phi_van_chuyen = (TextView) findViewById(R.id.tv_phi_van_chuyen);
        tv_phi_thu_ho = (TextView) findViewById(R.id.tv_phi_thu_ho);
        tv_tien_thu_ho = (TextView) findViewById(R.id.tv_tien_thu_ho);
        tv_tong_tien_thanh_toan = (TextView) findViewById(R.id.tv_tong_tien_thanh_toan);
        dong_y_ptvc = (TextView) findViewById(R.id.dong_y_ptvc);
    }

    private void nhan_yeu_cau_truyen_mang_hinh() {
        Intent intent = getIntent();
        truy_van = intent.getStringExtra("query");
    }
    public class tai_ds_ptvc extends AsyncTask<String, Void, ArrayList<van_chuyen>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_ds_ptvc(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected ArrayList<van_chuyen> doInBackground(String... strings) {
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
                van_chuyen[] ds_vc_tmp = gson.fromJson(data.toString(),van_chuyen[].class);
                ArrayList<van_chuyen> ds_vc_kq = new ArrayList<van_chuyen>(Arrays.asList(ds_vc_tmp));
                if(!isConnected()||isFinishing()) cancel(true);
                return ds_vc_kq;
            }
            catch (Exception ex)
            {
                Log.e("loi",ex.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<van_chuyen> s) {
            if(!isConnected()||isFinishing()) cancel(true);
            ds_van_chuyen = s;
            if(s!=null&&s.size()!=0)
               khoi_tao_van_chuyen();

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
