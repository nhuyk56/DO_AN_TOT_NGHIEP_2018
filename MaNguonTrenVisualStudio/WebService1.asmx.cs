using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data;
using System.Data.SqlClient;
using System.Web.Script.Serialization;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;
using System.Collections.ObjectModel;
namespace WebApplication2
{   
    /// <summary>
    /// Summary description for WebService1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class WebService1 : System.Web.Services.WebService
    {
        //Chú Ý
        string connStr = "Data Source=(local);Initial Catalog=YNNSHOP56131778;user=sa;pwd=1";

        [WebMethod]
        public string HelloWorld()
        {
            return "SOAP ĐÃ HOẠT ĐỘNG";
        }

        [WebMethod]
        public string layDuLieuLoaiSanPham()
        {
            //{"user":"nhuy","pass":"1996"}
            //JObject jObject = JObject.Parse(dataLogin);
            //string User = (string)jObject["user"]; string Pass = (string)jObject["pass"];
          
                SqlDataAdapter adap = new SqlDataAdapter("categoryGetData", connStr);
                DataTable ds = new DataTable();
                adap.Fill(ds);
                string JSONString = string.Empty;
            //JSON đây
                JSONString = JsonConvert.SerializeObject(ds);
                return JSONString;
        }
        [WebMethod]
        public string kiemTraThongTinDangNhap(string dataLogin)
        {
            //{"user":"nhuy","pass":"1996"}
            JObject jObject = JObject.Parse(dataLogin);
            string User = (string)jObject["user"]; string Pass = (string)jObject["pass"];
            try
            {
                SqlDataAdapter adap = new SqlDataAdapter("dn " + User + "," + Pass, connStr);
                DataTable ds = new DataTable();
                adap.Fill(ds);
                DataRow[] foundRows;
                foundRows = ds.Select();
                //return Int32.Parse(foundRows[0][1].ToString());
                if (foundRows[0][0].ToString() != null) return "Thanh Cong";
                else return "That Bai";
            }
            catch
            {
                return "That Bai";
            }

        }


        
        
    }
}
