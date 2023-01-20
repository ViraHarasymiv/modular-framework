package com.guru99.tests;

import com.aventstack.extentreports.Status;
import com.guru99.pages.LoginPage;
import commonLibs.implementation.CommonDriver;
import commonLibs.utils.ConfigUtils;
import commonLibs.utils.ReportUtils;
import commonLibs.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Properties;

public class BaseTest {
    CommonDriver commonDriver;
    String url;
    WebDriver driver;
    LoginPage loginPage;
    String currentWorkingDirectory;
    Properties configProperty;
    String configFileName;
    String reportFileName;
    ReportUtils reportUtils;
    ScreenshotUtils screenshot;

    @BeforeSuite
    public void preSetup() throws Exception {
        currentWorkingDirectory = System.getProperty("user.dir");
        configFileName = currentWorkingDirectory + "/config/config.properties";
        reportFileName = currentWorkingDirectory + "/reports/guru99TestReport.html";
        configProperty = ConfigUtils.readProperties(configFileName);
        reportUtils = new ReportUtils(reportFileName);
    }

    @BeforeClass
    public void setup() throws Exception {
        url = configProperty.getProperty("baseUrl");
        String browserType = configProperty.getProperty("browserType");
        commonDriver = new CommonDriver(browserType);
        driver = commonDriver.getDriver();
        loginPage = new LoginPage(driver);
        screenshot = new ScreenshotUtils(driver);
        commonDriver.navigateToUrl(url);
    }
    @AfterMethod
    public void postTestAction(ITestResult result) throws Exception {
        String testCaseName = result.getName();
        long executionTime = System.currentTimeMillis();
        String screenshotFilename = currentWorkingDirectory +
                "/screenshots/"
                + testCaseName + executionTime + ".jpeg";
        if(result.getStatus() == ITestResult.FAILURE){
            reportUtils.addTestLog(Status.FAIL,"One or more steps are failed");
            screenshot.captureAndSaveScreenShot(screenshotFilename);
            reportUtils.attachScreenshotToReport(screenshotFilename);
        }
    }

    @AfterClass
    public void tearDown() {
        commonDriver.closeAllBrowser();
    }

    @AfterSuite
    public void postTearDown(){
        reportUtils.flushReport();
    }
}
