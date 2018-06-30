<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="them.aspx.cs" Inherits="WebApplication2.QuanTri.Banner.them" %>
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
       
       <div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>THÊM BANNER QUẢNG CÁO</td>
					</tr>
                    </table>
           </div>
        <br />
        <table style="width: 711px; height: 60px;">
            <tr style="height: 20px; width: 237px; margin-top:20px;">
                <td style="height: 20px; width: 237px; ">Banner mới</td>
                <td style="height: 20px; width: 360px">
                    <asp:TextBox ID="TextBox1" runat="server" Width="360px"></asp:TextBox>
                </td>
                <td style="height: 20px; width: 237px">
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" ControlToValidate="TextBox1" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
            </tr>
            <tr>
                <td style="height: 20px; width: 237px">&nbsp;</td>
                <td style="height: 20px; width: 237px">&nbsp;</td>
                <td style="height: 20px; width: 237px">&nbsp;</td>
            </tr>
            <tr>
                <td style="height: 20px; width: 237px"></td>
                <td style="height: 20px; width: 237px">
                    <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Text="Thêm" Width="360px" />
                </td>
                <td style="height: 20px; width: 237px"></td>
            </tr>
        </table>
        <br />
        <br />

    </div>
</asp:Content>
