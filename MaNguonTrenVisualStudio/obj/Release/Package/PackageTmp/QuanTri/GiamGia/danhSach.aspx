<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="danhSach.aspx.cs" Inherits="WebApplication2.QuanTri.GiamGia.danhSach" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
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
                            string sql =
  "select id_giam_gia, ma_giam_gia, ten_giam_gia, ly_do_giam_gia, cast(FORMAT(thoi_diem_bat_dau_giam_gia, 'dd-MM-yyyy HH:mm:ss') as varchar) as thoi_diem_bat_dau_giam_gia, cast(FORMAT(thoi_diem_ket_thuc_giam_gia, 'dd-MM-yyyy HH:mm:ss') as varchar) as thoi_diem_ket_thuc_giam_gia , CASE    WHEN DATEDIFF(MINUTE, GETDATE(),thoi_diem_bat_dau_giam_gia)>0 THEN 1    ELSE 0  END from giam_gia";

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
                            string edit = "<td><a href=\"?xoa=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-waste-20.png\" width='20' height='20'>" +
                                "</a>" +
                             "<a  style=\"margin-left:10px\" href=\"../GiamGia/sua.aspx?id_giam_gia=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-compose-20.png\" width='20' height='20'>" +
                                "</a>" + "<a  style=\"margin-left:10px\" href=\"../GiamGia/danhSachSanPham.aspx?id_giam_gia=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-insert-table-20.png\" width='20' height='20'>" +
                                "</a>" + "</td>";
                            if (int.Parse(ds.Rows[i][6].ToString())==0)
                                  edit = "<td>"+
                                "</a>" + "<a  style=\"margin-left:10px\" href=\"../GiamGia/danhSachSanPham.aspx?id_giam_gia=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-insert-table-20.png\" width='20' height='20'>" +
                                "</a>" + "</td>";
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
</asp:Content>
