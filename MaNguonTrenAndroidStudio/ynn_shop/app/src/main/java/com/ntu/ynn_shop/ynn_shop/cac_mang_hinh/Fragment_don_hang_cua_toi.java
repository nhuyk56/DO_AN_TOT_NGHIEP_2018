package com.ntu.ynn_shop.ynn_shop.cac_mang_hinh;

import android.app.Activity;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeNoticeDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeWarningDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.gson.Gson;
import com.ntu.ynn_shop.ynn_shop.Interface.CustomItemClickListener;
import com.ntu.ynn_shop.ynn_shop.R;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_chi_tiet_san_pham_don_hang;
import com.ntu.ynn_shop.ynn_shop.cac_bo_chuyen_doi.bcd_dong_don_hang_cua_toi;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.dinh_dang_tien;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.ma_hoa_base64;
import com.ntu.ynn_shop.ynn_shop.cac_cong_cu.tao_khoang_trong_doc;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.chi_tiet_san_pham_don_hang;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.dang_ki.doi_mat_khau;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.dang_ki.nguoi;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.don_hang_cua_toi;
import com.ntu.ynn_shop.ynn_shop.cac_doi_tuong.thong_tin_don_hang_cua_toi;
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

public class Fragment_don_hang_cua_toi extends Fragment {
    View view;
    SharedPreferences Luu_tru;
    RecyclerView RV_dhct;
    bcd_dong_don_hang_cua_toi bcdDongDonHangCuaToi;
    Gson gson;
    ArrayList<don_hang_cua_toi> ds_don_hang_cua_toi;
    Activity mang_hinh;
    int postion_tmp;
    dinh_dang_tien dinh_dang ;
    Dialog Chi_tiet_don_hang;
    TextView tv_ma_don_hang, tv_ngay_dat_hang, tv_ngay_xac_nhan,tv_tinh_trang_don_hang,
            tv_dia_chi, tv_ten_phuong_thuc_van_chuyen,
            tv_ma_van_chuyen, tv_thoi_gian_giao, tv_phi_van_chuyen,
            tv_phi_thu_ho, tv_tien_thu_ho, tv_tong_tien_thanh_toan, tv_huy_don_hang, bt_thoat ;
    RecyclerView RV_chi_tiet_don_hang;
    thong_tin_don_hang_cua_toi thongTinDonHangCuaToi;
    ArrayList<chi_tiet_san_pham_don_hang> ds_san_pham_chi_tiet;
    bcd_dong_chi_tiet_san_pham_don_hang bcdDongChiTietSanPhamDonHang;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_don_hang_cua_toi, container,false);
        anh_xa();
        thiet_lap_chi_tiet_don_hang();
        new tai_don_hang_cua_toi("lay_danh_sach_don_hang","id_nguoi")
                .execute(Luu_tru.getInt("id_nguoi", 0)+"");
        return view;
    }

    private void khoi_tao_danh_sach_don_hang() {
        bcdDongDonHangCuaToi = new bcd_dong_don_hang_cua_toi(getActivity(), ds_don_hang_cua_toi, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                postion_tmp = position;
                new tai_thong_tin_don_hang("lay_thong_tin_don_hang", "id_don_hang")
                        .execute(ds_don_hang_cua_toi.get(position).getMa_don_hang()+"");
            }
        });
        RV_dhct.addItemDecoration(new tao_khoang_trong_doc(1));
        RV_dhct.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        RV_dhct.setAdapter(bcdDongDonHangCuaToi);
        RV_dhct.setNestedScrollingEnabled(false);
    }
    private void anh_xa() {
        mang_hinh = getActivity();
        Luu_tru =getActivity().getSharedPreferences("ynn_shop",MODE_PRIVATE);
        gson = new Gson();
        RV_dhct = (RecyclerView) view.findViewById(R.id.RV_dhct);
    }
    private void hien_thi_thong_tin_don_hang() {
        tv_ma_don_hang.setText("#"+thongTinDonHangCuaToi.getId_don_hang());
        tv_ngay_dat_hang.setText(""+thongTinDonHangCuaToi.getNgay_tao_don_hang());
        tv_ngay_xac_nhan.setText(""+thongTinDonHangCuaToi.getNgay_xac_nhan_don_hang_thanh_cong());
        if(thongTinDonHangCuaToi.getNgay_xac_nhan_don_hang_thanh_cong()==null)
            tv_ngay_xac_nhan.setText("Bạn chưa xác nhận đơn hàng!");
        tv_ten_phuong_thuc_van_chuyen.setText(""+thongTinDonHangCuaToi.getTen_ptvc());
        tv_tinh_trang_don_hang.setText(""+thongTinDonHangCuaToi.getTen_ttdh());
        //tv_ghi_chu.setText("#"+thongTinDonHangCuaToi.());
        tv_dia_chi.setText(""+thongTinDonHangCuaToi.getDia_chi_nhan_hang_chi_tiet());

        if(thongTinDonHangCuaToi.getSo_ngay_giao_gan_nhat()==thongTinDonHangCuaToi.getSo_ngay_giao_xa_nhat())
            tv_thoi_gian_giao.setText("Khoản "+thongTinDonHangCuaToi.getSo_ngay_giao_gan_nhat());
        else
        tv_thoi_gian_giao.setText("Khoản "+thongTinDonHangCuaToi.getSo_ngay_giao_gan_nhat() + " đến " + thongTinDonHangCuaToi.getSo_ngay_giao_xa_nhat() +" ngày");

        if(thongTinDonHangCuaToi.getMa_van_chuyen()==null)
            tv_ma_van_chuyen.setText("Chưa có");
        else tv_ma_van_chuyen.setText(thongTinDonHangCuaToi.getMa_van_chuyen());
        tv_phi_van_chuyen.setText(""+dinh_dang.dinh_dang(""+thongTinDonHangCuaToi.getPhi_van_chuyen()));
        tv_phi_thu_ho.setText(""+dinh_dang.dinh_dang(""+thongTinDonHangCuaToi.getPhi_thu_ho()));
        tv_tien_thu_ho.setText(""+dinh_dang.dinh_dang(""+thongTinDonHangCuaToi.getTien_thu_ho()));
        tv_tong_tien_thanh_toan.setText(""+dinh_dang.dinh_dang(""+thongTinDonHangCuaToi.getTong_tien()));
        if(thongTinDonHangCuaToi.getId_ttdh()==0||thongTinDonHangCuaToi.getId_ttdh()==-1)
        {
            tv_huy_don_hang.setBackgroundColor(Color.parseColor("#f57224"));
        }
        else if(thongTinDonHangCuaToi.getId_ttdh()< -1)
        {
            tv_huy_don_hang.setBackgroundColor(Color.parseColor("#d7d6d6"));
        }
        else if(thongTinDonHangCuaToi.getId_ttdh() > 0)
        {
            tv_huy_don_hang.setBackgroundColor(Color.parseColor("#d7d6d6"));
        }
        //tv_huy_don_hang.setText("#"+thongTinDonHangCuaToi.getId_don_hang());
        tv_huy_don_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thongTinDonHangCuaToi.getId_ttdh()==0||thongTinDonHangCuaToi.getId_ttdh()==-1)
                {
                    new AwesomeWarningDialog(getActivity())
                            .setTitle("HỦY ĐƯƠN HÀNG")
                            .setMessage("Bạn có thật sự muốn hủy đơn hàng này ?")
                            .setColoredCircle(R.color.dialogNoticeBackgroundColor)
                            .setDialogIconAndColor(R.drawable.ic_notice, R.color.white)
                            .setCancelable(true)
                            .setButtonText(getString(R.string.dialog_ok_button))
                            .setButtonBackgroundColor(R.color.dialogNoticeBackgroundColor)
                            .setButtonText(getString(R.string.dialog_ok_button))
                            .setWarningButtonClick(new Closure() {
                                @Override
                                public void exec() {
                                    new huy_don_hang("huy_don_hang","id_don_hang")
                                            .execute(ds_don_hang_cua_toi.get(postion_tmp).getMa_don_hang()+"");
                                }
                            })
                            .show();

                }
                else if(thongTinDonHangCuaToi.getId_ttdh()< -1)
                {
                    Toast.makeText(getActivity(),"Đơn hàng đã bị hủy trước đó", Toast.LENGTH_SHORT).show();
                }
                else if(thongTinDonHangCuaToi.getId_ttdh() > 0)
                {
                    Toast.makeText(getActivity(),"Không cho phép hủy!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chi_tiet_don_hang.dismiss();

            }
        });
        //RV_chi_tiet_don_hang.setText("#"+thongTinDonHangCuaToi.getId_don_hang());
        Chi_tiet_don_hang.show();
        new tai_ds_san_pham_chi_tiet("lay_danh_sach_chi_tiet_don_hang", "id_don_hang")
                .execute(ds_don_hang_cua_toi.get(postion_tmp).getMa_don_hang()+"");
    }
    private void hien_chi_tiet_san_pham_don_hang() {
        bcdDongChiTietSanPhamDonHang = new bcd_dong_chi_tiet_san_pham_don_hang(getActivity(), ds_san_pham_chi_tiet,  new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), mang_hinh_san_pham.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id_san_pham", ds_san_pham_chi_tiet.get(position).getId_san_pham());
                getActivity().startActivity(intent);
            }
        });
        RV_chi_tiet_don_hang.setAdapter(bcdDongChiTietSanPhamDonHang);
        RV_chi_tiet_don_hang.getLayoutManager().scrollToPosition(0);
    }
    private void thiet_lap_chi_tiet_don_hang() {
        Chi_tiet_don_hang = new Dialog(getActivity());
        Chi_tiet_don_hang.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Chi_tiet_don_hang.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Chi_tiet_don_hang.setContentView(R.layout.popup_chi_tiet_don_hang);
        Chi_tiet_don_hang.setCancelable(false);
        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        lWindowParams.copyFrom(Chi_tiet_don_hang.getWindow().getAttributes());
        lWindowParams.width = WindowManager.LayoutParams.FILL_PARENT; // this is where the magic happens
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        Chi_tiet_don_hang.getWindow().setAttributes(lWindowParams);
        tv_ma_don_hang  = (TextView) Chi_tiet_don_hang.findViewById(R.id.tv_ma_don_hang) ;
        tv_ngay_dat_hang  = (TextView) Chi_tiet_don_hang.findViewById(R.id.tv_ngay_dat_hang) ;
        tv_ten_phuong_thuc_van_chuyen  = (TextView) Chi_tiet_don_hang.findViewById(R.id.tv_ten_phuong_thuc_van_chuyen) ;
        tv_tinh_trang_don_hang  = (TextView) Chi_tiet_don_hang.findViewById(R.id.tv_tinh_trang_don_hang) ;
        tv_dia_chi  = (TextView) Chi_tiet_don_hang.findViewById(R.id.tv_dia_chi) ;
        tv_thoi_gian_giao  = (TextView) Chi_tiet_don_hang.findViewById(R.id.tv_thoi_gian_giao) ;
        tv_ma_van_chuyen  = (TextView) Chi_tiet_don_hang.findViewById(R.id.tv_ma_van_chuyen) ;
        tv_phi_van_chuyen  = (TextView) Chi_tiet_don_hang.findViewById(R.id.tv_phi_van_chuyen) ;
        tv_phi_thu_ho  = (TextView) Chi_tiet_don_hang.findViewById(R.id.tv_phi_thu_ho) ;
        tv_tien_thu_ho  = (TextView) Chi_tiet_don_hang.findViewById(R.id.tv_tien_thu_ho) ;
        tv_tong_tien_thanh_toan  = (TextView) Chi_tiet_don_hang.findViewById(R.id.tv_tong_tien_thanh_toan) ;
        tv_huy_don_hang  = (TextView) Chi_tiet_don_hang.findViewById(R.id.tv_huy_don_hang) ;
        RV_chi_tiet_don_hang  = (RecyclerView) Chi_tiet_don_hang.findViewById(R.id.RV_chi_tiet_don_hang) ;
        bt_thoat = (TextView) Chi_tiet_don_hang.findViewById(R.id.bt_thoat) ;
        tv_ngay_xac_nhan = (TextView) Chi_tiet_don_hang.findViewById(R.id.tv_ngay_xac_nhan);
        RV_chi_tiet_don_hang.addItemDecoration(new tao_khoang_trong_doc(1));
        RV_chi_tiet_don_hang.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        RV_chi_tiet_don_hang.setNestedScrollingEnabled(false);
    }
    public class tai_don_hang_cua_toi extends AsyncTask<String, Void, ArrayList<don_hang_cua_toi>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_don_hang_cua_toi(String method_DN, String PARAM_DN) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected ArrayList<don_hang_cua_toi> doInBackground(String... strings) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            boolean geted = false;
            int recall = 0;
            while (geted==false && recall < 5)
            {
                if(!isConnected()||getActivity().isFinishing()) cancel(true);
                recall++;
                try{
                    geted=true;
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
                    Log.d("123aaaaaaaaaaaaaaaaaa", data.toString());
                    don_hang_cua_toi[] ds_dhct_tmp = gson.fromJson(data.toString(),don_hang_cua_toi[].class);
                    ArrayList<don_hang_cua_toi> dsdhct_kq = new ArrayList<don_hang_cua_toi>(Arrays.asList(ds_dhct_tmp));
                    if(!isConnected()||getActivity().isFinishing()) cancel(true);
                    return dsdhct_kq;
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
        protected void onPostExecute(ArrayList<don_hang_cua_toi> s) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            if(s!=null && s.size()>0)
            {
                ds_don_hang_cua_toi=s;
                khoi_tao_danh_sach_don_hang();
            }
            super.onPostExecute(s);
        }
    }

    public class tai_thong_tin_don_hang extends AsyncTask<String, Void, thong_tin_don_hang_cua_toi> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_thong_tin_don_hang(String method_DN, String PARAM_DN) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected thong_tin_don_hang_cua_toi doInBackground(String... strings) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            boolean geted = false;
            int recall = 0;
            while (geted==false && recall < 5)
            {
                if(!isConnected()||getActivity().isFinishing()) cancel(true);
                recall++;
                try{
                    geted=true;
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
                    thong_tin_don_hang_cua_toi[] thong_tin_don_hang_cua_tois = gson.fromJson(data.toString(),thong_tin_don_hang_cua_toi[].class);
                    ArrayList<thong_tin_don_hang_cua_toi> thong_tin_don_hang_cua_toi_r = new ArrayList<thong_tin_don_hang_cua_toi>(Arrays.asList(thong_tin_don_hang_cua_tois));
                    if(!isConnected()||getActivity().isFinishing()) cancel(true);
                    return thong_tin_don_hang_cua_toi_r.get(0);
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
        protected void onPostExecute(thong_tin_don_hang_cua_toi s) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            if(s!=null)
            {
                Log.d("qweeerqewre", gson.toJson(s));
                thongTinDonHangCuaToi=s;
                hien_thi_thong_tin_don_hang();

            }
            super.onPostExecute(s);
        }
    }
    public class huy_don_hang extends AsyncTask<String, Void, String> {
        private String Method_DN;
        private String PARAM_DN = null;

        public huy_don_hang(String method_DN, String PARAM_DN) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected String doInBackground(String... strings) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
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
                jsonObject = new JSONObject(s.toString());
                Toast.makeText(getActivity(),jsonObject.getString("chi_tiet"),
                        Toast.LENGTH_SHORT).show();
                Chi_tiet_don_hang.dismiss();
                new tai_thong_tin_don_hang("lay_thong_tin_don_hang", "id_don_hang")
                        .execute(ds_don_hang_cua_toi.get(postion_tmp).getMa_don_hang()+"");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(s);
        }
    }

    public class tai_ds_san_pham_chi_tiet extends AsyncTask<String, Void, ArrayList<chi_tiet_san_pham_don_hang>> {
        private String Method_DN;
        private String PARAM_DN = null;

        public tai_ds_san_pham_chi_tiet(String method_DN, String PARAM_DN) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            Method_DN = method_DN;
            this.PARAM_DN = PARAM_DN;
        }
        protected  ArrayList<chi_tiet_san_pham_don_hang> doInBackground(String... strings) {
            boolean geted = false;
            int recall = 0;
            while (geted==false && recall < 5)
            {
                if(!isConnected()||getActivity().isFinishing()) cancel(true);
                recall++;
                try{
                    if(!isConnected()||getActivity().isFinishing()) cancel(true);
                    geted=true;
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
                    chi_tiet_san_pham_don_hang[] tmp = gson.fromJson(data.toString(),chi_tiet_san_pham_don_hang[].class);
                    ArrayList<chi_tiet_san_pham_don_hang> kq = new ArrayList<chi_tiet_san_pham_don_hang>(Arrays.asList(tmp));
                    if(!isConnected()||getActivity().isFinishing()) cancel(true);
                    return kq;
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
        protected void onPostExecute( ArrayList<chi_tiet_san_pham_don_hang> s) {
            if(!isConnected()||getActivity().isFinishing()) cancel(true);
            if(s!=null)
            {
                ds_san_pham_chi_tiet=s;
                Log.d("em_day_roi123", gson.toJson(s));
                hien_chi_tiet_san_pham_don_hang();
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