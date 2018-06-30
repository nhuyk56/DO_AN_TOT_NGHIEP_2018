<%@ Page Title="" Language="C#" MasterPageFile="~/YNNSHOP56131778/PAGE/Site1.Master" AutoEventWireup="true" CodeBehind="category.aspx.cs" Inherits="WebApplication2.YNNSHOP56131778.PAGE.MANAGER.category" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style type="text/css">
        .auto-style3 {
            height: 23px;
        }
        .auto-style4 {
            width: 1060px;
        }
        .auto-style5 {
            height: 23px;
            width: 117px;
        }
        .auto-style8 {
            width: 117px;
        }
        .auto-style11 {
            height: 23px;
            width: 118px;
        }
        .auto-style12 {
            width: 118px;
        }
        .auto-style13 {
            width: 117px;
            height: 26px;
        }
        .auto-style14 {
            width: 118px;
            height: 26px;
        }
        .auto-style15 {
            width: 352px;
        }
        .auto-style16 {
            width: 353px;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

    <p>
        <table id="TBTHEM" class="auto-style4">
            <tr>
                <td class="auto-style3" colspan="9" style="text-align: center">THÊM CATEGORY</td>
            </tr>
            <tr>
                <td class="auto-style5"></td>
                <td class="auto-style5"></td>
                <td class="auto-style5">ID_CATEGORY</td>
                <td class="auto-style5">
                    <asp:TextBox ID="themID" runat="server"></asp:TextBox>
                </td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
            </tr>
            <tr>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">NAME_CATEGORY</td>
                <td class="auto-style8">
                    <asp:TextBox ID="ThemName" runat="server"></asp:TextBox>
                </td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style5"></td>
                <td class="auto-style5"></td>
                <td class="auto-style5">BANNER_CATEGORY</td>
                <td class="auto-style5">
                    <asp:FileUpload ID="ThemBanner" runat="server" Width="121px" />
                </td>
                <td class="auto-style11">&nbsp;</td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
            </tr>
            <tr>
                <td class="auto-style13"></td>
                <td class="auto-style13"></td>
                <td class="auto-style13">ICON_CATEGORY</td>
                <td class="auto-style13">
                    <asp:FileUpload ID="ThemIcon" runat="server" Width="121px" />
                </td>
                <td class="auto-style14">&nbsp;</td>
                <td class="auto-style14"></td>
                <td class="auto-style14"></td>
                <td class="auto-style14"></td>
                <td class="auto-style14"></td>
            </tr>
            <tr>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">LEVEL_CATEGORY</td>
                <td class="auto-style8">
                    <asp:DropDownList ID="ThemLevel" runat="server">
                    </asp:DropDownList>
                </td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style5"></td>
                <td class="auto-style5"></td>
                <td class="auto-style5">PARENTID</td>
                <td class="auto-style5">
                    <asp:DropDownList ID="ThemParentID" runat="server">
                    </asp:DropDownList>
                </td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
            </tr>
            <tr>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">
                    <asp:Button ID="Button2" runat="server" Text="Thêm" Width="131px" />
                </td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
            </tr>
        </table>
        <br />
    </p>
        <table id="TBSUA" class="auto-style4">
            <tr>
                <td class="auto-style3" colspan="9" style="text-align: center">SỬA&nbsp; CATEGORY</td>
            </tr>
            <tr>
                <td class="auto-style5"></td>
                <td class="auto-style5"></td>
                <td class="auto-style5">ID_CATEGORY</td>
                <td class="auto-style5">
                    <asp:TextBox ID="suaID" runat="server"></asp:TextBox>
                </td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
            </tr>
            <tr>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">NAME_CATEGORY</td>
                <td class="auto-style8">
                    <asp:TextBox ID="suaName" runat="server"></asp:TextBox>
                </td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style5"></td>
                <td class="auto-style5"></td>
                <td class="auto-style5">BANNER_CATEGORY</td>
                <td class="auto-style5">
                    <asp:FileUpload ID="SuaBanner" runat="server" Width="121px" />
                </td>
                <td class="auto-style11">&nbsp;</td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
            </tr>
            <tr>
                <td class="auto-style13"></td>
                <td class="auto-style13"></td>
                <td class="auto-style13">ICON_CATEGORY</td>
                <td class="auto-style13">
                    <asp:FileUpload ID="SuaIcon" runat="server" Width="121px" />
                </td>
                <td class="auto-style14">&nbsp;</td>
                <td class="auto-style14"></td>
                <td class="auto-style14"></td>
                <td class="auto-style14"></td>
                <td class="auto-style14"></td>
            </tr>
            <tr>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">LEVEL_CATEGORY</td>
                <td class="auto-style8">
                    <asp:DropDownList ID="SuaLevel" runat="server">
                    </asp:DropDownList>
                </td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style5"></td>
                <td class="auto-style5"></td>
                <td class="auto-style5">PARENTID</td>
                <td class="auto-style5">
                    <asp:DropDownList ID="SuaParentID" runat="server">
                    </asp:DropDownList>
                </td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
            </tr>
            <tr>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">&nbsp;</td>
                <td class="auto-style8">
                    <asp:Button ID="btnSua" runat="server" Text="Sửa" Width="131px" />
                </td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
                <td class="auto-style12">&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style5"></td>
                <td class="auto-style5"></td>
                <td class="auto-style5"></td>
                <td class="auto-style5"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
                <td class="auto-style11"></td>
            </tr>
        </table>
    <p>
        &nbsp;</p>
    <p style="margin-top: 19px">
        &nbsp;</p>
    <p>
        <table class="auto-style4">
            <tr>
                <td style="text-align: center" class="auto-style15">
                    &nbsp;</td>
                <td style="text-align: center" class="auto-style16">
                    DANH SÁCH CATEGORY</td>
                <td style="text-align: center" class="auto-style16">
                    &nbsp;</td>
            </tr>
            <tr>
                <td style="text-align: center" class="auto-style15">
                    &nbsp;</td>
                <td style="text-align: center" class="auto-style16">
                    <asp:GridView ID="danhsachcategory" runat="server"
                        autogenerateselectbutton="True"
                        >
                    </asp:GridView>
                </td>
                <td style="text-align: center" class="auto-style16">
                    &nbsp;</td>
            </tr>
        </table>
    </p>
        <script type="text/javascript">
         <!--
    function GetURLParameter(sParam) {
        var sPageURL = window.location.search.substring(1);
        var sURLVariables = sPageURL.split('&');
        for (var i = 0; i < sURLVariables.length; i++) {
            var sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] == sParam) {
                return sParameterName[1];
            }
        }
    }
    var them = false;
    try { them = GetURLParameter("them") } catch (Ex) { }
    if (GetURLParameter("them") != "true") document.getElementById("TBTHEM").style.display = 'none';
    var sua = false;
    try { sua = GetURLParameter("sua") } catch (Ex) { }
    if (GetURLParameter("sua") != "true") document.getElementById("TBSUA").style.display = 'none';
    //-->
    </script>
</asp:Content>
