package com.company.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.company.base.TestBase;

public class LoginPage extends TestBase{
	//PageFactory -OR
	@FindBy(name="username")
	@CacheLookup
	WebElement username;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement password;
	
	@FindBy(id="submit")
	@CacheLookup
	WebElement submit;
	
//  initializing the page object	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
//	Action
	public String validateHomePageTitle() {
		return driver.getTitle();
	}
	
	public HomePage login() {
		username.sendKeys("Deepak");
		password.sendKeys("aggarwal");
		submit.click();
		return new HomePage();
	}
}
