using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2.QuanTri.KhoHang
{
    public partial class xoa : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            int id_kho_hang = -1;
            try
            {
                if (Request.QueryString["id_kho_hang"] != null && Request.QueryString["id_kho_hang"] != "")
                {
                    id_kho_hang = int.Parse(Request.QueryString["id_kho_hang"]);
                }
            }
            catch (Exception qqe) { }
            if (id_kho_hang != -1)
            {
                string loi = "Xóa bị lỗi! Liên kết với sẩn phẩm";
                try
                {
                    WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                    string sql =
                    "select * from kho_hang where id_kho_hang=" + id_kho_hang;
                    DataTable ds = new DataTable();
                    System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                    new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                    if (ds.Rows.Count==0)
                    {
                        loi = "Không thể xóa! Vì lỗi ID kho hàng";
                        int.Parse("a");
                    }
                    //
                    SqlConnection connDB = new SqlConnection(connect.getconnect());
                    SqlCommand cmd = new SqlCommand("kho_hang_xoa", connDB);
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.Add("@id_kho_hang", SqlDbType.NVarChar).Value = id_kho_hang;
                    connDB.Open();
                    cmd.ExecuteNonQuery();
                    connDB.Close();
                    Response.Write("<script language='javascript'> alert('Xóa thành công'); window.open('" + Session["KhoHang"].ToString() + "','_self', 1); </script>");
                }
                catch (Exception x)
                {
                    Response.Write("<script language='javascript'> alert('" + loi + "');history.go(-1); </script>");

                }
            }
        }
    }
}