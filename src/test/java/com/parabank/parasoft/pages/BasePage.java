package com.parabank.parasoft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage extends Page {
    public BasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getElement(By selector) {
        return driver.findElement(selector);
    }

    @Override
    public List<WebElement> getElements(By selector) {
        return driver.findElements(selector);
    }

    @Override
    public String getTextFromElement(By selector) {
        return getElement(selector).getText();
    }

    @Override
    public void clickElement(By selector) {
        getElement(selector).click();

    }

    @Override
    public String getPageTitle() {
        return driver.getTitle();
    }
}
