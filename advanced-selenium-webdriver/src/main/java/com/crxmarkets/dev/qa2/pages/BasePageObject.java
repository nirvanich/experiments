package com.crxmarkets.dev.qa2.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {

	protected WebDriver driver;
	protected Logger log;

	public BasePageObject(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;

	}

	/** Open page with given URL */
	protected void openUrl(String url) {
		driver.get(url);
	}

	/** Get the current page URL from the browser */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/** Get the current page title */
	public String getCurrentPageTitle() {
		return driver.getTitle();
	}

	/** Get the current page HTML sourse */
	public String getCurrentPageSource() {
		return driver.getPageSource();
	}

	/** Find element using given locator */
	protected WebElement find(By locator) {
		return driver.findElement(locator);
	}

	protected List<WebElement> findAll(By locator) {
		return driver.findElements(locator);
	}

	/** Click on element with given locator when its visible */
	protected void click(By locator) {
		waitForVisibilityOf(locator, 5);
		find(locator).click();
	}

	/** Type given text into element with given locator */
	protected void type(String text, By locator) {
		waitForVisibilityOf(locator, 5);
		find(locator).sendKeys(text);
	}

	/**
	 * Wait for specific ExpectedCondition for the given amount of time in seconds
	 */
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}

	/**
	 * Wait for given number of seconds for element with given locator to be visible
	 * on the page
	 */
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}

	protected void waitForCickability(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.elementToBeClickable(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}

	/**
	 * Switch to a browser tab by particular Title
	 */
	public void switchToWindowWithTitle(String expectedTitle) {
		String firstWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> windowsIterator = allWindows.iterator();

		while (windowsIterator.hasNext()) {
			String windowHandle = windowsIterator.next().toString();
			if (!windowHandle.equals(firstWindow)) {
				driver.switchTo().window(windowHandle);
				if (getCurrentPageTitle().contains(expectedTitle)) {
					break;
				}
			}
		}

	}

	/** Press key on given locator when its visible */
	protected void pressKey(By locator, Keys key) {
		waitForVisibilityOf(locator, 5);
		find(locator).sendKeys(key);
	}

	/** Press key using Action class */
	protected void pressKeyWithActions(Keys key) {
		log.info("Pressing " + key.name() + " using Actions class");
		Actions action = new Actions(driver);
		action.sendKeys(key).build().perform();
	}

	/** Type into element using Action class */
	protected void jsTypeToUploadField(String text, By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"document.evaluate(\"//input[@type='file']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.setAttribute('aria-hidden', 'false')");
		find(locator).sendKeys(text);
		js.executeScript(
				"document.evaluate(\"//input[@type='file']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.setAttribute('aria-hidden', 'true')");

	}
}