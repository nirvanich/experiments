package com.crxmarkets.dev.qa2.uploadtests;

import org.testng.annotations.Test;

import com.crxmarkets.dev.qa2.base.TestUtilities;
import com.crxmarkets.dev.qa2.pages.HomePage;
import com.crxmarkets.dev.qa2.pages.LogInPage;
import com.crxmarkets.dev.qa2.pages.OnboardingPage;

public class UploadTests extends TestUtilities {

	@Test(dataProvider = "files")
	public void UploadSupplierProposalTest(int no, String fileName) {
		log.info("Starting Upload Supplier Proposal Test #" + no + " for " + fileName);
		// open main page
		LogInPage loginPage = new LogInPage(driver, log);
		loginPage.openPage();

		// execute log in
		HomePage homepage = loginPage.logIn("admin@crx.lu", "P@ssw0rd12");

		// click Onboarding button
		OnboardingPage onboardingPage = homepage.openOnboardingPage();

		// click upload new proposals button
		onboardingPage.clickUploadNewProposalsButton();

		// open buyers drop down
		onboardingPage.clickUploadFormBuyerInput();
		sleep(500);

		// select ASUS buyer from the dropdown
		onboardingPage.selectBuyerFromUploadDropdown("vattenfall");
		sleep(500);

		// click choose file button
		// onboardingPage.clickChooseFileButton();

		// send file location
		onboardingPage.selectFile(fileName);

		// click upload button
		onboardingPage.clickUploadProposalsButton();
		sleep(4000);

		// verify growl message

		// apply filter to see newly onboarded supplier
		// Open Buyers dropdown
		onboardingPage.clickBuyerDropdown();

		// Select Buyer from the filter
		onboardingPage.selectBuyerCompanyFromDropdown("vattenfall");

		// Input Supplier to the Supplier search String field
		if (no==1) {
			
			onboardingPage.inputSupplierSearchString("MultiProductTestSupplier2");

			// clicking Search button
			onboardingPage.clickSearchButton();
			onboardingPage.waitForFilterResults();

			// check onboarding processes in the table
			System.out.println("There are " + onboardingPage.countFilterResults("vattenfall", "MultiProductTestSupplier2")
					+ " matching row(s) for the Buyer+Supplier");
		} 
		else {
			if (no==2) {
				onboardingPage.inputSupplierSearchString("MultiProductTestSupplier3");

				// clicking Search button
				onboardingPage.clickSearchButton();
				onboardingPage.waitForFilterResults();

				// check onboarding processes in the table
				System.out.println("There are " + onboardingPage.countFilterResults("vattenfall", "MultiProductTestSupplier3")
						+ " matching row(s) for the Buyer+Supplier");
			} 
			
		}

	}

}
