using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.yeu_cau_danh_sach
{
    public class danh_gia
    {

        public float so_sao_thap_nhat { get; set; }

        public danh_gia(
        float so_sao_thap_nhat)
        {

            this.so_sao_thap_nhat = so_sao_thap_nhat;
        }
        public float getso_sao_thap_nhat()
        {
            return so_sao_thap_nhat;

        }
        public void setso_sao_thap_nhat(float so_sao_thap_nhat)
        {
            this.so_sao_thap_nhat = so_sao_thap_nhat;

        }

    }
}