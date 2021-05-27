/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ElecInfoDAO;
import dao.ElecPriceDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ElecInfo;
import model.ElectricityPrice;
import model.user;

/**
 *
 * @author nsqa
 */
@WebServlet(name = "AdminController", urlPatterns = { "/admin", "/admin-config", "/admin-update", "/admin-pay",
		"/admin-add-info", "/admin-config-update", "/admin-config-add" })
public class AdminController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		user u = (user) request.getSession().getAttribute("user");
		if (u != null && u.getRole().equalsIgnoreCase("admin"))
			doPost(request, response);
		else
			response.sendRedirect(request.getContextPath() + "/show_Login");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dis = null;
		ElecInfoDAO infoDao = new ElecInfoDAO();
		ElecPriceDAO priceDao = new ElecPriceDAO();

		switch (request.getServletPath()) {
		case "/admin":
			List<ElecInfo> infos = new ArrayList<ElecInfo>();
			try {
				infos = infoDao.findAll();
			} catch (SQLException e2) {
				response.sendRedirect(request.getContextPath() + "/show_Login");
			}
			request.getSession().setAttribute("infos", infos);
			dis = request.getRequestDispatcher("/View/admin_index.jsp");
			dis.forward(request, response);
			break;
		case "/admin-config":
			List<ElectricityPrice> prices = priceDao.findAll();
			request.getSession().setAttribute("prices", prices);
			dis = request.getRequestDispatcher("/View/admin_config.jsp");
			dis.forward(request, response);
			break;
		case "/admin-update":
			String idUpdate = request.getParameter("idUpdate");
			String amount = request.getParameter("amount");
			try {
				infoDao.updateAmount(Integer.parseInt(idUpdate), Integer.parseInt(amount));
			} catch (NumberFormatException | SQLException e1) {
				response.sendRedirect("admin");
			}
			response.sendRedirect("admin");
			break;

		case "/admin-pay":
			String idPay = request.getParameter("idPay");
			try {
				infoDao.handInMoney(Integer.parseInt(idPay));
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath() + "/admin");
			}
			response.sendRedirect(request.getContextPath() + "/admin");
			break;
		case "/admin-add-info":
			String monthToAdd = request.getParameter("monthToAdd");
			String[] strings = monthToAdd.split("-");
//                System.out.println(strings[1] + "/" + strings[0]);
			try {
				infoDao.bulkInsert(strings[1] + "/" + strings[0]);
//				infoDao.bulkInsertTest(strings[1] + "/" + strings[0]);
			} catch (SQLException e) {
				response.sendRedirect("admin");
			}
			response.sendRedirect("admin");
			break;

		case "/admin-config-update":
			String idConfig = request.getParameter("retard");
			String level1 = request.getParameter("level_1");
			String level2 = request.getParameter("level_2");
			String level3 = request.getParameter("level_3");
			String level4 = request.getParameter("level_4");
			String level5 = request.getParameter("level_5");
			String level6 = request.getParameter("level_6");
			String area = request.getParameter("area_update");
			System.out.println(idConfig + " " + area);
			List<Integer> levelsUpdate = new ArrayList<>();
			levelsUpdate.add(Integer.parseInt(level1));
			levelsUpdate.add(Integer.parseInt(level2));
			levelsUpdate.add(Integer.parseInt(level3));
			levelsUpdate.add(Integer.parseInt(level4));
			levelsUpdate.add(Integer.parseInt(level5));
			levelsUpdate.add(Integer.parseInt(level6));

			priceDao.updateById(Long.parseLong(idConfig), area, levelsUpdate);
			response.sendRedirect("admin-config");
			break;
		case "/admin-config-add":
			String level1Add = request.getParameter("level_1_add");
			String level2Add = request.getParameter("level_2_add");
			String level3Add = request.getParameter("level_3_add");
			String level4Add = request.getParameter("level_4_add");
			String level5Add = request.getParameter("level_5_add");
			String level6Add = request.getParameter("level_6_add");
			String areaAdd = request.getParameter("area_add");
			List<Integer> levelsAdd = new ArrayList<>();
			levelsAdd.add(Integer.parseInt(level1Add));
			levelsAdd.add(Integer.parseInt(level2Add));
			levelsAdd.add(Integer.parseInt(level3Add));
			levelsAdd.add(Integer.parseInt(level4Add));
			levelsAdd.add(Integer.parseInt(level5Add));
			levelsAdd.add(Integer.parseInt(level6Add));

			priceDao.insertOne(areaAdd, levelsAdd);
			response.sendRedirect("admin-config");
			break;

		default:
			break;
		}

	}

}
