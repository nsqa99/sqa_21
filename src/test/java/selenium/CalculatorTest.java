package selenium;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorTest {
	WebDriver driver;
	JavascriptExecutor executor;

	@BeforeEach
	public void setUp() throws InterruptedException {
//		System.setProperty("webdriver.gecko.driver", "/home/nsqa/ptit/sqa/geckodriver");
//		this.driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "/home/nsqa/ptit/sqa/chromedriver/chromedriver");
		
		ChromeOptions option=new ChromeOptions();
		option.setPageLoadStrategy(PageLoadStrategy.NONE);
		this.driver = new ChromeDriver(option);
		executor = (JavascriptExecutor) driver;
		this.driver.get("http://localhost:8080/SQA/show_Login");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernamelog")));
		txtUsername.sendKeys("lta");
		WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordlog")));
		txtPassword.sendKeys("1" + Keys.ENTER);
	}
	
	@AfterEach
	public void tearDown() {
		this.driver.quit();
	}
	
	@Test
	public void testCalculateZeroUrban() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-urban")));
		btn.click();
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("urban-payment__total")));
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String expect = "0";
		String actual = total.getText();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testCalculateNeagtiveUrban() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-urban")));
		amount.clear();
		amount.sendKeys("-1");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-urban")));
		btn.click();
		String expect= "Value must be greater than or equal to 0.";
        String actual = amount.getAttribute("validationMessage");

        assertEquals(expect, actual);
	}
	
	
	@Test
	public void testCalculate1LevelUrban() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-urban")));
		amount.clear();
		amount.sendKeys("49");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-urban")));
		btn.click();
//		String expect = "90,444";
//		String actual = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("urban-payment__total"))).getText();
//		assertEquals(expect, actual);
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("urban-payment__total")));
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String expect = "90,444";
		String actual = total.getText();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testCalculate2LevelUrban() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-urban")));
		amount.clear();
		amount.sendKeys("51");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-urban")));
		btn.click();
//		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("urban-payment__total")));
//		Actions actions = new Actions(driver);
//		actions.moveToElement(total).perform();
//		String expect = "94,197";
//		String actual = total.getText();
//		assertEquals(expect, actual);
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("urban-payment__total")));
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String expect = "94,197";
		String actual = total.getText();
		assertEquals(expect, actual);
		
	}
	
	@Test
	public void testCalculate3LevelUrban() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-urban")));
		amount.clear();
		amount.sendKeys("101");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-urban")));
		btn.click();
//		Thread.sleep(1000);
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("urban-payment__total")));
		String expect = "189,875";
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String actual = total.getText();
		assertEquals(expect, actual);
		
	}
	
	@Test
	public void testCalculate4LevelUrban() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-urban")));
		amount.clear();
		amount.sendKeys("201");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-urban")));
		btn.click();
//		String expect = "411,990";
//		String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("urban-payment__total"))).getText();
//		assertEquals(expect, actual);
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("urban-payment__total")));
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String expect = "411,990";
		String actual = total.getText();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testCalculate5LevelUrban() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-urban")));
		amount.clear();
		amount.sendKeys("301");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-urban")));
		btn.click();
//		String expect = "691,277";
//		String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("urban-payment__total"))).getText();
//		assertEquals(expect, actual);
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("urban-payment__total")));
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String expect = "691,277";
		String actual = total.getText();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testCalculate6LevelUrban() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-urban")));
		amount.clear();
		amount.sendKeys("401");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-urban")));
		btn.click();
//		String expect = "1,003,120";
//		String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("urban-payment__total"))).getText();
//		assertEquals(expect, actual);
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("urban-payment__total")));
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String expect = "1,003,120";
		String actual = total.getText();
		assertEquals(expect, actual);
	}
	
	
	@Test
	public void testCalculateZeroRural() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement btnMove = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnR")));
		btnMove.click();
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-rural")));
		amount.clear();
		amount.sendKeys("0");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-rural")));
		btn.click();
//		String expect = "0";
//		String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rural-payment__total"))).getText();
//		assertEquals(expect, actual);
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("rural-payment__total")));
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String expect = "0";
		String actual = total.getText();
		assertEquals(expect, actual);
	}
	
	
	@Test
	public void testCalculateNeagtiveRural() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement btnMove = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnR")));
		btnMove.click();
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-rural")));
		amount.clear();
		amount.sendKeys("-1");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-rural")));
		btn.click();
		String expect= "Value must be greater than or equal to 0.";
        String actual = amount.getAttribute("validationMessage");

        assertEquals(expect, actual);
	}
	
	@Test
	public void testCalculate1LevelRural() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement btnMove = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnR")));
		btnMove.click();
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-rural")));
		amount.clear();
		amount.sendKeys("49");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-rural")));
		btn.click();
//		String expect = "75,622";
//		String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rural-payment__total"))).getText();
//		assertEquals(expect, actual);
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("rural-payment__total")));
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String expect = "75,622";
		String actual = total.getText();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testCalculate2LevelRural() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement btnMove = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnR")));
//		btnMove.click();
		executor.executeScript("arguments[0].click();", btnMove);
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-rural")));
		amount.clear();
		amount.sendKeys("51");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-rural")));
//		btn.click();
		executor.executeScript("arguments[0].click();", btn);
//		String expect = "78,770";
//		String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rural-payment__total"))).getText();
//		assertEquals(expect, actual);\
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("rural-payment__total")));
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String expect = "78,770";
		String actual = total.getText();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testCalculate3LevelRural() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement btnMove = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnR")));
		btnMove.click();
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-rural")));
		amount.clear();
		amount.sendKeys("101");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-rural")));
		btn.click();
//		String expect = "159,159";
//		String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rural-payment__total"))).getText();
//		assertEquals(expect, actual);
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("rural-payment__total")));
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String expect = "159,159";
		String actual = total.getText();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testCalculate4LevelRural() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement btnMove = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnR")));
		btnMove.click();
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-rural")));
		amount.clear();
		amount.sendKeys("201");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-rural")));
		btn.click();
//		String expect = "334,478";
//		String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rural-payment__total"))).getText();
//		assertEquals(expect, actual);
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("rural-payment__total")));
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String expect = "334,478";
		String actual = total.getText();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testCalculate5LevelRural() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement btnMove = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnR")));
		btnMove.click();
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-rural")));
		amount.clear();
		amount.sendKeys("301");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-rural")));
		btn.click();
//		String expect = "551,574";
//		String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rural-payment__total"))).getText();
//		assertEquals(expect, actual);
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("rural-payment__total")));
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String expect = "551,574";
		String actual = total.getText();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testCalculate6LevelRural() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cal = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tính tiền điện")));
		cal.click();
		Thread.sleep(2000);
		WebElement btnMove = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnR")));
		btnMove.click();
		WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("consumed-rural")));
		amount.clear();
		amount.sendKeys("401");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-rural")));
		btn.click();
//		String expect = "797,085";
//		String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rural-payment__total"))).getText();
//		assertEquals(expect, actual);
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("rural-payment__total")));
		Actions actions = new Actions(driver);
		actions.moveToElement(total).perform();
		String expect = "797,085";
		String actual = total.getText();
		assertEquals(expect, actual);
	}
	
}
