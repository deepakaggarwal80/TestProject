package com.company.listeners;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportListener implements IReporter{
	private ExtentReports reports;
	
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		ExtentHtmlReporter htmlreporter=new ExtentHtmlReporter(outputDirectory+File.separator+"ExtentReport.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlreporter);
		
		for (ISuite suite:suites) {
			Map<String,ISuiteResult> result =suite.getResults();
			
			for (ISuiteResult r:result.values()) {
				ITestContext context=r.getTestContext();
				
				buildTestNodes(context.getPassedTests(),Status.PASS);
				buildTestNodes(context.getFailedTests(),Status.FAIL);
				buildTestNodes(context.getSkippedTests(),Status.SKIP);
				
			}
		}
		reports.flush();
	}
	private  void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test;
        
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = reports.createTest(result.getMethod().getMethodName());
                
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                }
                else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }
                
                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
    }
    
    private  Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();      
    }

}
