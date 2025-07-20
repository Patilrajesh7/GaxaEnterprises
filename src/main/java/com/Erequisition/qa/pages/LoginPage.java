package com.Erequisition.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Erequisition.qa.base.TestBase;

public class LoginPage extends TestBase {

    // Object repository
    @FindBy(name = "user-name")
    WebElement username;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(xpath = "//div[@class='login_logo']")
    WebElement logo;

    // Constructor
    public LoginPage() {
        PageFactory.initElements(getDriver(), this); 
    }

    public String ValidateLoginPageTitle() {
        return getDriver().getTitle(); 
    }

    public boolean validatedLogo() {
        return logo.isDisplayed();
    }

    public HomePage loginFunction(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
        return new HomePage();
    }
}
