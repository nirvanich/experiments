package stepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
public class stepDefinition {

    @Given("^User is on landing page$")
    public void user_is_on_landing_page() throws Throwable {
        throw new PendingException();
    }

    @When("^User login into application with username and password$")
    public void user_login_into_application_with_username_and_password() throws Throwable {
        throw new PendingException();
    }

    @Then("^Home page is populated$")
    public void home_page_is_populated() throws Throwable {
        throw new PendingException();
    }

    @And("^logout button is visible$")
    public void logout_button_is_visible() throws Throwable {
        throw new PendingException();
    }

}