<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="suasp.aspx.cs" Inherits="WebApplication2.QuanTri.GiamGia.suasp" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
     <%
        try
        {
            int id_giam_gia = 0;
            int id_san_pham = 0;
            try
            {
                if (Request.QueryString["id_giam_gia"] != null && Request.QueryString["id_giam_gia"] != "")
                    id_giam_gia = int.Parse(Request.QueryString["id_giam_gia"]);
                if (Request.QueryString["id_san_pham"] != null && Request.QueryString["id_san_pham"] != "")
                    id_san_pham = int.Parse(Request.QueryString["id_san_pham"]);
            }
            catch (Exception a) { }
            if (id_giam_gia == 0 || id_san_pham == 0) int.Parse("a");
            WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
            string sql = "select id_san_pham, ten_san_pham, anh_san_pham, cast(gia_cao as int), cast(gia_ban_ra as int), cast(gia_nhap_san_pham as int) FROM san_pham where id_san_pham =" + id_san_pham;
            System.Data.DataTable ds = new System.Data.DataTable();
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
            if (ds == null || ds.Rows.Count == 0) int.Parse("a");
            sql = "select id_giam_gia, ma_giam_gia, ten_giam_gia, ly_do_giam_gia, cast(FORMAT(thoi_diem_bat_dau_giam_gia, 'yyyy/MM/dd HH:mm:ss')as varchar), cast(FORMAT(thoi_diem_ket_thuc_giam_gia, 'yyyy/MM/dd HH:mm:ss')as varchar) from giam_gia where id_giam_gia=" + id_giam_gia;
            ds = new System.Data.DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
            if (ds == null || ds.Rows.Count == 0) int.Parse("a");
            WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
     %>
     <div id="admincon">
         <div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>CHƯƠNG TRÌNH GIẢM GIÁ</td>
					</tr>
					<tr class="tieudespmoi">
						<td>ID</td>
                        <td>Mã giảm giá</td>
						<td>Tên chương trình giảm giá</td>
						<td>Lý do</td>
						<td>Thời điểm bắt đầu</td>
						<td>Thời điểm kết thúc</td>
					</tr>
					
					
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
            for (int i = 0; i < ds.Rows.Count; i++)
            {
                Response.Write(
               "<tr> " +
                                " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                               "  <td>" + ds.Rows[i][1].ToString() + "</td> " +
                               "  <td> " + ds.Rows[i][2].ToString() + "</td> " +
                               "  <td>" + ds.Rows[i][3].ToString() + "</td> " +
                               "  <td >" + ds.Rows[i][4].ToString() + "</td> " +
                               "   <td >" + ds.Rows[i][5].ToString() + "</td " +
                              "   </tr> "
                               );
            }
                                            
                    
                     %>
				</table>
			</div>
		</div>
      <div id="admincon">
			<div id="sanphammoi">
				<table scrolling="yes">
					<tr class="sanphammoitheongay">
						<td colspan=1000>SẢN PHẨM CHUẨN BỊ THÊM VÀO CHƯƠNG TRÌNH GIẢM GIÁ</td>
					</tr>
					<tr class="tieudespmoi">
						<td>ID</td>
                        <td>Tên</td>
						<td>Ảnh</td>
						<td>Giá cao</td>
						<td>Giá bán ra</td>
						<td>Giá nhập</td>
					</tr>

                    <%
            sql = "select id_san_pham, ten_san_pham, anh_san_pham, cast(gia_cao as int), cast(gia_ban_ra as int), cast(gia_nhap_san_pham as int) FROM san_pham where id_san_pham =" + id_san_pham;
            connect = new connect();
            ds = new DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
            for (int i = 0; i < ds.Rows.Count; i++)
            {
                Response.Write(
               "<tr> " +
               " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                               "  <td>" + mH.Base64Decode(ds.Rows[i][1].ToString()) + "</td> " +
                               "  <td> <img src=' " + mH.Base64Decode(ds.Rows[i][2].ToString()) + "' width='150' height='100'></td> " +
                               "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][3].ToString())) + " vnđ</td> " +
                              "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][4].ToString())) + " vnđ</td> " +
                               "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][5].ToString())) + " vnđ</td> " +
                              "   </tr> "

                               );
            }
                                            
                    
                     %>
				</table>
</div></div>
     <div id="admincon">
         <div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td>THÔNG TIN THÊM</td>
					</tr>
                    </table>
					</div>
         <table style="width:100%; border: 1px solid white;">
                                <tr>
                                    <td style="text-align: right; width: 386px; height: 22px">&nbsp;</td>
                                    <td style="height: 22px; text-align: left">
                                        &nbsp;</td>
                                </tr>
                                <tr>
                                    <td style="text-align: right; width: 386px; height: 22px">Phần trăm giảm</td>
                                    <td style="height: 22px; text-align: left">
                                        <asp:TextBox style="margin-left:20px; " ID="TextBox2" runat="server" Width="184px" TextMode="Number"></asp:TextBox>
&nbsp;(%)<asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="TextBox2" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        <asp:RangeValidator ID="RangeValidator3" runat="server" ControlToValidate="TextBox2" ErrorMessage="ít nhất 3% nhiều nhất 15%" ForeColor="Red" MaximumValue="15.0" MinimumValue="3.0" Type="Double"></asp:RangeValidator>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 386px; text-align: right; height: 21px">&nbsp;</td>
                                    <td style="text-align: left; height: 21px">
                                        &nbsp;</td>
                                </tr>
                                <tr>
                                    <td style="width: 386px; text-align: right; height: 21px">Giảm tối đa</td>
                                    <td style="text-align: left; height: 21px">
                                        <asp:TextBox  style="margin-left:20px; " ID="TextBox3" runat="server" Width="184px" TextMode="Number"></asp:TextBox>
                                        (vnđ)<asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ControlToValidate="TextBox3" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        <asp:RangeValidator ID="RangeValidator2" runat="server" ControlToValidate="TextBox3" ErrorMessage="Ít nhất 10.000 vnđ nhiều nhất 200.000vnđ" ForeColor="Red" MaximumValue="200000" MinimumValue="10000" Type="Double"></asp:RangeValidator>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;" colspan="2">
                                        <br />
                                        <asp:Button ID="Button1" runat="server" Height="43px" OnClick="Button1_Click" Text="Sửa" Width="149px" />
                                        <br />
                                    </td>
                                </tr>
                                </table>
         </div>






                <%
        }
        catch
        {
            Response.Write("<script language='javascript'> alert('Đã xảy ra lỗi');history.go(-1); </script>");
        } %>
</asp:Content>
