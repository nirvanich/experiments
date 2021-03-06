package com.crxmarkets.dev.qa2.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageOnboardingPage extends HomePage {

	private String pageUrl = "https://qa2.dev.crxmarkets.com/crx-web/app/onboarding/admin-manage-onboarding";

	private By headerText = By.xpath("//form[@id='manageOnboardingMsgForm']");

	public ManageOnboardingPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public String getManageOnboardingPageUrl() {
		return pageUrl;
	}

	public void waitForHeader() {
		waitForVisibilityOf(headerText, 10);
	}
	
	public void printHeaderText() {
		System.out.println(find(headerText).getText());
	}
	
	public boolean isHeaderTextCorrect(String s) {
		if (find(headerText).getText().contains(s)) {
			return true;
		}

		return false;
	}

	
}
