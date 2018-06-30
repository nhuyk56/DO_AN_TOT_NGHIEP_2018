using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.nguoi_dung
{
    public class nguoi
    {

        public string email_nguoi { get; set; }

        public string mat_khau_nguoi { get; set; }

        public string ten_nguoi { get; set; }

        public string ngay_sinh { get; set; }

        public string dia_chi_nguoi { get; set; }

        public string sdt_nguoi { get; set; }

        public bool gioi_tinh_nguoi { get; set; }

        public nguoi(
        string email_nguoi,
        string mat_khau_nguoi,
        string ten_nguoi,
        string ngay_sinh,
        string dia_chi_nguoi,
        string sdt_nguoi,
        bool gioi_tinh_nguoi)
        {

            this.email_nguoi = email_nguoi;

            this.mat_khau_nguoi = mat_khau_nguoi;

            this.ten_nguoi = ten_nguoi;

            this.ngay_sinh = ngay_sinh;

            this.dia_chi_nguoi = dia_chi_nguoi;

            this.sdt_nguoi = sdt_nguoi;

            this.gioi_tinh_nguoi = gioi_tinh_nguoi;
        }
        public string getemail_nguoi()
        {
            return email_nguoi;

        }
        public string getmat_khau_nguoi()
        {
            return mat_khau_nguoi;

        }
        public string getten_nguoi()
        {
            return ten_nguoi;

        }
        public string getngay_sinh()
        {
            return ngay_sinh;

        }
        public string getdia_chi_nguoi()
        {
            return dia_chi_nguoi;

        }
        public string getsdt_nguoi()
        {
            return sdt_nguoi;

        }
        public bool getgioi_tinh_nguoi()
        {
            return gioi_tinh_nguoi;

        }
        public void setemail_nguoi(string email_nguoi)
        {
            this.email_nguoi = email_nguoi;

        }
        public void setmat_khau_nguoi(string mat_khau_nguoi)
        {
            this.mat_khau_nguoi = mat_khau_nguoi;

        }
        public void setten_nguoi(string ten_nguoi)
        {
            this.ten_nguoi = ten_nguoi;

        }
        public void setngay_sinh(string ngay_sinh)
        {
            this.ngay_sinh = ngay_sinh;

        }
        public void setdia_chi_nguoi(string dia_chi_nguoi)
        {
            this.dia_chi_nguoi = dia_chi_nguoi;

        }
        public void setsdt_nguoi(string sdt_nguoi)
        {
            this.sdt_nguoi = sdt_nguoi;

        }
        public void setgioi_tinh_nguoi(bool gioi_tinh_nguoi)
        {
            this.gioi_tinh_nguoi = gioi_tinh_nguoi;

        }



    }
}