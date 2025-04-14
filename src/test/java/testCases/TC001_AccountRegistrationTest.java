package testCases;
import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(priority=1,groups={"Regression","Master"})
	public void verify_account_regitrtion() {
		try {
			
		logger.info("Staring TC001_AccountRegistration");
		
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on the myaccount");

		hp.clickRegister();
		logger.info("clicked on the Registration");

		AccountRegistrationPage regpag=new AccountRegistrationPage(driver);
		regpag.setFirstname(radomeString().toUpperCase());
		regpag.setlastname(radomeString().toUpperCase());
	    regpag.settxtEmail(radomeString()+"@gmail.com");
		//.settxtEmail("vnm4u@gmail.com");
		regpag.setTelephone(radomenumber());
		String pwd=radomealphanumber();
		//String pwd="kovi@96569";
		regpag.setpassword(pwd);
		regpag.setconfirmpassword(pwd);	
		regpag.setprivacyPolicy();
		regpag.clickcontinue();
		String confmng=regpag.getConfirmationmessge();
		Assert.assertEquals(confmng, "Your Account Has Been Created!");
		}
		catch(Exception e) 
		{
			logger.error("testfailed" +e.getMessage());
			logger.debug("debuglogs");
			Assert.fail();
		}
		logger.info("TC001 Account Registration TestCase Finished");
		
	}

	
	
}
