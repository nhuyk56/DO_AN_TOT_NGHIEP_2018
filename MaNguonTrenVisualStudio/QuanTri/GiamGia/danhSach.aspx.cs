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
    public partial class danhSach : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Session["GiamGia"] = HttpContext.Current.Request.Url.AbsoluteUri;
            try { Session["GiamGia"] = WebApplication2.QuanTri.maHoa.RemoveQueryStringByKey(HttpContext.Current.Request.Url.AbsoluteUri, "xoa"); }
            catch (Exception a) { }
            int id_giam_gia = -1;
            int xoa = 0;
            try
            {
                if (Request.QueryString["xoa"] != null && Request.QueryString["xoa"] != "")
                {
                    id_giam_gia = int.Parse(Request.QueryString["xoa"]);
                    xoa = 1;
                }
            }
            catch (Exception qqe) { }
            if (id_giam_gia != -1 && xoa == 1)
            {
                string loi = "Xóa bị lỗi! Liên kết với sẩn phẩm";
                try
                {
                    WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                    string sql =
"select CASE    WHEN DATEDIFF(MINUTE, GETDATE(),thoi_diem_ket_thuc_giam_gia)>0 THEN 1    ELSE 0  END from giam_gia where id_giam_gia="+id_giam_gia;
                    DataTable ds = new DataTable();
                    System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                    new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                    if(int.Parse(ds.Rows[0][0].ToString())==0)
                    {
                        loi = "Không thể xóa! Chương trình giảm giá này đã diễn ra! tốt đẹp!";
                        int.Parse("a");
                    }
                    //
                    SqlConnection connDB = new SqlConnection(connect.getconnect());
                    SqlCommand cmd = new SqlCommand("giam_gia_xoa", connDB);
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.Add("@id_giam_gia", SqlDbType.NVarChar).Value = id_giam_gia;
                    connDB.Open();
                    cmd.ExecuteNonQuery();
                    connDB.Close();
                    Response.Write("<script language='javascript'> alert('Thành công'); window.open('" + Session["GiamGia"].ToString() + "','_self', 1); </script>");
                }
                catch (Exception x)
                {
                    Response.Write("<script language='javascript'> alert('"+loi+"');history.go(-1); </script>");

                }

            }
        }
    }
}