package com.company.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.company.base.TestBase;
import com.company.util.TestUtil;

public class TestListener extends TestBase implements ITestListener{
//	public ExtentReports extentReports=new ExtentReports();
//	public ExtentTest extentTestLogger;
	
	private static final String OUTPUT_FOLDER = "test-output/";
    private static final String FILE_NAME = "Extent.html";
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
	

	public void onTestStart(ITestResult result) {
		System.out.println("on test method " +  getTestMethodName(result) + " start");
		extentTestLogger= extentReports.createTest(getTestMethodName(result));
	}

	public void onTestSuccess(ITestResult result) {
		 System.out.println("on test method " + getTestMethodName(result) + " success");

	}

	public void onTestFailure(ITestResult result) {
		 System.out.println("on test method " + getTestMethodName(result) + " failure");
		 extentReports.attachReporter(htmlReporter);
		 if (result.getStatus()==ITestResult.FAILURE) {
			 try {
				extentTestLogger.fail(result.getThrowable().getMessage()).addScreenCaptureFromPath(TestUtil.takeScreenShot());
				extentReports.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }

	}

	public void onTestSkipped(ITestResult result) {
		 System.out.println("on test method " + getTestMethodName(result) + " skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		 System.out.println("test failed but within success % " + getTestMethodName(result));

	}

	public void onStart(ITestContext context) {
		 System.out.println("on start of test " + context.getName());

	}

	public void onFinish(ITestContext context) {
		  System.out.println("on finish of test " + context.getName());

	}

	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}

}
