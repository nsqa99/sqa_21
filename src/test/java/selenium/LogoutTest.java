package selenium;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutTest {
	WebDriver driver;

	@BeforeEach
	public void setUp() throws InterruptedException {
//		System.setProperty("webdriver.gecko.driver", "/home/nsqa/ptit/sqa/geckodriver");
//		this.driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "/home/nsqa/ptit/sqa/chromedriver/chromedriver");
		this.driver = new ChromeDriver();
		this.driver.get("http://localhost:8080/SQA/show_Login");

	}

	@AfterEach
	public void tearDown() {
		this.driver.quit();
	}

	@Test
	public void testLogoutAdmin() {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernamelog")));
		txtUsername.sendKeys("nsqa");
		WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordlog")));
		txtPassword.sendKeys("1" + Keys.ENTER);
		WebElement btnU = wait.until(ExpectedConditions.elementToBeClickable(By.id("userDropdown")));
//		btnU.click();
		executor.executeScript("arguments[0].click();", btnU);
		WebElement btnLogout = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='dropdown-item']")));
//		btnLogout.click();
		executor.executeScript("arguments[0].click();", btnLogout);
		WebElement btnConfirm = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='logoutModal']//a[@aria-label='Logout']")));
//		btnConfirm.click();
		Actions action=new Actions(driver);
		action.moveToElement(btnConfirm).click().perform();
//		executor.executeScript("arguments[0].click();", btnConfirm);
		String expect = "http://localhost:8080/SQA/show_Login";
		String actual = driver.getCurrentUrl();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testLogoutUser() {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Actions action=new Actions(driver);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernamelog")));
		txtUsername.sendKeys("lta");
		WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordlog")));
		txtPassword.sendKeys("1" + Keys.ENTER);
		WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("dropbtn")));//button[@class='dropbtn']
		
		action.moveToElement(we).moveToElement(we).perform();
		WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='tab-button']")));
		logout.click();
		
		String expect = "http://localhost:8080/SQA/show_Login";
		String actual = driver.getCurrentUrl();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testCancelLogoutAdmin() {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernamelog")));
		txtUsername.sendKeys("nsqa");
		WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordlog")));
		txtPassword.sendKeys("1" + Keys.ENTER);
		WebElement btnU = wait.until(ExpectedConditions.elementToBeClickable(By.id("userDropdown")));
//		btnU.click();
		executor.executeScript("arguments[0].click();", btnU);
		WebElement btnLogout = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='dropdown-item']")));
//		btnLogout.click();
		executor.executeScript("arguments[0].click();", btnLogout);
		WebElement btnConfirm = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='logoutModal']//button[@aria-label='Cancel']")));
//		btnConfirm.click();
		executor.executeScript("arguments[0].click();", btnConfirm);
		String expect = "http://localhost:8080/SQA/admin";
		String actual = driver.getCurrentUrl();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testCloseLogoutAdmin() {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernamelog")));
		txtUsername.sendKeys("nsqa");
		WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordlog")));
		txtPassword.sendKeys("1" + Keys.ENTER);
		WebElement btnU = wait.until(ExpectedConditions.elementToBeClickable(By.id("userDropdown")));
//		btnU.click();
		executor.executeScript("arguments[0].click();", btnU);
		WebElement btnLogout = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='dropdown-item']")));
//		btnLogout.click();
		executor.executeScript("arguments[0].click();", btnLogout);
		WebElement btnConfirm = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='logoutModal']//button[@aria-label='Close']")));
//		btnConfirm.click();
		executor.executeScript("arguments[0].click();", btnConfirm);
		String expect = "http://localhost:8080/SQA/admin";
		String actual = driver.getCurrentUrl();
		assertEquals(expect, actual);
	}
	
}
