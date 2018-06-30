using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2.QuanTri.PhieuNhap
{
    public partial class suasp : System.Web.UI.Page
    {
        int id_phieu_nhap = 0;
        int id_san_pham = 0;
        int so_luong_nhap = 0;
        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                if (Request.QueryString["id_phieu_nhap"] != null && Request.QueryString["id_phieu_nhap"] != "")
                    id_phieu_nhap = int.Parse(Request.QueryString["id_phieu_nhap"]);
                if (Request.QueryString["id_san_pham"] != null && Request.QueryString["id_san_pham"] != "")
                    id_san_pham = int.Parse(Request.QueryString["id_san_pham"]);
            }
            catch (Exception a) { }

            WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
            string sql =
               " select so_luong_nhap " +
                  " from chi_tiet_phieu_nhap "+
                 " where id_phieu_nhap="+id_phieu_nhap+" and id_san_pham="+id_san_pham;
            System.Data.DataTable ds = new System.Data.DataTable();
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
            so_luong_nhap = int.Parse(ds.Rows[0][0].ToString());
            
            sql = "select cast(gia_nhap_san_pham as int) " +
                    "from san_pham " +
                    "where id_san_pham=" + id_san_pham;
             ds = new System.Data.DataTable();
             ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            new SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
           
            if (!IsPostBack)
            {
                TextBox2.Text = so_luong_nhap + "";
                TextBox3.Text = ds.Rows[0][0].ToString();
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            string loi = "không sửa được!";
           try
            {
                int sl_kho = 0, khoan = 0, stt = 0, id_kho=0;
                string sql = "select id_kho_hang " +
                        "from phieu_nhap_hang_hoa " +
                        "where  id_phieu_nhap=" + id_phieu_nhap;
                WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                System.Data.DataTable ds = new System.Data.DataTable();
                System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
            id_kho = int.Parse(ds.Rows[0][0].ToString());

                sql = "select so_luong " +
                "from chi_tiet_kho_hang " +
                "where id_kho_hang=" + id_kho + " and id_san_pham=" + id_san_pham;
                ds = new System.Data.DataTable();
                new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                sl_kho = int.Parse(ds.Rows[0][0].ToString());

                if (so_luong_nhap > int.Parse(TextBox2.Text))
                {
                    khoan = so_luong_nhap - int.Parse(TextBox2.Text);
                    stt = +1;
                }
                else if (so_luong_nhap == int.Parse(TextBox2.Text))
                {
                    khoan = 0;
                    stt = 0;
                }
                else
                {
                    khoan = int.Parse(TextBox2.Text) - so_luong_nhap;
                    stt = +1;
                }

                if (stt == -1 && khoan > sl_kho) 
                { loi = "Số lượng sửa giảm xuống vượt quá số lượng trong kho!"; int.Parse("a"); }
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("chi_tiet_phieu_nhap_sua", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_san_pham", SqlDbType.NVarChar).Value = id_san_pham;
                cmd.Parameters.Add("@id_phieu_nhap", SqlDbType.NVarChar).Value = id_phieu_nhap;
                 cmd.Parameters.Add("@so_luong_nhap", SqlDbType.NVarChar).Value = TextBox2.Text;
                cmd.Parameters.Add("@id_kho_hang", SqlDbType.NVarChar).Value = id_kho;
                cmd.Parameters.Add("@stt", SqlDbType.NVarChar).Value = stt;
               cmd.Parameters.Add("@khoan", SqlDbType.NVarChar).Value = khoan;
                cmd.Parameters.Add("@gia_nhap_san_pham", SqlDbType.NVarChar).Value = TextBox3.Text;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();

                 Response.Write("<script language='javascript'> alert('Sửa thành công');  window.open('" + Session["PhieuNhap"].ToString() + "','_self', 1);</script>");
            }
            catch (Exception x)
            {
               Response.Write("<script language='javascript'> alert('"+loi+"'); history.go(-1); </script>");

            }

        }
    }
}