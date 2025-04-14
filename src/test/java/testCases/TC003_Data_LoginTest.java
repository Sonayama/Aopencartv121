package testCases;
/*Data Valid login sucess test Pass
 Data valid Login unsucess full fail
 data invalid login sucess fail
 data invalid login unsucessfull pass
 */
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.LoginDataProvider;



public class TC003_Data_LoginTest extends BaseClass{

	@Test(priority=2,dataProvider="LoginData",dataProviderClass=LoginDataProvider.class,groups={"DataDriven","Master"})
	void testLogin(String email,String pwd,String typeofdata)
	{
		
		logger.info("Staring TC003_Login_data Driven Started ");
		try {
		//Home
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		//Login
		hp.clickLogin();
		LoginPage lp=new LoginPage(driver);
		lp.setemail(email);
		lp.setPwd(pwd);
		lp.clickLogin();	
	//	System.out.print(driver.getTitle());
		MyAccountPage mcp= new MyAccountPage(driver);
		boolean targetPage=mcp.IsmyAccountPageexist();
		
		if(typeofdata.equalsIgnoreCase("valid") & targetPage==true)
		{
			mcp.Logout();
			Assert.assertTrue(true);			
		}
		else if(typeofdata.equalsIgnoreCase("valid") & targetPage==false)
		{
			Assert.assertTrue(false);
		}
		else if(typeofdata.equalsIgnoreCase("invalid") & targetPage==true)
		{
			mcp.Logout();
			Assert.assertTrue(false);
			
		}
		else if(typeofdata.equalsIgnoreCase("invalid") & targetPage==false)
		{
			Assert.assertTrue(true);
		}
		
		}
		catch (Exception e)
		{
			Assert.fail();
			logger.info("Finished TC003_Datadriven_Login Error occured");
			
		}
		
		logger.info("Finished TC003_Datadriven_Login  Finished");

	}
		
}
