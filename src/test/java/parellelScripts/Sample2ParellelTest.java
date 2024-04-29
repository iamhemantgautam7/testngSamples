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

public class Sample2ParellelTest {


	@Test
	public void Test1() {
		long id=Thread.currentThread().getId();
		System.out.println("sample2ParellelTest1..."+id);
	}

	@Test
	public void Test2() {
		long id=Thread.currentThread().getId();
		System.out.println("sample2ParellelTest2..."+id);
	}
	@Test
	public void Test3() {
		long id=Thread.currentThread().getId();
		System.out.println("sample2ParellelTest3..."+id);
	}@Test
	public void Test4() {
		long id=Thread.currentThread().getId();
		System.out.println("sample2ParellelTest4..."+id);
	}	
}
