package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;



public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		
	}

@FindBy(how=How.XPATH,using="//input[@id='input-email']") WebElement txt_email;
@FindBy(xpath="//input[@id='input-password']") WebElement txt_pwd;
@FindBy(xpath="//input[@type='submit']") WebElement btn_login;
  
public void setemail(String user)
{

	txt_email.sendKeys(user);
}
public void setPwd(String pwd)
{
	txt_pwd.sendKeys(pwd);
}
public void clickLogin()
{
	btn_login.click();
}

}



