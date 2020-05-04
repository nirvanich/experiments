package com.crxmarkets.dev.qa2.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OnboardingPage extends HomePage {

	private String pageUrl = "https://qa2.dev.crxmarkets.com/crx-web/app/onboarding/admin-onboarding";

	private By crxActionRequiredCheckbox = By.xpath("//div[@id='onboardingKycTabView:suppliersOnboardingFilterForm:crxActionRequiredFilter']/div[2]");
	private By crxActionRequiredCheckboxChecked = By.cssSelector(".ui-c.ui-chkbox-icon.ui-icon.ui-icon-check");
	private By buyerDropdown = By.xpath("//div[@id='onboardingKycTabView:suppliersOnboardingFilterForm:buyerFilter']");
	private By btCompanySelector = By.xpath("//*[@id='onboardingKycTabView:suppliersOnboardingFilterForm:buyerFilter_panel']/div[2]/ul/li[7]/div");
	private By searchButton = By.xpath("//button[@id='onboardingKycTabView:suppliersOnboardingFilterForm:j_idt100']");
	private By filterKey = By.xpath("//span[@class='ui-panel-title']//span[@class='filterKey']");
	private By filterValue = By.xpath("//span[@class='ui-panel-title']//span[@class='filterValue']");

	public OnboardingPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public void checkingCrxActionRequired() {
		click(crxActionRequiredCheckbox);
	}
	public void clickBuyerDropdown() {
		click(buyerDropdown);
	}
	public void selectBtCompanyFromDropdown() {
		click(btCompanySelector);
	}

	public void clickSearchButton() {
		click(searchButton);
	}

	public String getOnboardingPageUrl() {
		return pageUrl;
	}

	public void waitForFilterResults() {
		waitForCickability(searchButton, 10);
	}

	public List<String> getFilterResults() {
		List<WebElement> filterKeyList = findAll(filterKey);
		List<WebElement> filterValueList = findAll(filterValue);
		List<String> filterResultsList = new ArrayList<String>();

		for (int i = 0; i < filterKeyList.size(); i++) {
			filterResultsList.add(i, filterKeyList.get(i).getText() + " " + filterValueList.get(i).getText());
		}
		return filterResultsList;

	}
	
	public boolean isCrxActionRequiredCheckboxChecked() {
		if (find(crxActionRequiredCheckboxChecked).isDisplayed()){
			return true;
		}
		
		return false;
	}
	
	public boolean isFilterApplied(List<String> list, String filter) {
		for (String result : list) {
			if (result.contains(filter)) {
				return true;
			}
		}
		return false;
	}
}
