package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AdminLoginPage {
	WebDriver driver;
	public AdminLoginPage (WebDriver driver)
	{
		this.driver = driver;
	}
	@FindBy (name = "txtUsername")
	WebElement usernametextfiled;
	
	@FindBy (name = "txtPassword")
	WebElement passwordtextfiled;
	
	@FindBy (name = "Submit")
	WebElement loginbutton;
	
public boolean verifyAdminLogin (String username, String Password)
{
	usernametextfiled.sendKeys(username);
	passwordtextfiled.sendKeys(Password);
	loginbutton.click();
	String Expected = "dashboard";
	String Actual = driver.getCurrentUrl();
	if (Actual.contains(Expected))
	{
		Reporter.log("Login Pass", true);
		return true;
	}
	else
	{
		Reporter.log("Login Fail", true);
		return false;
	}
}

}
