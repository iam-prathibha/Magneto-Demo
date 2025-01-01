package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
		
	
	@Test(dataProvider="LoginTestData", dataProviderClass= DataProviders.class, groups="datadriven")
	public void loginDDT(String email, String pwd, String status) throws InterruptedException {
		
		logger.info("**************** TC003_Started verifying login test **********");
		
		//Home Page
		
		try {
			HomePage hp= new HomePage(driver);
			hp.clickLoginLink();
			
			//Login Page
			LoginPage lp= new LoginPage(driver);
			lp.sendEmail(email);
			lp.sendPassword(pwd);
			lp.clickOnSignIn();
			Thread.sleep(5000);
			
			boolean targetPage=hp.isCustomerOptionsMenuEnabled();
			System.out.println(targetPage);
			//MyAccount Page
			MyAccountPage accPage= new MyAccountPage(driver);
			//boolean text= accPage.isMyAccountPageExists();
			
			
			
			/* Valid
			 * login success - MyAccountTestPass -Pass
			 * 				 - MyAccountTestFail -Fail
			 * 
			 * InValid
			 * login Fail - MyAccountTestPass- Fail
			 * 			  - MyAccountTestFail- Pass	
			 */
			
			if(status.equalsIgnoreCase("Valid")) {
				if(targetPage) {
					logger.info("--------- Entered Valid if condition ------");
					hp.clickCustomerOptionsMenu();
					Thread.sleep(2000);
					hp.clickMyAccountOption();
					logger.info("--------- Able to click My account option  ------");
					Thread.sleep(2000);
					accPage.clickCustomerMenu();
					logger.info("--------- Able to click options menu  ------");
					Thread.sleep(2000);
					accPage.clickSignout();
					logger.info("--------- Able to click signout option  ------");
					Assert.assertTrue(true);				
				}
				else {
					Assert.assertTrue(false);
				}
			}
			
			if(status.equalsIgnoreCase("Invalid")) {
				if(targetPage) {
					hp.clickCustomerOptionsMenu();
					//accPage.clickCustomerMenu();
					Thread.sleep(2000);
					accPage.clickSignout();
					Assert.assertTrue(false);				
				}
				else {
					Assert.assertTrue(true);
				}
			}
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("****************  Verifying login test from TC003 is finished **********");
	}
	
	
	
	
}
