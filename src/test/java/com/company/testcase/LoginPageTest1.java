package com.company.testcase;

import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//import org.testng.log4testng.Logger; //for test ng

import com.company.base.TestBase;
import com.company.listeners.TestListener;
import com.company.pages.HomePage;
import com.company.pages.LoginPage;

@Listeners(TestListener.class)
public class LoginPageTest1 extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
//	Logger log = Logger.getLogger(LoginPageTest.class); // for testng
	Logger log = LogManager.getLogger();
	public LoginPageTest1() {
		super();
	}
	
	@BeforeClass
	public void setUp() {
		try {
			initialization();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	

	@Test//(priority=3,enabled=true)
	public void testMethod1() {
		Assert.assertEquals(true, true);
		extentTestLogger.pass("Extent Test Logger pass");
	}
	@Test//(priority=4,enabled=true)
	public void testMethod2() {
		Assert.assertEquals(true, false);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}
