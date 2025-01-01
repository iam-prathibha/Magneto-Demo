package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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

import testBase.BaseClass;


public class ExtentReportManager implements ITestListener{
	
	
	ExtentSparkReporter sp;
	ExtentReports er;
	ExtentTest et;
	
	String repName;
	
	public void onStart(ITestContext context) {
	    // not implemented
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName= "Test-Report"+timeStamp+".html";
		sp= new ExtentSparkReporter("./reports/"+repName);
		sp.config().setDocumentTitle("MagnetDemoTestReport");
		sp.config().setReportName("Functional Testing");
		sp.config().setTheme(Theme.DARK);
		
		er=new ExtentReports();
		er.attachReporter(sp);
		
		er.setSystemInfo("Application Name", "Magneto Demo");
		er.setSystemInfo("Module","Admin");
		er.setSystemInfo("Sub Module", "Customers");
		er.setSystemInfo("User Name", System.getProperty("user.name"));
		er.setSystemInfo("Environment", "QA");
		
		String os= context.getCurrentXmlTest().getParameter("os");
		er.setSystemInfo("Operating System", os);
		
		String browser= context.getCurrentXmlTest().getParameter("browser");
		er.setSystemInfo("Operating System", browser);
		
		List<String> includedGroups= context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			er.setSystemInfo("Included Groups", includedGroups.toString());
		}
		
	
	}
	
	public void onTestSuccess(ITestResult result) {
	    // not implemented
		et=er.createTest(result.getTestClass().getName());
		et.assignCategory(result.getMethod().getGroups());
		et.log(Status.PASS, "Test case PASSED is "+result.getName());
	  }
	
	
	public void onTestFailure(ITestResult result) {
	    // not implemented
		 et=er.createTest(result.getTestClass().getName());
		 et.assignCategory(result.getMethod().getGroups());
		 et.log(Status.FAIL, "Test case FAILED is "+result.getName());
		 et.log(Status.INFO, "Test case FAILED cause is "+result.getThrowable().getMessage());
		 
		 try {
			 System.out.println(" result : "+ result.getName());
			 String imgPath= new BaseClass().captureScreen(result.getName());
			 et.addScreenCaptureFromPath(imgPath);
			 
			 
		 }catch(Exception e1) {
			 e1.printStackTrace();
		 }
		 
		 
	}
	
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	 et=er.createTest(result.getTestClass().getName());
	 et.assignCategory(result.getMethod().getGroups());
	 et.log(Status.SKIP, "Test case SKIPPED is "+result.getName());
	 et.log(Status.INFO, "Test case SKIPPED cause is "+result.getThrowable().getMessage());

	  }
	
	public void onFinish(ITestContext context) {
        er.flush();
        
        String extentReportPath=System.getProperty("user.dir")+"/reports/"+repName;
        File extentReport= new File(extentReportPath);
        
        try {
        	Desktop.getDesktop().browse(extentReport.toURI());
        }catch(IOException e) {
        	e.printStackTrace();
        }
    }
}
