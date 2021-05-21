/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.user;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author admin
 */
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession ses = request.getSession();
        String username, fullname, pass1, address, phone, identity;
        username = request.getParameter("usernameSign");
        fullname = request.getParameter("fullnameSign");
        identity = request.getParameter("identity");
        pass1 = request.getParameter("passSign");
        address = request.getParameter("addressSign");
        phone = request.getParameter("phoneSign");

        String pass = DigestUtils.md5Hex(pass1);
        user u = new user();
        userDAO ud = new userDAO();
        if (ud.checkUsername(username)) {
            u.setUsername(username);
            u.setFullname(fullname);
            u.setPassword(pass);
            u.setAddress(address);
            u.setSdt(phone);
            u.setIdentityNum(Long.parseLong(identity));
            u.setAreaId(1);
            ud.addUser(u);
            response.sendRedirect("signup_success");
        } else {
            ses.setAttribute("UserTonTai", ud.checkUsername(username));
            RequestDispatcher dis = request.getRequestDispatcher("/View/signup.jsp");
            dis.forward(request, response);
        }
    }

}
