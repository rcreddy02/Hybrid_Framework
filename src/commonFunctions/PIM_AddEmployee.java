package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PIM_AddEmployee {

	@FindBy (linkText = "PIM")
	WebElement pimlink;
	@FindBy (name = "btnAdd")
	WebElement addbutton;
	public void pimbutton() throws Throwable
	{
		pimlink.click();
		Thread.sleep(5000);
		addbutton.click();
	}

	

}
