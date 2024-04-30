package testngScripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ExternalDataDrivenExcel {
	
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
	
	//dataDriven framework created here, pulling data from CSV file
	@Test(dataProvider = "logindata")
	public void validLogin(String strUser, String strPwd) {
		driver.get(prop.getProperty("url"));
		driver.findElement(By.cssSelector(readObjPath("#username"))).sendKeys(strUser); // this is for CSSselector
		// driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.xpath(readObjPath("//input[@type='password' and @name='password']"))).sendKeys(strPwd);

		// driver.findElement(By.className("radius")).click(); //this will also work
		driver.findElement(By.cssSelector(readObjPath(".fa.fa-2x.fa-sign-in"))).click();
		boolean isValid=driver.findElement(By.cssSelector("div.flash.success")).isDisplayed();
		Assert.assertTrue(isValid);
		driver.close();
		
		
	}
	//to read from csv file we need to add csv liberery using mven repositiry
	@DataProvider(name="logindata")
	public String readObjPath(String objName){
		String objPath="";
		String path= System.getProperty("user.dir")+"//src//test//resources//testData//loginObj.xlsx";
		
		FileInputStream fis;
		XSSFWorkbook workbook =null;
		
		try {
			fis =new FileInputStream(path);
			workbook =new XSSFWorkbook(fis);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		XSSFSheet loginSheet=workbook.getSheet("loginPage");
		int numRows=loginSheet.getLastRowNum();
		
		for(int i=1; i<=numRows; i++) {
			XSSFRow row=loginSheet.getRow(i);
			
			
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(objName)) {
				objPath =row.getCell(1).getStringCellValue();
			}
		}
		return objPath;
		
	}
	
	
	
}
