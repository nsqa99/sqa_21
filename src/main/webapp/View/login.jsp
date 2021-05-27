<%-- 
    Document   : login
    Created on : Nov 23, 2020, 1:55:18 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <meta charset="utf-8">
        <meta charset="viewport" content="width=device-width,initial-scale=1.0">
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" type="text/css" href="css/login.css">

    </head>
    <body>
        <div id="container" class="container">
            <!--FORM SELECTION-->
            <div class="row">
                <!--đăng ký-->

                <div class="col align-items-center flex-col">

                    <form action="SignUp" method="post">

                        <div class="form sign-up">

                            <div class="input-group">
                                <i class='bx bxs-user'></i>
                                <input name="usernameSign" type="text" placeholder="username" required="required">
                            </div>
                            <div class="input-group">
                                <i class='bx bxs-user'></i>
                                <input name="fullnameSign" type="text" placeholder="Fullname" required="required">
                            </div>
                            <div class="input-group">
                                <i class='bx bx-mail-send'></i>
                                <input name="emailSign" type="email" placeholder="email" required="required">
                            </div>
                            <div class="input-group">
                                <i class='bx bxs-lock-alt' ></i>
                                <input name="passSign" type="password" placeholder="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required="required">
                            </div>


                            <div class="input-group">
                                <i class='bx bxs-user'></i>
                                <input name="addressSign" type="text" placeholder="Address" required="required">
                            </div>
                            <div class="input-group">
                                <i class='bx bxs-user'></i>
                                <input name="phoneSign" type="text" placeholder="Phone" required="required">
                            </div>
                            <input type="submit" value="Đăng ký" style="width: 100%;background-color: var(--primary-color); color: white; height: 50px; cursor: pointer; border: none">
                            <p>
                                <span>
                                    Đã có tài khoản sẵn sàng
                                </span>
                                <b onclick="toggle()" class="pointer">
                                    Đăng nhập
                                </b>
                            </p>
                        </div>
                    </form>


                </div>

                <!--kết thúc đăng ký-->
                <!--đăng nhập-->
                <div class="col align-items-center flex-col">
                    <div class="form-wrapper align-items-center">
                        <form action="LogIn" method="post">
                            <div class="form sign-in">
                                <%
                                    if (session.getAttribute("loginError") != null) {
                                        session.removeAttribute("loginError");
                                %>
                                <p id="error_msg" style="font-size: 14px; color: red;">*Tên đăng nhập hoặc mật khẩu bị sai, vui lòng thử lại</p>
                                <% }%>
                                <div class="input-group">
                                    <i class='bx bxs-user'></i>
                                    <input name="usernamelog" type="text" placeholder="Tên đăng nhập" required="required">
                                </div>
                                <div class="input-group">
                                    <i class='bx bxs-lock-alt' ></i>
                                    <input name="passwordlog" type="password" placeholder="Mật khẩu" required="required">
                                </div>
                                <input type="submit" value="Đăng nhập"style="width: 100%;background-color: var(--primary-color); color: white; height: 50px; cursor: pointer; border: none">

                                <p>
                                    <span>
                                        Chưa có tài khoản
                                    </span>
                                    <a href="show_SignUp" style="text-decoration: none; color: black;"><b>Đăng ký</b></a>
                                </p>
                            </div>
                        </form>
                    </div>

                </div>
                <!--kết thúc đăng nhập-->
            </div>
            <!--END FORM SELECTION-->
            <!--CONTENT SELECTION-->
            <div class="row content-row">
                <!--sign-in content-->
                <div class="col align-items-center flex-col">
                    <div class="text sign-in">

                        <h2>Tập đoàn điện lực Việt Nam</h2>
                        <p>VIETNAM ELECTRICITY</p>
                    </div>
                    <div class="img sign-in">
                        <img src="login/evn.jpg" alt="WELCOME">
                    </div>
                </div>
                <!--end sign-in content-->
                <!--sign-up content-->
                <div class="col align-items-center flex-col">
                    <div class="img sign-up">
                        <img src="login/evn.jpg" alt="Join" width="30px" >
                    </div>
                    <div class="text sign-up">


                        <h2>Tập đoàn điện lực Việt Nam</h2>
                        <p>VIETNAM ELECTRICITY</p>

                    </div>
                </div>
                <!--end sign-up content-->
            </div> 
            <!--END CONTENT SELECTION-->
        </div>

        <script src="js/log.js"></script>
    </body>
    <script type="text/javascript">
                                    const taga = document.getElementsByTagName("a");
                                    for (var i = 0; i < taga.length; i++)
                                    {
                                        taga.item(i).addEventListener("click", function ()
                                        {
                                            submitt(this);

                                        });
                                    }

                                    function submitt(z)
                                    {
                                        z.children[0].submit();
                                    }
    </script>
</html>
