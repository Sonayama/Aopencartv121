package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.How;

public class AccountRegistrationPage extends BasePage{

	
public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
		
	}

//@FindBy(how=How.XPATH,using="//span[normalize-space()='My Account']") WebElement lnkMyAccount;

@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstname;
@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLatname;
@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
@FindBy(xpath="//input[@id='input-password']") WebElement txtpassword;
@FindBy(xpath="//input[@id='input-confirm']") WebElement txtconfirmPassword;
@FindBy(xpath="//input[@name='agree']") WebElement chekPolicy;
@FindBy(xpath="//input[@value='Continue']") WebElement btnContinued;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;

public void setFirstname(String fname)
{

	txtFirstname.sendKeys(fname);
}
public void setlastname(String lname)
{

	txtLatname.sendKeys(lname);
}
public void settxtEmail(String email)
{

	txtEmail.sendKeys(email);
}
public void setTelephone(String phone)
{

	txtTelephone.sendKeys(phone);
}

public void setpassword(String pwd)
{

	txtpassword.sendKeys(pwd);
}
public void setconfirmpassword(String confirmpwd)
{

	txtconfirmPassword.sendKeys(confirmpwd);
}

public void setprivacyPolicy()
{

	chekPolicy.click();
}
public void clickcontinue()
{
	btnContinued.click();
}

public String getConfirmationmessge() 
{
	try {
		return(msgConfirmation.getText());
	}
	catch(Exception e)
{
	return(e.getMessage());
}

}
}

