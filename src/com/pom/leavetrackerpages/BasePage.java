package com.pom.leavetrackerpages;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class BasePage {
	protected AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void singleTap(WebElement element) {
        int x = element.getLocation().getX() + element.getSize().getWidth() / 2;
        int y = element.getLocation().getY() + element.getSize().getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(tap));
    }
    

   public void swipeLeftAction(WebElement element) {
       
        //to get the center coordinates
        int centerX = element.getLocation().getX() + element.getSize().getWidth() / 2;
        int centerY = element.getLocation().getY() + element.getSize().getHeight() / 2;
        // Debugging: Print the values of centerX and centerY
        
     
        System.out.println("centerX: " + centerX);
        System.out.println("centerY: " + centerY);
        System.out.println("Element text: " + element.getText());
        System.out.println("Element content-desc: " + element.getDomAttribute("content-desc"));
        
        
        // Define swipe points (leftwards swipe)
        int startX = centerX + 200;
        int endX = centerX - 200;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
        		.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, centerY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, centerY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
   }
   public void UserScrollHandle(String partialDesc) throws InterruptedException
	{
		
	   By elementSeekBar = AppiumBy.xpath("//*[contains(@content-desc, '" + partialDesc + "')]");
	   //System.out.println("Element content-desc: " + ((WebElement) elementSeekBar).getDomAttribute("content-desc"));
		scrollToElement(driver,elementSeekBar);
		
				
		Thread.sleep(3000);
	
	}
	
	public static void scrollToElement(AppiumDriver driver, By by) //By - Mechanism used to locate elements within a document
	{
		int screenH= driver.manage().window().getSize().height;
		int screenW= driver.manage().window().getSize().width;
		
		//starting the swipe from the center of the screen, move according to the screen height and width
		int startX = screenW/2;
		int startY= (int) (screenH * 0.7); //it should be anything within 10 // Start swipe from lower part of screen
		int endY=(int) (screenH*0.2); // Swipe upwards
		
		 int maxScrolls = 5; // ✅ Prevents infinite loops
	        int scrollCount = 0;
		
		while (driver.findElements(by).isEmpty() && scrollCount < maxScrolls)
		{
			
			System.out.println("Scrolling attempt: " + (scrollCount + 1));
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence scroll = new Sequence(finger, 1)
					.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), startX, startY))
					.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), startX, endY))
					.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			
			driver.perform(Arrays.asList(scroll));
			scrollCount++;
		}
			
			 if (driver.findElements(by).isEmpty()) {
		            throw new RuntimeException("Element not found after scrolling!");
		        }
		
	}
	public WebElement scrollToElementByContentDesc(AppiumDriver driver,String partialDesc) {
	    int attempts = 0;
	    int maxScrolls=5;
	    while (attempts < maxScrolls) {
	        try {
	            WebElement element = driver.findElement(
	                AppiumBy.xpath("//*[contains(@content-desc, '" + partialDesc + "')]")
	            );
	            if (element.isDisplayed()) {
	                System.out.println("Element found: " + element.getAttribute("content-desc"));
	                return element;
	            }
	        } catch (NoSuchElementException e) {
	            // Element not found yet — scroll and retry
	        }

	        // Perform swipe up to scroll down
	     /*   Dimension size = driver.manage().window().getSize();
	        int startX = size.getWidth() / 2;
	        int startY = (int) (size.getHeight() * 0.7);
	        int endY = (int) (size.getHeight() * 0.2);*/
	        
	        int screenH= driver.manage().window().getSize().height;
			int screenW= driver.manage().window().getSize().width;
			
			int startX = screenW/2;
			int startY= (int) (screenH * 0.7); //it should be anything within 10 // Start swipe from lower part of screen
			int endY=(int) (screenH*0.2); // Swipe upwards
	       
	        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        Sequence scroll = new Sequence(finger, 1)
	            .addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, startY))
	            .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	            .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY))
	            .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	        driver.perform(Arrays.asList(scroll));
	        attempts++;
	        
	    }

	    throw new NoSuchElementException("Element with content-desc containing '" + partialDesc + "' not found after scrolling.");
	}
	
	public void scrollAndClickByContentDesc(AppiumDriver driver, String partialDesc) {
	    By targetLocator = AppiumBy.xpath("//*[contains(@content-desc, '" + partialDesc + "')]");
	    int maxSwipes = 7;
	    int swipeCount = 0;

	    Dimension size = driver.manage().window().getSize();
	    int startX = size.getWidth() / 2;
	    int startY = (int) (size.getHeight() * 0.7);
	    int endY = (int) (size.getHeight() * 0.3);

	    while (driver.findElements(targetLocator).isEmpty() && swipeCount < maxSwipes) {
	        System.out.println("Swipe #" + (swipeCount + 1));
	        
	        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        Sequence swipe = new Sequence(finger, 1)
	            .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
	            .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	            .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY))
	            .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	        driver.perform(Collections.singletonList(swipe));
	        swipeCount++;

	        try {
	            Thread.sleep(800); // allow UI to stabilize
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    if (!driver.findElements(targetLocator).isEmpty()) {
	        WebElement element = driver.findElement(targetLocator);
	        System.out.println("Found element: " + element.getAttribute("content-desc"));
	        element.click();
	    } else {
	        throw new NoSuchElementException("Element with content-desc containing '" + partialDesc + "' not found after " + maxSwipes + " swipes.");
	    }
	}
}
