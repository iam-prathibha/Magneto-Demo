package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@id='email']")
	WebElement txt_Email;
	
	@FindBy(xpath="//fieldset[@class='fieldset login']//input[@id='pass']")
	WebElement txt_Password;
	
	@FindBy(xpath="//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]")
	WebElement btn_SignIn;
	
	public void sendEmail(String email) {
		txt_Email.sendKeys(email);
	}
	
	public void sendPassword(String password) {
		txt_Password.sendKeys(password);
	}
	
	public void clickOnSignIn() {
		btn_SignIn.click();
	}
}
