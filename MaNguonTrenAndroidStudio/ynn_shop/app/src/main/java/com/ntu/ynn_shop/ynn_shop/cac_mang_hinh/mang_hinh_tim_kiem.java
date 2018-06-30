package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.tag;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.yeu_cau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class mang_hinh_tim_kiem extends AppCompatActivity {
    SharedPreferences Luu_tru;
    EditText et_nd_tk;
    TextView tv_x,  tv_search;
    Toolbar toolbar;
    TagContainerLayout TCL_lstk;
    ArrayList<tag> ds_ls_tk;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_tim_kiem);
        anh_xa();
        thiet_lap_tag();
    }

    private void thiet_lap_tag() {
        String lich_su = Luu_tru.getString("lich_su_tim_kiem", null);
        if(lich_su!=null)
        {
            tag[] tmp = gson.fromJson(lich_su,tag[].class);
            ArrayList<tag> kq = new ArrayList<tag>(Arrays.asList(tmp));
            ds_ls_tk  = kq;
            for(int i = 0; i < ds_ls_tk.size(); i++)
                TCL_lstk.addTag(ds_ls_tk.get(i).getTen_tim_kiem());
        }

        TCL_lstk.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                Intent intent = new Intent(mang_hinh_tim_kiem.this, mang_hinh_ds_san_pham.class);
                yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                yeu_cau.getBo_loc().setTim_kiem(text);
                intent.putExtra("yeu_cau",gson.toJson(yeu_cau));
                startActivity(intent);
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {
                ds_ls_tk.remove(position);
                TCL_lstk.removeTag(position);
                SharedPreferences.Editor sua=Luu_tru.edit();
                sua.putString("lich_su_tim_kiem", gson.toJson(ds_ls_tk));
                sua.commit();
            }
        });
    }

    private void anh_xa() {
        ds_ls_tk = new ArrayList<>();
        gson = new Gson();
        Luu_tru =getSharedPreferences("ynn_shop",MODE_PRIVATE);
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
        et_nd_tk = (EditText) toolbar.findViewById(R.id.et_nd_tk);
        tv_x = (TextView) toolbar.findViewById(R.id.tv_x);
        tv_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_nd_tk.setText("");
            }
        });
        tv_search = (TextView) toolbar.findViewById(R.id.tv_search);
        TCL_lstk = (TagContainerLayout) findViewById(R.id.TCL_lstk);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_nd_tk.getText().toString().trim()!=null&&et_nd_tk.getText().toString().trim()!=""&&
                        et_nd_tk.getText().toString().trim().length()!=0){
                    TCL_lstk.addTag(et_nd_tk.getText().toString());
                    ds_ls_tk.add(new tag(et_nd_tk.getText().toString()));
                    SharedPreferences.Editor sua=Luu_tru.edit();
                    sua.putString("lich_su_tim_kiem", gson.toJson(ds_ls_tk));
                    sua.commit();
                    //chuyển trang
                    Intent intent = new Intent(mang_hinh_tim_kiem.this, mang_hinh_ds_san_pham.class);
                    yeu_cau yeu_cau = new tao_yeu_cau().getYeu_cau();
                    yeu_cau.getBo_loc().setTim_kiem(et_nd_tk.getText().toString());
                    intent.putExtra("yeu_cau",gson.toJson(yeu_cau));
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        //getFragmentManager().popBackStack();
    }

}
