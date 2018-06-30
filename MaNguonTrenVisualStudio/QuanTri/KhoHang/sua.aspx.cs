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
    public partial class sua : System.Web.UI.Page
    {
        connect connect;
        int id_kho_hang = 0;
        protected void Page_Load(object sender, EventArgs e)
        {
            connect connect;
            try
            {
                if (Request.QueryString["id_kho_hang"] != null && Request.QueryString["id_kho_hang"] != "")
                {
                    id_kho_hang = int.Parse(Request.QueryString["id_kho_hang"]);
                }
            }
            catch (Exception qqe) { }
            if (id_kho_hang == 0) Response.Redirect("./danhSach.aspx");
            WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
            connect = new connect();
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            string sql = "select id_kho_hang, id_khu_vuc, ten_kho_hang, dia_chi_kho_hang_chi_tiet, dien_tich_kho, cast(FORMAT(ngay_tao_kho, 'yyyy-MM-dd') as varchar) as ngay_tao_kho, ghi_chu_kho " +
                    "from kho_hang " +
                    "where id_kho_hang=" + id_kho_hang;
            
            DataTable ds = new DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
            if (ds == null || ds.Rows.Count == 0) Response.Redirect("./danhSach.aspx");

            //1. danh sách tỉnh
             sql = "select id_khu_vuc, ten_khu_vuc from khu_vuc where id_khu_vuc_cha=0";
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
            //2. set mã tỉnh
            sql = "select id_khu_vuc_cha from khu_vuc where id_khu_vuc=" + ds.Rows[0][1].ToString();
            tmp = new DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(tmp);
            string id_tinh = tmp.Rows[0][0].ToString();

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

            if (!IsPostBack)
            {
                TextBox6.Text = ds.Rows[0][0].ToString();
                TextBox7.Text = ds.Rows[0][2].ToString();
                TextBox3.Text = ds.Rows[0][3].ToString();
                TextBox8.Text = ds.Rows[0][4].ToString();
                TextBox9.Text = ds.Rows[0][5].ToString();
                TextBox10.Text = ds.Rows[0][6].ToString();
                DropDownList2.SelectedValue = id_tinh;
                DropDownList1.SelectedValue = ds.Rows[0][1].ToString();
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            try
            {
                connect = new connect();
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("kho_hang_sua", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_kho_hang", SqlDbType.NVarChar).Value = id_kho_hang;
                cmd.Parameters.Add("@id_khu_vuc", SqlDbType.NVarChar).Value = DropDownList1.SelectedValue;
                cmd.Parameters.Add("@ten_kho_hang", SqlDbType.NVarChar).Value = TextBox7.Text;
                cmd.Parameters.Add("@dia_chi_kho_hang_chi_tiet", SqlDbType.NVarChar).Value = TextBox3.Text;
                cmd.Parameters.Add("@dien_tich_kho", SqlDbType.NVarChar).Value = TextBox8.Text;
                cmd.Parameters.Add("@ngay_tao_kho", SqlDbType.NVarChar).Value = TextBox9.Text;
                cmd.Parameters.Add("@ghi_chu_kho", SqlDbType.NVarChar).Value = TextBox10.Text;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('Sửa thành công');  window.open('" + Session["KhoHang"].ToString() + "','_self', 1);</script>");
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