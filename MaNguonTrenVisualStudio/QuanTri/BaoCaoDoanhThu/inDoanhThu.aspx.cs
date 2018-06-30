using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2.QuanTri.BaoCaoDoanhThu
{
    public partial class inDoanhThu : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            //  System.DateTime batdau = DateTime.Parse(Request.Form[TextBox4.UniqueID]);
           // System.DateTime ketthuc = DateTime.Parse(Request.Form[TextBox5.UniqueID]);
            maHoa m = new maHoa();
            string batdau = m.Base64Encode(Request.Form[TextBox1.UniqueID]);
            string ketthuc = m.Base64Encode(Request.Form[TextBox2.UniqueID]);
            Response.Redirect("BaoCaoDT.aspx?ngaybaydau="+batdau+"&ngayketthuc="+ketthuc); 
        }
    }
}