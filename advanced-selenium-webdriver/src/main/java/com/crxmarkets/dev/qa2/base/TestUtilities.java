package com.crxmarkets.dev.qa2.base;

import org.testng.annotations.DataProvider;

public class TestUtilities extends BaseTest {
	
	// STATIC SLEEP
	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider(name="files")
	protected static Object[][] files(){
		return new Object[][] {
			{1, "Proposals.Import1.xlsx"},
			{2, "Proposals.Import2.xlsx"}
			
		};
		
		
	}

}
