package com.crxmarkets.dev.qa2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

	@Test
	public void incorrectUsernameTest() {
		System.out.println("Starting incorrectUsernameTest..");

//		Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		Maximize browser window
		driver.manage().window().maximize();

//		open test page
		String url = "https://qa2.dev.crxmarkets.com/crx-web";
		driver.navigate().to(url);
		System.out.println("Page is opened.");

//		enter user name
		WebElement username = driver.findElement(By.id("loginForm:username"));
		username.sendKeys("IncorrectUsername");


//		enter password
		WebElement password = driver.findElement(By.xpath("//input[@id='loginForm:password']"));
		password.sendKeys("P@ssw0rd12");

//		click login button
		WebElement logInButton = driver.findElement(By.id("submitBtn"));
		logInButton.click();
//		sleep(3000);

//		verifications:
//		url
		String expectedUrl = "https://login.qa2.dev.crxmarkets.com/login?error=INCORRECT_CREDENTIALS";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");

//		Successful incorrect user message 
		WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-msg-div']"));
		String expectedMessage = "Authentication failed, please try again.";
		String actualMessage = errorMessage.getText();
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
