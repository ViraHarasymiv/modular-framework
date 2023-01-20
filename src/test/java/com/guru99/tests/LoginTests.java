package com.guru99.tests;

import com.aventstack.extentreports.Status;
import commonLibs.utils.ReportUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    @Parameters({"username","password"})
    @Test
    public void verifyUserLoginWithCorrectCredentials(String username, String password) {
        reportUtils.createATestCase("verify user login with correct credentials");
        reportUtils.addTestLog(Status.INFO,"Performing Login");
        loginPage.loginToApplication(username, password);
        String expectedTitle = "Guru99 Bank Manager HomePage";
        String actualTitle = commonDriver.getTitleOfThePage();
        reportUtils.addTestLog(Status.INFO, "Comparing expected and actual titles");
        Assert.assertEquals(actualTitle, expectedTitle);
    }
}
