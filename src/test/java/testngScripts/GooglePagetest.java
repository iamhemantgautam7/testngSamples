package testngScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class GooglePagetest {
  @Test
  public void javaSearchTest() {
	  
	  ChromeOptions options = new ChromeOptions();
		options.setBrowserVersion("115");
//		options.addArguments("--headless"); 
//		options.addArguments("--blink-settings=imagesEnabled=false"); // this will disable the images from page
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	//	driver.get("https://www.google.com/");
		
		driver.navigate().to("https://www.google.com/");
		System.out.println("Page title :..."+ driver.getTitle());
		
		WebElement srcBox=driver.findElement(By.id("APjFqb"));
	//	WebElement srcBox=driver.findElement(By.name("q"));
	//	WebElement srcBox=driver.findElement(By.className("gLFyf"));
	//	WebElement srcBox=driver.findElement(By.tagName("textarea"));
		
		
		srcBox.sendKeys("Java Tutorials");
		srcBox.sendKeys(Keys.ENTER);
				
		
  }
}
