using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.LoaiSanPham
{
    public partial class sua : System.Web.UI.Page
    {
        WebApplication2.QuanTri.maHoa mH;
        int id_loai_san_pham;
        connect connect;
        string id_cha;
        protected void Page_Load(object sender, EventArgs e)
        {
            id_loai_san_pham = 0;
            connect = new connect();
            mH = new WebApplication2.QuanTri.maHoa();
            try
            {
                if (Request.QueryString["id_loai_san_pham"] != null && Request.QueryString["id_loai_san_pham"] != "")
                    id_loai_san_pham = int.Parse(Request.QueryString["id_loai_san_pham"]);
            }
            catch (Exception a) { }
            if (id_loai_san_pham == 0) Response.Redirect("danhsachloaisanpham.aspx");
            
            System.Data.DataTable ds = new System.Data.DataTable();
            {
                string sql = "select id_loai_san_pham, ten_loai_san_pham, anh_loai_san_pham, bieu_tuong_loai_san_pham, cap_do_loai_san_pham, id_cha_loai_san_pham from loai_san_pham where id_loai_san_pham=" + id_loai_san_pham;
                WebApplication2.YNNSHOP56131778.CONGFIG.connect cnt = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                System.Data.SqlClient.SqlCommand lenh = new System.Data.SqlClient.SqlCommand(sql, ketnoi);
                ketnoi.Open();
                System.Data.SqlClient.SqlDataAdapter data = new System.Data.SqlClient.SqlDataAdapter(lenh);
                data.Fill(ds);
                ketnoi.Close();
                id_cha = ds.Rows[0][5].ToString();
                if (!IsPostBack)
                {
                    TextBox1.Text = mH.Base64Decode(ds.Rows[0][1].ToString());
                    TextBox4.Text = ds.Rows[0][4].ToString();
                }
                //TextBox4.Text = ds.Rows[0][4].ToString();
            }
            System.Data.DataTable lsp = new System.Data.DataTable();
            {
                string sql = "select id_loai_san_pham, ten_loai_san_pham from loai_san_pham where cap_do_loai_san_pham = " + (int.Parse(TextBox4.Text) - 1);
                WebApplication2.YNNSHOP56131778.CONGFIG.connect cnt = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                System.Data.SqlClient.SqlCommand lenh = new System.Data.SqlClient.SqlCommand(sql, ketnoi);
                ketnoi.Open();
                System.Data.SqlClient.SqlDataAdapter data = new System.Data.SqlClient.SqlDataAdapter(lenh);
                data.Fill(lsp);
                ketnoi.Close();
                for (int i = 0; i < lsp.Rows.Count; i++)
                    lsp.Rows[i][1] = mH.Base64Decode(lsp.Rows[i][1].ToString());
                if ((int.Parse(TextBox4.Text)) == 0)
                {
                    System.Data.DataRow row;
                    row = lsp.NewRow();
                    row[0] = 0;
                    row[1] = "cao nhất";
                    lsp.Rows.Add(row);
                }
                DropDownList1.DataSource = lsp;
                DropDownList1.DataTextField = "ten_loai_san_pham";
                DropDownList1.DataValueField = "id_loai_san_pham";
                DropDownList1.DataBind();
                try {DropDownList1.Items.FindByValue(id_cha + "").Selected = true;}catch (Exception a) { }
            }
            
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            try{
	            string ten_loai_san_pham = mH.Base64Encode(TextBox1.Text);
                string anh_loai_san_pham = null;
                if (TextBox2.Text != null)
                {
                    anh_loai_san_pham = TextBox2.Text;
                }
                string bieu_tuong_loai_san_pham = null;
                if (TextBox2.Text != null)
                {
                    bieu_tuong_loai_san_pham = TextBox3.Text;
                }
                string cap_do_loai_san_pham = null ;
                if (TextBox4.Text != null)
                {
                    cap_do_loai_san_pham = (TextBox4.Text);
                }
                string id_cha_loai_san_pham = DropDownList1.SelectedValue;
            //
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("loai_san_pham_sua_thanh_phan", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_loai_san_pham", SqlDbType.NVarChar).Value = id_loai_san_pham;

                if (ten_loai_san_pham == null || ten_loai_san_pham == "")
                    cmd.Parameters.Add("@ten_loai_san_pham", SqlDbType.NVarChar).Value = DBNull.Value;
                else
                    cmd.Parameters.Add("@ten_loai_san_pham", SqlDbType.NVarChar).Value = ten_loai_san_pham;

                if (anh_loai_san_pham == null || anh_loai_san_pham == "")
                    cmd.Parameters.Add("@anh_loai_san_pham", SqlDbType.NVarChar).Value = DBNull.Value;
                else
                cmd.Parameters.Add("@anh_loai_san_pham", SqlDbType.NVarChar).Value = anh_loai_san_pham;

                if (bieu_tuong_loai_san_pham == null || bieu_tuong_loai_san_pham == "")
                    cmd.Parameters.Add("@bieu_tuong_loai_san_pham", SqlDbType.NVarChar).Value = DBNull.Value;
                else
                cmd.Parameters.Add("@bieu_tuong_loai_san_pham", SqlDbType.NVarChar).Value = bieu_tuong_loai_san_pham;

                if (cap_do_loai_san_pham == null||cap_do_loai_san_pham == "")
                    cmd.Parameters.Add("@cap_do_loai_san_pham", SqlDbType.NVarChar).Value = DBNull.Value;
                else
                cmd.Parameters.Add("@cap_do_loai_san_pham", SqlDbType.NVarChar).Value = cap_do_loai_san_pham;

                if (id_cha_loai_san_pham == null || id_cha_loai_san_pham == "")
                    cmd.Parameters.Add("@id_cha_loai_san_pham", SqlDbType.NVarChar).Value = DBNull.Value;
                else
                    cmd.Parameters.Add("@id_cha_loai_san_pham", SqlDbType.NVarChar).Value = id_cha_loai_san_pham;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('sửa thành công'); window.open('"+Session["LoaiSanPham"].ToString()+"','_self', 1); </script>");
            }
            catch (Exception x)
            {
               Response.Write("<script language='javascript'> alert('sửa bị lỗi, Vì liên kết bảng!'); history.go(-1); </script>");
            
            }
        }

        protected void TextBox4_TextChanged(object sender, EventArgs e)
        {
            try
            {
                {
                    DropDownList1.DataSource = null;
                    System.Data.DataTable lsp = new System.Data.DataTable();
                    {
                        string sql = "select id_loai_san_pham, ten_loai_san_pham from loai_san_pham where cap_do_loai_san_pham = " + (int.Parse(TextBox4.Text) - 1);
                        WebApplication2.YNNSHOP56131778.CONGFIG.connect cnt = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                        System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                        System.Data.SqlClient.SqlCommand lenh = new System.Data.SqlClient.SqlCommand(sql, ketnoi);
                        ketnoi.Open();
                        System.Data.SqlClient.SqlDataAdapter data = new System.Data.SqlClient.SqlDataAdapter(lenh);
                        data.Fill(lsp);
                        ketnoi.Close();
                        for (int i = 0; i < lsp.Rows.Count; i++)
                            lsp.Rows[i][1] = mH.Base64Decode(lsp.Rows[i][1].ToString());
                        if ((int.Parse(TextBox4.Text))==0)
                        {
                            System.Data.DataRow row;
                        row = lsp.NewRow();
                        row[0] = 0;
                        row[1] = "cao nhất";
                        lsp.Rows.Add(row);
                        }
                        DropDownList1.DataSource = lsp;
                        DropDownList1.DataTextField = "ten_loai_san_pham";
                        DropDownList1.DataValueField = "id_loai_san_pham";
                        DropDownList1.DataBind();
                        try { DropDownList1.Items.FindByValue(id_cha + "").Selected = true; }
                        catch (Exception a) { }
                    }

                }
            }
            catch (Exception x)
            {

            }
        }
    }
}






