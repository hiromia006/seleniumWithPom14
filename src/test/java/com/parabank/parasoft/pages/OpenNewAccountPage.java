package com.parabank.parasoft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenNewAccountPage extends BasePage {
    public OpenNewAccountPage(WebDriver driver) {
        super(driver);
    }

    public OpenedAccountPage clickOpenNewAccountButton() {
        getElement(By.cssSelector("input[value='Open New Account']")).click();
        return navigateToPage(OpenedAccountPage.class);
    }
}
