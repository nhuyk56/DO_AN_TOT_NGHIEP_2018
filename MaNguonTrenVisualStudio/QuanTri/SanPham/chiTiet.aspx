<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="chiTiet.aspx.cs" Inherits="WebApplication2.QuanTri.SanPham.chiTiet" %>
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
        int id_san_pham = 0;
        connect connect;
        try
        {
            if (Request.QueryString["id_san_pham"] != null && Request.QueryString["id_san_pham"] != "")
                id_san_pham = int.Parse(Request.QueryString["id_san_pham"]);
        }
        catch (Exception a) { }
        if (id_san_pham == 0) Response.Redirect("./danhSach.aspx");
        WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
        string sql = "SELECT sp.id_san_pham, th.ten_thuong_hieu, sp.ten_san_pham, sp.anh_san_pham, cast(sp.gia_cao as int), cast(gia_ban_ra as int), cast(sp.gia_nhap_san_pham as int), sp.mo_ta_san_pham_html, sp.dac_tinh_san_pham, sp.da_co_vat, cast(FORMAT(sp.ngay_cap_nhat, 'yyyy-MM-dd') as varchar) as ngay_cap_nhat  FROM san_pham sp join thuong_hieu th on sp.id_thuong_hieu=th.id_thuong_hieu where id_san_pham=" + id_san_pham;
        connect = new connect();
        System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
        DataTable ds = new DataTable();
        new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);

        Label1.Text = ds.Rows[0][0].ToString();
        Label2.Text = mH.Base64Decode(ds.Rows[0][1].ToString());
        Label4.Text = mH.Base64Decode(ds.Rows[0][2].ToString());
        Label6.Text = String.Format("{0:n0}", int.Parse(ds.Rows[0][4].ToString())) + " vnđ";
        Label7.Text = String.Format("{0:n0}", int.Parse(ds.Rows[0][5].ToString())) + " vnđ";
        Label8.Text = String.Format("{0:n0}", int.Parse(ds.Rows[0][6].ToString())) +" vnđ";
        if (bool.Parse(ds.Rows[0][9].ToString()) == true) Label11.Text = "Đã có";
        else Label11.Text = "Chưa có";
        Label12.Text = ds.Rows[0][10].ToString();
    %>
       <div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=2 style="height: 30px">CHI TIẾT SẢN PHẨM</td>
					</tr>
     
                                    <tr>
                                        <td style="width: 281px; height: 20px">ID Sản phẩm</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:Label ID="Label1" runat="server" EnableTheming="True" Width="200px"></asp:Label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 281px; height: 20px">Tên thương hiệu</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:Label ID="Label2" runat="server" EnableTheming="True" Width="200px"></asp:Label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 281px; height: 20px">tên sản phẩm</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:Label ID="Label4" runat="server" EnableTheming="True" Width="200px"></asp:Label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 281px; height: 20px">Ảnh sản phẩm</td>
                                        <td style="width: 366px; height: 20px">
                                           <div id="anh" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 281px; height: 20px">Giá cao</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:Label ID="Label6" runat="server" EnableTheming="True" Width="200px"></asp:Label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 281px; height: 20px">Giá bán ra</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:Label ID="Label7" runat="server" EnableTheming="True" Width="200px"></asp:Label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 281px; height: 20px">Giá nhập</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:Label ID="Label8" runat="server" EnableTheming="True" Width="200px"></asp:Label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 281px; height: 20px">Mô tả</td>
                                        <td >
                                            <div id="mota" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 281px; height: 20px">Đặc tính</td>
                                        <td>
                                             <div id="dactinh" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 281px; height: 20px">VAT</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:Label ID="Label11" runat="server" EnableTheming="True" Width="200px"></asp:Label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 281px; height: 20px">Ngày cập nhật</td>
                                        <td style="width: 366px; height: 20px">
                                            <asp:Label ID="Label12" runat="server" EnableTheming="True" Width="200px"></asp:Label>
                                        </td>
                                    </tr>
                                    </table>
        <br />
              </div>
    <script>
        function b64DecodeUnicode(str) {
        return decodeURIComponent(atob(str).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
    }
        </script>
    <%Response.Write("<script>    document.getElementById(\"anh\").innerHTML = \"<img src=' " + mH.Base64Decode(ds.Rows[0][3].ToString()) + "' width='150' height='100'> \";</script>"); %>
    <%Response.Write("<script>    document.getElementById(\"mota\").innerHTML =b64DecodeUnicode(\"" + (ds.Rows[0][7].ToString()) + "\")</script>"); %>
    <%Response.Write("<script>    document.getElementById(\"dactinh\").innerHTML =b64DecodeUnicode(\"" + (ds.Rows[0][8].ToString()) + "\")</script>"); %>
</asp:Content>
