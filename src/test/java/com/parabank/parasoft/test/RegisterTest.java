package com.parabank.parasoft.test;

import com.parabank.parasoft.pages.LoginPage;
import com.parabank.parasoft.pages.OverviewPage;
import com.parabank.parasoft.pages.RegisterPage;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test
    public void registerShouldSucceed() {
        LoginPage page = pg.navigateToPage(LoginPage.class);
        RegisterPage registerPage = page.clickRegisterLink();
        registerPage = registerPage
                .fillFirstName("John")
                .fillLastName("Doe")
                .fillAddress("123 Main St")
                .fillCity("Anytown")
                .fillState("Anystate")
                .fillZipCode("12345")
                .fillPhone("123-456-7890")
                .fillSsn("123-45-6789")
                .fillUsername("johndoe")
                .fillPassword("password123")
                .fillConfirmPassword("password123");

        OverviewPage oPage = registerPage.clickRegisterButton();
        Assert.assertTrue(oPage.hasLogOutLink());
    }

    @Test
    public void registerShouldSucceedV2() {
        String username = LoremIpsum.getInstance().getName().replaceAll(" ", "");
        OverviewPage oPage = pg.navigateToPage(LoginPage.class)
                .clickRegisterLink()
                .fillFirstName(LoremIpsum.getInstance().getFirstName())
                .fillLastName(LoremIpsum.getInstance().getLastName())
                .fillAddress(LoremIpsum.getInstance().getTitle(5))
                .fillCity(LoremIpsum.getInstance().getCity())
                .fillState(LoremIpsum.getInstance().getStateAbbr())
                .fillZipCode(LoremIpsum.getInstance().getZipCode())
                .fillPhone(LoremIpsum.getInstance().getPhone())
                .fillSsn(LoremIpsum.getInstance().getPhone())
                .fillUsername(username)
                .fillPassword(username)
                .fillConfirmPassword(username)
                .clickRegisterButton();
        Assert.assertTrue(oPage.hasLogOutLink());
    }

    @Test
    public void registerShouldFailWithOutUsername() {
        String existingUsername = getUsername(); // Assuming this username already exists
        RegisterPage registerPage = pg.navigateToPage(LoginPage.class)
                .clickRegisterLink()
                .fillFirstName("Jane")
                .fillLastName("Smith")
                .fillAddress("456 Elm St")
                .fillCity("Othertown")
                .fillState("Otherstate")
                .fillZipCode("67890")
                .fillPhone("098-765-4321")
                .fillSsn("987-65-4321")
                .fillPassword("newpassword123")
                .fillConfirmPassword("newpassword123")
                .clickRegisterLink();
        Assert.assertTrue(registerPage.hasErrorMessage(1));
    }

    @Test
    public void registerShouldFailWithOnlyUsername() {
        RegisterPage registerPage = pg.navigateToPage(LoginPage.class)
                .clickRegisterLink()
                .fillFirstName("Jane")
                .clickRegisterLink();
        Assert.assertTrue(registerPage.hasErrorMessage(9));
    }
}
