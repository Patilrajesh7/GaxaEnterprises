package com.Erequisition.qa.testcases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Erequisition.qa.base.TestBase;
import com.Erequisition.qa.pages.HomePage;
import com.Erequisition.qa.pages.LoginPage;

public class LoginTest extends TestBase {
    LoginPage loginpage;
    HomePage homepage;

    public LoginTest() {
        super();
    }

    @BeforeMethod
    public void setup() {
        initialization();
        loginpage = new LoginPage();
    }

    @Test(priority = 1)
    public void loginpagetitletes() {
        String title = loginpage.ValidateLoginPageTitle();
        Assert.assertEquals(title, "Swag Labs");

        
        TakesScreenshot screenshot = (TakesScreenshot) TestBase.getDriver();		
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source, new File("D:\\Frameworks\\IP Projects\\Erequisition\\screenshots\\screenshotFortitleVerification.png"));     
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot is captured");
    }

    @Test(priority = 2)
    public void loginpageLogotest() {
        boolean logo = loginpage.validatedLogo();
        Assert.assertTrue(logo);
    }

    @Test(priority = 3)
    public void logintest() {
        homepage = loginpage.loginFunction(prop.getProperty("username"), prop.getProperty("password"));
        takeScreenshot("logintest"); 
    }

    @AfterMethod
    public void teardown() {
       
        if (TestBase.getDriver() != null) {
            TestBase.getDriver().quit();
            tdriver.remove(); 
        }
    }
}
