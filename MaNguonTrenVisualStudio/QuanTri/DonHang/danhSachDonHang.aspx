<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="danhSachDonHang.aspx.cs" Inherits="WebApplication2.QuanTri.DonHang.danhSachDonHang" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <style type="text/css">
        .pagination {
padding: 20px;
margin-bottom: 20px;
}
table, td, th {
    border: 1px solid black;
}

table {
    border-collapse: collapse;
    width: 100%;
}

th {
    text-align: left;
}
.page {
    display: inline-block;
    color: #717171;
    padding: 0px 9px;
    margin-right: 1px;
    border-radius: 3px;
    border: solid 1px #c0c0c0;
    background: #e9e9e9;
    font-size:15px;
    text-decoration: none;
}
.page:hover, .page.gradient:hover {
    background: #595959;
    box-shadow: inset 0px 0px 8px rgba(0,0,0, .5), 0px 1px 0px rgba(255,255,255, .8);
    text-shadow: 0px 0px 3px rgba(0,0,0, .5);
    color: #f0f0f0;
}
 
.page.active {
    border: none;
    background: #616161;
    box-shadow: inset 0px 0px 8px rgba(0,0,0, .5), 0px 1px 0px rgba(255,255,255, .8);
    color: #f0f0f0;
    text-shadow: 0px 0px 3px rgba(0,0,0, .5);
}
    </style>
    <div id="admincon">
			<%
                int id_ttdh = -10000, trang = 0;
                try
                {
                    if (Request.QueryString["id_ttdh"] != null && Request.QueryString["id_ttdh"] != "")
                        id_ttdh = int.Parse(Request.QueryString["id_ttdh"]);
                    if (Request.QueryString["trang"] != null && Request.QueryString["trang"] != "")
                        trang = int.Parse(Request.QueryString["trang"]);
                }
                catch (Exception e) { }
                if (id_ttdh ==  - 10000) Response.Redirect("./danhSachTTDH.aspx"); %>
			<div id="sanphammoi">
				<table>
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>DANH SÁCH ĐƠN HÀNG</td>
					</tr>
					<tr class="tieudespmoi">
						<td>Đơn hàng</td>
                        <td>Tình trạng đơn hàng</td>
						<td>Người đặt</td>
						<td>Tình trạng người</td>
						<td>Địa chỉ</td>
						<td>Tổng tiền</td>
                        <td>Ngày đặt</td>
                        <td>hoạt động</td>
					</tr>
					
					
                                <%@Import Namespace="System" %>
        <%@Import Namespace="System.Collections.Generic" %>
        <%@Import Namespace="System.Linq" %>
        <%@Import Namespace="System.Web" %>
        <%@Import Namespace="System.Web.UI" %>
        <%@Import Namespace="System.Web.UI.WebControls" %>
        <%@Import Namespace="System.Data" %>
        <%@Import Namespace="System.Text" %>
        <%@Import Namespace="WebApplication2.YNNSHOP56131778.CONGFIG" %>
        <%@Import Namespace="System.Data.SqlClient" %>
                        <%
                            string sql =
"select   "+
 "dh.id_don_hang,      "+
 "ttdh.ten_ttdh,      "+
 "n.ten_nguoi,      "+
" ttn.ten_tinh_trang ,   "+  
 "concat(kv.ten_khu_vuc,', ', dh.dia_chi_nhan_hang_chi_tiet) as diachi,     "+
"cast(dh.tong_tien as int) as tong_tien,   "+
" cast(FORMAT(dh.ngay_tao_don_hang, 'dd-MM-yyyy HH:mm:ss') as varchar) as ngaytao     "+
" from      "+
" (     "+
 "select h.id_khu_vuc, concat(t.ten_khu_vuc,', ',h.ten_khu_vuc) as ten_khu_vuc    "+ 
 "from khu_vuc h join khu_vuc t on h.id_khu_vuc_cha=t.id_khu_vuc     "+
" ) as kv join don_hang dh on dh.id_khu_vuc= kv.id_khu_vuc    "+ 
" join  tinh_trang_don_hang ttdh on dh.id_ttdh=ttdh.id_ttdh     "+
" join nguoi n on n.id_nguoi=dh.id_nguoi     "+
" join tinh_trang_nguoi ttn on ttn.id_tinh_trang_nguoi=n.id_tinh_trang_nguoi     "+
" where dh.id_ttdh="+id_ttdh+
" order by dh.ngay_tao_don_hang desc   "+
"   offset " + trang * 20 + " rows " +
 "   fetch next 20 rows only ";

                            connect connect = new connect();
                            DataTable ds = new DataTable();
                            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                            ds = new DataTable();
                            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                   %>
                    <%
                        for(int i = 0 ; i < ds.Rows.Count; i++)
                        {
                            Response.Write(
                            "<tr> "  +
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td>" + ds.Rows[i][1].ToString() + "</td> " +
                                            "  <td  align=left  style=padding-left:5px> " + ds.Rows[i][2].ToString() + "</td> " +
                                            "  <td>" + ds.Rows[i][3].ToString() + "</td> " +
                                            "  <td  align=left  style=padding-left:5px>" + ds.Rows[i][4].ToString() + "</td> " +
                                           "   <td align=right style=padding-right:10px>" + String.Format(" {0:0,0}", long.Parse(ds.Rows[i][5].ToString())) + "  vnđ</td> " +
                                           "   <td >" + ds.Rows[i][6].ToString() + "</td> " +
                                            "   <td ><a href=chiTietDonHang.aspx?id_don_hang="+ds.Rows[i][0].ToString()+">Chi tiết</a></td " +
                                           "   </tr> "
                                            
                                            
                                            );
                        }
                                            
                    
                     %>
                    <tr><td colspan="1000"> <a href='../DonHang/danhSachTTDH.aspx' class='btn btn-info' style='align-self:flex-end;background-color: #2de135; border-color: green;margin: 3px;' role='button'>&lt;&lt;Trở về</a></td></tr>
				</table>
			</div>
		  <div class="pagination">
        <%
            {

                string page = WebApplication2.QuanTri.maHoa.RemoveQueryStringByKey(
                    System.Web.HttpContext.Current.Request.Url.AbsoluteUri, "trang");
                sql = "  select count(*) from don_hang where id_ttdh=" + id_ttdh;
             DataTable t = new DataTable();
             new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(t);
             int max = int.Parse(t.Rows[0][0].ToString());
             if (max % 20 != 0) max = ((int)max / 20) + 1; else max = (int)max / 20;
             Response.Write("Tổng " + max + " trang | ");
             if (trang > 3 && trang < max - 3)
             {
                 int i = 0;
                 Response.Write("<a href='" + page + "&trang=" + 0 + "' class='page'>Đẩu</a>");
                 for (i = trang - 3; i < trang + 3; i++)
                 {
                     if (i == trang)
                         Response.Write("<a href='#' class='page active'>" + i + "</a>");
                     else
                         Response.Write("<a href='" + page + "&trang=" + i + "' class='page'>" + i + "</a>");
                 }
                 if (i < max)
                     Response.Write("<a href='" + page + "&trang=" + (max - 1) + "' class='page'>Cuối</a>");
             }
             else if (trang <= 3)
             {
                 int i = 0;
                 for (i = 0; i < 6 && i < max; i++)
                 {
                     if (i == trang)
                         Response.Write("<a href='#' class='page active'>" + i + "</a>");
                     else
                         Response.Write("<a href='" + page + "&trang=" + i + "' class='page'>" + i + "</a>");
                 }
                 if (i < max)
                     Response.Write("<a href='" + page + "&trang=" + (max - 1) + "' class='page'>Cuối</a>");
             }
             else if (trang >= max - 3)
             {
                 Response.Write("<a href='" + page + "&trang=" + 0 + "' class='page'>Đẩu</a>");
                 int i = 0;
                 for (i = trang - 3; i <= max - 1; i++)
                 {
                     if (i == trang)
                         Response.Write("<a href='#' class='page active'>" + i + "</a>");
                     else
                         Response.Write("<a href='" + page + "&trang=" + i + "' class='page'>" + i + "</a>");
                 }
             }
            }
        %>
</div>
			</div>
		</div>
               </div>
</asp:Content>
