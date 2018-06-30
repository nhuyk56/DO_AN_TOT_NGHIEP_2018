using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.SanPham
{
    public partial class them : System.Web.UI.Page
    {
        connect connect;
        maHoa mH;
        protected void Page_Load(object sender, EventArgs e)
        {
            mH = new maHoa();
            connect = new connect();
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            //1. danh sách TH           
            string sql = "select id_thuong_hieu, ten_thuong_hieu from thuong_hieu";
            connect = new connect();
            DataTable tmp = new DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(tmp);
            for (int i = 0; i < tmp.Rows.Count; i++)
                tmp.Rows[i][1] = mH.Base64Decode(tmp.Rows[i][1].ToString());
            if (!IsPostBack)
            {
                DropDownList0.DataSource = tmp;
                DropDownList0.DataTextField = "ten_thuong_hieu";
                DropDownList0.DataValueField = "id_thuong_hieu";
                DropDownList0.DataBind();
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            string loi = "Lỗi không xác định";
            try
            {
                int id_sp = lay_id();
                loi = "Giá không chứa dấu thập phân";
                int.Parse(TextBox3.Text);
                int.Parse(TextBox4.Text);
                int.Parse(TextBox5.Text);
                loi = "Khối lượng không chứa dấu thập phân";
                int.Parse(TextBox8.Text);
                connect = new connect();
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("san_pham_them", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_san_pham", SqlDbType.NVarChar).Value = id_sp;//id_san_pham
                cmd.Parameters.Add("@id_thuong_hieu", SqlDbType.NVarChar).Value = DropDownList0.SelectedValue;
                cmd.Parameters.Add("@ten_san_pham", SqlDbType.NVarChar).Value = mH.Base64Encode(TextBox1.Text);
                cmd.Parameters.Add("@anh_san_pham", SqlDbType.NVarChar).Value = mH.Base64Encode(TextBox2.Text);
                cmd.Parameters.Add("@gia_cao", SqlDbType.NVarChar).Value = TextBox3.Text;
                cmd.Parameters.Add("@gia_ban_ra", SqlDbType.NVarChar).Value = TextBox4.Text;
                cmd.Parameters.Add("@gia_nhap_san_pham", SqlDbType.NVarChar).Value = TextBox5.Text;
                cmd.Parameters.Add("@mo_ta_san_pham_html", SqlDbType.NVarChar).Value = mH.Base64Encode(TextBox6.Text);
                cmd.Parameters.Add("@dac_tinh_san_pham", SqlDbType.NVarChar).Value = mH.Base64Encode(TextBox7.Text);
                cmd.Parameters.Add("@khoi_luong_san_pham_gram", SqlDbType.NVarChar).Value = TextBox8.Text; ;
                cmd.Parameters.Add("@da_co_vat", SqlDbType.NVarChar).Value = 1 ;
                cmd.Parameters.Add("@ngay_cap_nhat", SqlDbType.NVarChar).Value = DateTime.Now.ToString();
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('Thêm thành công, tiến hành thêm ảnh cho sản phẩm này');  window.open('danhSachAnh.aspx?id_san_pham="+id_sp+"','_self', 1);</script>");
            }
            catch (Exception qr)
            {
                Response.Write("<script language='javascript'> alert('" + loi + "'); history.go(-1)</script>");
            }
        }


        private int lay_id()
        {
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            //1. danh sách TH           
            string sql = "select top 1 id_san_pham + 1 from san_pham order by id_san_pham desc";
            connect = new connect();
            DataTable tmp = new DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(tmp);
            return int.Parse(tmp.Rows[0][0].ToString());
        }
    }
}