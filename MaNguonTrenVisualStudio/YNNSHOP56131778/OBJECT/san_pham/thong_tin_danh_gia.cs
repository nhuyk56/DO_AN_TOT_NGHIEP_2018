using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.san_pham
{
    public class thong_tin_danh_gia
    {
        public int id_danh_gia { get; set; }

        public bool da_mua { get; set; }

        public string tieu_de_danh_gia { get; set; }

        public int so_sao_danh_gia { get; set; }

        public string noi_dung { get; set; }

        public string ngay_viet_danh_gia { get; set; }

        public string ten_nguoi { get; set; }

        public thong_tin_danh_gia(
        int id_danh_gia,
        bool da_mua,
        string tieu_de_danh_gia,
        int so_sao_danh_gia,
        string noi_dung,
        string ngay_viet_danh_gia,
        string ten_nguoi)
        {

            this.id_danh_gia = id_danh_gia;

            this.da_mua = da_mua;

            this.tieu_de_danh_gia = tieu_de_danh_gia;

            this.so_sao_danh_gia = so_sao_danh_gia;

            this.noi_dung = noi_dung;

            this.ngay_viet_danh_gia = ngay_viet_danh_gia;

            this.ten_nguoi = ten_nguoi;
        }
        public int getid_danh_gia()
        {
            return id_danh_gia;

        }
        public bool getda_mua()
        {
            return da_mua;

        }
        public string gettieu_de_danh_gia()
        {
            return tieu_de_danh_gia;

        }
        public int getso_sao_danh_gia()
        {
            return so_sao_danh_gia;

        }
        public string getnoi_dung()
        {
            return noi_dung;

        }
        public string getngay_viet_danh_gia()
        {
            return ngay_viet_danh_gia;

        }
        public string getten_nguoi()
        {
            return ten_nguoi;

        }
        public void setid_danh_gia(int id_danh_gia)
        {
            this.id_danh_gia = id_danh_gia;

        }
        public void setda_mua(bool da_mua)
        {
            this.da_mua = da_mua;

        }
        public void settieu_de_danh_gia(string tieu_de_danh_gia)
        {
            this.tieu_de_danh_gia = tieu_de_danh_gia;

        }
        public void setso_sao_danh_gia(int so_sao_danh_gia)
        {
            this.so_sao_danh_gia = so_sao_danh_gia;

        }
        public void setnoi_dung(string noi_dung)
        {
            this.noi_dung = noi_dung;

        }
        public void setngay_viet_danh_gia(string ngay_viet_danh_gia)
        {
            this.ngay_viet_danh_gia = ngay_viet_danh_gia;

        }
        public void setten_nguoi(string ten_nguoi)
        {
            this.ten_nguoi = ten_nguoi;

        }

    }
}