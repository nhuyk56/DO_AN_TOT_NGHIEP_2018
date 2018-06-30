<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="inDoanhThu.aspx.cs" Inherits="WebApplication2.QuanTri.BaoCaoDoanhThu.inDoanhThu" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
       <script src="Scripts/jquery-1.4.1.min.js" type="text/javascript"></script>
<script src="Scripts/jquery.dynDateTime.min.js" type="text/javascript"></script>
<script src="Scripts/calendar-en.min.js" type="text/javascript"></script>
<link href="Styles/calendar-blue.css" rel="stylesheet" type="text/css" />
      <script>
          $(function () {
              $("#<%=TextBox1.ClientID %>").dynDateTime({
                  showsTime: false,
                  ifFormat: "%Y/%m/%d",
                  daFormat: "%l;%M %p, %e %m, %Y",
                  align: "BR",
                  electric: false,
                  singleClick: false,
                  displayArea: ".siblings('.dtcDisplayArea')",
                  button: ".next()"
              });
          });
          $(function () {
              $("#<%=TextBox2.ClientID %>").dynDateTime({
                  showsTime: false,
                  ifFormat: "%Y/%m/%d",
                  daFormat: "%l;%M %p, %e %m, %Y",
                  align: "BR",
                  electric: false,
                  singleClick: false,
                  displayArea: ".siblings('.dtcDisplayArea')",
                  button: ".next()"
              });
          });
    </script>
    <div style="margin-left:210px">
<table style="width:100%; border: 1px solid green; height: 93px;">
            <tr>
                <td colspan="2" style="width:100%;text-align: center;color: white;font-size: 22px;font-weight: bold;background:green; height:31px;">BÁO CÁO DOANH THU</td>
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 44%; padding-right:50px">&nbsp;</td>
                <td style="height: 30px; margin-left:10px"> &nbsp;</td>
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 44%; padding-right:50px">Ngày bắt đầu</td>
                <td style="height: 30px; margin-left:10px"> 
                    <asp:TextBox ID="TextBox1" runat="server" ReadOnly="True" Width="196px"></asp:TextBox>
                    <img src="calender.png" /></td>
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 44%; padding-right:50px">&nbsp;</td>
                <td style="height: 30px; margin-left:10px"> 
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator6" runat="server" ControlToValidate="TextBox1" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                </td>
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 44%; padding-right:50px">Ngày kết thúc</td>
                <td style="height: 30px; margin-left:10px"> 
                    <asp:TextBox ID="TextBox2" runat="server" ReadOnly="True" Width="196px"></asp:TextBox>
                    <img src="calender.png" /></td>
            </tr>

            <tr>
                <td style="height: 30px; text-align: right; width: 44%; padding-right:50px">&nbsp;</td>
                <td style="height: 30px; margin-left:10px"> 
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator7" runat="server" ControlToValidate="TextBox2" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                </td>
            </tr>

            <tr>
                <td style="height: 30px; text-align: right; width: 44%; padding-right:50px">&nbsp;</td>
                <td style="height: 30px; margin-left:10px"> 
                    <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Text="Thực hiện" Width="147px" />
                </td>
            </tr>

            <tr>
                <td style="height: 30px; text-align: right; width: 44%; padding-right:50px">&nbsp;</td>
                <td style="height: 30px; margin-left:10px"> &nbsp;</td>
            </tr>

</table>
        </div>
</asp:Content>
