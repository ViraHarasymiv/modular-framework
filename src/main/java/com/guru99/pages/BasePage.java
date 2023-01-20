package com.guru99.pages;

import commonLibs.implementation.ElementControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;
    protected ElementControl elementControl;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        elementControl = new ElementControl(driver);
        PageFactory.initElements(driver, this);
    }
}
