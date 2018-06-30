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
    public partial class sua : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            string sql = "select id_nguoi, email_nguoi, mat_khau_nguoi, ten_nguoi, cast(FORMAT(ngay_sinh, 'yyyy-MM-dd') as varchar) as ngay_sinh, dia_chi_nguoi, sdt_nguoi, gioi_tinh_nguoi from nguoi where id_nguoi=";
            int id_nguoi = 0;
            WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
            connect connect;
            try
            {
                if (Request.QueryString["id_nguoi"] != null && Request.QueryString["id_nguoi"] != "")
                {
                    id_nguoi = int.Parse(Request.QueryString["id_nguoi"]);
                }
            }
            catch (Exception qqe) { }
            if (id_nguoi == 0) Response.Redirect("./DanhSach.aspx");
            connect = new connect();
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            DataTable ds = new DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql+ + id_nguoi, ketnoi)).Fill(ds);
            if (ds == null || ds.Rows.Count == 0) Response.Redirect("./DanhSach.aspx");
            maHoa m = new maHoa();
            if (!IsPostBack)
            {
                TextBox6.Text = ds.Rows[0][0].ToString();
                TextBox0.Text = ds.Rows[0][1].ToString();
                TextBox1.Text = m.Base64Decode(ds.Rows[0][2].ToString());
                TextBox1.Attributes["type"] = "password";
                TextBox2.Text = ds.Rows[0][3].ToString();
                TextBox3.Text = ds.Rows[0][4].ToString();
                TextBox4.Text = ds.Rows[0][5].ToString();
                TextBox5.Text = ds.Rows[0][6].ToString();
                if (bool.Parse(ds.Rows[0][7].ToString()) == true)
                {
                    RadioButton1.Checked = true;
                    RadioButton2.Checked = false;
                }
                else
                {
                    RadioButton1.Checked = false;
                    RadioButton2.Checked = true;
                }
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            try{
            maHoa m = new maHoa();
            connect connect = new connect();
            SqlConnection connDB = new SqlConnection(connect.getconnect());
            SqlCommand cmd = new SqlCommand("nhan_vien_sua", connDB);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@email_nguoi", SqlDbType.NVarChar).Value = TextBox0.Text;
            cmd.Parameters.Add("@mat_khau_nguoi", SqlDbType.NVarChar).Value =m.Base64Encode(TextBox1.Text);
            cmd.Parameters.Add("@ten_nguoi", SqlDbType.NVarChar).Value = TextBox2.Text;
            cmd.Parameters.Add("@ngay_sinh", SqlDbType.NVarChar).Value = TextBox3.Text;
            cmd.Parameters.Add("@dia_chi_nguoi", SqlDbType.NVarChar).Value = TextBox4.Text;
            cmd.Parameters.Add("@sdt_nguoi", SqlDbType.NVarChar).Value = TextBox5.Text;
            cmd.Parameters.Add("@gioi_tinh_nguoi", SqlDbType.NVarChar).Value = RadioButton1.Checked;
            connDB.Open();
            cmd.ExecuteNonQuery();
            connDB.Close();
                Response.Write("<script language='javascript'> alert('Sửa thành công'); window.open('DanhSach.aspx','_self', 1); </script>");
            }
            catch (Exception x)
            {
                Response.Write("<script language='javascript'> alert('Lỗi không sửa được!'); history.go(-1);</script>");
            }
        }
    }

}