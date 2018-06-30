using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT
{
    public class thong_bao
    {

        public bool tinh_trang { get; set; }

        public string chi_tiet { get; set; }

        public dynamic du_lieu { get; set; }

        public thong_bao(
        bool tinh_trang,
        string chi_tiet,
        dynamic du_lieu)
        {
            this.du_lieu = new System.Dynamic.ExpandoObject();
            this.tinh_trang = tinh_trang;

            this.chi_tiet = chi_tiet;

            this.du_lieu = du_lieu;
        }
        public bool gettinh_trang()
        {
            return tinh_trang;

        }
        public string getchi_tiet()
        {
            return chi_tiet;

        }
        public dynamic getdu_lieu()
        {
            return du_lieu;

        }
        public void settinh_trang(bool tinh_trang)
        {
            this.tinh_trang = tinh_trang;

        }
        public void setchi_tiet(string chi_tiet)
        {
            this.chi_tiet = chi_tiet;

        }
        public void setdu_lieu(dynamic du_lieu)
        {
            this.du_lieu = du_lieu;

        }

    }
}