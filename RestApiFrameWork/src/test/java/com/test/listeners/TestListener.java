
package com.test.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



public class TestListener implements ITestListener {
	
	 public static ExtentReports extent;
	 public static ExtentTest logger;
	 public static GenerateReports report=GenerateReports.getInstance();



	public void onStart(ITestContext context) {
		System.out.println("Inside OnStart listener method");
		report.startExtentReport();
	}

	public void onFinish(ITestContext context) {
		System.out.println(("Inside OnFinish listener method"));
		report.endReport();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("Inside OnTestStart listener method"));
		report.startSingleTestReport(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Inside OnTestSuccess listener method");
		report.logTestpassed();
	}

	public void onTestFailure(ITestResult result) {
		String failedTest = result.getName();
		String screenshotDir = "./screenshots/";
		System.out.println("Inside OnTestFailure listener method");
		report.logTestFailed();
		try {
			report.attachScreeshot(screenshotDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Inside OnTestSkipped listener method");
		report.logTestSkipped();
	}

//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
//	}

}