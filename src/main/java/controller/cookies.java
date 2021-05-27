/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.userDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.user;

/**
 *
 * @author admin
 */
@WebServlet(name = "cookies", urlPatterns = {"/cookies"})
public class cookies extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userDAO userDao = new userDAO();
        String username = "";
        String password = "";
        Cookie log = null;
        Cookie[] logs = null;
        logs = request.getCookies();
        if (logs != null) {
            for (int i = 0; i < logs.length; i++) {
                log = logs[i];
                if (log.getName().equals("username")) {
                    username = log.getValue();
                }
                if (log.getName().equals("password")) {
                    password = log.getValue();
                }
            }
            user u = userDao.checkLogin(username, password);
            if (u != null) {
                if (u.getRole().equalsIgnoreCase("admin")) {
                    response.sendRedirect(request.getContextPath() + "/admin");
                    return;
                } else {
                    response.sendRedirect(request.getContextPath() + "/main");
                    return;
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/show_Login");

    }

}
