using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT
{
    public class c1_item
    {

        public int id_loai_san_pham { get; set; }

        public string ten_loai_san_pham { get; set; }

        public int cap_do_loai_san_pham { get; set; }

        public int id_cha_loai_san_pham { get; set; }

        public List<c2_group> list_c2_group { get; set; }

        public c1_item(
        int id_loai_san_pham,
        string ten_loai_san_pham,
        int cap_do_loai_san_pham,
        int id_cha_loai_san_pham,
        List<c2_group> list_c2_group)
        {

            this.id_loai_san_pham = id_loai_san_pham;

            this.ten_loai_san_pham = ten_loai_san_pham;

            this.cap_do_loai_san_pham = cap_do_loai_san_pham;

            this.id_cha_loai_san_pham = id_cha_loai_san_pham;

            this.list_c2_group = list_c2_group;
        }
        public int getid_loai_san_pham()
        {
            return id_loai_san_pham;

        }
        public string getten_loai_san_pham()
        {
            return ten_loai_san_pham;

        }
        public int getcap_do_loai_san_pham()
        {
            return cap_do_loai_san_pham;

        }
        public int getid_cha_loai_san_pham()
        {
            return id_cha_loai_san_pham;

        }
        public List<c2_group> getlist_c2_group()
        {
            return list_c2_group;

        }
        public void setid_loai_san_pham(int id_loai_san_pham)
        {
            this.id_loai_san_pham = id_loai_san_pham;

        }
        public void setten_loai_san_pham(string ten_loai_san_pham)
        {
            this.ten_loai_san_pham = ten_loai_san_pham;

        }
        public void setcap_do_loai_san_pham(int cap_do_loai_san_pham)
        {
            this.cap_do_loai_san_pham = cap_do_loai_san_pham;

        }
        public void setid_cha_loai_san_pham(int id_cha_loai_san_pham)
        {
            this.id_cha_loai_san_pham = id_cha_loai_san_pham;

        }
        public void setlist_c2_group(List<c2_group> list_c2_group)
        {
            this.list_c2_group = list_c2_group;

        }

    }
}