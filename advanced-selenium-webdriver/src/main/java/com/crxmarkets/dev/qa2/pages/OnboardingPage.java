package com.crxmarkets.dev.qa2.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OnboardingPage extends HomePage {

	private String pageUrl = "https://qa2.dev.crxmarkets.com/crx-web/app/onboarding/admin-onboarding";

	private By crxActionRequiredCheckbox = By
			.xpath("//div[@id='onboardingKycTabView:suppliersOnboardingFilterForm:crxActionRequiredFilter']/div[2]");
	private By crxActionRequiredCheckboxChecked = By.cssSelector(".ui-c.ui-chkbox-icon.ui-icon.ui-icon-check");
	private By buyerDropdown = By.xpath("//div[@id='onboardingKycTabView:suppliersOnboardingFilterForm:buyerFilter']");
	private By btCompanySelector = By.xpath(
			"//*[@id='onboardingKycTabView:suppliersOnboardingFilterForm:buyerFilter_panel']/div[2]/ul/li[7]/div");
	private By searchButton = By.xpath("//button[@id='onboardingKycTabView:suppliersOnboardingFilterForm:j_idt100']");
	private By filterKey = By.xpath("//span[@class='ui-panel-title']//span[@class='filterKey']");
	private By filterValue = By.xpath("//span[@class='ui-panel-title']//span[@class='filterValue']");
	private By anyRowWithRolexSupplier = By.xpath("//*[contains(text(),'Rolex')]");
	private By uploadNewProposalsButton = By.xpath("//button/*[contains(text(),'Upload New Proposals')]");
	private By uploadFormBuyerInput = By.xpath("//div[@id='onboardingKycTabView:supplierOnboardingTabView:uploadForm:buyerInput']");
	private By asusSelectorInUploadForm = By.xpath("//*[@id='onboardingKycTabView:supplierOnboardingTabView:uploadForm:buyerInput_items']/*[contains(text(),'ASUS')]");
	private By chooseFileButton = By.xpath("//span[@role='button']");
	private By uploadProposalsButton = By.xpath("//button[@id='onboardingKycTabView:supplierOnboardingTabView:uploadForm:uploadButton']");
	private By inputFile = By.xpath("//input[@id='onboardingKycTabView:supplierOnboardingTabView:uploadForm:j_idt113_input']");
	
	public OnboardingPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public void checkingCrxActionRequired() {
		click(crxActionRequiredCheckbox);
	}

	public void clickBuyerDropdown() {
		click(buyerDropdown);
	}
	
	public void clickuploadProposalsButton() {
		click(uploadProposalsButton);
	}
	
	public void clickChooseFileButton() {
		click(chooseFileButton);
	}

	public void selectBtCompanyFromDropdown() {
		click(btCompanySelector);
	}
	
	public void selectAsusFromUploadDropdown() {
		click(asusSelectorInUploadForm);
	}

	public void clickSearchButton() {
		click(searchButton);
	}
	
	public void clickUploadNewProposalsButton() {
		click(uploadNewProposalsButton);
	}

	public void clickUploadFormBuyerInput() {
		click(uploadFormBuyerInput);
	}
	
	public void searchByEnter() {
		pressKey(searchButton, Keys.ENTER);
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
		if (find(crxActionRequiredCheckboxChecked).isDisplayed()) {
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

	public void clickOnRolexSupplier() {
		log.info("Opening Manage Rolex Onboarding page");
		click(anyRowWithRolexSupplier);
	}

	public ManageOnboardingPage switchToManageOnboardingPage() {
		log.info("Switching to Manage Onboarding page");
		switchToWindowWithTitle("Manage");
		return new ManageOnboardingPage(driver, log);
	}
	
	public void selectFile(String fileName) {
		log.info("Selecting '" + fileName + "' file from Files folder");
		String filePath = "C:\\Gitstuff\\advanced-selenium-webdriver\\src\\main\\resources\\Proposals.Import1.xlsx";
		//String filePath = System.getProperty("user.dir") + "//src//main//resources//" + fileName;
		typeWithActions(filePath, inputFile);
		log.info("File " + filePath + " selected");
	}
}
