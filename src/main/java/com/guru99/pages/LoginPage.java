package com.guru99.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @CacheLookup
    @FindBy(name="uid")
    private WebElement userId;

    @CacheLookup
    @FindBy(name="password")
    private WebElement userPassword;

    @CacheLookup
    @FindBy(name="btnLogin")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginToApplication(String username, String password) {
        elementControl.setText(userId, username);
        elementControl.setText(userPassword, password);
        elementControl.clickElement(loginButton);
    }
}
