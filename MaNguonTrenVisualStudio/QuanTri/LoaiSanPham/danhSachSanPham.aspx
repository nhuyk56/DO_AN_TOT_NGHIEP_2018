<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="danhSachSanPham.aspx.cs" Inherits="WebApplication2.QuanTri.LoaiSanPham.danhSachSanPham" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
        <style> 
        @font-face {
      font-family: 'fontlsp';
      src: url('loaisanpham.ttf') format('truetype');
      font-weight: normal;
      font-style: normal;
    }
        </style>
       </style>
       <style type="text/css">
        .pagination {
padding: 20px;
margin-bottom: 20px;
}
table, td, th {
    border: 1px solid black;
}

table {
    border-collapse: collapse;
    width: 100%;
}

th {
    text-align: left;
}
.page {
    display: inline-block;
    color: #717171;
    padding: 0px 9px;
    margin-right: 1px;
    border-radius: 3px;
    border: solid 1px #c0c0c0;
    background: #e9e9e9;
    font-size:15px;
    text-decoration: none;
}
.page:hover, .page.gradient:hover {
    background: #595959;
    box-shadow: inset 0px 0px 8px rgba(0,0,0, .5), 0px 1px 0px rgba(255,255,255, .8);
    text-shadow: 0px 0px 3px rgba(0,0,0, .5);
    color: #f0f0f0;
}
 
.page.active {
    border: none;
    background: #616161;
    box-shadow: inset 0px 0px 8px rgba(0,0,0, .5), 0px 1px 0px rgba(255,255,255, .8);
    color: #f0f0f0;
    text-shadow: 0px 0px 3px rgba(0,0,0, .5);
}
    </style>
     <div id="admincon">
			
			<div id="sanphammoi">
				<table scrolling="yes">
				 <%
                            int id_loai_san_pham = -1;
                            try
                            {
                                if (Request.QueryString["id_loai_san_pham"] != null && Request.QueryString["id_loai_san_pham"] != "")
                                    id_loai_san_pham = int.Parse(Request.QueryString["id_loai_san_pham"]);
                            }
                            catch (Exception e) { }
                            if (id_loai_san_pham == -1) if (Session["LoaiSanPham"]!=null) Response.Redirect(Session["LoaiSanPham"].ToString());
                                else Response.Redirect("danhsachloaisanpham.aspx");
                   %>
					<tr class="sanphammoitheongay">
						<td colspan=1000>DANH MỤC</td>
					</tr>
					<tr class="tieudespmoi">
                        <td style="width:100px">Hành động</td>
						<td>ID</td>
                        <td>Tên</td>
						<td>Cấp</td>
						<td>Số con</td>
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
                            string sql =
  "SELECT lsp.id_loai_san_pham,  lsp.ten_loai_san_pham, lsp.cap_do_loai_san_pham,  sl.sl "
  + "FROM loai_san_pham lsp "
  + "join  "
  + "( "
  + "select c0.id_loai_san_pham, count(c1.id_loai_san_pham) as sl "
  + "from loai_san_pham c0 left join loai_san_pham c1 on c0.id_loai_san_pham=c1.id_cha_loai_san_pham "
  + "group by c0.id_loai_san_pham "
  + ") sl on lsp.id_loai_san_pham=sl.id_loai_san_pham "
  + "WHERE lsp.id_loai_san_pham="+ id_loai_san_pham;

                            connect connect = new connect();
                            DataTable ds = new DataTable();
                            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                            ds = new DataTable();
                            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                   %>
                    <%
                        WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                        for(int i = 0 ; i < ds.Rows.Count; i++)
                        {
                            Response.Write(
                            "<tr> "  +
                             "<td><a href=\"../LoaiSanPham/xoa.aspx?id_loai_san_pham=" + ds.Rows[i][0].ToString() + "\">" +
                        "<img src=\"../anh/icons8-waste-20.png\" width='20' height='20'>" +
                        "</a>" +
                          "<a  style=\"margin-left:10px\" href=\"../LoaiSanPham/sua.aspx?id_loai_san_pham=" + ds.Rows[i][0].ToString() + "\">" +
                        "<img src=\"../anh/icons8-compose-20.png\" width='20' height='20'>" +
                        "</a>"+"</td>" +
                                            "   <td >#" + ds.Rows[i][0].ToString() + " </td> " +
                                            "   <td >" + mH.Base64Decode(ds.Rows[i][1].ToString()) + " </td> " +
                                            "   <td >" + ds.Rows[i][2].ToString() + " </td> " +
                                            "   <td ><a href=./danhsachloaisanpham2.aspx?id_loai_san_pham=" + ds.Rows[i][0].ToString() + ">" + ds.Rows[i][3].ToString() + "</a></td " +
                                           "   </tr> "
                                            
                                            
                                            );
                        }
                                            
                    
                     %>
				</table>
                 <br />
                <a href="<%
                    try { Response.Write(Session["LoaiSanPham"].ToString()); }
                    catch (Exception xz)
                    {
                        Response.Write("./danhsachloaisanpham.aspx");
                    }
                    %>" class="btn btn-info" style="background-color: #2de135; border-color: green;" role="button"><< Trở về</a>&nbsp;
			    <br />
			</div>
		</div>
     <%
                int trang = 0;
                try
                {
                    if (Request.QueryString["trang"] != null && Request.QueryString["trang"] != "")
                        trang = int.Parse(Request.QueryString["trang"]);
                }
                catch (Exception e) { }
                sql = "select sp.id_san_pham, th.ten_thuong_hieu, sp.ten_san_pham, sp.anh_san_pham, cast(sp.gia_cao as int), cast(sp.gia_ban_ra as int), cast(sp.gia_nhap_san_pham as int)" +
                        " from danh_sach_loai_san_pham_cua_san_pham lsp "+
                        " join san_pham sp on sp.id_san_pham=lsp.id_san_pham  "+
                        " join thuong_hieu th on th.id_thuong_hieu=sp.id_thuong_hieu "+
                        " where id_loai_san_pham="+ id_loai_san_pham+
                        " order by id_san_pham desc" +
                    "   offset " + trang * 20 + " rows " +
                    "   fetch next 20 rows only ";
                    ;
                connect = new connect();
                ds = new DataTable();
                new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                 %>
    <br />
           <div id="admincon">
			<div id="sanphammoi">
				<table scrolling="yes">
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>DANH SÁCH SẢN PHẨM</td>
					</tr>
					<tr class="tieudespmoi">
                        <td style="width:100px">Hành động</td>
						<td>ID sản phẩm</td>
                        <td>Tên tên thương hiệu</td>
						<td>Tên sản phẩm</td>
						<td>Ảnh sản phẩm</td>
                        <td>Giá cao</td>
                        <td>Giá bán ra</td>
                        <td>Giá nhập</td>
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
                            string edit = "<td><a href=\"../LoaiSanPham/xoasp.aspx?id_san_pham=" + ds.Rows[i][0].ToString() + "&id_loai_san_pham=" + id_loai_san_pham + "\">" +
                                "<img src=\"../anh/icons8-waste-20.png\" width='20' height='20'>" +
                                "</a>" +"</td>";
                            Response.Write(
                            "<tr> "  +
                            edit+
                          
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td>" + mH.Base64Decode(ds.Rows[i][1].ToString()) + "</td> " +
                                            "  <td>" + mH.Base64Decode(ds.Rows[i][2].ToString()) + "</td> " +
                                            "  <td> <img src=' " + mH.Base64Decode(ds.Rows[i][3].ToString()) + "' width='150' height='100'></td> " +
                                            "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][4].ToString())) + " vnđ</td> " +
                                           "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][5].ToString())) + " vnđ</td> " +
                                           "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][6].ToString())) + " vnđ</td> " +
                                           "   </tr> "
                                            );
                        }
                                            
                    
                     %>
				</table>
			</div>
                <div class="pagination">
        <%
            {

                string page = WebApplication2.QuanTri.maHoa.RemoveQueryStringByKey(
                    System.Web.HttpContext.Current.Request.Url.AbsoluteUri, "trang");
                sql = "select count(*) from danh_sach_loai_san_pham_cua_san_pham where id_loai_san_pham="+id_loai_san_pham;
             DataTable t = new DataTable();
             new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(t);
             int max = int.Parse(t.Rows[0][0].ToString());
             if (max % 20 != 0) max = ((int)max / 20) + 1; else max =(int) max / 20;
             Response.Write("Tổng " + max + " trang | ");
             if(trang > 3 && trang < max - 3)
             {
                 int i = 0;
                 Response.Write("<a href='" + page + "&trang="+0+"' class='page'>Đẩu</a>");
                 for(i = trang - 3; i < trang + 3; i++)
                 {
                     if(i==trang)
                         Response.Write("<a href='#' class='page active'>" + i + "</a>");
                     else
                         Response.Write("<a href='" + page + "&trang=" + i + "' class='page'>" + i + "</a>");
                 }
                 if(i < max)
                     Response.Write("<a href='" + page + "&trang=" + (max -1)+ "' class='page'>Cuối</a>");
             }
             else if (trang <= 3)
             {
                 int i = 0;
                 for (i = 0; i < 6 && i < max; i++)
                 {
                     if (i == trang)
                         Response.Write("<a href='#' class='page active'>" + i + "</a>");
                     else
                         Response.Write("<a href='" + page + "&trang=" + i + "' class='page'>" + i + "</a>");
                 }
                 if (i < max)
                     Response.Write("<a href='" + page + "&trang=" + (max - 1) + "' class='page'>Cuối</a>");
             }
             else if (trang >= max - 3)
             {
                 Response.Write("<a href='" + page + "&trang=" + 0 + "' class='page'>Đẩu</a>");
                 int i = 0;
                 for (i = trang - 3; i <= max-1; i++)
                 {
                     if (i == trang)
                         Response.Write("<a href='#' class='page active'>" + i + "</a>");
                     else
                         Response.Write("<a href='" + page + "&trang=" + i + "' class='page'>" + i + "</a>");
                 }
             }
            }
        %>
</div>
			</div>
		    <br />
     <div id="admincon">
			<div id="sanphammoi">
				<table scrolling="yes">
					<tr class="sanphammoitheongay">
						<td>THÊM SẢN PHẨM VÀO DANH MỤC</td>
					</tr>
					<tr >
                        <td>
                            <br />
                            Nhập id sản phẩm
                            <asp:TextBox style="margin-left:10px"  ID="TextBox1" runat="server" Width="472px" TextMode="Number"></asp:TextBox>
                            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="TextBox1" ErrorMessage="Không rỗng" ForeColor="Red"></asp:RequiredFieldValidator>
                            <br />
                            <br />
                            (Vào danh sách sản phẩm lấy id sản phẩm rồi nhập vào)<br />
                            <br />
                            <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Text="Thêm" Width="198px" />
                            <br />
                            <br />
                        </td>
					</tr>
				</table>
			</div>
		</div>

</asp:Content>
