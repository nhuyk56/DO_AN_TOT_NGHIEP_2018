using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.yeu_cau_danh_sach
{
    public class phan_trang
    {

        public int vi_tri_bat_dau { get; set; }

        public int vi_tri_ket_thuc { get; set; }

        public phan_trang(
        int vi_tri_bat_dau,
        int vi_tri_ket_thuc)
        {

            this.vi_tri_bat_dau = vi_tri_bat_dau;

            this.vi_tri_ket_thuc = vi_tri_ket_thuc;
        }
        public int getvi_tri_bat_dau()
        {
            return vi_tri_bat_dau;

        }
        public int getvi_tri_ket_thuc()
        {
            return vi_tri_ket_thuc;

        }
        public void setvi_tri_bat_dau(int vi_tri_bat_dau)
        {
            this.vi_tri_bat_dau = vi_tri_bat_dau;

        }
        public void setvi_tri_ket_thuc(int vi_tri_ket_thuc)
        {
            this.vi_tri_ket_thuc = vi_tri_ket_thuc;

        }

    }
}