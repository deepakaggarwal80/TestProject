package com.company.listeners;

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
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentTestNGListener implements IReporter {
    
    private static final String OUTPUT_FOLDER = "test-output/";
    private static final String FILE_NAME = "Extent.html";
    
    private ExtentReports extentReports;

    public  void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        init();
        
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                
                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
                buildTestNodes(context.getPassedTests(), Status.PASS);
                
            }
        }
        
        for (String s : Reporter.getOutput()) {
            extentReports.setTestRunnerOutput(s);
        }
        
        extentReports.flush();
    }
    
    private  void init() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
        htmlReporter.config().setAutoCreateRelativePathMedia(true);
        htmlReporter.config().setDocumentTitle("ExtentReports - Created by TestNG Listener");
        htmlReporter.config().setReportName("ExtentReports - Created by TestNG Listener");
//        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setTheme(Theme.STANDARD);
        
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setReportUsesManualConfiguration(true);
    }
    
    private  void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest extentTestLogger;
        
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                extentTestLogger = extentReports.createTest(result.getMethod().getMethodName());
                
                for (String group : result.getMethod().getGroups())
                    extentTestLogger.assignCategory(group);

                if (result.getThrowable() != null) {
                    extentTestLogger.log(status, result.getThrowable());
                }
                else {
                    extentTestLogger.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }
                
                extentTestLogger.getModel().setStartTime(getTime(result.getStartMillis()));
                extentTestLogger.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
    }
    
    private  Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();      
    }
}