package selenium;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
	WebDriver driver;

    @BeforeEach
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","/home/nsqa/ptit/sqa/chromedriver/chromedriver");
        ChromeOptions option=new ChromeOptions();
		option.setPageLoadStrategy(PageLoadStrategy.EAGER);
		this.driver = new ChromeDriver(option);
        this.driver.get("http://localhost:8080/SQA/show_Login");
    }
    
    @AfterEach
    public  void tearDown() {
        this.driver.quit();
    }
    
    @Test
    public void testLoginUserSuccess() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernamelog")));
        txtUsername.sendKeys("lta");
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordlog")));
        txtPassword.sendKeys("1" + Keys.ENTER);

        String expect = "http://localhost:8080/SQA/main";
        String actual = driver.getCurrentUrl();
        Assertions.assertEquals(expect, actual);
    }
    
    @Test
    public void testLoginAdminSuccess() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernamelog")));
        txtUsername.sendKeys("admin");
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordlog")));
        txtPassword.sendKeys("1" + Keys.ENTER);

        String expect= "http://localhost:8080/SQA/admin";
        String actual= driver.getCurrentUrl();
        Assertions.assertEquals(expect, actual);
    }
    
    @Test
    public void testLoginWithBlankUsername() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordlog")));
        txtPassword.sendKeys("1" + Keys.ENTER);
        WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernamelog")));
        
        String expect= "Please fill out this field.";
        String actual = txtUsername.getAttribute("validationMessage");

        
        Assertions.assertEquals(expect, actual);
    }
    
    @Test
    public void testLoginWithBlankPassword() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernamelog")));
        txtUsername.sendKeys("nsqa" + Keys.ENTER);
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordlog")));
        
        String expect= "Please fill out this field.";
        String actual = txtPassword.getAttribute("validationMessage");

        Assertions.assertEquals(expect, actual);
    }
    
    @Test
    public void testLoginFailRightUsnWrongPw() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernamelog")));
        txtUsername.sendKeys("nsqa");
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordlog")));
        txtPassword.sendKeys("123" + Keys.ENTER);
        WebElement txtError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error_msg")));

        String expect= "*Tên đăng nhập hoặc mật khẩu bị sai, vui lòng thử lại";
        String actual= txtError.getText();
        Assertions.assertEquals(expect, actual);
    }
    
    @Test
    public void testLoginFailWrongUsnRightPw() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernamelog")));
        txtUsername.sendKeys("test");
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordlog")));
        txtPassword.sendKeys("1" + Keys.ENTER);
        WebElement txtError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error_msg")));

        String expect= "*Tên đăng nhập hoặc mật khẩu bị sai, vui lòng thử lại";
        String actual= txtError.getText();
        Assertions.assertEquals(expect, actual);
    }
    
    @Test
    public void testLoginFailWrongUsnWrongPw() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernamelog")));
        txtUsername.sendKeys("test");
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordlog")));
        txtPassword.sendKeys("123" + Keys.ENTER);
        WebElement txtError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error_msg")));

        String expect= "*Tên đăng nhập hoặc mật khẩu bị sai, vui lòng thử lại";
        String actual= txtError.getText();
        Assertions.assertEquals(expect, actual);
    }
}
