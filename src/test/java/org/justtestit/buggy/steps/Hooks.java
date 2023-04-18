package org.justtestit.buggy.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.justtestit.buggy.Web;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Log4j2
public class Hooks {

    final Web web;

    public Hooks(Web web) {
        this.web = web;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        //web.restDriver();
        ThreadContext.put("loggerId", scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        web.quitDriver();
        ThreadContext.put("loggerId", "main");
    }

}