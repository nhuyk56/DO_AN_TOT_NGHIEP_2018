using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2.QuanTri.DonHang
{
    public partial class danhSachDonHang : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Session["danhSachDonHang"] = HttpContext.Current.Request.Url.AbsoluteUri;
        }
    }
}