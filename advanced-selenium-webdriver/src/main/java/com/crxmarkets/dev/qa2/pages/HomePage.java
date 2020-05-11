package com.crxmarkets.dev.qa2.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePageObject {

	private String homePageUrl = "https://qa2.dev.crxmarkets.com/crx-web/app/cockpits/crx-home";
	private By logOutButton = By.xpath("//a[@id='mainMenu:crxLogoutItem']");
	private By message = By.cssSelector("form[name='j_idt62'] > h1");
	private By onboardingButton = By.xpath("//a[@id='mainMenu:crxSuppliersOnboardingItem']");
	private By auctionBundlingButton = By.xpath("//a[@id='mainMenu:crxAttemptBundlingItem']");

	public HomePage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/** Expected URL of the page */
	public String getHomePageUrl() {
		return homePageUrl;
	}

	public boolean logOutButtonVisible() {
		return find(logOutButton).isDisplayed();
	}

	public String getSuccessLoginMessage() {
		return find(message).getText();
	}
	
	public OnboardingPage openOnboardingPage() {
		log.info("Opening Onboarding page");
		click(onboardingButton);
		return new OnboardingPage(driver, log);
	}
	
	public AuctionBundlingPage openAuctionBundlingPage() {
		log.info("Opening Auction Bundling page");
		click(auctionBundlingButton);
		return new AuctionBundlingPage(driver, log);
	}
}