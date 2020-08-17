package main.conduit.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupPage extends PageObject{
	
	@FindBy(xpath="/html/body/div/div/div/div/div/div/form/fieldset/fieldset[1]/input")
	private WebElement userName;
	
	@FindBy(xpath="html/body/div/div/div/div/div/div/form/fieldset/fieldset[2]/input")
	private WebElement email;
	
	@FindBy(xpath="html/body/div/div/div/div/div/div/form/fieldset/fieldset[3]/input")
	private WebElement password;
	
	@FindBy(xpath="/html/body/div/div/div/div/div/div/form/fieldset/button")
	private WebElement submitButton;
	
	@FindBy(xpath="html/body/div/div/div/div[2]/div/div[1]/div/ul/li[1]/a")
	private WebElement yourFeed;
	
	

	public SignupPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void enterEmail(String email) {
		this.email.clear();
		this.email.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}
	
	public void clickSubmitButton() {
		submitButton.click();
	}
	// TODO:: this is not working
	public boolean isInitialized() {
		return yourFeed.isDisplayed();
	}
}
