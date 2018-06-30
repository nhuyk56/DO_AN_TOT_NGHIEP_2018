<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="xoa.aspx.cs" Inherits="WebApplication2.QuanTri.SanPham.xoa" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <%
        int id_san_pham = -1;
        try
        {
            if (Request.QueryString["xoa"] != null && Request.QueryString["xoa"] != "")
            {
                id_san_pham = int.Parse(Request.QueryString["xoa"]);
            }
        }
        catch (Exception qqe) { }
        if (id_san_pham != -1)
        {
            string loi = "Xóa bị lỗi! Vì liên kết";
            try
            {
                WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                System.Data.SqlClient.SqlConnection connDB = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand("san_pham_xoa", connDB);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_san_pham", System.Data.SqlDbType.NVarChar).Value = id_san_pham;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                Response.Write("<script language='javascript'> alert('Xóa thành công'); window.open('" + Session["SanPham"].ToString() + "','_self', 1); </script>");
            }
            catch (Exception x)
            {
                Response.Write("<script language='javascript'> alert('" + loi + "');history.go(-1); </script>");

            }
        }
    %>
</asp:Content>
