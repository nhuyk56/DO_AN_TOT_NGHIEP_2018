using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.yeu_cau_danh_sach
{
    public class yeu_cau_ds
    {
        public bo_loc bo_loc { get; set; }

        public List<sap_xep> sap_xep { get; set; }

        public yeu_cau_ds(
        bo_loc bo_loc,
        List<sap_xep> sap_xep)
        {

            this.bo_loc = bo_loc;

            this.sap_xep = sap_xep;
        }
        public bo_loc getbo_loc()
        {
            return bo_loc;

        }
        public List<sap_xep> getsap_xep()
        {
            return sap_xep;

        }
        public void setbo_loc(bo_loc bo_loc)
        {
            this.bo_loc = bo_loc;

        }
        public void setsap_xep(List<sap_xep> sap_xep)
        {
            this.sap_xep = sap_xep;

        }
    }
}