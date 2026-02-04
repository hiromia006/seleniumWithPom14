package com.parabank.parasoft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestLoanPage extends BasePage {
    public RequestLoanPage(WebDriver driver) {
        super(driver);
    }

    public RequestLoanPage fillLoanAmount(String amount) {
        getElement(By.id("amount")).sendKeys(amount);
        return this;
    }

    public RequestLoanPage fillDownPayment(String downPayment) {
        getElement(By.id("downPayment")).sendKeys(downPayment);
        return this;
    }

    public ApprovedLoanPage clickApplyNowButton() {
        clickElement(By.cssSelector("input[value='Apply Now']"));
        return navigateToPage(ApprovedLoanPage.class);
    }
}
