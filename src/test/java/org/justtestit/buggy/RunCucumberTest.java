package org.justtestit.buggy;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"org.justtestit.buggy.steps"},
        plugin = {
                "pretty",
                "html:target/cucumber-html-reports/default.html",
                "timeline:target/cucumber-html-reports/timeline",
                "json:target/cucumber-html-reports/json/cucumber.json"
        }
)
public class RunCucumberTest {

}
