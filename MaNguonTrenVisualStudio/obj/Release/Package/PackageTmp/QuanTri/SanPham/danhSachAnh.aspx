<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="danhSachAnh.aspx.cs" Inherits="WebApplication2.QuanTri.SanPham.danhSachAnh" %>
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
        int id_san_pham = 0;
                try
                {
                    if (Request.QueryString["id_san_pham"] != null && Request.QueryString["id_san_pham"] != "")
                        id_san_pham = int.Parse(Request.QueryString["id_san_pham"]);
                }
                catch (Exception e) { }
                if (id_san_pham == 0) Response.Redirect("./danhSach.aspx");
                connect connect;
                WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                string sql = "select sp.id_san_pham, th.ten_thuong_hieu, sp.ten_san_pham, sp.anh_san_pham, cast(sp.gia_cao as int),  cast(sp.gia_ban_ra as int), cast(sp.gia_nhap_san_pham as int),  cast(FORMAT(sp.ngay_cap_nhat, 'yyyy-MM-dd') as varchar) as ngay_cap_nhat  from san_pham sp join thuong_hieu th on sp.id_thuong_hieu=th.id_thuong_hieu " +
                    " where id_san_pham=" + id_san_pham;
                connect = new connect();
                DataTable ds = new DataTable();
                System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                 %>
           <div id="admincon">
			<div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>SẢN PHẨM</td>
					</tr>
					<tr class="tieudespmoi">
                        <td style="width:100px">Hành động</td>
						<td>ID sản phẩm</td>
                        <td>Tên tên thương hiệu</td>
						<td>Tên sản phẩm</td>
						<td>Ảnh sản phẩm</td>
                        <td>Giá cao</td>
                        <td>Giá bán ra</td>
                        <td>Giá nhập</td>
                        <td>Ngày cập nhật</td>
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
                            string edit = "<td><a href=\"../SanPham/xoa.aspx?xoa=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-waste-20.png\" width='20' height='20'>" +
                                "</a>" +
                             "<a  style=\"margin-left:10px\" href=\"../SanPham/sua.aspx?id_san_pham=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-compose-20.png\" width='20' height='20'></a>" +
                                "<a  style=\"margin-left:10px\" href=\"../SanPham/chiTiet.aspx?id_san_pham=" + ds.Rows[i][0].ToString() + "\">" +
                                "<img src=\"../anh/icons8-more-details-20.png\" width='20' height='20'></a>" +"</td>";
                            Response.Write(
                            "<tr> "  +edit+
                          
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td>" + mH.Base64Decode(ds.Rows[i][1].ToString()) + "</td> " +
                                            "  <td>" + mH.Base64Decode(ds.Rows[i][2].ToString()) + "</td> " +
                                            "  <td> <img src=' " + mH.Base64Decode(ds.Rows[i][3].ToString()) + "' width='150' height='100'></td> " +
                                            "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][4].ToString())) + " vnđ</td> " +
                                           "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][5].ToString())) + " vnđ</td> " +
                                           "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][6].ToString())) + " vnđ</td> " +
                                            "  <td>" + ds.Rows[i][7].ToString()+"</td> " +
                                           "   </tr> "
                                            );
                        }
                                            
                    
                     %>
				</table>
			    <br />
                <a href="<%
                    try { Response.Write(Session["SanPham"].ToString()); }
                    catch (Exception xz)
                    {
                        Response.Write("./danhSach.aspx");
                    }
                    %>" class="btn btn-info" style="background-color: #2de135; border-color: green;" role="button"><< Trở về</a>&nbsp;
			    <br />
                <br />
			</div>
               </div>
      <!--- Phần sản phẩm --->
      <div id="admincon">
			<div id="sanphammoi">
				<table scrolling="yes">
					<tr class="sanphammoitheongay">
						<td colspan=3>DANH SÁCH ẢNH CỦA SẢN PHẨM</td>
					</tr>
					<tr class="tieudespmoi">
                        <td style="width:100px">Hành động</td>
						<td>ID</td>
						<td>Ảnh</td>
					</tr>

                    <%
                        sql = "select id_anh, id_san_pham, duong_link_anh from danh_sach_anh_cua_san_pham where id_san_pham=" + id_san_pham;
                        connect = new connect();
                        ds = new DataTable();
                        new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                        for(int i = 0 ; i < ds.Rows.Count; i++)
                        {
                            string edit = "<td><a href=\"../SanPham/xoaAnh.aspx?id_anh=" + ds.Rows[i][0].ToString() +"\">" +
                        "<img src=\"../anh/icons8-waste-20.png\" width='20' height='20'>" +
                        "</a>"+ "</td>";
                             Response.Write(
                            "<tr> "  +
                            edit +
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td> <img src=' " + mH.Base64Decode(ds.Rows[i][2].ToString()) + "' width='150' height='100'></td> " +
                                             "   </tr> "
                                            
                                            );
                        }
                                            
                    
                     %>
				</table>
			</div>
		</div>
    <br />
     <div id="admincon">
			<div id="sanphammoi">
				<table scrolling="yes">
					<tr class="sanphammoitheongay">
						<td>THÊM ẢNH</td>
					</tr>
					<tr >
                        <td>
                            <br />
                            Đường link ảnh
                            <asp:TextBox style="margin-left:10px"  ID="TextBox1" runat="server" Width="472px"></asp:TextBox>
                            <br />
                            <br />
                            <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Text="Thêm" Width="198px" />
                            <br />
                            <br />
                        </td>
					</tr>
				</table>
			</div>
		</div>


</asp:Content>
