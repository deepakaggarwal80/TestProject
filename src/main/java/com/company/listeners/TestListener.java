package com.company.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.company.base.TestBase;

public class TestListener extends TestBase implements ITestListener{

	
	private static final String OUTPUT_FOLDER = "test-output/";
    private static final String FILE_NAME = "Extent.html";
//	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
//	ExtentReports extentReports=new ExtentReports();
//	ExtentTest extentTestLogger;

	private static ExtentReports extent = ExtentManager.createInstance(OUTPUT_FOLDER + FILE_NAME);
	public static ThreadLocal<ExtentTest> ETLogger= new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(getTestMethodName(result));
		ETLogger.set(test);
		setETLog(ETLogger);
		softAssert.set(new SoftAssert() );
		ETLogger.get().info("Starting test method " +  getTestMethodName(result));
	}

	public void onTestSuccess(ITestResult result) {
		softAssert.get().assertAll();

	}

	public void onTestFailure(ITestResult result) {
//		 System.out.println("on test method " + getTestMethodName(result) + " failure");
//		 if (result.getStatus()==ITestResult.FAILURE) {
//			 try {
//				 ETLogger.get().fail(result.getThrowable().getMessage()).addScreenCaptureFromPath(TestUtil.takeScreenShot());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		 }
//		Markup m =MarkupHelper.createLabel("Test case Failed: "+ getTestMethodName(result), ExtentColor.RED);
//		 ETLogger.get().fail(m);

	}

	public void onTestSkipped(ITestResult result) {
//		 System.out.println("on test method " + getTestMethodName(result) + " skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		 System.out.println("test failed but within success % " + getTestMethodName(result));

	}

	public void onStart(ITestContext context) {
//		 System.out.println("on start of test " + context.getName());

	}

	public void onFinish(ITestContext context) {
//		  System.out.println("on finish of test " + context.getName());
		  if (extent !=null)
			  extent.flush();
	}

	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}

}
