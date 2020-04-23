package com.crxmarkets.dev.qa2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {

	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void PositiveLoginTest() {
		System.out.println("Starting LoginTest..");

//		Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		Maximize browser window
		driver.manage().window().maximize();

//		open test page
		String url = "https://qa2.dev.crxmarkets.com/crx-web";
		driver.get(url);
		System.out.println("Page is opened.");

//		enter user name
		WebElement username = driver.findElement(By.id("loginForm:username"));
		username.sendKeys("admin@crx.lu");

//		enter password
		WebElement password = driver.findElement(By.xpath("//input[@id='loginForm:password']"));
		password.sendKeys("P@ssw0rd12");

//		click login button
		WebElement logInButton = driver.findElement(By.id("submitBtn"));
		logInButton.click();
//		sleep(3000);

//		verifications:

		// URL
		String expectedUrl = "https://qa2.dev.crxmarkets.com/crx-web/app/cockpits/crx-home";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");

//		logout button is visible 
		WebElement logOutButton = driver.findElement(By.xpath("//a[@id='mainMenu:crxLogoutItem']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "Logout button is not visible");

//		Successful login message 
		WebElement successMessage = driver.findElement(By.cssSelector("form[name='j_idt62'] > h1"));
		String expectedMessage = "CRX home";
		String actualMessage = successMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message is not the same as expected");

//		Close browser
		driver.quit();
	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 2, groups = { "negativeTests", "smokeTests" })
	public void negativeLoginTest(String username, String password, String expectedMessage) {
		System.out.println("Starting negativeLoginTest with credentials: " + username + ":" + password);

//		Create driver
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

//		Maximize browser window
		driver.manage().window().maximize();

//		open test page
		String url = "https://qa2.dev.crxmarkets.com/crx-web";
		driver.navigate().to(url);
		System.out.println("Page is opened.");

//		enter user name
		WebElement usernameElement = driver.findElement(By.id("loginForm:username"));
		usernameElement.sendKeys(username);

//		enter password
		WebElement passwordElement = driver.findElement(By.xpath("//input[@id='loginForm:password']"));
		passwordElement.sendKeys(password);

//		click login button
		WebElement logInButton = driver.findElement(By.id("submitBtn"));
		logInButton.click();
//		sleep(3000);

//		verifications:
//		url
		String expectedUrl = "https://login.qa2.dev.crxmarkets.com/login?error";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertTrue(actualUrl.contains(expectedUrl), "Actual page url is not the same as expected");

//		Successful incorrect user message 
		WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-msg-div']"));
		String actualMessage = errorMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message is not the same as expected");

//		Close browser
		driver.quit();
	}

	/*
	 * private void sleep(long m) { try { Thread.sleep(m); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */
}
