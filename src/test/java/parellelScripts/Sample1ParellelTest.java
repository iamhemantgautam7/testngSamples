package parellelScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Sample1ParellelTest {


	@Test
	public void Test1() {
		System.out.println("sample1ParellelTest1");
	}

	@Test
	public void Test2() {
		System.out.println("sample1ParellelTest2");
	}
	@Test
	public void Test3() {
		System.out.println("sample1ParellelTest3");
	}@Test
	public void Test4() {
		System.out.println("sample1ParellelTest4");
	}	
}
