using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WebApplication2.YNNSHOP56131778.OBJECT.yeu_cau_danh_sach;
namespace WebApplication2.YNNSHOP56131778.OBJECT.san_pham
{
    public class san_pham
    {

        public int id_user { get; set; }

        public bool tha_tim { get; set; }

        public int id_san_pham { get; set; }

        public string ten_san_pham { get; set; }//

        public thuong_hieu thuong_hieu { get; set; }

        public List<loai_sp> danh_sach_loai_san_pham { get; set; }

        public List<string> danh_sach_anh { get; set; }

        public long gia_cao { get; set; }

        public long gia_ban_ra { get; set; }

        public string mo_ta_san_pham_html { get; set; }

        public string dac_tinh_san_pham { get; set; }

        public float khoi_luong_san_pham_gram { get; set; }

        public bool da_co_vat { get; set; }

        public int so_tim { get; set; }

        public List<thong_tin_so_luong> thong_tin_so_luong { get; set; }

        public List<thong_tin_danh_gia> danh_sach_danh_gia { get; set; }

        public san_pham(
        int id_user,
        bool tha_tim,
        int id_san_pham,
        string ten_san_pham,
        thuong_hieu thuong_hieu,
        List<loai_sp> danh_sach_loai_san_pham,
        List<string> danh_sach_anh,
        long gia_cao,
        long gia_ban_ra,
        string mo_ta_san_pham_html,
        string dac_tinh_san_pham,
        float khoi_luong_san_pham_gram,
        bool da_co_vat,
        int so_tim,
        List<thong_tin_so_luong> thong_tin_so_luong,
        List<thong_tin_danh_gia> danh_sach_danh_gia)
        {

            this.id_user = id_user;

            this.tha_tim = tha_tim;

            this.id_san_pham = id_san_pham;

            this.ten_san_pham = ten_san_pham;

            this.thuong_hieu = thuong_hieu;

            this.danh_sach_loai_san_pham = danh_sach_loai_san_pham;

            this.danh_sach_anh = danh_sach_anh;

            this.gia_cao = gia_cao;

            this.gia_ban_ra = gia_ban_ra;

            this.mo_ta_san_pham_html = mo_ta_san_pham_html;

            this.dac_tinh_san_pham = dac_tinh_san_pham;

            this.khoi_luong_san_pham_gram = khoi_luong_san_pham_gram;

            this.da_co_vat = da_co_vat;

            this.so_tim = so_tim;

            this.thong_tin_so_luong = thong_tin_so_luong;

            this.danh_sach_danh_gia = danh_sach_danh_gia;

        }
        public int getid_user()
        {
            return id_user;

        }
        public bool gettha_tim()
        {
            return tha_tim;

        }
        public int getid_san_pham()
        {
            return id_san_pham;

        }
        public string getten_san_pham()
        {
            return ten_san_pham;

        }
        public thuong_hieu getthuong_hieu()
        {
            return thuong_hieu;

        }
        public List<loai_sp> getdanh_sach_loai_san_pham()
        {
            return danh_sach_loai_san_pham;

        }
        public List<string> getdanh_sach_anh()
        {
            return danh_sach_anh;

        }
        public long getgia_cao()
        {
            return gia_cao;

        }
        public long getgia_ban_ra()
        {
            return gia_ban_ra;

        }
        public string getmo_ta_san_pham_html()
        {
            return mo_ta_san_pham_html;

        }
        public string getdac_tinh_san_pham()
        {
            return dac_tinh_san_pham;

        }
        public float getkhoi_luong_san_pham_gram()
        {
            return khoi_luong_san_pham_gram;

        }
        public bool getda_co_vat()
        {
            return da_co_vat;

        }
        public int getso_tim()
        {
            return so_tim;

        }
        public List<thong_tin_so_luong> getthong_tin_so_luong()
        {
            return thong_tin_so_luong;

        }
        public List<thong_tin_danh_gia> getdanh_sach_danh_gia()
        {
            return danh_sach_danh_gia;

        }
        public void setid_user(int id_user)
        {
            this.id_user = id_user;

        }
        public void settha_tim(bool tha_tim)
        {
            this.tha_tim = tha_tim;

        }
        public void setid_san_pham(int id_san_pham)
        {
            this.id_san_pham = id_san_pham;

        }
        public void setten_san_pham(string ten_san_pham)
        {
            this.ten_san_pham = ten_san_pham;

        }
        public void setthuong_hieu(thuong_hieu thuong_hieu)
        {
            this.thuong_hieu = thuong_hieu;

        }
        public void setdanh_sach_loai_san_pham(List<loai_sp> danh_sach_loai_san_pham)
        {
            this.danh_sach_loai_san_pham = danh_sach_loai_san_pham;

        }
        public void setdanh_sach_anh(List<string> danh_sach_anh)
        {
            this.danh_sach_anh = danh_sach_anh;

        }
        public void setgia_cao(long gia_cao)
        {
            this.gia_cao = gia_cao;

        }
        public void setgia_ban_ra(long gia_ban_ra)
        {
            this.gia_ban_ra = gia_ban_ra;

        }
        public void setmo_ta_san_pham_html(string mo_ta_san_pham_html)
        {
            this.mo_ta_san_pham_html = mo_ta_san_pham_html;

        }
        public void setdac_tinh_san_pham(string dac_tinh_san_pham)
        {
            this.dac_tinh_san_pham = dac_tinh_san_pham;

        }
        public void setkhoi_luong_san_pham_gram(float khoi_luong_san_pham_gram)
        {
            this.khoi_luong_san_pham_gram = khoi_luong_san_pham_gram;

        }
        public void setda_co_vat(bool da_co_vat)
        {
            this.da_co_vat = da_co_vat;

        }
        public void setso_tim(int so_tim)
        {
            this.so_tim = so_tim;

        }
        public void setthong_tin_so_luong(List<thong_tin_so_luong> thong_tin_so_luong)
        {
            this.thong_tin_so_luong = thong_tin_so_luong;

        }
        public void setdanh_sach_danh_gia(List<thong_tin_danh_gia> danh_sach_danh_gia)
        {
            this.danh_sach_danh_gia = danh_sach_danh_gia;

        }


    }
}