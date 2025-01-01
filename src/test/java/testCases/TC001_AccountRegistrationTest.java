package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;



import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	
	@Test(groups={"Regression","Master"})
	public void verifyRegistrationTest() throws InterruptedException {
		try {
		logger.info("****** Verifying Regestration Test Case Started **********");
	
		HomePage hp= new HomePage(driver);

		//hp.clickMyAccount();
		
		hp.clickRegisterLink();
		
		logger.info("****** clicked on create an account link **********");
		
		AccountRegistrationPage rp= new AccountRegistrationPage(driver);
		
		rp.setFirstName("Open");
		rp.setLasttName("Cart");
		String email= randomString()+"@gmail.com";
		rp.setEmail(email);
		logger.info("******** Entered mail : "+email+" ********");
		String password= randomAlphaNumeric()+"@";
		rp.setPassword(password);
		rp.setPasswordConfirmation(password);
		logger.info("******** Entered password : "+password+" ********");
		rp.clickCreateAccount();
		Thread.sleep(2000);
		logger.info("******************** Started validating the confirmation message ******************");
		Assert.assertEquals(rp.getConfirmationMessage(), "Thank you for registering with Main Website Store.");
		}catch(Exception e) {
			logger.error("----------------------Test Failed-------------------------");
			logger.debug("================Debug Logs==============");
			Assert.fail();
		}
		
		logger.info("******** Verifying Registration Test Case Finished **********");
	}
	
	
}
