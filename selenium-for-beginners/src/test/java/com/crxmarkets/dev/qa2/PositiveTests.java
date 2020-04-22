package com.crxmarkets.dev.qa2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

//		sleep for 2 seconds
		sleep(500);

//		enter user name
		WebElement username = driver.findElement(By.id("loginForm:username"));
		username.sendKeys("admin@crx.lu");
		sleep(500);

//		enter password
		WebElement password = driver.findElement(By.xpath("//input[@id='loginForm:password']"));
		password.sendKeys("P@ssw0rd12");
		sleep(500);

//		click login button
		WebElement logInButton = driver.findElement(By.id("submitBtn"));
		logInButton.click();
		sleep(3000);

//		verifications:
//		logout button is visible 
		WebElement logOutButton = driver.findElement(By.xpath("//a[@id='mainMenu:crxLogoutItem']"));

//		Successful login message form[name='j_idt62'] > h1
		WebElement successMessage = driver.findElement(By.cssSelector("form[name='j_idt62'] > h1"));
		
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
