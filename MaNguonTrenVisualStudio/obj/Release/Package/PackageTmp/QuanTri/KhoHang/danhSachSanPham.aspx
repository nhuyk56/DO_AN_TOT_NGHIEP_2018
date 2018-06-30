<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="danhSachSanPham.aspx.cs" Inherits="WebApplication2.QuanTri.KhoHang.danhSachSanPham" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
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
                bool hieu_luc = true;
                connect connect;
                int id_kho_hang = 0;
                try
                {
                    if (Request.QueryString["id_kho_hang"] != null && Request.QueryString["id_kho_hang"] != "")
                        id_kho_hang = int.Parse(Request.QueryString["id_kho_hang"]);
                }
                catch (Exception a) { }
                int trang = 0;
                try
                {
                    if (Request.QueryString["trang"] != null && Request.QueryString["trang"] != "")
                        trang = int.Parse(Request.QueryString["trang"]);
                }
                catch (Exception e) { }
                if (id_kho_hang == 0) Response.Redirect("./danhSach.aspx");
                WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                string sql = "select kh.id_kho_hang, kh.ten_kho_hang, kv.ht+ ', ' + kh.dia_chi_kho_hang_chi_tiet, kh.dien_tich_kho, cast(FORMAT(kh.ngay_tao_kho, 'yyyy-MM-dd') as varchar) as ngay_tao_kho, kh.ghi_chu_kho "+
                                "from   "+
                                "(  "+
                                "	select h.id_khu_vuc, t.ten_khu_vuc + ', ' +h.ten_khu_vuc as 'ht'  "+
                                "	from khu_vuc h join khu_vuc t on h.id_khu_vuc_cha = t.id_khu_vuc  "+
                                 ") as kv join kho_hang kh on kh.id_khu_vuc=kv.id_khu_vuc  "+
								 "where kh.id_kho_hang=" + id_kho_hang; 
                connect = new connect();
                DataTable ds = new DataTable();
                System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                if (ds == null || ds.Rows.Count == 0) Response.Redirect("./danhSach.aspx");
                 %>
           <div id="admincon">
			
			<div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>KHO HÀNG</td>
					</tr>
					<tr class="tieudespmoi">
                        <td style="width:100px">Hành động</td>
						<td>ID kho</td>
                        <td>Tên kho hàng</td>
						<td>Tên địa chỉ</td>
						<td>Diện tích</td>
                        <td>Ngày tạo kho</td>
                        <td>Ghi chú</td>
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
                            string edit = "<td><a href=\"../KhoHang/xoa.aspx?id_kho_hang=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-waste-20.png\" width='20' height='20'>" +
                                "</a>" +
                             "<a  style=\"margin-left:10px\" href=\"../KhoHang/sua.aspx?id_kho_hang=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-compose-20.png\" width='20' height='20'>" +
                                "</a>" + "</td>";
                            Response.Write(
                            "<tr> "  +edit+
                          
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td>" + ds.Rows[i][1].ToString() + "</td> " +
                                            "  <td> " +ds.Rows[i][2].ToString() + "</td> " +
                                            "  <td>"+ds.Rows[i][3].ToString()+"m2</td> "+
                                              " <td>" + ds.Rows[i][4].ToString() + "</td> " +
                                            "  <td>" + ds.Rows[i][5].ToString() + "</td> " +
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
						<td colspan=1000>CHI TIẾT SẢN PHẨM TRONG KHO</td>
					</tr>
					<tr class="tieudespmoi">
                        <td style="width:100px">Hành động</td>
						<td>ID</td>
                        <td>Tên</td>
						<td>Ảnh</td>
                        <td>Số lượng</td>
                         <td>Ngày nhập kho</td>
					</tr>

                    <%
                        sql = "SELECT sp.id_san_pham, sp.ten_san_pham, sp.anh_san_pham, ctkh.so_luong, cast(FORMAT(ctkh.ngay_nhap_vao_kho, 'yyyy-MM-dd') as varchar) as ngay_nhap_vao_kho  "+
                                " FROM chi_tiet_kho_hang ctkh join san_pham sp on ctkh.id_san_pham=sp.id_san_pham  "+
                                " where id_kho_hang="+id_kho_hang+
                                " order by ctkh.so_luong asc" +
                                "   offset " + trang * 20 + " rows " +
                                "   fetch next 20 rows only ";
                        connect = new connect();
                        ds = new DataTable();
                        new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                        for(int i = 0 ; i < ds.Rows.Count; i++)
                        {
                             Response.Write(
                            "<tr> "  +
                            "<td>Không cho phép</td>"+
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td>" + mH.Base64Decode(ds.Rows[i][1].ToString()) + "</td> " +
                                            "  <td> <img src=' " + mH.Base64Decode(ds.Rows[i][2].ToString()) + "' width='150' height='100'></td> " +
                                           "  <td>" + ds.Rows[i][3].ToString() + "</td> " +
                                            "  <td>" + ds.Rows[i][4].ToString() + "</td> " +
                                             "   </tr> "
                                            
                                            );
                        }
                                            
                    
                     %>
				</table>
			</div>
          <div class="pagination">
        <%
            {
                string page = WebApplication2.QuanTri.maHoa.RemoveQueryStringByKey(
                       System.Web.HttpContext.Current.Request.Url.AbsoluteUri, "trang");
            string sql_nguoi = "select count(*) "+
                                "from chi_tiet_kho_hang "+
                                "where id_kho_hang="+id_kho_hang;
            System.Data.DataTable dtt = new System.Data.DataTable();
            System.Data.SqlClient.SqlConnection kn = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            dtt = new System.Data.DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql_nguoi, kn)).Fill(dtt);
            int max = int.Parse(dtt.Rows[0][0].ToString());
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
</asp:Content>
