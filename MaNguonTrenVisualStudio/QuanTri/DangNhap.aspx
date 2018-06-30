<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="DangNhap.aspx.cs" Inherits="WebApplication2.QuanTri.DangNhap" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        
    <div>
    
    </div>
    </form>
    <link rel="stylesheet" href="css/login.css" type="text/css">
<div class="body">
    <div class="tieude1">
        <div class="quantri">
            <h2>Đăng nhập quản trị</h2>
        </div>
    </div>
    <%
            if (Request.Form["email"] != null && Request.Form["matkhau"] != null)
            {
                string email = Request.Form["email"];
                string matkhau = System.Convert.ToBase64String(System.Text.Encoding.UTF8.GetBytes(Request.Form["matkhau"]));
                WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                System.Data.DataTable ds = new System.Data.DataTable();
                System.Data.SqlClient.SqlDataAdapter adap = new System.Data.SqlClient.SqlDataAdapter("DangNhap '" + email+ "','" + matkhau+"'", connect.getconnect());
                adap.Fill(ds);
                if(ds==null || ds.Rows.Count ==0)
                    Response.Write( "<p class='thongbao1'>Sai email hoặc mật khẩu!</p>");
                else
                {
                    Session["dangNhap"] = ds;
                    Response.Write("<script language='javascript'> alert('Đăng nhập quản trị thành công'); window.open('DonHang/danhSachDonHang.aspx?id_ttdh=0','_self', 1); </script>");
                }
            } 

       
      %>
    <div class="admin_login">
    <form action="" method="post">
        <label>Email:</label><input type="text" name="email" placeholder=" Địa chỉ email"><br>
        <label>Mật khẩu:</label><input type="password" name="matkhau" placeholder=" Mật khẩu"><br>
        <button name="login" class="dangnhap" style="color:#66FF66">Đăng nhập</button><button class="thoat"><a href="../index.asp.net">Thoát</a></button>
    </form>
</div>
</div>
</body>
</html>
