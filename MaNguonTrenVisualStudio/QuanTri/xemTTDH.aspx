<%@ Page Title="" Language="C#" MasterPageFile="~/QuanTri/Site1.Master" AutoEventWireup="true" CodeBehind="xemTTDH.aspx.cs" Inherits="WebApplication2.QuanTri.xemTTDH" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div style="margin-left:300px">
    <%
        WebApplication2.YNNSHOP56131778.CONGFIG.connect connect;
        WebApplication2.QuanTri.maHoa mH = new WebApplication2.QuanTri.maHoa();
        string sql = "select id_ttdh, ten_ttdh from tinh_trang_don_hang";
        connect = new WebApplication2.YNNSHOP56131778.CONGFIG.connect();
        System.Data.DataTable ds = new System.Data.DataTable();
        System.Data.SqlClient.SqlConnection ketnoi = new System.Data.SqlClient.SqlConnection(connect.getconnect());
        new System.Data.SqlClient.SqlDataAdapter(new System.Data.SqlClient.SqlCommand(sql, ketnoi)).Fill(ds); 
    for(int i = 0; i< ds.Rows.Count; i++){
        Response.Write(
         "<a href='' class='btn btn-info' style='background-color: #2de135; border-color: green;' role='button'>"+ds.Rows[i][1].ToString()+"</a>&nbsp;"+
    "<br /><br />"
        
        );
    }
   

        %>

</div>
</asp:Content>
