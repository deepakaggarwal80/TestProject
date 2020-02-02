package com.company.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.company.base.VerificationLibrary;

public class HomePage extends CommonPage{
	//PageFactory -OR
	@FindBy(name="username")
//	@CacheLookup
	public WebElement username;
//	
//	@FindBy(name="password")
//	@CacheLookup
//	WebElement password;
	
	@FindBy(xpath="//input[@name='btnK']")
//	@CacheLookup
	public WebElement submit;
	
	@FindBy(xpath="//input[@name='q']")
//	@CacheLookup
	public WebElement googleSearchBox;
	
	
//  initializing the page object	
	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}
//	Action
	public void validateHomePageTitle() {
		verifyPageTitle("Google");
		verifyElementPresentOnPage(submit);
	}
	
	public SignInPage login() {
//		username.sendKeys("Deepak");
//		password.sendKeys("aggarwal");
//		submit.click();
//		extentTestLogger.info("Entering text in search box");
//		googleSearchBox.sendKeys("Hello");
		sendText(googleSearchBox, "Hello");
//		extentTestLogger.info("Clicking button");
//		pressKey(submit, Keys.ENTER);
		click(submit);
		click(signIn);
//		extentTestLogger.pass("result page opened");
//		verifyCSSValue(submit,"b","c");
		return new SignInPage();
	}
}
