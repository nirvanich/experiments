package com.crxmarkets.dev.qa2.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePageObject {

	private String pageUrl = "https://qa2.dev.crxmarkets.com/crx-web/app/cockpits/crx-home";
	private By logOutButton = By.xpath("//a[@id='mainMenu:crxLogoutItem']");
	private By message = By.cssSelector("form[name='j_idt62'] > h1");
	
	public SecureAreaPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	/** Expected URL of the page */
	public String getPageUrl() {
		return pageUrl;
	}

	public boolean logOutButtonVisible() {
		return find(logOutButton).isDisplayed();
	}
	
	public String getSuccessMessage() {
		return find(message).getText();
	}
}
