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
    public partial class xoasp : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            string loi = "Lỗi không xóa được!";
            int xoa = 0;
            int id_phieu_nhap = 0;
            int id_san_pham = 0;
            try
            {
                if (Request.QueryString["id_phieu_nhap"] != null && Request.QueryString["id_phieu_nhap"] != "")
                    id_phieu_nhap = int.Parse(Request.QueryString["id_phieu_nhap"]);
                if (Request.QueryString["id_san_pham"] != null && Request.QueryString["id_san_pham"] != "")
                    id_san_pham = int.Parse(Request.QueryString["id_san_pham"]);
                if (Request.QueryString["xoa"] != null && Request.QueryString["xoa"] != "")
                    xoa = int.Parse(Request.QueryString["xoa"]);
            }
            catch (Exception a) { }

            if (xoa != 0 && id_phieu_nhap != 0 && id_san_pham != 0)
            {
                try
                {
                    WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                    System.Data.DataTable ds = new System.Data.DataTable();
                    System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                     string sql = "select id_kho_hang from phieu_nhap_hang_hoa where id_phieu_nhap=" + id_phieu_nhap;
                     ds = new System.Data.DataTable();
                     new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                    int id_kho_hang = int.Parse(ds.Rows[0][0].ToString());
                     sql = "select so_luong "+
                    "from chi_tiet_kho_hang "+
                    "where id_kho_hang="+id_kho_hang+" and id_san_pham="+id_san_pham;
                    ds = new System.Data.DataTable();
                    new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                    int so_luong_trong_kho = int.Parse(ds.Rows[0][0].ToString());

                    sql="select so_luong_nhap "+
                    "from chi_tiet_phieu_nhap "+
                    "where id_phieu_nhap="+id_phieu_nhap+" and id_san_pham="+id_san_pham;
                    ds = new System.Data.DataTable();
                    new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                    int so_luong_phieu_nhap = int.Parse(ds.Rows[0][0].ToString());
                    if (so_luong_phieu_nhap > so_luong_trong_kho)
                    {
                        loi = "không xóa được! Số lượng sản phẩm trong phiếu nhập lớn hơn số lượng sản phẩm trong kho!";
                        int.Parse("a");
                    }
                    SqlConnection connDB = new SqlConnection(connect.getconnect());
                    SqlCommand cmd = new SqlCommand("chi_tiet_phieu_nhap_xoa", connDB);
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.Add("@id_san_pham", SqlDbType.NVarChar).Value = id_san_pham;
                    cmd.Parameters.Add("@id_phieu_nhap", SqlDbType.NVarChar).Value = id_phieu_nhap;
                    cmd.Parameters.Add("@so_luong_nhap", SqlDbType.NVarChar).Value = so_luong_phieu_nhap;
                    cmd.Parameters.Add("@id_kho_hang", SqlDbType.NVarChar).Value = id_kho_hang;
                    connDB.Open();
                    cmd.ExecuteNonQuery();
                    connDB.Close();
                    Response.Write("<script language='javascript'> alert('Xóa thành công'); window.open('" + Session["PhieuNhap"].ToString() + "','_self', 1);</script>");
                }
                catch (Exception x)
                {
                    Response.Write("<script language='javascript'> alert('"+loi+"');history.go(-1); </script>");

                }

            }
        }
    }
}