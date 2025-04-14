package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;



public class TC002_LoginTest extends BaseClass{

	@Test(priority=2,groups={"Sanity","Master"})
	void testLogin()
	{
		
		logger.info("Staring TC001_Login");

		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		LoginPage lp=new LoginPage(driver);
		lp.setemail("vnm4u@gmail.com");
		lp.setPwd("kovi@96569");
		lp.clickLogin();	
	//	System.out.print(driver.getTitle());
		
		MyAccountPage mcp= new MyAccountPage(driver);
		//mcp.IsmyAccountPageexist();
		Assert.assertEquals(true,mcp.IsmyAccountPageexist());
		
		logger.info("Finished TC002_Login Sucess full");

	}
		
}
