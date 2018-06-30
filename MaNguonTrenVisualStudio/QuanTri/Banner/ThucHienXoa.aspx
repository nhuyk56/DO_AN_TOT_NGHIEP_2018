<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="ThucHienXoa.aspx.cs" Inherits="WebApplication2.QuanTri.Banner.ThucHienXoa" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    			<%
                int id_anh = 0;
                try
                {
                    if (Request.QueryString["id_anh"] != null && Request.QueryString["id_anh"] != "")
                        id_anh = int.Parse(Request.QueryString["id_anh"]);
                }
                catch (Exception e) { }
                WebApplication2.YNNSHOP56131778.CONGFIG.connect connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
                if (id_anh == 0) Response.Redirect("./DanhSach.aspx");
                System.Data.SqlClient.SqlConnection connDB = new System.Data.SqlClient.SqlConnection(connect.getconnect());
                System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand("banner_xoa", connDB);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_anh", System.Data.SqlDbType.NVarChar).Value = id_anh;
                connDB.Open();
                cmd.ExecuteNonQuery();
                connDB.Close();
                System.Data.DataTable ds = new System.Data.DataTable();
                System.Data.SqlClient.SqlDataAdapter adap = new System.Data.SqlClient.SqlDataAdapter("select id_anh, duong_link_anh from anh_trinh_chieu where id_anh=" + id_anh, connect.getconnect());
                adap.Fill(ds);
                if (ds == null || ds.Rows.Count == 0)
                    Response.Write("<script language='javascript'> alert('Xóa thành công'); window.open('./DanhSach.aspx','_self', 1); </script>");
                else Response.Write("<script language='javascript'> alert('Lỗi id ảnh không tồn tại'); window.open('./DanhSach.aspx','_self', 1); </script>"); ;
                %>
</asp:Content>
