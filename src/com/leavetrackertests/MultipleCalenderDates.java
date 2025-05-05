package com.leavetrackertests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class MultipleCalenderDates {

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
		//Thread.sleep(2000);
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(2));
		  wait2.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.ScrollView/android.widget.EditText[2]")));
		
	
		WebElement pwdButton = driver.findElement(AppiumBy.xpath("//android.widget.ScrollView/android.widget.EditText[2]"));
		
	
		if (pwdButton.isDisplayed() && pwdButton.isEnabled()) {
			pwdButton.click(); // Click first if needed
			pwdButton.sendKeys("Kibbcom@1");
		} else {
		    System.out.println("Field not interactable!");
		}
		Thread.sleep(1000);
		((HidesKeyboard) driver).hideKeyboard();
		
		WebElement loginButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Login\"]"));
		loginButton.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//*[contains(@content-desc, 'Calendar')]")));
		//Thread.sleep(8000);
		
		WebElement calenderButton = driver.findElement(AppiumBy.xpath("//*[contains(@content-desc, 'Calendar')]"));
		//Thread.sleep(1000);
		 WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(4));
		  wait1.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//*[contains(@content-desc, 'Calendar')]")));
		
		SingleTap(driver,calenderButton); // called directly, since it is a static method
		/*Thread.sleep(2000);
		
		WebElement addButton = driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]"));
		SingleTap(driver,addButton); // called directly, since it is a static method
		Thread.sleep(2000);
		
		//android.widget.Button[@content-desc="Single Date"]
		WebElement sdateButton = driver.findElement(AppiumBy.xpath("//*[contains(@content-desc, 'Multiple')]"));
		sdateButton.click();
		
		//2025-03-26
		WebElement fromdate = driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]"));
		SingleTap(driver,fromdate);
		Thread.sleep(2000);
		//pickDate.click();
		//android.widget.Button[@content-desc="28, Friday, March 28, 2025"]
		WebElement selectFromDate = driver.findElement(AppiumBy.accessibilityId("15, Tuesday, April 15, 2025"));
		selectFromDate.click();
		Thread.sleep(2000);
		WebElement dateOK1 = driver.findElement(AppiumBy.accessibilityId("OK"));
		dateOK1.click();
		
		WebElement todate = driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]"));
		SingleTap(driver,todate);
		Thread.sleep(2000);
		WebElement selectToDate = driver.findElement(AppiumBy.accessibilityId("21, Monday, April 21, 2025"));
		selectToDate.click();
		
		WebElement dateOK = driver.findElement(AppiumBy.accessibilityId("OK"));
		dateOK.click();
		//Working from office
		WebElement selectDropdown = driver.findElement(AppiumBy.accessibilityId("Working from office"));
		selectDropdown.click();
		WebElement selectOption = driver.findElement(AppiumBy.accessibilityId("Working from office"));
		SingleTap(driver,selectOption);
		
		WebElement submit = driver.findElement(AppiumBy.accessibilityId("Submit"));
		submit.click();
		Thread.sleep(2000);*/
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

