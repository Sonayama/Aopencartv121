package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage{

	
	public HomePage(WebDriver driver)
	{
		super(driver);
		
	}

@FindBy(how=How.XPATH,using="//span[normalize-space()='My Account']") WebElement lnkMyAccount;

@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegister;
@FindBy(xpath="//a[normalize-space()='Login']") WebElement lnkLogin;


public void clickMyAccount()
{
	lnkMyAccount.click();
}


public void clickRegister()
{
	lnkRegister.click();
}
public void clickLogin()
{
	lnkLogin.click();
}

}