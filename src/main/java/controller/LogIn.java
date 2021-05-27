/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author admin
 */
@WebServlet(name = "LogIn", urlPatterns = {"/LogIn"})
public class LogIn extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username=request.getParameter("usernamelog");
        String password=request.getParameter("passwordlog");
        String md5password= DigestUtils.md5Hex(password);
        UserDAO ud = new UserDAO();
        User u = ud.checkLogin(username, md5password);
        if(u != null){
                Cookie ck1 = new Cookie("username", username);
                Cookie ck2 = new Cookie("password", md5password);
                ck1.setMaxAge(60*60*24*30);
                ck2.setMaxAge(60*60*24*30);
                response.addCookie(ck1);
                response.addCookie(ck2);
                request.getSession().setAttribute("user", u);
                if (u.getRole().equalsIgnoreCase("admin")) {
                    response.sendRedirect(request.getContextPath() + "/admin");
                    return;
                }
                response.sendRedirect(request.getContextPath() + "/main");
        }
        else{
            request.getSession().setAttribute("loginError", "Error");
            response.sendRedirect(request.getContextPath() + "/show_Login");
        }
    }

}
