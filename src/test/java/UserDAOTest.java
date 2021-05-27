import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import model.User;
import org.junit.Test;

import dao.UserDAO;

public class UserDAOTest {
	private UserDAO dao = new UserDAO();
	private Connection con = dao.con;

	@Test
	public void testLoginFailWithCorrectUsernameAndWrongPassword() {
		User u = dao.checkLogin("nsqa", "0");
		assertNull(u);
	}

	@Test
	public void testLoginFailWithWrongUsernameAndCorrectPassword() {
		User u = dao.checkLogin("nsq", "c4ca4238a0b923820dcc509a6f75849b");
		assertNull(u);
	}

	@Test
	public void testLoginFailWithWrongUsernameAndWrongPassword() {
		User u = dao.checkLogin("nsq", "0");
		assertNull(u);
	}

	@Test
	public void testLoginSuccess() {
		User u = dao.checkLogin("lta", "c4ca4238a0b923820dcc509a6f75849b");
		assertNotNull(u);
	}

	@Test
	public void testAddUserSuccess() {
		try {
			int initSize = dao.findAll().size();
			User u = new User(123456789, "Test Fullname", "Test addr", "0123124123", 1, "abc", "alo");
			dao.addUser(u);
			assertNotEquals(initSize, dao.findAll().size());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	public void testAddUserFailWithDuplicateUsername() throws Exception {
		int initSize = dao.findAll().size();
		User u = new User(123456789, "Test Fullname", "Test addr", "0123124123", 3, "nsqa", "alo");
		dao.addUser(u);
		assertEquals(initSize, dao.findAll().size());
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
		User expect = new User(1, 1, "Nguyễn Sỹ Quang Anh", "1234567890", "Hà Đông, Hà Nội", "admin", 12345678);
		User u = dao.findById(1);
		assertEquals(expect, u);
	}

	@Test
	public void testFindByIdFailWithIdNotFound() {
		User u = dao.findById(0);
		assertNull(u.getFullname());
	}

	@Test
	public void testFindAll() {
		List<User> result = dao.findAll();
		User u1 = new User(1, 1, "Nguyễn Sỹ Quang Anh", "1234567890", "Hà Đông, Hà Nội", "admin", 12345678);
		User u2 = new User(2, 1, "Lê Thế Anh", "1234567890", "Hà Đông, Hà Nội", "user", 827387123);
		List<User> expect = Arrays.asList(u1, u2);
		assertEquals(expect, result);
	}

}
