package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.ntu.ynn_shop.ynn_shop.R;
public class man_hinh_tai_khoan extends AppCompatActivity implements View.OnClickListener {
    FragmentManager fragmentManager;
    Fragment thong_tin_tai_khoan, don_hang_cua_toi, danh_sach_yeu_thich;
    LinearLayout LL_TTTK, LL_DHCT, LL_DSYT;
    TextView TV_TTTK, TV_TTTK_Line, TV_DHCT, TV_DHCT_Line, TV_DSYT, TV_DSYT_Line;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_tai_khoan);
        System.gc();
        thiet_lap_tab();
        anh_xa();
        khoi_chay_lan_dau();
    }

    private void anh_xa() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LL_TTTK = (LinearLayout) findViewById(R.id.LL_TTTK);
        LL_DHCT = (LinearLayout) findViewById(R.id.LL_DHCT);
        LL_DSYT = (LinearLayout) findViewById(R.id.LL_DSYT);
        LL_TTTK.setOnClickListener(this);
        LL_DHCT.setOnClickListener(this);
        LL_DSYT.setOnClickListener(this);
        TV_TTTK = (TextView) findViewById(R.id.TV_TTTK);
        TV_TTTK_Line = (TextView) findViewById(R.id.TV_TTTK_Line);
        TV_DHCT = (TextView) findViewById(R.id.TV_DHCT);
        TV_DHCT_Line = (TextView) findViewById(R.id.TV_DHCT_Line);
        TV_DSYT = (TextView) findViewById(R.id.TV_DSYT);
        TV_DSYT_Line = (TextView) findViewById(R.id.TV_DSYT_Line);
        fragmentManager = getFragmentManager();
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
        ((LinearLayout) findViewById(R.id.LL_tab_Gio_hang)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), man_hinh_gio_hang.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
//        ((LinearLayout) findViewById(R.id.LL_tab_Tai_khoan)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), man_hinh_tai_khoan.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }
    private void en(TextView a, TextView b){
        a.setTextColor(Color.parseColor("#f57224"));
        b.setBackgroundColor(Color.parseColor("#f57224"));
    }
    private void dis(TextView a, TextView b){
        a.setTextColor(Color.parseColor("#9e9e9e"));
        //b.setBackgroundColor(Color.parseColor("#ffffff"));
        b.setBackgroundColor(Color.parseColor("#ffffff"));
    }
    void khoi_chay_lan_dau(){
        en(TV_TTTK, TV_TTTK_Line);
        dis(TV_DHCT, TV_DHCT_Line);
        dis(TV_DSYT, TV_DSYT_Line);
        thong_tin_tai_khoan= null;
        thong_tin_tai_khoan = new Fragment_thong_tin_tai_khoan();
        fragmentManager.beginTransaction().add(R.id.ff_noi_dung, thong_tin_tai_khoan, "TTTK")
                .addToBackStack("TTTK")
                .commit();
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.LL_TTTK:
                en(TV_TTTK, TV_TTTK_Line);
                dis(TV_DHCT, TV_DHCT_Line);
                dis(TV_DSYT, TV_DSYT_Line);
                thong_tin_tai_khoan= null;
                thong_tin_tai_khoan = new Fragment_thong_tin_tai_khoan();
                if(getFragmentManager().findFragmentByTag("TTTK") == null)
                {
                    fragmentManager.beginTransaction().add(R.id.ff_noi_dung, thong_tin_tai_khoan, "TTTK")
                            .addToBackStack("TTTK")
                            .commit();
                }
                else getFragmentManager().popBackStack("TTTK",0);
                break;
            case R.id.LL_DHCT:
                dis(TV_TTTK, TV_TTTK_Line);
                en(TV_DHCT, TV_DHCT_Line);
                dis(TV_DSYT, TV_DSYT_Line);
                don_hang_cua_toi=null;
                don_hang_cua_toi = new Fragment_don_hang_cua_toi();
                if(getFragmentManager().findFragmentByTag("DHCT") == null)
                {
                    fragmentManager.beginTransaction().add(R.id.ff_noi_dung, don_hang_cua_toi, "DHCT")
                            .addToBackStack("DHCT")
                            .commit();
                }
                else getFragmentManager().popBackStack("DHCT",0);
                break;
            case R.id.LL_DSYT:
                dis(TV_TTTK, TV_TTTK_Line);
                dis(TV_DHCT, TV_DHCT_Line);
                en(TV_DSYT, TV_DSYT_Line);
                danh_sach_yeu_thich=null;
                danh_sach_yeu_thich = new Fragment_danh_sach_yeu_thich();
                if(getFragmentManager().findFragmentByTag("DSYT") == null)
                {
                    fragmentManager.beginTransaction().add(R.id.ff_noi_dung, danh_sach_yeu_thich, "DSYT")
                            .addToBackStack("DSYT")
                            .commit();
                }
                else getFragmentManager().popBackStack("DSYT",0);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //getFragmentManager().popBackStack();
    }
}

