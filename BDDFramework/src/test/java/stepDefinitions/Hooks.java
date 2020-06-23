package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@GetDefect")
	public void beforeScenario() {
		System.out.println("BeforeScenario test method execution");
		
	}

}
