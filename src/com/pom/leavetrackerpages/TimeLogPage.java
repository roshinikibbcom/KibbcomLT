package com.pom.leavetrackerpages;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class TimeLogPage extends BasePage{
	

    public TimeLogPage(AppiumDriver driver) {
		// TODO Auto-generated constructor stub
    	 super(driver);
	}

	public void openTimeLog() {
    	WebElement timeLog = (WebElement) driver.findElement(AppiumBy.xpath("//*[contains(@content-desc, 'Timelog')]"));
        singleTap(timeLog);
    }

    public void checkIn() {
    	WebElement checkInBtn = (WebElement) driver.findElement(AppiumBy.accessibilityId("Check in"));
        singleTap(checkInBtn);
    }

    public void checkOut() {
    	WebElement checkOutBtn = (WebElement) driver.findElement(AppiumBy.accessibilityId("Check out"));
        singleTap(checkOutBtn);
    }

    public void selectTask(String taskName) {
    	WebElement selectTask = (WebElement) driver.findElement(AppiumBy.accessibilityId("Select Your Task"));
        singleTap(selectTask);

        WebElement task = (WebElement) driver.findElement(AppiumBy.accessibilityId(taskName));
        singleTap(task);
    }

    public void enterDescription(String desc) {
    	WebElement descField = (WebElement) driver.findElement(AppiumBy.xpath("//android.widget.EditText"));
        singleTap(descField);
        descField.sendKeys(desc);
    }


}
