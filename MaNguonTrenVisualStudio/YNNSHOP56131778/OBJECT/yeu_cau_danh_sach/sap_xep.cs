using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.yeu_cau_danh_sach
{
    public class sap_xep
    {
        public string id_sap_xep { get; set; }

        public string ten_sap_xep { get; set; }

        public bool loai_sap_xep { get; set; }

        public sap_xep(
        string id_sap_xep,
        string ten_sap_xep,
        bool loai_sap_xep)
        {

            this.id_sap_xep = id_sap_xep;

            this.ten_sap_xep = ten_sap_xep;

            this.loai_sap_xep = loai_sap_xep;
        }
        public string getid_sap_xep()
        {
            return id_sap_xep;

        }
        public string getten_sap_xep()
        {
            return ten_sap_xep;

        }
        public bool getloai_sap_xep()
        {
            return loai_sap_xep;

        }
        public void setid_sap_xep(string id_sap_xep)
        {
            this.id_sap_xep = id_sap_xep;

        }
        public void setten_sap_xep(string ten_sap_xep)
        {
            this.ten_sap_xep = ten_sap_xep;

        }
        public void setloai_sap_xep(bool loai_sap_xep)
        {
            this.loai_sap_xep = loai_sap_xep;

        }

    }
}