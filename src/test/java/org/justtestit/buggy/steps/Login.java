package org.justtestit.buggy.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.justtestit.buggy.Web;

import static org.junit.Assert.assertTrue;

@Log4j2
public class Login {

    final Web web;

    public Login(Web web) {
        this.web = web;
    }

    @Given("I'm in Buggy Cars Rating home page")
    public void i_m_in_buggy_cars_rating_home_page() {
      web.home.isLoginAvailable();
    }

    @When("I enter User Name {string} and Password {string}")
    public void i_enter_user_name_and_password(String userName, String password) {
        web.home.login(userName,password);
    }

    @Then("I should be able to login to the system with greeting {string}")
    public void i_should_be_able_to_login_to_the_system_with_greeting(String greeting) {
        assertTrue(web.home.isUserLoggedIn(greeting).contains(greeting));
    }

    @Then("I should get an error message {string}")
    public void i_should_get_an_error_message(String errorMessage) {
        assertTrue(web.home.getErrorMessage().contains(errorMessage));
    }

}
