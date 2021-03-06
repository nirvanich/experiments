package com.crxmarkets.dev.qa2;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crxmarkets.dev.qa2.base.TestUtilities;
import com.crxmarkets.dev.qa2.pages.AuctionBundlingPage;
import com.crxmarkets.dev.qa2.pages.HomePage;
import com.crxmarkets.dev.qa2.pages.LogInPage;

public class AuctionBundlingTests extends TestUtilities {

	@Test
	public void settingPlannedAuctionDateToToday() {
		log.info("Starting test of changing Planned Auction Date to Today");

		// open main page
		LogInPage loginPage = new LogInPage(driver, log);
		loginPage.openPage();

		// execute log in
		HomePage homepage = loginPage.logIn("admin@crx.lu", "P@ssw0rd12");

		// click Auction Bundling button
		AuctionBundlingPage auctionBundlingPage = homepage.openAuctionBundlingPage();
		//verifications:
				// URL
		Assert.assertTrue(auctionBundlingPage.getCurrentUrl().contains(auctionBundlingPage.getAuctionBundlingPageUrl()), "Page URL is not equals to expected");
		
		// click Vattenfall button
		auctionBundlingPage.clickVattenfallButton();
		
		// click Planned Auction Date Calendar Button
		auctionBundlingPage.clickPlannedAuctionDateCalendarButton();
		
		// pick Todays Date
		auctionBundlingPage.selectTodaysDate();
		
		// confirm in the dialog
		auctionBundlingPage.confirmChangingAuctionDate();
		sleep(500);
		
		//verifications:
			// Planned Auction Date input
		Assert.assertTrue(auctionBundlingPage.isPannedAuctionDateToday(auctionBundlingPage.getCurrentDate()), "Auction Planned Date is not changed to Today");
	}

}
