package com.pom.leavetrackerpages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboard;

public class LoginPage extends BasePage{
	protected WebDriverWait wait;
	
	 public LoginPage(AppiumDriver driver) {
	        super(driver);
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	         
	    }

	    public void enterEmail(String email) {
	    	WebElement emailField = (WebElement) driver.findElement(AppiumBy.xpath("//android.widget.ScrollView/android.widget.EditText[1]"));
	       // emailField.click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.ScrollView/android.widget.EditText[1]")));
	       // wait.until(ExpectedConditions.visibilityOfElementLocated((By) emailField));
	     emailField.click();
	     emailField.clear();
	       emailField.sendKeys(email);
	        ((HidesKeyboard) driver).hideKeyboard();
	    }

	    public void enterPassword(String password) {
	    	WebElement pwdField = (WebElement) driver.findElement(AppiumBy.xpath("//android.widget.ScrollView/android.widget.EditText[2]"));
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.ScrollView/android.widget.EditText[2]")));
	    	pwdField.click();
	    	pwdField.clear();
	        pwdField.sendKeys(password);
	        ((HidesKeyboard) driver).hideKeyboard();
	    }

	    public void clickLogin() {
	    	WebElement loginBtn = (WebElement) driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Login\"]"));
	        loginBtn.click();
	    }
	    
	    public void swipeCard()
		{
			
	    	WebElement swipeCardName = (WebElement) driver.findElement(AppiumBy.xpath("//*[contains(@content-desc, 'Achal')]"));
//			if (swipeCardName.isDisplayed())
//			{
//				//swipeCardName.click();
//			//swipeLeftAction(swipeCardName);
//			}
			//singleTap(swipeCardName);
	    	System.out.println(swipeCardName.getText());
	    	swipeLeftAction(swipeCardName);
		}

}
