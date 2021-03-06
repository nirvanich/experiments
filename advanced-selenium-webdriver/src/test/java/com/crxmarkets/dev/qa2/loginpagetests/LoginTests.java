package com.crxmarkets.dev.qa2.loginpagetests;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crxmarkets.dev.qa2.base.CsvDataProviders;
import com.crxmarkets.dev.qa2.base.TestUtilities;
import com.crxmarkets.dev.qa2.pages.HomePage;
import com.crxmarkets.dev.qa2.pages.LogInPage;

public class LoginTests extends TestUtilities {

	@Test
	public void logTheDefect() {
	
		createJiraIssue("Login test failed", "Actual message is not the same as expected", getSessionId());
	}
	
	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void PositiveLoginTest() {

		SoftAssert softAssert = new SoftAssert();
		
//		open test page
		LogInPage logInPage = new LogInPage(driver, log);
		logInPage.openPage();
		takeScreenshot("LoginPage opened");
//		execute log in
		HomePage homepage = logInPage.logIn("admin@crx.lu", "P@ssw0rd1");
		takeScreenshot("HomePage opened");

//		verifications:
//		 URL
		if (homepage.getCurrentUrl().contains(homepage.getHomePageUrl()) != true) {
			createJiraIssue("Positive login test faised", getBugDescription(homepage.getHomePageUrl(),homepage.getCurrentUrl()), getSessionId());
		}
		
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
		
			
//		Get logs
		List<LogEntry> logs = getBrowserLogs();
		
//		Verifying there are no Java Script Errors in the console
		for (LogEntry logEntry : logs) {
			if (logEntry.getLevel().toString().equals("SEVERE")) {
				softAssert.fail("Severe error: " + logEntry.getMessage());
			}
		}
		softAssert.assertAll();
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
