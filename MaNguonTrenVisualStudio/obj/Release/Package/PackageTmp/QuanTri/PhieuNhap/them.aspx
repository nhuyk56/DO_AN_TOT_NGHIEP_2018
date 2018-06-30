<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="them.aspx.cs" Inherits="WebApplication2.QuanTri.PhieuNhap.them" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
       <script src="Scripts/jquery-1.4.1.min.js" type="text/javascript"></script>
<script src="Scripts/jquery.dynDateTime.min.js" type="text/javascript"></script>
<script src="Scripts/calendar-en.min.js" type="text/javascript"></script>
<link href="Styles/calendar-blue.css" rel="stylesheet" type="text/css" />
     
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
						<td colspan=1000>THÊM PHIẾU NHẬP</td>
					</tr>
                    </table>
           </div>
                                <br />
                                <table style="width: 711px; height: 100px;">
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 178px; height: 20px">Nhập vào kho</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:DropDownList Width="200px" ID="DropDownList1" runat="server">
                                            </asp:DropDownList>
                                        </td>
                                        <td style="width: 178px; height: 20px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                        <td style="width: 366px; height: 20px">
                                            &nbsp;</td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 178px; height: 20px">Mô tả nguồn nhập</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox3" runat="server" Width="200px" TextMode="MultiLine"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" ControlToValidate="TextBox3" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                        <td style="width: 366px; height: 20px">
                                            &nbsp;</td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 20px; width: 23px"></td>
                                        <td style="height: 20px; width: 178px"></td>
                                        <td style="height: 20px; width: 366px">
                                            <asp:Button ID="Button1" runat="server" Width="200px" Text="Thêm" OnClick="Button1_Click" />
                                        </td>
                                        <td style="height: 20px; width: 178px"></td>
                                    </tr>
                                </table>
        <br />
              </div>
</asp:Content>
