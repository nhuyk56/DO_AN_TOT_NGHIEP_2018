<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="xoaAnh.aspx.cs" Inherits="WebApplication2.QuanTri.SanPham.xoaAnh" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <%
        int id_anh = -1;
        try
        {
            if (Request.QueryString["id_anh"] != null && Request.QueryString["id_anh"] != "")
            {
                id_anh = int.Parse(Request.QueryString["id_anh"]);
            }
        }
        catch (Exception qqe) { }
        if (id_anh == -1) Response.Redirect("./danhSach.aspx");
        {
            string loi = "Xóa bị lỗi! Vì liên kết";
            try
            {
                WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                System.Data.SqlClient.SqlConnection connDB = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand("danh_sach_anh_cua_san_pham_xoa", connDB);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_anh", System.Data.SqlDbType.NVarChar).Value = id_anh;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('Xóa thành công'); window.open('" + Session["danhSachAnh"].ToString() + "','_self', 1); </script>");
            }
            catch (Exception x)
            {
                Response.Write("<script language='javascript'> alert('" + loi + "');history.go(-1); </script>");
            }
        }
    %>
</asp:Content>
