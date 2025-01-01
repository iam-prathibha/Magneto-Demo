package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.net.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass (groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os,String bs) throws InterruptedException, IOException
	{
		logger= LogManager.getLogger(this.getClass());
		
		FileReader file=new FileReader("./src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		
		//OS
		if((p.getProperty("execution_env")).equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities= new DesiredCapabilities();
			if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("windows")){
				capabilities.setPlatform(Platform.WIN11);
				}
			else {
				System.out.println("No matching OS");
			}
			//Browser
			switch(bs.toLowerCase()) {
			case "chrome" : capabilities.setBrowserName("chrome"); break;
			case "firefox" : capabilities.setBrowserName("firefox"); break;
			default: System.out.println("Invalid Browser"); return;
			}
			URL remoteUrl= new URL("http://192.168.2.29:4444/wd/hub");
			driver= new RemoteWebDriver(remoteUrl,capabilities);
			
			
		}


		if((p.getProperty("execution_env")).equalsIgnoreCase("local")) {
			switch(bs.toLowerCase()) {
			case "chrome" : driver=new ChromeDriver(); break;
			case "firefox" : driver= new FirefoxDriver(); break;
			default: System.out.println("Invalid Browser"); return;
			}
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(p.getProperty("url"));
		
		driver.manage().window().maximize();
		
	}
	
	@AfterClass (groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		driver.quit();
	}
	
	
	
	public String randomString() {
		String txt_GeneratedString=RandomStringUtils.randomAlphabetic(5);
		return txt_GeneratedString;
	}
	
	public String randomNumeric() {
		String txt_GeneratedNumeric=RandomStringUtils.randomNumeric(5);
		return txt_GeneratedNumeric;
	}
	
	public String randomAlphaNumeric() {
		String txt_GeneratedAlphaNumeric=RandomStringUtils.randomAlphanumeric(8);
		return txt_GeneratedAlphaNumeric;
	}
	
	public String captureScreen(String name) {
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		System.out.println(name);
		TakesScreenshot takeScreenShot= (TakesScreenshot) driver;
		File sourceFile= takeScreenShot.getScreenshotAs(OutputType.FILE);
		System.out.println("********** Took SS **********");
		String targetFilePath= System.getProperty("user.dir")+"/screenShots/"+name+"_"+timeStamp+".png";
		
		File tagetFile= new File(targetFilePath);
		System.out.println("************ created ss in target file path*********8");
		sourceFile.renameTo(tagetFile);
		System.out.println(targetFilePath);
		return targetFilePath;
	}
}