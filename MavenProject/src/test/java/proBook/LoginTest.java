package proBook;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	
	WebDriver driver;
	String url = "http://probook.progideo.com";
	String expectedTitle1 = "Fil d'actualités - ProBook";
	String actualTitle1 = null;
	String actualTitle2 = null;
	
	@DataProvider(name = "Data1")
	public static Object[][] data1() {
		return new Object[][] {{ "ly-maroc", "yumi16061995", "Fil d'actualités - ProBook" }};
	}
	@Test(dataProvider = "Data1")
	public void login(String username, String password, String expectedTitle2) throws InterruptedException {
		driver.findElement(By.linkText("Se connecter / s'inscrire")).click();
		actualTitle1 = driver.getTitle();
		
        Assert.assertEquals(actualTitle1, expectedTitle1);
        
		WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login_username")));
        
		driver.findElement(By.id("login_username")).sendKeys(username);
		driver.findElement(By.id("login_password")).sendKeys(password);
		driver.findElement(By.id("loginBtn")).click();
		
		Thread.sleep(1000);
		actualTitle2 = driver.getTitle();
		Assert.assertEquals(actualTitle2, expectedTitle2);
	}
  
	
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "/home/progideo/eclipse/drivers/chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
	}

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }

}
