package com.parabank.parasoft.test;

import com.parabank.parasoft.pages.ApprovedLoanPage;
import com.parabank.parasoft.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RequestLoanTest extends BaseTest {

    @Test
    public void requestLoanShouldSucceed() {
        ApprovedLoanPage page4 = pg
                .navigateToPage(LoginPage.class)
                .doLogin(getUsername(), getPassword())
                .clickRequestLoanLink()
                .fillLoanAmount("5000")
                .fillDownPayment("1000")
                .clickApplyNowButton();
        Assert.assertTrue(page4.isApprovedLoanId());
    }


}
