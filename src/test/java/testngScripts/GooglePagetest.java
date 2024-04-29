package testngScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class GooglePagetest {

	
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
	
	@AfterTest
	public void finishExtent() {
		extentReports.flush();
	}
	
	@BeforeMethod
	public void setup() {

		ChromeOptions options = new ChromeOptions();
		options.setBrowserVersion("115");
//		options.addArguments("--headless"); 
//		options.addArguments("--blink-settings=imagesEnabled=false"); // this will disable the images from page

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	//@Test(alwaysRun=true, dependsOnMethods="seleniumSearchTest") //- this will be depends on Selenium method and always run wont skip this test even though selenium test is failed
	@Test(groups="desktop")
	public void javaSearchTest() {
		
		extentTest = extentReports.createTest("Java Search Test");

		driver.get("https://www.google.com/");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(driver.getTitle(), "Google 1");
//		Assert.assertEquals(driver.getTitle(), "Google1");
		WebElement srcBox = driver.findElement(By.id("APjFqb"));
		srcBox.sendKeys("Java Tutorial");
		srcBox.sendKeys(Keys.ENTER);
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");

	//	softAssert.assertAll();
	}

	@Test
	public void seleniumSearchTest() {
		extentTest = extentReports.createTest("Selenium Search Test");
		driver.get("https://www.google.com/");
		WebElement srcBox = driver.findElement(By.id("APjFqb"));
		srcBox.sendKeys("Selenium Tutorial");
		System.out.println("Page title :..." + driver.getTitle());
		srcBox.sendKeys(Keys.ENTER);
		Assert.assertEquals(driver.getTitle(), "Selenium Tutorial1 - Google Search");
	}

	@Test
	public void cucumberSearchTest() {
		extentTest = extentReports.createTest("Cucumber Search Test");

		driver.get("https://www.google.com/");
		
		  WebElement srcBox = driver.findElement(By.id("APjFqb"));
		  srcBox.sendKeys("cucumber Tutorial"); System.out.println("Page title :..." +driver.getTitle());
		 
		srcBox.sendKeys(Keys.ENTER);
		Assert.assertEquals(driver.getTitle(), "cucumber Tutorial - Google Search");
	
}
	//@Test(enabled=false) to ignore the test case from execution 
	@Test
	public void appiumSearchTest() {
		extentTest = extentReports.createTest("Appium Search Test");

		driver.get("https://www.google.com/");
		WebElement srcBox = driver.findElement(By.id("APjFqb"));
		srcBox.sendKeys("appium Tutorial");
		System.out.println("Page title :..." + driver.getTitle());
		srcBox.sendKeys(Keys.ENTER);
		Assert.assertEquals(driver.getTitle(), "appium Tutorial - Google Search");
	
}
	
	
}
