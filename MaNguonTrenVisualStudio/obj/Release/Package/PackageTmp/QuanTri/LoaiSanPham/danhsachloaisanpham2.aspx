﻿<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="danhsachloaisanpham2.aspx.cs" Inherits="WebApplication2.QuanTri.LoaiSanPham.danhsachloaisanpham2" %>
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
				  <%
                            int id_cha_loai_san_pham = -1;
                            try
                            {
                                if (Request.QueryString["id_loai_san_pham"] != null && Request.QueryString["id_loai_san_pham"] != "")
                                    id_cha_loai_san_pham = int.Parse(Request.QueryString["id_loai_san_pham"]);
                            }
                            catch (Exception e) { }
                            if (id_cha_loai_san_pham == -1) Response.Redirect("./danhsachloaisanpham.aspx");
                   %>
					<tr class="sanphammoitheongay">
						<td colspan=1000>DANH MỤC SẢN PHẨM <% Response.Write(id_cha_loai_san_pham);%></td>
					</tr>
					<tr class="tieudespmoi">
                         <td style="width:100px">Hành động</td>
						<td>ID</td>
                        <td>Tên</td>
						<td>ảnh đại diện</td>
						<td>Cấp</td>
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
        <%@Import Namespace="System.Data.SqlClient" %>......
                   
                        <%
                            string sql =
  "select id_loai_san_pham,  ten_loai_san_pham,  anh_loai_san_pham,  cap_do_loai_san_pham " +
  "from loai_san_pham " +
  "where id_cha_loai_san_pham=" + id_cha_loai_san_pham;

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
                            Response.Write("    <style> .class" + ds.Rows[i][3].ToString().Replace("\\", "") + ":before {    font-family: fontlsp;  font-size: 100px; content: '" + ds.Rows[i][3].ToString().Replace("u", "") + "' } </style>");
                            Response.Write(
                            "<tr> "  +
                              "<td><a href=\"../LoaiSanPham/xoa.aspx?id_loai_san_pham=" + ds.Rows[i][0].ToString() + "\">" +
                        "<img src=\"../anh/icons8-waste-20.png\" width='20' height='20'>" +
                        "</a>" +
                          "<a  style=\"margin-left:10px\" href=\"../LoaiSanPham/sua.aspx?id_loai_san_pham=" + ds.Rows[i][0].ToString() + "\">" +
                        "<img src=\"../anh/icons8-compose-20.png\" width='20' height='20'>" +
                        "</a>"+ "<a  style=\"margin-left:10px\" href=\"../LoaiSanPham/danhSachSanPham.aspx?id_loai_san_pham=" + ds.Rows[i][0].ToString() + "\">" +
                        "<img src=\"../anh/icons8-insert-table-20.png\" width='20' height='20'>" +
                        "</a>"+"</td>" +
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td>" + mH.Base64Decode(ds.Rows[i][1].ToString()) + "</td> " +
                                            "  <td> " + "<img src=" + ds.Rows[i][2].ToString() + " style=margin:10px width=auto height=100>" + "</td> " +
                                            "  <td >" + ds.Rows[i][3].ToString() + "</td> " +
                                           "   </tr> "
                                            
                                            
                                            );
                        }
                                            
                    
                     %>
				</table>
			</div>
		</div>
</asp:Content>
