package org.justtestit.buggy.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.justtestit.buggy.Web;

import static org.junit.Assert.*;

@Log4j2
public class Voting {

    final Web web;

    int voteIncrement;
    String dateTime;
    String comment;
    String voteDateOld;
    String voteAuthorOld;
    String voteCommentOld;

    public Voting(Web web) {
        this.web = web;
    }

    @Given("I'm in Overall Rating page")
    public void i_m_in_overall_rating_page() {
        dateTime = web.home.preLogin();
        web.home.clickOverallRating();
    }

    @When("I select a car {string} & {string} from the list")
    public void i_select_a_car_from_the_list(String make, String model) {
        web.overallRating.selectCar(make, model);
    }

    @When("I vote with a comment {string}")
    public void i_vote_with_a_comment(String comment) {
        voteDateOld = web.model.getTopVoteDate();
        voteAuthorOld = web.model.getTopVoteAuthor();
        voteCommentOld = web.model.getTopVoteComment();
        this.comment = comment + " on " + dateTime;
        voteIncrement = web.model.vote(this.comment);
    }

    @Then("the vote count should increase by one")
    public void the_vote_count_should_increase_by_one() {
        assertEquals(1, voteIncrement);
    }

    @Then("I should be able to see my vote in the table")
    public void i_should_be_able_to_see_my_vote_in_the_table() {
        String voteDateNew = web.model.getTopVoteDate();
        String voteAuthorNew = web.model.getTopVoteAuthor();
        String voteCommentNew = web.model.getTopVoteComment();

        assertFalse("vote date is empty", voteDateNew.isEmpty());
        assertFalse("vote author is empty", voteAuthorNew.isEmpty());
        assertFalse("vote comment is empty", voteCommentNew.isEmpty());

        assertNotEquals(voteDateOld, voteDateNew);
        assertNotEquals(voteAuthorOld, voteAuthorNew);
        assertNotEquals(voteCommentOld, voteCommentNew);

        String firstName = voteAuthorNew.split(" ")[0];
        String lastName = voteAuthorNew.split(" ")[1];

        assertEquals("fn" + dateTime, firstName);
        assertEquals("ln" + dateTime, lastName);
    }

    @When("I vote without a comment")
    public void i_vote_without_a_comment() {
        voteDateOld = web.model.getTopVoteDate();
        voteAuthorOld = web.model.getTopVoteAuthor();
        voteCommentOld = web.model.getTopVoteComment();

        voteIncrement = web.model.vote("");
    }

}
