using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.NhanVien
{
    public partial class xoa : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            int id_nguoi = 0;
            try
            {
                if (Request.QueryString["id_nguoi"] != null && Request.QueryString["id_nguoi"] != "")
                    id_nguoi = int.Parse(Request.QueryString["id_nguoi"]);
            }
            catch (Exception a) { }
            if (id_nguoi == 0) Response.Redirect("./DanhSach.aspx");
            try
            {
                connect connect = new connect();
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("nhan_vien_xoa", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_nguoi", SqlDbType.NVarChar).Value = id_nguoi;
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