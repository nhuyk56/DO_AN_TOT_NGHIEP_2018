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
    public partial class danhSach : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Session["PhieuNhap"] = HttpContext.Current.Request.Url.AbsoluteUri;
            try { Session["PhieuNhap"] = WebApplication2.QuanTri.maHoa.RemoveQueryStringByKey(HttpContext.Current.Request.Url.AbsoluteUri, "xoa"); }
            catch (Exception a) { }
            int id_phieu_nhap = -1;
            int xoa = 0;
            try
            {
                if (Request.QueryString["xoa"] != null && Request.QueryString["xoa"] != "")
                {
                    id_phieu_nhap = int.Parse(Request.QueryString["xoa"]);
                    xoa = 1;
                }
            }
            catch (Exception qqe) { }
            if (id_phieu_nhap != -1 && xoa == 1)
            {
                string loi = "Xóa bị lỗi! Liên kết với sẩn phẩm";
                try
                {
                    WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                    string sql =
"select CASE    WHEN DATEDIFF(HOUR, GETDATE(),ngay_tao_phieu)>-24 THEN 1    ELSE 0  END from phieu_nhap_hang_hoa where id_phieu_nhap=" + id_phieu_nhap;
                    DataTable ds = new DataTable();
                    System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                    new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                    if (int.Parse(ds.Rows[0][0].ToString()) == 0)
                    {
                        loi = "Không thể xóa!Vì đã sau 24 giờ kể từ lúc tạo phiếu nhập!";
                        int.Parse("a");
                    }
                    //
                    SqlConnection connDB = new SqlConnection(connect.getconnect());
                    SqlCommand cmd = new SqlCommand("phieu_nhap_hang_hoa_xoa", connDB);
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.Add("@id_phieu_nhap", SqlDbType.NVarChar).Value = id_phieu_nhap;
                    connDB.Open();
                    cmd.ExecuteNonQuery();
                    connDB.Close();
                    Response.Write("<script language='javascript'> alert('Xóa thành công'); window.open('" + Session["PhieuNhap"].ToString() + "','_self', 1); </script>");
                }
                catch (Exception x)
                {
                    Response.Write("<script language='javascript'> alert('" + loi + "');history.go(-1); </script>");

                }
            }
        }
    }
}