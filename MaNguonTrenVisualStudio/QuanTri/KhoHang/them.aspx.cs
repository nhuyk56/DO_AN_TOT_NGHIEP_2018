using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.KhoHang
{
    public partial class them : System.Web.UI.Page
    {
        connect connect;
        protected void Page_Load(object sender, EventArgs e)
        {
            //1. danh sách tỉnh
            connect = new connect();
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            string sql = "select id_khu_vuc, ten_khu_vuc from khu_vuc where id_khu_vuc_cha=0";
            connect = new connect();
            DataTable tmp = new DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(tmp);
            if (!IsPostBack)
            {
                DropDownList2.DataSource = tmp;
                DropDownList2.DataTextField = "ten_khu_vuc";
                DropDownList2.DataValueField = "id_khu_vuc";
                DropDownList2.DataBind();
            }
            //3. huyện
            sql = "select id_khu_vuc, ten_khu_vuc from khu_vuc where id_khu_vuc_cha=" + tmp.Rows[0][0].ToString();
            tmp = new DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(tmp);
            if (!IsPostBack)
            {
                DropDownList1.DataSource = tmp;
                DropDownList1.DataTextField = "ten_khu_vuc";
                DropDownList1.DataValueField = "id_khu_vuc";
                DropDownList1.DataBind();
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            try
            {
                connect = new connect();
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("proc_kho_hang_them", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_khu_vuc", SqlDbType.NVarChar).Value = DropDownList1.SelectedValue;
                cmd.Parameters.Add("@ten_kho_hang", SqlDbType.NVarChar).Value = TextBox7.Text;
                cmd.Parameters.Add("@dia_chi_kho_hang", SqlDbType.NVarChar).Value = TextBox3.Text;
                cmd.Parameters.Add("@dien_tich_kho", SqlDbType.NVarChar).Value = TextBox8.Text;
                cmd.Parameters.Add("@ngay_tao_kho", SqlDbType.NVarChar).Value = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");;
                cmd.Parameters.Add("@ghi_chu_kho", SqlDbType.NVarChar).Value = TextBox10.Text;
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

        protected void DropDownList2_SelectedIndexChanged(object sender, EventArgs e)
        {
            //3. huyện
            connect = new connect();
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            string sql = "select id_khu_vuc, ten_khu_vuc from khu_vuc where id_khu_vuc_cha=" + DropDownList2.SelectedValue;
            DataTable tmp = new DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(tmp);
            // if (!IsPostBack)
            {
                DropDownList1.DataSource = tmp;
                DropDownList1.DataTextField = "ten_khu_vuc";
                DropDownList1.DataValueField = "id_khu_vuc";
                DropDownList1.DataBind();
            }
        }
    }
}