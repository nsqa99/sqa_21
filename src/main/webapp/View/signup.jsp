<%-- 
    Document   : signup
    Created on : Nov 23, 2020, 2:31:52 PM
    Author     : admin
--%>


<%@page import="dao.UserDAO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng kí</title>
        <meta charset="utf-8">
	<meta charset="viewport" content="width=device-width,initial-scale=1.0">
	<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
	<link rel="stylesheet" type="text/css" href="css/signup.css">
	<style>
            <%@include file ="/css/signup.css" %>
        </style>
    </head>
    <body>
        <div id="container" class="container">
		<!--FORM SELECTION-->
		<div class="row">
			<!--đăng ký-->
			<div class="col align-items-center flex-col">
				
                                    <form action="SignUp" method="post">
					<div class="form sign-up">
                                            <%
                                                if(session.getAttribute("Error")!=null){
                                                
                                            %>
                                            <p id="error_signup" style="font-size: 14px; color: red;">*<%=session.getAttribute("Error") %></p>
                                            
                                            <%session.removeAttribute("Error"); 
                                            } %>
						<div class="input-group">
							<i class='bx bxs-user'></i>
                                                        <input name="usernameSign" type="text" placeholder="Tên đăng nhập" required="required">
                                                        
						</div>
                                            <div class="input-group">
							<i class='bx bxs-user'></i>
							<input name="fullnameSign" type="text" placeholder="Họ và tên" required="required">
						</div>
						<div class="input-group">
							<i class='bx bxs-user'></i>
							<input name="identity" pattern="(?:[0-9]{9}|[0-9]{12})" title="Chứng minh thư nhân dân phải bao gồm 9 hoặc 12 chữ số" type="text" placeholder="CMTND/CCCD" required="required">
						</div>
						<div class="input-group">
							<i class='bx bxs-lock-alt' ></i>
                            <input name="passSign" type="password" placeholder="Mật khẩu" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Mật khẩu có độ dài tối thiểu 8 kí tự và phải bao gồm ít nhất 1 kí tự in  hoa, 1 kí tự thường và 1 chữ số" required="required">
						</div>
                        <div class="input-group">
							<i class='bx bxs-lock-alt' ></i>
                            <input name="rePassSign" type="password" placeholder="Nhập lại mật khẩu" required="required">
						</div>                        
						
                        <div class="input-group">
							<i class='bx bxs-user'></i>
							<input name="addressSign" type="text" placeholder="Địa chỉ" required="required">
						</div>
                                            <div class="input-group">
							<i class='bx bxs-user'></i>
							<input name="phoneSign" pattern="[0-9]{10}" title="Số điện thoại phải bao gồm 10 chữ số" type="text" placeholder="Số điện thoại" required="required">
						</div>
						<div class="input-group" style="display: flex; justify-content: space-between; width: 100%">
							<div style="width: 50%">
								
								<label for="check-urban" >
									<input name="area_check" checked type="radio" id="check-urban" value="1" required="required">Thành phố</label>
							</div>
							<div style="width: 50%">
								
								<label for="check-rural" >
									<input name="area_check" type="radio" id="check-rural" value="2" required="required">
									Nông thôn</label>
							</div>
								
							
							
						</div>
                       	<input id="btnSignup" type="submit" value="Đăng ký" style="width: 100%;background-color: var(--primary-color); color: white; height: 50px; cursor: pointer; border: none">
						<p>
							<span>
								Đã có tài khoản sẵn sàng
							</span>
                                                        <a href="show_Login" style="text-decoration: none; color: black;"><b>Đăng nhập</b></a>
						</p>
					</div>
                                    </form>
				
				
			</div>
			<!--kết thúc đăng ký-->
			<div class="col align-items-center flex-col">
				<div class="form-wrapper align-items-center">
                                    <form action="LogIn" method="post">
					<div class="form sign-in">
						<div class="input-group">
							<i class='bx bxs-user'></i>
							<input name="usernamelog" type="text" placeholder="username" required="required">
						</div>
						<div class="input-group">
							<i class='bx bxs-lock-alt' ></i>
							<input name="passwordlog" type="password" placeholder="password" required="required">
						</div>
                                            <input type="submit" value="Đăng nhập"style="width: 100%;background-color: var(--primary-color); color: white; height: 50px; cursor: pointer; border: none">
						
						<p>
							<span>
								Chưa có tài khoản
							</span>
							<b onclick="toggle()" class="pointer">
								Đăng ký
							</b>	
						</p>
					</div>
                                    </form>
				</div>
				
			</div>
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
					<img src="login/evn.jpg" alt="Join">
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

	<script src="js/sign.js"></script>
    </body>
    <script type="text/javascript">
                const taga = document.getElementsByTagName("a");
		for(var i=0;i<taga.length;i++)
		{
			taga.item(i).addEventListener("click", function()
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

