import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import utils.Calculator;

public class CalculatorUtilTest {
	
	@Test
	public void testNegativeAmount() {
		ArrayList<Integer> expect = new ArrayList<>();
		ArrayList<Integer> result = Calculator.calculateAmountLevels(-1);
		assertEquals(expect, result);
	}
	
	@Test
	public void testZeroAmount() {
		ArrayList<Integer> expect = new ArrayList<>(){{
			add(0);
		}};
		ArrayList<Integer> result = Calculator.calculateAmountLevels(0);
		assertEquals(expect, result);
	}
	
	@Test
	public void testAmountLessThan50() {
		ArrayList<Integer> expect = new ArrayList<>(){{
			add(12);
		}};
		ArrayList<Integer> result = Calculator.calculateAmountLevels(12);
		assertEquals(expect, result);
	}
	
	@Test
	public void testAmountMoreThan50AndLessThan100() {
		ArrayList<Integer> expect = new ArrayList<>(){{
			add(50);
			add(1);
		}};
		ArrayList<Integer> result = Calculator.calculateAmountLevels(51);
		assertEquals(expect, result);
	}
	
	
	@Test
	public void testAmountMoreThan100() {
		ArrayList<Integer> expect = new ArrayList<>(){{
			add(50);
			add(50);
			add(1);
		}};
		ArrayList<Integer> result = Calculator.calculateAmountLevels(101);
		assertEquals(expect, result);
	}
	
	@Test
	public void testAmountMoreThan200() {
		ArrayList<Integer> expect = new ArrayList<>(){{
			add(50);
			add(50);
			add(100);
			add(1);
		}};
		ArrayList<Integer> result = Calculator.calculateAmountLevels(201);
		assertEquals(expect, result);
	}
	
	@Test
	public void testAmountMoreThan300() {
		ArrayList<Integer> expect = new ArrayList<>(){{
			add(50);
			add(50);
			add(100);
			add(100);
			add(1);
		}};
		ArrayList<Integer> result = Calculator.calculateAmountLevels(301);
		assertEquals(expect, result);
	}
	
	@Test
	public void testAmountMoreThan400() {
		ArrayList<Integer> expect = new ArrayList<>(){{
			add(50);
			add(50);
			add(100);
			add(100);
			add(100);
			add(1);
		}};
		ArrayList<Integer> result = Calculator.calculateAmountLevels(401);
		assertEquals(expect, result);
	}
	
}
