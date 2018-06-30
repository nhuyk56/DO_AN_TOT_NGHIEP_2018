package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_menu_c0;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_menu_c1;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_menu_cdcb;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.json2_loai_san_pham_tat_ca;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.loai_san_pham_cap_0;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.loai_san_pham_cap_1;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thuong_hieu;
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

public class mang_hinh_menu extends AppCompatActivity {
    SharedPreferences Luu_tru;
    ArrayList<loai_san_pham_cap_0> danh_sach_lsp_c0 = new ArrayList<loai_san_pham_cap_0>();
    ListView lv_mn_c0;
    int currentSelectTed = 0;
    GridView gv_dslsp_cdcb;
    LinearLayout lnlo_dslsp_cdcb, lnlo_dslsp_mnc1;
    ExpandableListView elv_dslsp_mnc1;
    Dialog awesomeInfoDialog;
    Gson gson = new Gson();
    bcd_dong_menu_cdcb bcd_dmn_cdcb;
    bcd_dong_menu_c1 bcd_dmn_c1;
    Toolbar toolbar;
    TextView tv_tieu_de, tv_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_menu);
        System.gc();
        anhxa();
        thiet_lap_tab();
        new asynctask_tai_xuly_load("Lay_Du_Lieu_Loai_San_Pham",null).execute("");
    }
    private void thiet_lap_tab() {
        ((LinearLayout) findViewById(R.id.LL_tab_Trang_Chu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mang_hinh_trang_chu.class);
                startActivity(intent);
                finish();
            }
        });
        ((LinearLayout) findViewById(R.id.LL_tab_Giam_gia)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mang_hinh_giam_gia.class);
                startActivity(intent);
                finish();
            }
        });
//        ((LinearLayout) findViewById(R.id.LL_tab_Danh_muc)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), mang_hinh_menu.class);
//                startActivity(intent);
//                finish();
//            }
//        });
        ((LinearLayout) findViewById(R.id.LL_tab_Gio_hang)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), man_hinh_gio_hang.class);
                startActivity(intent);
                finish();
            }
        });
        ((LinearLayout) findViewById(R.id.LL_tab_Tai_khoan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), man_hinh_tai_khoan.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void cai_dat_mang_hinh(boolean cdcb, boolean lmnc1) {
        if(cdcb) lnlo_dslsp_cdcb.setVisibility(View.VISIBLE);
        else lnlo_dslsp_cdcb.setVisibility(View.INVISIBLE);
        if(lmnc1) lnlo_dslsp_mnc1.setVisibility(View.VISIBLE);
        else lnlo_dslsp_mnc1.setVisibility(View.INVISIBLE);
    }
    private void khoi_tao_menu_chi_danh_cho_ban() {
        tv_tieu_de.setText(danh_sach_lsp_c0.get(0).getTen_loai_san_pham());
        if(bcd_dmn_cdcb==null)
        {
            bcd_dmn_cdcb = new bcd_dong_menu_cdcb(this,danh_sach_lsp_c0.get(0).getDanh_sach_chi_danh_cho_ban());
            Log.d("dataonline",danh_sach_lsp_c0.get(0).getDanh_sach_chi_danh_cho_ban().get(0).ten_loai_san_pham);
            gv_dslsp_cdcb.setAdapter(bcd_dmn_cdcb);
            gv_dslsp_cdcb.setNumColumns(3);
        }
    }
    private void khoi_tao_menu_c0() {
        final bcd_dong_menu_c0 bcd_mn_c0 = new bcd_dong_menu_c0(this, danh_sach_lsp_c0);
        lv_mn_c0.setAdapter(bcd_mn_c0);
        lv_mn_c0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position != currentSelectTed) {
                    tv_tieu_de.setText(danh_sach_lsp_c0.get(position).getTen_loai_san_pham());
                    currentSelectTed = position;
                    bcd_mn_c0.setposition(position);
                    bcd_mn_c0.notifyDataSetChanged();
                    if(position==0) {
                        cai_dat_mang_hinh(true,false);
                        khoi_tao_menu_chi_danh_cho_ban();
                    }
                    else {
                        cai_dat_mang_hinh(false,true);
                        khoi_mang_hinh_c1(danh_sach_lsp_c0.get(position).getDanh_sach_loai_san_pham_cap_1(),
                                danh_sach_lsp_c0.get(position).getAnh_loai_san_pham(),
                                danh_sach_lsp_c0.get(position).getDanh_sach_thuong_hieu());
                    }
                }
            }
        });
    }
    private void khoi_mang_hinh_c1(
            final ArrayList<loai_san_pham_cap_1> danh_sach_loai_san_pham_cap_1,
            String anh_dai_dien,
            ArrayList<thuong_hieu> danh_sach_thuong_hieu) {
        if(bcd_dmn_c1==null)
        {
            bcd_dmn_c1 = new bcd_dong_menu_c1(
                    this,
                    danh_sach_loai_san_pham_cap_1,
                    anh_dai_dien,
                    danh_sach_thuong_hieu
            );
            elv_dslsp_mnc1.setAdapter(bcd_dmn_c1);
            elv_dslsp_mnc1.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    Intent intent = new Intent(getApplicationContext(), mang_hinh_ds_san_pham.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                    ArrayList<loai_sp> ds_lsp = new ArrayList<loai_sp>();
                    ds_lsp.add(new loai_sp(danh_sach_loai_san_pham_cap_1.get(groupPosition).id_loai_san_pham, danh_sach_loai_san_pham_cap_1.get(groupPosition).ten_loai_san_pham));
                    yeu_cau.getBo_loc().setDs_loai_sp(ds_lsp);
                    intent.putExtra("yeu_cau", gson.toJson(yeu_cau));
                    getApplicationContext().startActivity(intent);
                    return true;
                }
            });
        }
        else {
            bcd_dmn_c1.setDanh_sach_lsp_c1(danh_sach_loai_san_pham_cap_1);
            bcd_dmn_c1.setAnh_dai_dien(anh_dai_dien);
            bcd_dmn_c1.setDanh_sach_thuong_hieu(danh_sach_thuong_hieu);
            bcd_dmn_c1.notifyDataSetChanged();
        }

    }
    private void anhxa() {
        Luu_tru =getSharedPreferences("ynn_shop",MODE_PRIVATE);
        lv_mn_c0 = (ListView) findViewById(R.id.lv_mn_c0);
        gv_dslsp_cdcb = (GridView) findViewById(R.id.gv_dslsp_cdcb);
        lnlo_dslsp_cdcb = (LinearLayout) findViewById(R.id.lnlo_dslsp_cdcb);
        lnlo_dslsp_mnc1 = (LinearLayout) findViewById(R.id.lnlo_dslsp_mnc1);
        elv_dslsp_mnc1 = (ExpandableListView) findViewById(R.id.elv_dslsp_mnc1);
        awesomeInfoDialog = new AwesomeProgressDialog(this)
                .setTitle("ĐANG XỬ LÝ")
                .setMessage("Xin chờ trong giây lát!")
                .setCancelable(false)
                .show();
        toolbar = (Toolbar) findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        tv_tieu_de = (TextView) toolbar.findViewById(R.id.tv_tieu_de) ;
        tv_search = (TextView) toolbar.findViewById(R.id.tv_search) ;
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mang_hinh_menu.this, mang_hinh_tim_kiem.class);
                startActivity(intent);
            }
        });
    }


    public class asynctask_tai_xuly_load extends AsyncTask<String, Void, ArrayList<loai_san_pham_cap_0>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public asynctask_tai_xuly_load(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }

        @Override
        protected void onPreExecute() {

            if(!isConnected()||isFinishing()) cancel(true);super.onPreExecute();
        }

        protected ArrayList<loai_san_pham_cap_0> doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            boolean geted = false;
            int recall = 0;
            while (geted==false && recall < 5)
            {
                recall++;
                try{
                    if(!isConnected()||isFinishing()) cancel(true);
//                String input=strings[0];
//                SoapObject requests = new SoapObject(thiet_lap_server.NAME_SPACE, Method_DN);
//                if(PARAM_DN != null)
//                    requests.addProperty(PARAM_DN, input);
//                SoapSerializationEnvelope envelope  = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                envelope.dotNet=true;
//                envelope.setOutputSoapObject(requests);
//                HttpTransportSE httpTransportSE = new HttpTransportSE(thiet_lap_server.SERVER_URL);
//                httpTransportSE.call(thiet_lap_server.NAME_SPACE+Method_DN, envelope);
//                SoapPrimitive data = (SoapPrimitive) envelope.getResponse();
                 String menu = Luu_tru.getString("menu", null);
                json2_loai_san_pham_tat_ca json2_lsp_tat_ca = new json2_loai_san_pham_tat_ca();
                return json2_lsp_tat_ca.lay_c0(menu);
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
        protected void onPostExecute(ArrayList<loai_san_pham_cap_0> loai_san_pham_cap_0s) {
            if(!isConnected()||isFinishing()) cancel(true);
            danh_sach_lsp_c0=loai_san_pham_cap_0s;
            khoi_tao_menu_c0();
            cai_dat_mang_hinh(true,false);
            khoi_tao_menu_chi_danh_cho_ban();
            awesomeInfoDialog.dismiss();
            super.onPostExecute(loai_san_pham_cap_0s);
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
