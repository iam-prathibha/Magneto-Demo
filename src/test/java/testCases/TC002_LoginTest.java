package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	
	@Test(groups={"Sanity","Master"})
	public void verifyLogInTest() throws InterruptedException {
		logger.info("**************** Started verifying login test **********");
		
		HomePage hp= new HomePage(driver);
		
		hp.clickLoginLink();
		
		LoginPage lp= new LoginPage(driver);
		logger.info("************* Login Page Loaded*************");
		lp.sendEmail(p.getProperty("loginEmail"));
		lp.sendPassword(p.getProperty("loginPwd"));
		lp.clickOnSignIn();
		logger.info("************ Entered the login details **************");
		Thread.sleep(2000);
		logger.info("************ Entered the HomePage **************");
		
		hp.clickCustomerOptionsMenu();
		logger.info("************ Entered the customers options menu **************");
		Thread.sleep(3000);
		hp.clickMyAccountOption();
		Thread.sleep(2000);
		logger.info("************ Entered the MyAccountPage **************");
		MyAccountPage accPage= new MyAccountPage(driver);
		
		
		try {
		logger.info("************* Verification of MyAccount Page Started *************");
		
			Assert.assertTrue(accPage.isMyAccountPageExists());
		
		}catch(Exception e) {

			logger.info("************* Verification of MyAccount Page got failed *************");
			Assert.fail();
		}
		

		logger.info("************* Verification of Login Test is finish *************");
	}
	

}
