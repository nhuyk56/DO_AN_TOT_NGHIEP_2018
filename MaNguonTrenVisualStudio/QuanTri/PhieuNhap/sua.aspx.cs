using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.PhieuNhap
{
    public partial class sua : System.Web.UI.Page
    {
        bool hieu_luc = true;
        connect connect;
        int id_phieu_nhap = 0;
        protected void Page_Load(object sender, EventArgs e)
        {
            connect connect;
            try
            {
                if (Request.QueryString["id_phieu_nhap"] != null && Request.QueryString["id_phieu_nhap"] != "")
                    id_phieu_nhap = int.Parse(Request.QueryString["id_phieu_nhap"]);
            }
            catch (Exception a) { }
            if (id_phieu_nhap == 0) Response.Redirect("./danhSach.aspx");
            WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();


           string sql = "select id_kho_hang, ten_kho_hang from kho_hang";
            connect = new connect();
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            DataTable ds = new DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
            if (!IsPostBack)
            {
                DropDownList1.DataSource = ds;
                DropDownList1.DataTextField = "ten_kho_hang";
                DropDownList1.DataValueField = "id_kho_hang";
                DropDownList1.DataBind();
            }

            sql = "select pnhh.id_phieu_nhap, pnhh.id_nguoi, n.ten_nguoi, pnhh.id_kho_hang,  cast(FORMAT(pnhh.ngay_tao_phieu, 'yyyy-MM-dd') as varchar) as ngay_tao_phieu, pnhh.mo_ta_nguon_nhap, CASE WHEN DATEDIFF(HOUR, GETDATE(), pnhh.ngay_tao_phieu)>=-24 THEN 1    ELSE 0  END " +
                        "from phieu_nhap_hang_hoa pnhh " +
                        "join nguoi n on pnhh.id_nguoi=n.id_nguoi " +
                        "where pnhh.id_phieu_nhap=" + id_phieu_nhap;
            connect = new connect();
            ds = new DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
            if (ds == null || ds.Rows.Count == 0) Response.Redirect("./danhSach.aspx");
            if (int.Parse(ds.Rows[0][6].ToString()) == 0) hieu_luc = false;
            else hieu_luc = true;
            if (hieu_luc == false) Response.Redirect("./danhSach.aspx");

            if (!IsPostBack)
            {
                TextBox6.Text = ds.Rows[0][0].ToString();
                TextBox7.Text = ds.Rows[0][2].ToString();
                TextBox8.Text = ds.Rows[0][4].ToString();
                TextBox3.Text = ds.Rows[0][5].ToString();
                DropDownList1.SelectedValue = ds.Rows[0][3].ToString();
            }
            
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            try
            {
                connect = new connect();
                SqlConnection connDB = new SqlConnection(connect.getconnect());
                SqlCommand cmd = new SqlCommand("phieu_nhap_hang_hoa_sua", connDB);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_phieu_nhap", SqlDbType.NVarChar).Value = id_phieu_nhap;
                cmd.Parameters.Add("@mo_ta_nguon_nhap", SqlDbType.NVarChar).Value = TextBox3.Text;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('Sửa thành công');  window.open('danhSach.aspx','_self', 1);</script>");
            }
            catch (Exception qr)
            {
                Response.Write("<script language='javascript'> alert('Lỗi không xác định'); history.go(-1)</script>");
            }
        }
    }
}