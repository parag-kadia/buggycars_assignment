package org.justtestit.buggy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home extends Base {

    @FindBy(linkText = "Buggy Rating")
    WebElement buggyRating;

    @FindBy(linkText = "Register")
    WebElement register;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    WebElement login;

    @FindBy(name = "login")
    WebElement userName;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//span[contains(text(),'Hi')] ")
    WebElement greeting;

    @FindBy(xpath = "//span[contains(text(),'Invalid username/password')]")
    WebElement errorMessage;

    @FindBy(xpath = "//a[@href='/overall']")
    WebElement overallRating;

    @FindBy(linkText = "Profile")
    WebElement profile;

    public Home(WebDriver driver) {
        super(driver);
    }

    public Register clickRegister() {
        register.click();
        return new Register(driver);
    }

    public boolean isLoginAvailable() {
        return login.isEnabled();
    }

    public Home login(String userName, String password) {
        this.userName.sendKeys(userName);
        this.password.sendKeys(password);
        login.click();
        return new Home(driver);
    }

    public String isUserLoggedIn(String greeting) {
        return this.greeting.getText();
    }


    public String getErrorMessage() {
        return this.errorMessage.getText();
    }

    public OverallRating clickOverallRating() {
        overallRating.click();
        return new OverallRating(driver);
    }

    public Profile clickProfile() {
        profile.click();
        return new Profile(driver);
    }

    public Home clickBuggyRating(){
        buggyRating.click();
        return this;
    }
}
