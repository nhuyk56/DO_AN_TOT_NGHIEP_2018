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
    public partial class them : System.Web.UI.Page
    {
        WebApplication2.QuanTri.maHoa mH;
        connect connect;
        protected void Page_Load(object sender, EventArgs e)
        {
            connect = new connect();
            mH = new WebApplication2.QuanTri.maHoa();
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
           try{
               DataTable x = new DataTable();
            string sql = "select top 1 id_thuong_hieu + 1 from thuong_hieu  order by id_thuong_hieu desc ";
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            System.Data.SqlClient.SqlCommand lenh = new System.Data.SqlClient.SqlCommand(sql, ketnoi);
            ketnoi.Open();
            System.Data.SqlClient.SqlDataAdapter data = new System.Data.SqlClient.SqlDataAdapter(lenh);
            data.Fill(x);
            ketnoi.Close();
            SqlConnection connDB = new SqlConnection(connect.getconnect());
            SqlCommand cmd = new SqlCommand("thuong_hieu_them", connDB);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@id_thuong_hieu", SqlDbType.NVarChar).Value = x.Rows[0][0];
            cmd.Parameters.Add("@ten_thuong_hieu", SqlDbType.NVarChar).Value = mH.Base64Encode(TextBox1.Text);
            cmd.Parameters.Add("@anh_thuong_hieu", SqlDbType.NVarChar).Value = mH.Base64Encode(TextBox2.Text);
            connDB.Open();
            cmd.ExecuteNonQuery();
            connDB.Close();
            Response.Write("<script language='javascript'> alert('Thêm thành công'); window.open('danhSach.aspx','_self', 1); </script>");
               }
               catch (Exception x)
               {
                   Response.Write("<script language='javascript'> alert('thêm bị lỗi!');history.go(-1); </script>");

               }
        }
    }
}