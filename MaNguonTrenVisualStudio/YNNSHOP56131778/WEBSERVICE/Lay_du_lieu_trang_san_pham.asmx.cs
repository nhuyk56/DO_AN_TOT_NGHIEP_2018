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
using System.Web.Script.Serialization;
using WebApplication2.YNNSHOP56131778.OBJECT.giam_gia;
using System.Net;
using System.Collections.Specialized;
using System.Collections;
using System.Net.Http;
using WebApplication2.YNNSHOP56131778.FUNCTION;
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
    /// Summary description for Lay_du_lieu_trang_san_pham
    /// </summary>
    /// 
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class Lay_du_lieu_trang_san_pham : System.Web.Services.WebService
    {

        public string lay_key()
        {
            Random rand = new Random((int)DateTime.Now.Ticks);
            int numIterations = 0;
            numIterations = rand.Next(1, 100);
            string a =  Base64Encode(Base64Encode(DateTime.Now.ToString()));
            string kq="";
            for(int i  = 0; i < a.Length; i++)
            {
                kq += a[rand.Next(0, a.Length-1)];
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
                    mailMessage.Subject = "test";
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
            catch(Exception e)
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
        public string doi_mat_khau(string email, string key, string mat_khau_moi)
        {
                if (Application[email]!=null)
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
                        return new thong_bao(true, "Đổi mật khẩu thành công!", new System.Dynamic.ExpandoObject()).ParserJSon();
                    }

                return new thong_bao(false, "Code khôi phục mật khẩu đã hết hạn hoặc không tồn tại!", new System.Dynamic.ExpandoObject()).ParserJSon();
        } 
        public static string Base64Encode(string plainText) {
          var plainTextBytes = System.Text.Encoding.UTF8.GetBytes(plainText);
          return System.Convert.ToBase64String(plainTextBytes);
        }

        public static string Base64Decode(string base64EncodedData) {
          var base64EncodedBytes = System.Convert.FromBase64String(base64EncodedData);
          return System.Text.Encoding.UTF8.GetString(base64EncodedBytes);
        }
    }
}
