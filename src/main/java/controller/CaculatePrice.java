/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ElecPriceDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ElectricityPrice;
import utils.Calculator;

import org.apache.tomcat.jni.SSLContext;

/**
 *
 * @author nsqa
 */
@WebServlet(name = "CaculatePrice", urlPatterns = {"/calculator"})
public class CaculatePrice extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("prices");
        session.removeAttribute("amounts-urban");
        session.removeAttribute("amounts-rural");
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/show_Login");
            return;
        }

        ElecPriceDAO elecDao = new ElecPriceDAO();
        ArrayList<ElectricityPrice> prices = elecDao.findAll();
        session.setAttribute("prices", prices);

        RequestDispatcher dis = request.getRequestDispatcher("/View/calculator.jsp");
        dis.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        handleCalculateRequest(request, response);

    }

    private void handleCalculateRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String consumedAmountUrban = request.getParameter("consumed-urban");
        String consumedAmountRural = request.getParameter("consumed-rural");
//        calculateAmountLevels(consumedAmount).forEach((item) -> {
//            System.out.println(item); 
//        });
        int consumedAmount = Integer.parseInt(consumedAmountUrban != null ? consumedAmountUrban : consumedAmountRural);
        request.getSession().setAttribute(
                (consumedAmountUrban != null ? "amounts-urban" : "amounts-rural"),
                Calculator.calculateAmountLevels(consumedAmount));
        RequestDispatcher dis = request.getRequestDispatcher("/View/calculator.jsp");
        dis.forward(request, response);
    }

}
