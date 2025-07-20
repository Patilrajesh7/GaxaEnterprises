package com.Erequisition.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Erequisition.qa.utilities.TestUtilities;

public class TestBase {

    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();
    public static Properties prop;

    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\main\\java\\com\\Erequisition\\qa\\config\\config.properties"
            );
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the current thread's WebDriver instance.
     */
    public static WebDriver getDriver() {
        return tdriver.get();
    }

    /**
     * Initializes WebDriver and sets it into ThreadLocal for parallel execution.
     */
    
    @BeforeMethod
    public void initialization() {
        try {
            System.out.println("Browser launch");

            WebDriver driver = null;

            if (prop.getProperty("browser_name").equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "D:\\Frameworks\\driver\\chromedriver.exe");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                options.setAcceptInsecureCerts(true);

                driver = new ChromeDriver(options);

            } else if (prop.getProperty("browser_name").equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
                System.out.println("Edge Browser is Opened");
            } else {
                throw new RuntimeException("Unsupported browser: " + prop.getProperty("browser_name"));
            }

            tdriver.set(driver);

            getDriver().get(prop.getProperty("url"));
            getDriver().manage().window().maximize();
            getDriver().manage().deleteAllCookies();
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtilities.IMPLICIT_WAIT));
            getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtilities.PAGE_LOAD_TIMEOUT));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Browser initialization failed: " + e.getMessage());
        }
    }


    /**
     * Takes a screenshot and saves it with the test method name and timestamp.
     */
    public void takeScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir")
            + "\\screenshots\\" + testName + "_" + timestamp + ".png";
        try {
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            System.out.println("Screenshot taken: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Automatically takes a screenshot on test failure and quits driver.
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getMethod().getMethodName());
        }

        if (getDriver() != null) {
            getDriver().quit();
            tdriver.remove();  // Clean up ThreadLocal to avoid memory leaks
        }
    }
}
