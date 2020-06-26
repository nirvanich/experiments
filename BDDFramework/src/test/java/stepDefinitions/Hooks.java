package stepDefinitions;

import java.io.IOException;
import java.util.Collection;

import org.junit.Assume;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import resources.Utilities;


public class Hooks {
	String afterTag;
	@Before
	public void beforeScenario(Scenario sc) throws IOException {
		String tag;
		Collection<String> tags = sc.getSourceTagNames();
		if(tags!=null){
			for(int i=0; i<tags.size(); i++) {
				tag = (String) tags.toArray()[i];
				if (tag.startsWith("@Issue")) {
				tag = tag.split("#")[1].split("\"")[0];
				System.out.println(tag);
				afterTag = tag;
				Assume.assumeTrue(Utilities.getAutomationStatus(tag));
				Utilities.transitIssueToInProgress(tag);
				}
			}
		}	
	}
	
	@After
	public void afterScenario(Scenario sc) {
		String status = sc.getStatus().toString();
		System.out.println(status);
		System.out.println("------------------------------");
		
		if (status.contains("PASSED")) {
			Utilities.transitIssueToPassed(afterTag);
		} else if (status.contains("FAILED")) {
			Utilities.transitIssueToFailed(afterTag);
		}
	}
}