using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.LoaiSanPham
{
    public partial class danhSachSanPham : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            string loi="Sản phẩm này không tồn tại!";
            try
            {
                string sql =
                "kiem_tra_san_pham " + TextBox1.Text;
                connect connect = new connect();
                DataTable ds = new DataTable();
                System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                ds = new DataTable();
                new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                if (ds.Rows.Count == 0) int.Parse("a");//hủy thao tác
                loi = "Sản phẩm đã thêm vào trước đó!";
                WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                System.Data.SqlClient.SqlConnection connDB = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand("danh_sach_loai_san_pham_cua_san_pham_them", connDB);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_san_pham", System.Data.SqlDbType.NVarChar).Value = TextBox1.Text;
                cmd.Parameters.Add("@id_loai_san_pham", System.Data.SqlDbType.NVarChar).Value = int.Parse(Request.QueryString["id_loai_san_pham"]);
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('Thêm thành công'); window.open('" + Page.Request.Url.ToString() + "','_self', 1); </script>");
            }
            catch (Exception x)
            {
                Response.Write("<script language='javascript'> alert('" + loi + "');history.go(-1); </script>");
            }

        }
    }
}