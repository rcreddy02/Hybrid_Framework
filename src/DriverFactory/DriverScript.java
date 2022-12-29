package DriverFactory;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import commonFunctions.AddEmployeeDetails;
import commonFunctions.AdminLoginPage;
import commonFunctions.AdminLogout;
import commonFunctions.PIM_AddEmployee;
import config.Apputil;
import utilities.ExcelFileUtil;

public class DriverScript extends Apputil {
	String inputfilepath = "F:\\Selenium\\Hybrid_Framework\\Test_Input\\DataEngine.xlsx";
	String outputfilepath = "F:\\Selenium\\Hybrid_Framework\\Test_Output\\results.xlsx";
	String TCsheet = "TestCase";
	String TSsheet = "TestSteps";
	
	@Test
	public void startTest () throws Throwable
	{
		boolean res = false;
		String TCstatus = "";
		ExcelFileUtil xl = new ExcelFileUtil(inputfilepath);
//		count number rows in Test case and Test steps sheets
		int TCcount = xl.rowcount(TCsheet);
		int TScount = xl.rowcount(TSsheet);
		
//		reading excel data
		
		for (int i=1; i<=TCcount;i++)
		{
//			Read TCsheet module status
			String TCModule = xl.getcelldata(TCsheet, i,2);
			if (TCModule.equalsIgnoreCase("Y"))
			{
				String TCid = xl.getcelldata(TCsheet, i,0);
				for (int j=0;j<=TScount;j++)
				{
					String TSid = xl.getcelldata(TSsheet, j, 0);
					if (TCid.equalsIgnoreCase(TSid))
					{
						String keyword = xl.getcelldata(TSsheet, j,3);
						if (keyword.equalsIgnoreCase("AdminLogin"))
						{
							AdminLoginPage pblogin = PageFactory.initElements(driver, AdminLoginPage.class);
							String para1 = xl.getcelldata(TSsheet, j,5);
							String para2 = xl.getcelldata(TSsheet, j, 6);
							res = pblogin.verifyAdminLogin(para1,para2);
						
							}
						else if (keyword.equalsIgnoreCase("AddEmployee"))
						{
							PIM_AddEmployee pbpim = PageFactory.initElements(driver, PIM_AddEmployee.class);
							AddEmployeeDetails pbaddemp = PageFactory.initElements(driver, AddEmployeeDetails.class);
							String para1 = xl.getcelldata(TSsheet, j,5);
							String Para2 = xl.getcelldata(TSsheet, j,6);
							String para3 = xl.getcelldata(TSsheet, j,7);
							String para4 = xl.getcelldata(TSsheet, j,8);
							String para5 = xl.getcelldata(TSsheet, j,9);
							String para6 = xl.getcelldata(TSsheet, j,10);
							pbpim.pimbutton();
							res = pbaddemp.verifyAddemployee(para1, Para2, para3, para4, para5, para6);
									
						}
						else if (keyword.equalsIgnoreCase("AdminLogout"))
						{
							AdminLogout pblogout = PageFactory.initElements(driver,AdminLogout.class);
							res = pblogout.verifylogout();
						}
						String TSstatus="";
						if(res)
						{
							//if res is true write pass into status cell in TSSheet
							TSstatus="Pass";
							xl.setCelldata(TSsheet, j, 4, TSstatus, outputfilepath);
							
						}
						else
						{
							//if res is false write fail into status cell in TSSheet
							TSstatus="Fail";
							xl.setCelldata(TSsheet, j, 4, TSstatus, outputfilepath);
						}
						TCstatus=TSstatus;
				}
			}
				//write TCstatus into status cell in TCSheet
				xl.setCelldata(TCsheet, i, 3, TCstatus, outputfilepath);
			}
			else
			{
				//which test case flag N write as Blocked status in TCsheet status cell
				xl.setCelldata(TCsheet, i, 3, "Blocked", outputfilepath);
		}
			
	}
	}
	

}
