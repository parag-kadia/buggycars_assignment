package org.justtestit.buggy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Model extends Base {

    @FindBy(xpath = "//h4[contains(text(),'Votes')]/strong")
    WebElement votes;

    @FindBy(id = "comment")
    WebElement comment;

    @FindBy(xpath = "//button[contains(text(),'Vote!')]")
    WebElement vote;

    @FindBy(xpath = "//table[@class='table']//tr[1]//td[1]")
    WebElement voteDate;

    @FindBy(xpath = "//table[@class='table']//tr[1]//td[2]")
    WebElement voteAuthor;

    @FindBy(xpath = "//table[@class='table']//tr[1]//td[3]")
    WebElement voteComment;

    public Model(WebDriver driver) {
        super(driver);
    }

    public int vote(String comment) {
        int voteCountBefore = getVotes();
        this.comment.sendKeys(comment);
        vote.click();
        int voteCountAfter = getVotes();
        while (voteCountAfter == voteCountBefore) {
            voteCountAfter = getVotes();
        }
        return voteCountAfter - voteCountBefore;
    }

    public int getVotes() {
        return Integer.parseInt(votes.getText());
    }

    public String getTopVoteDate() {
        return voteDate.getText();
    }

    public String getTopVoteAuthor() {
        return voteAuthor.getText();
    }

    public String getTopVoteComment() {
        return voteComment.getText();
    }
}
