package stepDefinitions;

import java.util.Collection;

import org.junit.Assume;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import resources.Utilities;


public class Hooks {
	
	@Before
	public void beforeScenario(Scenario sc) {
		String tag;
		Collection<String> tags = sc.getSourceTagNames();
		if(tags!=null){

			for(int i=0; i<tags.size(); i++) {
				tag = tags.iterator().next().split("@")[1];
				if (tag.startsWith("RAT")) {
				System.out.println(tag);
				}
				Assume.assumeTrue(Utilities.getAutomationStatus(tag));
			}
			
		}		
		
	}
}