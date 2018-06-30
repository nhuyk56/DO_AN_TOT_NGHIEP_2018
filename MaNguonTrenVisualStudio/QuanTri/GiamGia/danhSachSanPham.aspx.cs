using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2.QuanTri.GiamGia
{
    public partial class danhSachSanPham : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Session["GiamGia"] = HttpContext.Current.Request.Url.AbsoluteUri;
        }
    }
}