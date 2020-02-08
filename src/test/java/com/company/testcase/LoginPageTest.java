package com.company.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.company.base.VerificationLibrary;
import com.company.pages.HomePage;
import com.company.pages.SignInPage;

@Listeners(com.company.listeners.TestListener.class)
public class LoginPageTest extends VerificationLibrary{
	
//		@Test(priority=1,description = "Login Page Title Test", retryAnalyzer=com.company.util.RetryAnalyzer.class)
//		@Test(description = "Login Page Title Test")
	public void loginPageTitleTest() {
		HomePage loginPage=new HomePage();
		testLog("Deepak Aggarwal");
		loginPage.validateHomePageTitle();
		verifyPageTitle("Google");
		verifyElementIsDisplayed(loginPage.submit);
	}

	@Test(description = "Login Page Test")

	public void loginTest(){
		HomePage loginPage=new HomePage();
		SignInPage signInPage=new SignInPage();
//		loginPage = new LoginPage();
		//		homePage=loginPage.login();
		loginPage.login();
		signInPage.enterUserName();
		openNewTab();
		
	}



	@AfterClass
	public void tearDown() {
		closeBrowser();
	}

}
