package com.crxmarkets.dev.qa2.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class AuctionBundlingPage extends HomePage {

	private String pageUrl = "https://qa2.dev.crxmarkets.com/crx-web/app/auctions/auction-bundling-cockpit";

	private By vattenfallButton = By.xpath("//button[@id='buyersSelector:selectBuyerBtn10604']");
	private By plannedAuctionDateCalendarButton = By.xpath("//*[@id='bundlesDetailsForm:bundlesTable:0:auctionDate']/button");
	private By todaysDate = By.xpath("//*[contains(@class, 'ui-datepicker-today')]");
	private By changingPlannedAuctionDateConfirmationDialog = By.xpath("//*[@id='commonConfirmationDialog']");
	private By changingPlannedAuctionDateYesButton = By.xpath("//*[@id='commonConfirmationDialogForm:commonConfirmationDialogConfirmButton']");
	private By auctionDateInput = By.xpath("//*[@id='bundlesDetailsForm:bundlesTable:0:auctionDate_input']");
	private By systemClock = By.xpath("//*[@id='crxSystemClock']");

	public AuctionBundlingPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public void clickVattenfallButton() {
		click(vattenfallButton);
	}

	public String getAuctionBundlingPageUrl() {
		return pageUrl;
	}

	public void clickPlannedAuctionDateCalendarButton() {
		click(plannedAuctionDateCalendarButton);
	}

	public void selectTodaysDate() {
		click(todaysDate);
	}

	public void confirmChangingAuctionDate() {
		click(changingPlannedAuctionDateYesButton);
	}
	
	public void printPlannedDateText() {
		System.out.println(find(auctionDateInput).getAttribute("value"));
	}
	public boolean isPannedAuctionDateToday(String s) {
		if (find(auctionDateInput).getAttribute("value").contains(s)) {
			return true;
		}

		return false;
	}

	public String getCurrentDate() {
		String allDate = find(systemClock).getText();
		String currentDate = allDate.substring(0, allDate.indexOf(" "));
		
		return currentDate;
		
	}
}
