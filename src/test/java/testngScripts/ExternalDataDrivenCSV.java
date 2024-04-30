package testngScripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class ExternalDataDrivenCSV {
	
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
		driver.findElement(By.cssSelector("#username")).sendKeys(strUser); // this is for CSSselector
		// driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.xpath("//input[@type='password' and @name='password']")).sendKeys(strPwd);

		// driver.findElement(By.className("radius")).click(); //this will also work
		driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in")).click();
		boolean isValid=driver.findElement(By.cssSelector("div.flash.success")).isDisplayed();
		Assert.assertTrue(isValid);
		driver.close();
		
		
	}
	//to read from csv file we need to add csv liberery using mven repositiry
	@DataProvider(name="logindata")
	public Object[][] getData(){
		String path= System.getProperty("user.dir")+"//src//test//resources//testData//loginData.csv";
		CSVReader reader = null;
		
		try {
			reader=new CSVReader(new FileReader(path));
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		String cols[];
		ArrayList<Object> dataList=new ArrayList<Object>();
		
		try {
			while((cols=reader.readNext()) !=null) {
				Object record[]= {cols[0], cols[1]};
				dataList.add(record);
				
				
			}
			reader.close();
		}catch (CsvValidationException | IOException e) {
			e.printStackTrace();
		}
	
		dataList.toArray();
		
		return dataList.toArray(new Object [dataList.size()][]);
		
	}
	
	
}
