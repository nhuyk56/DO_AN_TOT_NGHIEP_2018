using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.nguoi_dung
{
    public class dang_nhap
    {

        public string email_nguoi { get; set; }

        public string mat_khau_nguoi { get; set; }

        public dang_nhap(
        string email_nguoi,
        string mat_khau_nguoi)
        {

            this.email_nguoi = email_nguoi;

            this.mat_khau_nguoi = mat_khau_nguoi;
        }
        public string getemail_nguoi()
        {
            return email_nguoi;

        }
        public string getmat_khau_nguoi()
        {
            return mat_khau_nguoi;

        }
        public void setemail_nguoi(string email_nguoi)
        {
            this.email_nguoi = email_nguoi;

        }
        public void setmat_khau_nguoi(string mat_khau_nguoi)
        {
            this.mat_khau_nguoi = mat_khau_nguoi;

        }

    }
}