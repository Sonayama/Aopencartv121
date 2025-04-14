package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	
public Logger logger;//Log4j

	public static WebDriver driver;
	public Properties p;
	@SuppressWarnings("deprecation")
	@BeforeClass(groups={"Regression","Master","Sanity","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		//lodding  config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		 logger=LogManager.getLogger(this.getClass());
		 
		 if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		 {
			 DesiredCapabilities capability=new DesiredCapabilities();	 
			 
			 if(os.equalsIgnoreCase("windows"))
				{
				capability.setPlatform(Platform.WIN10);
				}
			 else if(os.equalsIgnoreCase("mac"))
			 	{
				 capability.setPlatform(Platform.MAC);
			 	}
			 else
			 {
				 System.out.println("invalid platform");
				 return;
			 }

			 switch(br.toLowerCase())
				{
				case "chrome":capability.setBrowserName("chrome");break;
				case "edge": capability.setBrowserName("MicrosoftEdge");break;
				default: System.out.println("invalid Browser"); return;
				}
			 
			driver=new RemoteWebDriver(new URL("http://192.168.2.25:4444/wd/hub"), capability);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get(p.getProperty("appURL"));
			 
		 }
		 
		 if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		 {
		 
		 switch(br.toLowerCase())
			{
			/*case "chrome":ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			driver=new ChromeDriver(options);break;
			*/
			case "chrome": driver=new ChromeDriver();break;
			case "edge": driver= new EdgeDriver();break;
			case "firefox": driver=new FirefoxDriver();break;
			default: System.out.println("invalid Browser"); return;
			}
		 driver.manage().deleteAllCookies();
		 driver.manage().window().maximize();
		 driver.get(p.getProperty("appURL"));
		 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 }
		}
	@SuppressWarnings("deprecation")
	public String radomeString()
	{
		String generatedString =RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String radomenumber()
	{
		@SuppressWarnings("deprecation")
		String generatedString =RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String radomealphanumber()
	{
		@SuppressWarnings("deprecation")
		String generatedString1 =RandomStringUtils.randomAlphabetic(4);
		@SuppressWarnings("deprecation")
		String generatedString2 =RandomStringUtils.randomNumeric(4);
		
		return generatedString1+"@"+generatedString2;
	}
	public String captureScreen(String tname)throws IOException
	{
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takescreeshot=(TakesScreenshot)driver;
		File sourcefile=takescreeshot.getScreenshotAs(OutputType.FILE);
		String targetfilepath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp;
		File targetFile=new File(targetfilepath);
		sourcefile.renameTo(targetFile);		
		return targetfilepath;
		
	}
	
	
	
	@AfterClass(groups={"Regression","Master","Sanity","DataDriven"})
	public void teardown()
	{
		driver.quit();
	}
}
