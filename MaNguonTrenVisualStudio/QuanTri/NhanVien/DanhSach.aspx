<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="DanhSach.aspx.cs" Inherits="WebApplication2.QuanTri.NhanVien.DanhSach" %>
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
     <%
         @Import Namespace="System" %>
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
                connect connect;
                WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                string sql = "select id_nguoi, email_nguoi, ten_nguoi, cast(FORMAT(ngay_sinh, 'dd-MM-yyyy') as varchar) as ngay_sinh, dia_chi_nguoi, CASE WHEN gioi_tinh_nguoi=1 THEN 'Nam'  else 'nu' end as GT from nguoi where la_nhan_vien=1 order by id_nguoi desc " +
                    "   offset " + trang * 20 + " rows " +
                    "   fetch next 20 rows only ";
                    ;
                connect = new connect();
                DataTable ds = new DataTable();
                System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                 %>
           <div id="admincon">
			<div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>DANH SÁCH NHÂN VIÊN</td>
					</tr>
					<tr class="tieudespmoi">
                        <td style="width:100px">Hành động</td>
						<td>ID/td>
                        <td>Email</td>
						<td>Tên</td>
						<td>Ngày sinh</td>
                        <td>Địa chỉ</td>
                        <td>Giới tính</td>
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
                            string edit = "<td><a href=\"../NhanVien/xoa.aspx?id_nguoi=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-waste-20.png\" width='20' height='20'>" +
                                "</a>" +
                             "<a  style=\"margin-left:10px\" href=\"../NhanVien/sua.aspx?id_nguoi=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-compose-20.png\" width='20' height='20'></a>"+ "</td>";
                            Response.Write(
                            "<tr> "  +edit+
                          
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td>" + ds.Rows[i][1].ToString() + "</td> " +
                                           "  <td>" + ds.Rows[i][2].ToString() + "</td> " +
                                            "  <td>" + ds.Rows[i][3].ToString() + "</td> " +
                                             "  <td>" + ds.Rows[i][4].ToString() + "</td> " +
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
                sql = "select count(*)+1 from nguoi where la_nhan_vien=1";
             DataTable t = new DataTable();
             new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(t);
             int max = int.Parse(t.Rows[0][0].ToString());
             if (max % 20 != 0) max = ((int)max / 20) + 1; else max = (int)max / 20;
             Response.Write("Tổng " + max + " trang | ");
             if (trang > 3 && trang < max - 3)
             {
                 int i = 0;
                 Response.Write("<a href='" + page + "?trang=" + 0 + "' class='page'>Đẩu</a>");
                 for (i = trang - 3; i < trang + 3; i++)
                 {
                     if (i == trang)
                         Response.Write("<a href='#' class='page active'>" + i + "</a>");
                     else
                         Response.Write("<a href='" + page + "?trang=" + i + "' class='page'>" + i + "</a>");
                 }
                 if (i < max)
                     Response.Write("<a href='" + page + "?trang=" + (max - 1) + "' class='page'>Cuối</a>");
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
                     Response.Write("<a href='" + page + "?trang=" + (max - 1) + "' class='page'>Cuối</a>");
             }
             else if (trang >= max - 3)
             {
                 Response.Write("<a href='" + page + "?trang=" + 0 + "' class='page'>Đẩu</a>");
                 int i = 0;
                 for (i = trang - 3; i <= max - 1; i++)
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
		</div>
               </div>
</asp:Content>
