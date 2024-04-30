package testngScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExternalFileTest {
	
	WebDriver driver;
	Properties prop;

	@BeforeTest
	public void initSetup() throws IOException {
		prop =new Properties();
		String path=System.getProperty("user.dir")+ "//src//test//resources//configFile//config.properties";
	FileInputStream fis=new FileInputStream(path);
	prop.load(fis);
	fis.close();
	}
	
	
	@BeforeMethod
public void setup() {
		String strBrowser=prop.getProperty("browser");
		if(strBrowser.equalsIgnoreCase("chrome")) {
			 driver = new ChromeDriver();
		}
		else if(strBrowser.equalsIgnoreCase("edge")) {
			 driver = new EdgeDriver();
		}
	}
	
	@Test
	public void validLogin() {
		driver.get(prop.getProperty("url"));
		driver.findElement(By.cssSelector("#username")).sendKeys("tomsmith"); // this is for CSSselector
		// driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.xpath("//input[@type='password' and @name='password']")).sendKeys("SuperSecretPassword!");

		// driver.findElement(By.className("radius")).click(); //this will also work
		driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in")).click();
		boolean isValid=driver.findElement(By.cssSelector("div.flash.success")).isDisplayed();
		Assert.assertTrue(isValid);
		
		
	}
}
