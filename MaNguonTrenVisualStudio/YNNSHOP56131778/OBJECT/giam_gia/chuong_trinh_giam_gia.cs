using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
namespace WebApplication2.YNNSHOP56131778.OBJECT.giam_gia
{
    public class chuong_trinh_giam_gia
    {

        public int id_giam_gia { get; set; }

        public string ma_giam_gia { get; set; }

        public string ten_giam_gia { get; set; }

        public string ly_do_giam_gia { get; set; }

        public string thoi_diem_bat_dau_giam_gia { get; set; }

        public string thoi_diem_ket_thuc_giam_gia { get; set; }

        public List<sp_ap_dung> ds_sp_ap_dung { get; set; }

        public chuong_trinh_giam_gia(
        int id_giam_gia,
        string ma_giam_gia,
        string ten_giam_gia,
        string ly_do_giam_gia,
        string thoi_diem_bat_dau_giam_gia,
        string thoi_diem_ket_thuc_giam_gia,
        List<sp_ap_dung> ds_sp_ap_dung)
        {

            this.id_giam_gia = id_giam_gia;

            this.ma_giam_gia = ma_giam_gia;

            this.ten_giam_gia = ten_giam_gia;

            this.ly_do_giam_gia = ly_do_giam_gia;

            this.thoi_diem_bat_dau_giam_gia = thoi_diem_bat_dau_giam_gia;

            this.thoi_diem_ket_thuc_giam_gia = thoi_diem_ket_thuc_giam_gia;

            this.ds_sp_ap_dung = ds_sp_ap_dung;
        }
        public int getid_giam_gia()
        {
            return id_giam_gia;

        }
        public string getma_giam_gia()
        {
            return ma_giam_gia;

        }
        public string getten_giam_gia()
        {
            return ten_giam_gia;

        }
        public string getly_do_giam_gia()
        {
            return ly_do_giam_gia;

        }
        public string getthoi_diem_bat_dau_giam_gia()
        {
            return thoi_diem_bat_dau_giam_gia;

        }
        public string getthoi_diem_ket_thuc_giam_gia()
        {
            return thoi_diem_ket_thuc_giam_gia;

        }
        public List<sp_ap_dung> getds_sp_ap_dung()
        {
            return ds_sp_ap_dung;

        }
        public void setid_giam_gia(int id_giam_gia)
        {
            this.id_giam_gia = id_giam_gia;

        }
        public void setma_giam_gia(string ma_giam_gia)
        {
            this.ma_giam_gia = ma_giam_gia;

        }
        public void setten_giam_gia(string ten_giam_gia)
        {
            this.ten_giam_gia = ten_giam_gia;

        }
        public void setly_do_giam_gia(string ly_do_giam_gia)
        {
            this.ly_do_giam_gia = ly_do_giam_gia;

        }
        public void setthoi_diem_bat_dau_giam_gia(string thoi_diem_bat_dau_giam_gia)
        {
            this.thoi_diem_bat_dau_giam_gia = thoi_diem_bat_dau_giam_gia;

        }
        public void setthoi_diem_ket_thuc_giam_gia(string thoi_diem_ket_thuc_giam_gia)
        {
            this.thoi_diem_ket_thuc_giam_gia = thoi_diem_ket_thuc_giam_gia;

        }
        public void setds_sp_ap_dung(List<sp_ap_dung> ds_sp_ap_dung)
        {
            this.ds_sp_ap_dung = ds_sp_ap_dung;

        }

    }
}