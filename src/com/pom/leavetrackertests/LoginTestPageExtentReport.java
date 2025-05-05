package com.pom.leavetrackertests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
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
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class LoginTestPageExtentReport {
	public static AppiumDriver driver;
    LoginPage loginPage;
    TimeLogPage timeLogPage;
    
    public ExtentSparkReporter spark;
    public ExtentReports extent;
    public ExtentTest logger;

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
        
        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("./TestReport/"+timeStamp+"+AutomationReport.html");
        spark.config().setTheme(Theme.STANDARD);	
        //attach all these in the report by creating Object
        extent.attachReporter(spark);
        }

    @Test
    public void testLoginFunction() throws InterruptedException {
    	logger = extent.createTest("LoginPage Test Case");
    	logger.info("Login Page is displayed");
        loginPage.enterEmail("roshini.setru@kibbcom.com");
        loginPage.enterPassword("Kibbcom@1");
        loginPage.clickLogin();
        Thread.sleep(8000);

        timeLogPage.openTimeLog();
        Thread.sleep(2000);
        timeLogPage.checkIn();
        Thread.sleep(3000);
        timeLogPage.checkOut();
        Thread.sleep(3000);
        timeLogPage.selectTask("Mobile app");
        Thread.sleep(3000);
        timeLogPage.enterDescription("Automate");
        Thread.sleep(3000);
        timeLogPage.checkOut();
        Thread.sleep(3000);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

}
