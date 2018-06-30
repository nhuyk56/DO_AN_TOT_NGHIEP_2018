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
    public partial class them : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            string loi = "Dữ liệu đầu vào chưa chính xác";
            try
            {
                if (kiem_tra_email_ton_tai(TextBox0.Text) != false)
                {
                    loi = "Email đã tồn tại!";
                    int.Parse("a");
                }
                    int id_tinh_trang_nguoi = 11111;
                    bool la_nhan_vien = true;
                    var info = TimeZoneInfo.FindSystemTimeZoneById("SE Asia Standard Time");
                    DateTimeOffset localServerTime = DateTimeOffset.Now;
                    DateTimeOffset localTime = TimeZoneInfo.ConvertTime(localServerTime, info);
                    //time hien tai localTime.DateTime.ToString()
                    //return localTime.DateTime.ToString();
                    //1.id
                    maHoa m = new maHoa();
                    int id_nguoi = khoi_tao_id_nguoi();
                    nguoi_them(id_nguoi, 11112,
                        TextBox0.Text,
                        m.Base64Encode(TextBox1.Text),
                    TextBox2.Text,
                    TextBox3.Text,
                    TextBox4.Text,
                   TextBox5.Text,
                   RadioButton1.Checked,
                   localTime.DateTime.ToString(), false);
                    if (kiem_tra_email_ton_tai(TextBox0.Text) != true)
                    {
                        loi ="tạo không thành công, thử lại sau";
                        int.Parse("a");
                    }
                Response.Write("<script language='javascript'> alert('Thêm thành công'); window.open('DanhSach.aspx','_self', 1); </script>");
            }
            catch (Exception x)
            {
                Response.Write("<script language='javascript'> alert('"+loi+"'); history.go(-1);</script>");
            }
        }
        public void nguoi_them(int id_nguoi, int id_tinh_trang_nguoi, string email_nguoi,
                string mat_khau_nguoi, string ten_nguoi, string ngay_sinh, string dia_chi_nguoi,
                string sdt_nguoi, bool gioi_tinh_nguoi, string ngay_tao_nguoi, bool la_nhan_vien)
        {
            /*
              nguoi(id_nguoi, id_tinh_trang_nguoi,email_nguoi,
		        mat_khau_nguoi, ten_nguoi, ngay_sinh, dia_chi_nguoi,
		        sdt_nguoi, gioi_tinh_nguoi, ngay_tao_nguoi, la_nhan_vien)
            */
            connect connect = new connect();
            SqlConnection connDB = new SqlConnection(connect.getconnect());
            SqlCommand cmd = new SqlCommand("nguoi_them", connDB);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@id_nguoi", SqlDbType.NVarChar).Value = id_nguoi;
            cmd.Parameters.Add("@id_tinh_trang_nguoi", SqlDbType.NVarChar).Value = id_tinh_trang_nguoi;
            cmd.Parameters.Add("@email_nguoi", SqlDbType.NVarChar).Value = email_nguoi;
            cmd.Parameters.Add("@mat_khau_nguoi", SqlDbType.NVarChar).Value = mat_khau_nguoi;
            cmd.Parameters.Add("@ten_nguoi", SqlDbType.NVarChar).Value = ten_nguoi;
            cmd.Parameters.Add("@ngay_sinh", SqlDbType.NVarChar).Value = ngay_sinh;
            cmd.Parameters.Add("@dia_chi_nguoi", SqlDbType.NVarChar).Value = dia_chi_nguoi;
            cmd.Parameters.Add("@sdt_nguoi", SqlDbType.NVarChar).Value = sdt_nguoi;
            cmd.Parameters.Add("@gioi_tinh_nguoi", SqlDbType.NVarChar).Value = gioi_tinh_nguoi;
            cmd.Parameters.Add("@ngay_tao_nguoi", SqlDbType.NVarChar).Value = ngay_tao_nguoi;
            cmd.Parameters.Add("@la_nhan_vien", SqlDbType.NVarChar).Value = 1;
            connDB.Open();
            cmd.ExecuteNonQuery();
            connDB.Close();
        }

        private bool kiem_tra_email_ton_tai(string email_nguoi)
        {
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand("select * from nguoi where email_nguoi='" + email_nguoi + "'", ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            data.Fill(ds);
            DataRow[] foundRows;
            foundRows = ds.Select();
            if (ds != null && ds.Rows.Count != 0)
                return true;//khong
            else return false;
        }
        private int khoi_tao_id_nguoi()
        {
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand("select top 1 id_nguoi from nguoi order by id_nguoi desc", ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            data.Fill(ds);
            DataRow[] foundRows;
            foundRows = ds.Select();
            int id_nguoi = Int32.Parse(foundRows[0][0].ToString());
            return id_nguoi + 1;
        }
    }
}