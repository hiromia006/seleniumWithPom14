package com.parabank.parasoft.pages;

import com.aventstack.extentreports.Status;
import com.parabank.parasoft.report.ReportTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BasePage extends Page {
    public BasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getElement(By selector) {
        WebElement element = null;
        try {
            addInfo("Selenium Webdriver going to find a WebElement with " + selector + " Locator");
            element = driver.findElement(selector);
            addInfo("Selenium Webdriver found a Web Element with " + selector + " Locator");
        } catch (Exception e) {
            addFailInfo("Selenium Webdriver is not found a Web Element with " + selector + " Locator");
            System.out.println("Element not found: " + selector);
        }
        return element;
    }

    @Override
    public List<WebElement> getElements(By selector) {
        List<WebElement> elements = null;
        try {
            addInfo("Selenium Webdriver going to find a WebElements with " + selector + " Locator");
            elements = driver.findElements(selector);
            addInfo("Selenium Webdriver found a Web Elements with " + selector + " Locator");
        } catch (Exception e) {
            addFailInfo("Selenium Webdriver is not found a Web Elements with " + selector + " Locator");
            System.out.println("Elements not found: " + selector);
        }
        return elements;
    }

    @Override
    public String getTextFromElement(By selector) {
        return getElement(selector).getText();
    }

    @Override
    public void clickElement(By selector) {
        getElement(selector).click();
        addInfo("Selenium Webdriver clicked on the element with " + selector + " Locator");

    }

    @Override
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Override
    public void waitForElementToBeVisible(By selector) {
        addInfo("Selenium Webdriver is waiting for the element with " + selector + " Locator to be visible");
        wait.until(ExpectedConditions.visibilityOf(getElement(selector)));
    }

    public void addInfo(String message) {
        if (ReportTestManager.getTest() != null) {
            ReportTestManager.getTest().log(Status.INFO, message);
        }
    }

    public void addFailInfo(String message) {
        if (ReportTestManager.getTest() != null) {
            ReportTestManager.getTest().log(Status.FAIL, message);
        }
    }
}
