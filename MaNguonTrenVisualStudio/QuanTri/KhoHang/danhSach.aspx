<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="danhSach.aspx.cs" Inherits="WebApplication2.QuanTri.KhoHang.danhSach" %>
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
      <style> 
        @font-face {
      font-family: 'fontlsp';
      src: url('loaisanpham.ttf') format('truetype');
      font-weight: normal;
      font-style: normal;
    }
          .auto-style1 {
              width: 100px;
              height: 30px;
          }
          .auto-style2 {
              height: 30px;
          }
        </style>
     <div id="admincon">
			
			<div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>KHO HÀNG</td>
					</tr>
					<tr class="tieudespmoi">
                        <td class="auto-style1">Hành động</td>
						<td class="auto-style2">ID kho</td>
                        <td class="auto-style2">Tên kho hàng</td>
						<td class="auto-style2">Địa chỉ</td>
						<td class="auto-style2">Diện tích</td>
                        <td class="auto-style2">Ngày tạo kho</td>
                        <td class="auto-style2">Ghi chú</td>
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
                            int trang = 0;
                            try
                            {
                                if (Request.QueryString["trang"] != null && Request.QueryString["trang"] != "")
                                    trang = int.Parse(Request.QueryString["trang"]);
                            }
                            catch (Exception e) { }
                            string sql =
                                  "select kh.id_kho_hang, kh.ten_kho_hang, kv.ht+ ', ' + kh.dia_chi_kho_hang_chi_tiet, kh.dien_tich_kho, cast(FORMAT(kh.ngay_tao_kho, 'yyyy-MM-dd') as varchar) as ngay_tao_kho, kh.ghi_chu_kho " +
                                "from  "+
                                "( "+
                                "	select h.id_khu_vuc, t.ten_khu_vuc + ', ' +h.ten_khu_vuc as 'ht' "+
                                "	from khu_vuc h join khu_vuc t on h.id_khu_vuc_cha = t.id_khu_vuc "+
                                 ") as kv join kho_hang kh on kh.id_khu_vuc=kv.id_khu_vuc "+
                                 "order by id_kho_hang desc" +
                                  "  offset " + trang * 20 + " rows " +
                                  "  fetch next 20 rows only";

                            connect connect = new connect();
                            DataTable ds = new DataTable();
                            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                            ds = new DataTable();
                            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                   %>
                    <%
                        for(int i = 0 ; i < ds.Rows.Count; i++)
                        {
                            string edit = "<td><a href=\"../KhoHang/xoa.aspx?id_kho_hang=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-waste-20.png\" width='20' height='20'>" +
                                "</a>" +
                             "<a  style=\"margin-left:10px\" href=\"../KhoHang/sua.aspx?id_kho_hang=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-compose-20.png\" width='20' height='20'>" +
                                "</a>" + "<a  style=\"margin-left:10px\" href=\"../KhoHang/danhSachSanPham.aspx?id_kho_hang=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-insert-table-20.png\" width='20' height='20'>" +
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
         <div class="pagination">
        <%
            {
                string page = WebApplication2.QuanTri.maHoa.RemoveQueryStringByKey(
                       System.Web.HttpContext.Current.Request.Url.AbsoluteUri, "trang");
            string sql_nguoi = "select COUNT(*)/20 + 1 from kho_hang";
            System.Data.DataTable dtt = new System.Data.DataTable();
            System.Data.SqlClient.SqlConnection kn = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            dtt = new System.Data.DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql_nguoi, kn)).Fill(dtt);
            int max = int.Parse(dtt.Rows[0][0].ToString());
            Response.Write("Tổng " + max + " trang | ");
             if(trang > 3 && trang < max - 3)
             {
                 int i = 0;
                 Response.Write("<a href='" + page + "?trang="+0+"' class='page'>Đẩu</a>");
                 for(i = trang - 3; i < trang + 3; i++)
                 {
                     if(i==trang)
                         Response.Write("<a href='#' class='page active'>" + i + "</a>");
                     else
                         Response.Write("<a href='" + page + "?trang=" + i + "' class='page'>" + i + "</a>");
                 }
                 if(i < max)
                     Response.Write("<a href='" + page + "?trang=" + max + "' class='page'>Cuối</a>");
             }
             else if (trang <= 3)
             {
                 int i = 0;
                 for (i = 0; i < 6 && i < max; i++)
                 {
                     if (i == trang)
                         Response.Write("<a href='#' class='page active'>" + i + "</a>");
                     else
                         Response.Write("<a href='" + page + "?trang=" + i + "' class='page'>" + i + "</a>");
                 }
                 if (i < max)
                     Response.Write("<a href='" + page + "?trang=" + max + "' class='page'>Cuối</a>");
             }
             else if (trang >= max - 3)
             {
                 Response.Write("<a href='" + page + "?trang=" + 0 + "' class='page'>Đẩu</a>");
                 int i = 0;
                 for (i = trang - 3; i <= max; i++)
                 {
                     if (i == trang)
                         Response.Write("<a href='#' class='page active'>" + i + "</a>");
                     else
                         Response.Write("<a href='" + page + "?trang=" + i + "' class='page'>" + i + "</a>");
                 }
             }
            }
        %>
		</div>
		</div>
</asp:Content>
