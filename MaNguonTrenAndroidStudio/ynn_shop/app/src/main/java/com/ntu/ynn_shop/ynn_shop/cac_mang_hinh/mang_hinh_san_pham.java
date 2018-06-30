package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_mhsp_danh_gia;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_slide_anh_san_pham;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.lam_tron;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_khoang_trong;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_khoang_trong_doc;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.gio_hang.san_pham_gh;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham.danh_gia;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham.san_pham;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham.them_danh_gia;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham.tra_ve_tim;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.trang_san_pham.truy_van_san_pham;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.yeu_cau_ds.yeu_cau;
import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Arrays;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class mang_hinh_san_pham extends AppCompatActivity {
    Toolbar toolbar;
    Gson gson;
    dinh_dang_tien dinh_dang;
    private static ma_hoa_base64 ma_hoa;
    static san_pham sp;
    ViewPager viewPager;
    static PhotoViewAttacher photoViewAttacher;
    TextView TV_STT_anh, TV_STT_Share, TV_STT_Tim,  TV_ten_sp, TV_sp_gia_ban_ra, tv_sp_gia_cao, tv_sp_giam_pt,
            tv_so_tim, tv_dong_dssp_rv_so_sao, TV_sp_sl,TV_sp_ic_vat, TV_sp_ten_vat;
    MaterialRatingBar mrb_sp_tb_sao;
    bcd_dong_slide_anh_san_pham bcd_dsasp;
    WebView wv_gtsp;
    WebView wv_ctsp;
    TextView TV_saotb,tv_sp_so_luot_danh_gia, tv_5sao, tv_4sao, tv_3sao, tv_2sao, tv_1sao;
    MaterialRatingBar mrb_tbsao;
    RoundCornerProgressBar pr_5sao, pr_4sao, pr_3sao, pr_2sao, pr_1sao;
    RecyclerView RV_danh_gia;
    bcd_dong_mhsp_danh_gia bcdDongMhspDanhGia;
    Dialog dialog;
    static Dialog phong_to;
    truy_van_san_pham truyVanSanPham;
    TextView tv_mhsp_them_vao_gio_hang;
    SharedPreferences Luu_tru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_san_pham);
        System.gc();
        anh_xa();
        thiet_lap_toolbar();
        //truyVanSanPham = new truy_van_san_pham(12001459, 136891415);
        thiet_lap_tab();
        thiet_lap_cuon();
        thiet_lap_popup_phong_to();
        nhan_yeu_cau_truyen_mang_hinh();
        Log.d("jsonnnnn", new Gson().toJson(truyVanSanPham));
        new tai_json_sp("lay_du_lieu_san_pham","json").execute(new Gson().toJson(truyVanSanPham));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.menu_tim_kiem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.trang_chu) {
            Intent intent = new Intent(mang_hinh_san_pham.this, mang_hinh_trang_chu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        } else if (id == R.id.trang_giam_gia) {
            Intent intent = new Intent(mang_hinh_san_pham.this, mang_hinh_giam_gia.class);
            startActivity(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            return true;
        } else if (id == R.id.trang_danh_muc) {
            Intent intent = new Intent(mang_hinh_san_pham.this, mang_hinh_menu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        } else if (id == R.id.trang_tai_khoan) {
            Intent intent = new Intent(mang_hinh_san_pham.this, man_hinh_tai_khoan.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }else if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void thiet_lap_toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((TextView) toolbar.findViewById(R.id.tv_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mang_hinh_san_pham.this, mang_hinh_tim_kiem.class);
                startActivity(intent);
            }
        });
        ((TextView) toolbar.findViewById(R.id.tv_cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mang_hinh_san_pham.this, man_hinh_gio_hang.class);
                startActivity(intent);
            }
        });
    }
    private void nhan_yeu_cau_truyen_mang_hinh() {
        Intent intent = getIntent();
        int id_sp = intent.getIntExtra("id_san_pham", 0);
        truyVanSanPham  = new truy_van_san_pham(Luu_tru.getInt("id_nguoi", 0), id_sp);
    }
    public static void hien_thi_hinh_anh(int p){
       Picasso.with(phong_to.getContext()).load(ma_hoa.giai_ma(sp.getDanh_sach_anh().get(p).toString()) + "_1200x1200q80.jpg")
               .into((PhotoView) phong_to.findViewById(R.id.PV_anh_phong_to));
        photoViewAttacher = new PhotoViewAttacher((PhotoView) phong_to.findViewById(R.id.PV_anh_phong_to));
        phong_to.show();
    }

    private void thiet_lap_popup_phong_to() {
        phong_to = new Dialog(mang_hinh_san_pham.this);
        phong_to.requestWindowFeature(Window.FEATURE_NO_TITLE);
        phong_to.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        phong_to.setContentView(R.layout.poup_phong_to_anh);
        phong_to.setCancelable(false);

        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        lWindowParams.copyFrom(phong_to.getWindow().getAttributes());
        lWindowParams.width = WindowManager.LayoutParams.FILL_PARENT; // this is where the magic happens
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        phong_to.getWindow().setAttributes(lWindowParams);
        ((TextView) phong_to.findViewById(R.id.TV_huy_popup)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phong_to.dismiss();
            }
        });
    }
    private void thiet_lap_cuon() {
        ((NestedScrollView) findViewById(R.id.NSV_mh_sp)).setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int tong_quan_dau=((RelativeLayout) findViewById(R.id.RL_diem_TQ)).getTop();
                int tong_quan_cuoi=((LinearLayout) findViewById(R.id.RL_diem_TQ_b)).getBottom();
                int thong_tin_sp_dau=((LinearLayout) findViewById(R.id.LL_diem_ttsp_d)).getTop();
                int thong_tin_sp_cuoi=((LinearLayout) findViewById(R.id.LL_diem_ttsp_c)).getBottom();
                int danh_gia_dau=((LinearLayout) findViewById(R.id.LL_diem_danh_gia)).getTop();
                int danh_gia_cuoi=((LinearLayout) findViewById(R.id.LL_diem_danh_gia)).getBottom();
                if     (scrollY > tong_quan_dau &&  scrollY < tong_quan_cuoi ){
                    en(((TextView) findViewById(R.id.TV_TQ)), ((TextView) findViewById(R.id.TV_TQ_Line)));
                    dis(((TextView) findViewById(R.id.TV_TTSP)), ((TextView) findViewById(R.id.TV_TTSP_Line)));
                    dis(((TextView) findViewById(R.id.TV_DG)), ((TextView) findViewById(R.id.TV_DG_Line)));
                    Log.d("12345tt", "đang ở tổng quan");
                }
                else if(scrollY > thong_tin_sp_dau &&  scrollY < thong_tin_sp_cuoi ) {
                    dis(((TextView) findViewById(R.id.TV_TQ)), ((TextView) findViewById(R.id.TV_TQ_Line)));
                    en(((TextView) findViewById(R.id.TV_TTSP)), ((TextView) findViewById(R.id.TV_TTSP_Line)));
                    dis(((TextView) findViewById(R.id.TV_DG)), ((TextView) findViewById(R.id.TV_DG_Line)));
                    Log.d("12345tt", "đang ở thông tin sản phẩm");
                }
                else if(scrollY > danh_gia_dau &&  scrollY < danh_gia_cuoi ) {
                    dis(((TextView) findViewById(R.id.TV_TQ)), ((TextView) findViewById(R.id.TV_TQ_Line)));
                    dis(((TextView) findViewById(R.id.TV_TTSP)), ((TextView) findViewById(R.id.TV_TTSP_Line)));
                    en(((TextView) findViewById(R.id.TV_DG)), ((TextView) findViewById(R.id.TV_DG_Line)));
                    Log.d("12345tt", "đang ở đánh giá");
                }
                Log.d("12345dg", ((LinearLayout) findViewById(R.id.LL_diem_danh_gia)).getTop()+"");
                Log.d("12345sc", "scrollX: "+scrollX+" ,scrollY: " +scrollY + " ,oldScrollX: " + oldScrollX + " ,oldScrollY: " + oldScrollY);
            }
        });
    }

    private void thiet_lap_thong_tin_san_pham() {
        if(!sp.isTha_tim()) {
            //TV_STT_Tim.setText("\ue801");
            TV_STT_Tim.setTextColor(Color.parseColor("#ffffff"));
        }
        else {
           // TV_STT_Tim.setText("\ue800");
            TV_STT_Tim.setTextColor(Color.parseColor("#ff0000"));
        }
        TV_STT_Tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new gui_tim("them_xoa_tim", "json").execute(new Gson().toJson(truyVanSanPham));
            }
        });

        int sl = 0;
        for(int i =0; i< sp.getThong_tin_so_luong().size(); i++)
            sl += sp.getThong_tin_so_luong().get(i).getSo_luong();
        if(sl==0)
        {
            tv_mhsp_them_vao_gio_hang.setText("Cháy hàng");
            tv_mhsp_them_vao_gio_hang.setEnabled(false);
            tv_mhsp_them_vao_gio_hang.setBackgroundColor(Color.parseColor("#7e9b9b9b"));
            //xóa màu
        }
        else {
            tv_mhsp_them_vao_gio_hang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(man_hinh_gio_hang.ds_san_pham_gh==null)
                        man_hinh_gio_hang.ds_san_pham_gh=new ArrayList<>();

                    int sl=0;
                    for(int i =0; i< sp.getThong_tin_so_luong().size(); i++)
                        sl += sp.getThong_tin_so_luong().get(i).getSo_luong();
                    boolean f = false;
                    int max = man_hinh_gio_hang.ds_san_pham_gh.size();
                        for(int i=0; i<max; i++)
                                if(man_hinh_gio_hang.ds_san_pham_gh.get(i).getId_san_pham()==sp.getId_san_pham())
                                {
                                    f=true;
                                    if(man_hinh_gio_hang.ds_san_pham_gh.get(i).getSo_luong()<sl)
                                    {
                                        if(man_hinh_gio_hang.ds_san_pham_gh.get(i).getMa_giam_gia()==null)
                                        {
                                            man_hinh_gio_hang.ds_san_pham_gh.get(i).setSo_luong(
                                                    man_hinh_gio_hang.ds_san_pham_gh.get(i).getSo_luong()+1

                                            );
                                            Toast.makeText(mang_hinh_san_pham.this,"Đã thêm vào giỏ hàng",
                                                    Toast.LENGTH_SHORT).show();
                                            SharedPreferences.Editor sua=Luu_tru.edit();
                                            sua.putString("gio_hang", gson.toJson(man_hinh_gio_hang.ds_san_pham_gh));
                                            sua.commit();
                                        }
                                        else
                                            Toast.makeText(mang_hinh_san_pham.this,"Đã áp dụng mã giảm giá thì số lượng phải bằng 1!",
                                                    Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                        Toast.makeText(mang_hinh_san_pham.this,"Xin lỗi! Đã hết số lượng!",
                                                Toast.LENGTH_SHORT).show();
                                    break;
                                }

                        if(f==false)  {
                                man_hinh_gio_hang.ds_san_pham_gh.add(new san_pham_gh(
                                        sp.getId_san_pham(),
                                        sp.getTen_san_pham(),
                                        sl,
                                        sp.getDanh_sach_anh().get(0),
                                        sp.getKhoi_luong_san_pham_gram(),
                                        sp.getGia_ban_ra()));
                            SharedPreferences.Editor sua=Luu_tru.edit();
                            sua.putString("gio_hang", gson.toJson(man_hinh_gio_hang.ds_san_pham_gh));
                            sua.commit();
                                Toast.makeText(mang_hinh_san_pham.this,"Đã thêm vào giỏ hàng",
                                        Toast.LENGTH_SHORT).show();
                        }
                        Log.d("sadsfasdfafd", new Gson().toJson(man_hinh_gio_hang.ds_san_pham_gh));
                }
            });
        }
        TV_sp_sl.setText(sl + "");
        tv_so_tim.setText(sp.getSo_tim()+"");
        ((TextView) toolbar.findViewById(R.id.tv_tieu_de)).setText(ma_hoa.giai_ma(sp.getTen_san_pham()));
        TV_ten_sp.setText(ma_hoa.giai_ma(sp.getTen_san_pham()));
        TV_sp_gia_ban_ra.setText(dinh_dang.dinh_dang(sp.getGia_ban_ra()+""));
        tv_sp_gia_cao.setText( dinh_dang.dinh_dang(sp.getGia_cao()+""));
        tv_sp_gia_cao.setPaintFlags(tv_sp_gia_cao.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tv_sp_giam_pt.setText("-"+new lam_tron().tron(100 - (sp.getGia_ban_ra()*1.0/sp.gia_cao)*100,"#.##") + "%");
        if(sp.getDanh_sach_danh_gia()==null)
        tv_dong_dssp_rv_so_sao.setText(""+0);
        else tv_dong_dssp_rv_so_sao.setText(sp.getDanh_sach_danh_gia().size()+"");
        if(sp.isDa_co_vat()) {
            TV_sp_ic_vat.setText("\ue800");
            TV_sp_ten_vat.setText("Đã bao gồm thuế VAT");
            TV_sp_ic_vat.setTextColor(Color.parseColor("#4caf50"));
            TV_sp_ten_vat.setTextColor(Color.parseColor("#4caf50"));
        }
        else {
            TV_sp_ic_vat.setText("\ue801");
            TV_sp_ten_vat.setText("Chưa bao gồm thuế VAT");
            TV_sp_ic_vat.setTextColor(Color.parseColor("#FF0000"));
            TV_sp_ten_vat.setTextColor(Color.parseColor("#FF0000"));
        }
        int so_sao=0;
        if(sp.getDanh_sach_danh_gia()==null) mrb_sp_tb_sao.setRating(0);
        else
        {
            for(int i = 0; i<sp.getDanh_sach_danh_gia().size(); i++)
                so_sao+=sp.getDanh_sach_danh_gia().get(i).getSo_sao_danh_gia();
            mrb_sp_tb_sao.setRating((float) (so_sao*1.0/sp.getDanh_sach_danh_gia().size()));
        }
        bcd_dsasp =  new bcd_dong_slide_anh_san_pham(sp.getDanh_sach_anh(), mang_hinh_san_pham.this);
        viewPager.setAdapter(bcd_dsasp);
        TV_STT_anh.setText(1+"/"+(sp.getDanh_sach_anh().size()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TV_STT_anh.setText((position+1)+"/"+sp.getDanh_sach_anh().size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//
//        //tăng tốc render webview html
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // chromium, enable hardware acceleration
//            wv_gtsp.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//            //wv_ctsp.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        } else {
//            // older android version, disable hardware acceleration
//            wv_gtsp.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//            //wv_ctsp.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }
        wv_gtsp.loadDataWithBaseURL(null, "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + ma_hoa.giai_ma(sp.getMo_ta_san_pham_html()), "text/html", "UTF-8", null);
        wv_ctsp.loadDataWithBaseURL(null, "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + ma_hoa.giai_ma(sp.getDac_tinh_san_pham()), "text/html", "UTF-8", null);
        if(sp.getDanh_sach_danh_gia()!=null && sp.getDanh_sach_danh_gia().size()!=0)
        {
            thiet_lap_thong_ke();
        }
        bcdDongMhspDanhGia = new bcd_dong_mhsp_danh_gia(mang_hinh_san_pham.this, sp.getDanh_sach_danh_gia());
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(mang_hinh_san_pham.this, LinearLayoutManager.VERTICAL, false);
        RV_danh_gia.setLayoutManager(verticalLayoutmanager);
        RV_danh_gia.addItemDecoration(new tao_khoang_trong_doc(2));
        RV_danh_gia.setAdapter(bcdDongMhspDanhGia);
//chia sẻ
        ((TextView) findViewById(R.id.TV_STT_Share)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "giá gôc: " + tv_sp_gia_cao.getText().toString()+ ", giảm " +tv_sp_giam_pt.getText().toString() + " chỉ còn " + TV_sp_gia_ban_ra.getText().toString();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,  TV_ten_sp.getText().toString());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Chia sẻ sản phẩm"));
            }
        });
        thiet_lap_popup_gui_danh_gia();
//thêm đánh giá
        ((LinearLayout) findViewById(R.id.LL_them_danh_gia)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }
    private void thiet_lap_thong_ke()
    {
        float tb = 0;
        for(int i = 0; i  < sp.getDanh_sach_danh_gia().size(); i++)
            tb+=sp.getDanh_sach_danh_gia().get(i).getSo_sao_danh_gia();
        tb=tb/sp.getDanh_sach_danh_gia().size();
        mrb_sp_tb_sao.setRating(tb);
        TV_saotb.setText(mrb_sp_tb_sao.getRating()+"");
        tv_dong_dssp_rv_so_sao.setText(sp.getDanh_sach_danh_gia().size()+"");
        tv_sp_so_luot_danh_gia.setText(tv_dong_dssp_rv_so_sao.getText()+" Lượt đánh giá");
        pr_5sao.setMax(Integer.parseInt(tv_dong_dssp_rv_so_sao.getText().toString()));
        pr_4sao.setMax(Integer.parseInt(tv_dong_dssp_rv_so_sao.getText().toString()));
        pr_3sao.setMax(Integer.parseInt(tv_dong_dssp_rv_so_sao.getText().toString()));
        pr_2sao.setMax(Integer.parseInt(tv_dong_dssp_rv_so_sao.getText().toString()));
        pr_1sao.setMax(Integer.parseInt(tv_dong_dssp_rv_so_sao.getText().toString()));
        int [] tmp = {0,0,0,0,0,0};
        for(int i = 0; i < sp.getDanh_sach_danh_gia().size(); i++)
            tmp[sp.getDanh_sach_danh_gia().get(i).getSo_sao_danh_gia()]++;
        pr_1sao.setProgress(tmp[1]);
        pr_2sao.setProgress(tmp[2]);
        pr_3sao.setProgress(tmp[3]);
        pr_4sao.setProgress(tmp[4]);
        pr_5sao.setProgress(tmp[5]);
        tv_5sao.setText(tmp[5]+"");
        tv_4sao.setText(tmp[4]+"");
        tv_3sao.setText(tmp[3]+"");
        tv_2sao.setText(tmp[2]+"");
        tv_1sao.setText(tmp[1]+"");
        mrb_tbsao.setRating(mrb_sp_tb_sao.getRating());
    }
    private void thiet_lap_popup_gui_danh_gia(){
        dialog = new Dialog(mang_hinh_san_pham.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.popup_them_danh_gia);
        dialog.setCancelable(false);
        ((Button) dialog.findViewById(R.id.btn_popup_them_dg_thoi)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RatingBar) dialog.findViewById(R.id.RB_popup_them_dg_sao)).setRating(0);
               ((EditText) dialog.findViewById(R.id.ET_popup_them_dg_tieu_de)).setText("");
                ((EditText) dialog.findViewById(R.id.ET_popup_them_dg_noi_dung)).setText("");
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.btn_popup_them_dg_xn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gui_danh_gia();
            }
        });
    }
    private void gui_danh_gia(){
        int rv_sao = (int) ((RatingBar) dialog.findViewById(R.id.RB_popup_them_dg_sao)).getRating();
        String rv_tieu_đe = ((EditText) dialog.findViewById(R.id.ET_popup_them_dg_tieu_de)).getText().toString();
        String rv_nd = ((EditText) dialog.findViewById(R.id.ET_popup_them_dg_noi_dung)).getText().toString();
        if(rv_sao==0)
            Toast.makeText(mang_hinh_san_pham.this, "Bạn chưa chọn số sao",
                    Toast.LENGTH_LONG).show();
        else if(rv_tieu_đe==null || rv_tieu_đe == ""|| rv_tieu_đe.length()==0)
            Toast.makeText(mang_hinh_san_pham.this, "Bạn chưa viết tiêu đề",
                    Toast.LENGTH_LONG).show();
        else if(rv_nd ==null || rv_nd == "" || rv_nd.length() == 0)
            Toast.makeText(mang_hinh_san_pham.this, "Bạn chưa viết nội dung",
                    Toast.LENGTH_LONG).show();
        else {
            them_danh_gia themDanhGia = new them_danh_gia(
                    sp.getId_san_pham(),
                    sp.getId_user(),
                    true,
                    ma_hoa.ma_hoa(rv_tieu_đe),
                    rv_sao,
                    ma_hoa.ma_hoa(rv_nd),
                    "2018-02-12 05:02:35");
                    dialog.dismiss();
                    Log.d("ssssssssssss", new Gson().toJson(themDanhGia));
                    new gui_dg("them_danh_gia","json").execute(new Gson().toJson(themDanhGia));
        }}
    private void thiet_lap_tab(){
        ((LinearLayout) findViewById(R.id.LL_TQ)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                en(((TextView) findViewById(R.id.TV_TQ)), ((TextView) findViewById(R.id.TV_TQ_Line)));
                dis(((TextView) findViewById(R.id.TV_TTSP)), ((TextView) findViewById(R.id.TV_TTSP_Line)));
                dis(((TextView) findViewById(R.id.TV_DG)), ((TextView) findViewById(R.id.TV_DG_Line)));
                int tong_quan_dau=((RelativeLayout) findViewById(R.id.RL_diem_TQ)).getTop();
                ((NestedScrollView) findViewById(R.id.NSV_mh_sp)).scrollTo(0, tong_quan_dau);
            }
        });
        ((LinearLayout) findViewById(R.id.LL_TTSP)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dis(((TextView) findViewById(R.id.TV_TQ)), ((TextView) findViewById(R.id.TV_TQ_Line)));
                en(((TextView) findViewById(R.id.TV_TTSP)), ((TextView) findViewById(R.id.TV_TTSP_Line)));
                dis(((TextView) findViewById(R.id.TV_DG)), ((TextView) findViewById(R.id.TV_DG_Line)));
                int thong_tin_sp_dau=((LinearLayout) findViewById(R.id.LL_diem_ttsp_d)).getTop();
                ((NestedScrollView) findViewById(R.id.NSV_mh_sp)).scrollTo(0, thong_tin_sp_dau);
            }
        });
        ((LinearLayout) findViewById(R.id.LL_DG)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dis(((TextView) findViewById(R.id.TV_TQ)), ((TextView) findViewById(R.id.TV_TQ_Line)));
                dis(((TextView) findViewById(R.id.TV_TTSP)), ((TextView) findViewById(R.id.TV_TTSP_Line)));
                en(((TextView) findViewById(R.id.TV_DG)), ((TextView) findViewById(R.id.TV_DG_Line)));
                int danh_gia_dau=((LinearLayout) findViewById(R.id.LL_diem_danh_gia)).getTop();
                ((NestedScrollView) findViewById(R.id.NSV_mh_sp)).scrollTo(0, danh_gia_dau);
            }
        });
    }
    private void en(TextView a, TextView b){
        a.setTextColor(Color.parseColor("#f57224"));
        b.setBackgroundColor(Color.parseColor("#f57224"));
    }
    private void dis(TextView a, TextView b){
        a.setTextColor(Color.parseColor("#9e9e9e"));
        //b.setBackgroundColor(Color.parseColor("#ffffff"));
        b.setBackgroundColor(Color.parseColor("#9e9e9e"));
    }
    //new
    private void anh_xa() {
        Luu_tru =getSharedPreferences("ynn_shop",MODE_PRIVATE);
        gson = new Gson();
        dinh_dang = new dinh_dang_tien();
        ma_hoa = new ma_hoa_base64();
        tv_mhsp_them_vao_gio_hang = (TextView) findViewById(R.id.tv_mhsp_them_vao_gio_hang);
        TV_STT_anh  = (TextView) findViewById(R.id.TV_STT_anh);
        TV_STT_Share  = (TextView) findViewById(R.id.TV_STT_Share);
        TV_STT_Tim  = (TextView) findViewById(R.id.TV_STT_Tim);
        viewPager = (ViewPager) findViewById(R.id.vp_slide_anh_san_pham) ;
        TV_ten_sp  = (TextView) findViewById(R.id.TV_ten_sp);
        TV_sp_gia_ban_ra  = (TextView) findViewById(R.id.TV_sp_gia_ban_ra);
        tv_sp_gia_cao  = (TextView) findViewById(R.id.tv_sp_gia_cao);
        tv_sp_giam_pt  = (TextView) findViewById(R.id.tv_sp_giam_pt);
        tv_so_tim  = (TextView) findViewById(R.id.tv_so_tim                                                    );
        tv_dong_dssp_rv_so_sao  = (TextView) findViewById(R.id.tv_dong_dssp_rv_so_sao);
        tv_dong_dssp_rv_so_sao  = (TextView) findViewById(R.id.tv_dong_dssp_rv_so_sao);
        TV_sp_ic_vat  = (TextView) findViewById(R.id.TV_sp_ic_vat);
        TV_sp_ten_vat  = (TextView) findViewById(R.id.TV_sp_ten_vat);
        mrb_sp_tb_sao = (MaterialRatingBar) findViewById(R.id.mrb_sp_tb_sao);
        wv_gtsp = (WebView) findViewById(R.id.wv_gtsp);
        wv_ctsp = (WebView) findViewById(R.id.wv_ctsp);
        TV_sp_sl = (TextView) findViewById(R.id.TV_sp_sl);
// TextView TV_saotb,tv_sp_so_luot_danh_gia;
//    MaterialRatingBar mrb_tbsao;
//    RoundCornerProgressBar pr_5sao, pr_4sao, pr_3sao, pr_2sao, pr_1sao;
        TV_saotb = (TextView) findViewById(R.id.TV_saotb);
        tv_sp_so_luot_danh_gia = (TextView) findViewById(R.id.tv_sp_so_luot_danh_gia);
        pr_5sao = (RoundCornerProgressBar) findViewById(R.id.pr_5sao);
        pr_4sao = (RoundCornerProgressBar) findViewById(R.id.pr_4sao);
        pr_3sao = (RoundCornerProgressBar) findViewById(R.id.pr_3sao);
        pr_2sao = (RoundCornerProgressBar) findViewById(R.id.pr_2sao);
        pr_1sao = (RoundCornerProgressBar) findViewById(R.id.pr_1sao);
        mrb_tbsao = (MaterialRatingBar) findViewById(R.id.mrb_tbsao);
        //tv_5sao, tv_4sao, tv_3sao, tv_2sao, tv_1sao;
        tv_5sao= (TextView) findViewById(R.id.tv_5sao);
        tv_4sao= (TextView) findViewById(R.id.tv_4sao);
        tv_3sao= (TextView) findViewById(R.id.tv_3sao);
        tv_2sao= (TextView) findViewById(R.id.tv_2sao);
        tv_1sao= (TextView) findViewById(R.id.tv_1sao);
        RV_danh_gia  = (RecyclerView) findViewById(R.id.RV_danh_gia);
        RV_danh_gia.setNestedScrollingEnabled(false);
    }
    public class tai_json_sp extends AsyncTask<String, Void, san_pham> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_json_sp(String method_DN, String PARAM_DN) {
            if(!isConnected()||isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected san_pham doInBackground(String... strings) {
            if(!isConnected()||isFinishing()) cancel(true);
            try{
                if(!isConnected()||isFinishing()) cancel(true);
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
                //if(data.toString() == "Thanh Cong") dialog.setMessage("Thanh Cong");
                //else dialog.setMessage("That bai");
                //dialog.setMessage("That bai");
                //Log.d("ynn123",data.toString());
                if(!isConnected()||isFinishing()) cancel(true);
                return new Gson().fromJson(data.toString(), san_pham.class) ;
            }
            catch (Exception ex)
            {
                Log.e("loi",ex.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(san_pham s) {
            if(!isConnected()||isFinishing()) cancel(true);
           sp = s;
            thiet_lap_thong_tin_san_pham();
            super.onPostExecute(s);
        }
    }
    public class gui_dg extends AsyncTask<String, Void, String> {
        private String Method_DN;
        private String PARAM_DN = null;

        public gui_dg(String method_DN, String PARAM_DN) {
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
                //if(jsonObject.getBoolean("tinh_trang")==true)
                    Toast.makeText(mang_hinh_san_pham.this,jsonObject.getString("chi_tiet"),
                            Toast.LENGTH_LONG).show();
                if(jsonObject.getBoolean("tinh_trang")==true){
                    Gson gson = new Gson();
                    danh_gia[] dg = gson.fromJson(jsonObject.getString("du_lieu"),danh_gia[].class);
                    ArrayList<danh_gia> danh_sach_danh_gia = new ArrayList<danh_gia>(Arrays.asList(dg));
                    bcdDongMhspDanhGia.setDanh_sach_danh_gia(danh_sach_danh_gia);
                    sp.setDanh_sach_danh_gia(danh_sach_danh_gia);
                    thiet_lap_thong_ke();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(s);
        }
    }
    public class gui_tim extends AsyncTask<String, Void, String> {
        private String Method_DN;
        private String PARAM_DN = null;

        public gui_tim(String method_DN, String PARAM_DN) {
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
                //if(jsonObject.getBoolean("tinh_trang")==true)
                Toast.makeText(mang_hinh_san_pham.this,jsonObject.getString("chi_tiet"),
                        Toast.LENGTH_SHORT).show();
                tra_ve_tim traVeTim = new Gson().fromJson(jsonObject.getString("du_lieu"),tra_ve_tim.class);
                if(!(traVeTim.isTha_tim()==true))
                        TV_STT_Tim.setTextColor(Color.parseColor("#ffffff"));
                    else
                        TV_STT_Tim.setTextColor(Color.parseColor("#ff0000"));
                tv_so_tim.setText(traVeTim.getSl() + "");
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
