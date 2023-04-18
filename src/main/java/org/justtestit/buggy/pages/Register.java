package org.justtestit.buggy.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class Register extends Base {

    @FindBy(id = "username")
    WebElement userName;

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "confirmPassword")
    WebElement confirmPassword;

    @FindBy(xpath = "//button[contains(text(),'Register')]")
    WebElement register;

    @FindBy(xpath = "//div[contains(text(),'Registration is successful')]")
    WebElement successMessage;

    @FindBy(xpath = "//div[contains(text(),'UsernameExistsException: User already exists')]")
    WebElement errorMessage;

    public Register(WebDriver driver) {
        super(driver);
    }

    public String registerANewUser(String login, String firstName, String lastName, String password) {
        String dateTime = "";
        if(login.endsWith("-RANDOM")){
            dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSS"));
            login = login.replace("RANDOM", dateTime);
            firstName = firstName + dateTime;
            lastName = lastName + dateTime;
        }
        //log.info("Login:" + login);
        this.userName.sendKeys(login);
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.password.sendKeys(password);
        this.confirmPassword.sendKeys(password);
        register.click();

        return dateTime;
    }

    public String getSuccessMessage() {
        return waitUntilVisibilityOf(successMessage).getText();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
