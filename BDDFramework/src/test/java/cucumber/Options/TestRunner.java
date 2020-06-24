package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features/Login.feature",
		glue = "stepDefinitions",
		stepNotifications = true/*
								 * , tags = "@GetDefect"
								 */
		)

public class TestRunner {
	
	

}
