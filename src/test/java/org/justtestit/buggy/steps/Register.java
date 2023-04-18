package org.justtestit.buggy.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.justtestit.buggy.Web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@Log4j2
public class Register {

    final Web web;

    public Register(Web web) {
        this.web = web;
    }

    @Given("I'm in Register page")
    public void i_m_in_register_page() {
        web.home.clickRegister();
    }

    @When("I submit Login {string}, First Name {string}, Last Name {string}, Password {string},random {string}")
    public void i_submit_login_first_name_last_name_password_random(String login, String firstName, String lastName, String password,String Random1) {
        Random random = new Random();
        String x = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSS"));;
        if(Random1.equals("true")) {
            web.register.registerANewUser(login + x, firstName, lastName, password);
        }else {
            web.register.registerANewUser(login , firstName, lastName, password);
        }
    }

    @Then("I should be see success message {string}")
    public void i_should_be_see_success_message(String message) {
        assertEquals(message, web.register.getSuccessMessage());
    }

    @Then("I should be see error message {string}")
    public void i_should_be_see_error_message(String message) {
        assertEquals(message, web.register.getErrorMessage());
    }

}