<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="sua.aspx.cs" Inherits="WebApplication2.QuanTri.ThuongHieu.sua" %>
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
             int id_thuong_hieu = 0;
                try
                {
                    if (Request.QueryString["id_thuong_hieu"] != null && Request.QueryString["id_thuong_hieu"] != "")
                        id_thuong_hieu = int.Parse(Request.QueryString["id_thuong_hieu"]);
                }
                catch (Exception e) { }
                if (id_thuong_hieu == 0) Response.Redirect("./danhSach.aspx");
                WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                string sql = "select id_thuong_hieu, ten_thuong_hieu, anh_thuong_hieu from thuong_hieu where id_thuong_hieu=" + id_thuong_hieu;
                            connect connect = new connect();
                            DataTable ds = new DataTable();
                            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                            ds = new DataTable();
                            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                            TextBox1.Text =  mH.Base64Decode(ds.Rows[0][1].ToString());
                            string anh = "http://denrakaev.com/wp-content/uploads/2015/03/no-image.png";
                            if (ds.Rows[0][2].ToString() != "" && ds.Rows[0][2].ToString() != null)
                                anh = mH.Base64Decode(ds.Rows[0][2].ToString());
                            %>
       <div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>&nbsp;SỬA THƯƠNG HIỆU</td>
					</tr>
                    </table>
           </div>
                                <br />
                                <table style="width: 711px; height: 100px;">
                                    <tr>
                                        <td style="height: 20px; width: 23px"></td>
                                        <td style="height: 20px; width: 178px">Tên thương hiệu</td>
                                        <td style="height: 20px; width: 366px">
                                            <asp:TextBox ID="TextBox1" runat="server" Width="181px"></asp:TextBox>
                                            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="TextBox1" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        </td>
                                        <td style="height: 20px; width: 178px"></td>
                                    </tr>
                                    <tr>
                                        <td style="height: 20px; width: 23px">&nbsp;</td>
                                        <td style="height: 20px; width: 178px">&nbsp;</td>
                                        <td style="height: 20px; width: 366px">
                                            &nbsp;</td>
                                        <td style="height: 20px; width: 178px">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 23px; height: 20px"></td>
                                        <td style="width: 178px; height: 20px">Ảnh thương hiệu cũ</td>
                                        <td style="width: 366px; height: 20px">
                                            <img alt=""style="width: 100px; height: 100px" src="<%Response.Write(anh); %>" /></td>
                                        <td style="width: 178px; height: 20px"></td>
                                    </tr>
                                    <tr>
                                        <td style="height: 21px; width: 23px"></td>
                                        <td style="height: 21px; width: 178px"></td>
                                        <td style="height: 21px; width: 366px">
                                            </td>
                                        <td style="height: 21px; width: 178px"></td>
                                    </tr>
                                    <tr>
                                        <td style="height: 20px; width: 23px">&nbsp;</td>
                                        <td style="height: 20px; width: 178px">Ảnh thương hiệu mới</td>
                                        <td style="height: 20px; width: 366px">
                                            <asp:TextBox ID="TextBox2" runat="server" Width="181px"></asp:TextBox>
                                            </td>
                                        <td style="height: 20px; width: 178px">&nbsp;</td>
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
                                            <asp:Button ID="Button1" runat="server" Width="181px" Text="Sửa" OnClick="Button1_Click" />
                                        </td>
                                        <td style="height: 20px; width: 178px"></td>
                                    </tr>
                                </table>
        <br />
              </div>
</asp:Content>
