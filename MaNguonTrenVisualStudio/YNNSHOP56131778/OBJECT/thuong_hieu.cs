using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT
{
    public class thuong_hieu
    {

        public int id_thuong_hieu { get; set; }

        public string ten_thuong_hieu { get; set; }

        public string anh_thuong_hieu { get; set; }

        public thuong_hieu(
        int id_thuong_hieu,
        string ten_thuong_hieu,
        string anh_thuong_hieu)
        {

            this.id_thuong_hieu = id_thuong_hieu;

            this.ten_thuong_hieu = ten_thuong_hieu;

            this.anh_thuong_hieu = anh_thuong_hieu;
        }
        public int getid_thuong_hieu()
        {
            return id_thuong_hieu;

        }
        public string getten_thuong_hieu()
        {
            return ten_thuong_hieu;

        }
        public string getanh_thuong_hieu()
        {
            return anh_thuong_hieu;

        }
        public void setid_thuong_hieu(int id_thuong_hieu)
        {
            this.id_thuong_hieu = id_thuong_hieu;

        }
        public void setten_thuong_hieu(string ten_thuong_hieu)
        {
            this.ten_thuong_hieu = ten_thuong_hieu;

        }
        public void setanh_thuong_hieu(string anh_thuong_hieu)
        {
            this.anh_thuong_hieu = anh_thuong_hieu;

        }

    }
}