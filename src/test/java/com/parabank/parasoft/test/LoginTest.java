package com.parabank.parasoft.test;

import com.parabank.parasoft.pages.LoginPage;
import com.parabank.parasoft.pages.OverviewPage;
import com.parabank.parasoft.util.ParaBankUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void checkTittle() {
        LoginPage lPage = pg.navigateToPage(LoginPage.class);
        String title = lPage.getPageTitle();
        Assert.assertEquals(title, ParaBankUtil.TITTLE);
    }

    @Test
    public void loginShouldSucceedWithValidCredentialV1() {
        LoginPage lPage = pg.navigateToPage(LoginPage.class);
        lPage = lPage
                .fillUsername("sqa")
                .fillPassword("sqa");

        OverviewPage oPage = lPage
                .clickLoginButton();
        Assert.assertTrue(oPage.hasLogOutLink());
    }

    @Test
    public void loginShouldSucceedWithValidCredentialV2() {
        OverviewPage oPage = pg.navigateToPage(LoginPage.class)
                .fillUsername("sqa")
                .fillPassword("sqa")
                .clickLoginButton();
        Assert.assertTrue(oPage.hasLogOutLink());
    }
}


