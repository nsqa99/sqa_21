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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandInMoneyTest {
	WebDriver driver;
	JavascriptExecutor executor;
	
	@BeforeEach
	public void setUp() throws InterruptedException {
//		System.setProperty("webdriver.gecko.driver", "/home/nsqa/ptit/sqa/geckodriver");
//		this.driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "/home/nsqa/ptit/sqa/chromedriver/chromedriver");
		this.driver = new ChromeDriver();
		this.driver.get("http://localhost:8080/SQA/show_Login");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernamelog")));
		txtUsername.sendKeys("nsqa");
		WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordlog")));
		txtPassword.sendKeys("1" + Keys.ENTER);
		
	}
	
	@AfterEach
	public void tearDown() {
		this.driver.quit();
	}
	
	@Test
	public void testHandinMoneySuccess() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement btnHandin = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btn-handin-13")));
		executor.executeScript("arguments[0].click();", btnHandin);
		WebElement btnConfirm = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='payModal']//button[@aria-label='Handin']")));
		executor.executeScript("arguments[0].click();", btnConfirm);
		WebElement pay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paid-amount")));
		WebElement debt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("debt")));
		String expect = debt.getText();
		String actual = pay.getText();
		assertEquals(expect, actual);
	}
	
	@Test
	public void cancelHandin() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement btnUpdate = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-handin-14")));
		executor.executeScript("arguments[0].click();", btnUpdate);
		WebElement btnCancelUpdate = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='payModal']//button[@aria-label='Cancel']")));
		executor.executeScript("arguments[0].click();", btnCancelUpdate);

		Boolean actual = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='payModal']")));
		boolean expect = true;
		assertEquals(expect, actual);
	}
	
	@Test
	public void closeHandin() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement btnUpdate = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-handin-14")));
//		btnUpdate.click();
		executor.executeScript("arguments[0].click();", btnUpdate);
		WebElement btnClose = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='payModal']//button[@aria-label='Close']")));
//    	btnClose.click();
		executor.executeScript("arguments[0].click();", btnClose);

		Boolean actual = wait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='payModal']")));
		boolean expect = true;
		assertEquals(expect, actual);
		assertEquals(expect, actual);
	}
}
