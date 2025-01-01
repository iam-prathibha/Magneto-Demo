package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//span[@class='base']")
	WebElement txt_Header;
	
	
	@FindBy(xpath="//div[@class='panel header']//button[@class='action switch']")
	WebElement btn_CustomerMenu;
	
	@FindBy(xpath="//div[@class='panel header']//ul[@class='header links']//div[@class='customer-menu']//li[4]")
	WebElement btn_Signout;
	
	public Boolean isMyAccountPageExists() {
		try {
		return txt_Header.isDisplayed();
		}catch(Exception e){
			return false;
		}
	}
	
	public void clickCustomerMenu() {
		btn_CustomerMenu.click();
	}
	
	public void clickSignout() {
		btn_Signout.click();
	}
	
	
	}
	

