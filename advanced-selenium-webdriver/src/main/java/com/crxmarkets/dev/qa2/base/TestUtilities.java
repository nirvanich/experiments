package com.crxmarkets.dev.qa2.base;

public class TestUtilities extends BaseTest {
	
	// STATIC SLEEP
	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
