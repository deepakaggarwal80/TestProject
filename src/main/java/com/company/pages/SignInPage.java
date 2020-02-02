package com.company.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends CommonPage{
	@FindBy(xpath="//input[@type='email']")
	public WebElement signIn;
	
	public SignInPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public void enterUserName() {
		sendText(signIn, "deepakaggarwal@gmail.com");
	}
}
