package selenium;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;

public class UpdateConsumedAmountTest {
	WebDriver driver;
//	JavascriptExecutor executor;

	@BeforeEach
	public void setUp() throws InterruptedException {
//		System.setProperty("webdriver.gecko.driver", "/home/nsqa/ptit/sqa/geckodriver");
//		this.driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "/home/nsqa/ptit/sqa/chromedriver/chromedriver");
		this.driver = new ChromeDriver();
//        this.executor = (JavascriptExecutor)driver;
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
	public void testUpdateSuccess() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		WebElement btnUpdate = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btn-update-13")));
//    	btnUpdate.click();
		executor.executeScript("arguments[0].click();", btnUpdate);
		WebElement txtAmount = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='amount']")));
		txtAmount.clear();
		txtAmount.sendKeys("100");
		WebElement btnConfirmUpdate = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
//    	btnConfirmUpdate.click();
		executor.executeScript("arguments[0].click();", btnConfirmUpdate);
		WebElement consumedAmount = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='consumed-amount-1-05/2021']")));

		String expect = "100";
		String actual = consumedAmount.getText();
		assertEquals(expect, actual);
	}

	@Test
	public void testUpdateBlank() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		WebElement btnUpdate = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btn-update-13")));
		btnUpdate.click();
		executor.executeScript("arguments[0].click();", btnUpdate);
		WebElement txtAmount = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='amount']")));
		txtAmount.clear();
		WebElement btnConfirmUpdate = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='updateModal']//button[@aria-label='Update']")));
//    	btnConfirmUpdate.click();
		executor.executeScript("arguments[0].click();", btnConfirmUpdate);
//    	WebElement consumedAmount = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='consumed-amount-1-05/2021']")));

		String expect = "Please fill out this field.";
		String actual = txtAmount.getAttribute("validationMessage");
		assertEquals(expect, actual);
	}

	@Test
	public void testUpdateWrongFormatZero() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement btnUpdate = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-update-13")));
//    	btnUpdate.click();
		executor.executeScript("arguments[0].click();", btnUpdate);

		WebElement txtAmount = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='amount']")));
		txtAmount.clear();
		txtAmount.sendKeys("0");
		WebElement btnConfirmUpdate = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='updateModal']//button[@aria-label='Update']")));
//    	btnConfirmUpdate.click();
		executor.executeScript("arguments[0].click();", btnConfirmUpdate);
//    	WebElement consumedAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='consumed-amount-1-05/2021']")));

		String expect = "Value must be greater than or equal to 1.";
		String actual = txtAmount.getAttribute("validationMessage");
		assertEquals(expect, actual);
	}

	@Test
	public void testUpdateWrongFormatNegative() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement btnUpdate = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-update-13")));
//    	btnUpdate.click();
		executor.executeScript("arguments[0].click();", btnUpdate);

		WebElement txtAmount = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='amount']")));
		txtAmount.clear();
		txtAmount.sendKeys("-1");
		WebElement btnConfirmUpdate = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='updateModal']//button[@aria-label='Update']")));
		executor.executeScript("arguments[0].click();", btnConfirmUpdate);
//    	btnConfirmUpdate.click();
//    	WebElement consumedAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='consumed-amount-1-05/2021']")));

		String expect = "Value must be greater than or equal to 1.";
		String actual = txtAmount.getAttribute("validationMessage");
		assertEquals(expect, actual);
	}

	@Test
	public void testUpdateWrongFormatNotNumber() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement btnUpdate = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-update-13")));
//    	btnUpdate.click();
		executor.executeScript("arguments[0].click();", btnUpdate);

		WebElement txtAmount = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='amount']")));
		txtAmount.clear();
		txtAmount.sendKeys("abc");
		WebElement btnConfirmUpdate = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='updateModal']//button[@aria-label='Update']")));
		executor.executeScript("arguments[0].click();", btnConfirmUpdate);
//    	btnConfirmUpdate.click();
//    	WebElement consumedAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='consumed-amount-1-05/2021']")));

		String expect = "Please fill out this field.";
		String actual = txtAmount.getAttribute("validationMessage");
		assertEquals(expect, actual);
	}

	@Test
	public void testCancelUpdate() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement btnUpdate = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-update-13")));
		executor.executeScript("arguments[0].click();", btnUpdate);
		WebElement btnCancelUpdate = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='updateModal']//button[@aria-label='Cancel']")));
		executor.executeScript("arguments[0].click();", btnCancelUpdate);

		Boolean actual = wait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updateModal']")));
		boolean expect = true;
		assertEquals(expect, actual);
	}


	@Test
	public void testAddFailWithWrongMonthFormat() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement btnAdd = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-target='#addModal']")));
//		btnAdd.click();
		executor.executeScript("arguments[0].click();", btnAdd);
		WebElement month = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='month']")));
		month.sendKeys("April 2021");

		String expect = "";
		String actual = month.getText();
		assertEquals(expect, actual);
	}

	@Test
	public void testAddFailMonthBlank() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement btnAdd = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-target='#addModal']")));
//		btnAdd.click();
		executor.executeScript("arguments[0].click();", btnAdd);

		WebElement month = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='month']")));
		WebElement btnConfirm = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='addModal']//button[@aria-label='Add']")));
//    	btnConfirm.click();
		executor.executeScript("arguments[0].click();", btnConfirm);
		String expect = "Please fill out this field.";
		String actual = month.getAttribute("validationMessage");
		assertEquals(expect, actual);
	}

	@Test
	public void testCancelAddMonth() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement btnAdd = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-target='#addModal']")));
//		btnAdd.click();
		executor.executeScript("arguments[0].click();", btnAdd);
//		WebElement month = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='month']")));
		WebElement btnCancel = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='addModal']//button[@aria-label='Cancel']")));
//		btnCancel.click();
		executor.executeScript("arguments[0].click();", btnCancel);

		Boolean actual = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='addModal']")));
		boolean expect = true;
		assertEquals(expect, actual);
	}
	
	@Test
	public void testCloseUpdate() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement btnUpdate = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-update-13")));
//		btnUpdate.click();
		executor.executeScript("arguments[0].click();", btnUpdate);
		WebElement btnClose = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='updateModal']//button[@aria-label='Close']")));
//    	btnClose.click();
		executor.executeScript("arguments[0].click();", btnClose);

		Boolean actual = wait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updateModal']")));
		boolean expect = true;
		assertEquals(expect, actual);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testCloseMonthAdd() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement btnUpdate = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-update-14")));
//		btnUpdate.click();
		executor.executeScript("arguments[0].click();", btnUpdate);
		WebElement div = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='addModal']")));
		if (div.isDisplayed()) {
			WebElement btnClose = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='addModal']//button[@class='close']")));
			executor.executeScript("arguments[0].click();", btnClose);
		} else {
			assertEquals(true, false);
		}
		Boolean actual = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='addModal']")));
		boolean expect = true;
		assertEquals(expect, actual);

	}

	@Test
	public void testAddSucess() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement btnAdd = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-target='#addModal']")));
//		btnAdd.click();
		executor.executeScript("arguments[0].click();", btnAdd);
		WebElement month = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='month']")));
		month.clear();
		executor.executeScript("arguments[0].setAttribute('value', '2021-06');", month);
		WebElement btnConfirm = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='addModal']//button[@aria-label='Add']")));
//    	btnConfirm.click();
		executor.executeScript("arguments[0].click();", btnConfirm);
		List<WebElement> tr = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody//tr")));
		int expect = 6;
		int actual = tr.size();
		assertEquals(expect, actual);
	}

}
