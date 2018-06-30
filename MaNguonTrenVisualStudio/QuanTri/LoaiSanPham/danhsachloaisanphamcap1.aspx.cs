using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2.QuanTri.LoaiSanPham
{
    public partial class danhsachloaisanphamcap1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Session["LoaiSanPham"] = HttpContext.Current.Request.Url.AbsoluteUri;
        }
    }
}