<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="sua.aspx.cs" Inherits="WebApplication2.QuanTri.SanPham.sua" %>
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
						<td colspan=1000>SỬA SẢN PHẨM</td>
					</tr>
                    </table>
           </div>
                                <br />
                                <table style="width: 711px; height: 100px;">
                                    <tr>
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">ID Sản phẩm</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:TextBox ID="TextBox0" runat="server" Enabled="False" Width="400px" ReadOnly="True"></asp:TextBox>
                                        </td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
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
                                        <td style="width: 23px; height: 20px">&nbsp;</td>
                                        <td style="width: 281px; height: 20px">&nbsp;</td>
                                        <td style="width: 366px; height: 20px">
                                            &nbsp;</td>
                                        <td style="width: 178px; height: 20px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 281px; height: 20px">Ảnh sản phẩm cũ</td>
                                        <td style="width: 366px; height: 20px">
                                            <%
                                                int id_san_pham = 0;
                                                WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                                                connect connect;
                                                try
                                                {
                                                    if (Request.QueryString["id_san_pham"] != null && Request.QueryString["id_san_pham"] != "")
                                                    {
                                                        id_san_pham = int.Parse(Request.QueryString["id_san_pham"]);
                                                    }
                                                }
                                                catch (Exception qqe) { }
                                                if (id_san_pham == 0) Response.Redirect("./danhSach.aspx");
                                                connect = new connect();
                                                System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                                                string sql = "SELECT anh_san_pham  FROM san_pham " +
                                                          "where id_san_pham=" + id_san_pham;

                                                DataTable ds = new DataTable();
                                                new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                                                if (ds == null || ds.Rows.Count == 0) Response.Redirect("./danhSach.aspx");

                                                Response.Write("<img src=' " + mH.Base64Decode(ds.Rows[0][0].ToString()) + "' width='300' height='250'>");
                                             %>
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
                                        <td style="width: 281px; height: 22px">Ảnh sản phẩm mới</td>
                                        <td style="width: 366px; height: 22px">
                                            <asp:TextBox ID="TextBox2" runat="server" Width="400px"></asp:TextBox>
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
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 281px; height: 20px">Ngày cập nhật</td>
                                        <td style="width: 366px; height: 20px; font-weight: 700;">
                                            <asp:TextBox ID="TextBox9" runat="server" Width="400px" Enabled="False" ReadOnly="True"></asp:TextBox>
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
                                            <asp:Button ID="Button1" runat="server" Width="400px" Text="Sửa" OnClick="Button1_Click" />
                                        </td>
                                        <td style="height: 20px; width: 178px"></td>
                                    </tr>
                                </table>
        <br />
              </div>
</asp:Content>

