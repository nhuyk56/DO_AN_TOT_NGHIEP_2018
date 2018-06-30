using Microsoft.Reporting.WebForms;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.BaoCaoDoanhThu
{
    public partial class BaoCaoDT : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            connect connect = new connect();
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            string loi = "Dữ liệu đầu vào lỗi";
            string ngaybaydau = "";
            string ngayketthuc = "";
            try
            {

                try
                {
                    if (Request.QueryString["ngaybaydau"] != null && Request.QueryString["ngaybaydau"].Length != 0)
                        ngaybaydau = Request.QueryString["ngaybaydau"];
                    if (Request.QueryString["ngayketthuc"] != null && Request.QueryString["ngayketthuc"].Length != 0)
                        ngayketthuc = Request.QueryString["ngayketthuc"];
                }
                catch (Exception a) { }
                if (ngaybaydau.Length == 0 || ngayketthuc.Length == 0) int.Parse("a");
                loi = "Lỗi không xác định!";
                maHoa m = new maHoa();
                ngaybaydau = m.Base64Decode(ngaybaydau);
                ngayketthuc = m.Base64Decode(ngayketthuc);
                if (!IsPostBack)
                {
                    WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                    string sql = "select dh.id_don_hang, cast(FORMAT(dh.ngay_tao_don_hang, 'dd-MM-yyyy') as varchar) as ngay_tao_don_hang, " +
                    " sp.ten_san_pham, ctdh.so_luong,  " +
                    " cast (sp.gia_nhap_san_pham   as int) donGiaNhap,  " +
                    " cast(ctdh.so_luong * sp.gia_nhap_san_pham as int)thanhTienNhap, " +
                    " cast(ctdh.gia_da_giam as int)donGiaBan, " +
                    " cast(ctdh.so_luong * ctdh.gia_da_giam as int)thanhTienBan, " +
                    " cast((ctdh.so_luong * ctdh.gia_da_giam - ctdh.so_luong * sp.gia_nhap_san_pham) as int) tienLoi " +
                    " from don_hang dh join chi_tiet_don_hang ctdh on dh.id_don_hang = ctdh.id_don_hang " +
                    " join san_pham sp on sp.id_san_pham=ctdh.id_san_pham " +
                    " where " +
                    " DATEDIFF(DAY,'" + ngaybaydau + "', dh.ngay_tao_don_hang) >= 0 " +
                    " and DATEDIFF(DAY,dh.ngay_tao_don_hang, '" + ngayketthuc + "') >= 0 " +
                    " and dh.id_ttdh=3 " +
                    " order by dh.id_don_hang asc";
                    DataTable tmp = new DataTable();
                    new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(tmp);

                    //khungHoaDon KHD = new khungHoaDon();
                    //DataTable BangChiTietDoanhThu = KHD.Tables["ChiTietSanPhamDonHang"];
                    DataTable BangChiTietDoanhThu = new DataTable();
                    BangChiTietDoanhThu.Columns.Add("id_don_hang");
                    BangChiTietDoanhThu.Columns.Add("ngay_tao_don_hang");
                    BangChiTietDoanhThu.Columns.Add("ten_san_pham");
                    BangChiTietDoanhThu.Columns.Add("so_luong");
                    BangChiTietDoanhThu.Columns.Add("donGiaNhap");
                    BangChiTietDoanhThu.Columns.Add("thanhTienNhap");
                    BangChiTietDoanhThu.Columns.Add("donGiaBan");
                    BangChiTietDoanhThu.Columns.Add("thanhTienBan");
                    BangChiTietDoanhThu.Columns.Add("tienLoi");
                    long tongdoanhthu = 0, tongtienloi = 0;
                    for (int i = 0; i < tmp.Rows.Count; i++)
                    {
                        tongdoanhthu += int.Parse(tmp.Rows[i][7].ToString());
                        tongtienloi += int.Parse(tmp.Rows[i][8].ToString());
                        DataRow x = BangChiTietDoanhThu.NewRow();
                        x[0] = tmp.Rows[i][0].ToString();
                        x[1] = tmp.Rows[i][1].ToString();
                        x[2] = mH.Base64Decode(tmp.Rows[i][2].ToString());
                        x[3] = tmp.Rows[i][3].ToString();
                        x[4] = String.Format("{0:n0}", int.Parse(tmp.Rows[i][4].ToString())).Replace(",", ".") + " vnđ";
                        x[5] = String.Format("{0:n0}", int.Parse(tmp.Rows[i][5].ToString())).Replace(",", ".") + " vnđ";
                        x[6] = String.Format("{0:n0}", int.Parse(tmp.Rows[i][6].ToString())).Replace(",", ".") + " vnđ";
                        x[7] = String.Format("{0:n0}", int.Parse(tmp.Rows[i][7].ToString())).Replace(",", ".") + " vnđ";
                        x[8] = String.Format("{0:n0}", int.Parse(tmp.Rows[i][8].ToString())).Replace(",", ".") + " vnđ";
                        BangChiTietDoanhThu.Rows.Add(x);
                    }

                    ReportViewer1.ProcessingMode = ProcessingMode.Local;
                    ReportViewer1.LocalReport.ReportPath = Server.MapPath("Report1.rdlc");
                    //
                    ReportParameter[] param = new ReportParameter[4];
                    param[0] = new ReportParameter("thoiGianDauCuoi",  "TỪ NGÀY "+ ngaybaydau +" ĐẾN NGÀY "+ ngayketthuc);
                    param[1] = new ReportParameter("ngaytaobaocao", DateTime.Now.ToString("dd/MM/yyyy"));
                    param[2] = new ReportParameter("tongdoanhthu", String.Format("{0:n0}", tongdoanhthu).Replace(",", ".") + " vnđ");
                    param[3] = new ReportParameter("tongtienloi", String.Format("{0:n0}", tongtienloi).Replace(",", ".") + " vnđ");
                    ReportViewer1.LocalReport.SetParameters(param);


                    //
                    ReportDataSource datasource = new ReportDataSource("PhaiChinhXac", BangChiTietDoanhThu);
                    ReportViewer1.LocalReport.DataSources.Clear();
                    ReportViewer1.LocalReport.DataSources.Add(datasource);
                }

            }
            catch (Exception a)
            {
                Response.Write("<script language='javascript'> alert('" + loi + "'); history.go(-1); </script>");
            }
            //
            
        }
    }
}