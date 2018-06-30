using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.PhieuNhap
{
    public partial class them : System.Web.UI.Page
    {
        connect connect;
        protected void Page_Load(object sender, EventArgs e)
        {
            string sql = "select id_kho_hang, ten_kho_hang from kho_hang";
             connect = new connect();
            DataTable ds = new DataTable();
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
            if (!IsPostBack)
            {
                DropDownList1.DataSource = ds;
                DropDownList1.DataTextField = "ten_kho_hang";
                DropDownList1.DataValueField = "id_kho_hang";
                DropDownList1.DataBind();
            }

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            try
            {
                DataTable ds = new DataTable();
                ds = (DataTable)Session["dangNhap"];
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("phieu_nhap_hang_hoa_them", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_kho_hang", SqlDbType.NVarChar).Value = DropDownList1.SelectedValue;
                cmd.Parameters.Add("@id_nguoi", SqlDbType.NVarChar).Value = ds.Rows[0][0].ToString();
                cmd.Parameters.Add("@mo_ta_nguon_nhap", SqlDbType.NVarChar).Value = TextBox3.Text;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('Thêm thành công');  window.open('danhSach.aspx','_self', 1);</script>");
            }
            catch (Exception qr)
            {
                Response.Write("<script language='javascript'> alert('Lỗi không xác định'); history.go(-1)</script>");
            }
        }
    }
}