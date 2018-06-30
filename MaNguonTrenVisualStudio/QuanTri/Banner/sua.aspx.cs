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
    public partial class sua : System.Web.UI.Page
    {
        int id_anh = 0;
        protected void Page_Load(object sender, EventArgs e)
        {
           
            try
            {
                if (Request.QueryString["id_anh"] != null && Request.QueryString["id_anh"] != "")
                    id_anh = int.Parse(Request.QueryString["id_anh"]);
            }
            catch (Exception r) { }
            if (id_anh == 0) Response.Redirect("./DanhSach.aspx");
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            try
            {
                connect connect = new connect();
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("pro_banner_sua", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_anh", SqlDbType.NVarChar).Value = id_anh;
                cmd.Parameters.Add("@duong_link_anh", SqlDbType.NVarChar).Value = TextBox1.Text;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                TextBox1.Text = "";
                Response.Write("<script language='javascript'> alert('Sửa thành công');  window.open('DanhSach.aspx','_self', 1);</script>");
            }
            catch (Exception c)
            {
                Response.Write("<script language='javascript'> alert('Sửa bị lỗi');  history.go(-1);</script>");
            }
        }
    }
}