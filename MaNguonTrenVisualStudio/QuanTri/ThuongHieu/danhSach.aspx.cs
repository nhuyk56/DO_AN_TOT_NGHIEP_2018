using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2.QuanTri.ThuongHieu
{
    public partial class danhSach : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            int id_thuong_hieu = -1;
            int xoa = 0;
            try
            {
                if (Request.QueryString["xoa"] != null && Request.QueryString["xoa"] != "")
                {
                    id_thuong_hieu = int.Parse(Request.QueryString["xoa"]);
                    xoa = 1;
                }
            }
            catch (Exception qqe) { }
            if (id_thuong_hieu != -1 && xoa==1)
            {
                try {
                    WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                    SqlConnection connDB = new SqlConnection(connect.getconnect());
                    SqlCommand cmd = new SqlCommand("thuong_hieu_xoa", connDB);
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.Add("@id_thuong_hieu", SqlDbType.NVarChar).Value = id_thuong_hieu;
                    connDB.Open();
                    cmd.ExecuteNonQuery();
                    connDB.Close();
                    Response.Write("<script language='javascript'> alert('Xóa thành công'); window.open(document.referrer,'_self', 1);</script>");
                }
                catch (Exception x)
                {
                    Response.Write("<script language='javascript'> alert('Xóa bị lỗi! Liên kết với sẩn phẩm');history.go(-1); </script>");

                }

            }
        }
    }
}