using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2.QuanTri.SanPham
{
    public partial class danhSachAnh : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Session["danhSachAnh"] = HttpContext.Current.Request.Url.AbsoluteUri;
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            int id_san_pham = 0;
            try
            {
                if (Request.QueryString["id_san_pham"] != null && Request.QueryString["id_san_pham"] != "")
                    id_san_pham = int.Parse(Request.QueryString["id_san_pham"]);
            }
            catch (Exception a) { }
            if (id_san_pham == 0) Response.Redirect("./danhSach.aspx");
            {
                string loi = "Thêm bị lỗi";
                try
                {
                    WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                    WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                    System.Data.SqlClient.SqlConnection connDB = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                    System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand("danh_sach_anh_cua_san_pha_them", connDB);
                    cmd.CommandType = System.Data.CommandType.StoredProcedure;
                    cmd.Parameters.Add("@id_san_pham", System.Data.SqlDbType.NVarChar).Value = id_san_pham;
                    cmd.Parameters.Add("@duong_link_anh", System.Data.SqlDbType.NVarChar).Value = mH.Base64Encode(TextBox1.Text);
                    connDB.Open();
                    cmd.ExecuteNonQuery();
                    connDB.Close();
                    Response.Write("<script language='javascript'> alert('Thêm thành công'); window.open('" + Session["danhSachAnh"].ToString() + "','_self', 1); </script>");
                }
                catch (Exception x)
                {
                    Response.Write("<script language='javascript'> alert('" + loi + "');history.go(-1); </script>");
                }
            }
        }
    }
}