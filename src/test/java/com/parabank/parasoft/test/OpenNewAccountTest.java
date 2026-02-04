package com.parabank.parasoft.test;

import com.parabank.parasoft.pages.LoginPage;
import com.parabank.parasoft.pages.OpenNewAccountPage;
import com.parabank.parasoft.pages.OpenedAccountPage;
import com.parabank.parasoft.pages.OverviewPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenNewAccountTest extends BaseTest {

    @Test
    public void openNewAccountShouldSucceed() {
        LoginPage page = pg.navigateToPage(LoginPage.class);

        OverviewPage page1 = page
                .doLogin(getUsername(), getPassword());

        OpenNewAccountPage page2 = page1
                .clickOpenNewAccountLink();

        OpenedAccountPage page3 = page2
                .clickOpenNewAccountButton();
        Assert.assertTrue(page3.hasOpenedAccountId());
    }

    @Test
    public void openNewAccountShouldSucceedV2() {
        OpenedAccountPage page3 = pg
                .navigateToPage(LoginPage.class)
                .doLogin(getUsername(), getPassword())
                .clickOpenNewAccountLink()
                .clickOpenNewAccountButton();
        Assert.assertTrue(page3.hasOpenedAccountId());
    }

}
