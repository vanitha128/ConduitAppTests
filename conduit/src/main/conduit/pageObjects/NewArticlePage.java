package main.conduit.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewArticlePage extends PageObject {
	public NewArticlePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "/html/body/div/div/div/div/div/div/form/fieldset/fieldset[1]/input")
	private WebElement articleTitle;
	
	@FindBy(xpath = "html/body/div/div/div/div/div/div/form/fieldset/fieldset[2]/input")
	private WebElement articleDescription;
	
	@FindBy(xpath = "/html/body/div/div/div/div/div/div/form/fieldset/fieldset[3]/textarea")
	private WebElement articleBody;
	
	@FindBy(xpath = "html/body/div/div/div/div/div/div/form/fieldset/button")
	private WebElement publishArticle;
	
	@FindBy(xpath = "html/body/div/app-header/nav/div/ul[2]/li[1]/a")
	private WebElement home;
	
	
	public void enterArticleTitle(String articleTitle) {
		this.articleTitle.clear();
		this.articleTitle.sendKeys(articleTitle);
	}
	
	public void enterArticleDescription(String articleDescription) {
		this.articleDescription.clear();
		this.articleDescription.sendKeys(articleDescription);
	}
	
	public void enterArticleBody(String articleBody) {
		this.articleBody.clear();
		this.articleBody.sendKeys(articleBody);
	}
	
	public void publishArticle() {
		publishArticle.click();
	}
	
	public void clickHome() {
		home.click();
	}
	
	

}
