package com.pom.leavetrackertests;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

//present at timestamp 2:00:00 for reference  //commoms.io - added during ItestListeneres in pom.xml
public class FailedScreenshotutility implements ITestListener {

	public String destDir;
	public DateFormat dateFormat;
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		
		System.out.println("****Testcase is being started*******");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		
		System.out.println(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		
		System.out.println(result);
		TakeScreenshotfromMobile(result,"Fail");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}  //implements - methods of iTestListner to be implemented in ths class
//be default it is not showing me to add the methods
	public void TakeScreenshotfromMobile(ITestResult result, String status)
	{
		destDir= "./Screenshot";
		//If I want to define my current active driver screenshot i have to invoke that driver object over here
		//Commented below line as there is some error in this - sort out and execute later - error rectified
		File srcFile= ((TakesScreenshot)LoginTestPage.driver).getScreenshotAs(OutputType.FILE);
		
		dateFormat =new SimpleDateFormat("dd-MM-YYYY hh_mm_ss");
		new File(destDir).mkdir();
		
		String destFile=dateFormat.format(new Date()) +".png";
		
		try
		{
			FileUtils.copyFile(srcFile, new File(destDir +"/" +destFile)); 
		}
		catch(Exception e)
		{
			
		}
	}
	
}


