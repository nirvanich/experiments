package com.crxmarkets.dev.qa2.loginpagetests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crxmarkets.dev.qa2.base.CsvDataProviders;
import com.crxmarkets.dev.qa2.base.TestUtilities;
import com.crxmarkets.dev.qa2.pages.HomePage;
import com.crxmarkets.dev.qa2.pages.LogInPage;

public class LoginTests extends TestUtilities {

	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void PositiveLoginTest() {

//		open test page
		LogInPage logInPage = new LogInPage(driver, log);
		logInPage.openPage();
		takeScreenshot("LoginPage opened");
//		execute log in
		HomePage homepage = logInPage.logIn("admin@crx.lu", "P@ssw0rd12");
		takeScreenshot("HomePage opened");

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

	
	@Test(priority = 1, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
	public void negativeLoginTest(Map<String, String> testData) {
		
		SoftAssert softAssert = new SoftAssert();
//		Data
		String no = testData.get("no");
		String username = testData.get("username");
		String password = testData.get("password");
		String expectedMessage = testData.get("expectedMessage");
		String description = testData.get("description");
		
		log.info("Starting negativeLoginTest #" + no + " for " + description);
		
//		open test page
		LogInPage logInPage = new LogInPage(driver, log);
		logInPage.openPage();

//		execute log in
		logInPage.logIn(username, password);
		
		logInPage.waitForErrorMessage();

//		verifications:
//		URL
		softAssert.assertTrue(logInPage.getCurrentUrl().contains(logInPage.getErrorPageUrl()),
				"Actual page url is not the same as expected");
		
//		Successful incorrect user message 
		Assert.assertTrue(logInPage.getErrorMessage().contains(expectedMessage),
				"Actual error message is not the same as expected");
		
	}

}
