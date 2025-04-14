package utilities;


import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;


public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	
	public ExtentReports  extent;
	public ExtentTest test ;
	String repName;
	
	
	
	public void onStart(ITestContext testContext) {
		
		
		String  timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		  
		repName="Test-Report"+timestamp+".html";
		
		sparkReporter =new ExtentSparkReporter(".\\reports\\"+repName);
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setDocumentTitle("Functional Testing");

		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("coumputer", "Localhost");
		extent.setSystemInfo("Tester Name",System.getProperty("user.name"));
		extent.setSystemInfo("Enviroment","QA");
	
		String os =testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser =testContext.getCurrentXmlTest().getParameter("browser");
		
		extent.setSystemInfo("Browser", browser);
		
		List<String>includedGropus=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGropus.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGropus.toString());
		}
		

		
	  }
	
	
public void onTestStart(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());


	  }
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.PASS, result.getName()+"is Passed");
		
		 try {
			 String imgpath=new BaseClass().captureScreen(result.getName());
			 test.addScreenCaptureFromPath(imgpath);
			 
		 }
		 catch(Exception e) 
		 {
			 e.printStackTrace();
		 }
			
	
	
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.FAIL, result.getName()+"is Failed");	
		 test.log(Status.FAIL," Error Occured"+result.getThrowable());
		 
		 try {
			 String imgpath=new BaseClass().captureScreen(result.getName());
			 test.addScreenCaptureFromPath(imgpath);
			 
		 }
		 catch(Exception e) 
		 {
			 e.printStackTrace();
		 }
		 
	}
	public void onTestSkipped(ITestResult result) {

		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP, result.getName()+"is Skipped");
	}
		 public void onFinish(ITestContext testContext) {
			 
			 extent.flush();
			 String pathofExtentRepot=(System.getProperty("user.dir")+"\\reports\\"+repName);
			 File extentReport=new File(pathofExtentRepot);
			 
			 try {
				 Desktop.getDesktop().browse(extentReport.toURI());
			 }
			 catch(Exception e) {
				 e.printStackTrace();
			 }
			 
		 System.out.println("Test Execution compleated");	
		 }
	

}
