package test.conduit;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import main.conduit.pageObjects.NewArticlePage;
import main.conduit.pageObjects.LoginPage;
import main.conduit.pageObjects.HomePage;
import test.conduit.setup.SetupTest;

public class ConduitFrontEndSigninTest extends SetupTest {

	private String driverUrl = "https://demo.realworld.io/";

	@DataProvider(name = "testDataForArticleCreation")
	public Object[][] getDataForArticleCreation() {
		return new Object[][] { { "zeroloose00@gmail.com", "testing123", "test article zeroloose",
				"this is test article", "this is my sample test article" }, };
	}

	@Test(groups = { "article" }, dataProvider = "testDataForArticleCreation")
	public void testSignInAndPublishArticle(String email, String password, String articleTitle,
			String articleDescription, String articleBody) throws InterruptedException {
		driverUrl = "https://demo.realworld.io/#/login";
		driver.get(driverUrl);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.clickSubmitButton();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// click on new article after signing in
		HomePage homePage = new HomePage(driver);
		homePage.clickNewArticleLink();

		NewArticlePage newArticlePage = new NewArticlePage(driver);
		newArticlePage.enterArticleTitle(articleTitle);
		newArticlePage.enterArticleDescription(articleDescription);
		newArticlePage.enterArticleBody(articleBody);
		newArticlePage.publishArticle();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		newArticlePage.clickHome();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// click home button and navigate to global feed
		// TODO: Need to check the xpath since it seems different
		// homePage.clickGlobalFeed();
		// Thread.sleep(2000);
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// navigate to the article of global feed
		// homePage.clickTestArticle();

		driver.findElement(By.xpath("/html/body/div/app-header/nav/div/ul[2]/li[3]/a")).click();
		driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/button")).click();
	}

}
