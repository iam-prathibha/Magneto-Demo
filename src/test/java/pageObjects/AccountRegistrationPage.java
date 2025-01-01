package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='firstname']")
	WebElement txt_FirstName;
	
	@FindBy(xpath="//input[@id='lastname']")
	WebElement txt_LastName;
	
	@FindBy(xpath="//input[@id='email_address']")
	WebElement txt_Email;

	@FindBy(xpath="//input[@id='password']")
	WebElement txt_Password;
	
	@FindBy(xpath="//input[@id='password-confirmation']")
	WebElement txt_PasswordConfirmation;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chk_AgreeTerms;
	
	@FindBy(xpath="//button[@title='Create an Account']//span[contains(text(),'Create an Account')]")
	WebElement btn_CreateAccount;
	
	@FindBy(xpath="//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
	WebElement msg_Confirmation;
	
	public void setFirstName(String firstName) {
		txt_FirstName.sendKeys(firstName);
	}
	
	public void setLasttName(String lastName) {
		txt_LastName.sendKeys(lastName);
	}
	public void setEmail(String email) {
		txt_Email.sendKeys(email);
	}
	
	public void setPassword(String password) {
		txt_Password.sendKeys(password);
	}
	
	public void setPasswordConfirmation(String password) {
		txt_PasswordConfirmation.sendKeys(password);
	}
	
	
	
	public void clickCreateAccount() {
		btn_CreateAccount.click();
	}
	
	public String getConfirmationMessage() {
		try {
			return msg_Confirmation.getText();
		}catch(Exception e) {
			return (e.getMessage());
		}
	}
}
