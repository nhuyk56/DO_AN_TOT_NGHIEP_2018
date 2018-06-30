using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.gio_hang
{
    public class sp_mgg
    {
        public int id_san_pham { get; set; }

        public string ma_giam_gia { get; set; }

        public sp_mgg(
        int id_san_pham,
        string ma_giam_gia)
        {

            this.id_san_pham = id_san_pham;

            this.ma_giam_gia = ma_giam_gia;
        }
        public int getid_san_pham()
        {
            return id_san_pham;

        }
        public string getma_giam_gia()
        {
            return ma_giam_gia;

        }
        public void setid_san_pham(int id_san_pham)
        {
            this.id_san_pham = id_san_pham;

        }
        public void setma_giam_gia(string ma_giam_gia)
        {
            this.ma_giam_gia = ma_giam_gia;

        }

    }
}