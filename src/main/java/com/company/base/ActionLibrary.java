package com.company.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.company.util.TestUtil;

public class ActionLibrary extends TestBase{


	private static String parentWindowHandle;

	public void checkCheckBox(WebElement webElement) {
		try {
			if (!webElement.isSelected()) {
				webElement.click();
				testLog(true, "Checked checkbox. Locator:  " + getLocatorValue(webElement));
			}
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}



	public void checkCheckBox(WebElement webElement, int index) {
		try {
			this.highlightElement(webElement);
			if (!(getIndexedWebElement(webElement, index)).isSelected()) {
				(getIndexedWebElement(webElement, index)).click();
				testLog(true,"Checked the checkbox  with index: "+ index + ".  Locator: "  + getLocatorValue(webElement));
			}

		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void clearAndSendKey(WebElement webElement, String data) {
		clearTextBoxValue(webElement);
		sendText(webElement,data);
	}

	public void clearCookies() {
		try {
			getDriver().manage().deleteAllCookies();
			testLog(true, "Cleared cookies");
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}
	public void clearTextBoxValue(WebElement webElement) {
		try {
			this.highlightElement(webElement);
			webElement.clear();
			testLog(true, "Cleared Text box. Locator: "+ getLocatorValue(webElement));
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void clearTextBoxValue(WebElement webElement, int index) {
		try {
			this.highlightElement(webElement);
			(getIndexedWebElement(webElement, index)).clear();
			testLog(true, "Cleared Text box with index" + index + ". Locator: "+ getLocatorValue(webElement));
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void click(WebElement webElement) {
		try {
			testLog(true,"clicking on locator: " + getLocatorValue(webElement));
			webElement.click();

		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}
	}

	public void click(WebElement webElement, int index) {
		try {
			testLog(true,"clicking on locator with index: "+ index + ". Locator: " + getLocatorValue(webElement));
			getIndexedWebElement(webElement, index).click();
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}
	}

	public void clickBrowserButtons(String buttonName) {
		try {
			switch (buttonName) {
			case "back" :

				getDriver().navigate().back();
				testLog(true, "Clicked: 'back' button");
				break;
			case "forward" :
				getDriver().navigate().forward();
				testLog(true, "Clicked: 'forward' button");
			case "refresh" :
				try {
					getDriver().navigate().refresh();
					Alert alert = getDriver().switchTo().alert();
					alert.accept();
				} catch (NoAlertPresentException e) {

				}
				testLog(true, "Clicked: 'refresh' button");
			}
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void clickIfExists(WebElement webElement) {
		try {
			if (getElementCount(webElement) > 0) {
				click(webElement);
				testLog(true, "clicked locator : " + getLocatorValue(webElement));
			}
		} catch (Exception e) {
			testLog(true, e.getStackTrace().toString());
		}

	}

	public void clickOnAlert() {
		try {
			(new WebDriverWait(getDriver(), TestUtil.EXPLICIT_WAIT))
			.until(ExpectedConditions.alertIsPresent()).accept();
			testLog(true, "Clicked on alert box. ");
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void closeActiveTab() {
		try {
			testLog("closing Active Tab");
			String select = Keys.chord(new CharSequence[]{Keys.CONTROL, "w"});
			getDriver().switchTo().activeElement().sendKeys(new CharSequence[]{select});
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void closePopupWindow() {
		try {
			String parent = getDriver().getWindowHandle();
			Set<String> pops = getDriver().getWindowHandles();
			Iterator<String> it = pops.iterator();

			while (it.hasNext()) {
				String popupHandle = ((String) it.next()).toString();
				if (!popupHandle.contains(parent)) {
					getDriver().switchTo().window(popupHandle);
					testLog("Closed Popup Title: " + getDriver().switchTo().window(popupHandle).getTitle());
					getDriver().close();
				}
			}
			getDriver().switchTo().window(parent);
			testLog(true, "closePopupWindow: Closed all popups successfully");
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void closeWindow() {
		try {
			String handle = getDriver().getWindowHandle();
			testLog("Closing window. Title: " + getDriver().switchTo().window(handle).getTitle());
			getDriver().switchTo().window(handle).close();
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void createCookie(String CookieName, String CookieValue) {
		Cookie cookie = new Cookie(CookieName, CookieValue);

		try {
			getDriver().manage().addCookie(cookie);
			testLog("Sucessfully added cookie " + CookieName + " to " + CookieValue);
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void doubleClick(WebElement webElement) {
		try {
			testLog(true,"clicking on locator : " + getLocatorValue(webElement));
			Actions action = new Actions(getDriver());
			action.doubleClick(webElement).perform();;
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void focusIframe() {
		try {
			WebElement iFrameElement = getDriver().findElement(By.tagName("iframe"));
			getDriver().switchTo().frame(iFrameElement);
			testLog(true, "Changing focus to IFrame.");
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void focusIframe(int index) {
		try {
			List<WebElement> frames = getDriver().findElements(By.tagName("iframe"));
			getDriver().switchTo().frame(index);
			int frameCount = frames.size() + 1;
			testLog(true,"Changing focus to " + index + "  iFrame Out of  " + frameCount + " Iframes");
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void focusIframe(WebElement webElement) {
		try {
			if (webElement == null) {
				testLog(false,"focusIframe: Failed to change focus to iframe");
			} else {
				this.highlightElement(webElement);
				getDriver().switchTo().frame(webElement);
				testLog(true,"Changing focus to IFrame");
			}
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void focusMainWindow() {
		try {
			getDriver().switchTo().window(parentWindowHandle);
			testLog("Changed focus to the Main browser window");
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void focusNewWindow() {
		try {
			parentWindowHandle = getDriver().getWindowHandle();
			if (getDriver().getWindowHandles().size() > 1) {
				Iterator<String> iterator = getDriver().getWindowHandles().iterator();
				while(iterator.hasNext()){
					String childWindow=iterator.next();
					// Compare whether the main windows is not equal to child window.
					if(!parentWindowHandle.equals(childWindow)){
						getDriver().switchTo().window(childWindow);
						testLog("Changed focus to a New Window");
					}
				}

			} else {
				testLog("No new window present.");
			}
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}
	public void focusOutOfIFrame() {
		try {
			getDriver().switchTo().defaultContent();
			testLog(true, "Changing focus out of iFrame.");
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public List<String> getAllOptions(WebElement webElement) {
		List<String> options = new ArrayList<String>();
		Iterator<WebElement> iterator = (new Select(webElement)).getOptions().iterator();

		while (iterator.hasNext()) {
			WebElement option = iterator.next();
			if (option.getAttribute("value") != "") {
				options.add(option.getText());
			}
		}

		return options;
	}

	public int getBrowserWindowCount() {
		int count = getDriver().getWindowHandles().size();
		testLog(count + ", browser windows/tabs are open");
		return count;
	}

	public String getCSSValue(WebElement webElement, String cssProp) {
		String value = "";

		try {
			value = webElement.getCssValue(cssProp).trim();
			testLog(true,"Retrieved CSS value: " + value + " from the " + cssProp + " Property");
			return value;
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
			return value;
		}
	}

	public String getCurrentURL() {
		try {
			String url = getDriver().getCurrentUrl();
			testLog(true, "getCurrentURL. URL: " + url);
			return url;
		} catch (Exception e) {
			testLog(false, e.getStackTrace().toString());
			return null;
		}
	}


	public int getDropDownOptionsCount(WebElement webElement) {
		int numberOfDropdownOptions = 0;
		try {
			Select ddBox = new Select(webElement);
			numberOfDropdownOptions = ddBox.getOptions().size();
			testLog(true,"Number of Options available in the Drop Down :  " + numberOfDropdownOptions);
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

		return numberOfDropdownOptions;
	}

	public String getElementAttribute(WebElement webElement, int index, String AttributeName) {
		String value = null;

		try {
			value = ((WebElement) getIndexedWebElement(webElement, index)).getAttribute(AttributeName).trim();
			testLog(true, "getElementAttribute: Retrieved the value: " + value + " from the " + AttributeName);
			return value;
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}
		return value;
	}


	public String getElementAttribute(WebElement webElement, String AttributeName) {
		String value = null;

		try {
			value = webElement.getAttribute(AttributeName);
			testLog(true, "getElementAttribute: Retrieved the value: " + value + " from the " + AttributeName);
			return value;
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}
		return value;
	}

	public int getElementCount(WebElement WebElement) {
		return getElements(WebElement).size();
	}

	public List<WebElement> getElements(WebElement webElement) {
		List<WebElement> elementList = getDriver().findElements((By) webElement);
		testLog(true, "getelementCount, Element count:  " + " = " + Integer.toString(elementList.size()));
		return elementList;
	}

	public String getElementTagName(WebElement webElement) {
		String tagName = null;

		try {
			tagName = webElement.getTagName();
			testLog(true,"getElementTagName: Retrieved the tag name: " + tagName + ". Locator: "+ getLocatorValue(webElement));
			return tagName;
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}
		return tagName;
	}

	public WebElement getIndexedWebElement(WebElement webElement, int index) {
		try {
			return getDriver().findElements((By) webElement).get(index);
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
			return null;
		}

	}
	
	public double getDoubleFromString(WebElement webElement, int index) {
		String stringFromPage = "";
		double convertedDouble = -1.0D;

		try {
			stringFromPage = this.getTextOnLocator(webElement, index).replaceAll("[^0-9.]", "");
			convertedDouble = Double.parseDouble(stringFromPage);
			testLog(true,"getDoubleFromString: Parsed " + stringFromPage);
			return convertedDouble;
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
			return convertedDouble;
		}
	}

	public int getIntFromString(WebElement webElement, int index) {
		String stringFromPage = "";
		int convertedInteger = -1;

		try {
			stringFromPage = this.getTextOnLocator(webElement, index).replaceAll("[^0-9.]", "");
			convertedInteger = Integer.parseInt(stringFromPage);
			testLog(true,"getIntFromString: Parsed " + stringFromPage);
			return convertedInteger;
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
			return convertedInteger;
		}
	}

	public String getIntFromStringAsString(WebElement webElement, int index) {
		String stringFromPage = "";

		try {
			stringFromPage = this.getTextOnLocator(webElement, index).replaceAll("[^0-9]", "");
			testLog(true,"getIntFromStringAsString: Parsed: " + stringFromPage);
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

		return stringFromPage;
	}

	public JavascriptExecutor getJSExecutor() {
		JavascriptExecutor jsExecutor = null;

		try {
			jsExecutor = (JavascriptExecutor) getDriver();
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

		return jsExecutor;
	}

	public Point getLocationPointObject(WebElement element) {
		try {
			return element.getLocation();
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
			return null;
		}
	}

	public Point getLocationPointObject(WebElement webElement, int index) {
		try {
			return (getIndexedWebElement(webElement, index)).getLocation();
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
			return null;
		}
	}

	public String getSelectedOptionDropDown(WebElement webElement) {
		WebElement selectedValueElement = null;
		String selectedValue = "";

		try {
			Select ddBox = new Select(webElement);
			selectedValueElement = ddBox.getFirstSelectedOption();
			selectedValue = selectedValueElement.getText().toString();
			testLog(true,"Selected Option from the Drop Down : " + selectedValue+ ". Locator: "+ getLocatorValue(webElement));
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

		return selectedValue;
	}

	public String getTextOnAlertBox() {
		String alertBoxText = "";

		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 2L);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = getDriver().switchTo().alert();
			alertBoxText = alert.getText();
			testLog(true, "Text from Alert box is : " + alertBoxText);
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

		return alertBoxText;
	}

	public String getTextOnLocator(WebElement webElement) {
		String value = null;

		try {
			value = webElement.getText();
			testLogAndContinue(true,"Retrieved the text: " + value.trim()+ ". Locator: "+ getLocatorValue(webElement));
			return value.trim();
		} catch (Exception e) {
			testLogAndContinue(false, "Failed to Retrieve the text");
			return null;
		}
	}


	public String getTextOnLocator(WebElement webElement, int index) {
		String value = null;

		try {
			value = getIndexedWebElement(webElement, index).getText();
			testLogAndContinue(true,"Retrieved the text: " + value.trim()+ ". Locator: "+ getLocatorValue(webElement));
			return value.trim();
		} catch (Exception e) {
			testLogAndContinue(false, "Failed to Retrieve the text");
			return null;
		}
	}


	public String getWindowHandle() {
		String windowHandle = null;

		try {
			windowHandle = getDriver().getWindowHandle();
		} catch (Exception e) {
			testLogAndContinue(false,"getWindowHandle failed. Refer stacktrace.");
		}

		testLog("Window handle returned:" + windowHandle);
		return windowHandle;
	}

	public Set<String> getWindowHandles() {
		try {
			return getDriver().getWindowHandles();
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
			return null;
		}
	}



	public void highlightElement(WebElement webElement) {
		if (!getDriver().getClass().getSimpleName().contains("Java")) {
//			getJSExecutor().executeScript("arguments[0].style.outline=' dotted " + "yellow" + "'",webElement);
			getJSExecutor().executeScript("arguments[0].setAttribute('style','border: solid 2px red');",webElement);
		}

	}
	public void hover(WebElement webElement) {
		try {
			Actions action = new Actions(getDriver());
			action.moveToElement(webElement).perform();
			testLog(true, "hover over element. Locator: "+ getLocatorValue(webElement));
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void hover(WebElement webElement, int index) {
		try {
			WebElement webelement = getIndexedWebElement(webElement, index);
			Actions action = new Actions(getDriver());
			action.moveToElement(webelement);
			testLog(true, "hover over element. Locator: "+ getLocatorValue(webElement));
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}


	public boolean isAlertPresent() {
		try {
			getDriver().switchTo().alert();
			return true;
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
			return false;
		}
	}


	public boolean isChecked(WebElement webElement) {
		if (webElement.isSelected()) {
			testLog("Checkbox is checked. Locator: " + getLocatorValue(webElement));
			return true;
		} else {
			testLog("Checkbox is NOT checked");
			return false;
		}
	}


	public boolean isEnabled(WebElement webElement) {
		try {
			if (webElement.isEnabled()) {
				testLog("WebElement is enabled" + getLocatorValue(webElement));
				return true;
			} else {
				testLog("WebElement is not enabled" + getLocatorValue(webElement));
				return false;
			}
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
			return false;
		}
	}


	public boolean isDisplayed(WebElement webElement) {
		try {
			if (webElement.isDisplayed()) {
				testLog("WebElement is present and displayed" + getLocatorValue(webElement));
				return true;
			} else {
				testLog("WebElement is not present and displayed" + getLocatorValue(webElement));
				return false;
			}
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
			return false;
		}
	}

	public boolean isDisplayed(WebElement webElement, int index) {
		try {
			if (getIndexedWebElement(webElement, index).isDisplayed()) {
				testLog("WebElement is present and displayed" + getLocatorValue(webElement));
				return true;
			} else {
				testLog("WebElement is not present and displayed" + getLocatorValue(webElement));
				return false;
			}
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
			return false;
		}
	}

	public void jsClick(WebElement webElement) {
		try {
			testLog(true, "JS Clicking Locator: " + getLocatorValue(webElement));
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", webElement);
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void openNewTab() {
		
		try {
			((JavascriptExecutor) getDriver()).executeScript("window.open();", new Object[0]);
			setParentWindowHandle(getDriver().getWindowHandle());
			Iterator<?> iterator = getDriver().getWindowHandles().iterator();

			while (iterator.hasNext()) {
				String winHandle = (String) iterator.next();
				getDriver().switchTo().window(winHandle);
			}

			testLog(true, "New tab opened successfully");
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void performSlide(WebElement webElement, int xOffset, int yOffset) {
		try {
			Actions move = new Actions(getDriver());
			Action action = move.dragAndDropBy(webElement, xOffset, yOffset).build();
			action.perform();
			testLog(true, "perform Slide");
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void pressKey(WebElement webElement, Keys key) {
		try {
			Actions action = new Actions(getDriver());
			action.sendKeys(webElement, key).perform();
			testLog(true,"Pressed key : " + key.toString());
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void rightClick(WebElement webElement) {
		try {
			testLog(true,"Right clicking on locator : " + getLocatorValue(webElement));
			Actions action = new Actions(getDriver());
			action.contextClick(webElement).perform();
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void scroll(String direction) {
		try {
			JavascriptExecutor js;
			if (direction.equalsIgnoreCase("down")) {
				js = (JavascriptExecutor) getDriver();
				js.executeScript("window.scrollBy(0,250)", new Object[]{""});
				testLog(true, "scroll: " + direction);
			}

			if (direction.equalsIgnoreCase("up")) {
				js = (JavascriptExecutor) getDriver();
				js.executeScript("window.scrollBy(250, 0)", new Object[]{""});
				testLog(true, "scroll: " + direction);
			}
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void scrollTillObjectIsVisible(WebElement webElement) {
		try {
			if (webElement.isDisplayed()) {
				((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);",webElement);
				testLog(true,"Scrolldown till Element appear: Element found. Locator: " + getLocatorValue(webElement));
			}
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void selectOptionFromDropdown(WebElement webElement, int index) {
		try {
			Select selector = new Select(webElement);
			selector.getOptions().get(index).getText();
			selector.selectByIndex(index);
			testLog(true,"selected option: " + selector.getOptions().get(index).getText()+ "on index: " + index + " from drop down"+ ". Locator: "+ getLocatorValue(webElement));
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void selectOptionFromDropdown(WebElement webElement, String visibleText) {

		try {
			(new Select(webElement)).selectByVisibleText(visibleText);
			testLog(true, "selected text: " + visibleText+ " from drop down"+ ". Locator: "+ getLocatorValue(webElement));
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void selectOptionFromDropdownValue(WebElement webElement, String value) {

		try {
			(new Select(webElement)).selectByValue(value);
			testLog(true,"selected value: " + value + " from drop down"+ ". Locator: "+ getLocatorValue(webElement));
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void sendText(WebElement webElement, int index, String text) {
		try {
			(getIndexedWebElement(webElement, index)).sendKeys(text);
			testLog(true, "Input value: " + text+ ". Locator: "+ getLocatorValue(webElement));
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void sendText(WebElement webElement, String text) {

		try {
			this.highlightElement(webElement);
			webElement.sendKeys(text);
			testLog(true, "Input value: " + text+ ". Locator: "+ getLocatorValue(webElement));
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void setCurrentURL(String url) {
		try {
			getDriver().get(url);
			testLog(true, "setCurrentURL to:" + url);
		} catch (Exception e) {
			testLog(false, e.getStackTrace().toString());
		}

	}

	private void setParentWindowHandle(String windowHandle) {
		parentWindowHandle=windowHandle;

	}

	public void switchBackToParentTab() {
		try {
//			getDriver().close();
			getDriver().switchTo().window(parentWindowHandle);
			testLog("Changed focus to a Parent Tab");
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}


	public void switchToChildTab() {
		try {
			parentWindowHandle = getDriver().getWindowHandle();
			ArrayList<String> newTab = new ArrayList<String>(getDriver().getWindowHandles());
			newTab.remove(parentWindowHandle);
			getDriver().switchTo().window((String) newTab.get(0));
			testLog("Changed focus to a Child Tab");
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void switchToWindow(String windowHandle) {
		try {
			getDriver().switchTo().window(windowHandle);
			testLog("Switched the focus to window handle:" + windowHandle);
		} catch (Exception e) {
			testLogAndContinue(false,"switchToWindow failed. Refer stacktrace.");
		}

	}

	public void unCheckCheckBox(WebElement webElement) {
		try {
			if (webElement.isSelected()) {
				webElement.click();
				testLog(true, "Unchecked checkbox " + getLocatorValue(webElement));
			}
		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void unCheckCheckBox(WebElement webElement, int index) {
		try {
			this.highlightElement(webElement);
			if ((getIndexedWebElement(webElement, index)).isSelected()) {
				(getIndexedWebElement(webElement, index)).click();
				testLog(true,"UnChecked the checkbox  with index: "+ index + ":"  + getLocatorValue(webElement));
			}

		} catch (Exception e) {
			testLog(false,e.getStackTrace().toString());
		}

	}

	public void waitElementClickable(WebElement webElement) {
		long start = System.currentTimeMillis();

		try {
			(new WebDriverWait(getDriver(), TestUtil.EXPLICIT_WAIT))
			.until(ExpectedConditions.elementToBeClickable(webElement));
			testLog(true,"waitForElementClickble: Waited: " + Long.toString(System.currentTimeMillis() - start)
			+ ". Locator: "+ getLocatorValue(webElement)  + ", Timeout = " + TestUtil.EXPLICIT_WAIT
			+ " seconds");
		} catch (TimeoutException e) {
			testLog(false,"waitForElementClickble: Exceeded timeout threshold! while waiting for locator "+ getLocatorValue(webElement)
					+  ", Timeout Threshold: "
					+ TestUtil.EXPLICIT_WAIT );
		}

	}

	public void waitForElementAbsent(WebElement webElement) {
		long startTime = System.currentTimeMillis();
		long totalWaitTime = 0L;

		try {
			startTime = System.currentTimeMillis();
			(new WebDriverWait(getDriver(), TestUtil.EXPLICIT_WAIT)).ignoring(NoSuchElementException.class)
			.ignoring(TimeoutException.class).until(ExpectedConditions.invisibilityOfElementLocated((By) webElement));
			totalWaitTime = System.currentTimeMillis() - startTime;
			testLog(true,
					"waitForElementAbsent: Waited " + Long.toString(totalWaitTime) + " ms for-> "
							+ " to be absent, Timeout = " + TestUtil.EXPLICIT_WAIT
							+ " seconds");
		} catch (TimeoutException e) {
			testLog(false,
					"waitForElementAbsent: Exceeded timeout threshold! while waiting for locator "+ getLocatorValue(webElement)
							+ " absence, Timeout Threshold: "
							+ TestUtil.EXPLICIT_WAIT);
		} catch (Exception e) {
			;
		}

	}

	public void waitForElementPresent(WebElement webElement) {
		long startTime = System.currentTimeMillis();
		long totalWaitTime = 0L;

		try {
			startTime = System.currentTimeMillis();
			(new WebDriverWait(getDriver(), TestUtil.EXPLICIT_WAIT)).ignoring(NoSuchElementException.class)
			.ignoring(TimeoutException.class).until(ExpectedConditions.visibilityOfElementLocated((By) webElement));
			totalWaitTime = System.currentTimeMillis() - startTime;
			testLog(true,
					"waitForElement: Waited " + Long.toString(totalWaitTime) + " ms for-> "
							+ ", Timeout = " + TestUtil.EXPLICIT_WAIT + " seconds");
		} catch (TimeoutException e) {
			testLog(false,
					"TimeoutException: Exceeded timeout threshold! while waiting for locator "+ getLocatorValue(webElement)
							+ ", Timeout Threshold: "
							+ TestUtil.EXPLICIT_WAIT);
		} catch (NoSuchElementException e) {
			testLog(false,
					"NoSuchElementException: Exceeded timeout threshold! while waiting for locator "+ getLocatorValue(webElement)
							+ ", Timeout Threshold: "
							+ TestUtil.EXPLICIT_WAIT);
		} catch (Exception e) {
			testLog(false,"waitForElement: Exceeded timeout threshold! while waiting for locator "+ getLocatorValue(webElement)
					+ ", Timeout Threshold: "
					+ TestUtil.EXPLICIT_WAIT);
		}

	}


	public void waitForElementPropertyChange(WebElement webElement, String attribute, String value) {
		WebDriverWait wait = new WebDriverWait(getDriver(), TestUtil.EXPLICIT_WAIT);
		wait.until(ExpectedConditions.attributeToBe(webElement, attribute, value));
	}
}
