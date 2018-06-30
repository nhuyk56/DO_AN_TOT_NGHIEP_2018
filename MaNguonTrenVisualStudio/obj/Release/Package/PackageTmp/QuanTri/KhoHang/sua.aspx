<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="sua.aspx.cs" Inherits="WebApplication2.QuanTri.KhoHang.sua" %>
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
						<td colspan=1000>SỬA KHO HÀNG</td>
					</tr>
                    </table>
           </div>
                                <br />
                                <table style="width: 711px; height: 100px;">
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">ID phiếu nhập</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox6" runat="server" Enabled="False" Width="200px" ReadOnly="True"></asp:TextBox>
                                        </td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">&nbsp;</td>
                                        <td style="width: 366px; height: 20px">
                                            &nbsp;</td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">Tên kho hàng</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox7" runat="server" Width="200px"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator6" runat="server" ControlToValidate="TextBox7" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">&nbsp;</td>
                                        <td style="width: 366px; height: 20px">
                                            &nbsp;</td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 281px; height: 20px">Tỉnh/Phố</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:DropDownList Width="200px" ID="DropDownList2" runat="server" AutoPostBack="true" OnSelectedIndexChanged="DropDownList2_SelectedIndexChanged">
                                            </asp:DropDownList>
                                        </td>
                                        <td style="width: 178px; height: 20px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">&nbsp;</td>
                                        <td style="width: 366px; height: 20px">
                                            &nbsp;</td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 281px; height: 20px">Quận/Huyện</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:DropDownList Width="200px" ID="DropDownList1" runat="server">
                                            </asp:DropDownList>
                                        </td>
                                        <td style="width: 178px; height: 20px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">&nbsp;</td>
                                        <td style="width: 366px; height: 20px">
                                            &nbsp;</td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 281px; height: 20px">Số nhà, Tên đường, Phường xã</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox3" runat="server" Width="200px" TextMode="MultiLine"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" ControlToValidate="TextBox3" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">&nbsp;</td>
                                        <td style="width: 366px; height: 20px">
                                            &nbsp;</td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 281px; height: 20px">Diện tích kho (m2)</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox8" runat="server" Width="200px" TextMode="Number"></asp:TextBox>
                                        </td>
                                        <td style="width: 178px; height: 20px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 281px; height: 20px"></td>
                                        <td style="width: 366px; height: 20px">
                                            </td>
                                        <td style="width: 178px; height: 20px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 281px; height: 20px">Ngày tạo kho</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox9" runat="server" Enabled="False" Width="200px" ReadOnly="True"></asp:TextBox>
                                        </td>
                                        <td style="width: 178px; height: 20px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">&nbsp;</td>
                                        <td style="width: 366px; height: 20px">
                                            &nbsp;</td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 281px; height: 20px">Ghi chú</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox10" runat="server" Width="200px"></asp:TextBox>
                                        </td>
                                        <td style="width: 178px; height: 20px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">&nbsp;</td>
                                        <td style="width: 366px; height: 20px">
                                            &nbsp;</td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 20px; width: 23px"></td>
                                        <td style="height: 20px; width: 281px"></td>
                                        <td style="height: 20px; width: 366px">
                                            <asp:Button ID="Button1" runat="server" Width="200px" Text="Sửa" OnClick="Button1_Click" />
                                        </td>
                                        <td style="height: 20px; width: 178px"></td>
                                    </tr>
                                </table>
        <br />
              </div>
</asp:Content>
