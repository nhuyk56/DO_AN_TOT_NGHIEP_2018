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
    public partial class suasp : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                int id_giam_gia = 0;
                int id_san_pham = 0;
                id_giam_gia = int.Parse(Request.QueryString["id_giam_gia"]);
                id_san_pham = int.Parse(Request.QueryString["id_san_pham"]);
                string sql = "select id_san_pham, id_giam_gia, phan_tram_giam, cast(giam_toi_da as int) from danh_sac_san_pham_giam_gia where id_giam_gia=" + id_giam_gia + " and id_san_pham=" + id_san_pham;
                WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                System.Data.DataTable ds = new System.Data.DataTable();
                System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                if (ds == null || ds.Rows.Count == 0) int.Parse("a");
                if (!IsPostBack)
                {
                    TextBox2.Text = ds.Rows[0][2].ToString();
                    TextBox3.Text = ds.Rows[0][3].ToString();
                }
            }
            catch (Exception a)
            {
                Response.Write("<script language='javascript'> alert('Đã xảy ra lỗi');history.go(-1); </script>");
            }
            
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            try
            {
                int id_giam_gia = 0;
                int id_san_pham = 0;
                id_giam_gia = int.Parse(Request.QueryString["id_giam_gia"]);
                id_san_pham = int.Parse(Request.QueryString["id_san_pham"]);

                WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("danh_sac_san_pham_giam_gia_sua", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_san_pham", SqlDbType.NVarChar).Value = id_san_pham;
                cmd.Parameters.Add("@id_giam_gia", SqlDbType.NVarChar).Value = id_giam_gia;
                cmd.Parameters.Add("@phan_tram_giam", SqlDbType.NVarChar).Value = TextBox2.Text;
                cmd.Parameters.Add("@giam_toi_da", SqlDbType.NVarChar).Value = TextBox3.Text;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('Sửa thành công'); window.open('"+Session["GiamGia"].ToString()+"','_self', 1); </script>");
            }
            catch (Exception x)
            {
                Response.Write("<script language='javascript'> alert('Sửa bị lỗi!'); history.go(-1); </script>");

            }
        }
    }
}