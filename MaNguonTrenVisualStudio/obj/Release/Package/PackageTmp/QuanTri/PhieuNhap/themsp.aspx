<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="themsp.aspx.cs" Inherits="WebApplication2.QuanTri.PhieuNhap.themsp" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
       <%
        string loi = "đã xảy ra lỗi";
        try
        {
            int id_phieu_nhap = 0;
            int id_san_pham = 0;
            try
            {
                if (Request.QueryString["id_phieu_nhap"] != null && Request.QueryString["id_phieu_nhap"] != "")
                    id_phieu_nhap = int.Parse(Request.QueryString["id_phieu_nhap"]);
                if (Request.QueryString["id_san_pham"] != null && Request.QueryString["id_san_pham"] != "")
                    id_san_pham = int.Parse(Request.QueryString["id_san_pham"]);
            }
            catch (Exception a) { }
            if (id_phieu_nhap == 0 || id_phieu_nhap == 0) int.Parse("a");
            WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
            
            string sql = "select id_san_pham, ten_san_pham, anh_san_pham, cast(gia_cao as int), cast(gia_ban_ra as int) FROM san_pham where id_san_pham =" + id_san_pham;
            System.Data.DataTable ds = new System.Data.DataTable();
            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
            if (ds == null || ds.Rows.Count == 0) int.Parse("a");
            
            sql = "select pnhh.id_phieu_nhap, kh.ten_kho_hang, 	   n.ten_nguoi, cast(FORMAT(pnhh.ngay_tao_phieu, 'yyyy-MM-dd') as varchar) as ngay_tao_phieu , CASE WHEN DATEDIFF(HOUR, GETDATE(),pnhh.ngay_tao_phieu)>=-24 THEN 1    ELSE 0  END  from  phieu_nhap_hang_hoa pnhh join kho_hang kh on pnhh.id_kho_hang=kh.id_kho_hang join nguoi n on n.id_nguoi=pnhh.id_nguoi where id_phieu_nhap = " + id_phieu_nhap;
            ds = new System.Data.DataTable();
            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
            if (ds == null || ds.Rows.Count == 0) int.Parse("a");
            if (int.Parse(ds.Rows[0][4].ToString()) == 0) int.Parse("a");
            WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
     %>
     <div id="admincon">
         <div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>PHIẾU NHẬP</td>
					</tr>
					<tr class="tieudespmoi">
						<td>ID phiếu nhập</td>
                        <td>Tên kho hàng</td>
						<td>Tên nhân viên tạo phiếu nhập</td>
						<td>Ngày tạo phiếu</td>
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
                        for(int i = 0 ; i < ds.Rows.Count; i++)
                        {
                            Response.Write(
                            "<tr> " +
                          
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td>" + ds.Rows[i][1].ToString() + "</td> " +
                                            "  <td> " +ds.Rows[i][2].ToString() + "</td> " +
                                            "  <td>"+ds.Rows[i][3].ToString()+"</td> "+
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
						<td colspan=1000>SẢN PHẨM CHUẨN BỊ NHẬP</td>
					</tr>
					<tr class="tieudespmoi">
						<td>ID</td>
                        <td>Tên</td>
						<td>Ảnh</td>
						<td>Giá cao</td>
						<td>Giá bán ra</td>
					</tr>

                    <%
            sql = "select id_san_pham, ten_san_pham, anh_san_pham, cast(gia_cao as int), cast(gia_ban_ra as int)  FROM san_pham where id_san_pham =" + id_san_pham;
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
                                    <td style="text-align: right; width: 386px; height: 22px">Số lượng nhập</td>
                                    <td style="height: 22px; text-align: left">
                                        <asp:TextBox style="margin-left:20px; " ID="TextBox2" runat="server" Width="184px" TextMode="Number"></asp:TextBox>
&nbsp;(cái)<asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="TextBox2" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        <asp:RangeValidator ID="RangeValidator3" runat="server" ControlToValidate="TextBox2" ErrorMessage="ít nhất 1" ForeColor="Red" MinimumValue="1" Type="Integer" MaximumValue="999999999"></asp:RangeValidator>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 386px; text-align: right; height: 21px">&nbsp;</td>
                                    <td style="text-align: left; height: 21px">
                                        &nbsp;</td>
                                </tr>
                                <tr>
                                    <td style="width: 386px; text-align: right; height: 21px">Giá nhập</td>
                                    <td style="text-align: left; height: 21px">
                                        <asp:TextBox  style="margin-left:20px; " ID="TextBox3" runat="server" Width="184px" TextMode="Number"></asp:TextBox>
                                        (vnđ)<asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ControlToValidate="TextBox3" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                                        <asp:RangeValidator ID="RangeValidator2" runat="server" ControlToValidate="TextBox3" ErrorMessage="Ít nhất 1000 vnđ " ForeColor="Red" MinimumValue="1000" Type="Integer" MaximumValue="999999999"></asp:RangeValidator>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;" colspan="2">
                                        <br />
                                        <asp:Button ID="Button1" runat="server" Height="43px" OnClick="Button1_Click" Text="Thêm" Width="149px" />
                                        <br />
                                    </td>
                                </tr>
                                </table>
         </div>






                <%
        }
        catch
        {
            Response.Write("<script language='javascript'> alert('"+loi+"');history.go(-1); </script>");
        } 
      %>
</asp:Content>
