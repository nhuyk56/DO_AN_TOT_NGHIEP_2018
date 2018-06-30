<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="danhSachSanPham.aspx.cs" Inherits="WebApplication2.QuanTri.GiamGia.danhSachSanPham" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
     <style> 
        @font-face {
      font-family: 'fontlsp';
      src: url('loaisanpham.ttf') format('truetype');
      font-weight: normal;
      font-style: normal;
    }
        </style>
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
                bool hieu_luc = true;
                connect connect;
                int id_giam_gia = 0;
                try
                {
                    if (Request.QueryString["id_giam_gia"] != null && Request.QueryString["id_giam_gia"] != "")
                        id_giam_gia = int.Parse(Request.QueryString["id_giam_gia"]);
                }
                catch (Exception a) { }
                if (id_giam_gia == 0) Response.Redirect("./danhSach.aspx");
                WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                string sql = "select id_giam_gia, ma_giam_gia, ten_giam_gia, ly_do_giam_gia, cast(FORMAT(thoi_diem_bat_dau_giam_gia, 'yyyy/MM/dd HH:mm:ss')as varchar), cast(FORMAT(thoi_diem_ket_thuc_giam_gia, 'yyyy/MM/dd HH:mm:ss')as varchar),CASE    WHEN DATEDIFF(MINUTE, GETDATE(),thoi_diem_bat_dau_giam_gia)>0 THEN 1    ELSE 0  END from giam_gia where id_giam_gia=" + id_giam_gia;
                connect = new connect();
                DataTable ds = new DataTable();
                System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                if (ds == null || ds.Rows.Count == 0) Response.Redirect("./danhSach.aspx");
                if (int.Parse(ds.Rows[0][6].ToString()) == 0) hieu_luc = false;
                 %>
			<div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>CHƯƠNG TRÌNH GIẢM GIÁ</td>
					</tr>
					<tr class="tieudespmoi">
                        <td style="width:100px">Hành động</td>
						<td>ID</td>
                        <td>Mã giảm giá</td>
						<td>Tên chương trình giảm giá</td>
						<td>Lý do</td>
						<td>Thời điểm bắt đầu</td>
						<td>Thời điểm kết thúc</td>
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
                        for(int i = 0 ; i < ds.Rows.Count; i++)
                        {
                            string edit = "<td><a href=\"../GiamGia/danhSach.aspx?xoa=" + ds.Rows[i][0].ToString() + "\">" +
                        "<img src=\"../anh/icons8-waste-20.png\" width='20' height='20'>" +
                        "</a>" +
                          "<a  style=\"margin-left:10px\" href=\"../GiamGia/sua.aspx?id_giam_gia=" + ds.Rows[i][0].ToString() + "\">" +
                        "<img src=\"../anh/icons8-compose-20.png\" width='20' height='20'>" +
                        "</a>" + "</td>";
                            if (hieu_luc == false) edit = "<td>Không cho phép</td>";
                             Response.Write(
                            "<tr> "  +edit+
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td>" + ds.Rows[i][1].ToString() + "</td> " +
                                            "  <td> " +ds.Rows[i][2].ToString() + "</td> " +
                                            "  <td>"+ds.Rows[i][3].ToString()+"</td> " +
                                            "  <td >" + ds.Rows[i][4].ToString() + "</td> " +
                                            "   <td >"+ ds.Rows[i][5].ToString() + "</td " +
                                           "   </tr> "
                                            
                                            
                                            );
                        }
                                            
                    
                     %>
				</table>
			</div>
		</div>
    <!--- Phần sản phẩm --->
      <div id="admincon">
			<div id="sanphammoi">
				<table scrolling="yes">
					<tr class="sanphammoitheongay">
						<td colspan=1000>DANH SÁCH SẢN PHẨM ĐÃ THÊM VÀO</td>
					</tr>
					<tr class="tieudespmoi">
                        <td style="width:100px">Hành động</td>
						<td>ID</td>
                        <td>Tên</td>
						<td>Ảnh</td>
						<td>Giá cao</td>
						<td>Giá bán ra</td>
						<td>Giá nhập</td>
                        <td>(%)Giảm</td>
                        <td>Giảm tối đa</td>
					</tr>

                    <%
                        sql = "  select sp.id_san_pham, "+
"  sp.ten_san_pham,  "+
"  sp.anh_san_pham,  "+
"  cast(sp.gia_cao as int),   "+
"  cast(sp.gia_ban_ra as int),   "+
"  cast(sp.gia_nhap_san_pham as int),   "+
"  dsspgg.phan_tram_giam,   "+
"  cast(dsspgg.giam_toi_da as int)  " +
"  from giam_gia GG join danh_sac_san_pham_giam_gia dsspgg on GG.id_giam_gia=dsspgg.id_giam_gia "+
"  join san_pham sp on sp.id_san_pham = dsspgg.id_san_pham "+
"  where dsspgg.id_giam_gia=" + id_giam_gia;
                        connect = new connect();
                        ds = new DataTable();
                        new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                        for(int i = 0 ; i < ds.Rows.Count; i++)
                        {
                            string edit = "<td><a href=\"../GiamGia/xoasp.aspx?xoa=1&id_san_pham=" + ds.Rows[i][0].ToString() + "&id_giam_gia=" + id_giam_gia + "\">" +
                        "<img src=\"../anh/icons8-waste-20.png\" width='20' height='20'>" +
                        "</a>" +
                          "<a  style=\"margin-left:10px\" href=\"../GiamGia/suasp.aspx?id_san_pham=" + ds.Rows[i][0].ToString() + "&id_giam_gia=" + id_giam_gia + "\">" +
                        "<img src=\"../anh/icons8-compose-20.png\" width='20' height='20'>" +
                        "</a>" + "</td>";
                            if (hieu_luc == false) edit = "<td>Không cho phép</td>";
                             Response.Write(
                            "<tr> "  +
                            edit +
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td>" + mH.Base64Decode(ds.Rows[i][1].ToString()) + "</td> " +
                                            "  <td> <img src=' " + mH.Base64Decode(ds.Rows[i][2].ToString()) + "' width='150' height='100'></td> " +
                                            "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][3].ToString())) + " vnđ</td> " +
                                           "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][4].ToString())) + " vnđ</td> " +
                                            "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][5].ToString())) + " vnđ</td> " +
                                            "   <td >"+ ds.Rows[i][6].ToString() + "%</td> " +
                                            "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][7].ToString())) + " vnđ</td> " +
                                           "   </tr> "
                                            
                                            );
                        }
                                            
                    
                     %>
				</table>
			</div>
		</div>
    <!--- Phần sản phẩm sẽ thêm--->
      <div id="admincon">
			<div id="sanphammoi">
				<table scrolling="yes">
					<tr class="sanphammoitheongay">
						<td colspan=1000>CHỌN SẢN PHẨM ĐỂ THÊM</td>
					</tr>
					<tr class="tieudespmoi">
                        <td style="width:100px">Hành động</td>
						<td>ID</td>
                        <td>Tên</td>
						<td>Ảnh</td>
						<td>Giá cao</td>
						<td>Giá bán ra</td>
						<td>Giá nhập</td>
					</tr>

                    <%
                        int trang = 0;
                        try
                        {
                            if (Request.QueryString["trang"] != null && Request.QueryString["trang"] != "")
                                trang = int.Parse(Request.QueryString["trang"]);
                        }
                        catch (Exception e) { }
                        sql =
"   select id_san_pham,  " +
"   ten_san_pham,  " +
"   anh_san_pham,  " +
"   cast(gia_cao as int),  " +
"   cast(gia_ban_ra as int),  " +
"   cast(gia_nhap_san_pham as int) " +
"   FROM san_pham " +
"   where id_san_pham not in ( " +
"   select id_san_pham " +
"   from danh_sac_san_pham_giam_gia " +
"   where id_giam_gia="+id_giam_gia+
"   ) " +
"   order by id_san_pham desc " +
"   offset " + trang * 20 + " rows " +
"   fetch next 20 rows only ";
                        connect = new connect();
                        ds = new DataTable();
                        new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                        for(int i = 0 ; i < ds.Rows.Count; i++)
                        {
                            string edit = "<td><a href=\"../GiamGia/themsp.aspx?id_san_pham=" + ds.Rows[i][0].ToString() + "&id_giam_gia=" + id_giam_gia + "\">" +
                        "<img src=\"../anh/icons8-plus-20.png\" width='20' height='20'>" +
                        "</a>" + "</td>";
                            if (hieu_luc == false) edit = "<td>Không cho phép</td>";
                             Response.Write(
                            "<tr> "  +
                            edit+
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td>" + mH.Base64Decode(ds.Rows[i][1].ToString()) + "</td> " +
                                            "  <td> <img src=' " + mH.Base64Decode(ds.Rows[i][2].ToString()) + "' width='150' height='100'></td> " +
                                            "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][3].ToString())) + " vnđ</td> " +
                                           "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][4].ToString())) + " vnđ</td> " +
                                            "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][5].ToString())) + " vnđ</td> " +
                                           "   </tr> "
                                            
                                            );
                        }
                                            
                    
                     %>
				</table>
                   </p>
    <div class="pagination">
        <%
            {

                string page = WebApplication2.QuanTri.maHoa.RemoveQueryStringByKey(
                    System.Web.HttpContext.Current.Request.Url.AbsoluteUri, "trang");
             sql = "select count(id_san_pham) FROM san_pham where id_san_pham not in ( select id_san_pham from danh_sac_san_pham_giam_gia where id_giam_gia="+id_giam_gia+") ";
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
</asp:Content>
