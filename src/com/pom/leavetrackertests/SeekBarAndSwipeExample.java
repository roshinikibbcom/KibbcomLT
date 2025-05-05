	package com.pom.leavetrackertests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pom.leavetrackerpages.BasePage;
import com.pom.leavetrackerpages.HomePage;
import com.pom.leavetrackerpages.LoginPage;
import com.pom.leavetrackerpages.TimeLogPage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

//program execution was not successful - changed Duration.ofMillis(500) from 0->500

public class SeekBarAndSwipeExample {
	public static AppiumDriver driver;
    LoginPage loginPage;
    TimeLogPage timeLogPage;
    HomePage homePage;
    BasePage basepage;
	
	
	@BeforeClass
	
	public void openApp() throws MalformedURLException, InterruptedException
	{
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
	       homePage= new HomePage(driver);
	       basepage=new BasePage(driver);
	}
	
	@Test
	
	public void SeekBarHandle() throws InterruptedException
	{
		loginPage.enterEmail("roshini.setru@kibbcom.com");
        loginPage.enterPassword("Kibbcom@1");
        loginPage.clickLogin();
        Thread.sleep(6000);
        String partialDesc = "Neeraj";
		
		By elementSeekBar = AppiumBy.xpath("//*[contains(@content-desc,'"+ partialDesc +"')]");
		System.out.println("Using XPath: " + elementSeekBar.toString());
		scrollToElement(driver,elementSeekBar);
		
		Thread.sleep(3000);
		
		WebElement elseek = driver.findElement(AppiumBy.xpath("//*[contains(@content-desc, '"+ partialDesc +"')]"));
		System.out.println((elseek.getText()));
		elseek.click();
		Thread.sleep(3000);
		
		
	}
	
	public static void scrollToElement(AppiumDriver driver, By  by) //By - Mechanism used to locate elements within a document
	{
		//System.out.println("Using XPath: " + driver.findElement(by).toString());
		//System.out.println("Inside Scroll Method");
		int screenH= driver.manage().window().getSize().height;
		int screenW= driver.manage().window().getSize().width;
		
		//starting the swipe from the center of the screen, move according to the screen height and width
		int startX = screenW/2;
		int startY= (int) (screenH * 0.7); //it should be anything within 10 // Start swipe from lower part of screen
		int endY=(int) (screenH*0.2); // Swipe upwards
		
		 int maxScrolls = 5; // ✅ Prevents infinite loops
	        int scrollCount = 0;
		
		while ( scrollCount < maxScrolls)
		{
			if (!driver.findElements(by).isEmpty()) {
	            System.out.println("Element found before scrolling.");
	            break;  // Break the loop if the element is found
	        }
			
			System.out.println("Scrolling attempt: " + (scrollCount + 1));
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence scroll = new Sequence(finger, 1)
					.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, startY))
					.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startX, endY))
					.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			
			driver.perform(Arrays.asList(scroll));
			 // Log the scroll attempt for visibility
	        System.out.println("Scroll action performed...");
	        // Wait for the content to load after scroll
	        try {
	            Thread.sleep(1000);  // Optional wait to allow screen to update
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			scrollCount++;
			// Check if the element is found after scrolling
		    if (driver.findElements(by).isEmpty()) {
		        throw new RuntimeException("Element not found after scrolling!");
		    } else {
		        System.out.println("Element found after scrolling!");
		    }
		}
			
			
	}
	
	
	@AfterClass
	
	public void teardown()
	{
		driver.quit();
	}

} 
