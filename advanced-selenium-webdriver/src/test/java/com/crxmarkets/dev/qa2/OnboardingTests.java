package com.crxmarkets.dev.qa2;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crxmarkets.dev.qa2.base.TestUtilities;
import com.crxmarkets.dev.qa2.pages.HomePage;
import com.crxmarkets.dev.qa2.pages.LogInPage;
import com.crxmarkets.dev.qa2.pages.ManageOnboardingPage;
import com.crxmarkets.dev.qa2.pages.OnboardingPage;

public class OnboardingTests extends TestUtilities {

	@Test
	public void searchingCrxActionRequired() {

		// open main page
		LogInPage loginPage = new LogInPage(driver, log);
		loginPage.openPage();

		// execute log in
		HomePage homepage = loginPage.logIn("admin@crx.lu", "P@ssw0rd12");

		// click Onboarding button
		OnboardingPage onboardingPage = homepage.openOnboardingPage();

		// checking CRX Action Required checkbox
		onboardingPage.checkingCrxActionRequired();

		// Open Buyers dropdown
		onboardingPage.clickBuyerDropdown();

		// Select Buyer from the filter
		onboardingPage.selectBuyerCompanyFromDropdown("11111");

		// clicking Search button
		onboardingPage.clickSearchButton();
		onboardingPage.waitForFilterResults();

		// Verify checkbox checked & filter applied
		// URL
		Assert.assertTrue(onboardingPage.getCurrentUrl().contains(onboardingPage.getOnboardingPageUrl()),
				"Actual page url is not the same as expected");

		// Filter applied and displayed

		Assert.assertTrue(onboardingPage.isFilterApplied(onboardingPage.getFilterResults(), "CRX Action Required yes"),
				"CRX Action Required filter is not applied");

		Assert.assertTrue(onboardingPage.isFilterApplied(onboardingPage.getFilterResults(), "Buyer 11111"),
				"11111 filter is not applied");

		Assert.assertTrue(onboardingPage.isCrxActionRequiredCheckboxChecked(),
				"CRX Action Required checkbox is not checked");

	}
	@Test
	public void verifyManageOnboardingPage() {

		// open main page
		LogInPage loginPage = new LogInPage(driver, log);
		loginPage.openPage();

		// execute log in
		HomePage homepage = loginPage.logIn("admin@crx.lu", "P@ssw0rd12");

		// click Onboarding button
		OnboardingPage onboardingPage = homepage.openOnboardingPage();

		// checking CRX Action Required checkbox
		onboardingPage.checkingCrxActionRequired();

		// Open Buyers dropdown
		onboardingPage.clickBuyerDropdown();

		// Select BT Buyer
		onboardingPage.selectBuyerCompanyFromDropdown("BT Company");

		// clicking Search button
		//onboardingPage.clickSearchButton();
		onboardingPage.searchByEnter();
		onboardingPage.waitForFilterResults();

		// Verify checkbox checked & filter applied
		// URL
		Assert.assertTrue(onboardingPage.getCurrentUrl().contains(onboardingPage.getOnboardingPageUrl()),
				"Actual page url is not the same as expected");

		// Filter applied and displayed

		Assert.assertTrue(onboardingPage.isFilterApplied(onboardingPage.getFilterResults(), "CRX Action Required yes"),
				"CRX Action Required filter is not applied");

		Assert.assertTrue(onboardingPage.isFilterApplied(onboardingPage.getFilterResults(), "Buyer BT Company"),
				"BT Company filter is not applied");

		Assert.assertTrue(onboardingPage.isCrxActionRequiredCheckboxChecked(),
				"CRX Action Required checkbox is not checked");

		// open manage onboarding page by click on onboarding process
		onboardingPage.clickOnRolexSupplier();
		sleep(1000);
		
		// switch to new open page by page Title
		ManageOnboardingPage manageOnboardingPage = onboardingPage.switchToManageOnboardingPage();

		// verify header text
		Assert.assertTrue(manageOnboardingPage.isHeaderTextCorrect("Onboarding for Rolex and BT Company"), "Onboarding of wrong Buyer and Supplier is opened");
	}

}
