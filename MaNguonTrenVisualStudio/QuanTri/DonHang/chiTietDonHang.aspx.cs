using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.DonHang
{
    public partial class chiTietDonHang : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {   
            string loi="Mã vận chuyển không rỗng";
             string MVC = "";
             int id_don_hang = 0;
             if (Request.Form.AllKeys.Contains("MVC"))
                try {
                if (Request.Form["MVC"].ToString().Length == 0)
                    int.Parse("aa");
                else MVC = Request.Form["MVC"].ToString();
                loi = "Mã đơn hàng sai!";
                if (Request.QueryString["id_don_hang"] != null && Request.QueryString["id_don_hang"] != "")
                    id_don_hang = int.Parse(Request.QueryString["id_don_hang"]);
                if (id_don_hang == 0) int.Parse("a");
                connect connect  = new connect();
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("cap_nhat_ma_van_chuyen", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_don_hang", SqlDbType.NVarChar).Value = id_don_hang;
                cmd.Parameters.Add("@ma_van_chuyen", SqlDbType.NVarChar).Value = MVC;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('Thêm mã vận chuyển thành công'); window.open('" + HttpContext.Current.Request.Url.AbsoluteUri + "','_self', 1); </script>");
            }
            catch (Exception a)
            {
                Response.Write("<script language='javascript'> alert('"+loi+"'); history.go(-1); </script>");
            }
        }
    }
}