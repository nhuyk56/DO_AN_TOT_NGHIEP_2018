using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.san_pham
{
    public class yeu_cau_san_pham
    {

        public int id_nguoi { get; set; }

        public int id_san_pham { get; set; }

        public yeu_cau_san_pham(
        int id_nguoi,
        int id_san_pham)
        {

            this.id_nguoi = id_nguoi;

            this.id_san_pham = id_san_pham;
        }
        public int getid_nguoi()
        {
            return id_nguoi;

        }
        public int getid_san_pham()
        {
            return id_san_pham;

        }
        public void setid_nguoi(int id_nguoi)
        {
            this.id_nguoi = id_nguoi;

        }
        public void setid_san_pham(int id_san_pham)
        {
            this.id_san_pham = id_san_pham;

        }

    }
}