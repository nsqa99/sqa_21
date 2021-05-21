import dao.ElecPriceDAO;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import model.ElectricityPrice;
import static org.junit.Assert.*;
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
public class ElecPriceDAOTest {
	private ElecPriceDAO dao = new ElecPriceDAO();
	private Connection con = dao.con;

	public ElecPriceDAOTest() {

	}

	@Test
	public void testFindByIdUrban() {
		long id = 1;
		ElectricityPrice result = dao.findById(id);
		ElectricityPrice expect = new ElectricityPrice();
		expect.setId(id);
		expect.setArea("Thành phố");
		ArrayList<Integer> levels = new ArrayList<>();
		levels.add(1678);
		levels.add(1734);
		levels.add(2014);
		levels.add(2536);
		levels.add(2834);
		levels.add(2927);
		expect.setLevels(levels);
		assertEquals(expect, result);
	}

	@Test
	public void testFindByIdRural() {
		long id = 2;
		ElectricityPrice result = dao.findById(id);
		ElectricityPrice expect = new ElectricityPrice();
		expect.setId(id);
		expect.setArea("Nông thôn");
		ArrayList<Integer> levels = new ArrayList<>();
		levels.add(1403);
		levels.add(1459);
		levels.add(1590);
		levels.add(1971);
		levels.add(2231);
		levels.add(2323);
		expect.setLevels(levels);
		assertEquals(expect, result);
	}

	@Test
	public void testFindByIdNotFound() {
		long id = 3;
		ElectricityPrice result = dao.findById(id);
		assertNull(result.getArea());
	}

	@Test
	public void testFindAll() {
		ArrayList<ElectricityPrice> result = dao.findAll();

		ElectricityPrice urban = new ElectricityPrice();
		urban.setId(1);
		urban.setArea("Thành phố");
		ArrayList<Integer> levelsU = new ArrayList<>();
		levelsU.add(1678);
		levelsU.add(1734);
		levelsU.add(2014);
		levelsU.add(2536);
		levelsU.add(2834);
		levelsU.add(2927);
		urban.setLevels(levelsU);

		ElectricityPrice rural = new ElectricityPrice();
		rural.setId(2);
		rural.setArea("Nông thôn");
		ArrayList<Integer> levels = new ArrayList<>();
		levels.add(1403);
		levels.add(1459);
		levels.add(1590);
		levels.add(1971);
		levels.add(2231);
		levels.add(2323);
		rural.setLevels(levels);

		ArrayList<ElectricityPrice> expect = new ArrayList<>();
		expect.add(urban);
		expect.add(rural);
		assertEquals(expect, result);
	}

	@Test
	public void testInsertOne() {
		ArrayList<ElectricityPrice> records = dao.findAll();
		int numOfRecords = records.size();
		String area = "Test area";
		ArrayList<Integer> levels = new ArrayList<>();
		levels.add(1);
		levels.add(2);
		levels.add(3);
		levels.add(4);
		levels.add(5);
		levels.add(6);
		try {
			con.setAutoCommit(false);
			dao.insertOne(area, levels);
			ArrayList<ElectricityPrice> newRecs = dao.findAll();
			ElectricityPrice newOne = newRecs.get(newRecs.size() - 1);
			assertEquals(numOfRecords + 1, newRecs.size());
			assertEquals(area, newOne.getArea());
			assertEquals(levels, newOne.getLevels());
		} catch (Exception e) {
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

	@Test(expected = NullPointerException.class)
	public void testInsertOneFail() throws Exception {
		String area = "Test fail area";
		ArrayList<Integer> levels = new ArrayList<>();
		levels.add(null);
		levels.add(2);
		levels.add(3);
		levels.add(4);
		levels.add(5);
		levels.add(6);
		con.setAutoCommit(false);
		dao.insertOne(area, levels);
		con.rollback();
		con.setAutoCommit(true);

	}

	@Test(expected = Exception.class)
	public void testInsertOneFailWithNegativeNumber() throws Exception {
		String area = "Test area fail negative";
		ArrayList<Integer> levels = new ArrayList<>();
		levels.add(-1);
		levels.add(2);
		levels.add(3);
		levels.add(4);
		levels.add(5);
		levels.add(6);
		con.setAutoCommit(false);
		dao.insertOne(area, levels);
		con.rollback();
		con.setAutoCommit(true);

	}

	@Test
	public void testUpdateOne() {
		long id = 1;
		String area = "Test update";
		ArrayList<Integer> levels = new ArrayList<>();
		levels.add(1123);
		levels.add(2);
		levels.add(3);
		levels.add(4);
		levels.add(5);
		levels.add(6);
		ElectricityPrice newPrice = new ElectricityPrice();
		newPrice.setId(id);
		newPrice.setArea(area);
		newPrice.setLevels(levels);
		try {
			con.setAutoCommit(false);
			dao.updateById(id, area, levels);
			ElectricityPrice newOne = dao.findById(id);
			assertEquals(newPrice, newOne);
		} catch (Exception e) {
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

	@Test(expected = SQLException.class)
	public void testUpdateFailIdNotFound() throws Exception {
		long id = -1;
		String area = "Test update fail";
		ArrayList<Integer> levels = new ArrayList<>();
		levels.add(1);
		levels.add(2);
		levels.add(3);
		levels.add(4);
		levels.add(5);
		levels.add(6);
		con.setAutoCommit(false);
		dao.updateById(id, area, levels);
		con.rollback();
		con.setAutoCommit(true);
	}
}
