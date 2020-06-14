package stepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
public class stepDefinition {

 
@Given("^User is on landing page$")
    public void user_is_on_landing_page() 
    {
       System.out.println("Landing Page is opened");
    }

    @When("^User login into application with username and password$")
    public void user_login_into_application_with_username_and_password()  
    { 
        System.out.println("User successfully logged in");
    }

    @Then("^Home page is populated$")
    public void home_page_is_populated()  
    {
    	System.out.println("Home Page is opened with correct URL");
    }

    @And("^logout button is visible$")
    public void logout_button_is_visible() 
    {
        System.out.println("Log out button is visible");
    }

}