package com.crxmarkets.dev.qa2;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExceptionsTests {
	private WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void setUp(@Optional("chrome") String browser) {

		// Create driver
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("Do not know how to start " + browser + ", starting Chrome instead");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}

		// Maximize browser window
		driver.manage().window().maximize();
	}
	// implicit wait
	// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	@AfterMethod(alwaysRun = true)
	private void tearDown() {

		// Close browser
		driver.quit();
	}

	@Test(priority = 1)
	public void notVisibleTest() {

		String url = "http://the-internet.herokuapp.com/dynamic_loading/1";
		driver.get(url);

		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement finishButton = driver.findElement(By.id("finish"));
		wait.until(ExpectedConditions.visibilityOf(finishButton));

		String expectedText = "Hello World!";
		Assert.assertEquals(finishButton.getText(), expectedText);
	}

	@Test(priority = 2)
	public void timeoutTest() {

		String url = "http://the-internet.herokuapp.com/dynamic_loading/1";
		driver.get(url);

		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 2);
		WebElement finishButton = driver.findElement(By.id("finish"));
		try {
			wait.until(ExpectedConditions.visibilityOf(finishButton));
		} catch (TimeoutException exception) {
			System.out.println("Exception cathced: " + exception.getMessage());
			sleep(3000);
		}

		String expectedText = "Hello World!";
		Assert.assertEquals(finishButton.getText(), expectedText);
	}

	@Test(priority = 3)
	public void noSuchElementTest() {

		String url = "http://the-internet.herokuapp.com/dynamic_loading/2";
		driver.get(url);

		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		Assert.assertTrue(
				wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish"), "Hello World!")),
				"Couldn't find text \"Hello World!\"");

//		WebElement finishButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));
//
//		String expectedText = "Hello World!";
//		Assert.assertEquals(finishButton.getText(), expectedText);
	}

	@Test(priority = 4)
	public void staleElementTest() {
		driver.get("http://the-internet.herokuapp.com/dynamic_controls");

		WebElement checkbox = driver.findElement(By.id("checkbox"));
		WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));

		WebDriverWait wait = new WebDriverWait(driver, 10);

		removeButton.click();
//		wait.until(ExpectedConditions.invisibilityOf(checkbox));
//		Assert.assertFalse(checkbox.isDisplayed());

//		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(checkbox)),
//				"The checkbox is still visible when it shouldn't");

		Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(checkbox)),
				"The checkbox is still visible when it shouldn't");

		WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
		addButton.click();

		checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));
		Assert.assertTrue(checkbox.isDisplayed(), "The checkbox is not visible when it should be");

	}

	@Test
	public void disabledElementTest() {
		driver.get("http://the-internet.herokuapp.com/dynamic_controls");

		WebElement enableButton = driver.findElement(By.xpath("//form[@id='input-example']/button"));
		WebElement textField = driver.findElement(By.xpath("//form[@id='input-example']/input"));

		enableButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(textField));

		textField.sendKeys("Text");
		
//		enableButton.click();
//		
//		wait.until(ExpectedConditions.textToBePresentInElement(enableButton, "Enable"));
		Assert.assertEquals(textField.getAttribute("value"), "Text");
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
