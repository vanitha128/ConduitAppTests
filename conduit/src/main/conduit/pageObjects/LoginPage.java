package main.conduit.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {
	
	
	
	@FindBy(xpath="/html/body/div/div/div/div/div/div/form/fieldset/fieldset[2]/input")
	private WebElement email;
	@FindBy(xpath="/html/body/div/div/div/div/div/div/form/fieldset/fieldset[3]/input")
	private WebElement password;
	@FindBy(xpath="/html/body/div/div/div/div/div/div/form/fieldset/button")
	private WebElement submitButton;
	
	public LoginPage(WebDriver driver) {
		super(driver);
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
}
