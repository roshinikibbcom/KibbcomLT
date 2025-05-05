
package com.pom.leavetrackerpages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class HomePage extends BasePage{
	protected WebDriverWait wait;
	
	 public HomePage(AppiumDriver driver) {
	        super(driver);
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	         
	    }
	 //WebElement homeTab = (WebElement) driver.findElement(AppiumBy.xpath("//*[contains(@content-desc, 'Home')]"));
	   
	 public boolean isHomePageDisplayed() {
	    	WebElement homeTab = (WebElement) driver.findElement(AppiumBy.xpath("//*[contains(@content-desc, 'Home')]"));
	       try 	
	    	{
	    		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(8));
	    		wait.until(ExpectedConditions.visibilityOfElementLocated((By) homeTab));
	        	return true;
	    	}
	       catch (Exception e)
	       {
	    	   return false;
	       }
	    	
	    }
	    
	    public void swipeCard(String username)
		{
	    	
	    	WebElement swipeCardName = (WebElement) driver.findElement(AppiumBy.xpath("//*[contains(@content-desc, '"+username+"')]"));
	    	//System.out.println(swipeCardName.getText());
	    	System.out.println("Matched element: " + swipeCardName.getAttribute("content-desc"));
	    	System.out.println("Displayed: " + swipeCardName.isDisplayed());
	    	System.out.println("Enabled: " + swipeCardName.isEnabled());
	    	System.out.println("Selected: " + swipeCardName.isSelected());
	    	System.out.println("Text: " + swipeCardName.getText());
	    	System.out.println("Bounds: " + swipeCardName.getRect().toString());
	    	if (swipeCardName.isDisplayed())
	    	{
			
	    		swipeLeftAction(swipeCardName);

	    	}
		}
	    
	    public void userScroll(String username) throws InterruptedException
	    {
	    	try
	    	{
	    	
	    	By swipeCardName = AppiumBy.xpath("//*[contains(@content-desc, '" + username + "')]");
	    	System.out.println("Found user" + ((WebElement) swipeCardName).getText());
	    	UserScrollHandle(username);
	    	
	    	}catch (Exception e) {
	            System.out.println("User not found: " + username);
	            e.printStackTrace();
	    	}
	        		}
	    	
	    	
	    	public void handleScroll(String name) throws InterruptedException
		    {
	    		scrollAndClickByContentDesc(driver,name);
		    		    	
	    }

}

