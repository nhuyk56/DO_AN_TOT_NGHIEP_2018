<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="danhSach.aspx.cs" Inherits="WebApplication2.QuanTri.ThuongHieu.danhSach" %>
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
        </style>
     <div id="admincon">
			
			<div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>THƯƠNG HIỆU</td>
					</tr>
					<tr class="tieudespmoi">
                        <td style="width:100px">Hành động</td>
						<td>ID</td>
                        <td>Tên</td>
						<td>ảnh đại diện</td>
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
                            string sql ="select id_thuong_hieu, ten_thuong_hieu,  anh_thuong_hieu from thuong_hieu"+
                                 " order by id_thuong_hieu asc " +
                                  "  offset " + trang * 20 + " rows " +
                                  "  fetch next " + 20 + " rows only";

                            connect connect = new connect();
                            DataTable ds = new DataTable();
                            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                            ds = new DataTable();
                            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                   %>
                    <%
                        WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                        for(int i = 0 ; i < ds.Rows.Count; i++)
                        {
                            string anh = "http://denrakaev.com/wp-content/uploads/2015/03/no-image.png";
                            if (ds.Rows[i][2].ToString() != "" && ds.Rows[i][2].ToString() != null)
                                anh = mH.Base64Decode(ds.Rows[i][2].ToString());
                            Response.Write(
                            "<tr> "  +
                             "<td><a href=\"?xoa=" + ds.Rows[i][0].ToString() + "\">" +
                        "<img src=\"../anh/icons8-waste-20.png\" width='20' height='20'>" +
                        "</a>" +
                          "<a  style=\"margin-left:10px\" href=\"../ThuongHieu/sua.aspx?id_thuong_hieu=" + ds.Rows[i][0].ToString() + "\">" +
                        "<img src=\"../anh/icons8-compose-20.png\" width='20' height='20'>" +
                        "</a></td>" +
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td>" + mH.Base64Decode(ds.Rows[i][1].ToString()) + "</td> " +
                                            "  <td> " + "<img src=" +anh + " style=margin:10px width=auto height=100>" + "</td> " +
                                            "   </tr> "
                                            
                                            
                                            );
                        }
                                            
                    
                     %>
				</table>
			</div>
          
    <div class="pagination">
        <%
            {  
            string page = System.Web.HttpContext.Current.Request.Url.AbsoluteUri.Split('?')[0];
            string sql_nguoi = "select count(id_thuong_hieu) from thuong_hieu";
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
</asp:Content>
