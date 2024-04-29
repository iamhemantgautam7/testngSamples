package testngScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class LoginPageTest {
	
	WebDriver driver;
	ExtentReports extentReports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;
	
	@BeforeTest
	public void initExtent() {
		extentReports = new ExtentReports();
		spark = new ExtentSparkReporter("test-output/SparkReport.html");
		extentReports.attachReporter(spark);
		
	}
	
	
	
	@Parameters("broswer")
	@BeforeMethod
	public void setup(String strBroswer) {
		
		if(strBroswer.equalsIgnoreCase("chrome")) {
			 driver = new ChromeDriver();
		}
		else if(strBroswer.equalsIgnoreCase("edge")) {
			 driver = new EdgeDriver();
		}
		
		
		
		 driver.manage().window().maximize();
		
	}
	
	@Test
	public void validLogin() {

		//ChromeOptions options = new ChromeOptions();
	//	options.setBrowserVersion("115");
//		options.addArguments("--headless"); 
//		options.addArguments("--blink-settings=imagesEnabled=false"); // this will disable the images from page

		//WebDriver driver = new ChromeDriver(options);
		
		// driver.get("https://www.google.com/");

		driver.get("https:the-internet.herokuapp.com/login");
		driver.findElement(By.cssSelector("#username")).sendKeys("tomsmith"); // this is for CSSselector
		// driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.xpath("//input[@type='password' and @name='password']")).sendKeys("SuperSecretPassword!");

		// driver.findElement(By.className("radius")).click(); //this will also work
		driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in")).click();
		boolean isValid=driver.findElement(By.cssSelector("div.flash.success")).isDisplayed();
		Assert.assertTrue(isValid);
		
		
	}
}
