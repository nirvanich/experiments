package com.crxmarkets.dev.qa2.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OnboardingPage extends HomePage {

	private String pageUrl = "https://qa2.dev.crxmarkets.com/crx-web/app/onboarding/admin-onboarding";

	private By crxActionRequiredCheckbox = By
			.xpath("//div[@id='onboardingKycTabView:suppliersOnboardingFilterForm:crxActionRequiredFilter']/div[2]");
	private By crxActionRequiredCheckboxChecked = By.cssSelector(".ui-c.ui-chkbox-icon.ui-icon.ui-icon-check");
	private By buyerDropdown = By.xpath("//div[@id='onboardingKycTabView:suppliersOnboardingFilterForm:buyerFilter']");
	private String buyerSelectorFilter = "//*[@id='onboardingKycTabView:suppliersOnboardingFilterForm:buyerFilter_panel']";
	private By searchButton = By.xpath("//button[contains(.,'Search')]");
	private By filterKey = By.xpath("//span[@class='ui-panel-title']//span[@class='filterKey']");
	private By filterValue = By.xpath("//span[@class='ui-panel-title']//span[@class='filterValue']");
	private By anyRowWithRolexSupplier = By.xpath("//*[contains(text(),'Rolex')]");
	private By uploadNewProposalsButton = By.xpath("//button/*[contains(text(),'Upload New Proposals')]");
	private By uploadFormBuyerInput = By.xpath("//div[@id='onboardingKycTabView:supplierOnboardingTabView:uploadForm:buyerInput']");
	private String buyerSelectorInUploadForm = "//*[@id='onboardingKycTabView:supplierOnboardingTabView:uploadForm:buyerInput_items']";
	private By chooseFileButton = By.xpath("//span[@role='button']");
	private By uploadProposalsButton = By.xpath("//button[@id='onboardingKycTabView:supplierOnboardingTabView:uploadForm:uploadButton']");
	private By inputFile = By.xpath("//input[@type='file']");
	private By SupplierSearchString = By.xpath("//input[@id='onboardingKycTabView:suppliersOnboardingFilterForm:searchTextFilter']");
	
	
	public OnboardingPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public void checkingCrxActionRequired() {
		click(crxActionRequiredCheckbox);
	}

	public void clickBuyerDropdown() {
		log.info("Clicking on buyer dropdown in the filter section");
		click(buyerDropdown);
	}
	
	public void clickUploadProposalsButton() {
		log.info("Clicking Upload Proposals button");
		click(uploadProposalsButton);
	}
	
	public void clickChooseFileButton() {
		click(chooseFileButton);
	}

	public void selectBuyerCompanyFromDropdown(String buyer) {
		log.info("Selecting '" + buyer + "' from the filter dropdown");
		By buyerSelector = By.xpath(buyerSelectorFilter + "//*[contains(text(),'" + buyer + "')]");
		click(buyerSelector);
	}
	
	public void selectBuyerFromUploadDropdown(String buyer) {
		log.info("Selecting '" + buyer + "' from the dropdown");
		By buyerSelector = By.xpath(buyerSelectorInUploadForm + "/*[contains(text(),'" + buyer + "')]");
		click(buyerSelector);
	}
	
	public void inputSupplierSearchString(String supplier) {
		log.info("Typing '" + supplier + "' into Supplier Search String");
		type(supplier, SupplierSearchString);		
	}
	
	public void clickSearchButton() {
		log.info("Clicking Search button");
		click(searchButton);
	}
	
	public void clickUploadNewProposalsButton() {
		log.info("Clicking Upload New Proposals button");
		click(uploadNewProposalsButton);
	}

	public void clickUploadFormBuyerInput() {
		log.info("Open Buyer selector in the Upload New Proposals popup");
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
	
	public int countFilterResults(String buyer, String supplier) {
		String xpathForChecks =
				  "//*[@id='onboardingKycTabView:supplierOnboardingTabView:suppliersOnboardingFormId:supplierOnboardingSummaryTable_data']"
				  + "/*[contains(.,'" + buyer + "') and contains(.,'" + supplier + "')]";
		List<WebElement> filterResultsRows = findAll(By.xpath(xpathForChecks));
		int count = filterResultsRows.size();
		return count;
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
	
	/** Type into hidden element via JS */
	protected void jsTypeToUploadField(String text, By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"document.evaluate(\"//input[@type='file']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.setAttribute('aria-hidden', 'false')");
		find(locator).sendKeys(text);
		js.executeScript(
				"document.evaluate(\"//input[@type='file']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.setAttribute('aria-hidden', 'true')");

	}
	
	public void selectFile(String fileName) {
		log.info("Selecting '" + fileName + "' file from Files folder");
		//String filePath = "C:\\Gitstuff\\advanced-selenium-webdriver\\src\\main\\resources\\files\\Proposals.Import1.xlsx";
		String filePath = System.getProperty("user.dir") + "//src//main//resources//files//" + fileName;
		jsTypeToUploadField(filePath, inputFile);
		log.info("File " + filePath + " selected");
	}
}
