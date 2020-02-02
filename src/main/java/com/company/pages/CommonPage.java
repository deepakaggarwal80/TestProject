package com.company.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.company.base.VerificationLibrary;

public class CommonPage  extends VerificationLibrary{
	@FindBy(xpath="//a[text()='Sign in']")
	public WebElement signIn;
	public CommonPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
}
