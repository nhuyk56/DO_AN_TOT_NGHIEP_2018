using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.yeu_cau_danh_sach
{
    public class bo_loc
    {
        public List<loai_sp> ds_loai_sp { get; set; }

        public List<thuong_hieu> ds_thuong_hieu { get; set; }

        public gia_san_pham gia_sp { get; set; }

        public danh_gia danh_gia { get; set; }

        public string tim_kiem { get; set; }

        public phan_trang phan_trang { get; set; }

        public bo_loc(
        List<loai_sp> ds_loai_sp,
        List<thuong_hieu> ds_thuong_hieu,
        gia_san_pham gia_sp,
        danh_gia danh_gia,
        string tim_kiem,
        phan_trang phan_trang)
        {

            this.ds_loai_sp = ds_loai_sp;

            this.ds_thuong_hieu = ds_thuong_hieu;

            this.gia_sp = gia_sp;

            this.danh_gia = danh_gia;

            this.tim_kiem = tim_kiem;

            this.phan_trang = phan_trang;
        }
        public List<loai_sp> getds_loai_sp()
        {
            return ds_loai_sp;

        }
        public List<thuong_hieu> getds_thuong_hieu()
        {
            return ds_thuong_hieu;

        }
        public gia_san_pham getgia_sp()
        {
            return gia_sp;

        }
        public danh_gia getdanh_gia()
        {
            return danh_gia;

        }
        public string gettim_kiem()
        {
            return tim_kiem;

        }
        public phan_trang getphan_trang()
        {
            return phan_trang;

        }
        public void setds_loai_sp(List<loai_sp> ds_loai_sp)
        {
            this.ds_loai_sp = ds_loai_sp;

        }
        public void setds_thuong_hieu(List<thuong_hieu> ds_thuong_hieu)
        {
            this.ds_thuong_hieu = ds_thuong_hieu;

        }
        public void setgia_sp(gia_san_pham gia_sp)
        {
            this.gia_sp = gia_sp;

        }
        public void setdanh_gia(danh_gia danh_gia)
        {
            this.danh_gia = danh_gia;

        }
        public void settim_kiem(string tim_kiem)
        {
            this.tim_kiem = tim_kiem;

        }
        public void setphan_trang(phan_trang phan_trang)
        {
            this.phan_trang = phan_trang;

        }

    }
}