package com.parabank.parasoft.test;

import com.parabank.parasoft.pages.BasePage;
import com.parabank.parasoft.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    WebDriver driver;
    Page pg;

    @BeforeMethod
    public void browserSetup() {
        if (System.getProperty("os.name").toLowerCase().contains("linux"))
            System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        pg=new BasePage(driver);

    }

    @AfterMethod
    public void browserTeardown() {
        driver.quit();
    }
}
