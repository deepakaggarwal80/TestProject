package com.company.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
Locator Naming Convention
Text Box	txt	txtEmail , txtPassword
Button		btn	btnRegister , btnLogin
Table		tbl	tblBooks, tblProducts
Label		lbl	lblUserName, lblPassword
Image		img	imgProfile, imgCart
Checkbox	chk	chkMenu
Combo box	cbo	cboCountry
Radiobutton	rdo	radioGender
Listbox		lst	lstCountry
Dialog		dlg	dlgNewDialog
Link		lnk	lnkSignin
*/


/*

Java follows camelcase syntax for naming the class, interface, method and variable.

class name		should start with uppercase letter and be a noun e.g. String, Color, Button, System, Thread etc.
interface name	should start with uppercase letter and be an adjective e.g. Runnable, Remote, ActionListener etc.
method name		should start with lowercase letter and be a verb e.g. actionPerformed(), main(), print(), println() etc.
variable name	should start with lowercase letter e.g. firstName, orderNumber etc.
package name	should be in lowercase letter e.g. java, lang, sql, util etc.
constants name	should be in uppercase letter. e.g. RED, YELLOW, MAX_PRIORITY etc.

*/


public class HomePage extends CommonPage{
	//PageFactory -OR
	@FindBy(name="username")
//	@CacheLookup
	public WebElement username;
//	
//	@FindBy(name="password")
//	@CacheLookup
//	WebElement password;
	
	@FindBy(xpath="(//input[@value='Google Search'])[2]")
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
		verifyElementIsDisplayed(submit);
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
		jsClick(submit);
		jsClick(btn_signIn);
//		extentTestLogger.pass("result page opened");
//		verifyCSSValue(submit,"b","c");
		return new SignInPage();
	}
}
