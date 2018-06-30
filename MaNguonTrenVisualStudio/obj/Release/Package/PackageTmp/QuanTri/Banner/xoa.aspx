<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="xoa.aspx.cs" Inherits="WebApplication2.QuanTri.Banner.xoa" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
       <div id="admincon" >
			<%
                int id_anh = 0;
                try
                {
                    if (Request.QueryString["id_anh"] != null && Request.QueryString["id_anh"] != "")
                        id_anh = int.Parse(Request.QueryString["id_anh"]);
                }
                catch (Exception e) { }
                if (id_anh == 0) Response.Redirect("./DanhSach.aspx");
                %>
			<div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>XÓA BANNER QUẢNG CÁO</td>
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
                            string sql = "select id_anh, duong_link_anh from anh_trinh_chieu where id_anh=" + id_anh;
                            connect connect = new connect();
                            DataTable ds = new DataTable();
                            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                            ds = new DataTable();
                            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                   %>
                    <%
                        WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                        
                        {
                            Response.Write(
                            "<tr> "  +
                            "  <td> " + "<img src=" + ds.Rows[0][1].ToString() + " width='360' height='236'></td> " +
                                            "   </tr> "+
                                            "<tr> "  +
                            "  <td> Bạn có thật sự muốn xóa ảnh này?</td> " +
                                            "   </tr> "+
                                             "<tr> "  +
                            "  <td><a href=\"./ThucHienXoa.aspx?id_anh="+id_anh+ "\""+ " class=\"btn btn-primary btn-lg active\" role=\"button\" aria-pressed=\"true\">Xóa ảnh này</button></td> " +
                                            "   </tr> "
                                             
                                            );
                        }
                                            
                    
                     %>
				</table>
			</div>
		</div>
</asp:Content>
