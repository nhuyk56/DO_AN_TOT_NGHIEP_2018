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
    public partial class themsp : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            string id_phieu_nhap = "", id_san_pham = "";
            try
            {
                if (Request.QueryString["id_phieu_nhap"] != null && Request.QueryString["id_phieu_nhap"] != "")
                    id_phieu_nhap = (Request.QueryString["id_phieu_nhap"]);
                if (Request.QueryString["id_san_pham"] != null && Request.QueryString["id_san_pham"] != "")
                    id_san_pham = (Request.QueryString["id_san_pham"]);
            }
            catch (Exception a) { }
            try
           {
               WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();

               System.Data.DataTable ds = new System.Data.DataTable();
               System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
               int id_kho_hang = 0;
               int them = 0;
               string sql = "select so_luong " +
               "from chi_tiet_kho_hang ctkh " +
               "join ( " +
               "select id_kho_hang " +
               "from phieu_nhap_hang_hoa where id_phieu_nhap=" + id_phieu_nhap + " " +
               ") idkh on ctkh.id_kho_hang=idkh.id_kho_hang " +
               "where id_san_pham=" + id_san_pham + "";
               ds = new System.Data.DataTable();
               new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
               if (ds == null || ds.Rows.Count == 0) them = 1;
               sql = "select id_kho_hang from phieu_nhap_hang_hoa where id_phieu_nhap=" + id_phieu_nhap;
               ds = new System.Data.DataTable();
               new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
               id_kho_hang = int.Parse(ds.Rows[0][0].ToString());
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("chi_tiet_phieu_nhap_them", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_phieu_nhap", SqlDbType.NVarChar).Value = id_phieu_nhap;
                cmd.Parameters.Add("@id_san_pham", SqlDbType.NVarChar).Value = id_san_pham;
                cmd.Parameters.Add("@id_kho_hang", SqlDbType.NVarChar).Value = id_kho_hang;
                cmd.Parameters.Add("@them", SqlDbType.NVarChar).Value = them;
                cmd.Parameters.Add("@so_luong_nhap", SqlDbType.NVarChar).Value = TextBox2.Text;
                cmd.Parameters.Add("@gia_nhap_san_pham", SqlDbType.NVarChar).Value = TextBox3.Text;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();

                Response.Write("<script language='javascript'> alert('Thêm thành công');  window.open('" + Session["PhieuNhap"].ToString() + "','_self', 1);</script>");
            }
            catch (Exception x)
            {
                Response.Write("<script language='javascript'> alert('Thêm bị lỗi!'); history.go(-1); </script>");

            }
        }
    }
}