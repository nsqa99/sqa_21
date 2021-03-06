/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
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
        String username, fullname, pass1, pass2, address, phone, identity, areaId;
        username = request.getParameter("usernameSign");
        fullname = request.getParameter("fullnameSign");
        identity = request.getParameter("identity");
        pass1 = request.getParameter("passSign");
        pass2 = request.getParameter("rePassSign");
        address = request.getParameter("addressSign");
        phone = request.getParameter("phoneSign");
        areaId = request.getParameter("area_check");
        if (!pass1.equals(pass2)) {
        	ses.setAttribute("Error", "Mật khẩu nhập lại không khớp");
            RequestDispatcher dis = request.getRequestDispatcher("/View/signup.jsp");
            dis.forward(request, response);
            return;
        }
        String pass = DigestUtils.md5Hex(pass1);
        User u = new User();
        UserDAO ud = new UserDAO();
        
        if (!ud.checkUsername(username)) {
            u.setUsername(username);
            u.setFullname(fullname);
            u.setPassword(pass);
            u.setAddress(address);
            u.setSdt(phone);
            u.setIdentityNum(Long.parseLong(identity));
            u.setAreaId(Long.parseLong(areaId));
            try {
				ud.addUser(u);
//            	ud.addUserTest(u);
			} catch (Exception e) {
				response.sendRedirect("show_Signup");
			}
            response.sendRedirect("signup_success");
        } else {
            ses.setAttribute("Error", "Tên tài khoản đã tồn tại");
            RequestDispatcher dis = request.getRequestDispatcher("/View/signup.jsp");
            dis.forward(request, response);
        }
    }

}
