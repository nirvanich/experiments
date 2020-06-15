package stepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
public class stepDefinition {

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

}