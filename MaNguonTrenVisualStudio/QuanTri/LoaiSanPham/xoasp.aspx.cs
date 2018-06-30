using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.LoaiSanPham
{
    public partial class xoasp : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            int id_loai_san_pham = 0;
            int id_san_pham = 0;
            try
            {
                if (Request.QueryString["id_loai_san_pham"] != null && Request.QueryString["id_loai_san_pham"] != "")
                    id_loai_san_pham = int.Parse(Request.QueryString["id_loai_san_pham"]);
                if (Request.QueryString["id_san_pham"] != null && Request.QueryString["id_san_pham"] != "")
                    id_san_pham = int.Parse(Request.QueryString["id_san_pham"]);
            }
            catch (Exception a) { }
            if (id_loai_san_pham == 0 || id_san_pham==0) Response.Redirect("./DanhSach.aspx");

            try
            {
                connect connect = new connect();
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("danh_sach_loai_san_pham_cua_san_pham_xoa", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_loai_san_pham", SqlDbType.NVarChar).Value = id_loai_san_pham;
                cmd.Parameters.Add("@id_san_pham", SqlDbType.NVarChar).Value = id_san_pham;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('Xóa thành công');window.open(document.referrer,'_self', 1);</script>");
            }
            catch (Exception x)
            {
                Response.Write("<script language='javascript'> alert('Xóa bị lỗi, Vì liên kết bảng!'); history.go(-1); </script>");

            }
        }
    }
}