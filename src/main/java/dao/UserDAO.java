/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.cj.jdbc.StatementImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author admin
 */
public class UserDAO extends BaseDAO {

	public UserDAO() {
		super();
	}

	public User checkLogin(String username, String password) {
		User u = new User();
		String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u.setUsername(username);
				u.setPassword(password);
				u.setRole(getRole(rs.getLong("userid")));
				u.setFullname(findById(rs.getLong("userid")).getFullname());
				return u;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addUser(User user) throws SQLException {

		String[] names = user.getFullname().split(" ");
		String sql1 = "INSERT INTO user values(null, ? , ?, null, ?, ?, ?, null, ?)";
		String sql2 = "INSERT INTO account values(null, ?, ?, ?)";
		String sql3 = "INSERT INTO user_role values(?, 1)";
		PreparedStatement ps1;
		try {
			con.setAutoCommit(false);
			ps1 = con.prepareStatement(sql1, StatementImpl.RETURN_GENERATED_KEYS);
			PreparedStatement ps2 = con.prepareStatement(sql2);
			PreparedStatement ps3 = con.prepareStatement(sql3);
			ps1.setLong(1, user.getIdentityNum());
			ps1.setString(2, names[names.length - 1]);
			ps1.setString(3, names[0]);
			ps1.setString(4, user.getAddress());
			ps1.setString(5, user.getSdt());
			ps1.setLong(6, user.getAreaId());
			int res1 = ps1.executeUpdate();
			ResultSet generatedKeys = ps1.getGeneratedKeys();
			if (generatedKeys.next()) {
				user.setIdUser(generatedKeys.getLong(1));
			}

			if (res1 != 0) {
				ps3.setLong(1, user.getIdUser());
				ps3.executeUpdate();
				ps2.setString(1, user.getUsername());
				ps2.setString(2, user.getPassword());
				ps2.setLong(3, user.getIdUser());
				ps2.executeUpdate();
			}
			con.commit();
		} catch (SQLException e) {
			con.rollback();
		} finally {
			con.setAutoCommit(true);
		}

	}

//	public void addUserTest(user user) throws SQLException {
//
//		String[] names = user.getFullname().split(" ");
//		String sql1 = "INSERT INTO user values(null, ? , ?, null, ?, ?, ?, null, ?)";
//		String sql2 = "INSERT INTO account values(null, ?, ?, ?)";
//		String sql3 = "INSERT INTO user_role values(?, 1)";
//		PreparedStatement ps1;
//		try {
//			con.setAutoCommit(false);
//			ps1 = con.prepareStatement(sql1, StatementImpl.RETURN_GENERATED_KEYS);
//			PreparedStatement ps2 = con.prepareStatement(sql2);
//			PreparedStatement ps3 = con.prepareStatement(sql3);
//			ps1.setLong(1, user.getIdentityNum());
//			ps1.setString(2, names[names.length - 1]);
//			ps1.setString(3, names[0]);
//			ps1.setString(4, user.getAddress());
//			ps1.setString(5, user.getSdt());
//			ps1.setLong(6, user.getAreaId());
//			int res1 = ps1.executeUpdate();
//			ResultSet generatedKeys = ps1.getGeneratedKeys();
//			if (generatedKeys.next()) {
//				user.setIdUser(generatedKeys.getLong(1));
//			}
//
//			if (res1 != 0) {
//				ps3.setLong(1, user.getIdUser());
//				ps3.executeUpdate();
//				ps2.setString(1, user.getUsername());
//				ps2.setString(2, user.getPassword());
//				ps2.setLong(3, user.getIdUser());
//				ps2.executeUpdate();
//			}
//		} catch (SQLException e) {
//			con.rollback();
//		} finally {
//			con.rollback();
//			con.setAutoCommit(true);
//
//		}
//
//	}

	public boolean checkUsername(String username) {
		boolean result = true;
		String sql = "SELECT username FROM account WHERE username = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {

				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getRole(long id) {
		String query = "SELECT role_name as role FROM user u INNER JOIN user_role ur INNER JOIN role r ON ur.role_id=r.role_id ON u.userid = ur.userid where u.userid=?;";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString("role");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public User findById(long id) {
		User u = new User();
		u.setIdUser(id);
		String query = "SELECT * FROM user WHERE userid=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u.setAddress(rs.getString("address"));
				u.setFullname(rs.getString("lastName") + " " + rs.getString("firstName"));
				u.setSdt(rs.getString("phoneNum"));
				u.setAreaId(rs.getLong("areaId"));
				u.setRole(getRole(id));
				u.setIdentityNum(rs.getLong("identityNumber"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return u;
	}

	public List<User> findAll() {
		List<User> us = new ArrayList<>();

		String query = "SELECT * FROM user";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setIdUser(rs.getLong("userid"));
				u.setAddress(rs.getString("address"));
				u.setFullname(rs.getString("lastName") + " " + rs.getString("firstName"));
				u.setSdt(rs.getString("phoneNum"));
				u.setAreaId(rs.getLong("areaId"));
				u.setRole(getRole(rs.getLong("userid")));
				u.setIdentityNum(rs.getLong("identityNumber"));
				us.add(u);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return us;
	}
}
