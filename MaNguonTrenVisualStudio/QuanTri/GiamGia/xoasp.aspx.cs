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
    public partial class xoasp : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

            int xoa = 0;
            int id_giam_gia = 0;
            int id_san_pham = 0;
            try
            {
                if (Request.QueryString["id_giam_gia"] != null && Request.QueryString["id_giam_gia"] != "")
                    id_giam_gia = int.Parse(Request.QueryString["id_giam_gia"]);
                if (Request.QueryString["id_san_pham"] != null && Request.QueryString["id_san_pham"] != "")
                    id_san_pham = int.Parse(Request.QueryString["id_san_pham"]);
                if (Request.QueryString["xoa"] != null && Request.QueryString["xoa"] != "")
                    xoa = int.Parse(Request.QueryString["xoa"]);
            }
            catch (Exception a) { }

            if (xoa != 0 && id_giam_gia != 0 && id_san_pham != 0)
            {
                try
                {
                    WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                    SqlConnection connDB = new SqlConnection(connect.getconnect());
                    SqlCommand cmd = new SqlCommand("danh_sac_san_pham_giam_gia_xoa", connDB);
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.Add("@id_giam_gia", SqlDbType.NVarChar).Value = id_giam_gia;
                    cmd.Parameters.Add("@id_san_pham", SqlDbType.NVarChar).Value = id_san_pham;
                    connDB.Open();
                    cmd.ExecuteNonQuery();
                    connDB.Close();
                    Response.Write("<script language='javascript'> alert('Xóa thành công'); window.open(document.referrer,'_self', 1);</script>");
                }
                catch (Exception x)
                {
                    Response.Write("<script language='javascript'> alert('Xóa bị lỗi!');history.go(-1); </script>");

                }

            }
        }
    }
}