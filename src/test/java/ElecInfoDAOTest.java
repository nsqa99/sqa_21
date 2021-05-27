import dao.ElecInfoDAO;
import dao.UserDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.ElecInfo;
import static org.junit.Assert.*;
import model.User;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nsqa
 */
public class ElecInfoDAOTest {
	private final ElecInfoDAO dao = new ElecInfoDAO();
	private final UserDAO userDao = new UserDAO();
	private final Connection con = dao.con;

	public ElecInfoDAOTest() {
	}

	@Test
	public void testBulkInsert() throws SQLException {
		String time = "07/2021";
		List<User> users = userDao.findAll();
		List<User> admins = users.stream().filter(u -> {
			return userDao.getRole(u.getIdUser()).equals("admin");
		}).collect(Collectors.toList());
		int numOfAdmin = admins.size();
		int initNumOfRecords = dao.findAll().size();
		int numOfNewRecords = 0;
		if (users != null) {
			numOfNewRecords = users.size() - numOfAdmin;
		}

		try {
			con.setAutoCommit(false);
			dao.bulkInsert(time);
			assertEquals(numOfNewRecords, dao.findAll().size() - initNumOfRecords);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Test(expected = Exception.class)
	public void testBulkInsertFail() throws Exception {
		String time = "sai ngay";
		con.setAutoCommit(false);
		dao.bulkInsert(time);
		con.rollback();
		con.setAutoCommit(true);

	}

	@Test
	public void testFindById() throws SQLException {
		long id = 1;
		ElecInfo result = dao.findById(id);
		ElecInfo expect = new ElecInfo();
		expect.setId(id);
		expect.setConsumedAmount(423);
		expect.setMonth("04/2021");
		expect.setPrice((float) 1073953.1);
		expect.setPaymentStatus("P");
		expect.setUserFullName("Nguyễn Sỹ Quang Anh");
		expect.setUserAddr("Hà Đông, Hà Nội");
		expect.setUserAreaId(1);
		expect.setUserPhoneNum("1234567890");
		expect.setUserid(1);
		assertEquals(expect, result);
	}

	@Test(expected = SQLException.class)
	public void testFindByIdNotFound() throws SQLException {
		long id = -1;
		ElecInfo result = dao.findById(id);
		assertNull(result.getMonth());
	}

	@Test
	public void testCalculateTotalUrbanZero() {
		int amount = 0;
		int areaId = 1;
		String expect = "0";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalUrbanOneLevel() {
		int amount = 49;
		int areaId = 1;
		String expect = "90,444";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalUrbanTwoLevel() {
		int amount = 51;
		int areaId = 1;
		String expect = "94,197";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalUrbanThreeLevel() {
		int amount = 101;
		int areaId = 1;
		String expect = "189,875";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalUrbanFourLevel() {
		int amount = 201;
		int areaId = 1;
		String expect = "411,990";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalUrbanFiveLevel() {
		int amount = 301;
		int areaId = 1;
		String expect = "691,277";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalUrbanSixLevel() {
		int amount = 401;
		int areaId = 1;
		String expect = "1,003,120";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalRuralZero() {
		int amount = 0;
		int areaId = 2;
		String expect = "0";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalRuralOneLevel() {
		int amount = 49;
		int areaId = 2;
		String expect = "75,622";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalRuralTwoLevel() {
		int amount = 51;
		int areaId = 2;
		String expect = "78,770";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalRuralThreeLevel() {
		int amount = 101;
		int areaId = 2;
		String expect = "159,159";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalRuralFourLevel() {
		int amount = 201;
		int areaId = 2;
		String expect = "334,478";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalRuralFiveLevel() {
		int amount = 301;
		int areaId = 2;
		String expect = "551,574";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalRuralSixLevel() {
		int amount = 401;
		int areaId = 2;
		String expect = "797,085";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalUrbanBillionNumber() {
		int amount = 123123213;
		int areaId = 2;
		String expect = "396,419,520,916";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testCalculateTotalRuralBillionNumber() {
		int amount = 123123213;
		int areaId = 2;
		String expect = "314,615,724,059";
		float total = dao.calculateTotal(amount, areaId);
		String result = String.format("%,.0f", total);
		assertEquals(expect, result);
	}

	@Test
	public void testFindAll() throws SQLException {
		ElecInfo info1 = new ElecInfo(1, "Nguyễn Sỹ Quang Anh", "Hà Đông, Hà Nội", "1234567890", "04/2021", 423,
				dao.calculateTotal(423, 1), "P", 1, 1);
		ElecInfo info2 = new ElecInfo(2, "Lê Thế Anh", "Hà Đông, Hà Nội", "1234567890", "04/2021", 120,
				dao.calculateTotal(120, 1), "P", 2, 1);
		ElecInfo info3 = new ElecInfo(13, "Nguyễn Sỹ Quang Anh", "Hà Đông, Hà Nội", "1234567890", "05/2021", 100,
				dao.calculateTotal(100, 1), "NP", 1, 1);
		ElecInfo info4 = new ElecInfo(14, "Lê Thế Anh", "Hà Đông, Hà Nội", "1234567890", "05/2021", 200,
				dao.calculateTotal(200, 1), "NP", 2, 1);
		ElecInfo info5 = new ElecInfo(233, "Nguyễn Sỹ Quang Anh", "Hà Đông, Hà Nội", "1234567890", "06/2021", 0,
				dao.calculateTotal(0, 1), "NP", 1, 1);
		ElecInfo info6 = new ElecInfo(234, "Lê Thế Anh", "Hà Đông, Hà Nội", "1234567890", "06/2021", 0,
				dao.calculateTotal(0, 1), "NP", 2, 1);
		ArrayList<ElecInfo> expect = new ArrayList<>();
		expect.add(info1);
		expect.add(info2);
		expect.add(info3);
		expect.add(info4);
		expect.add(info5);
		expect.add(info6);
		ArrayList<ElecInfo> results = dao.findAll();
		assertEquals(expect, results);
	}

	@Test
	public void testHandInMoneyFail() {
		try {
			dao.handInMoney(1);
		} catch (Exception e) {
			assertEquals("Hóa đơn điện 1 đã được thanh toán", e.getMessage());
		}

	}

	@Test
	public void testHandInMoneySuccess() throws Exception {
		con.setAutoCommit(false);
		dao.handInMoney(13);
		ElecInfo actual = dao.findById(2);
		ElecInfo expect = new ElecInfo(2, "Lê Thế Anh", "Hà Đông, Hà Nội", "1234567890", "04/2021", 120,
				dao.calculateTotal(120, 1), "P", 2, 1);
		assertEquals(expect, actual);
		con.rollback();
		con.setAutoCommit(true);
	}

	@Test
	public void testUpdateAmountSuccess() throws Exception {
		long id = 13;
		int newAmount = 150;
		con.setAutoCommit(false);
		dao.updateAmount(id, newAmount);
		ElecInfo actual = dao.findById(id);
		ElecInfo expect = new ElecInfo(13, "Nguyễn Sỹ Quang Anh", "Hà Đông, Hà Nội", "1234567890", "05/2021", 150,
				dao.calculateTotal(150, 1), "NP", 1, 1);
		assertEquals(expect, actual);
		con.rollback();
		con.setAutoCommit(true);
	}

	@Test(expected = Exception.class)
	public void testUpdateAmountFail() {
		long id = 5;
		int newAmount = 150;
		try {
			dao.updateAmount(id, newAmount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
