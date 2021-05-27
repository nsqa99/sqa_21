package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupTest {
	WebDriver driver;

	@BeforeEach
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","/home/nsqa/ptit/sqa/chromedriver/chromedriver");
        this.driver = new ChromeDriver();
		this.driver.get("http://localhost:8080/SQA/show_SignUp");
	}

	@AfterEach
	public void tearDown() {
		this.driver.quit();
	}

	// test dang ki thanh cong, area = Thanh pho
	@Test
	public void testSignupSuccessUrban() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("Quanganh123");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("Quanganh123");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("HN");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("1234567891");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		String expect ="http://localhost:8080/SQA/show_Login";
		String actual = driver.getCurrentUrl();
		Assertions.assertEquals(expect, actual);
	}

	// test dang ki thanh cong, area = Nong thon
	@Test
	public void testSignupSuccessRural() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest2");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test2");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("Quanganh123");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("Quanganh123");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("HN");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("1234567891");
		WebElement radioRural = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-rural")));
		radioRural.click();
		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		String expect ="http://localhost:8080/SQA/show_Login";
		String actual = driver.getCurrentUrl();
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupUsernameBlank() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("@Quanganh123");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("@Quanganh123");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		String expect = "Please fill out this field.";
		String actual = txtUsername.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupFullnameBlank() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));

		txtUsername.sendKeys("testusn");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("@Quanganh123");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("@Quanganh123");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		String expect = "Please fill out this field.";
		String actual = txtFullname.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupIdentityBlank() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");

		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("@Quanganh123");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("@Quanganh123");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));

		String expect = "Please fill out this field.";
		String actual = txtIden.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupPasswordBlank() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");

		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("@Quanganh123");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));

		String expect = "Please fill out this field.";
		String actual = txtPw.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupRePasswordBlank() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("@Quanganh123");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));

		String expect = "Please fill out this field.";
		String actual = txtRepw.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupAddressBlank() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("@Quanganh123");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("@Quanganh123");

		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));

		String expect = "Please fill out this field.";
		String actual = txtAddr.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupPhoneBlank() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("@Quanganh123");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("@Quanganh123");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));

		String expect = "Please fill out this field.";
		String actual = txtPhone.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupWrongIdentityLengthFormat8() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("12345678"); // 8 chu so
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("@Quanganh123");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("@Quanganh123");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		String expect = "Please match the requested format.";
		String actual = txtIden.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupWrongIdentityLengthFormat10() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("1234567891"); // 10 chu so
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("@Quanganh123");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("@Quanganh123");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		String expect = "Please match the requested format.";
		String actual = txtIden.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupWrongIdentityLengthFormat13() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("1234567891234"); // 13 chu so
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("@Quanganh123");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("@Quanganh123");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		String expect = "Please match the requested format.";
		String actual = txtIden.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupInvalidUsername() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("nsqa");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("@Quanganh123");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("@Quanganh123");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		WebElement error = wait.until(ExpectedConditions.elementToBeClickable(By.id("error_signup")));

		String expect = "*Tên tài khoản đã tồn tại";
		String actual = error.getText();
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupInvalidPasswordLengthFormat() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("@Qua1"); // length < 8
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("@Qua1");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		String expect = "Please match the requested format.";
		String actual = txtPw.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupInvalidPasswordCapFormat() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("@quanganh1"); // khong co ki tu in hoa
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("@quanganh1");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		String expect = "Please match the requested format.";
		String actual = txtPw.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupInvalidPasswordNormalFormat() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("@QUANGANH1"); // khong co ki tu in thuong
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("@QUANGANH1");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		String expect = "Please match the requested format.";
		String actual = txtPw.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupInvalidPasswordNumberFormat() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("nsqaYltd"); // khong co chu so
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("nsqaYltd");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		String expect = "Please match the requested format.";
		String actual = txtPw.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void testSignupRepassNotMatch() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("nsqaYltd1");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("nsqaYltd");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("0123456789");

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		WebElement error = wait.until(ExpectedConditions.elementToBeClickable(By.id("error_signup")));

		String expect = "*Mật khẩu nhập lại không khớp";
		String actual = error.getText();
		Assertions.assertEquals(expect, actual);
	}
	
	@Test
	public void testSignupInvalidPhoneLength9() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("nsqaYltd1");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("nsqaYltd1");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("012345678"); //9 chu so

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		String expect = "Please match the requested format.";
		String actual = txtPhone.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}
	
	@Test
	public void testSignupInvalidPhoneLength11() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameSign")));
		txtUsername.sendKeys("usernametest");
		WebElement txtFullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fullnameSign")));
		txtFullname.sendKeys("Full Name Test");
		WebElement txtIden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity")));
		txtIden.sendKeys("123456789");
		WebElement txtPw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passSign")));
		txtPw.sendKeys("nsqaYltd1");
		WebElement txtRepw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rePassSign")));
		txtRepw.sendKeys("nsqaYltd1");
		WebElement txtAddr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addressSign")));
		txtAddr.sendKeys("Hà Đông, Hà Nội");
		WebElement txtPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneSign")));
		txtPhone.sendKeys("01234567891"); //11 chu so

		WebElement btnSignup = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignup")));
		btnSignup.click();

		String expect = "Please match the requested format.";
		String actual = txtPhone.getAttribute("validationMessage");
		Assertions.assertEquals(expect, actual);
	}

}
