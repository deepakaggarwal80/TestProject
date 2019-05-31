package com.company.testcase;

import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import org.testng.log4testng.Logger; //for test ng

import com.company.base.TestBase;
import com.company.listeners.TestListener;
import com.company.pages.HomePage;
import com.company.pages.LoginPage;

@Listeners(TestListener.class)
public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
//	Logger log = Logger.getLogger(LoginPageTest.class); // for testng
	Logger log = LogManager.getLogger();
	public LoginPageTest() {
		super();
	}
	
	@BeforeClass
	public void setUp() {
		try {
			initialization();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		loginPage = new LoginPage();
	}
	
//	@Test(priority=1, retryAnalyzer=com.company.util.RetryAnalyzer.class)
	@Test//(priority=1,enabled=true)
	public void loginPageTitleTest() {
		extentTestLogger.info("Extent Test Logger info");
		log.info("info");
//		extentTestLogger.warning("Extent Test Logger warning");
		log.warn("warn");
		log.fatal("fatal");
//		extentTestLogger.fail("Extent Test Logger fail");
//		log.error("error");
		String title=loginPage.validateHomePageTitle();
		Assert.assertEquals(title,"Google");
		extentTestLogger.pass("Extent Test Logger pass");
	}
	
	@Test//(priority=2,enabled=true)
	
	public void loginTest() {
		homePage=loginPage.login();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}
