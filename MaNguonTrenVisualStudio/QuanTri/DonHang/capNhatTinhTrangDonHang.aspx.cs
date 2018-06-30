using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.DonHang
{
    public partial class capNhatTinhTrangDonHang : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            string loi = "Lỗi dữ liệu đầu vào";
            int id_don_hang = 0;
            int id_ttdh = -1000;
            int id_nguoi=  0;
               try
               {  

                if (Request.QueryString["id_don_hang"] != null && Request.QueryString["id_don_hang"] != "")
                        id_don_hang = int.Parse(Request.QueryString["id_don_hang"]);
                    if (id_don_hang == 0) int.Parse("a");

                    if (Request.QueryString["id_ttdh"] != null && Request.QueryString["id_ttdh"] != "")
                        id_ttdh = int.Parse(Request.QueryString["id_ttdh"]);
                    if (id_ttdh == -1000) int.Parse("a");

                    if (Request.QueryString["id_nguoi"] != null && Request.QueryString["id_nguoi"] != "")
                        id_nguoi = int.Parse(Request.QueryString["id_nguoi"]);
                    if (id_nguoi == 0) int.Parse("a");

                    loi = "Lỗi Không mong muốn";
                    connect connect = new connect();

                    
                    if(id_ttdh==-3)
                    {
                        SqlConnection connDB = new SqlConnection(connect.getconnect());
                        SqlCommand cmd = new SqlCommand("cap_nhat_tinh_trang_nguoi", connDB);
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.Add("@id_tinh_trang_nguoi", SqlDbType.NVarChar).Value = 11113;
                        cmd.Parameters.Add("@id_nguoi", SqlDbType.NVarChar).Value = id_nguoi;
                        connDB.Open();
                        cmd.ExecuteNonQuery();
                        connDB.Close();
                    }
                    if(id_ttdh==3)
                    {
                        {
                            SqlConnection connDB = new SqlConnection(connect.getconnect());
                            SqlCommand cmd = new SqlCommand("cap_nhat_tinh_trang_nguoi", connDB);
                            cmd.CommandType = CommandType.StoredProcedure;
                            cmd.Parameters.Add("@id_tinh_trang_nguoi", SqlDbType.NVarChar).Value = 11112;
                            cmd.Parameters.Add("@id_nguoi", SqlDbType.NVarChar).Value = id_nguoi;
                            connDB.Open();
                            cmd.ExecuteNonQuery();
                            connDB.Close();
                        }
                        /////////////////////////////////////////////////////
                        string sql = "select id_san_pham, so_luong from chi_tiet_don_hang where id_don_hang=" + id_don_hang;
                        DataTable dh = new DataTable();
                        System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                        new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(dh);

                        for (int i = 0; i < dh.Rows.Count; i++)
                        {
                            string sqlsl = "select top 1 id_kho_hang, id_san_pham, so_luong from chi_tiet_kho_hang where id_san_pham=" + dh.Rows[i][0].ToString()+ " order by so_luong desc";
                            DataTable dtsl= new DataTable();
                            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sqlsl, ketnoi)).Fill(dtsl);
                            if (int.Parse(dtsl.Rows[0][2].ToString()) < int.Parse(dh.Rows[i][1].ToString()))
                            {
                                loi = "Sản phẩm " + dtsl.Rows[0][1].ToString() + " không đủ số lượng trong kho! vui lòng báo cáo với khách hàng và hủy đơn hàng này!";
                                int.Parse("aaaaa");
                            }    
                        }
                        for (int i = 0; i < dh.Rows.Count; i++)
                        {
                            string sqlsl = "select top 1 id_kho_hang, id_san_pham, so_luong from chi_tiet_kho_hang where id_san_pham=" + dh.Rows[i][0].ToString() + " order by so_luong desc";
                            DataTable dtsl = new DataTable();
                            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sqlsl, ketnoi)).Fill(dtsl);
                            
                            SqlConnection connDB = new SqlConnection(connect.getconnect());
                            SqlCommand cmd = new SqlCommand("cap_nhat_so_luong_san_pham_cho_don_hang_thanh_cong", connDB);
                            cmd.CommandType = CommandType.StoredProcedure;
                            cmd.Parameters.Add("@id_kho_hang", SqlDbType.NVarChar).Value = dtsl.Rows[0][0].ToString();
                            cmd.Parameters.Add("@id_san_pham", SqlDbType.NVarChar).Value = dtsl.Rows[0][1].ToString();
                            cmd.Parameters.Add("@so_luong", SqlDbType.NVarChar).Value = dh.Rows[i][1].ToString();
                            connDB.Open();
                            cmd.ExecuteNonQuery();
                            connDB.Close();
                        }
                       
                    }
                    {
                        SqlConnection connDB = new SqlConnection(connect.getconnect());
                        SqlCommand cmd = new SqlCommand("cap_nhat_tinh_trang_don_hang", connDB);
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.Add("@id_ttdh", SqlDbType.NVarChar).Value = id_ttdh;
                        cmd.Parameters.Add("@id_don_hang", SqlDbType.NVarChar).Value = id_don_hang;
                        connDB.Open();
                        cmd.ExecuteNonQuery();
                        connDB.Close();
                    }
                    Response.Write("<script language='javascript'> alert('Cập nhật đơn hàng thành công');window.open(document.referrer,'_self', 1); </script>");
                }
                catch (Exception a)
                {
                    Response.Write("<script language='javascript'> alert('" + loi + "'); history.go(-1); </script>");
                }
        }
    }
}