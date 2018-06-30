<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="danhSachTTDH.aspx.cs" Inherits="WebApplication2.QuanTri.DonHang.danhSachTTDH" %>
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
						<td colspan=1000>CHỌN LOẠI ĐƠN HÀNG</td>
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
                                  "select id_ttdh, ten_ttdh from tinh_trang_don_hang";

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
         "<tr><td><br><a href='../DonHang/danhSachDonHang.aspx?id_ttdh="+ds.Rows[i][0].ToString()+"' class='btn btn-info' style='background-color: #2de135; border-color: green;' role='button'>" + ds.Rows[i][1].ToString() + "</a><br><br></td></tr>"
                          );
                        }
                                            
                    
                     %>
				</table>
			</div>
</asp:Content>
