using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.yeu_cau_danh_sach
{
    public class loai_sp
    {
         public int id_loai_san_pham { get; set; }

        public string ten_loai_san_pham { get; set; }

        public loai_sp(
        int id_loai_san_pham,
        string ten_loai_san_pham)
        {

            this.id_loai_san_pham = id_loai_san_pham;

            this.ten_loai_san_pham = ten_loai_san_pham;
        }
        public int getid_loai_san_pham()
        {
            return id_loai_san_pham;

        }
        public string getten_loai_san_pham()
        {
            return ten_loai_san_pham;

        }
        public void setid_loai_san_pham(int id_loai_san_pham)
        {
            this.id_loai_san_pham = id_loai_san_pham;

        }
        public void setten_loai_san_pham(string ten_loai_san_pham)
        {
            this.ten_loai_san_pham = ten_loai_san_pham;

        }
    }
}