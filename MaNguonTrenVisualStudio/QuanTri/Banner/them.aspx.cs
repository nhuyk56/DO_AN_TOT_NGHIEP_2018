using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.Banner
{
    public partial class them : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            try {
                connect connect = new connect();
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("anh_trinh_chieu_them", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@duong_link_anh", SqlDbType.NVarChar).Value = TextBox1.Text;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                TextBox1.Text = "";
                Response.Write("<script language='javascript'> alert('Thêm thành công'); window.open('DanhSach.aspx','_self', 1); </script>");
            }
            catch (Exception x)
            {
                Response.Write("<script language='javascript'> alert('Thêm bị lỗi'); history.go(-1);</script>");
            }
        }
    }
}