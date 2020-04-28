package com.crxmarkets.dev.qa2.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

	protected WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser) {

//		Create driver
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

//		Maximize browser window
		driver.manage().window().maximize();

		// implicit wait
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		// Close browser
		driver.quit();
	}

	
	
}