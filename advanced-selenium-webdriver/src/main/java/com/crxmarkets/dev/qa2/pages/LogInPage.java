package com.crxmarkets.dev.qa2.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage extends BasePageObject {

	private String pageUrl = "https://qa2.dev.crxmarkets.com/crx-web";
	private String errorPageUrl = "https://login-qa2.dev.crxmarkets.com/login?error";

	private By usernameLocator = By.id("loginForm:username");
	private By passwordLocator = By.xpath("//input[@id='loginForm:password']");
	private By logiInButtonLocator = By.id("submitBtn");
	private By errorMessage = By.xpath("//form");
	
	public LogInPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Open LogInPage with it's URL
	public void openPage() {
		log.info("Opening page: " + pageUrl);
		openUrl(pageUrl);
		log.info("Page is opened!");
	}
	public String getErrorPageUrl() {
		return errorPageUrl;
	}
	
	public String getErrorMessage() {
		return find(errorMessage).getText();
	}
	
	public void waitForErrorMessage() {
		waitForVisibilityOf(errorMessage, 5);
	}

	public HomePage logIn(String username, String password) {
		log.info("Executing Login with username [" + username + "] and password [" + password + "]");
		type(username, usernameLocator);
		type(password, passwordLocator);
		click(logiInButtonLocator);
		return new HomePage(driver, log);
	}
}
