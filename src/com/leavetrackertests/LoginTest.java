package com.leavetrackertests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class LoginTest {
	public static AppiumDriver driver;
	@BeforeClass
	
	public void openApp() throws MalformedURLException, InterruptedException
	{
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setPlatformVersion("12");
		options.setDeviceName("94BAY0LZYZ");
		options.setAppPackage("com.kibbcom.leave_tracker_app");
		options.setAppActivity("com.kibbcom.leave_tracker_app.MainActivity");
		options.autoGrantPermissions();
		
		URL url = new URL("http://127.0.0.1:4723");
		driver= new AndroidDriver(url,options);
		Thread.sleep(5000);
	}
	
	@Test
	
	public void testLoginFunction() throws InterruptedException
	{
		WebElement emailButton = driver.findElement(AppiumBy.xpath("//android.widget.ScrollView/android.widget.EditText[1]"));
		if (emailButton.isDisplayed() && emailButton.isEnabled()) {
			emailButton.click(); // Click first if needed
			emailButton.sendKeys("roshini.setru@kibbcom.com");
		} else {
		    System.out.println("Field not interactable!");
		}
		((HidesKeyboard) driver).hideKeyboard();
		Thread.sleep(2000);
		
		WebElement pwdButton = driver.findElement(AppiumBy.xpath("//android.widget.ScrollView/android.widget.EditText[2]"));
		
		if (pwdButton.isDisplayed() && pwdButton.isEnabled()) {
			pwdButton.click(); // Click first if needed
			pwdButton.sendKeys("Kibbcom@1");
		} else {
		    System.out.println("Field not interactable!");
		}
		
           		
		Thread.sleep(2000);
		((HidesKeyboard) driver).hideKeyboard();
		
		////android.widget.Button[@content-desc="Login"]
		WebElement loginButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Login\"]"));
		loginButton.click();
		Thread.sleep(10000);
		/*
		//android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View
		WebElement HMenu = driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View"));
		HMenu.click();
		Thread.sleep(3000);*/	

		/*WebElement HMenu = driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View"));
				
		Thread.sleep(1000);
		SingleTap(driver,HMenu); // called directly, since it is a static method
		Thread.sleep(2000);*/
		
		WebElement timeLog = driver.findElement(AppiumBy.xpath("//*[contains(@content-desc, 'Timelog')]"));
			Thread.sleep(1000);
		SingleTap(driver,timeLog); // called directly, since it is a static method
		Thread.sleep(3000);
		
		//android.view.View[@content-desc="Check in"]
		
		WebElement checkIn = driver.findElement(AppiumBy.accessibilityId("Check in"));
		SingleTap(driver,checkIn);
		Thread.sleep(3000);
		
		WebElement checkOut = driver.findElement(AppiumBy.accessibilityId("Check out"));
		SingleTap(driver,checkOut);
		Thread.sleep(3000);
		
		
		WebElement selectTask = driver.findElement(AppiumBy.accessibilityId("Select Your Task"));
		SingleTap(driver,selectTask);
		Thread.sleep(3000);
		
		WebElement chooseTask = driver.findElement(AppiumBy.accessibilityId("Mobile app"));
		SingleTap(driver,chooseTask);
		Thread.sleep(3000);
		
		//android.widget.EditText
		WebElement enterDesc = driver.findElement(AppiumBy.xpath("//android.widget.EditText"));
		SingleTap(driver,enterDesc);
		enterDesc.sendKeys("Automate");
		Thread.sleep(4000);
		
		WebElement checkOut1 = driver.findElement(AppiumBy.accessibilityId("Check out"));
		SingleTap(driver,checkOut1);
		Thread.sleep(3000);
		
	}
	
	public static void SingleTap(AppiumDriver driver,WebElement element)
	{
		//this will take the WebElement from the @Test method and take the x and y cordinate and perform the appropriate action
		int x = element.getLocation().getX() + element.getSize().getWidth()/2; //get the(x coordinate) size of the element / 2 - to get the mid size of the element
		int y = element.getLocation().getY() + element.getSize().getHeight()/2;
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1)
				.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y)) //move our finger on to the appropraite coordinate
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // left click - on mouse (LEFT) means here
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Arrays.asList(tap));
	}
	
	@AfterClass
	
	public void teardown()
	{
		driver.quit();
	}


}
