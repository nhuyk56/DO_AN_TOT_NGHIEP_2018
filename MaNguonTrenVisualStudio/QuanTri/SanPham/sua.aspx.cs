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
    public partial class sua : System.Web.UI.Page
    {
        connect connect;
        int id_san_pham = 0;
        string anh = "";
        maHoa mH;
        protected void Page_Load(object sender, EventArgs e)
        {
            mH = new maHoa();
            connect connect;
            try
            {
                if (Request.QueryString["id_san_pham"] != null && Request.QueryString["id_san_pham"] != "")
                {
                    id_san_pham = int.Parse(Request.QueryString["id_san_pham"]);
                }
            }
            catch (Exception qqe) { }
            if (id_san_pham == 0) Response.Redirect("./danhSach.aspx");
            connect = new connect();
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            string sql = "SELECT id_san_pham "+
                          ",id_thuong_hieu "+
                         " ,ten_san_pham "+
                          ",anh_san_pham "+
                         " ,cast(gia_cao as int)"+
                         " ,cast(gia_ban_ra as int)" +
                          ",cast(gia_nhap_san_pham as int)" +
                          ",mo_ta_san_pham_html "+
                          ",dac_tinh_san_pham "+
                          ",khoi_luong_san_pham_gram "+
                          ",da_co_vat "+
                         " , cast(FORMAT(ngay_cap_nhat, 'yyyy-MM-dd') as varchar) as ngay_cap_nhat" +
                     " FROM san_pham "+
                      "where id_san_pham=" + id_san_pham;
            
            DataTable ds = new DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
            if (ds == null || ds.Rows.Count == 0) Response.Redirect("./danhSach.aspx");
            anh = ds.Rows[0][3].ToString();
            //1. danh sách tỉnh
             sql = "select id_thuong_hieu, ten_thuong_hieu from thuong_hieu";
            connect = new connect();
             DataTable tmp = new DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(tmp);
            for(int i = 0; i<tmp.Rows.Count; i++)
                tmp.Rows[i][1] = mH.Base64Decode( tmp.Rows[i][1].ToString());
            if (!IsPostBack)
            {
                DropDownList0.DataSource = tmp;
                DropDownList0.DataTextField = "ten_thuong_hieu";
                DropDownList0.DataValueField = "id_thuong_hieu";
                DropDownList0.DataBind();
            }
            


            if (!IsPostBack)
            {
                DropDownList0.SelectedValue=ds.Rows[0][1].ToString();
                TextBox0.Text=ds.Rows[0][0].ToString();
                TextBox1.Text= mH.Base64Decode( ds.Rows[0][2].ToString());
                TextBox3.Text=ds.Rows[0][4].ToString();
                TextBox4.Text=ds.Rows[0][5].ToString();
                TextBox5.Text= ds.Rows[0][6].ToString();
                TextBox6.Text= mH.Base64Decode( ds.Rows[0][7].ToString());
                TextBox7.Text= mH.Base64Decode( ds.Rows[0][8].ToString());
                TextBox8.Text=ds.Rows[0][9].ToString();
                TextBox9.Text = ds.Rows[0][11].ToString();
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            string loi = "Lỗi không xác định";
            try
            {
                loi = "Giá không chứa dấu thập phân";
                int.Parse(TextBox3.Text);
                int.Parse(TextBox4.Text);
                int.Parse(TextBox5.Text);
                loi ="Khối lượng không chứa dấu thập phân";
                int.Parse(TextBox8.Text);
                connect = new connect();
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("san_pham_sua", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_san_pham", SqlDbType.NVarChar).Value = id_san_pham;
                cmd.Parameters.Add("@id_thuong_hieu", SqlDbType.NVarChar).Value = DropDownList0.SelectedValue;
                cmd.Parameters.Add("@ten_san_pham", SqlDbType.NVarChar).Value = mH.Base64Encode(TextBox1.Text);
                if (TextBox2.Text.Length == 0 || TextBox2.Text == "" || TextBox2.Text == null)
                    cmd.Parameters.Add("@anh_san_pham", SqlDbType.NVarChar).Value = anh;
                else
                    cmd.Parameters.Add("@anh_san_pham", SqlDbType.NVarChar).Value =  mH.Base64Encode(TextBox2.Text);

                cmd.Parameters.Add("@gia_cao", SqlDbType.NVarChar).Value = TextBox3.Text;
                cmd.Parameters.Add("@gia_ban_ra", SqlDbType.NVarChar).Value = TextBox4.Text;
                cmd.Parameters.Add("@gia_nhap_san_pham", SqlDbType.NVarChar).Value = TextBox5.Text;
                cmd.Parameters.Add("@mo_ta_san_pham_html", SqlDbType.NVarChar).Value = mH.Base64Encode(TextBox6.Text);
                cmd.Parameters.Add("@dac_tinh_san_pham", SqlDbType.NVarChar).Value = mH.Base64Encode(TextBox7.Text);
                cmd.Parameters.Add("@khoi_luong_san_pham_gram", SqlDbType.NVarChar).Value = TextBox8.Text; ;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('Sửa thành công');  window.open('" + Session["SanPham"].ToString() + "','_self', 1);</script>");
                //Response.Write("<script language='javascript'> alert('Sửa thành công');  window.open('" + Session["SanPham"].ToString() + "','_self', 1);</script>");
            }
            catch (Exception qr)
            {
                Response.Write("<script language='javascript'> alert('"+loi+"'); history.go(-1)</script>");
            }
        }

    }
}