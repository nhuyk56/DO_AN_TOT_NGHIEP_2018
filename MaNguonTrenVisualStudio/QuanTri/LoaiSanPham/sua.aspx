<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="sua.aspx.cs" Inherits="WebApplication2.QuanTri.LoaiSanPham.sua" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
        <style> 
        @font-face {
      font-family: 'fontlsp';
      src: url('loaisanpham.ttf') format('truetype');
      font-weight: normal;
      font-style: normal;
    }
            .auto-style1 {
                width: 237px;
                height: 21px;
            }
            </style>
    <div style="margin-left:203px;">
               <div id="sanphammoi">
				<table scrolling="yes">
				<%
                    WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                    int id_loai_san_pham = 0;
                    try
                    {
                        if (Request.QueryString["id_loai_san_pham"] != null && Request.QueryString["id_loai_san_pham"] != "")
                            id_loai_san_pham = int.Parse(Request.QueryString["id_loai_san_pham"]);
                    }
                    catch (Exception a) { }
                    if (id_loai_san_pham == 0) Response.Redirect("./danhsachloaisanpham.aspx");
                    System.Data.DataTable ds = new System.Data.DataTable();
                    {
                        string sql = "select id_loai_san_pham, ten_loai_san_pham, anh_loai_san_pham, bieu_tuong_loai_san_pham, cap_do_loai_san_pham, id_cha_loai_san_pham from loai_san_pham where id_loai_san_pham=" + id_loai_san_pham;
                        WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                        System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                        System.Data.SqlClient.SqlCommand lenh = new System.Data.SqlClient.SqlCommand(sql, ketnoi);
                        ketnoi.Open();
                        System.Data.SqlClient.SqlDataAdapter data = new System.Data.SqlClient.SqlDataAdapter(lenh);
                        data.Fill(ds);
                        ketnoi.Close();
                        TextBox1.Text =  mH.Base64Decode(ds.Rows[0][1].ToString());
                        
                    }
                    
                           
                                   Response.Write("    <style> .class" + ds.Rows[0][3].ToString().Replace("\\", "") + ":before {    font-family: fontlsp;  font-size: 100px; content: '" + ds.Rows[0][3].ToString().Replace("u", "") + "' } </style>");
                                    %>

					<tr class="sanphammoitheongay">
						<td colspan=1000>SỬA LOẠI SẢN PHẨM</td>
					</tr>
                    </table>
                   <br />
                  
           </div>
         <table style="width: 711px; height: 106px;">
                       <tr>
                           <td style="width: 237px; height: 21px">Tên loại sản phẩm</td>
                           <td style="width: 237px; height: 21px">
                               <asp:TextBox ID="TextBox1" runat="server" Width="229px"></asp:TextBox>
                           </td>
                           <td style="width: 237px; height: 21px">&nbsp;</td>
                       </tr>
                       <tr>
                           <td style="width: 237px; height: 21px">&nbsp;</td>
                           <td style="width: 237px; height: 21px">
                               &nbsp;</td>
                           <td style="width: 237px; height: 21px">&nbsp;</td>
                       </tr>
                       <tr>
                           <td style="width: 237px; height: 21px">Ảnh loại sản phẩm cũ</td>
                           <td style="width: 237px; height: 21px">
                               
                               <img style="width: 100px; height: 100px" src="<%Response.Write(ds.Rows[0][2].ToString()); %>" /></td>
                           <td style="width: 237px; height: 21px">&nbsp;</td>
                       </tr>
                       <tr>
                           <td style="width: 237px; height: 21px">&nbsp;</td>
                           <td style="width: 237px; height: 21px">
                               
                               &nbsp;</td>
                           <td style="width: 237px; height: 21px">&nbsp;</td>
                       </tr>
                       <tr>
                           <td style="width: 237px; height: 21px">Ảnh loại sản phẩm mới</td>
                           <td style="width: 237px; height: 21px">
                               <asp:TextBox ID="TextBox2" runat="server" Width="229px"></asp:TextBox>
                           </td>
                           <td style="width: 237px; height: 21px"></td>
                       </tr>
                       <tr>
                           <td style="width: 237px; height: 21px">&nbsp;</td>
                           <td style="width: 237px; height: 21px">
                               &nbsp;</td>
                           <td style="width: 237px; height: 21px">&nbsp;</td>
                       </tr>
                       <tr>
                           <td style="width: 237px; height: 21px">Biểu tượng loại sản phẩm cũ</td>
                           <td style="width: 237px; height: 21px">
                               <%Response.Write("<span class=class" + ds.Rows[0][3].ToString().Replace("\\", "") + "></span>"); %>
                           </td>
                           <td style="width: 237px; height: 21px"></td>
                       </tr>
                       <tr>
                           <td style="width: 237px; height: 21px">Biểu tượng loại sản phẩm mới</td>
                           <td style="width: 237px; height: 21px">
                               <asp:TextBox ID="TextBox3" runat="server" Width="229px"></asp:TextBox>
                           </td>
                           <td style="width: 237px; height: 21px">&nbsp;</td>
                       </tr>
                       <tr>
                           <td style="width: 237px; height: 21px">&nbsp;</td>
                           <td style="width: 237px; height: 21px">
                               &nbsp;</td>
                           <td style="width: 237px; height: 21px">&nbsp;</td>
                       </tr>
                       <tr>
                           <td class="auto-style1">Cấp độ</td>
                           <td class="auto-style1">
                               <asp:TextBox ID="TextBox4" runat="server" Width="229px" autopostback=true  TextMode="Number" OnTextChanged="TextBox4_TextChanged"></asp:TextBox>
                           </td>
                           <td class="auto-style1">
                                            <asp:RegularExpressionValidator ID="RegularExpressionValidator2" runat="server" ControlToValidate="TextBox4" ErrorMessage="Chỉ cho phép [0,1,2]" Font-Italic="False" ForeColor="Red" ValidationExpression="[0,1,2]"></asp:RegularExpressionValidator>
                                        </td>
                       </tr>
                       <tr>
                           <td style="width: 237px; height: 21px">&nbsp;</td>
                           <td style="width: 237px; height: 21px">
                               &nbsp;</td>
                           <td style="width: 237px; height: 21px">&nbsp;</td>
                       </tr>
                       <tr>
                           <td style="width: 237px; height: 22px">Loại sản phẩm cha</td>
                           <td style="width: 237px; height: 22px">
                               <asp:DropDownList ID="DropDownList1" runat="server" Width="229px">
                               </asp:DropDownList>
                           </td>
                           <td style="width: 237px; height: 22px">&nbsp;</td>
                       </tr>
                       <tr>
                           <td style="width: 237px; height: 22px">&nbsp;</td>
                           <td style="width: 237px; height: 22px">
                               &nbsp;</td>
                           <td style="width: 237px; height: 22px">&nbsp;</td>
                       </tr>
                       <tr>
                           <td style="width: 237px; height: 22px">&nbsp;</td>
                           <td style="width: 237px; height: 22px">
                               <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Text="Sửa" Width="225px" />
                           </td>
                           <td style="width: 237px; height: 22px">&nbsp;</td>
                       </tr>
                   </table>
    </div>
</asp:Content>
