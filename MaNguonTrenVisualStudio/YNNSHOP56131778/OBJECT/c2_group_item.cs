using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT
{
    public class c2_group_item
    {

        public int id_loai_san_pham { get; set; }

        public string ten_loai_san_pham { get; set; }

        public string anh_loai_san_pham { get; set; }

        public int cap_do_loai_san_pham { get; set; }

        public int id_cha_loai_san_pham { get; set; }

        public c2_group_item(
        int id_loai_san_pham,
        string ten_loai_san_pham,
        string anh_loai_san_pham,
        int cap_do_loai_san_pham,
        int id_cha_loai_san_pham)
        {

            this.id_loai_san_pham = id_loai_san_pham;

            this.ten_loai_san_pham = ten_loai_san_pham;

            this.anh_loai_san_pham = anh_loai_san_pham;

            this.cap_do_loai_san_pham = cap_do_loai_san_pham;

            this.id_cha_loai_san_pham = id_cha_loai_san_pham;
        }
        public int getid_loai_san_pham()
        {
            return id_loai_san_pham;

        }
        public string getten_loai_san_pham()
        {
            return ten_loai_san_pham;

        }
        public string getanh_loai_san_pham()
        {
            return anh_loai_san_pham;

        }
        public int getcap_do_loai_san_pham()
        {
            return cap_do_loai_san_pham;

        }
        public int getid_cha_loai_san_pham()
        {
            return id_cha_loai_san_pham;

        }
        public void setid_loai_san_pham(int id_loai_san_pham)
        {
            this.id_loai_san_pham = id_loai_san_pham;

        }
        public void setten_loai_san_pham(string ten_loai_san_pham)
        {
            this.ten_loai_san_pham = ten_loai_san_pham;

        }
        public void setanh_loai_san_pham(string anh_loai_san_pham)
        {
            this.anh_loai_san_pham = anh_loai_san_pham;

        }
        public void setcap_do_loai_san_pham(int cap_do_loai_san_pham)
        {
            this.cap_do_loai_san_pham = cap_do_loai_san_pham;

        }
        public void setid_cha_loai_san_pham(int id_cha_loai_san_pham)
        {
            this.id_cha_loai_san_pham = id_cha_loai_san_pham;

        }


    }
}