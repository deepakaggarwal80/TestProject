package com.company.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports createInstance(String filename) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filename);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Automation Report");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("user", "Automation");

		return extent;

	}
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}