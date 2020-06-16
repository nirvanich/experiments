package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import Cucumber.Automation.ReUsableMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jiraPojo.Fields;
import jiraPojo.IssueType;
import jiraPojo.NewIssue;
import jiraPojo.Priority;
import jiraPojo.Project;
import jiraPojo.Reporter;
import jiraPojo.Versions;

@RunWith(Cucumber.class)
public class stepDefinition {
	
	RequestSpecification jiraBugRequest;
	ResponseSpecification jsonStatus;
	Response response;

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

	 @Given("^Add defect payload$")
	    public void add_defect_payload() throws Throwable {
			//RestAssured.baseURI = "http://localhost:8085";
			
			NewIssue bug = new NewIssue();
			Fields fields = new Fields();
			Project project = new Project();
			IssueType issuetype = new IssueType();
			Reporter reporter = new Reporter();
			Priority priority = new Priority();
			Versions affectedVersion = new Versions();
			List<Versions> versions = new ArrayList<Versions>();
			
			reporter.setName("nirvanich");
			issuetype.setName("Bug");
			project.setKey("RAT");
			priority.setName("Highest");
			affectedVersion.setName("1.0");
			versions.add(affectedVersion);
					
			fields.setReporter(reporter);
			fields.setIssuetype(issuetype);
			fields.setProject(project);
			fields.setSummary("Defect made of Serialized Json");
			fields.setDescription("Test description");
			fields.setPriority(priority);
			fields.setVersions(versions);
			bug.setFields(fields);
			
			
			RequestSpecification jiraSpec = new RequestSpecBuilder().setBaseUri("http://localhost:8085").setContentType(ContentType.JSON).addCookie("JSESSIONID", ReUsableMethods.getSessionId()).build();
			jiraBugRequest = given().spec(jiraSpec).body(bug);
			jsonStatus = new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();
	    }

	    @When("^User calls \"([^\"]*)\" with post http request$")
	    public void user_calls_something_with_post_http_request(String strArg1) throws Throwable {
	    	response = 
					jiraBugRequest.when().post("/rest/api/2/issue")
					.then().spec(jsonStatus).extract().response();
	    }

	    @Then("^the API call is success with status code \"([^\"]*)\"$")
	    public void the_api_call_is_success_with_status_code_something(int strArg1) throws Throwable {
	    	System.out.println(response.asString());
	    	assertEquals(response.getStatusCode(),strArg1);
	    }


}