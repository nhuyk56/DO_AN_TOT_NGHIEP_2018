using Microsoft.Reporting.WebForms;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebApplication2.YNNSHOP56131778.CONGFIG;

namespace WebApplication2.QuanTri.DonHang
{
    public partial class inHoaDon : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            int id_don_hang = 0; 
                try
                {
                    if (Request.QueryString["id_don_hang"] != null && Request.QueryString["id_don_hang"] != "")
                        id_don_hang = int.Parse(Request.QueryString["id_don_hang"]);
                }
                catch (Exception a) { }
                if (id_don_hang == 0) Response.Redirect("./danhSachTTDH.aspx");
                string sql =

"select " +
"dh.id_don_hang, " +
"n.ten_nguoi, " +
"ttn.ten_tinh_trang, " +
"ttdh.ten_ttdh, " +
"ptvc.ten_ptvc, " +
"kv.ht+  ', ' +dh.dia_chi_nhan_hang_chi_tiet, " +
"dh.ma_van_chuyen, " +
"cast(dh.phi_van_chuyen as int), " +
"cast(dh.phi_thu_ho as int), " +
"cast(dh.tong_tien as int), " +
"cast(FORMAT(dh.ngay_tao_don_hang, 'dd-MM-yyyy') as varchar) as ngay_tao_don_hang , " +
"cast(FORMAT(dh.ngay_xac_nhan_don_hang_thanh_cong, 'dd-MM-yyyy') as varchar) as ngay_xac_nhan_don_hang_thanh_cong , " +
"dh.so_ngay_giao_gan_nhat, " +
"dh.so_ngay_giao_xa_nhat, " +
"n.sdt_nguoi, " +
"dh.id_ttdh, " +
"n.id_nguoi " +
"from don_hang dh  " +
"join nguoi n on dh.id_nguoi=n.id_nguoi " +
"join tinh_trang_nguoi ttn on ttn.id_tinh_trang_nguoi=n.id_tinh_trang_nguoi " +
"join tinh_trang_don_hang ttdh on ttdh.id_ttdh=dh.id_ttdh " +
"join (  " +
"	select h.id_khu_vuc, t.ten_khu_vuc+  ', ' +h.ten_khu_vuc as 'ht'  " +
"	from khu_vuc h join khu_vuc t on h.id_khu_vuc_cha = t.id_khu_vuc  " +
") as kv on kv.id_khu_vuc=dh.id_khu_vuc " +
"join phuong_thuc_van_chuyen ptvc on dh.id_ptvc=ptvc.id_ptvc " +
"WHERE dh.id_don_hang=" + id_don_hang;

                connect connect = new connect();
                DataTable ds = new DataTable();
                System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                ds = new DataTable();
                new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                if (ds.Rows.Count == 0) Response.Redirect("./danhSachTTDH.aspx");

            if (!IsPostBack)
            {
                WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                sql = "select sp.id_san_pham, th.ten_thuong_hieu, sp.ten_san_pham, sp.anh_san_pham, ctdh.ma_giam_gia, ctdh.so_luong, cast(ctdh.gia_san_pham as int), cast(ctdh.gia_da_giam as int) " +
                    "from chi_tiet_don_hang ctdh join san_pham sp on ctdh.id_san_pham=sp.id_san_pham " +
                    "join thuong_hieu th on th.id_thuong_hieu=sp.id_thuong_hieu " +
                    "where id_don_hang=" + id_don_hang;
                DataTable tmp = new DataTable();
                new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(tmp);

                //khungHoaDon KHD = new khungHoaDon();
                //DataTable BangChiTietSanPham = KHD.Tables["ChiTietSanPhamDonHang"];
                DataTable BangChiTietSanPham = new DataTable();
                BangChiTietSanPham.Columns.Add("Ten");
                BangChiTietSanPham.Columns.Add("SL");
                BangChiTietSanPham.Columns.Add("DG");
                BangChiTietSanPham.Columns.Add("tamTinh");
                BangChiTietSanPham.Columns.Add("Giam");
                BangChiTietSanPham.Columns.Add("ThanhTien");
                for (int i = 0; i < tmp.Rows.Count; i++)
                {
                    DataRow x = BangChiTietSanPham.NewRow();
                    x["Ten"] = mH.Base64Decode(tmp.Rows[i][2].ToString());
                    x["SL"] = tmp.Rows[i][5].ToString();
                    x["DG"] = String.Format("{0:n0}", int.Parse(tmp.Rows[i][6].ToString())).Replace(",", ".") + " vnđ";
                    long tam1 = int.Parse(tmp.Rows[i][6].ToString()) * int.Parse(tmp.Rows[i][5].ToString());
                    long tam2 = int.Parse(tmp.Rows[i][7].ToString()) * int.Parse(tmp.Rows[i][5].ToString());
                    long tam3 = tam1 - tam2;
                    x["tamTinh"] = String.Format("{0:n0}", tam1).Replace(",", ".") + " vnđ";
                    x["Giam"] = String.Format("{0:n0}", tam3).Replace(",",".") + " vnđ";
                    x["ThanhTien"] = String.Format("{0:n0}", tam2).Replace(",", ".") + " vnđ";
                    BangChiTietSanPham.Rows.Add(x);
                }

               

                ReportViewer1.ProcessingMode = ProcessingMode.Local;
                ReportViewer1.LocalReport.ReportPath = Server.MapPath("Report1.rdlc");
                //
                ReportParameter[] param = new ReportParameter[11];
                param[0] = new ReportParameter("maDonHang", "#" + ds.Rows[0][0].ToString());
                param[1] = new ReportParameter("hoTen", ds.Rows[0][1].ToString());
                param[2] = new ReportParameter("SDT", ds.Rows[0][14].ToString());
                param[3] = new ReportParameter("PTVC", ds.Rows[0][4].ToString());
                param[4] = new ReportParameter("DCGH", ds.Rows[0][5].ToString());
                param[5] = new ReportParameter("ngayDat", ds.Rows[0][10].ToString());
                long tam = (long.Parse(ds.Rows[0][9].ToString()) - long.Parse(ds.Rows[0][8].ToString()) - long.Parse(ds.Rows[0][7].ToString()));
                param[6] = new ReportParameter("tongCong", String.Format(" {0:0,0}", tam).Replace(",", ".") + " vnđ");
                param[7] = new ReportParameter("phiVanChuyen", String.Format(" {0:0,0}", long.Parse(ds.Rows[0][7].ToString())).Replace(",", ".") + " vnđ");
                param[8] = new ReportParameter("phiThuHo", String.Format(" {0:0,0}", long.Parse(ds.Rows[0][8].ToString())).Replace(",", ".") + " vnđ");
                param[9] = new ReportParameter("tongTienThanhToan", String.Format(" {0:0,0}", long.Parse(ds.Rows[0][9].ToString())).Replace(",", ".") + " vnđ");
                param[10] = new ReportParameter("ngayBaoCao", DateTime.Now.ToString("dd/MM/yyyy"));
                ReportViewer1.LocalReport.SetParameters(param);

                //
                ReportDataSource datasource = new ReportDataSource("PhaiChinhXac", BangChiTietSanPham);
                ReportViewer1.LocalReport.DataSources.Clear();
                ReportViewer1.LocalReport.DataSources.Add(datasource);
            }
        }
    }
}