package com.company.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.company.base.VerificationLibrary;
import com.company.listeners.TestListener;
import com.company.pages.HomePage;
import com.company.pages.SignInPage;

@Listeners(TestListener.class)
public class LoginPageTest1 extends VerificationLibrary{
	HomePage loginPage;
	SignInPage homePage;
	@Test(description = "Login Page Title Test")
	public void loginPageTitleTest() {
		HomePage loginPage=new HomePage();
		testLog("Deepak Aggarwal");
		loginPage.validateHomePageTitle();
		verifyPageTitle("Google");
		verifyElementIsDisplayed(loginPage.submit);
	}

	@AfterClass
	public void tearDown() {
		closeBrowser();
	}

}
