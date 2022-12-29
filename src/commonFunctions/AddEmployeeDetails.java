package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class AddEmployeeDetails {
	public WebDriver driver;
	public AddEmployeeDetails (WebDriver driver)
	{
		this.driver = driver;
	}
	@FindBy (name="firstName")
	WebElement firstname;
	@FindBy(name="middleName")
	WebElement middelname;
	@FindBy (name="lastName")
	WebElement lastname;
	@FindBy (name="employeeId")
	WebElement beforeemployeeid;
	@FindBy (name="photofile")
	WebElement propic;
	@FindBy (name="chkLogin")
	WebElement createlogin;
	@FindBy (name="user_name")
	WebElement emp_username;
	@FindBy (name="user_password")
	WebElement emp_password;
	@FindBy (name="re_password")
	WebElement emp_repassword;
	@FindBy (name="status")
	WebElement status;
	@FindBy (name="personal[txtEmployeeId]")
	WebElement afteremployeeid;
	
	
	public boolean verifyAddemployee (String fname, String mname, String lname, String username, String password, String confirmpassword)
	{
		firstname.sendKeys(fname);
		middelname.sendKeys(mname);
		lastname.sendKeys(lname);
		String before_employeeid = beforeemployeeid.getAttribute("value");
		if (!createlogin.isSelected())
		{
			createlogin.click();
		}
		else
		{
			Reporter.log("Create login selected", true);
		}
		emp_username.sendKeys(username);
		emp_password.sendKeys(password);
		emp_repassword.sendKeys(confirmpassword);
		String emp_status = status.getText();

		if (emp_status.contains("Enabled")) {

			Reporter.log("Data verified", true);
			driver.findElement(By.id("btnSave")).click();

		} else
		{
			Reporter.log(emp_status, true);

			Select drpdwn = new Select(driver.findElement(By.id("status")));
			drpdwn.selectByIndex(0);
			driver.findElement(By.id("btnSave")).click();
		}
		
		String After_emp = afteremployeeid.getAttribute("value");
		if (After_emp.equalsIgnoreCase(before_employeeid))
		{
			Reporter.log("Employee added successfully" +After_emp+"  "+before_employeeid, true);
			return true;
		}
		else
		{
			Reporter.log("Unable to add employee" +After_emp+"  "+before_employeeid, true);
			return false;
		}
	}

}
