package test.conduit;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import main.conduit.pageObjects.SignupPage;
import test.conduit.setup.SetupTest;

public class ConduitFrontEndSignupTest extends SetupTest{


	private String driverUrl = "https://demo.realworld.io/";

	@DataProvider(name = "testData")
	public Object[][] getData() {
		return new Object[][] { { "vanity0000", "zeroloose00000", "testing123" }, };
	}


	@Test(groups = { "signup", "critical" }, dataProvider = "testData")
	public void testSignup(String userName, String email, String password) throws InterruptedException {
		Random rand = new Random();

		// Generate random integers in range 0 to 999
		int rand_int1 = rand.nextInt(100000);
		driver.get(driverUrl);
		// click on sign up link
		// home page
		WebElement signup = driver.findElement(By.linkText("Sign up"));

		signup.click();
		SignupPage signupPage = new SignupPage(driver);
		// Signup page
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		signupPage.enterUserName(userName + rand_int1);
		signupPage.enterEmail(email + rand_int1 + "@gmail.com");
		signupPage.enterPassword(password);
		signupPage.clickSubmitButton();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(signupPage.isInitialized());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// TODO:: add logout
		/// html/body/div/app-header/nav/div/ul[2]/li[3]/a
		driver.findElement(By.xpath("/html/body/div/app-header/nav/div/ul[2]/li[3]/a")).click();
		driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/button")).click();

	}

	


}
