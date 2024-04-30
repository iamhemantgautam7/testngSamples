package testngScripts;

import java.io.FileInputStream;

import java.io.FileReader;
import java.io.IOException;

import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ExternalDataDrivenJson {
	
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
	public String[][] getData() throws IOException, ParseException{
		String path= System.getProperty("user.dir")+"//src//test//resources//testData//loginData.json";
		
		FileReader reader =new FileReader(path);
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(reader);
		JSONObject jsonObj=(JSONObject) obj;
		JSONArray userArray=(JSONArray)jsonObj.get("userLogins");
		String arr[][]=new String[userArray.size()][];
		for(int i=0; i<userArray.size(); i++) {
			JSONObject user=(JSONObject)userArray.get(i);
			String strUser=(String)user.get("username");
			String strPwd=(String)user.get("password");
			String record[]= {strUser, strPwd};
			arr[i]=record;
			
		}
		return arr;
		
		
		
	}
	
	
}
