package com.crxmarkets.dev.qa2.base;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

	protected WebDriver driver;
	protected Logger log;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser, ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
		log = LogManager.getLogger(testName);
		
		BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
		driver = factory.createDriver();

		// Maximize browser window
		driver.manage().window().maximize();

		// implicit wait
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		log.info("Close Driver");
		// Close browser
		driver.quit();
	}

}
