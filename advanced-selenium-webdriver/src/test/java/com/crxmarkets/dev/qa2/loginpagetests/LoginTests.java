package com.crxmarkets.dev.qa2.loginpagetests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.crxmarkets.dev.qa2.base.TestUtilities;
import com.crxmarkets.dev.qa2.pages.LogInPage;
import com.crxmarkets.dev.qa2.pages.HomePage;

public class LoginTests extends TestUtilities {

	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void PositiveLoginTest() {

//		open test page
		LogInPage logInPage = new LogInPage(driver, log);
		logInPage.openPage();

//		execute log in
		HomePage homepage = logInPage.logIn("admin@crx.lu", "P@ssw0rd12");

//		verifications:
//		 URL
		Assert.assertEquals(homepage.getCurrentUrl(), homepage.getHomePageUrl(),
				"Actual page url is not the same as expected");

//		logout button is visible 
		Assert.assertTrue(homepage.logOutButtonVisible(), "Logout button is not visible");

//		Successful login message 
		String expectedMessage = "CRX home";
		String actualMessage = homepage.getSuccessLoginMessage();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message is not the same as expected\nExpected message: [" + expectedMessage
						+ "]\nActual message: [" + actualMessage + "]");

	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 2, groups = { "negativeTests", "smokeTests" })
	public void negativeLoginTest(String username, String password, String expectedMessage) {

//		open test page
		LogInPage logInPage = new LogInPage(driver, log);
		logInPage.openPage();

//		execute log in
		logInPage.logIn(username, password);
		
		logInPage.waitForErrorMessage();

//		verifications:
//		URL
		Assert.assertTrue(logInPage.getCurrentUrl().contains(logInPage.getErrorPageUrl()),
				"Actual page url is not the same as expected");

//		Successful incorrect user message 
		Assert.assertTrue(logInPage.getErrorMessage().contains(expectedMessage),
				"Actual error message is not the same as expected");

	}

}
