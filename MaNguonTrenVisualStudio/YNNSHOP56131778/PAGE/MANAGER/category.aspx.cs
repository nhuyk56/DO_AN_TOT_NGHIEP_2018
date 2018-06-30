using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.YNNSHOP56131778.PAGE.MANAGER
{
    public partial class category : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

            if (!Page.IsPostBack)
            {
                danhsachcategory.DataSource = getDatabase();
                danhsachcategory.DataBind();
            }
        }

        
        private DataTable getDatabase()
        {
            connect connect = new connect();
            SqlDataAdapter adap = new SqlDataAdapter("PROC_CATEGORY_LIST ", connect.getconnect());
            DataTable ds = new DataTable();
            adap.Fill(ds);
            return ds;
        }

    }
}