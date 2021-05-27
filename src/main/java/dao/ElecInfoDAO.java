/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ElecInfo;
import model.ElectricityPrice;
import model.user;
import utils.Calculator;

/**
 *
 * @author nsqa
 */
public class ElecInfoDAO extends dao {

	private userDAO userDao = new userDAO();

	public ElecInfoDAO() {
		super();
	}

	public void bulkInsert(String time) throws SQLException {
		String query = "INSERT INTO electricity_info VALUES(null, ?, ?, 0, 0, 'NP');";

		PreparedStatement ps = con.prepareStatement(query);
		List<user> users = userDao.findAll();
//            for (user u : users) {
//                System.out.println(u.getIdUser());
//                System.out.println(u.getFullname());
//            }
		for (user u : users) {
			ps.setLong(1, u.getIdUser());
			ps.setString(2, time);
			ps.executeUpdate();
		}
	}
	
	public void bulkInsertTest(String time) throws SQLException {
		con.setAutoCommit(false);
		String query = "INSERT INTO electricity_info VALUES(null, ?, ?, 0, 0, 'NP');";

		PreparedStatement ps = con.prepareStatement(query);
		List<user> users = userDao.findAll();
//            for (user u : users) {
//                System.out.println(u.getIdUser());
//                System.out.println(u.getFullname());
//            }
		for (user u : users) {
			ps.setLong(1, u.getIdUser());
			ps.setString(2, time);
			ps.executeUpdate();
		}
		con.rollback();
		con.setAutoCommit(true);
	}

	public ElecInfo findById(long id) throws SQLException {
		ElecInfo info = new ElecInfo();
		info.setId(id);
		String query = "SELECT * FROM electricity_info WHERE elec_info_id=?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			info.setConsumedAmount(rs.getInt("consumedAmount"));
			info.setMonth(rs.getString("month_year"));
			info.setPaymentStatus(rs.getString("paymentStatus"));
			user u = userDao.findById(rs.getLong("userid"));
			info.setUserAddr(u.getAddress());
			info.setUserFullName(u.getFullname());
			info.setUserid(rs.getLong("userid"));
			info.setUserPhoneNum(u.getSdt());
			info.setUserAreaId(u.getAreaId());
			info.setPrice(calculateTotal(rs.getInt("consumedAmount"), u.getAreaId()));
		}
		return info;
	}

	public void updateAmount(long id, int newAmount) throws SQLException {
		String query = "UPDATE electricity_info SET consumedAmount=? WHERE elec_info_id=?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, newAmount);
		ps.setLong(2, id);
		ps.executeUpdate();
	}

	public void handInMoney(long id) throws Exception {
		ElecInfo info = findById(id);
		if (info.getPaymentStatus().equalsIgnoreCase("P"))
			throw new Exception("Hóa đơn điện " + id + " đã được thanh toán");
		String query = "UPDATE electricity_info SET paymentStatus='P', price = ? WHERE elec_info_id=?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setFloat(1, info.getPrice());
		ps.setLong(2, id);
		ps.executeUpdate();
	}

	public ArrayList<ElecInfo> findAll() throws SQLException {
		ArrayList<ElecInfo> prices = new ArrayList<>();
		String query = "SELECT * FROM electricity_info";

		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ElecInfo info = new ElecInfo();
			info.setId(rs.getLong("elec_info_id"));
			info.setConsumedAmount(rs.getInt("consumedAmount"));
			info.setMonth(rs.getString("month_year"));
			info.setPaymentStatus(rs.getString("paymentStatus"));
			user u = userDao.findById(rs.getLong("userid"));
			info.setUserAddr(u.getAddress());
			info.setUserFullName(u.getFullname());
			info.setUserPhoneNum(u.getSdt());
			info.setUserAreaId(u.getAreaId());
			info.setUserid(rs.getLong("userid"));
			info.setPrice(calculateTotal(rs.getInt("consumedAmount"), u.getAreaId()));
			prices.add(info);
		}
		return prices;
	}

	public float calculateTotal(int amount, long areaId) {
		ElecPriceDAO dao = new ElecPriceDAO();
		float total = 0;
		ElectricityPrice levels = dao.findById(areaId);
		List<Integer> levelsPrice = Calculator.calculateAmountLevels(amount);
		for (int i = 0; i < levelsPrice.size(); i++) {
			total += levels.getLevels().get(i) * levelsPrice.get(i);
		}
		return (total * (float) 1.1);
	}

}
