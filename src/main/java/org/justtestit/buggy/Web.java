package org.justtestit.buggy;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.justtestit.buggy.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Log4j2
public class Web {

    public WebDriver driver;

    public Home home;
    public Register register;
    public OverallRating overallRating;
    public Make make;
    public Model model;
    public Profile profile;

    public Web() {
        ThreadContext.put("loggerId", "main");
        initDriver();
        initPages();
    }

    private void initPages() {
        log.info("Initializing page objects...");
        home = new Home(driver);
        register = new Register(driver);
        overallRating = new OverallRating(driver);
        make = new Make(driver);
        model = new Model(driver);
        profile = new Profile(driver);
        log.info(String.format("Thread-%s %s", Thread.currentThread().getId(), "Pages Initialized"));
    }

    public void initDriver() {
        log.info("Initializing driver...");
        if (driver == null) {
            if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                System.setProperty("webdriver.chrome.driver", "C://Workspace//drivers//chromedriver//chromedriver");
            } else {
                System.setProperty("webdriver.chrome.driver", "C://Workspace//drivers//chromedriver//chromedriver.exe");
            }
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get("https://buggy.justtestit.org/");
        }
    }

    public void restDriver() {
        if (driver != null && !driver.getCurrentUrl().equals("https://buggy.justtestit.org/")) {
            driver.manage().deleteAllCookies();
            driver.get("https://buggy.justtestit.org/");
        }
    }

    public void quitDriver() {
        driver.quit();
    }

}
