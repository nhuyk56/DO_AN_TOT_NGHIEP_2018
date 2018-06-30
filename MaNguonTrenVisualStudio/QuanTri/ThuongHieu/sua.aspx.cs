using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.ThuongHieu
{
    public partial class sua : System.Web.UI.Page
    {
        WebApplication2.QuanTri.maHoa mH;
        int id_thuong_hieu;
        connect connect;
        protected void Page_Load(object sender, EventArgs e)
        {

            id_thuong_hieu = -1;
            connect = new connect();
            mH = new WebApplication2.QuanTri.maHoa();
            try
            {
                if (Request.QueryString["id_thuong_hieu"] != null && Request.QueryString["id_thuong_hieu"] != "")
                    id_thuong_hieu = int.Parse(Request.QueryString["id_thuong_hieu"]);
            }
            catch (Exception a) { }
            if (id_thuong_hieu == -1) Response.Redirect("./danhSach.aspx");
        }

        protected void Button1_Click(object sender, EventArgs e)
        {try{
            SqlConnection connDB = new SqlConnection(connect.getconnect());
            SqlCommand cmd = new SqlCommand("thuong_hieu_sua", connDB);
            cmd.CommandType = CommandType.StoredProcedure;
           
            cmd.Parameters.Add("@id_thuong_hieu", SqlDbType.NVarChar).Value = id_thuong_hieu;

            if (TextBox1.Text == null || TextBox1.Text == "")
                cmd.Parameters.Add("@ten_thuong_hieu", SqlDbType.NVarChar).Value = DBNull.Value;
            else
            cmd.Parameters.Add("@ten_thuong_hieu", SqlDbType.NVarChar).Value = mH.Base64Encode(TextBox1.Text);

            if (TextBox2.Text == null || TextBox2.Text == "")
                cmd.Parameters.Add("@anh_thuong_hieu", SqlDbType.NVarChar).Value = DBNull.Value;
            else
            cmd.Parameters.Add("@anh_thuong_hieu", SqlDbType.NVarChar).Value = mH.Base64Encode(TextBox2.Text);
            connDB.Open();
            cmd.ExecuteNonQuery();
            connDB.Close();
            Response.Write("<script language='javascript'> alert('sửa thành công');window.open('danhSach.aspx','_self', 1); </script>");
        }
        catch (Exception x)
        {
            Response.Write("<script language='javascript'> alert('sửa bị lỗi, Vì liên kết bảng!'); history.go(-1); </script>");

        }
        }
    }
}