using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data.SqlClient;
using System.Data;
using WebApplication2.YNNSHOP56131778.CLASS;
using WebApplication2.YNNSHOP56131778.OBJECT;
using WebApplication2.YNNSHOP56131778.CONGFIG;
using System.IO;
using Newtonsoft.Json;
using System.Text;
using WebApplication2.YNNSHOP56131778.OBJECT.yeu_cau_danh_sach;
using WebApplication2.YNNSHOP56131778.OBJECT.nguoi_dung;
using WebApplication2.YNNSHOP56131778.OBJECT.san_pham;
using WebApplication2.YNNSHOP56131778.OBJECT.giam_gia;
using WebApplication2.YNNSHOP56131778.OBJECT.gio_hang;
using WebApplication2.YNNSHOP56131778.FUNCTION;
using WebApplication2.YNNSHOP56131778.OBJECT.van_chuyen;
using WebApplication2.YNNSHOP56131778.OBJECT.thong_tin_don_hang;
using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Net;
using System.Net.Mail;
using System.Data;
using System.Data.SqlClient;
using System.Configuration;
using System.Text;
namespace WebApplication2.YNNSHOP56131778.WEBSERVICE
{
    /// <summary>
    /// Summary description for Lay_Du_Lieu_Trang_Menu
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class Lay_Du_Lieu_Trang_Menu : System.Web.Services.WebService
    {
        //dưới đây là khu vực khôi phục mật khẩu
        public string lay_key()
        {
            Random rand = new Random((int)DateTime.Now.Ticks);
            int numIterations = 0;
            numIterations = rand.Next(1, 100);
            string a = Base64Encode(Base64Encode(DateTime.Now.ToString()));
            string kq = "";
            for (int i = 0; i < a.Length; i++)
            {
                kq += a[rand.Next(0, a.Length - 1)];
            }
            int point = rand.Next(0, a.Length - 5);
            return kq.Substring(point, 5).ToUpper();
        }
        [WebMethod]
        public string tao_code(string email)
        {
            string sql = "select * from nguoi  where email_nguoi='" + email + "'";
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand(sql, ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            data.Fill(ds);
            DataRow[] foundRows;
            foundRows = ds.Select();
            ketnoi.Close();
            if (ds != null && ds.Rows.Count != 0)
                try
                {
                    string key = lay_key();
                    Application[email] = key;
                    using (MailMessage mailMessage = new MailMessage())
                    {
                        mailMessage.From = new MailAddress("ynn.shop.2018@gmail.com");
                        mailMessage.Subject = "KHÔI PHỤC MẬT KHẨU";
                        mailMessage.Body = PopulateBody(email, key);
                        mailMessage.IsBodyHtml = true;
                        mailMessage.To.Add(new MailAddress(email));
                        SmtpClient smtp = new SmtpClient();
                        smtp.Host = "smtp.gmail.com";
                        smtp.EnableSsl = true;
                        System.Net.NetworkCredential NetworkCred = new System.Net.NetworkCredential();
                        NetworkCred.UserName = "ynn.shop.2018@gmail.com";
                        NetworkCred.Password = "@@ynn.shop.2018";
                        smtp.UseDefaultCredentials = true;
                        smtp.Credentials = NetworkCred;
                        smtp.Port = 587;
                        smtp.Send(mailMessage);
                    }
                    return new thong_bao(true, "Đã gửi mã khôi phục đến " + email, new System.Dynamic.ExpandoObject()).ParserJSon();
                }
                catch (Exception e)
                {
                    return e.ToString();
                }
            return new thong_bao(false, "Email không tồn tại trong hệ thống", new System.Dynamic.ExpandoObject()).ParserJSon();
        }
        string PopulateBody(string user, string pass)
        {
            string body = string.Empty;
            using (StreamReader reader = new StreamReader(Server.MapPath("~/EmailTemplate.htm")))
            {
                body = reader.ReadToEnd();
            }
            body = body.Replace("{user}", user);
            body = body.Replace("{pass}", pass);
            return body;
        }
        [WebMethod]
        public string khoi_phuc_mat_khau(string json)
        {
            khoi_phuc_mat_khau dmk = JsonConvert.DeserializeObject<khoi_phuc_mat_khau>(json);
            string email = dmk.email;
            string key = dmk.key;
            string mat_khau_moi = dmk.mat_khau_moi;
            if (Application[email] != null)
                if (Application[email].ToString() == key)
                {
                    connect connect = new connect();
                    SqlConnection connDB = new SqlConnection(connect.getconnect());
                    SqlCommand cmd = new SqlCommand("doi_mat_khau", connDB);
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.Add("@email_nguoi", SqlDbType.NVarChar).Value = email;
                    cmd.Parameters.Add("@mat_khau_nguoi", SqlDbType.NVarChar).Value = mat_khau_moi;
                    connDB.Open();
                    cmd.ExecuteNonQuery();
                    connDB.Close();
                    Application[email] = null;
                    return new thong_bao(true, "khôi phục mật khẩu thành công!", new System.Dynamic.ExpandoObject()).ParserJSon();
                }

            return new thong_bao(false, "Code khôi phục mật khẩu đã hết hạn hoặc không tồn tại!", new System.Dynamic.ExpandoObject()).ParserJSon();
        }
        public static string Base64Encode(string plainText)
        {
            var plainTextBytes = System.Text.Encoding.UTF8.GetBytes(plainText);
            return System.Convert.ToBase64String(plainTextBytes);
        }
        public static string Base64Decode(string base64EncodedData)
        {
            var base64EncodedBytes = System.Convert.FromBase64String(base64EncodedData);
            return System.Text.Encoding.UTF8.GetString(base64EncodedBytes);
        }
        //khu vực trên là khu vực khôi phục mật khẩu
        [WebMethod]
        public string lay_danh_sach_anh_trinh_chieu()
        {
            string sql = "select duong_link_anh as link from anh_trinh_chieu";
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand(sql, ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            data.Fill(ds);
            ketnoi.Close();
            return DataTableToJSONWithJSONNet(ds);
        }
        [WebMethod]
        public string lay_danh_sach_yeu_thich_nguoi_dung(string id_nguoi)
        {
            string sql = " select sp.id_san_pham, sp.ten_san_pham, sp.anh_san_pham, sp.gia_cao, sp.gia_ban_ra, ds.so_sao_tb, ds.so_tim " +
                        " from ( " +
                        " select id_nguoi_tim.id_san_pham, id_nguoi_tim.so_tim ,   ROUND(AVG(CAST(dg.so_sao_danh_gia AS FLOAT)), 1) AS so_sao_tb " +
                        " from " +
                        " ( " +
                        " select id_sp.id_san_pham, count(dsmm.id_nguoi) as so_tim " +
                        " from ( " +
                        " select id_san_pham " +
                        " from danh_sach_mong_muon " +
                        " where id_nguoi=" + id_nguoi + " " +
                        " ) as id_sp join danh_sach_mong_muon dsmm on id_sp.id_san_pham=dsmm.id_san_pham " +
                        " group by id_sp.id_san_pham " +
                        " ) as id_nguoi_tim join danh_gia dg on id_nguoi_tim.id_san_pham=dg.id_san_pham " +
                        " group by id_nguoi_tim.id_san_pham, id_nguoi_tim.so_tim  " +
                        " ) ds join san_pham sp on ds.id_san_pham=sp.id_san_pham " +
                        " order by so_sao_tb desc, so_tim desc";
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand(sql, ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            data.Fill(ds);
            ketnoi.Close();
            return DataTableToJSONWithJSONNet(ds);
        }
        [WebMethod]
        public string lay_danh_sach_chi_tiet_don_hang(string id_don_hang)
        {
            string sql = "select sp.id_san_pham, sp.ten_san_pham, sp.anh_san_pham, ctdh.id_don_hang, ctdh. ma_giam_gia, ctdh.so_luong, ctdh.gia_da_giam, ctdh.gia_san_pham from chi_tiet_don_hang ctdh join san_pham sp on ctdh.id_san_pham=sp.id_san_pham where id_don_hang=" + id_don_hang;
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand(sql, ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            data.Fill(ds);
            ketnoi.Close();
            return DataTableToJSONWithJSONNet(ds);
        }
        [WebMethod]
        public string huy_don_hang(string id_don_hang)
        {
            try
            {
                connect connect = new connect();
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("huy_don_hang", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_don_hang", SqlDbType.NVarChar).Value = id_don_hang;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
            }
            catch(Exception e)
            {
                return new thong_bao(false, "Lỗi hệ thống " + e.ToString(), new System.Dynamic.ExpandoObject()).ParserJSon();
            }
            return new thong_bao(true, "Hủy đơn hàng thành công" ,new System.Dynamic.ExpandoObject()).ParserJSon();
        }
        [WebMethod]
        public string lay_thong_tin_don_hang(string id_don_hang)
        {
            string sql = "select dh.id_don_hang, ttdh.id_ttdh, ttdh.ten_ttdh, ptvc.ten_ptvc,  dh.ma_van_chuyen, dh.phi_van_chuyen, dh.phi_thu_ho,  (dh.tong_tien-dh.phi_thu_ho-dh.phi_van_chuyen) as tien_thu_ho,  dh.tong_tien,  cast(FORMAT(dh.ngay_tao_don_hang, 'dd-MM-yyyy HH:mm:ss') as varchar) as ngay_tao_don_hang,  cast(FORMAT(dh.ngay_xac_nhan_don_hang_thanh_cong, 'dd-MM-yyyy HH:mm:ss') as varchar) as ngay_xac_nhan_don_hang_thanh_cong,  dh.so_ngay_giao_gan_nhat, dh.so_ngay_giao_xa_nhat,   CONCAT(tinh.ten_khu_vuc, ', ' ,huyen.ten_khu_vuc, ', ',  dh.dia_chi_nhan_hang_chi_tiet)  as dia_chi_nhan_hang_chi_tiet  from  don_hang dh join tinh_trang_don_hang ttdh on dh.id_ttdh=ttdh.id_ttdh  join phuong_thuc_van_chuyen ptvc on ptvc.id_ptvc=dh.id_ptvc    join khu_vuc huyen on huyen.id_khu_vuc=dh.id_khu_vuc  join khu_vuc tinh on huyen.id_khu_vuc_cha=tinh.id_khu_vuc   where id_don_hang=" + id_don_hang;
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand(sql, ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            data.Fill(ds);
            ketnoi.Close();
            return DataTableToJSONWithJSONNet(ds);

        }
         [WebMethod]
        public string lay_danh_sach_don_hang(string id_nguoi){
            string sql = "select dh.id_don_hang as ma_don_hang, ttdh.ten_ttdh as tinh_trang_don_hang, dh.tong_tien as tong_tien_thanh_toan, cast(FORMAT(dh.ngay_tao_don_hang, 'dd-MM-yyyy HH:mm:ss') as varchar)  as ngay_dat_hang from don_hang dh join tinh_trang_don_hang ttdh on dh.id_ttdh=ttdh.id_ttdh where id_nguoi="+id_nguoi;
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand(sql, ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            data.Fill(ds);
            ketnoi.Close();
            return DataTableToJSONWithJSONNet(ds);

        }
        [WebMethod]
        public string nguoi_doi_mat_khau(string json)
        {
            string sql = "select * from nguoi where email_nguoi='{email}' and mat_khau_nguoi='{matkhau}'";
            doi_mat_khau dmk = JsonConvert.DeserializeObject<doi_mat_khau>(json);
            if (dmk.email_nguoi.Contains("'") || 
                dmk.mat_khau_nguoi.Contains("'")||
                dmk.mat_khau_moi.Contains("'") ||
                dmk.email_nguoi.Contains("/") || 
                dmk.mat_khau_nguoi.Contains("/")||
                dmk.mat_khau_moi.Contains("/") 
                )
                return new thong_bao(false, "không được chứa /, '", new System.Dynamic.ExpandoObject()).ParserJSon();
            sql = sql.Replace("{email}", dmk.email_nguoi);
            sql = sql.Replace("{matkhau}", dmk.mat_khau_nguoi);
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand(sql, ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            data.Fill(ds);
            DataRow[] foundRows;
            foundRows = ds.Select();
            ketnoi.Close();
            if (ds != null && ds.Rows.Count != 0)
            {
                doi_mk(dmk.email_nguoi, dmk.mat_khau_moi);
                return new thong_bao(true, "Đổi mật khẩu thành công! Mời bạn đăng nhập lại!", new System.Dynamic.ExpandoObject()).ParserJSon();
            }
            return new thong_bao(false, "sai mật khẩu", new System.Dynamic.ExpandoObject()).ParserJSon();
         
        }
        private void doi_mk(string email, string mat_khau_moi)
        {
            connect connect = new connect();
            SqlConnection connDB = new SqlConnection(connect.getconnect());
            SqlCommand cmd = new SqlCommand("doi_mat_khau", connDB);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@email_nguoi", SqlDbType.NVarChar).Value = email;
            cmd.Parameters.Add("@mat_khau_nguoi", SqlDbType.NVarChar).Value = mat_khau_moi;
            connDB.Open();
            cmd.ExecuteNonQuery();
            connDB.Close();
        }
        [WebMethod]
        public string nguoi_sua(string json)
        {
            nguoi data;
            try
            {
                data = JsonConvert.DeserializeObject<nguoi>(json);
            }
            catch(Exception e)
            {
                return new thong_bao(false, "dữ liệu không hợp lệ, vui lòng thử lại!", new System.Dynamic.ExpandoObject()).ParserJSon(); ;
            }
            nguoi_sua(
                data.getemail_nguoi(),
                data.getten_nguoi(),
                data.getngay_sinh(),
                data.getdia_chi_nguoi(),
                data.getsdt_nguoi(),
                data.getgioi_tinh_nguoi());
            return new thong_bao(true, "Sửa thành công!", new System.Dynamic.ExpandoObject()).ParserJSon(); ;
        }
        public void nguoi_sua(string email, string ten_nguoi, string ngay_sinh,string  dia_chi_nguoi,
		        string sdt_nguoi, bool gioi_tinh_nguoi)
        {
            /*
              nguoi(id_nguoi, id_tinh_trang_nguoi,email_nguoi,
		        mat_khau_nguoi, ten_nguoi, ngay_sinh, dia_chi_nguoi,
		        sdt_nguoi, gioi_tinh_nguoi, ngay_tao_nguoi, la_nhan_vien)
            */
            connect connect = new connect();
            SqlConnection connDB = new SqlConnection(connect.getconnect());
            SqlCommand cmd = new SqlCommand("nguoi_sua", connDB);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@email_nguoi", SqlDbType.NVarChar).Value =email;
            cmd.Parameters.Add("@ten_nguoi", SqlDbType.NVarChar).Value = ten_nguoi;
            cmd.Parameters.Add("@ngay_sinh", SqlDbType.NVarChar).Value = ngay_sinh;
            cmd.Parameters.Add("@dia_chi_nguoi", SqlDbType.NVarChar).Value = dia_chi_nguoi;
            cmd.Parameters.Add("@sdt_nguoi", SqlDbType.NVarChar).Value = sdt_nguoi;
            cmd.Parameters.Add("@gioi_tinh_nguoi", SqlDbType.NVarChar).Value = gioi_tinh_nguoi;
            connDB.Open();
            cmd.ExecuteNonQuery();
            connDB.Close();
        }
        [WebMethod]
        public string nguoi_xem(string id_nguoi)
        {
            nguoi nguoi_xem;
            string sql = "select email_nguoi,		ten_nguoi,		cast(FORMAT(ngay_sinh, 'yyyy-MM-dd') as varchar) as ngay_sinh,		dia_chi_nguoi,		sdt_nguoi,		gioi_tinh_nguoi from nguoi where id_nguoi=" + id_nguoi;
            connect connect = new connect();
            DataTable nguoi_dt = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh;
            new SqlDataAdapter(new SqlCommand(sql, ketnoi)).Fill(nguoi_dt);
            DataRow[] dong_nguoi = nguoi_dt.Select();
             nguoi_xem = new nguoi(
                 dong_nguoi[0][0].ToString(),
                 "********************",
             dong_nguoi[0][1].ToString(),
             dong_nguoi[0][2].ToString(),
             dong_nguoi[0][3].ToString(),
             dong_nguoi[0][4].ToString(),
             bool.Parse(dong_nguoi[0][5].ToString())
                 );
             return nguoi_xem.ParserJSon();
           
        }
         [WebMethod]
        public string dat_hang(string json)
        {
            don_hang d_hang = JsonConvert.DeserializeObject<don_hang>(json);
            //check MGG
            int max = d_hang.chi_tiet_don_hang.Count;
            for (int i = 0; i < max; i++)
            {
                if (d_hang.chi_tiet_don_hang[i].ma_giam_gia != null && d_hang.chi_tiet_don_hang[i].ma_giam_gia != "")
                    if (kiem_tra_ma_giam_gia(d_hang.chi_tiet_don_hang[i].ma_giam_gia) == false)
                        return new thong_bao(false, "Xin lỗi! Đơn hàng bị từ chối vì mã giảm giá đã hết hạn", new System.Dynamic.ExpandoObject()).ParserJSon();
                if (kiem_tra_so_luong(d_hang.chi_tiet_don_hang[i].id_san_pham, d_hang.chi_tiet_don_hang[i].so_luong) == false)
                    return new thong_bao(false, "Xin lỗi! Đơn hàng bị từ chối vì sản phẩm thứ "+i+" không đủ số lượng hoặc hết hàng!", new System.Dynamic.ExpandoObject()).ParserJSon();  
            }
            //lấy id_don_hang
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand("select top 1 id_don_hang from don_hang order by id_don_hang desc", ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            int id_don_hang = -1;
            data.Fill(ds);
            if ((ds != null && ds.Rows.Count != 0))
            {
                DataRow[] foundRows;
                foundRows = ds.Select();
                id_don_hang = Int32.Parse(foundRows[0][0].ToString()) + 1;
            }
            else id_don_hang = 11110;
            proc_don_hang_them(
                   id_don_hang,
                   d_hang.id_nguoi,
                   d_hang.id_ptvc,
                   d_hang.id_khu_vuc,
                   d_hang.phi_van_chuyen,
                   d_hang.phi_thu_ho,
                   d_hang.tong_tien,
                   d_hang.so_ngay_giao_gan_nhat,
                   d_hang.so_ngay_giao_xa_nhat,
                   d_hang.dia_chi_nhan_hang_chi_tiet
                   );
            for (int i = 0; i < max; i++)
            {
                pro_chi_tiet_don_hang_them(
                                        id_don_hang,
                                        d_hang.chi_tiet_don_hang[i].id_san_pham,
                                        d_hang.chi_tiet_don_hang[i].ma_giam_gia,
                                        d_hang.chi_tiet_don_hang[i].so_luong,
                                        d_hang.chi_tiet_don_hang[i].gia_san_pham,
                                        d_hang.chi_tiet_don_hang[i].gia_da_giam
                             );
            }
            ketnoi.Close();
            return new thong_bao(true,id_don_hang+"", new System.Dynamic.ExpandoObject()).ParserJSon();        
         }
        public bool kiem_tra_ma_giam_gia(string MGG)
         {
             string sql = "select * from  giam_gia where DATEDIFF(MINUTE, GETDATE(), thoi_diem_ket_thuc_giam_gia) > 0 and  ma_giam_gia='"+MGG+"' ";
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand(sql, ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            int id_don_hang = -1;
            data.Fill(ds);
            if (ds != null && ds.Rows.Count != 0)
                return true;
            return false;
        }
        public bool kiem_tra_so_luong(int id_san_pham, int so_luong)
        {
            string sql = "select * from ( select id_san_pham, sum(so_luong) as so_luong from chi_tiet_kho_hang group by id_san_pham ) as sp_sl where id_san_pham="+id_san_pham+" and so_luong>="+so_luong;
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand(sql, ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            data.Fill(ds);
            if (ds != null && ds.Rows.Count != 0)
                return true;
            return false;
        }
        public void xu_ly_nghi_van_hack(int id_don_hang)
        {
            connect connect = new connect();
            SqlConnection connDB = new SqlConnection(connect.getconnect());
            SqlCommand cmd = new SqlCommand("xu_ly_nghi_van_hack", connDB);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@id_don_hang", SqlDbType.NVarChar).Value = id_don_hang;
            connDB.Open();
            cmd.ExecuteNonQuery();
            connDB.Close();
        }
        public void pro_chi_tiet_don_hang_them(
                                        int id_don_hang,
	                                    int id_san_pham ,
	                                    string ma_giam_gia ,
	                                    int so_luong,
	                                    long gia_san_pham,
	                                    long gia_da_giam
        )
        {

            connect connect = new connect();
            SqlConnection connDB = new SqlConnection(connect.getconnect());
            SqlCommand cmd = new SqlCommand("pro_chi_tiet_don_hang_them", connDB);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@id_don_hang", SqlDbType.NVarChar).Value = id_don_hang;
            cmd.Parameters.Add("@id_san_pham", SqlDbType.NVarChar).Value = id_san_pham;
            if (ma_giam_gia == "" || ma_giam_gia == null)
                cmd.Parameters.Add("@ma_giam_gia", SqlDbType.NVarChar).Value = System.DBNull.Value;
            else
            cmd.Parameters.Add("@ma_giam_gia", SqlDbType.NVarChar).Value = ma_giam_gia;
            cmd.Parameters.Add("@so_luong", SqlDbType.NVarChar).Value = so_luong;
            cmd.Parameters.Add("@gia_san_pham", SqlDbType.NVarChar).Value = gia_san_pham;
            cmd.Parameters.Add("@gia_da_giam", SqlDbType.NVarChar).Value = gia_da_giam;
            connDB.Open();
            cmd.ExecuteNonQuery();
            connDB.Close();
        }
        public void proc_don_hang_them( int id_don_hang,
                                        int id_nguoi,
                                        int id_ptvc,
                                        int id_khu_vuc,
                                        long phi_van_chuyen,
                                        long phi_thu_ho,
                                        long tong_tien,
                                        float so_ngay_giao_gan_nhat,
                                        float so_ngay_giao_xa_nhat,
                                        string dia_chi_nhan_hang_chi_tiet
        )
        {

            connect connect = new connect();
            SqlConnection connDB = new SqlConnection(connect.getconnect());
            SqlCommand cmd = new SqlCommand("proc_don_hang_them", connDB);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@id_don_hang", SqlDbType.NVarChar).Value = id_don_hang;
            cmd.Parameters.Add("@id_nguoi", SqlDbType.NVarChar).Value = id_nguoi;
            cmd.Parameters.Add("@id_ptvc", SqlDbType.NVarChar).Value = id_ptvc;
            cmd.Parameters.Add("@id_khu_vuc", SqlDbType.NVarChar).Value = id_khu_vuc;
            cmd.Parameters.Add("@phi_van_chuyen", SqlDbType.NVarChar).Value = phi_van_chuyen;
            cmd.Parameters.Add("@phi_thu_ho", SqlDbType.NVarChar).Value = phi_thu_ho;
            cmd.Parameters.Add("@tong_tien", SqlDbType.NVarChar).Value = tong_tien;
            cmd.Parameters.Add("@so_ngay_giao_gan_nhat", SqlDbType.NVarChar).Value = so_ngay_giao_gan_nhat;
            cmd.Parameters.Add("@so_ngay_giao_xa_nhat", SqlDbType.NVarChar).Value = so_ngay_giao_xa_nhat;
            cmd.Parameters.Add("@dia_chi_nhan_hang_chi_tiet", SqlDbType.NVarChar).Value = dia_chi_nhan_hang_chi_tiet;
            connDB.Open();
            cmd.ExecuteNonQuery();
            connDB.Close();
        }

        [WebMethod]
        public string lay_danh_sach_phuong_thuc_van_chuyen(string query)
        {
            string tmp = query;
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh;
            DataRow[] foundRows;
            string sql_kho_hang = "select tinh_thanh.ma_khu_vuc, quan_huyen.ma_khu_vuc from kho_hang kh join khu_vuc quan_huyen on kh.id_khu_vuc=quan_huyen.id_khu_vuc join khu_vuc tinh_thanh on tinh_thanh.id_khu_vuc=quan_huyen.id_khu_vuc_cha";
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_kho_hang, ketnoi)).Fill(ds);
            if (!(ds != null && ds.Rows.Count != 0)) return "loi_lsp";
            foundRows = null;
            foundRows = ds.Select();
            List<dtvanchuyen> VC = new List<dtvanchuyen>();
            int max = ds.Rows.Count;
            for (int i = 0; i < max; i++)
            {
                query = tmp.Replace("{dia_chi_TTP_cua_kho}", foundRows[i][0].ToString()).Replace("{dia_chi_QH_cua_kho}", foundRows[i][1].ToString());
                MyWebRequest myRequest = new MyWebRequest("https://nhanh.vn/api/shipping/calculatefee", "POST", query);
                dtvanchuyen vanchuyen = JsonConvert.DeserializeObject<dtvanchuyen>(myRequest.GetResponse());
                VC.Add(vanchuyen);
            }        
            //price=12000&totalCod=12000&weight=10000&fromCity={dia_chi_TTP_cua_kho}&fromDistrict={dia_chi_QH_cua_kho}&toCity=3&toDistrict=29&contentId=0&carrierId=0&carrierServiceId=0&storeId=0
            //cách tối ưu là sql get ra 3 kho và kho nào ít nhất sẽ chọn kho đó
            dtvanchuyen vcmin = VC[0];
            long sum_min = 0;
            for (int j = 0; j < vcmin.data.Count; j++)
                sum_min += vcmin.data[j].totalFee;

            for (int i = 1; i < max; i++)
            {
                long sum = 0;
                for (int j = 0; j < VC[i].data.Count; j++)
                    sum += VC[i].data[j].totalFee;
                if(sum_min > sum)
                {
                    sum_min = sum;
                    vcmin = VC[i];
                }
            }

            //check
            List<Datum> arrdata = new List<Datum>();
            string check_ptvc = "select * from phuong_thuc_van_chuyen where id_ptvc=";
            for (int i = 0; i < vcmin.data.Count; i++ )
            {
                string sql_kt = ""+check_ptvc+vcmin.data[i].carrierId+""+vcmin.data[i].serviceType;
                ds = new DataTable();
                new SqlDataAdapter(new SqlCommand(sql_kt, ketnoi)).Fill(ds);
                if (ds != null && ds.Rows.Count != 0)
                    arrdata.Add(vcmin.data[i]);
            }
            return arrdata.ParserJSon();
        } 
        [WebMethod]
        public string lay_danh_sach_dia_chi(string id_khu_vuc_cha)
        {
            string sql_nguoi = "select * from khu_vuc where id_khu_vuc_cha=" + id_khu_vuc_cha;
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh;
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_nguoi, ketnoi)).Fill(ds);
            ketnoi.Close();
            return DataTableToJSONWithJSONNet(ds);

        }
        [WebMethod]
        public string lay_thong_tin_thanh_toan(string id_nguoi)
        {
            string sql_nguoi = "select ten_nguoi, email_nguoi, sdt_nguoi from nguoi where id_nguoi=" + id_nguoi;
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh;
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_nguoi, ketnoi)).Fill(ds);
            ketnoi.Close();
            return DataTableToJSONWithJSONNet(ds);
        }

          [WebMethod]
        public string kiem_tra_giam_gia(string json)
        {
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh;
            DataRow[] foundRows;
            sp_mgg data = JsonConvert.DeserializeObject<sp_mgg>(json);
            string sql_ktgg = "select gg.ma_giam_gia, cast(FORMAT(gg.thoi_diem_ket_thuc_giam_gia, 'HH:mm:ss') as varchar) as thoi_diem_ket_thuc_giam_gia, ds_sp_gg.phan_tram_giam, ds_sp_gg.giam_toi_da " +
                    "from giam_gia gg join danh_sac_san_pham_giam_gia ds_sp_gg on gg.id_giam_gia=ds_sp_gg.id_giam_gia " +
                    "where id_san_pham="+data.getid_san_pham()+" and ma_giam_gia='"+data.getma_giam_gia()+"' " +
                          " and DATEDIFF(MINUTE, GETDATE(), thoi_diem_ket_thuc_giam_gia) > 0 ";
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_ktgg, ketnoi)).Fill(ds);
            ketnoi.Close();
            return DataTableToJSONWithJSONNet(ds);
        }
       [WebMethod]
        public string Lay_danh_sach_san_pham_top_nam_sao()
        {
            connect connect = new connect();
            DataTable ds_t_ns = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh;
            DataRow[] foundRows;
            string sql_ds_top_nam_sao = " select top 16 sp.id_san_pham, sp.ten_san_pham, sp.anh_san_pham,sp.gia_cao, sp.gia_ban_ra, count(dsmm.id_nguoi) as so_tim " +
                                        " from  " +
                                        " ( " +
                                        " select sp.id_san_pham, count(dg.id_nguoi) as slsao " +
                                        " from san_pham sp " +
                                        " join danh_gia dg on sp.id_san_pham=dg.id_san_pham " +
                                        " where dg.so_sao_danh_gia=5 " +
                                       "  group by sp.id_san_pham " +
                                        " ) as sp_slsao join san_pham sp on sp_slsao.id_san_pham=sp.id_san_pham " +
                                        " join danh_sach_mong_muon dsmm on dsmm.id_san_pham=sp.id_san_pham " +
                                        " group by sp.id_san_pham, sp.ten_san_pham, sp.anh_san_pham,sp.gia_cao, sp.gia_ban_ra " +
                                        " order by so_tim desc ";
           // ds_t_ns = new DataTable();
          // new SqlDataAdapter(new SqlCommand(sql_ds_top_nam_sao, ketnoi)).Fill(ds_t_ns);
           try
           {
               ds_t_ns = new DataTable();
               new SqlDataAdapter(new SqlCommand(sql_ds_top_nam_sao, ketnoi)).Fill(ds_t_ns);
               foundRows = ds_t_ns.Select();
           }
           catch(Exception Exception)
           {
               ds_t_ns = new DataTable();
               new SqlDataAdapter(new SqlCommand(sql_ds_top_nam_sao, ketnoi)).Fill(ds_t_ns);
               foundRows = ds_t_ns.Select();
           }
            ketnoi.Close();
            return DataTableToJSONWithJSONNet(ds_t_ns);
        }
        [WebMethod]
        public string danh_muc_ua_thich()
        {
            connect connect = new connect();
            DataTable dmut = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh;
            DataRow[] foundRows;
            string sql_danh_muc_ua_thich =
                "select top 18 lsp.id_loai_san_pham, lsp.ten_loai_san_pham, lsp.anh_loai_san_pham " +
                 "from " +
                 "( " +
                     "select lsp.id_loai_san_pham, max(tmp.sl_tim) as max " +
                     "from  " +
                     "( " +
                         "select sp.id_san_pham, count(sp.id_san_pham) as sl_tim " +
                         "from danh_sach_mong_muon dsmm join san_pham sp on sp.id_san_pham=dsmm.id_san_pham " +
                         "group by sp.id_san_pham " +
                     ") as tmp join danh_sach_loai_san_pham_cua_san_pham dslspcsp on tmp.id_san_pham=dslspcsp.id_san_pham " +
                     "join loai_san_pham lsp on lsp.id_loai_san_pham=dslspcsp.id_loai_san_pham " +
                     "where lsp.anh_loai_san_pham <>'' and lsp.anh_loai_san_pham<>'http:' " +
                     "group by  lsp.id_loai_san_pham " +
                 ") as tmp join loai_san_pham lsp on tmp.id_loai_san_pham=lsp.id_loai_san_pham " +
                 "order by tmp.max desc ";
            dmut = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_danh_muc_ua_thich, ketnoi)).Fill(dmut);
            ketnoi.Close();
            return DataTableToJSONWithJSONNet(dmut);
        }
         [WebMethod]
        public string Lay_du_lieu_thuong_hieu_tot_nhat()
        {
            connect connect = new connect();
            DataTable thtn = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh;
            DataRow[] foundRows;
            string sql_thuong_hieu_tot_nhat = "select top 10 th.id_thuong_hieu, th.ten_thuong_hieu, th.anh_thuong_hieu, tmp.sl" +
               " from" +
               " (" +
               " select th.id_thuong_hieu,count(th.id_thuong_hieu) as sl" +
               " from san_pham sp join danh_gia dg  on sp.id_san_pham=dg.id_san_pham" +
               " join thuong_hieu th on th.id_thuong_hieu=sp.id_thuong_hieu" +
               " where dg.so_sao_danh_gia = 5" +
               " group by th.id_thuong_hieu" +
               " ) as tmp join thuong_hieu th on tmp.id_thuong_hieu=th.id_thuong_hieu" +
               " where th.anh_thuong_hieu <> ''"+
               " order by tmp.sl desc";
            thtn = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_thuong_hieu_tot_nhat, ketnoi)).Fill(thtn);
            ketnoi.Close();
            return DataTableToJSONWithJSONNet(thtn);
        }
        [WebMethod]
        public string Lay_du_lieu_giam_gia()
        {
            connect connect = new connect();
            DataTable DT_ctgg = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh;
            DataRow[] foundRows;
            //setting xong
            string sql_chuong_trinh_giam_gia = "select id_giam_gia, ma_giam_gia, ten_giam_gia, ly_do_giam_gia, cast(FORMAT(thoi_diem_bat_dau_giam_gia, 'dd-MM-yyyy HH:mm:ss') as varchar), cast(FORMAT(thoi_diem_ket_thuc_giam_gia, 'dd-MM-yyyy HH:mm:ss') as varchar) from giam_gia where DATEDIFF(MINUTE, GETDATE(), thoi_diem_ket_thuc_giam_gia) > 0 and DATEDIFF(HOUR, GETDATE(),thoi_diem_bat_dau_giam_gia) <= 24";
            DT_ctgg = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_chuong_trinh_giam_gia, ketnoi)).Fill(DT_ctgg);
            DataRow[] dong_ctgg = DT_ctgg.Select();
            List<chuong_trinh_giam_gia> ct_giam_gia = new List<chuong_trinh_giam_gia>();
            for (int i = 0; i < DT_ctgg.Rows.Count; i++)
            {
                //tao ct
                string sql_ds_ad = "select sp.id_san_pham , sp.ten_san_pham, sp.anh_san_pham, CAST( sp.gia_ban_ra AS int), ds_sp_gg.phan_tram_giam, CAST( ds_sp_gg.giam_toi_da AS int) from danh_sac_san_pham_giam_gia  ds_sp_gg join san_pham sp on ds_sp_gg.id_san_pham=sp.id_san_pham where id_giam_gia=" + dong_ctgg[i][0];
                DataTable ds_ad = new DataTable();
                new SqlDataAdapter(new SqlCommand(sql_ds_ad, ketnoi)).Fill(ds_ad);
                DataRow[] dong_sp_ad = ds_ad.Select();
                List<sp_ap_dung> ds_sp_ap_dung = new List<sp_ap_dung>();
                for (int j = 0; j < ds_ad.Rows.Count; j++)
                {
                    ds_sp_ap_dung.Add(new sp_ap_dung(
                            int.Parse(dong_sp_ad[j][0].ToString()),
                            dong_sp_ad[j][1].ToString(),
                            dong_sp_ad[j][2].ToString(),
                            int.Parse(dong_sp_ad[j][3].ToString()),
                            float.Parse(dong_sp_ad[j][4].ToString()),
                            long.Parse(dong_sp_ad[j][5].ToString())
                    ));
                }

                ct_giam_gia.Add(new chuong_trinh_giam_gia(
                     int.Parse(dong_ctgg[i][0].ToString()),
                     dong_ctgg[i][1].ToString(),
                     dong_ctgg[i][2].ToString(),
                     dong_ctgg[i][3].ToString(),
                     dong_ctgg[i][4].ToString(),
                     dong_ctgg[i][5].ToString(),
                     ds_sp_ap_dung
                    ));
            }

            //if (!(ds != null && ds.Rows.Count != 0))  
            ketnoi.Close();
            return ct_giam_gia.ParserJSon();
        }
        [WebMethod]
        public string Lay_du_lieu_nhanh_tay_keo_het()
        {
            connect connect = new connect();
            DataTable DT_ctgg = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh;
            DataRow[] foundRows;
            //setting xong
            string sql_chuong_trinh_giam_gia = "select top 1 id_giam_gia, ma_giam_gia, ten_giam_gia, ly_do_giam_gia, cast(FORMAT(thoi_diem_bat_dau_giam_gia, 'dd-MM-yyyy HH:mm:ss') as varchar), cast(FORMAT(thoi_diem_ket_thuc_giam_gia, 'dd-MM-yyyy HH:mm:ss') as varchar) from giam_gia  where DATEDIFF(MINUTE, GETDATE(), thoi_diem_ket_thuc_giam_gia) > 0 and DATEDIFF(HOUR, GETDATE(),thoi_diem_bat_dau_giam_gia) <= 24 order by  DATEDIFF(MINUTE, GETDATE(), thoi_diem_ket_thuc_giam_gia) asc";
            DT_ctgg = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_chuong_trinh_giam_gia, ketnoi)).Fill(DT_ctgg);
            DataRow[] dong_ctgg = DT_ctgg.Select();
            List<chuong_trinh_giam_gia> ct_giam_gia = new List<chuong_trinh_giam_gia>();
            for (int i = 0; i < DT_ctgg.Rows.Count; i++)
            {
                //tao ct
                string sql_ds_ad = "select top 5 sp.id_san_pham , sp.ten_san_pham, sp.anh_san_pham, CAST( sp.gia_ban_ra AS int), ds_sp_gg.phan_tram_giam, CAST( ds_sp_gg.giam_toi_da AS int) from danh_sac_san_pham_giam_gia  ds_sp_gg join san_pham sp on ds_sp_gg.id_san_pham=sp.id_san_pham where id_giam_gia=" + dong_ctgg[i][0];
                DataTable ds_ad = new DataTable();
                new SqlDataAdapter(new SqlCommand(sql_ds_ad, ketnoi)).Fill(ds_ad);
                DataRow[] dong_sp_ad = ds_ad.Select();
                List<sp_ap_dung> ds_sp_ap_dung = new List<sp_ap_dung>();
                for (int j = 0; j < ds_ad.Rows.Count; j++)
                {
                    ds_sp_ap_dung.Add(new sp_ap_dung(
                            int.Parse(dong_sp_ad[j][0].ToString()),
                            dong_sp_ad[j][1].ToString(),
                            dong_sp_ad[j][2].ToString(),
                            int.Parse(dong_sp_ad[j][3].ToString()),
                            float.Parse(dong_sp_ad[j][4].ToString()),
                            long.Parse(dong_sp_ad[j][5].ToString())
                    ));
                }

                ct_giam_gia.Add(new chuong_trinh_giam_gia(
                     int.Parse(dong_ctgg[i][0].ToString()),
                     dong_ctgg[i][1].ToString(),
                     dong_ctgg[i][2].ToString(),
                     dong_ctgg[i][3].ToString(),
                     dong_ctgg[i][4].ToString(),
                     dong_ctgg[i][5].ToString(),
                     ds_sp_ap_dung
                    ));
            }

            //if (!(ds != null && ds.Rows.Count != 0))  
            ketnoi.Close();
            return ct_giam_gia.ParserJSon();
        }
        [WebMethod]
        public string them_xoa_tim(string json)
        {
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh;
            DataRow[] foundRows;
            yeu_cau_san_pham yc_sp = JsonConvert.DeserializeObject<yeu_cau_san_pham>(json);
            //danh_gia_them dg_them = new danh_gia_them(150509568, 12001778, true, "rất tốt", 5, "mới nhìn đã thích", "11-02-2018 05:02:35");
            bool tha_tim;
            string sql_tim = "select * from danh_sach_mong_muon where id_san_pham=" + yc_sp.getid_san_pham() + " and id_nguoi=" + yc_sp.getid_nguoi();
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_tim, ketnoi)).Fill(ds);
            if (ds != null && ds.Rows.Count != 0)
                tha_tim = true;
            else tha_tim = false;

            if (tha_tim == false)
            {
                them_tim(yc_sp.getid_san_pham(), yc_sp.getid_nguoi());
                ds = new DataTable();
                new SqlDataAdapter(new SqlCommand(sql_tim, ketnoi)).Fill(ds);
                //
               
                if (ds != null && ds.Rows.Count != 0)
                {
                    string sql_so_tim = "select count(id_nguoi) from danh_sach_mong_muon where id_san_pham=" + yc_sp.getid_san_pham();
                    ds = new DataTable();
                    new SqlDataAdapter(new SqlCommand(sql_so_tim, ketnoi)).Fill(ds);
                    int so_tim;
                    if (!(ds != null && ds.Rows.Count != 0)) so_tim = 0;
                    else
                    {
                        foundRows = null;
                        foundRows = ds.Select();
                        so_tim = int.Parse(foundRows[0][0].ToString());
                    }

                    tra_ve_tim tv_tim = new tra_ve_tim();
                    tv_tim.tha_tim = true;
                    tv_tim.sl = so_tim;
                    return new thong_bao(true, "Thêm vào danh sách mong muốn thành công!", tv_tim).ParserJSon();
                }
                else
                {
                    string sql_so_tim = "select count(id_nguoi) from danh_sach_mong_muon where id_san_pham=" + yc_sp.getid_san_pham();
                    ds = new DataTable();
                    new SqlDataAdapter(new SqlCommand(sql_so_tim, ketnoi)).Fill(ds);
                    int so_tim;
                    if (!(ds != null && ds.Rows.Count != 0)) so_tim = 0;
                    else
                    {
                        foundRows = null;
                        foundRows = ds.Select();
                        so_tim = int.Parse(foundRows[0][0].ToString());
                    }

                    tra_ve_tim tv_tim = new tra_ve_tim();
                    tv_tim.tha_tim = false;
                    tv_tim.sl = so_tim;
                    return new thong_bao(false, "Thêm vào danh sách mong muốn thất bại!", tv_tim).ParserJSon();
                }
            }
            else if (tha_tim == true)
            {
                xoa_tim(yc_sp.getid_san_pham(), yc_sp.getid_nguoi());
                ds = new DataTable();
                new SqlDataAdapter(new SqlCommand(sql_tim, ketnoi)).Fill(ds);
                if (ds != null && ds.Rows.Count != 0)
                {
                    string sql_so_tim = "select count(id_nguoi) from danh_sach_mong_muon where id_san_pham=" + yc_sp.getid_san_pham();
                    ds = new DataTable();
                    new SqlDataAdapter(new SqlCommand(sql_so_tim, ketnoi)).Fill(ds);
                    int so_tim;
                    if (!(ds != null && ds.Rows.Count != 0)) so_tim = 0;
                    else
                    {
                        foundRows = null;
                        foundRows = ds.Select();
                        so_tim = int.Parse(foundRows[0][0].ToString());
                    }

                    tra_ve_tim tv_tim = new tra_ve_tim();
                    tv_tim.tha_tim = true;
                    tv_tim.sl = so_tim;
                    return new thong_bao(false, "Xóa trong danh sách mong muốn thất bại!", tv_tim).ParserJSon();
                }
                else
                {
                    string sql_so_tim = "select count(id_nguoi) from danh_sach_mong_muon where id_san_pham=" + yc_sp.getid_san_pham();
                    ds = new DataTable();
                    new SqlDataAdapter(new SqlCommand(sql_so_tim, ketnoi)).Fill(ds);
                    int so_tim;
                    if (!(ds != null && ds.Rows.Count != 0)) so_tim = 0;
                    else
                    {
                        foundRows = null;
                        foundRows = ds.Select();
                        so_tim = int.Parse(foundRows[0][0].ToString());
                    }

                    tra_ve_tim tv_tim = new tra_ve_tim();
                    tv_tim.tha_tim = false;
                    tv_tim.sl = so_tim;
                    return new thong_bao(true, "Xóa trong danh sách mong muốn thành công!", tv_tim).ParserJSon();
                }
            }
            return new thong_bao(false, "Lỗi kĩ thuật!", false).ParserJSon();
        }
        [WebMethod]
        public string them_danh_gia(string json)
        {
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh;
            DataRow[] foundRows;

            danh_gia_them dg_them = JsonConvert.DeserializeObject<danh_gia_them>(json);
            //danh_gia_them dg_them = new danh_gia_them(150509568, 12001778, true, "rất tốt", 5, "mới nhìn đã thích", "11-02-2018 05:02:35");
            int id_san_pham = dg_them.getid_san_pham();
            int id_nguoi = dg_them.getid_nguoi();
            bool da_mua;//
            string sql_da_mua = "select ctdh.* from don_hang dh join chi_tiet_don_hang ctdh on dh.id_don_hang=ctdh.id_don_hang where dh.id_ttdh=3 and ctdh.id_san_pham=" + dg_them.getid_san_pham() + " and dh.id_nguoi=" + dg_them.getid_nguoi();
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_da_mua, ketnoi)).Fill(ds);
            foundRows = null;
            foundRows = ds.Select();
            if (ds != null && ds.Rows.Count != 0)
                da_mua = true;
            else da_mua = false;
            string tieu_de_danh_gia = dg_them.gettieu_de_danh_gia();
            int so_sao_danh_gia = dg_them.getso_sao_danh_gia();
            string noi_dung = dg_them.getnoi_dung();
            string ngay_viet_danh_gia;
            var info = TimeZoneInfo.FindSystemTimeZoneById("SE Asia Standard Time");
            DateTimeOffset localServerTime = DateTimeOffset.Now;
            DateTimeOffset localTime = TimeZoneInfo.ConvertTime(localServerTime, info);
            ngay_viet_danh_gia = localTime.ToString("yyyy-MM-dd HH:mm:ss");
            ham_dg_them(id_san_pham, id_nguoi, da_mua, tieu_de_danh_gia, so_sao_danh_gia, noi_dung, ngay_viet_danh_gia);

            string sql_kiem_tra_them_danh_gia = "select * from danh_gia where id_nguoi=" + id_nguoi + " and id_san_pham=" + id_san_pham + " and cast(FORMAT(ngay_viet_danh_gia, 'yyyy-MM-dd HH:mm:ss') as varchar) = '" + ngay_viet_danh_gia + "'";
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_kiem_tra_them_danh_gia, ketnoi)).Fill(ds);
            foundRows = null;
            foundRows = ds.Select();
            if (ds != null && ds.Rows.Count != 0)
            {
                //danh_gia
                string sql_dg = "select dg.id_danh_gia, dg.da_mua, dg.tieu_de_danh_gia, dg.so_sao_danh_gia, dg.noi_dung, cast(FORMAT(dg.ngay_viet_danh_gia, 'dd-MM-yyyy HH:mm:ss') as varchar), n.ten_nguoi from danh_gia dg join nguoi n on dg.id_nguoi=n.id_nguoi where dg.id_san_pham=" + id_san_pham + " order by dg.ngay_viet_danh_gia desc";
                ds = new DataTable();
                new SqlDataAdapter(new SqlCommand(sql_dg, ketnoi)).Fill(ds);
                foundRows = null;
                foundRows = ds.Select();
                List<thong_tin_danh_gia> danh_sach_danh_gia = new List<thong_tin_danh_gia>();
                if (ds != null && ds.Rows.Count != 0)
                    for (int i = 0; i < ds.Rows.Count; i++)
                    {
                        danh_sach_danh_gia.Add(new thong_tin_danh_gia(
                             int.Parse(foundRows[i][0].ToString()),
                             bool.Parse(foundRows[i][1].ToString()),
                             foundRows[i][2].ToString(),
                             int.Parse(foundRows[i][3].ToString()),
                             foundRows[i][4].ToString(),
                             foundRows[i][5].ToString(),
                             foundRows[i][6].ToString()
                            ));
                    }
                return new thong_bao(true, "Đánh giá thành công!", danh_sach_danh_gia).ParserJSon();
            }
            else return new thong_bao(false, "Đánh giá thất bại!", new System.Dynamic.ExpandoObject()).ParserJSon();
        }
        //trang  sản phẩm
         [WebMethod]
        public string lay_du_lieu_san_pham(string json)
        {
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh;
            DataRow[] foundRows;
            // = new SqlCommand(sql, ketnoi);
            //ketnoi.Open();
            //SqlDataAdapter data = new SqlDataAdapter(lenh);
            //data.Fill(ds);
            //DataRow[] foundRows;
            //foundRows = ds.Select();

            yeu_cau_san_pham yc_sp = JsonConvert.DeserializeObject<yeu_cau_san_pham>(json);
            //Lấy id user
            int id_user = yc_sp.getid_nguoi();
            //Lấy thả tim
            bool tha_tim;
            string sql_tim = "select * from danh_sach_mong_muon where id_san_pham=" + yc_sp.getid_san_pham() + " and id_nguoi=" + yc_sp.getid_nguoi();
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_tim, ketnoi)).Fill(ds);
            if (ds != null && ds.Rows.Count != 0)
                tha_tim = true;
            else tha_tim = false;
            //Lấy id sản phảm
            int id_san_pham = yc_sp.getid_san_pham();
            //Truy vấn dùng chung
            //Lấy tên sản phảm
            string sql_san_pham = "select * from san_pham where id_san_pham=" + yc_sp.getid_san_pham();
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_san_pham, ketnoi)).Fill(ds);
            if (!(ds != null && ds.Rows.Count != 0)) return "loi_ten_sp";
            foundRows = null;
            foundRows = ds.Select();
            string ten_san_pham = foundRows[0][2].ToString();
            int id_thuong_hieu = int.Parse(foundRows[0][1].ToString());
            //th
            string sql_thuong_hieu = "select * from thuong_hieu where id_thuong_hieu=" + id_thuong_hieu;
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_thuong_hieu, ketnoi)).Fill(ds);
            string tmp_th = DataTableToJSONWithJSONNet(ds).Replace("[", "").Replace("]", "");
            if (!(ds != null && ds.Rows.Count != 0)) return "loi_thuong_hieu";
            thuong_hieu thuong_hieu = JsonConvert.DeserializeObject<thuong_hieu>(tmp_th);
            //lsp
            string sql_lsp = "select lsp.id_loai_san_pham, lsp.ten_loai_san_pham from danh_sach_loai_san_pham_cua_san_pham ds join loai_san_pham lsp on lsp.id_loai_san_pham=ds.id_loai_san_pham where ds.id_san_pham=" + id_san_pham;
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_lsp, ketnoi)).Fill(ds);
            if (!(ds != null && ds.Rows.Count != 0)) return "loi_lsp";
            foundRows = null;
            foundRows = ds.Select();
            List<loai_sp> danh_sach_lsp = new List<loai_sp>();
            for (int i = 0; i < ds.Rows.Count; i++)
            {
                danh_sach_lsp.Add(new loai_sp(
                    int.Parse(foundRows[i][0].ToString()),
                    foundRows[i][1].ToString()
                    ));
            }
            //danh sach anh
            string sql_anh = "select top 10 duong_link_anh from danh_sach_anh_cua_san_pham where id_san_pham=" + id_san_pham;
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_anh, ketnoi)).Fill(ds);
            if (!(ds != null && ds.Rows.Count != 0)) return "loi_ds_anh";
            foundRows = null;
            foundRows = ds.Select();
            List<string> danh_sach_anh = new List<string>();
            for (int i = 0; i < ds.Rows.Count; i++)
            {
                danh_sach_anh.Add(foundRows[i][0].ToString());
            }
            //sp
            string sql_sp = "select sp.gia_cao, sp.gia_ban_ra, sp.mo_ta_san_pham_html, sp.dac_tinh_san_pham, sp.khoi_luong_san_pham_gram, sp.da_co_vat from san_pham sp where sp.id_san_pham=" + id_san_pham;
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_sp, ketnoi)).Fill(ds);
            if (!(ds != null && ds.Rows.Count != 0)) return "loi_dssp";
            foundRows = null;
            foundRows = ds.Select();
            long gia_cao = (long)decimal.Parse(foundRows[0][0].ToString());
            long gia_ban_ra = (long)decimal.Parse(foundRows[0][1].ToString());
            string mo_ta_san_pham_html = foundRows[0][2].ToString();
            string dac_tinh_san_pham = foundRows[0][3].ToString();
            float khoi_luong_san_pham_gram = float.Parse(foundRows[0][4].ToString());
            bool da_co_vat = bool.Parse(foundRows[0][5].ToString());
            string sql_so_tim = "select count(id_nguoi) from danh_sach_mong_muon where id_san_pham=" + yc_sp.getid_san_pham();
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_so_tim, ketnoi)).Fill(ds);
            int so_tim;
            if (!(ds != null && ds.Rows.Count != 0)) so_tim=0;
             else
            {
                foundRows = null;
                foundRows = ds.Select();
                so_tim = int.Parse(foundRows[0][0].ToString());
            }
            //sl
            string sql_sl = "select kh.id_kho_hang, kh.ten_kho_hang, kh.dia_chi_kho_hang_chi_tiet as dia_chi_kho_hang, ctkh.so_luong, ctkh.id_san_pham from  chi_tiet_kho_hang ctkh join kho_hang kh on ctkh.id_kho_hang=kh.id_kho_hang where id_san_pham=" + id_san_pham;
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_sl, ketnoi)).Fill(ds);
            if (!(ds != null && ds.Rows.Count != 0)) return "loi_thong_tin_sl";
            foundRows = null;
            foundRows = ds.Select();
            List<thong_tin_so_luong> thong_tin_so_luong = new List<thong_tin_so_luong>();
            for (int i = 0; i < ds.Rows.Count; i++)
            {
                thong_tin_so_luong.Add(new thong_tin_so_luong(
                    int.Parse(
                    foundRows[i][0].ToString()),
                    foundRows[i][1].ToString(),
                    foundRows[i][2].ToString(),
                    int.Parse(foundRows[i][3].ToString()),
                    int.Parse(foundRows[i][4].ToString())
                    ));
            }
            //danh_gia
            string sql_dg = "select dg.id_danh_gia, dg.da_mua, dg.tieu_de_danh_gia, dg.so_sao_danh_gia, dg.noi_dung, cast(FORMAT(dg.ngay_viet_danh_gia, 'dd-MM-yyyy HH:mm:ss') as varchar), n.ten_nguoi from danh_gia dg join nguoi n on dg.id_nguoi=n.id_nguoi where dg.id_san_pham=" + id_san_pham + " order by dg.ngay_viet_danh_gia desc";
            ds = new DataTable();
            new SqlDataAdapter(new SqlCommand(sql_dg, ketnoi)).Fill(ds);
            foundRows = null;
            foundRows = ds.Select();
            List<thong_tin_danh_gia> danh_sach_danh_gia = new List<thong_tin_danh_gia>();
            if (ds != null && ds.Rows.Count!=0)
            for (int i = 0; i < ds.Rows.Count; i++)
            {
                danh_sach_danh_gia.Add(new thong_tin_danh_gia(
                     int.Parse(foundRows[i][0].ToString()),
                     bool.Parse(foundRows[i][1].ToString()),
                     foundRows[i][2].ToString(),
                     int.Parse(foundRows[i][3].ToString()),
                     foundRows[i][4].ToString(),
                     foundRows[i][5].ToString(),
                     foundRows[i][6].ToString()
                    ));
            }
            //lay kq ds_sp_them
            san_pham kq = new san_pham(id_user, tha_tim, id_san_pham, ten_san_pham, thuong_hieu, danh_sach_lsp, danh_sach_anh, gia_cao,
                gia_ban_ra, mo_ta_san_pham_html, dac_tinh_san_pham, khoi_luong_san_pham_gram, da_co_vat, so_tim,
                thong_tin_so_luong, danh_sach_danh_gia
                );
            return kq.ParserJSon();
        }
        //trang đăng nhập
         [WebMethod]
        public string gia_lap_du_lieu_dang_nhap()
        {
             return new dang_nhap("nhuyk56@gmhahiul.com", "MTIzNDU2Nzg5").ParserJSon();
        }
         [WebMethod]
         public string dang_nhap_nguoi_dung(string json)
         {
             string sql = "select * from nguoi where email_nguoi='{email}' and mat_khau_nguoi='{matkhau}' and la_nhan_vien=0";
             dang_nhap dang_nhap = JsonConvert.DeserializeObject<dang_nhap>(json);
             if (dang_nhap.email_nguoi.Contains("'") || dang_nhap.mat_khau_nguoi.Contains("'")
                  || dang_nhap.email_nguoi.Contains("/") || dang_nhap.mat_khau_nguoi.Contains("/"))
                 return new thong_bao(false, "Nghi vấn hack!", new System.Dynamic.ExpandoObject()).ParserJSon();
             sql = sql.Replace("{email}",dang_nhap.email_nguoi);
             sql = sql.Replace("{matkhau}",dang_nhap.mat_khau_nguoi);
             connect connect = new connect();
             DataTable ds = new DataTable();
             SqlConnection ketnoi = new SqlConnection(connect.getconnect());
             SqlCommand lenh = new SqlCommand(sql, ketnoi);
             ketnoi.Open();
             SqlDataAdapter data = new SqlDataAdapter(lenh);
             data.Fill(ds);
             DataRow[] foundRows;
             foundRows = ds.Select();
             if (ds != null && ds.Rows.Count != 0)
                 return new thong_bao(true, "đăng nhập thành công", foundRows[0][0].ToString()).ParserJSon();
             return new thong_bao(false, "đăng nhập thất bại, sai email hoặc mật khẩu", new System.Dynamic.ExpandoObject()).ParserJSon();
         }
        public List<sap_xep> khoi_tao_danh_sach_sap_xep()
        {
            List<sap_xep> danh_sach_sap_xep = new List<sap_xep>();
            danh_sach_sap_xep.Add(new sap_xep("sx_gs", "Giá sản phẩm",false));
            danh_sach_sap_xep.Add(new sap_xep("sx_dg", "Số sao đánh giá", true));
            danh_sach_sap_xep.Add(new sap_xep("sx_ut", "Độ ưa thích", false));
            //false là giảm
            //true là tăng
            return danh_sach_sap_xep;
        }
        [WebMethod]
        public string demo(string json)
        {
            nguoi nguoi_dang_ki = JsonConvert.DeserializeObject<nguoi>(json);
            thong_bao thong_bao = new thong_bao(false, "test", nguoi_dang_ki);
            return thong_bao.ParserJSon();
        }
        [WebMethod]
        public string gia_lap_du_lieu_dang_ki()
        {
            string sql = "select id_nguoi,email_nguoi,mat_khau_nguoi,ten_nguoi,ngay_sinh,dia_chi_nguoi,sdt_nguoi,gioi_tinh_nguoi from nguoi";
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand(sql, ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            data.Fill(ds);
            DataRow[] foundRows;
            foundRows = ds.Select();
            int max_nguoi = ds.Rows.Count;
            nguoi nguoi;
            //for(int i = 0; i < 1; i++)
                int i = 0;
            {
                //id_nguoi,email_nguoi,mat_khau_nguoi,ten_nguoi,ngay_sinh,dia_chi_nguoi,sdt_nguoi,gioi_tinh_nguoi
                nguoi = new nguoi(
                      foundRows[i][1].ToString(),
                      decode(foundRows[i][2].ToString()),
                      foundRows[i][3].ToString(),
                       foundRows[i][4].ToString(),
                      foundRows[i][5].ToString(),
                       foundRows[i][6].ToString(),
                      Boolean.Parse(foundRows[i][7].ToString())
                ); 
            }
            return nguoi.ParserJSon();
        }
        private bool kiem_tra_email_ton_tai(string email_nguoi)
        {
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand("select * from nguoi where email_nguoi='" + email_nguoi+"'", ketnoi);
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
            return id_nguoi+1;
        }
        public void nguoi_them(int id_nguoi, int id_tinh_trang_nguoi, string email_nguoi,
		        string mat_khau_nguoi, string ten_nguoi, string ngay_sinh,string  dia_chi_nguoi,
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
             cmd.Parameters.Add("@la_nhan_vien", SqlDbType.NVarChar).Value = 0;
            connDB.Open();
            cmd.ExecuteNonQuery();
            connDB.Close();
        }

        [WebMethod]
        public string dang_ki_nguoi_dung(string json)
        {
            //gia lap chuoi nhan ve
            /*
             {"id_nguoi":12001459,"email_nguoi":"nongthihanh03091999@gmail.com","mat_khau_nguoi":"nongthihanh03091999@gmail.com","ten_nguoi":"NÔNG THỊ HẠNH","ngay_sinh":"3/9/1999 00:00:00","dia_chi_nguoi":"Huyện Bình Gia, Lạng Sơn","sdt_nguoi":"01618415994","gioi_tinh_nguoi":false}
             * */
            nguoi nguoi_dang_ki;
            try
            {
                nguoi_dang_ki = JsonConvert.DeserializeObject<nguoi>(json);
            }
            catch(Exception e)
            {
                return new thong_bao(false, "dữ liệu không hợp lệ, vui lòng thử lại!", new System.Dynamic.ExpandoObject()).ParserJSon(); ;
            }
            /*
             1. kiem tra co chua
             2. them vao
             * 3. kiem tra co chua
             * 4 thong bao qua ban bao cao
             * */
            if (kiem_tra_email_ton_tai(nguoi_dang_ki.getemail_nguoi()) == false)
            {
                int id_tinh_trang_nguoi = 11111;
                bool la_nhan_vien = false;
                var info = TimeZoneInfo.FindSystemTimeZoneById("SE Asia Standard Time");
                DateTimeOffset localServerTime = DateTimeOffset.Now;
                DateTimeOffset localTime = TimeZoneInfo.ConvertTime(localServerTime, info);
                //time hien tai localTime.DateTime.ToString()
                //return localTime.DateTime.ToString();
                //1.id
                int id_nguoi = khoi_tao_id_nguoi();
                nguoi_them(id_nguoi, 11111, nguoi_dang_ki.getemail_nguoi(), nguoi_dang_ki.getmat_khau_nguoi(),
                nguoi_dang_ki.getten_nguoi(), nguoi_dang_ki.getngay_sinh(), nguoi_dang_ki.getdia_chi_nguoi(),
                nguoi_dang_ki.getsdt_nguoi(), nguoi_dang_ki.getgioi_tinh_nguoi(),
                localTime.DateTime.ToString(), false);
                if (kiem_tra_email_ton_tai(nguoi_dang_ki.getemail_nguoi()) == true)
                    return new thong_bao(true,"Xin chúc mừng! Đăng kí thành công! Vui lòng đăng nhập lại!", new System.Dynamic.ExpandoObject()).ParserJSon();
                else return new thong_bao(false, "đăng kí không thành công, thử lại sau", new System.Dynamic.ExpandoObject()).ParserJSon(); ;
            }
            else return new thong_bao(false, "đăng kí không thành công, email đã tồn tại!", new System.Dynamic.ExpandoObject()).ParserJSon(); ;
        }
        public string xu_ly_chuoi_timkiem(string s)
        {
            if (String.IsNullOrEmpty(s))
                return s;

            string result = "";

            //lấy danh sách các từ  

            string[] words = s.Split(' ');
            foreach (string word in words)
            {
                // từ nào là các khoảng trắng thừa thì bỏ  
                if (word.Trim() != "")
                {
                    if (word.Length > 1)
                    {
                        byte[] bytes = Encoding.UTF8.GetBytes( word.Substring(0, 1).ToUpper() + word.Substring(1).ToLower());
                        result += Convert.ToBase64String(bytes) + " ";

                        bytes = Encoding.UTF8.GetBytes( word.Substring(0).ToLower());
                        result += Convert.ToBase64String(bytes) + " ";
                    }
                    else
                    {
                        byte[] bytes = Encoding.UTF8.GetBytes(word.ToUpper());
                        result += Convert.ToBase64String(bytes) + " ";

                        bytes = Encoding.UTF8.GetBytes(word.ToLower());
                        result += Convert.ToBase64String(bytes) + " ";
                    }
                }

            }
            result = result.Replace("=", "");
            return result.Trim();
        }   
        [WebMethod]
        public string Lay_Du_Lieu_Loai_San_Pham()
        {
            
            connect connect = new connect();
            SqlDataAdapter adap = new SqlDataAdapter("proc_danh_sach_loai_san_pham_cap_0 ", connect.getconnect());
            DataTable ds = new DataTable();
            adap.Fill(ds);
            DataRow[] foundRows;
            foundRows = ds.Select();
            if (ds.Rows.Count == 0) return null;
            //return ds.Rows.Count + "";

            //Lấy dữ liệu cấp 0
            List<c0_item> danhsach_c0_item = new List<c0_item>();
            c0_item c0_item_0 = new c0_item(
                       0,//id
                       "Q2jhu4kgZMOgbmggY2hvIGLhuqFu",//name
                       "",//banner
                       "\\uE61A",//ico
                       0,
                       0, 
                       null,
                       null);
            danhsach_c0_item.Add(c0_item_0);

            //Lấy dữ liệu từ 1-11
            for(int j = 0; j < ds.Rows.Count; j++)
                {
                    c0_item c0_item_11 = new c0_item(
                        Int32.Parse(foundRows[j][0].ToString()) ,
                        foundRows[j][1].ToString(),
                        foundRows[j][2].ToString(),
                        foundRows[j][3].ToString(),
                        Int32.Parse(foundRows[j][4].ToString()),
                        Int32.Parse(foundRows[j][5].ToString()),
                        null,
                        null);
                    danhsach_c0_item.Add(c0_item_11);
                }

            //Xử Lý chỉ dành cho bạn
            adap = null;
            adap = new SqlDataAdapter("proc_danh_sach_loai_san_pham_chi_danh_cho_ban ", connect.getconnect());
            ds = new DataTable();
            adap.Fill(ds);
            foundRows = null;
            foundRows = ds.Select();
            if (ds.Rows.Count == 0) return null;
            //return ds.Rows.Count + "";
            //khai báo list chi danh cho ban
            List<c2_group_item> list_chi_danh_cho_ban = new List<c2_group_item>();
            for (int j = 0; j < ds.Rows.Count; j++)
            {
                if (foundRows[j][2].ToString().Length > 5)
                {
                    c2_group_item c2_group_item = new c2_group_item(
                    Int32.Parse(foundRows[j][0].ToString()),
                    foundRows[j][1].ToString(),
                    foundRows[j][2].ToString(),
                    Int32.Parse(foundRows[j][4].ToString()),
                    Int32.Parse(foundRows[j][5].ToString()));
                    list_chi_danh_cho_ban.Add(c2_group_item);
                }
            }
            danhsach_c0_item[0].list_chi_danh_cho_ban = list_chi_danh_cho_ban;

            //Lấy dữ liệu c1
            for (int i = 1; i < danhsach_c0_item.Count; i++)
            {
                adap = null;
                adap = new SqlDataAdapter("proc_danh_sach_loai_san_pham_theo_id " + danhsach_c0_item[i].getid_loai_san_pham(), connect.getconnect());
                ds = new DataTable();
                adap.Fill(ds);
                foundRows = null;
                foundRows = ds.Select();
                if (ds.Rows.Count != 0)
                {
                    List<c1_item> danh_sach_c1_item = new List<c1_item>();
                    for (int j = -1; j < ds.Rows.Count; j++)
                    {
                         c1_item c1_item;
                         if (j == -1)
                         {
                             //header_danh_cho_cho_banner
                             c1_item = new c1_item(
                                -1,
                                null,
                                -1,
                                -1,
                                null
                            );
                         }
                        else
                         {
                             c1_item = new c1_item(
                                  Int32.Parse(foundRows[j][0].ToString()),
                                 foundRows[j][1].ToString(),
                                 Int32.Parse(foundRows[j][4].ToString()),
                                 Int32.Parse(foundRows[j][5].ToString()),
                                 null
                              );
                         }
                        danh_sach_c1_item.Add(c1_item);
                    }
                    danhsach_c0_item[i].list_c1_item = danh_sach_c1_item;
                }
            }

            //Lấy dữ liệu thuong hiệu
            for (int i = 1; i < danhsach_c0_item.Count; i++)
            {
                adap = null;
                adap = new SqlDataAdapter("proc_danh_sach_thuong_hieu_theo_loai_san_pham_id " + danhsach_c0_item[i].getid_loai_san_pham(), connect.getconnect());
                ds = new DataTable();
                adap.Fill(ds);
                foundRows = null;
                foundRows = ds.Select();
                if (ds.Rows.Count != 0)
                {
                    List<thuong_hieu> danh_sach_thuong_hieu = new List<thuong_hieu>();
                    for (int j = 0; j < ds.Rows.Count; j++)
                    {
                        thuong_hieu thuong_hieu = new thuong_hieu(
                             Int32.Parse(foundRows[j][0].ToString()),
                            foundRows[j][1].ToString(),
                            foundRows[j][2].ToString()
                       );
                        danh_sach_thuong_hieu.Add(thuong_hieu);
                    }
                    danh_sach_thuong_hieu = Shuffle(danh_sach_thuong_hieu, danh_sach_thuong_hieu.Count);
                    if (danh_sach_thuong_hieu.Count > 6)
                    {
                        List<thuong_hieu> tmp = new List<thuong_hieu>();
                        for (int j = 0; j < 6; j++)
                        {
                             tmp.Add(danh_sach_thuong_hieu[j]);
                        }
                        danh_sach_thuong_hieu = null;
                        danh_sach_thuong_hieu = tmp;
                    }
                    danhsach_c0_item[i].list_thuong_hieu = danh_sach_thuong_hieu;
                }
            }

            //Lấy dữ liệu c2 group & c2 group item
            for (int i = 1; i < danhsach_c0_item.Count; i++)
            {
                for(int j = 0; j < danhsach_c0_item[i].list_c1_item.Count; j++)
                {
                    adap = null;
                    adap = new SqlDataAdapter("proc_danh_sach_loai_san_pham_theo_id " + danhsach_c0_item[i].list_c1_item[j].getid_loai_san_pham(), connect.getconnect());
                    ds = new DataTable();
                    adap.Fill(ds);
                    foundRows = null;
                    foundRows = ds.Select();
                    if (ds.Rows.Count != 0)
                    {
                        List<c2_group_item> danh_sach_c2_group_item = new List<c2_group_item>();
                        for (int z = 0; z < ds.Rows.Count; z++)
                        {
                            c2_group_item c2_group_item = new c2_group_item(
                                 Int32.Parse(foundRows[z][0].ToString()),
                                foundRows[z][1].ToString(),
                                foundRows[z][2].ToString(),
                                Int32.Parse(foundRows[z][4].ToString()),
                                Int32.Parse(foundRows[z][5].ToString())
                             );
                            danh_sach_c2_group_item.Add(c2_group_item);
                        }
                        danhsach_c0_item[i].list_c1_item[j].list_c2_group = convert_c2_group(danh_sach_c2_group_item);
                    }
                }
            }
                return danhsach_c0_item.ParserJSon(); 
        }
        [WebMethod]
        public string khoi_tao_trang_danh_sach(string json_yeu_cau_ds)
        {
            yeu_cau_ds yeu_cau_ds;
            yeu_cau_ds = JsonConvert.DeserializeObject<yeu_cau_ds>(json_yeu_cau_ds);
            //return ds_sp_yc.ParserJSon();
            //string sql = "select ten_san_pham from san_pham where ten_san_pham like '%Jw%' or ten_san_pham like '%xJFlbg%'";
            //return truy_van_sql(yeu_cau_ds);
           //// SqlDataAdapter adap = new SqlDataAdapter(sql, connect.getconnect());
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand(truy_van_sql(yeu_cau_ds), ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            data.Fill(ds);
           // DataRow[] foundRows;
           // foundRows = ds.Select();
            //int max_ds_sp_tra_ve 
            return DataTableToJSONWithJSONNet(ds);
            //return truy_van_sql(yeu_cau_ds);
            //return ds_sp_yc.ParserJSon();
        }
        [WebMethod]
        public string khoi_tao_yeu_cau_tra_ve_cho_trang_danh_sach(string json_ds_sp_yc)
        {
            int tmp_a, tmp_b;
            //demo dữ liệu đầu vào
            yeu_cau_ds yeu_cau_ds;
            yeu_cau_ds = JsonConvert.DeserializeObject<yeu_cau_ds>(json_ds_sp_yc);
            tmp_a = yeu_cau_ds.bo_loc.phan_trang.vi_tri_bat_dau;
            tmp_b = yeu_cau_ds.bo_loc.phan_trang.vi_tri_ket_thuc;
            yeu_cau_ds.bo_loc.phan_trang.vi_tri_bat_dau = 0;
            yeu_cau_ds.bo_loc.phan_trang.vi_tri_ket_thuc = 9000000;
            string bang_sp = truy_van_sql(yeu_cau_ds);
            string sql_lsp = "select lsp.id_loai_san_pham, lsp.ten_loai_san_pham from  ( select distinct lsp.id_loai_san_pham from  ({san_pham})/*(san_pham)*/ as sp join danh_sach_loai_san_pham_cua_san_pham dslsp_sp on sp.id_san_pham=dslsp_sp.id_san_pham join loai_san_pham lsp on lsp.id_loai_san_pham=dslsp_sp.id_loai_san_pham ) as idlsp join loai_san_pham lsp on idlsp.id_loai_san_pham=lsp.id_loai_san_pham ".Replace("{san_pham}", bang_sp);
            string sql_th = "select distinct th.id_thuong_hieu, th.ten_thuong_hieu, th.anh_thuong_hieu from ({san_pham}) sptmp join san_pham sp on sptmp.id_san_pham=sp.id_san_pham join thuong_hieu th on sp.id_thuong_hieu=th.id_thuong_hieu".Replace("{san_pham}", bang_sp);
            //return sql_th;
            //Lấy dữ liệu danh sách lsp
            connect connect = new connect();
            DataTable ds = new DataTable();
            SqlConnection ketnoi = new SqlConnection(connect.getconnect());
            SqlCommand lenh = new SqlCommand(sql_lsp, ketnoi);
            ketnoi.Open();
            SqlDataAdapter data = new SqlDataAdapter(lenh);
            data.Fill(ds);
            DataRow[] foundRows;
            foundRows = ds.Select();
            int max_ds_lsp = ds.Rows.Count;
            List<loai_sp> danh_sach_lsp = new List<loai_sp>();
            for(int i = 0; i< 10 && i < max_ds_lsp; i++)
                danh_sach_lsp.Add(new loai_sp(int.Parse(foundRows[i][0].ToString()), foundRows[i][1].ToString()));
            ketnoi.Close();
            //lấy dsth
            ds = new DataTable();
            lenh = new SqlCommand(sql_th, ketnoi);
            ketnoi.Open();
            data = new SqlDataAdapter(lenh);
            data.Fill(ds);
            foundRows = null;
            foundRows = ds.Select();
            int max_ds_th = ds.Rows.Count;
            List<thuong_hieu> danh_sach_thuong_hieu = new List<thuong_hieu>();
            for (int i = 0; i < 10 && i < max_ds_th; i++)
                danh_sach_thuong_hieu.Add(new thuong_hieu(int.Parse(foundRows[i][0].ToString()), foundRows[i][1].ToString(), foundRows[i][2].ToString()));
            yeu_cau_ds.bo_loc.phan_trang.vi_tri_bat_dau = tmp_a;
            yeu_cau_ds.bo_loc.phan_trang.vi_tri_ket_thuc = tmp_b;
            yeu_cau_ds ds_yc_tv = new yeu_cau_ds(
                //new bo_loc(danh_sach_thuong_hieu, danh_sach_lsp, yeu_cau_ds.bo_loc.gia_sp, yeu_cau_ds.bo_loc.danh_gia, yeu_cau_ds.bo_loc.tim_kiem, yeu_cau_ds.bo_loc.phan_trang),
                new bo_loc(danh_sach_lsp, danh_sach_thuong_hieu, yeu_cau_ds.bo_loc.gia_sp,yeu_cau_ds.bo_loc.danh_gia,yeu_cau_ds.bo_loc.tim_kiem,yeu_cau_ds.bo_loc.phan_trang)
                ,yeu_cau_ds.sap_xep
                );
            ketnoi.Close();
            return ds_yc_tv.ParserJSon();
        }
        public List<thuong_hieu> Shuffle(List<thuong_hieu> array, int max)
        {
            Random _random = new Random();
            var random = _random;
            for (int i = max; i > 1; i--)
            {
                // Pick random element to swap.
                int j = random.Next(i); // 0 <= j <= i-1
                // Swap.
                thuong_hieu tmp = array[j];
                array[j] = array[i - 1];
                array[i - 1] = tmp;
            }
            return array;
        }
        public string DataTableToJSONWithJSONNet(DataTable table)
        {
            string JSONString = string.Empty;
            JSONString = JsonConvert.SerializeObject(table);
            return JSONString;
        }
        public string truy_van_sql(yeu_cau_ds yeu_cau_ds)
        {
            //0. đọc dữ liệu
            string body = string.Empty;
            using (StreamReader reader = new StreamReader(Server.MapPath("~/truy_van_ds_sp_tra_ve.txt")))
            {
                body = reader.ReadToEnd();
            }
            //1.id_loai_san_pham
            if (!(yeu_cau_ds.bo_loc.ds_loai_sp == null ||yeu_cau_ds.bo_loc.ds_loai_sp.Count == 0))
            {
                //body = body.Replace("{lsp.id_loai_san_pham}", "and lsp.id_loai_san_pham=" + yeu_cau_ds.bo_loc.id_loai_san_pham);
                string sql_lsp = "";
                bool f = false;
                for(int i = 0; i < yeu_cau_ds.bo_loc.ds_loai_sp.Count; i++)
                {
                    if(f==false)
                    {
                        f = true;
                        sql_lsp += "lsp.id_loai_san_pham=" + yeu_cau_ds.bo_loc.ds_loai_sp[i].id_loai_san_pham;
                    }
                    else
                        sql_lsp += " or lsp.id_loai_san_pham=" + yeu_cau_ds.bo_loc.ds_loai_sp[i].id_loai_san_pham;
                }
                body = body.Replace("{lsp.id_loai_san_pham}", "and (" + sql_lsp + ")");
            }
            else body = body.Replace("{lsp.id_loai_san_pham}", "");
            //2.{id_thuong_hieu}
            if (!( yeu_cau_ds.bo_loc.ds_thuong_hieu == null||yeu_cau_ds.bo_loc.ds_thuong_hieu.Count == 0))
            {
                //body = body.Replace("{th.id_thuong_hieu}", "and th.id_thuong_hieu=" + yeu_cau_ds.bo_loc.id_thuong_hieu);
                string sql_th = "";
                bool f = false;
                for (int i = 0; i < yeu_cau_ds.bo_loc.ds_thuong_hieu.Count; i++)
                {
                    if (f == false)
                    {
                        f = true;
                        sql_th += "th.id_thuong_hieu=" + yeu_cau_ds.bo_loc.ds_thuong_hieu[i].id_thuong_hieu;
                    }
                    else
                        sql_th += " or th.id_thuong_hieu=" + yeu_cau_ds.bo_loc.ds_thuong_hieu[i].id_thuong_hieu;
                }
                body = body.Replace("{th.id_thuong_hieu}", "and (" + sql_th + ")");
            }
            else body = body.Replace("{th.id_thuong_hieu}", "");
            //3.{gia_ban_ra_>}
            if (!(yeu_cau_ds.bo_loc.gia_sp == null || yeu_cau_ds.bo_loc.gia_sp.gia_thap_nhat == null || yeu_cau_ds.bo_loc.gia_sp.gia_thap_nhat == -1))
                body = body.Replace("{sp.gia_ban_ra_>}", "and sp.gia_ban_ra >= " + yeu_cau_ds.bo_loc.gia_sp.gia_thap_nhat);
            else body = body.Replace("{sp.gia_ban_ra_>}", "");
            //4.{gia_ban_ra_<}
            if (!(yeu_cau_ds.bo_loc.gia_sp == null || yeu_cau_ds.bo_loc.gia_sp.gia_cao_nhat == null || yeu_cau_ds.bo_loc.gia_sp.gia_cao_nhat == -1))
                body = body.Replace("{sp.gia_ban_ra_<}", "and sp.gia_ban_ra <= " + yeu_cau_ds.bo_loc.gia_sp.gia_cao_nhat);
            else body = body.Replace("{sp.gia_ban_ra_<}", "");
            //5.{so_sao_danh_gia_>=}
            if (!(yeu_cau_ds.bo_loc.danh_gia == null || yeu_cau_ds.bo_loc.danh_gia.so_sao_thap_nhat == null || yeu_cau_ds.bo_loc.danh_gia.so_sao_thap_nhat == -1))
                body = body.Replace("{so_sao_tb}", "where bo_loc_tb_sao.so_sao_tb >=" + yeu_cau_ds.bo_loc.danh_gia.so_sao_thap_nhat);
                else
                body = body.Replace("{so_sao_tb}", "");
            //6.{phan_trang}
            if (!(yeu_cau_ds.bo_loc.phan_trang == null || yeu_cau_ds.bo_loc.phan_trang.vi_tri_bat_dau == null || yeu_cau_ds.bo_loc.phan_trang.vi_tri_bat_dau == -1))
            {
                body = body.Replace("{vitri_bat_dau}", yeu_cau_ds.bo_loc.phan_trang.vi_tri_bat_dau + "");
                body = body.Replace("{so_phan_tu_can_lay}", (yeu_cau_ds.bo_loc.phan_trang.vi_tri_ket_thuc + 1 - 
                    yeu_cau_ds.bo_loc.phan_trang.vi_tri_bat_dau) + "");
            }
            else
            {
                body = body.Replace("{vitri_bat_dau}", 0 + "");
                body = body.Replace("{so_phan_tu_can_lay}", 21 + "");
            }
            //7.search
            if (!(yeu_cau_ds.bo_loc.tim_kiem == null || yeu_cau_ds.bo_loc.tim_kiem == ""))
            {
              string sql_tk = "";
              string chuoi_tim_kiem = xu_ly_chuoi_timkiem(yeu_cau_ds.bo_loc.tim_kiem);
              string[] words = chuoi_tim_kiem.Split(' ');
              Boolean f = false;
              foreach (string word in words)
              {
                  if(f==false)
                  {
                      f = true;
                      sql_tk += "sp.ten_san_pham like '%"+word+"%'";
                  }
                  else sql_tk += "or sp.ten_san_pham like '%" + word + "%'";
                  if (f == false)
                  {
                      f = true;
                      sql_tk += "th.ten_thuong_hieu like '%" + word + "%'";
                  }
                  else sql_tk += "or th.ten_thuong_hieu like '%" + word + "%'";
                  if (f == false)
                  {
                      f = true;
                      sql_tk += "th.ten_thuong_hieu like '%" + word + "%'";
                  }
                  else sql_tk += "or lsp.ten_loai_san_pham like '%" + word + "%'";
                  
              }
              body = body.Replace("{search}", "and (" + sql_tk + ")");
            }
            else
            {
                body = body.Replace("{search}", "");
            }
            //8.sap_xep
            {
                string sql_sap_xep = "";
                /*
                if (ds_yc.sap_xep.danh_gia != -1) if (ds_yc.sap_xep.danh_gia == 1) sql_sap_xep = "order by bang_sp_tbsao_tongtim_stt.so_sao_tb asc";
                                                else sql_sap_xep = "order by bang_sp_tbsao_tongtim_stt.so_sao_tb desc";
                else if (ds_yc.sap_xep.gia_san_pham != -1) if (ds_yc.sap_xep.gia_san_pham == 1) sql_sap_xep = "order by sp.gia_ban_ra asc";
                    else sql_sap_xep = "order by sp.gia_ban_ra desc";
                else if (ds_yc.sap_xep.ua_thich != -1) if (ds_yc.sap_xep.ua_thich == 1) sql_sap_xep = "order by bang_sp_tbsao_tongtim_stt.so_tim asc";
                    else sql_sap_xep = "order by bang_sp_tbsao_tongtim_stt.so_tim desc";
                */
                bool flag = false;
                for (int i = 0; i < yeu_cau_ds.sap_xep.Count; i++)
                {
                    if (yeu_cau_ds.sap_xep[i].id_sap_xep == "sx_gs")
                    {
                        if(flag==false)
                        {
                            flag = true;
                            if (yeu_cau_ds.sap_xep[i].loai_sap_xep == false)
                                sql_sap_xep += "order by sp.gia_ban_ra desc";
                            else
                                sql_sap_xep += "order by sp.gia_ban_ra asc";
                        }
                        else
                        {
                            if (yeu_cau_ds.sap_xep[i].loai_sap_xep == false)
                                sql_sap_xep += ", sp.gia_ban_ra desc";
                            else
                                sql_sap_xep += ", sp.gia_ban_ra asc";
                        }
                    }
                    else if (yeu_cau_ds.sap_xep[i].id_sap_xep == "sx_ut")
                    {
                        if (flag == false)
                        {
                            flag = true;
                            if (yeu_cau_ds.sap_xep[i].loai_sap_xep == false)
                                sql_sap_xep += "order by bang_sp_tbsao_tongtim_stt.so_tim desc";
                            else
                                sql_sap_xep += "order by bang_sp_tbsao_tongtim_stt.so_tim asc";
                        }
                        else
                        {
                            if (yeu_cau_ds.sap_xep[i].loai_sap_xep == false)
                                sql_sap_xep += ", bang_sp_tbsao_tongtim_stt.so_tim desc";
                            else
                                sql_sap_xep += ", bang_sp_tbsao_tongtim_stt.so_tim asc";
                        }
                    }
                    else if (yeu_cau_ds.sap_xep[i].id_sap_xep == "sx_dg")
                    {
                        if (flag == false)
                        {
                            flag = true;
                            if (yeu_cau_ds.sap_xep[i].loai_sap_xep == false)
                                sql_sap_xep += "order by bang_sp_tbsao_tongtim_stt.so_sao_tb desc";
                            else
                                sql_sap_xep += "order by bang_sp_tbsao_tongtim_stt.so_sao_tb asc";
                        }
                        else
                        {
                            if (yeu_cau_ds.sap_xep[i].loai_sap_xep == false)
                                sql_sap_xep += ", bang_sp_tbsao_tongtim_stt.so_sao_tb desc";
                            else
                                sql_sap_xep += ", bang_sp_tbsao_tongtim_stt.so_sao_tb asc";
                        }
                    }
                }
                    body = body.Replace("{sap_xep}", sql_sap_xep);
            }
            return body;
        }
        public List<c2_group> convert_c2_group(List<c2_group_item> danh_sach_c2_group_item)
        {
            List<c2_group> danh_sach_c2_group = new List<c2_group>();
            int f = 0;
            List<c2_group_item> tmp = new List<c2_group_item>();
            for(int i = 0; i < danh_sach_c2_group_item.Count; i++)
            {
                tmp.Add(danh_sach_c2_group_item[i]);
                f++;
                if (f == 3)
                {
                    c2_group c2_group = new c2_group(tmp);
                    danh_sach_c2_group.Add(c2_group);
                    tmp = null;
                    tmp = new List<c2_group_item>();
                    f = 0;
                }
            }

            if (tmp.Count != 0)
            {
                c2_group c2_group = new c2_group(tmp);
                danh_sach_c2_group.Add(c2_group);
            }
            return danh_sach_c2_group;
        }
        private string decode(string data)
        {
            byte[] plain = Convert.FromBase64String(data);
            Encoding iso = Encoding.GetEncoding("ISO-8859-6");
            return iso.GetString(plain);
        }
        public void ham_dg_them(int id_san_pham, int id_nguoi, bool da_mua, string tieu_de_danh_gia, int so_sao_danh_gia, string noi_dung, string ngay_viet_danh_gia)
        {
            connect connect = new connect();
            SqlConnection connDB = new SqlConnection(connect.getconnect());
            SqlCommand cmd = new SqlCommand("danh_gia_them", connDB);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@id_san_pham", SqlDbType.NVarChar).Value = id_san_pham;
            cmd.Parameters.Add("@id_nguoi", SqlDbType.NVarChar).Value = id_nguoi;
            cmd.Parameters.Add("@da_mua", SqlDbType.NVarChar).Value = da_mua;
            cmd.Parameters.Add("@tieu_de_danh_gia", SqlDbType.NVarChar).Value = tieu_de_danh_gia;
            cmd.Parameters.Add("@so_sao_danh_gia", SqlDbType.NVarChar).Value = so_sao_danh_gia;
            cmd.Parameters.Add("@noi_dung", SqlDbType.NVarChar).Value = noi_dung;
            cmd.Parameters.Add("@ngay_viet_danh_gia", SqlDbType.NVarChar).Value = ngay_viet_danh_gia;
            connDB.Open();
            cmd.ExecuteNonQuery();
            connDB.Close();
        }
        public void them_tim(int id_san_pham, int id_nguoi)
        {
            connect connect = new connect();
            SqlConnection connDB = new SqlConnection(connect.getconnect());
            SqlCommand cmd = new SqlCommand("danh_sach_mong_muon_them", connDB);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@id_san_pham", SqlDbType.NVarChar).Value = id_san_pham;
            cmd.Parameters.Add("@id_nguoi", SqlDbType.NVarChar).Value = id_nguoi;
            connDB.Open();
            cmd.ExecuteNonQuery();
            connDB.Close();
        }
        public void xoa_tim(int id_san_pham, int id_nguoi)
        {
            connect connect = new connect();
            SqlConnection connDB = new SqlConnection(connect.getconnect());
            SqlCommand cmd = new SqlCommand("danh_sach_mong_muon_xoa", connDB);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@id_san_pham", SqlDbType.NVarChar).Value = id_san_pham;
            cmd.Parameters.Add("@id_nguoi", SqlDbType.NVarChar).Value = id_nguoi;
            connDB.Open();
            cmd.ExecuteNonQuery();
            connDB.Close();
        }
    }
}
