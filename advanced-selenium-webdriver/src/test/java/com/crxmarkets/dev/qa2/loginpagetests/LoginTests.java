package com.crxmarkets.dev.qa2.loginpagetests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.crxmarkets.dev.qa2.base.TestUtilities;
import com.crxmarkets.dev.qa2.pages.LogInPageObject;
import com.crxmarkets.dev.qa2.pages.SecureAreaPage;

public class LoginTests extends TestUtilities {

	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void PositiveLoginTest() {

		log.info("Starting login Test");

//		open test page
		LogInPageObject logInPage = new LogInPageObject(driver, log);
		logInPage.openPage();

//		execute logIn
		SecureAreaPage homepage = logInPage.logIn("admin@crx.lu", "P@ssw0rd12");

//		verifications:

		// URL
		Assert.assertEquals(homepage.getCurrentUrl(), homepage.getPageUrl(),
				"Actual page url is not the same as expected");

//		logout button is visible 
		Assert.assertTrue(homepage.logOutButtonVisible(), "Logout button is not visible");

//		Successful login message 
		String expectedMessage = "CRX home";
		String actualMessage = homepage.getSuccessMessage();
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message is not the same as expected\nExpected message: [" + expectedMessage + "]\nActual message: [" + actualMessage + "]");

	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 2, groups = { "negativeTests", "smokeTests" })
	public void negativeLoginTest(String username, String password, String expectedMessage) {

		log.info("Starting negativeLoginTest with credentials: " + username + ":" + password);

//		open test page
		String url = "https://qa2.dev.crxmarkets.com/crx-web";
		driver.navigate().to(url);
		log.info("Page is opened.");

//		enter user name
		WebElement usernameElement = driver.findElement(By.id("loginForm:username"));
		usernameElement.sendKeys(username);

//		enter password
		WebElement passwordElement = driver.findElement(By.xpath("//input[@id='loginForm:password']"));
		passwordElement.sendKeys(password);

//		click login button
		WebElement logInButton = driver.findElement(By.id("submitBtn"));
		logInButton.click();

//		verifications:
//		URL
		String expectedUrl = "https://login-qa2.dev.crxmarkets.com/login?error";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertTrue(actualUrl.contains(expectedUrl), "Actual page url is not the same as expected");

//		Successful incorrect user message 
		WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-msg-div']"));
		String actualMessage = errorMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message is not the same as expected");

	}

}
