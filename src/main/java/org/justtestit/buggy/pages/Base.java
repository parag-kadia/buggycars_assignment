package org.justtestit.buggy.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

@Log4j2
public class Base {

    protected final WebDriver driver;
    protected final WebDriverWait driverWait;

    public Base(WebDriver driver) {
        this.driver = driver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 15), this);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        int timeoutInSeconds = 30;
        driverWait = new WebDriverWait(this.driver, timeoutInSeconds);
    }

    public WebElement waitUntilVisibilityOf(WebElement element) {
        log.debug("Wait until element to be visible...");
        return (WebElement) driverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public String getTitle() {
        log.info("Returning Title...");
        String title = driver.getTitle();
        int counter = 0;
        while (title.isEmpty() && counter++ < 10) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                log.error(e);
            }
            title = driver.getTitle();
        }
        return title;
    }

    public String getPageSource() {
        log.info("Returning Page Source...");
        return driver.getPageSource();
    }

    public void mouseover(WebElement webElement) {
        Actions action = new Actions(driver);
        action.moveToElement(webElement).build().perform();
    }

    public String preLogin() {
        new Home(driver).clickRegister();
        String dateTime = new Register(driver).registerANewUser("parag-RANDOM", "fn", "ln", "BCR123^bcr");
        driver.navigate().back();
        //Temporary fix to site slowness 
        try {
            sleep(30000);
        } catch (InterruptedException e) {
            log.error(e);
        }
        //waitUntilVisibilityOf(driver.findElement(By.xpath("//a[@href='/overall']")));
       // new Home(driver).login("BCR-" + dateTime, "BCR123^bcr");
        new Home(driver).login("parag-" + dateTime, "BCR123^bcr");
        return dateTime;
    }

}
