package com.automationninjatutorial.qa.listener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automationninjatutorial.qa.utilities.ExtentReport;
import com.automationninjatutorial.qa.utilities.Utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ListenerClass implements ITestListener {
	
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReport.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+" started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName()+" got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//System.out.println("Screenshot taken.");
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		/*File srcScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";
		try {
			FileHandler.copy(srcScreenShot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getTestName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+" got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+" got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
		String pathOfExtenReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtenReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
