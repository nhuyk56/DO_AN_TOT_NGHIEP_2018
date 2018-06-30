using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.thong_tin_don_hang
{
    public class don_hang
    {
          //public int id_don_hang {get; set;}
          public int id_nguoi {get; set;}
          public int id_ptvc {get; set;}
          public int id_khu_vuc {get; set;}
          public long phi_van_chuyen {get; set;}
          public long phi_thu_ho {get; set;}
          public long tong_tien {get; set;}
          public float so_ngay_giao_gan_nhat {get; set;}
          public float so_ngay_giao_xa_nhat {get; set;}
          public string dia_chi_nhan_hang_chi_tiet { get; set; }
          public List<san_pham_don_hang> chi_tiet_don_hang  { get; set; }
    }
}