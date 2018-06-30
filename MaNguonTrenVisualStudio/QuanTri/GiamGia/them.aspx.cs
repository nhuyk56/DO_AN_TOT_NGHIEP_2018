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
    public partial class them : System.Web.UI.Page
    {
        connect connect;
        WebApplication2.QuanTri.maHoa mH;
        protected void Page_Load(object sender, EventArgs e)
        {
            mH = new WebApplication2.QuanTri.maHoa();
            connect = new connect();
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            System.DateTime batdau = DateTime.Parse(Request.Form[TextBox4.UniqueID]);
            System.DateTime ketthuc = DateTime.Parse(Request.Form[TextBox5.UniqueID]);
            System.DateTime hientai = DateTime.Now;
            TimeSpan ketqua=ketthuc.Subtract(batdau);
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
                        SqlCommand cmd = new SqlCommand("giam_gia_Them", connDB);
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.Add("@ma_giam_gia", SqlDbType.NVarChar).Value = TextBox2.Text;
                        cmd.Parameters.Add("@ten_giam_gia", SqlDbType.NVarChar).Value = TextBox1.Text;
                        cmd.Parameters.Add("@ly_do_giam_gia", SqlDbType.NVarChar).Value = TextBox3.Text; ;
                        cmd.Parameters.Add("@thoi_diem_bat_dau_giam_gia", SqlDbType.NVarChar).Value = batdau.ToString();
                        cmd.Parameters.Add("@thoi_diem_ket_thuc_giam_gia", SqlDbType.NVarChar).Value = ketthuc.ToString();
                        connDB.Open();
                        cmd.ExecuteNonQuery();
                        connDB.Close();
                        Response.Write("<script language='javascript'> alert('Thêm thành công');  window.open(document.referrer,'_self', 1);</script>");
            }
            catch(Exception qr)
            {
                Response.Write("<script language='javascript'> alert('"+loi+"'); history.go(-1)</script>");
            }
            
        }
    }
}