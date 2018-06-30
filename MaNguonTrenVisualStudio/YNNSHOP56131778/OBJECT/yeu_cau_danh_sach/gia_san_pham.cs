using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.yeu_cau_danh_sach
{
    public class gia_san_pham
    {

        public long gia_thap_nhat { get; set; }

        public long gia_cao_nhat { get; set; }

        public gia_san_pham(
        long gia_thap_nhat,
        long gia_cao_nhat)
        {

            this.gia_thap_nhat = gia_thap_nhat;

            this.gia_cao_nhat = gia_cao_nhat;
        }
        public long getgia_thap_nhat()
        {
            return gia_thap_nhat;

        }
        public long getgia_cao_nhat()
        {
            return gia_cao_nhat;

        }
        public void setgia_thap_nhat(long gia_thap_nhat)
        {
            this.gia_thap_nhat = gia_thap_nhat;

        }
        public void setgia_cao_nhat(long gia_cao_nhat)
        {
            this.gia_cao_nhat = gia_cao_nhat;

        }

    }
}