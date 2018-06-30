<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="them.aspx.cs" Inherits="WebApplication2.QuanTri.SanPham.them" %>
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
						<td colspan=1000>THÊM SẢN PHẨM</td>
					</tr>
                    </table>
           </div>
                                <br />
                                <table style="width: 711px; height: 100px;">
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">&nbsp;</td>
                                        <td style="width: 366px; height: 20px">
                                            </td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">Thương hiệu</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:DropDownList Width="400px" ID="DropDownList0" runat="server" >
                                            </asp:DropDownList>
                                        </td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
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
                                        <td style="width: 281px; height: 20px">Tên sản phẩm</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox1" runat="server" Width="400px"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator12" runat="server" ControlToValidate="TextBox1" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
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
                                        <td style="width: 23px; height: 22px"></td>
                                        <td style="width: 281px; height: 22px">Ảnh sản phẩm</td>
                                        <td style="width: 366px; height: 22px">
                                            <asp:TextBox ID="TextBox2" runat="server" Width="400px"></asp:TextBox>

                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator15" runat="server" ControlToValidate="TextBox2" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>

                                        </td>
                                        <td style="width: 178px; height: 22px"></td>
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
                                        <td style="width: 281px; height: 20px">Giá cao (VNĐ)</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox3" runat="server" Width="400px" TextMode="Number"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator9" runat="server" ControlToValidate="TextBox3" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 21px"></td>
                                        <td style="width: 281px; height: 21px"></td>
                                        <td style="width: 366px; height: 21px">
                                            </td>
                                        <td style="width: 178px; height: 21px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">Giá bán ra&nbsp; (VNĐ)</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox4" runat="server" Width="400px" TextMode="Number"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator10" runat="server" ControlToValidate="TextBox4" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
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
                                        <td style="width: 281px; height: 20px">Giá nhập (VNĐ)</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox5" runat="server" Width="400px" TextMode="Number"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator11" runat="server" ControlToValidate="TextBox5" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 281px; height: 20px"></td>
                                        <td style="width: 366px; height: 20px">
                                            </td>
                                        <td style="width: 178px; height: 20px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">Mô tả sản phẩm</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox6" runat="server" Width="400px" TextMode="MultiLine"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator13" runat="server" ControlToValidate="TextBox6" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
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
                                        <td style="width: 281px; height: 20px">Đặc tính sản phẩm</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox7" runat="server" Width="400px" TextMode="MultiLine"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" ControlToValidate="TextBox7" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
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
                                        <td style="width: 281px; height: 20px">Khối lượng (gram)</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox8" runat="server" Width="400px" TextMode="Number"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator14" runat="server" ControlToValidate="TextBox8" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
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
                                        <td style="width: 281px; height: 20px">VAT</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:CheckBox ID="CheckBox1" runat="server" Checked="True" Enabled="False" Text="Đã có" />
                                        </td>
                                        <td style="width: 178px; height: 20px"></td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 17px"></td>
                                        <td style="width: 281px; height: 17px"></td>
                                        <td style="width: 366px; height: 17px">
                                            </td>
                                        <td style="width: 178px; height: 17px"></td>
                                    </tr>
                                    <tr>
                                        <td style="height: 20px; width: 23px"></td>
                                        <td style="height: 20px; width: 281px"></td>
                                        <td style="height: 20px; width: 366px">
                                            <asp:Button ID="Button1" runat="server" Width="400px" Text="Thêm" OnClick="Button1_Click" />
                                        </td>
                                        <td style="height: 20px; width: 178px"></td>
                                    </tr>
                                </table>
        <br />
              </div>
</asp:Content>
