package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_dssp_recyclerview;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_khoang_trong;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.sp_ds.san_pham_tds;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_danh_sach_yeu_thich extends Fragment {
    View view;
    SharedPreferences Luu_tru;
    RecyclerView RV_dong_ds_san_pham;
    ArrayList<san_pham_tds> danh_sach_san_pham;
    Gson gson;
    bcd_dong_dssp_recyclerview bcd_d_dssp_rv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_danh_sach_yeu_thich, container,false);
        anh_xa();
        new tai_danh_sach_yeu_thich("lay_danh_sach_yeu_thich_nguoi_dung", "id_nguoi")
        .execute(Luu_tru.getInt("id_nguoi", 0)+"");
        return view;
    }
    private void khoitao_manghinh_danh_sach_sp() {
        RV_dong_ds_san_pham.setHasFixedSize(true);
        RV_dong_ds_san_pham.addItemDecoration(new tao_khoang_trong(1));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        RV_dong_ds_san_pham.setLayoutManager(staggeredGridLayoutManager); // set LayoutManager to RecyclerView
        bcd_d_dssp_rv = new bcd_dong_dssp_recyclerview(getActivity(),danh_sach_san_pham) ;
        RV_dong_ds_san_pham.setAdapter(bcd_d_dssp_rv);
    }
    private void anh_xa() {
        gson = new Gson();
        RV_dong_ds_san_pham = (RecyclerView) view.findViewById(R.id.RV_dong_ds_san_pham);
        Luu_tru =getActivity().getSharedPreferences("ynn_shop",MODE_PRIVATE);
    }
    public class tai_danh_sach_yeu_thich extends AsyncTask<String, Void,  ArrayList<san_pham_tds>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_danh_sach_yeu_thich(String method_DN, String PARAM_DN) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected  ArrayList<san_pham_tds> doInBackground(String... strings) {
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
                HttpTransportSE httpTransportSE = new HttpTransportSE(thiet_lap_server.SERVER_URL);
                httpTransportSE.call(thiet_lap_server.NAME_SPACE+Method_DN, envelope);
                SoapPrimitive data = (SoapPrimitive) envelope.getResponse();
                san_pham_tds[] sp = new san_pham_tds[0];
                sp = gson.fromJson(data.toString(),san_pham_tds[].class);
                ArrayList<san_pham_tds> splist = new ArrayList<san_pham_tds>(Arrays.asList(sp));
                return splist;
            }
            catch (Exception ex)
            {
                Log.e("loi",ex.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<san_pham_tds> san_pham_tds) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            if(san_pham_tds!=null)
            {
                danh_sach_san_pham=san_pham_tds;
                khoitao_manghinh_danh_sach_sp();
            }
            super.onPostExecute(san_pham_tds);
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
