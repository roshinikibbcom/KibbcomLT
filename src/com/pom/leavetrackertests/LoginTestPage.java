package com.pom.leavetrackertests;

import com.pom.leavetrackerpages.BasePage;
import com.pom.leavetrackerpages.HomePage;
import com.pom.leavetrackerpages.LoginPage;
import com.pom.leavetrackerpages.TimeLogPage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners({FailedScreenshotutility.class})
public class LoginTestPage {
	public static AppiumDriver driver;
    LoginPage loginPage;
    TimeLogPage timeLogPage;
    BasePage basePage;
    HomePage homePage;

    @BeforeClass
    public void openApp() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("12")
                .setDeviceName("94BAY0LZYZ")
                .setAppPackage("com.kibbcom.leave_tracker_app")
                .setAppActivity("com.kibbcom.leave_tracker_app.MainActivity")
                .autoGrantPermissions();

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        Thread.sleep(5000);

        loginPage = new LoginPage(driver);
        timeLogPage = new TimeLogPage(driver);
        homePage = new HomePage(driver);
        basePage =new BasePage(driver);
    }

    @Test
    public void testLoginFunction() throws InterruptedException {
        loginPage.enterEmail("roshini.setru@kibbcom.com");
        loginPage.enterPassword("Kibbcom@1");
        loginPage.clickLogin();
        
        Thread.sleep(8000);

      homePage.swipeCard("Aditya");
    }

    @AfterClass
    
    public void teardown() {
        driver.quit();
    }

}
