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
    public partial class them : System.Web.UI.Page
    {
        connect connect;
         WebApplication2.QuanTri.maHoa mH;
        protected void Page_Load(object sender, EventArgs e)
        {
             mH = new WebApplication2.QuanTri.maHoa();
                connect = new connect();
        }

        protected void TextBox4_TextChanged(object sender, EventArgs e)
        {
            try 
            {

                
                
                {
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
                    }

                }
            }
            catch (Exception x)
            {

            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            //
            try
            {
                DataTable x = new DataTable();
                string sql = "select top 1 id_loai_san_pham + 1 from loai_san_pham  order by id_loai_san_pham desc ";
                System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                System.Data.SqlClient.SqlCommand lenh = new System.Data.SqlClient.SqlCommand(sql, ketnoi);
                ketnoi.Open();
                System.Data.SqlClient.SqlDataAdapter data = new System.Data.SqlClient.SqlDataAdapter(lenh);
                data.Fill(x);
                ketnoi.Close();

                string ten_loai_san_pham = mH.Base64Encode(TextBox1.Text);
                string anh_loai_san_pham = TextBox2.Text;
                string bieu_tuong_loai_san_pham = TextBox3.Text;
                string cap_do_loai_san_pham = (TextBox4.Text);
                string id_cha_loai_san_pham = DropDownList1.SelectedValue;
                //
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("loai_san_pham_them", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_loai_san_pham", SqlDbType.NVarChar).Value = x.Rows[0][0];
                    cmd.Parameters.Add("@ten_loai_san_pham", SqlDbType.NVarChar).Value = ten_loai_san_pham;
                    cmd.Parameters.Add("@anh_loai_san_pham", SqlDbType.NVarChar).Value = anh_loai_san_pham;                    
                cmd.Parameters.Add("@bieu_tuong_loai_san_pham", SqlDbType.NVarChar).Value = bieu_tuong_loai_san_pham;
                    cmd.Parameters.Add("@cap_do_loai_san_pham", SqlDbType.NVarChar).Value = cap_do_loai_san_pham;
                    cmd.Parameters.Add("@id_cha_loai_san_pham", SqlDbType.NVarChar).Value = id_cha_loai_san_pham;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('Thêm thành công'); window.open('danhsachloaisanpham.aspx','_self', 1); </script>");
            }
            catch (Exception x)
            {
                Response.Write("<script language='javascript'> alert('Thêm bị lỗi!'); history.go(-1); </script>");

            }
        }
    }
}