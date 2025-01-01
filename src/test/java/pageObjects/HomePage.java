package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	
	public HomePage(WebDriver driver){
		super(driver);
	}
	
	/*@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement myAccountLink;
	
	public void clickMyAccount() {
		//myAccountLink.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", myAccountLink);
		myAccountLink.click();
	} */
	
	@FindBy(xpath="//div[@class='panel header']//a[normalize-space()='Create an Account']")
	WebElement registerLink;
	
	@FindBy(xpath="//div[@class='panel header']//a[contains(text(),'Sign In')]")
	WebElement loginLink;
	
	@FindBy(xpath="//div[@class='panel header']//button[@class='action switch']")
	WebElement btn_CustomerOptionsMenu;
	
	@FindBy(xpath="//div[@class='panel header']//ul[@class='header links']//div[@class='customer-menu']//li[1]")
	WebElement li_MyAccountOption;
	
	public void clickRegisterLink() {
		registerLink.click();
	}
	
	public void clickLoginLink() {
		loginLink.click();
	}
	
	public void clickCustomerOptionsMenu() {
		btn_CustomerOptionsMenu.click();
	}
	
	public void clickMyAccountOption() {
		li_MyAccountOption.click();
	}
	
	public Boolean isCustomerOptionsMenuEnabled() {
		try {
		return btn_CustomerOptionsMenu.isEnabled();
		}catch(Exception e) {
			return false;
		}
	}
}
