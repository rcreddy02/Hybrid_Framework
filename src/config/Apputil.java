package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

// Write Pre-condition and Post condition in this class
public class Apputil {

// Declare Webdriver object as a Global Variable
	
 public static WebDriver driver;
 public static Properties p;
 
@BeforeTest
public static void setUp() throws Throwable
{
	p = new Properties();
	p.load(new FileInputStream("F:\\Selenium\\Hybrid_Framework\\Environment.properties"));
	if(p.getProperty("Browser").equalsIgnoreCase("Chrome"))
	{
	System.setProperty("webdriver.chrome.driver", "E:\\Softwares\\Selenium_SW\\Browser drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	}
	else if(p.getProperty("Browser").equalsIgnoreCase("Firefox"))
	{
	System.setProperty("webdriver.gecko.driver", "E:\\Softwares\\Selenium_SW\\Browser drivers\\geckodriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	}
	driver.get(p.getProperty("URL"));
}

@AfterTest
public static void tierDown()
{
	driver.quit();
}

}
