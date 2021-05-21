import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import model.user;
import org.junit.Test;

import dao.userDAO;


public class UserDAOTest {
	private userDAO dao = new userDAO();
	private Connection con = dao.con;
	
	@Test
	public void testLoginFailWithCorrectUsernameAndWrongPassword() {
		user u = dao.checkLogin("nsqa", "0");
		assertNull(u);
	}
	
	@Test
	public void testLoginFailWithWrongUsernameAndCorrectPassword() {
		user u = dao.checkLogin("nsq", "c4ca4238a0b923820dcc509a6f75849b");
		assertNull(u);
	}
	
	@Test
	public void testLoginFailWithWrongUsernameAndWrongPassword() {
		user u = dao.checkLogin("nsq", "0");
		assertNull(u);
	}
	
	@Test
	public void testLoginSuccess() {
		user u = dao.checkLogin("lta", "c4ca4238a0b923820dcc509a6f75849b");
		assertNotNull(u);
	}
	
	@Test
	public void testAddUserSuccess() throws SQLException {
		con.setAutoCommit(false);
		user u = new user(123456789, "Test Fullname", "Test addr", "0123124123", 1, "abc", "alo");
		dao.addUser(u);
		assertNotNull(dao.findById(3));
		
		con.rollback();
		con.setAutoCommit(true);
	}
	
	@Test(expected = Exception.class)
	public void testAddUserFailWithWrongAreaId() throws SQLException {
		con.setAutoCommit(false);
		user u = new user(123456789, "Test Fullname", "Test addr", "0123124123", 3, "abc", "alo");
		dao.addUser(u);
		
		con.rollback();
		con.setAutoCommit(true);
	}
	
	@Test
	public void testCheckCorrectUsername() {
		assertTrue(dao.checkUsername("nsqa"));
	}
	
	@Test
	public void testCheckWrongUsername() {
		assertFalse(dao.checkUsername("n"));
	}
	
	@Test
	public void testGetUserRole() {
		assertEquals(dao.getRole(1), "admin");
	}
	
	@Test
	public void testGetAdminRole() {
		assertEquals(dao.getRole(2), "user");
	}
	
	@Test
	public void testGetRoleFail() {
		assertNull(dao.getRole(0));
	}
	
	@Test
	public void testFindByIdSuccess() {
		user expect = new user(1, 1, "Nguyễn Sỹ Quang Anh", "1234567890",
				"Hà Đông, Hà Nội", "admin", 12345678);
		user u = dao.findById(1);
		assertEquals(expect, u);
	}
	
	@Test
	public void testFindByIdFailWithIdNotFound() {
		user u = dao.findById(0);
		assertNull(u.getFullname());
	}
	
	@Test
	public void testFindAll() {
		List<user> result = dao.findAll();
		user u1 = new user(1, 1, "Nguyễn Sỹ Quang Anh", "1234567890",
				"Hà Đông, Hà Nội", "admin", 12345678);
		user u2 = new user(2, 1, "Lê Thế Anh", "1234567890",
				"Hà Đông, Hà Nội", "user", 827387123);
		List<user> expect = Arrays.asList(u1, u2);
		assertEquals(expect, result);
	}
	
}
