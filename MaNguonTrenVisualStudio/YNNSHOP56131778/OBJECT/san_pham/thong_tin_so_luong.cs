using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.san_pham
{
    public class thong_tin_so_luong
    {

        public int id_kho_hang { get; set; }

        public string ten_kho_hang { get; set; }

        public string dia_chi_kho_hang { get; set; }

        public int so_luong { get; set; }

        public int id_san_pham { get; set; }

        public thong_tin_so_luong(
        int id_kho_hang,
        string ten_kho_hang,
        string dia_chi_kho_hang,
        int so_luong,
        int id_san_pham)
        {

            this.id_kho_hang = id_kho_hang;

            this.ten_kho_hang = ten_kho_hang;

            this.dia_chi_kho_hang = dia_chi_kho_hang;

            this.so_luong = so_luong;

            this.id_san_pham = id_san_pham;
        }
        public int getid_kho_hang()
        {
            return id_kho_hang;

        }
        public string getten_kho_hang()
        {
            return ten_kho_hang;

        }
        public string getdia_chi_kho_hang()
        {
            return dia_chi_kho_hang;

        }
        public int getso_luong()
        {
            return so_luong;

        }
        public int getid_san_pham()
        {
            return id_san_pham;

        }
        public void setid_kho_hang(int id_kho_hang)
        {
            this.id_kho_hang = id_kho_hang;

        }
        public void setten_kho_hang(string ten_kho_hang)
        {
            this.ten_kho_hang = ten_kho_hang;

        }
        public void setdia_chi_kho_hang(string dia_chi_kho_hang)
        {
            this.dia_chi_kho_hang = dia_chi_kho_hang;

        }
        public void setso_luong(int so_luong)
        {
            this.so_luong = so_luong;

        }
        public void setid_san_pham(int id_san_pham)
        {
            this.id_san_pham = id_san_pham;

        }

    }
}