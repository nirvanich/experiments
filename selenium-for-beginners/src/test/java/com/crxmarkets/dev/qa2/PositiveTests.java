package com.crxmarkets.dev.qa2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void loginTest() {
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
//		url
		String expectedUrl = "https://qa2.dev.crxmarkets.com/crx-web/app/cockpits/crx-home";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");

//		logout button is visible 
		WebElement logOutButton = driver.findElement(By.xpath("//a[@id='mainMenu:crxLogoutItem']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "Logout button is not visible");

//		Successful login message form[name='j_idt62'] > h1
		WebElement successMessage = driver.findElement(By.cssSelector("form[name='j_idt62'] > h1"));
		String expectedMessage = "CRX home";
		String actualMessage = successMessage.getText();
//		Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message is not the same as expected");

//		Close browser
		driver.quit();
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
