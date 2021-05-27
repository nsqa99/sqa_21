<%-- 
    Document   : index
    Created on : Nov 23, 2020, 12:37:41 AM
    Author     : admin
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tập đoàn điện lực Việt Nam</title>
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <meta charset="utf-8" content="width=device-width,initial-scale=1" >
    </head>
    <body>

        <div class="nav-bar flex fl-middle">
            <a href="<%=request.getContextPath()%>/">
                <img class="logo-header" src="assets/logo.png" alt="logo">
            </a>
            <ul class="nav-list flex fl-middle">
                <li class="nav-list__item">
                    <a class="tab-button active" href="<%=request.getContextPath()%>/">Trang chủ</a>
                </li>
                <li class="nav-list__item">
                    <a class="tab-button" href="<%=request.getContextPath()%>/calculator">Tính tiền điện</a>
                </li>
                
                <li class="nav-list__item">
                    <a class="tab-button" href="#">Tin tức</a>
                </li>
                <li class="nav-list__item">
                    <a class="tab-button" href="#">Liên hệ</a>
                </li>
                <%
                User u = (User) request.getSession().getAttribute("user");
                %>
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
        <div class="banner">
            <img src="assets/3_evnhanoi.png" alt="evn hanoi" class="banner__text">
            <h1>Phát triển nguồn năng lượng xanh</h1>
        </div>
        <div class="footer flex fl-center">
            <table class="footer__table">
                <tbody>
                    <tr>
                        <th>TỔNG CÔNG TY ĐIỆN LỰC TP.HÀ NỘI</th>
                        <th>Tra cứu thông tin</th>
                        <th>Thanh toán hóa đơn</th>
                        <th>Công bố thông tin</th>
                    </tr>
                    <tr>
                        <td>Địa chỉ: 69 Đinh Tiên Hoàng, Hoàn Kiếm, Hà Nội</td>



                        <td>Tra cứu chỉ số công tơ</td>
                        <td>Hóa đơn tiền điện</td>

                        <td>Thông cáo báo chí</td>
                    </tr>
                    <tr>
                        <td>Tel: (084)24 12345677</td>


                        <td>Điện năng tiêu thụ</td>
                        <td>Thanh toán tại điểm thu tiền điện</td>
                        <td>Thông tin doanh nghiệp</td>

                    </tr>
                    <tr>
                        <td>Fax: (084)24 22120299</td>


                    </tr>
                    <tr>
                        <td>Email: evnhanoi123@nsqa.vn</td>

                    </tr>
                </tbody>
            </table>
        </div>
    </body>

</html>
