<%-- 
    Document   : calculator
    Created on : Mar 31, 2021, 10:29:33 PM
    Author     : nsqa
--%>

<%@page import="model.user"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="model.ElectricityPrice"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/calculator.css" type="text/css">
        <title>Tính tiền điện</title>
    </head>

    <body>
        <div class="nav-bar flex fl-middle">
            <a href="<%=request.getContextPath()%>/">
                <img class="logo-header" src="assets/logo.png" alt="logo">
            </a>
            <ul class="nav-list flex fl-middle">
                <li class="nav-list__item">
                    <a class="tab-button" href="<%=request.getContextPath()%>/">Trang chủ</a>
                </li>
                <li class="nav-list__item">
                    <a class="tab-button active" href="<%=request.getContextPath()%>/calculator">Tính tiền điện</a>
                </li>
                
                <li class="nav-list__item">
                    <a class="tab-button" href="#">Tin tức</a>
                </li>
                <li class="nav-list__item">
                    <a class="tab-button" href="#">Liên hệ</a>
                </li>
                <% user u = (user) request.getSession().getAttribute("user");%>
                <div class="btn__header">
                    <li class="nav-list__item greet flex fl-middle">
                        <div>Xin chào</div>
                        <div class="dropdown">
                            <button class="dropbtn"><%= u != null ? u.getFullname() : "N/A"%> 
                                <i class="fa fa-caret-down"></i>
                            </button>
                            <div class="dropdown-content">
                                <form action="logout" method="get"><button class="tab-button" href="#">Đăng xuất</button></form>
                            </div>
                        </div>
                    </li>
                </div>
            </ul>
        </div>
        <div class="tab">
            <button id="<%=request.getParameter("type") == null || request.getParameter("type").equals("1") ? "defaultOpen" : ""%>" class="tablinks" onclick="openTab(event, 'urban')">Thành phố</button>
            <button id="<%=request.getParameter("type") != null && request.getParameter("type").equals("2") ? "defaultOpen" : ""%>" class="tablinks" onclick="openTab(event, 'rural')">Nông thôn</button>
            <!--<form action="<%= request.getContextPath()%>/calculator" method="post">
                <input type="number" name="type" value="1" hidden>
                
            </form>
            <form action="<%= request.getContextPath()%>/calculator" method="post">
                
                <input type="number" name="type" value="2" hidden>
            </form>
            --> 

        </div>

        <!-- Tab content -->
        <div id="urban" class="tabcontent">
            <div class="header orange">Nhập các thông số</div>
            <form action="<%= request.getContextPath()%>/calculator" method="post">
                <div class="metrics flex ">
                    <input type="number" name="type" value="1" hidden>
                    <table class="metric-table table-margin">
                        <!--                    <tr>
                                                <td class="td-width-large">
                                                    <label>Từ ngày - đến ngày</label>
                                                </td>
                                                <td class="td-width">
                                                    <input class="date-picker" type="date" name="start" id="start-urban">
                        
                                                </td>
                                                <td class="td-width"><input class="date-picker" type="date" name="end" id="end-urban"></td>
                                            </tr>-->
                        <tr>
                            <td class="td-width-large"><label for="consumed-urban">Tổng điện năng tiêu thụ (kWh)</label></td>
                            <td><input type="number" min="0" name="consumed-urban" required value="<%=request.getParameter("consumed-urban") != null ? request.getParameter("consumed-urban") : 0%>" id="consumed-urban"></td>

                        </tr>
                        <tr>
                            <td></td>
                            <td><input class="btn" type="submit" value="Tính toán"></td>
                        </tr>
                    </table>

                </div>
            </form>
            <%
                ArrayList<ElectricityPrice> prices = (ArrayList<ElectricityPrice>) session.getAttribute("prices");
            %>
            <div class="header orange">Kết quả</div>
            <div class="result flex ">
                <table class="result-table result-table-margin bordered">
                    <tr>
                        <th></th>
                        <th>ĐƠN GIÁ THEO QĐ 648/QĐ-BCT (đồng/kWh)</th>
                        <th>SẢN LƯỢNG (kWh)</th>
                        <th>THÀNH TIỀN (đồng)</th>
                    </tr>
                    <%
                        ArrayList<Integer> amountsUrban = (ArrayList<Integer>) session.getAttribute("amounts-urban");
                        String totalUrbanString = "";
                        String vatUrbanString = "";
                        String totalU = "";
                        int totalUrban = 0;
                        if (amountsUrban == null) {
                            for (int i = 0; i < prices.get(0).getLevels().size(); i++) {
                                if (i < 2) {
                    %>
                    <tr>
                        <td><label>Bậc thang <%=i + 1%></label></td>
                        <td class=""><label><%= prices.get(0).getLevels().get(i)%></label></td>
                        <td class=""><label id="rural-amount-<%= i + 1%>">50</label></td>
                        <td class=""><label id="rural-result-<%= i + 1%>"></label></td>

                    </tr>
                    <%          } else {%>
                    <tr>
                        <td><label>Bậc thang <%=i + 1%></label></td>
                        <td class=""><label><%= prices.get(0).getLevels().get(i)%></label></td>
                        <td class=""><label id="rural-amount-<%= i + 1%>">100</label></td>
                        <td class=""><label id="rural-result-<%= i + 1%>"></label></td>

                    </tr>
                    <%          }
                        }
                    } else {
                        for (int i = 0; i < amountsUrban.size(); i++) {
                            int fee = prices.get(0).getLevels().get(i) * amountsUrban.get(i);
                            totalUrban += fee;
                    %>
                    <tr>
                        <td><label>Bậc thang <%=i + 1%></label></td>
                        <td class=""><label><%= prices.get(0).getLevels().get(i)%></label></td>
                        <td class=""><label id="rural-amount-1"><%=amountsUrban.get(i)%></label></td>
                        <td class=""><label id="rural-result-1"><%=String.format("%,.0f", (float) fee)%></label></td>

                    </tr>
                    <%  }
                            totalU = String.format("%,.0f", (float) totalUrban);
                            vatUrbanString = String.format("%,.0f", totalUrban * 0.1);
                            totalUrbanString= String.format("%,.0f", totalUrban * 1.1);
                        }
                    %>
                </table>
            </div>
            <div class="header orange">Thành tiền</div>
            <div class="payment flex ">
                <table class="table-payment table-margin">
                    <tr>
                        <td class="td-width-large"><label>Tiền điện</label></td>
                        <td class="td-width border-bottom"><label id="urban-payment__elec"><%=totalU.equals(" ")
                                ? "" : totalU%></label></td>
                    </tr>
                    <tr>
                        <td class="td-width-large"><label>Thuế GTGT (10%) tiền điện</label></td>
                        <td class="td-width border-bottom"><label id="urban-payment__vat"><%=vatUrbanString.equals(" ") ? "" : vatUrbanString%></label></td>
                    </tr>
                    <tr>
                        <td class="td-width-large"><label class="orange bold">Tổng cộng tiền thanh toán (đồng)</label></td>
                        <td class="td-width border-bottom"><label id="urban-payment__total"><%=totalUrbanString.equals(" ") ? "" : totalUrbanString%></label></td>
                    </tr>
                </table>
            </div>
        </div>

        <div id="rural" class="tabcontent">
            <div class="header orange">Nhập các thông số</div>
            <form action="<%=request.getContextPath()%>/calculator" method="post">
                <div class="metrics flex ">
                    <input type="number" name="type" value="2" hidden>
                    <table class="metric-table table-margin">
                        <!--                    <tr>
                                                <td class="td-width-large">
                                                    <label>Từ ngày - đến ngày</label>
                                                </td>
                                                <td class="td-width">
                                                    <input class="date-picker" type="date" name="start" id="start-rural">
                        
                                                </td>
                                                <td class="td-width"><input class="date-picker" type="date" name="end" id="end-rural"></td>
                                            </tr>-->
                        <tr>
                            <td class="td-width-large"><label for="consumed-rural">Tổng điện năng tiêu thụ (kWh)</label></td>
                            <td><input type="number" min="0" required name="consumed-rural" value="<%=request.getParameter("consumed-rural") != null ? request.getParameter("consumed-rural") : 0%>" id="consumed-rural"></td>

                        </tr>
                        <tr>
                            <td></td>
                            <td><input class="btn" type="submit" value="Tính toán"></td>
                        </tr>
                    </table>

                </div>
            </form>
            <div class="header orange">Kết quả</div>
            <div class="result flex ">
                <table class="result-table result-table-margin bordered">
                    <tr>
                        <th></th>
                        <th>ĐƠN GIÁ THEO QĐ 648/QĐ-BCT (đồng/kWh)</th>
                        <th>SẢN LƯỢNG (kWh)</th>
                        <th>THÀNH TIỀN (đồng)</th>
                    </tr>
                    <%
                        ArrayList<Integer> amountsRural = (ArrayList<Integer>) session.getAttribute("amounts-rural");
                        String totalRuralString = "";
                        String totalRuralS = "";
                        String vatRuralString = "";
                        int totalRural = 0;
                        if (amountsRural == null) {
                            for (int i = 0; i < prices.get(1).getLevels().size(); i++) {
                                if (i < 2) {
                    %>
                    <tr>
                        <td><label>Bậc thang <%=i + 1%></label></td>
                        <td class=""><label><%= prices.get(1).getLevels().get(i)%></label></td>
                        <td class=""><label id="rural-amount-<%= i + 1%>">50</label></td>
                        <td class=""><label id="rural-result-<%= i + 1%>"></label></td>

                    </tr>
                    <%          } else {%>
                    <tr>
                        <td><label>Bậc thang <%=i + 1%></label></td>
                        <td class=""><label><%= prices.get(1).getLevels().get(i)%></label></td>
                        <td class=""><label id="rural-amount-<%= i + 1%>">100</label></td>
                        <td class=""><label id="rural-result-<%= i + 1%>"></label></td>

                    </tr>
                    <%          }
                        }
                    } else {
                        for (int i = 0; i < amountsRural.size(); i++) {
                            int fee = prices.get(1).getLevels().get(i) * amountsRural.get(i);
                            totalRural += fee;
                    %>
                    <tr>
                        <td><label>Bậc thang <%=i + 1%></label></td>
                        <td class=""><label><%= prices.get(1).getLevels().get(i)%></label></td>
                        <td class=""><label id="rural-amount-1"><%=amountsRural.get(i)%></label></td>
                        <td class=""><label id="rural-result-1"><%=String.format("%,.0f", (float) fee)%></label></td>

                    </tr>
                    <%        }
                            totalRuralS = String.format("%,.0f", (float) totalRural);
                            vatRuralString = String.format("%,.0f", totalRural * 0.1);
                            totalRuralString = String.format("%,.0f", totalRural * 1.1);
                        }
                    %>
                </table>
            </div>
            <div class="header orange">Thành tiền</div>
            <div class="payment flex ">
                <table class="table-payment table-margin">
                    <tr>
                        <td class="td-width-large"><label>Tiền điện</label></td>
                        <td class="td-width border-bottom"><label id="rural-payment__elec"><%=totalRuralS.equals(" ") ? "" : totalRuralS%></label></td>
                    </tr>
                    <tr>
                        <td class="td-width-large"><label>Thuế GTGT (10%) tiền điện</label></td>
                        <td class="td-width border-bottom"><label id="rural-payment__vat"><%=vatRuralString.equals(" ") ? "" : vatRuralString%></label></td>
                    </tr>
                    <tr>
                        <td class="td-width-large"><label class="orange bold">Tổng cộng tiền thanh toán (đồng)</label></td>
                        <td class="td-width border-bottom"><label id="rural-payment__total"><%=totalRuralString.equals(" ") ? "" : totalRuralString%></label></td>
                    </tr>
                </table>
            </div>
        </div>
        <script src="js/calculator.js"></script>
    </body>

</html>
