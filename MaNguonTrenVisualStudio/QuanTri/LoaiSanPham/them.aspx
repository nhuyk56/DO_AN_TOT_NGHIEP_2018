<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="them.aspx.cs" Inherits="WebApplication2.QuanTri.LoaiSanPham.them" %>
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
						<td colspan=1000>THÊM DANH MỤC SẢN PHẨM</td>
					</tr>
                    </table>
           </div>
                                <br />
                                <table style="width: 711px; height: 100px;">
                                    <tr>
                                        <td style="height: 20px; width: 23px"></td>
                                        <td style="height: 20px; width: 178px">Tên loại sản phẩm</td>
                                        <td style="height: 20px; width: 366px">
                                            <asp:TextBox ID="TextBox1" runat="server" Width="181px"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="TextBox1" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="height: 20px; width: 178px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 178px; height: 20px">Ảnh loại sản phẩm</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox2" runat="server" Width="181px"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" ControlToValidate="TextBox2" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="width: 178px; height: 20px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 178px; height: 20px">Biểu tượng loại sản phẩm</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox3" runat="server" Width="181px"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" ControlToValidate="TextBox3" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="width: 178px; height: 20px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 178px; height: 20px">Cấp độ loại sản phẩm</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox4" runat="server" autopostback=true Width="181px" OnTextChanged="TextBox4_TextChanged" TextMode="Number"></asp:TextBox>
                                            <asp:RegularExpressionValidator ID="RegularExpressionValidator2" runat="server" ControlToValidate="TextBox4" ErrorMessage="Chỉ cho phép [0,1,2]" Font-Italic="False" ForeColor="Red" ValidationExpression="[0,1,2]"></asp:RegularExpressionValidator>
                                        </td>
                                        <td style="width: 178px; height: 20px">
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator6" runat="server" ControlToValidate="TextBox4" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="height: 20px; width: 23px" aria-autocomplete="none">&nbsp;</td>
                                        <td style="height: 20px; width: 178px" aria-autocomplete="none">Loại sản phẩm cha</td>
                                        <td style="height: 20px; width: 366px" aria-autocomplete="none">
                                            <asp:DropDownList ID="DropDownList1"  Width="181px" runat="server">
                                            </asp:DropDownList>
                                        </td>
                                        <td style="height: 20px; width: 178px" aria-autocomplete="none">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 20px; width: 23px">&nbsp;</td>
                                        <td style="height: 20px; width: 178px">&nbsp;</td>
                                        <td style="height: 20px; width: 366px">
                                            &nbsp;</td>
                                        <td style="height: 20px; width: 178px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 20px; width: 23px"></td>
                                        <td style="height: 20px; width: 178px"></td>
                                        <td style="height: 20px; width: 366px">
                                            <asp:Button ID="Button1" runat="server" Width="181px" Text="Thêm" OnClick="Button1_Click" />
                                        </td>
                                        <td style="height: 20px; width: 178px"></td>
                                    </tr>
                                </table>
        <br />
              </div>
</asp:Content>
