package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AdminLogout {
	WebDriver driver;
	public AdminLogout (WebDriver driver)
	{
		this.driver = driver;
	}
	@FindBy (id= "welcome")
	WebElement avataricon;
	@FindBy (linkText = "Logout")
	WebElement logoutbutton;
	@FindBy (name = "Submit")
	WebElement loginbutton;
	
	public boolean verifylogout () throws Throwable
	{
		avataricon.click();
		Thread.sleep(5000);
		logoutbutton.click();
		Thread.sleep(2000);
		if (loginbutton.isDisplayed())
		{
			Reporter.log("Pass",true);
			return true;
		}
		else
		{
			Reporter.log("Fail", true);
			return false;
		}
	}
	

	
	
	
	
	
}
