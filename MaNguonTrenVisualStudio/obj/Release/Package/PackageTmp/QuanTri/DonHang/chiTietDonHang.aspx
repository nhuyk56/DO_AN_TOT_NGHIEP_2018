<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="chiTietDonHang.aspx.cs" Inherits="WebApplication2.QuanTri.DonHang.chiTietDonHang" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div style="margin-left:210px">
        <%
            int id_don_hang = 0; 
                try
                {
                    if (Request.QueryString["id_don_hang"] != null && Request.QueryString["id_don_hang"] != "")
                        id_don_hang = int.Parse(Request.QueryString["id_don_hang"]);
                }
                catch (Exception e) { }
                if (id_don_hang == 0) Response.Redirect("./danhSachTTDH.aspx");  %>
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

"select " +
"dh.id_don_hang, " +
"n.ten_nguoi, " +
"ttn.ten_tinh_trang, " +
"ttdh.ten_ttdh, " +
"ptvc.ten_ptvc, " +
"kv.ht+  ', ' +dh.dia_chi_nhan_hang_chi_tiet, " +
"dh.ma_van_chuyen, " +
"cast(dh.phi_van_chuyen as int), " +
"cast(dh.phi_thu_ho as int), " +
"cast(dh.tong_tien as int), " +
"cast(FORMAT(dh.ngay_tao_don_hang, 'dd-MM-yyyy') as varchar) as ngay_tao_don_hang , " +
"cast(FORMAT(dh.ngay_xac_nhan_don_hang_thanh_cong, 'dd-MM-yyyy') as varchar) as ngay_xac_nhan_don_hang_thanh_cong , " +
"dh.so_ngay_giao_gan_nhat, " +
"dh.so_ngay_giao_xa_nhat, "+
"n.sdt_nguoi, "+
"dh.id_ttdh, "+
"n.id_nguoi " +
"from don_hang dh  " +
"join nguoi n on dh.id_nguoi=n.id_nguoi " +
"join tinh_trang_nguoi ttn on ttn.id_tinh_trang_nguoi=n.id_tinh_trang_nguoi " +
"join tinh_trang_don_hang ttdh on ttdh.id_ttdh=dh.id_ttdh " +
"join (  " +
"	select h.id_khu_vuc, t.ten_khu_vuc+  ', ' +h.ten_khu_vuc as 'ht'  " +
"	from khu_vuc h join khu_vuc t on h.id_khu_vuc_cha = t.id_khu_vuc  " +
") as kv on kv.id_khu_vuc=dh.id_khu_vuc " +
"join phuong_thuc_van_chuyen ptvc on dh.id_ptvc=ptvc.id_ptvc " +
"WHERE dh.id_don_hang=" + id_don_hang;

                            connect connect = new connect();
                            DataTable ds = new DataTable();
                            System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                            ds = new DataTable();
                            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                            int ttdh = int.Parse(ds.Rows[0][15].ToString());
                            int id_nguoi = int.Parse(ds.Rows[0][16].ToString()); 
                             %>
        <table style="width:100%; border: 1px solid green; height: 93px;">
            <tr>
                <td colspan="2" style="width:100%;text-align: center;color: white;font-size: 22px;font-weight: bold;background:green; height:31px;">CHI TIẾT ĐƠN HÀNG</td>
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 50%;padding-right:50px">ID Đơn hàng</td>
                <td style="height: 30px; margin-left:10px"><%Response.Write(ds.Rows[0][0].ToString()); %></td>
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 50%;padding-right:50px">Tên khách đặt hàng</td>
                <td style="height: 30px; margin-left:10px"><%Response.Write(ds.Rows[0][1].ToString()); %></td>
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 50%;padding-right:50px">Số điện thoại</td>
                <td style="height: 30px; "><%Response.Write(ds.Rows[0][14].ToString()); %></td>
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 50%;padding-right:50px">Tình trạng khách hàng</td>
                <td style="height: 30px; margin-left:10px"><%Response.Write(ds.Rows[0][2].ToString()); %></td>
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 50%;padding-right:50px">Tình trạng đơn hàng</td>
                <td style="height: 30px; margin-left:10px"><%Response.Write(ds.Rows[0][3].ToString()); %></td>
            </tr>
             <tr>
                <td style="height: 30px; text-align: right; width: 50%;padding-right:50px">Ngày tạo đơn hàng</td>
                <td style="height: 30px; margin-left:10px"><%Response.Write(ds.Rows[0][10].ToString()); %></td>
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 50%;padding-right:50px">Ngày xác nhận đơn hàng thành công</td>
                <td style="height: 30px; width: 280px;margin-left:10px"><%
                                                                            if (ds.Rows[0][11].ToString().Length == 0)
                                                                                Response.Write("Gọi cho khách hàng để xác nhận!");
                                                                                else
                                                                                Response.Write(ds.Rows[0][11].ToString()); %></td>
                 
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 50%;padding-right:50px">Thời gian giao dự kiến</td>
                <td style="height: 30px; width: 280px;margin-left:10px"><%
                                                           if(int.Parse(ds.Rows[0][12].ToString())
                                                               ==int.Parse(ds.Rows[0][13].ToString()))
                                                               Response.Write("chậm nhất "+ds.Rows[0][12].ToString()+" ngày");
                                                           else Response.Write("chậm nhất " + ds.Rows[0][13].ToString() + " ngày và nhanh nhất " + ds.Rows[0][12].ToString() +" ngày");
                                                           Response.Write(" (tính từ lúc xác nhận đơn hàng thành công!)");%></td>
               
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 50%;padding-right:50px">Phương thức vận chuyển</td>
                <td style="height: 30px; width: 280px;margin-left:10px"><%Response.Write(ds.Rows[0][4].ToString()); %></td>
                
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 50%;padding-right:50px">Địa chỉ giao hàng</td>
                <td style="height: 30px; width: 280px;margin-left:10px"><%Response.Write(ds.Rows[0][5].ToString()); %></td>
                
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 50%;padding-right:50px">Mã vận chuyển</td>
                <td style="height: 30px; width: 280px;margin-left:10px"><%
                                                                            if(ds.Rows[0][6].ToString().Length==0)
                                                                                Response.Write("Cập nhật sau xác nhận đơn hàng thành công");
                                                                            else Response.Write(ds.Rows[0][6].ToString()); %></td>
                
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 50%;padding-right:50px">Phí vận chuyển</td>
                <td style="height: 30px; width: 280px;margin-left:10px"><%Response.Write(String.Format(" {0:0,0}", long.Parse(ds.Rows[0][7].ToString())));%>vnđ</td>
                 
            </tr>
            <tr>
                <td style="text-align: right; width: 50%; height: 30px;padding-right:50px">Phí thu hộ</td>
                <td style="width: 280px; height: 30px;margin-left:10px"><%Response.Write(String.Format(" {0:0,0}", long.Parse(ds.Rows[0][8].ToString())));%>vnđ</td>
                <td style="width: 281px; height: 30px"></td>
            </tr>
            <tr>
                <td style="text-align: right; width: 50%; height: 30px;padding-right:50px">Tiền thu hộ</td>
                <td style="width: 280px; height: 30px;margin-left:10px"><% 
                                                                            long tam  =(long.Parse(ds.Rows[0][9].ToString()) - long.Parse(ds.Rows[0][8].ToString()) - long.Parse(ds.Rows[0][7].ToString()));
                                                                            Response.Write(String.Format(" {0:0,0}", tam));%>vnđ</td>
                <td style="width: 281px; height: 30px">&nbsp;</td>
            </tr>
            <tr>
                <td style="height: 30px; text-align: right; width: 50%;padding-right:50px">Tổng tiền thanh toán</td>
                <td style="height: 30px; width: 280px;margin-left:10px"><%Response.Write(String.Format(" {0:0,0}", long.Parse(ds.Rows[0][9].ToString())));%>vnđ</td>
                
            </tr>
                     <tr>
                <td colspan="2" style="text-align: center;
color: white;
font-size: 22px;
font-weight: bold;
background:green; height: 31px;">THAO TÁC</td>
            </tr>
            <tr>
                <td style="height: 90px; text-align: center; width: 100%"  colspan="2" >
                    <%
                        if (Session["danhSachDonHang"] == null || Session["danhSachDonHang"].ToString().Length==0)
                        Response.Write(
            " <a href='../DonHang/danhSachTTDH.aspx' class='btn btn-info' style='background-color: #2de135; border-color: green;margin: 3px;' role='button'>Trở về xem đơn hàng hàng khác</a> "
                             );
                        else Response.Write(
       " <a href='" + Session["danhSachDonHang"] .ToString()+ "' class='btn btn-info' style='background-color: #2de135; border-color: green;margin: 3px;' role='button'>Trở về xem đơn hàng hàng khác</a> "
                        );
                             
                        if (ttdh >= 1)
                            Response.Write(
             " <a href='../DonHang/inHoaDon.aspx?id_don_hang=" + id_don_hang+"' class='btn btn-info' style='background-color: #2de135; border-color: green;margin: 3px;' role='button'>In hóa đơn</a> "
                              );
                             
                        sql = "select id_ttdh, ten_ttdh from tinh_trang_don_hang where id_ttdh";
                        if (ttdh <= -2) sql += "<-1000";
                        else if (ttdh == -1) sql += "<=-2 or id_ttdh=1";
                        else if (ttdh == 0) sql += "<=-1 or id_ttdh=1";
                        else if (ttdh <=1)
                        {
                            sql += "<=-2 or id_ttdh=2"; //them ma van chuyen
                            
                            Response.Write(
" <a onclick='show()' class='btn btn-info' style='background-color: #2de135; border-color: green;margin: 3px;' role='button'>Cập nhật mã vận chuyển</a> "
                         );
                        }
                        else if (ttdh <=2)
                        {
                            sql += "<=-2 or id_ttdh=3"; //them ma van chuyen
                            
                            Response.Write(
       " <a onclick='show()' class='btn btn-info' style='background-color: #2de135; border-color: green;margin: 3px;' role='button'>Cập nhật mã vận chuyển</a> "
                        );
                        }
                        else sql += "<-1000";
                            
                        ds = new DataTable();
                        new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                 
                        for(int i = 0 ; i < ds.Rows.Count; i++)
                        {
                            Response.Write(
         " <a href='../DonHang/capNhatTinhTrangDonHang.aspx?id_ttdh=" + ds.Rows[i][0].ToString() + "&id_don_hang=" + id_don_hang + "&id_nguoi="+id_nguoi+"' class='btn btn-info' style='background-color: #2de135; border-color: green;margin: 3px;' role='button'>" + ds.Rows[i][1].ToString() + "</a> "
                          );
                        }
                                            
                    
                     %>
                </td>
            </tr>
            </table>
          </div>
      <div id="admincon">
			<div id="sanphammoi">
				<table>
				
					<tr class="sanphammoitheongay">
						<td colspan=1000>DANH SÁCH SẢN PHẨM</td>
					</tr>
					<tr class="tieudespmoi">
						<td>IDSP</td>
						<td>Tên</td>
						<td>Ảnh</td>
						<td>Mã giảm giá</td>
						<td>Số lượng</td>
                        <td>Giá sản phẩm</td>
                        <td>Giá đã giảm</td>
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
                            WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
                            sql = "select sp.id_san_pham, th.ten_thuong_hieu, sp.ten_san_pham, sp.anh_san_pham, ctdh.ma_giam_gia, ctdh.so_luong, cast(ctdh.gia_san_pham as int), cast(ctdh.gia_da_giam as int) " +
                                "from chi_tiet_don_hang ctdh join san_pham sp on ctdh.id_san_pham=sp.id_san_pham " +
                                "join thuong_hieu th on th.id_thuong_hieu=sp.id_thuong_hieu " +
                                "where id_don_hang=" + id_don_hang;
                            ds = new DataTable();
                            new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds);
                   %>
                    <%
                        for(int i = 0 ; i < ds.Rows.Count; i++)
                        {
                            string MGG = "Không";
                            if (ds.Rows[i][4].ToString().Length != 0) MGG = ds.Rows[i][4].ToString();
                            Response.Write(
                            "<tr> "  +
                                             " <td>#" + ds.Rows[i][0].ToString() + "</td> " +
                                            "  <td>" + mH.Base64Decode(ds.Rows[i][2].ToString()) + "</td> " +
                                            "  <td> <img src=' " + mH.Base64Decode(ds.Rows[i][3].ToString()) + "' width='150' height='100'></td> " +
                                             "  <td>" + MGG + "</td> " +
                                             "  <td>" + ds.Rows[i][5].ToString() + "</td> " +
                                            "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][6].ToString())) + " vnđ</td> " +
                                           "  <td>" + String.Format("{0:n0}", int.Parse(ds.Rows[i][7].ToString())) + " vnđ</td> " +
                                           "   </tr> "
                                            );
                        }
                                            
                    
                     %>
				</table>
			</div>
    <!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">NHẬP MÃ VẬN CHUYỂN CHO ĐƠN HÀNG</h4>
      </div>
      <div class="modal-body">
       
<!--End mc_embed_signup-->
 
   <!-- Begin MailChimp Signup Form -->
<form  action="/action_page.php" class="form-horizontal" role="form">
  <div class="form-group"> 
    <input type="text" placeholder="Nhập mã vận chuyển" name="MVC" class="required email form-control"  />
    </div> 
    <button type="submit" class='btn btn-info' style='width: 100%;;background-color: #2de135; border-color: green;margin: 3px;' role='button'>Cập nhật</button>
</form>
<!--End mc_embed_signup-->
      </div>
    </div>
  </div>
</div>
<script src="https://getbootstrap.com/docs/3.3/dist/js/bootstrap.min.js"></script>
    <script>
        function show() {
            $('#myModal').modal('show');
        }
    </script>
</asp:Content>
