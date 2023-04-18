package org.justtestit.buggy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class OverallRating extends Base {

    public OverallRating(WebDriver driver) {
        super(driver);
    }

    public Model selectCar(String make, String model) {
        if(driver.findElements(By.linkText(model)).size() != 0){
            driver.findElement(By.linkText(model)).click();
        } else if(driver.findElements(By.linkText(make)).size() != 0){
            driver.findElement(By.linkText(make)).click();
            driver.findElement(By.linkText(model)).click();
        }
        return new Model(driver);
    }
}
