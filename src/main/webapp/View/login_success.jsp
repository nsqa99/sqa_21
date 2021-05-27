<%-- 
    Document   : login_success
    Created on : Mar 26, 2021, 7:49:46 AM
    Author     : admin
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        if(session.getAttribute("user")!=null);
                    User u = new User();
                    u = (User) session.getAttribute("user");
        %>
        <h1>Xin chào <%=u.getFullname()%></h1>
    </body>
</html>
