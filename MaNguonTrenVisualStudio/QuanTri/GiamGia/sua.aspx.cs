using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.GiamGia
{
    public partial class sua : System.Web.UI.Page
    {
        connect connect;
        int id_giam_gia = 0;
        protected void Page_Load(object sender, EventArgs e)
        {
            string loi = "sửa bị lỗi!";
                try{

                    try
                    {
                        if (Request.QueryString["id_giam_gia"] != null && Request.QueryString["id_giam_gia"] != "")
                            id_giam_gia = int.Parse(Request.QueryString["id_giam_gia"]);
                    }
                    catch (Exception a) { }
                    if (id_giam_gia == 0) Response.Redirect("./danhSach.aspx");
                    //

                    connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                    string sql =
"select CASE    WHEN DATEDIFF(MINUTE, GETDATE(),thoi_diem_ket_thuc_giam_gia)>0 THEN 1    ELSE 0  END from giam_gia where id_giam_gia=" + id_giam_gia;
                    DataTable ds = new DataTable();
                    System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                    new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                    if (int.Parse(ds.Rows[0][0].ToString()) == 0)
                    {
                        loi = "Không thể sửa! Chương trình giảm giá này đã diễn ra! tốt đẹp!";
                        int.Parse("a");
                    }
                    ////

                     sql = "select id_giam_gia, ma_giam_gia, ten_giam_gia, ly_do_giam_gia, cast(FORMAT(thoi_diem_bat_dau_giam_gia, 'yyyy/MM/dd HH:mm:ss')as varchar), cast(FORMAT(thoi_diem_ket_thuc_giam_gia, 'yyyy/MM/dd HH:mm:ss')as varchar) from giam_gia where id_giam_gia=" + id_giam_gia;
                    connect = new connect();
                     ds = new DataTable();
                    new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                    if (!IsPostBack)
                    {
                        TextBox1.Text = ds.Rows[0][2].ToString();
                        TextBox2.Text = ds.Rows[0][1].ToString();
                        TextBox3.Text = ds.Rows[0][3].ToString();
                        TextBox4.Text = ds.Rows[0][4].ToString();
                        TextBox5.Text = ds.Rows[0][5].ToString();
                    }
                
                }
                catch (Exception a) {
                    Response.Write("<script language='javascript'> alert('" + loi + "'); window.open('" + Session["GiamGia"].ToString() + "','_self', 1); </script>");
                }
           
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            System.DateTime batdau = DateTime.Parse(Request.Form[TextBox4.UniqueID]);
            System.DateTime ketthuc = DateTime.Parse(Request.Form[TextBox5.UniqueID]);
            System.DateTime hientai = DateTime.Now;
            TimeSpan ketqua = ketthuc.Subtract(batdau);
            string loi = "";
            try
            {
                if (batdau.Subtract(hientai).TotalDays < 0 ||
                    batdau.Subtract(hientai).TotalDays > 1 ||
                    batdau.Subtract(hientai).TotalHours < 0 ||
                    batdau.Subtract(hientai).TotalHours > 24 ||
                    batdau.Subtract(hientai).TotalMinutes < 30)
                {
                    loi = "Thời điểm bắt đầu phải sau hiện tại ít nhất 30 phút để chuẩn bị! nhiều nhất 24 giờ";
                    int.Parse("aaaa");
                }

                if (ketthuc.Subtract(batdau).TotalDays < 0 ||
                    ketthuc.Subtract(batdau).TotalDays > 1 ||
                    ketthuc.Subtract(batdau).TotalHours < 0 ||
                    ketthuc.Subtract(batdau).TotalHours > 24 ||
                ketthuc.Subtract(batdau).TotalMinutes < 5
                )
                {
                    loi = "Thời điểm kết thúc phải sau thời điểm bắt đầu ít nhất 5 phút, nhiều nhất 24 giờ";
                    int.Parse("aaaa");
                }

                loi = "Lỗi dữ liệu!";
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("giam_gia_sua", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_giam_gia", SqlDbType.NVarChar).Value = id_giam_gia;
                cmd.Parameters.Add("@ma_giam_gia", SqlDbType.NVarChar).Value = TextBox2.Text;
                cmd.Parameters.Add("@ten_giam_gia", SqlDbType.NVarChar).Value = TextBox1.Text;
                cmd.Parameters.Add("@ly_do_giam_gia", SqlDbType.NVarChar).Value = TextBox3.Text;
                cmd.Parameters.Add("@thoi_diem_bat_dau_giam_gia", SqlDbType.NVarChar).Value = batdau.ToString();
                cmd.Parameters.Add("@thoi_diem_ket_thuc_giam_gia", SqlDbType.NVarChar).Value = ketthuc.ToString();
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('Sửa thành công');  window.open('./danhSach.aspx','_self', 1);</script>");
            }
            catch (Exception qr)
            {
                Response.Write("<script language='javascript'> alert('" + loi + "'); history.go(-1)</script>");
            }
        }
    }
}