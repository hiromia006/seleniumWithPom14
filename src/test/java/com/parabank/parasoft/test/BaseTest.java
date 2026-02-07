package com.parabank.parasoft.test;

import com.parabank.parasoft.pages.BasePage;
import com.parabank.parasoft.pages.Page;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    Page pg;
    private Properties prop;

    public BaseTest() {
        String path = System.getProperty("user.dir") + "/src/test/resources/config.properties";
        prop = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(path);
            prop.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void browserSetup() {
        String browserName = prop.getProperty("browserName");

        if (Objects.equals(browserName, "firefox")) {
            if (System.getProperty("os.name").toLowerCase().contains("linux"))
                System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
            driver = new FirefoxDriver();
        } else if (Objects.equals(browserName, "chrome")) {
            driver = new ChromeDriver();
        } else if (Objects.equals(browserName, "edge")) {
            driver = new EdgeDriver();
        } else if (Objects.equals(browserName, "safari")) {
            // SafariDriver is included with Safari on macOS, no need to set a system property
            driver = new SafariDriver();
        } else if (Objects.equals(browserName, "headlessFirefox")) {
            if (System.getProperty("os.name").toLowerCase().contains("linux"))
                System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        } else if (Objects.equals(browserName, "headlessChrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }


        driver.manage().window().maximize();
        driver.get(prop.getProperty("baseUrl"));
        pg = new BasePage(driver);

    }

    @AfterMethod
    public void browserTeardown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(result.getName());
        }
        driver.quit();
    }

    public String getUsername() {
        return prop.getProperty("username");
    }

    public String getPassword() {
        return prop.getProperty("password");
    }

    public void takeScreenshot(String fileName) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String currentDir = System.getProperty("user.dir") + "/build/screenshots/";
            FileUtils.copyFile(scrFile, new File(currentDir + fileName + "_"+System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public WebDriver getWebDriver() {
        return driver;
    }
}
