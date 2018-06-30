<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="sua.aspx.cs" Inherits="WebApplication2.QuanTri.GiamGia.sua" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <script src="Scripts/jquery-1.4.1.min.js" type="text/javascript"></script>
<script src="Scripts/jquery.dynDateTime.min.js" type="text/javascript"></script>
<script src="Scripts/calendar-en.min.js" type="text/javascript"></script>
<link href="Styles/calendar-blue.css" rel="stylesheet" type="text/css" />
      <script>
          $(function () {
              $("#<%=TextBox4.ClientID %>").dynDateTime({
                  showsTime: true,
                  ifFormat: "%Y/%m/%d %H:%M:%S",
                  daFormat: "%l;%M %p, %e %m, %Y",
                  align: "BR",
                  electric: false,
                  singleClick: false,
                  displayArea: ".siblings('.dtcDisplayArea')",
                  button: ".next()"
              });
          });
          $(function () {
              $("#<%=TextBox5.ClientID %>").dynDateTime({
                  showsTime: true,
                  ifFormat: "%Y/%m/%d %H:%M:%S",
                  daFormat: "%l;%M %p, %e %m, %Y",
                  align: "BR",
                  electric: false,
                  singleClick: false,
                  displayArea: ".siblings('.dtcDisplayArea')",
                  button: ".next()"
              });
          });
    </script>
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
						<td colspan=1000>THÊM CHƯƠNG TRÌNH GIẢM GIÁ</td>
					</tr>
                    </table>
           </div>
                                <br />
                                <table style="width: 711px; height: 100px;">
                                    <tr>
                                        <td style="height: 20px; width: 23px"></td>
                                        <td style="height: 20px; width: 178px">Tên giảm giá</td>
                                        <td style="height: 20px; width: 465px">
                                            <asp:TextBox ID="TextBox1" runat="server" Width="274px"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="TextBox1" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="height: 20px; width: 178px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 178px; height: 20px">Mã giảm giá</td>
                                        <td style="width: 465px; height: 20px">
                                            <asp:TextBox ID="TextBox2" runat="server" Width="274px"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" ControlToValidate="TextBox2" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="width: 178px; height: 20px">
                                            <asp:RegularExpressionValidator ID="RegularExpressionValidator1" runat="server" ControlToValidate="TextBox2" ErrorMessage="GỒM 3 KÍ TỰ [A-Z] VÀ 2 KÍ TỰ [0-9]" ForeColor="Red" ValidationExpression="[A-Z]{3}[0-9]{2}"></asp:RegularExpressionValidator>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 178px; height: 20px">Lý do giảm giá</td>
                                        <td style="width: 465px; height: 20px">
                                            <asp:TextBox ID="TextBox3" runat="server" Width="274px"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" ControlToValidate="TextBox3" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="width: 178px; height: 20px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 21px"></td>
                                        <td style="width: 178px; height: 21px">Thời điểm bắt đầu</td>
                                        <td style="width: 465px; height: 21px">
                                            <asp:TextBox ID="TextBox4" runat="server"  Width="274px"  TextMode="DateTime" ReadOnly="True"></asp:TextBox>
                                            <img src="calender.png" />
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator6" runat="server" ControlToValidate="TextBox4" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="width: 178px; height: 21px">
                                            &nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 20px; width: 23px" aria-autocomplete="none">&nbsp;</td>
                                        <td style="height: 20px; width: 178px" aria-autocomplete="none">Thời điểm kết thúc</td>
                                        <td style="height: 20px; width: 465px" aria-autocomplete="none">
                                            <asp:TextBox ID="TextBox5" runat="server" Width="274px"  TextMode="DateTime" ReadOnly="True" Height="21px"></asp:TextBox>
                                            <img src="calender.png" />
                                             <asp:RequiredFieldValidator ID="RequiredFieldValidator7" runat="server" ControlToValidate="TextBox5" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="height: 20px; width: 178px" aria-autocomplete="none">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 20px; width: 23px">&nbsp;</td>
                                        <td style="height: 20px; width: 178px">&nbsp;</td>
                                        <td style="height: 20px; width: 465px">
                                            &nbsp;</td>
                                        <td style="height: 20px; width: 178px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 20px; width: 23px"></td>
                                        <td style="height: 20px; width: 178px"></td>
                                        <td style="height: 20px; width: 465px">
                                            <asp:Button ID="Button1" runat="server" Width="181px" Text="Sửa" OnClick="Button1_Click" />
                                        </td>
                                        <td style="height: 20px; width: 178px"></td>
                                    </tr>
                                </table>
        <br />
              </div>
</asp:Content>
