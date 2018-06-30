using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;
namespace WebApplication2.YNNSHOP56131778.PAGE
{
    public partial class Site1 : System.Web.UI.MasterPage
    {
        connect connect = new connect();
        protected void Page_Load(object sender, EventArgs e)
        {
            
        }

        private int kiemTraThongTinDangNhap(string User, string Pass)
        {
            try
            {
                SqlDataAdapter adap = new SqlDataAdapter("ADMINLOGIN " + User + "," + Pass, connect.getconnect());
                DataTable ds = new DataTable();
                adap.Fill(ds);
                DataRow[] foundRows;
                foundRows = ds.Select();
                return Int32.Parse(foundRows[0][0].ToString());
            }
            catch
            {
                return -1;
            }

        }
    }
}