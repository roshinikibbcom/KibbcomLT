package com.pom.leavetrackertests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.pom.leavetrackerpages.HomePage;
import com.pom.leavetrackerpages.LoginPage;
import com.pom.leavetrackerpages.TimeLogPage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import utils.ExcelUtils;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class LoginTestMultipleCredentials {
	public static AppiumDriver driver;
    LoginPage loginPage;
    TimeLogPage timeLogPage;
    HomePage homePage;
    
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
        homePage = new HomePage(driver);
        
        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("./TestReport/"+timeStamp+"+AutomationReport.html");
        spark.config().setTheme(Theme.STANDARD);	
        //attach all these in the report by creating Object
        extent.attachReporter(spark);
        }
    
    @DataProvider(name="Logindata")
    public Object[][] getLogindata() throws IOException
    {
    	String filepath= System.getProperty("user.dir")+"/testdata/testdata.xlsx";
    	ExcelUtils.loadExcel(filepath, "Sheet1");
    	int rowCount= ExcelUtils.getRowCount();
    	Object[][] data= new Object[rowCount-1][2];
    	
    	for (int i=1;i < rowCount; i++)
    	{
    		data[i-1][0]=ExcelUtils.getCelldata(i, 0);
    		data[i-1][1]=ExcelUtils.getCelldata(i, 1);
    		    		
    	}	
    	
    	ExcelUtils.closeExcel();
    	return data;
    }

    @Test(dataProvider = "Logindata")
    public void testLoginFunction(String username, String password) throws InterruptedException {
    	logger = extent.createTest("LoginPage Test Case");
    	logger.info("Trying to Login");
//        loginPage.enterEmail("roshini.setru@kibbcom.com");
//        loginPage.enterPassword("Kibbcom@1");
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
             
               
        logger.info("Login Page is being displayed");
        loginPage.clickLogin();
        Thread.sleep(8000);
        boolean loginResult = homePage.isHomePageDisplayed();
        if (loginResult == true) {
        	logger.pass("Login success for: " + username);
        }else {
        	logger.fail("Login fail for: "+ username);
        }
               
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

}
