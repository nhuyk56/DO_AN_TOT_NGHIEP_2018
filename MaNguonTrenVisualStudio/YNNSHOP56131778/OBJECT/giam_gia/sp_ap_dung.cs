using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.giam_gia
{
    public class sp_ap_dung
    {

        public int id_san_pham { get; set; }

        public string ten_san_pham { get; set; }

        public string anh_san_pham { get; set; }

        public long gia_ban_ra { get; set; }

        public float phan_tram_giam { get; set; }

        public long giam_toi_da { get; set; }

        public sp_ap_dung(
        int id_san_pham,
        string ten_san_pham,
        string anh_san_pham,
        long gia_ban_ra,
        float phan_tram_giam,
        long giam_toi_da)
        {

            this.id_san_pham = id_san_pham;

            this.ten_san_pham = ten_san_pham;

            this.anh_san_pham = anh_san_pham;

            this.gia_ban_ra = gia_ban_ra;

            this.phan_tram_giam = phan_tram_giam;

            this.giam_toi_da = giam_toi_da;
        }
        public int getid_san_pham()
        {
            return id_san_pham;

        }
        public string getten_san_pham()
        {
            return ten_san_pham;

        }
        public string getanh_san_pham()
        {
            return anh_san_pham;

        }
        public long getgia_ban_ra()
        {
            return gia_ban_ra;

        }
        public float getphan_tram_giam()
        {
            return phan_tram_giam;

        }
        public long getgiam_toi_da()
        {
            return giam_toi_da;

        }
        public void setid_san_pham(int id_san_pham)
        {
            this.id_san_pham = id_san_pham;

        }
        public void setten_san_pham(string ten_san_pham)
        {
            this.ten_san_pham = ten_san_pham;

        }
        public void setanh_san_pham(string anh_san_pham)
        {
            this.anh_san_pham = anh_san_pham;

        }
        public void setgia_ban_ra(long gia_ban_ra)
        {
            this.gia_ban_ra = gia_ban_ra;

        }
        public void setphan_tram_giam(float phan_tram_giam)
        {
            this.phan_tram_giam = phan_tram_giam;

        }
        public void setgiam_toi_da(long giam_toi_da)
        {
            this.giam_toi_da = giam_toi_da;

        }


    }
}