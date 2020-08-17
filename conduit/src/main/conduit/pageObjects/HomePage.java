package main.conduit.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject{
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="/html/body/div/app-header/nav/div/ul[2]/li[2]/a")
	private WebElement newArticleLink;
	
	@FindBy(xpath="/html/body/div/div/div/div[2]/div/div[1]/div/ul/li[2]/a")
	private WebElement globalFeed;
	
	@FindBy(xpath="/html/body/div/div/div/div[2]/div/div[1]/article-list/article-preview[1]/div/a")
	private WebElement testArticle;
	
	
	public void clickNewArticleLink() {
		newArticleLink.click();
	}
	
	public void clickGlobalFeed() {
		globalFeed.click();
	}
	
	public void clickTestArticle() {
		testArticle.click();
	}

}
