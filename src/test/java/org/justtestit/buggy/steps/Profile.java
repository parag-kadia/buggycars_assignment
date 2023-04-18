package org.justtestit.buggy.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.justtestit.buggy.Web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Log4j2
public class Profile {

    final Web web;

    String gender;
    String age;
    String address;

    public Profile(Web web) {
        this.web = web;
    }

    @Given("I'm in Buggy Cars Rating profile page")
    public void i_m_in_buggy_cars_rating_profile_page() {
        web.home.preLogin();
        web.home.clickProfile();
    }

    @When("I update gender {string}, age {string} and address {string}")
    public void i_update_gender_age_and_address(String gender, String age, String address) {
        this.gender = gender;
        this.age = age;
        this.address = address;
        String status = web.profile.updateData(gender, age, address);
        assertEquals("The profile has been saved successful", status);
    }

    @Then("I should be able to view save data")
    public void i_should_be_able_to_view_save_data() {
        web.home.clickBuggyRating();
        web.home.clickProfile();

        assertEquals(gender, web.profile.getGender());
        assertEquals(age, web.profile.getAge());
        assertEquals(address, web.profile.getAddress());

    }

}
