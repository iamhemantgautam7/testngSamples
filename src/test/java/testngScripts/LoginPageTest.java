package testngScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class LoginPageTest {
	@Test
	public void validLogin() {

		ChromeOptions options = new ChromeOptions();
		options.setBrowserVersion("115");
//		options.addArguments("--headless"); 
//		options.addArguments("--blink-settings=imagesEnabled=false"); // this will disable the images from page

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		// driver.get("https://www.google.com/");

		driver.get("https:the-internet.herokuapp.com/login");
		driver.findElement(By.cssSelector("#username")).sendKeys("tomsmith"); // this is for CSSselector
		// driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.xpath("//input[@type='password' and @name='password']")).sendKeys("SuperSecretPassword!");

		// driver.findElement(By.className("radius")).click(); //this will also work
		driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in")).click();

	}
}
