using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT
{
    public class c0_item
    {
        //thêm một phần tử rỗng vào đầu của list c1 để làm header

        public int id_loai_san_pham { get; set; }

        public string ten_loai_san_pham { get; set; }

        public string anh_loai_san_pham { get; set; }

        public string bieu_tuong_loai_san_pham { get; set; }

        public int cap_do_loai_san_pham { get; set; }

        public int id_cha_loai_san_pham { get; set; }

        public List<c1_item> list_c1_item { get; set; }

        public List<thuong_hieu> list_thuong_hieu { get; set; }
        public List<c2_group_item> list_chi_danh_cho_ban { get; set; }
        public c0_item(
        int id_loai_san_pham,
        string ten_loai_san_pham,
        string anh_loai_san_pham,
        string bieu_tuong_loai_san_pham,
        int cap_do_loai_san_pham,
        int id_cha_loai_san_pham,
        List<c1_item> list_c1_item,
        List<thuong_hieu> list_thuong_hieu)
        {

            this.id_loai_san_pham = id_loai_san_pham;

            this.ten_loai_san_pham = ten_loai_san_pham;

            this.anh_loai_san_pham = anh_loai_san_pham;

            this.bieu_tuong_loai_san_pham = bieu_tuong_loai_san_pham;

            this.cap_do_loai_san_pham = cap_do_loai_san_pham;

            this.id_cha_loai_san_pham = id_cha_loai_san_pham;

            this.list_c1_item = list_c1_item;

            this.list_thuong_hieu = list_thuong_hieu;
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
        public string getbieu_tuong_loai_san_pham()
        {
            return bieu_tuong_loai_san_pham;

        }
        public int getcap_do_loai_san_pham()
        {
            return cap_do_loai_san_pham;

        }
        public int getid_cha_loai_san_pham()
        {
            return id_cha_loai_san_pham;

        }
        public List<c1_item> getlist_c1_item()
        {
            return list_c1_item;

        }
        public List<thuong_hieu> getlist_thuong_hieu()
        {
            return list_thuong_hieu;

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
        public void setbieu_tuong_loai_san_pham(string bieu_tuong_loai_san_pham)
        {
            this.bieu_tuong_loai_san_pham = bieu_tuong_loai_san_pham;

        }
        public void setcap_do_loai_san_pham(int cap_do_loai_san_pham)
        {
            this.cap_do_loai_san_pham = cap_do_loai_san_pham;

        }
        public void setid_cha_loai_san_pham(int id_cha_loai_san_pham)
        {
            this.id_cha_loai_san_pham = id_cha_loai_san_pham;

        }
        public void setlist_c1_item(List<c1_item> list_c1_item)
        {
            this.list_c1_item = list_c1_item;

        }
        public void setlist_thuong_hieu(List<thuong_hieu> list_thuong_hieu)
        {
            this.list_thuong_hieu = list_thuong_hieu;

        }

    }
}