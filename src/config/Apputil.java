package config;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

// Write Pre-condition and Post condition in this class
public class Apputil {

// Declare Webdriver object as a Global Variable
	
 public static WebDriver driver;
 
@BeforeTest
public static void setUp()
{
	System.setProperty("webdriver.chrome.driver", "E:\\Softwares\\Selenium_SW\\Browser drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	
}

@AfterTest
public static void tierDown()
{
	driver.quit();
}

}
