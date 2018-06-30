<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="sua.aspx.cs" Inherits="WebApplication2.QuanTri.Banner.sua" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div style="margin-left:203px;">
        
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
                int id_anh = 0;
                try
                {
                    if (Request.QueryString["id_anh"] != null && Request.QueryString["id_anh"] != "")
                        id_anh = int.Parse(Request.QueryString["id_anh"]);
                }
                catch (Exception e) { }
                if (id_anh == 0) Response.Redirect("./DanhSach.aspx");
                
                            string sql = "select id_anh, duong_link_anh from anh_trinh_chieu where id_anh=" + id_anh;
                            connect connect = new connect();
                            DataTable ds = new DataTable();
                            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                            ds = new DataTable();
                            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                   %>
       <div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>SỬA BANNER QUẢNG CÁO</td>
					</tr>
                    </table>
           </div>
        <br />
        <table style="width: 711px; height: 60px;">
            <tr>
                <td style="width: 237px; height: 20px">Banner cũ</td>
                <td style="width: 237px; height: 20px">
                    <img alt="" src="<%  Response.Write(ds.Rows[0][1].ToString());%>" width='360' height='236'/></td>
                <td style="width: 237px; height: 20px"></td>
            </tr>
             <tr>
                <td style="width: 237px; height: 20px"></td>
                 </tr>
            <tr style="height: 20px; width: 237px; margin-top:20px;">
                <td style="height: 20px; width: 237px; ">Banner mới</td>
                <td style="height: 20px; width: 360px">
                    <asp:TextBox ID="TextBox1" runat="server" Width="360px"></asp:TextBox>
                </td>
                <td style="height: 20px; width: 237px">&nbsp;</td>
            </tr>
            <tr>
                <td style="height: 20px; width: 237px">&nbsp;</td>
                <td style="height: 20px; width: 237px">&nbsp;</td>
                <td style="height: 20px; width: 237px">&nbsp;</td>
            </tr>
            <tr>
                <td style="height: 20px; width: 237px">&nbsp;</td>
                <td style="height: 20px; width: 237px">
                    <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Text="Sửa" Width="360px" />
                </td>
                <td style="height: 20px; width: 237px">&nbsp;</td>
            </tr>
        </table>
        <br />
        <br />

    </div>
</asp:Content>
