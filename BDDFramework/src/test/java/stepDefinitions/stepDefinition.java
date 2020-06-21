package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;

import Cucumber.Automation.ReUsableMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilities;

@RunWith(Cucumber.class)
public class stepDefinition extends Utilities {
	
	RequestSpecification jiraCreateIssue;
	ResponseSpecification jsonStatus;
	Response response;
	TestDataBuild data = new TestDataBuild();

	@Given("^User is on login page$")
	public void user_is_on_login_page() throws Throwable {
		System.out.println("Login page is opened");

	}

	@When("^User login with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_login_with_something_and_something(String strArg1, String strArg2) throws Throwable {
		System.out.println("User is logging in with username:[" + strArg1 + "] and password:[" + strArg2 + "]");

	}

	@Then("^Page is opened with correct URL - \"([^\"]*)\"$")
	public void page_is_opened_with_correct_url_something(String strArg1) throws Throwable {
		System.out.println("Page URL is corresponding to expected: " + strArg1);

	}

	@And("^Logout button is visible$")
	public void logout_button_is_visible() throws Throwable {
		System.out.println("Logout Button is visible");

	}

	@And("^Correct error message is displayed - \"([^\"]*)\"$")
	public void correct_error_message_is_displayed_something(String strArg1) throws Throwable {
		System.out.println("Error message is corresponding to expected: " + strArg1);

	}

	 @Given("^Add issue payload: \\\"([^\\\"]*)\\\", \\\"([^\\\"]*)\\\", \\\"([^\\\"]*)\\\"$")
	    public void add_issue_payload(String priority, String summary, String version) throws Throwable {
			
		 	jiraCreateIssue = given().spec(requestSpecification()).body(data.addIssuePayload(priority, summary, version));
			
	    }

	 @When("User calls {string} resource for issue {string} with {string} http request")
	    public void user_calls_something_with_post_http_request(String resource, String issueKey, String method) throws Throwable {
		 
		 if (method.equalsIgnoreCase("POST")) {
			APIResources resourceAPI = APIResources.valueOf(resource);
			response = jiraCreateIssue.when().post(resourceAPI.getResource());
		 } 
		 else if (method.equalsIgnoreCase("GET")) {
			 APIResources resourceAPI = APIResources.valueOf(resource);
			 response = given().spec(requestSpecification()).when().get(resourceAPI.getResource() + "/" + issueKey);
		 }
		 else if (method.equalsIgnoreCase("DELETE")) {
			 APIResources resourceAPI = APIResources.valueOf(resource);
			 response = given().spec(requestSpecification()).when().delete(resourceAPI.getResource() + "/" + issueKey);
		 }
		 
	    }

	    @Then("^Response status code \"([^\"]*)\"$")
	    public void the_api_call_is_success_with_status_code_something(int strArg1) throws Throwable {
	    	System.out.println(response.asString());
	    	assertEquals(response.getStatusCode(),strArg1);
	    }


}