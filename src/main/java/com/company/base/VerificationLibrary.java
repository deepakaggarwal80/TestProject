package com.company.base;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class VerificationLibrary extends ActionLibrary{

public void verifyAttributeValue(WebElement webElement, int index, String attributeName, String expectedValue) {
		String actualValue = null;

		try {
			WebElement element = getIndexedWebElement(webElement, index);
			this.highlightElement(element);
			actualValue = (getIndexedWebElement(webElement, index)).getAttribute(attributeName)
					.trim();
			if (actualValue.equals(expectedValue)) {
				testLog(true,"verifyAttributeValue: The value in the attribute equals the expected value. Element-> "
								+ getLocatorValue(webElement) + ", index: " + index + ", attribute name: " + attributeName
								+ ", attribute value: " + actualValue);
			} else {
				testLogAndContinue(false,"verifyAttributeValue: The value in the attribute does not equal the expected value. Element-> "
						+ getLocatorValue(webElement) + ", index: " + index + ", attribute name: " + attributeName
								+ ", expected value: " + expectedValue + ", actual value: " + actualValue);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyAttributeValue(WebElement webElement, String attributeName, String expectedValue) {
		String actualValue = null;

		try {
			this.highlightElement(webElement);
			actualValue = webElement.getAttribute(attributeName);
			if (actualValue.equals(expectedValue)) {
				testLog(true,"verifyAttributeValue: The value in the attribute equals the expected value. Element-> "
						+ getLocatorValue(webElement) + ", attribute name: " + attributeName
								+ ", attribute value: " + actualValue);
			} else {
				testLogAndContinue(false,"verifyAttributeValue: The value in the attribute does not equal the expected value. Element-> "
								 + ", attribute name: " + attributeName + ", expected value: "
								+ expectedValue + ", actual value: " + actualValue);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyAttributeValueContainsText(WebElement webElement, int index, String attributeName, String expectedValue) {
		String actualValue = null;

		try {
			WebElement element = getIndexedWebElement(webElement, index);
			this.highlightElement(element);
			actualValue = element.getAttribute(attributeName).trim();
			if (actualValue.contains(expectedValue)) {
				testLog(true,"verifyAttributeValue: The value in the attribute contains the expected text. Element-> "
						+ getLocatorValue(webElement) + ", attribute name: " + attributeName
								+ ", attribute value: " + actualValue);
			} else {
				testLogAndContinue(false,"verifyAttributeValue: The value in the attribute does not contain the expected text. Element-> "
						+ getLocatorValue(webElement) + ", attribute name: " + attributeName + ", expected value: "
								+ expectedValue + ", actual value: " + actualValue);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyAttributeValueContainsText(WebElement webElement, String attributeName, String expectedValue) {
		String actualValue = null;

		try {
			this.highlightElement(webElement);
			actualValue = webElement.getAttribute(attributeName);
			if (actualValue.contains(expectedValue)) {
				testLog(true,"verifyAttributeValue: The value in the attribute contains the expected text. Element-> "
						+ getLocatorValue(webElement) + ", attribute name: " + attributeName
								+ ", attribute value: " + actualValue);
			} else {
				testLogAndContinue(false,"verifyAttributeValue: The value in the attribute does not contain the expected text. Element-> "
						+ getLocatorValue(webElement) + ", attribute name: " + attributeName + ", expected value: "
								+ expectedValue + ", actual value: " + actualValue);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyCheckboxIsChecked(WebElement webElement) {
		try {
			this.highlightElement(webElement);
			if (webElement.isSelected()) {
				testLog(true,"verifyCheckboxIsChecked: The checkbox is checked. Element-> " + getLocatorValue(webElement));
			} else {
				testLogAndContinue(false,"verifyCheckboxIsChecked: The checkbox is NOT checked. Element-> " + getLocatorValue(webElement) );
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyCheckboxIsChecked(WebElement webElement, int index) {
		try {
			WebElement element = getIndexedWebElement(webElement, index);
			this.highlightElement(element);
			if (element.isSelected()) {
				testLog(true,"verifyCheckboxIsChecked: The checkbox is checked. Element-> " 
						+ getLocatorValue(webElement)	+ ", index: " + index);
			} else {
				testLogAndContinue(false,"verifyCheckboxIsChecked: The checkbox is NOT checked. Element-> " 
						+ getLocatorValue(webElement) + ", index: " + index);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyCheckboxIsNotChecked(WebElement webElement) {
		try {
			this.highlightElement(webElement);
			if (!webElement.isSelected()) {
				testLog(true,"verifyCheckboxIsNotChecked: The checkbox is NOT checked. Element-> "+ getLocatorValue(webElement));
			} else {
				testLogAndContinue(false,"verifyCheckboxIsNotChecked: The checkbox is checked. Element-> "+ getLocatorValue(webElement) );
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyCheckboxIsNotChecked(WebElement webElement, int index) {
		try {
			WebElement element = getIndexedWebElement(webElement, index);
			this.highlightElement(element);
			Boolean selectionStatus = element.isSelected();
			if (!selectionStatus) {
				testLog(true,"verifyCheckboxIsNotChecked: The checkbox is NOT checked. Element-> " 
						+ getLocatorValue(webElement) + ", index: " + index);
			} else {
				testLogAndContinue(false,"verifyCheckboxIsNotChecked: The checkbox is checked. Element-> " 
						+ getLocatorValue(webElement) + ", index: " + index);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyCondition(boolean expression, String verificationDescription) {
		if (expression) {
			testLog(true,"verifyCondition: Passed " + verificationDescription);
		} else {
			testLogAndContinue(false,"verifyCondition: Failed" + verificationDescription);
		}

	}

	public void verifyCSSValue(WebElement webElement, String cssProp, String expectedValue) {
		String actualValue = null;

		try {
			this.highlightElement(webElement);
			actualValue = webElement.getCssValue(cssProp);
			if (actualValue.equals(expectedValue)) {
				testLog(true,"verifyCSSValue: The value in the CSS property equals the expected value. Element-> " + getLocatorValue(webElement)
								 + ", CSS Property name: " + cssProp + ", attribute value: "
								+ actualValue);
			} else {
				testLogAndContinue(false,"verifyCSSValue: The value in the CSS property does not equal the expected value. Element-> " + getLocatorValue(webElement)
								 + ", CSS Property name: " + cssProp + ", expected value: "
								+ expectedValue + ", actual value: " + actualValue);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyCurrentUrl(String URL) {
		if (this.getCurrentURL().equalsIgnoreCase(URL)) {
			testLog(true, "Expected URL matches current URL");
		} else {
			testLogAndContinue(false, "Expected URL matches current URL");
		}

	}

	public void verifyDropDownContainsSelection(WebElement webElement, String selectedItem) {
		boolean isSelected = false;

		try {
			this.highlightElement(webElement);
			Select comboBox = new Select(webElement);
			WebElement selections = comboBox.getFirstSelectedOption();
			if (selections.getText().contains(selectedItem)) {
				isSelected = true;
			}

			if (isSelected) {
				testLog(true, "verifyDropDownSelection: '" + selectedItem
						+ "' is selected. Element-> "+ getLocatorValue(webElement) );
			} else {
				testLogAndContinue(false, "verifyDropDownSelection: '"
						+ selectedItem + "' is NOT selected. Element-> "+ getLocatorValue(webElement) );
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyDropDownContainsSelectionIgnoreCase(WebElement webElement, String selectedItem) {
		boolean isSelected = false;

		try {
			this.highlightElement(webElement);
			Select comboBox = new Select(webElement);
			WebElement selections = comboBox.getFirstSelectedOption();
			if (selections.getText().toLowerCase().contains(selectedItem.toLowerCase())) {
				isSelected = true;
			}

			if (isSelected) {
				testLog(true, "verifyDropDownSelection: '" + selectedItem
						+ "' is selected. Element-> "+ getLocatorValue(webElement) );
			} else {
				testLogAndContinue(false, "verifyDropDownSelection: '"
						+ selectedItem + "' is NOT selected. Element-> "+ getLocatorValue(webElement) );
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyDropDownSelection(WebElement webElement, String selectedItem) {
		boolean isSelected = false;

		try {
			this.highlightElement(webElement);
			Select comboBox = new Select(webElement);
			WebElement selections = comboBox.getFirstSelectedOption();
			if (selections.getText().equals(selectedItem)) {
				isSelected = true;
			}

			if (isSelected) {
				testLog(true, "verifyDropDownSelection: '" + selectedItem
						+ "' is selected. Element-> "+ getLocatorValue(webElement) );
			} else {
				testLogAndContinue(false, "verifyDropDownSelection: '"
						+ selectedItem + "' is NOT selected. Element-> " + getLocatorValue(webElement));
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyDropDownSelectionIgnoreCase(WebElement webElement, String selectedItem) {
		boolean isSelected = false;

		try {
			this.highlightElement(webElement);
			Select comboBox = new Select(webElement);
			WebElement selections = comboBox.getFirstSelectedOption();
			if (selections.getText().toLowerCase().equals(selectedItem.toLowerCase())) {
				isSelected = true;
			}

			if (isSelected) {
				testLog(true, "verifyDropDownSelection: '" + selectedItem
						+ "' is selected. Element-> " + getLocatorValue(webElement));
			} else {
				testLogAndContinue(false, "verifyDropDownSelection: '"
						+ selectedItem + "' is NOT selected. Element-> " + getLocatorValue(webElement));
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyElementAbsenceInsideParentElement(WebElement parentWebElement, WebElement childWebElement) {

		try {
			if (parentWebElement.findElement((By) childWebElement) != null) {
				testLog(true,"verifyElementAbsenceInsideParentElement: verified-> Element"+getLocatorValue(childWebElement) + "absent inside parent element"+getLocatorValue(parentWebElement)); 
			}
			else
			testLogAndContinue(false,"verifyElementAbsenceInsideParentElement: verified-> Element"+getLocatorValue(childWebElement) + "present inside parent element"+getLocatorValue(parentWebElement));
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}
	
	public void verifyElementPresenceInsideParentElement(WebElement parentWebElement, WebElement childWebElement) {
		try {
			if (parentWebElement.findElement((By) childWebElement) != null) {
				testLogAndContinue(true,"verifyElementPresenceInsideParentElement: verified-> Element"+getLocatorValue(childWebElement) + "present inside parent element"+getLocatorValue(parentWebElement));
			}
			else
				testLog(false,"verifyElementPresenceInsideParentElement: verified-> Element"+getLocatorValue(childWebElement) + "absent inside parent element"+getLocatorValue(parentWebElement)); 
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}
	}

	public void verifyElementAbsentOnPage(WebElement webElement) {
		try {
			if (webElement.isDisplayed()) {
				this.highlightElement(webElement);
				testLogAndContinue(false,"verifyElementAbsentOnPage: Element found on the page when not expected. Element-> "
						+ getLocatorValue(webElement));
			} else {
				testLog(true,"verifyElementAbsentOnPage: Element found in the DOM but not displayed. Element-> "
						+ getLocatorValue(webElement));
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyElementAbsentOnPage(WebElement webElement, int index) {
		try {
			WebElement element = getIndexedWebElement(webElement, index);
			if (element.isDisplayed()) {
				this.highlightElement(element);
				testLogAndContinue(false,"verifyElementAbsentOnPage: Element found on the page when not expected. Element-> "
						+ getLocatorValue(webElement));
			} else {
				testLogAndContinue(true,"verifyElementAbsentOnPage: Element found in the DOM but not displayed. Element-> "
						+ getLocatorValue(webElement));
			}
		
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyElementOccurenceCount(WebElement webElement, int count) {
		try {
			if (getElementCount(webElement)== count) {
				testLog(true,"verifyElementOccurenceCount: Element occurrence count is correct. Element-> "
						+ getLocatorValue(webElement) + " Count: " + count);
			} else {
				testLogAndContinue(false,"verifyElementOccurenceCount: Element occurrence count is INCORRECT. Element-> "
						+ getLocatorValue(webElement) + " Expected count: " + count + ", Actual count: "
								+ getElementCount(webElement));
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}


	public void verifyElementPresentOnPage(WebElement webElement) {
		try {
			if (webElement.isDisplayed()) {
				this.highlightElement(webElement);
				testLog(true,"verifyElementPresentOnPage: Element found. Element-> "+ getLocatorValue(webElement) );
			} else {
				testLogAndContinue(false,"verifyElementPresentOnPage: Element NOT found. Element-> "+ getLocatorValue(webElement) );
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyElementPresentOnPage(WebElement webElement, int index) {
		try {
			WebElement element = getIndexedWebElement(webElement, index);
			if (element.isDisplayed()) {
				this.highlightElement(element);
				testLog(true,"verifyElementPresentOnPage: Element " + getLocatorValue(webElement));
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyEqual(double valueA, double valueB, String verificationDescription) {
		if (valueA == valueB) {
			testLog(true, "verifyEqual: " + verificationDescription
					+ ": value '" + valueA + "' is equal to value '" + valueB + "'");
		} else {
			testLogAndContinue(false, "verifyEqual: " + verificationDescription
					+ ": value '" + valueA + "' is NOT equal to value: " + valueB + "'");
		}

	}

	public void verifyEqual(int valueA, int valueB, String verificationDescription) {
		if (valueA == valueB) {
			testLog(true, "verifyEqual: " + verificationDescription
					+ ": value '" + valueA + "' is equal to value '" + valueB + "'");
		} else {
			testLogAndContinue(false, "verifyEqual: " + verificationDescription
					+ ": value '" + valueA + "' is NOT equal to value: " + valueB + "'");
		}

	}


	public void verifyGreater(int expectedGreater, int expectedLess, String verificationDescription) {
		if (expectedGreater > expectedLess) {
			testLog(true, "verifyGreater: " + verificationDescription
					+ ": value: " + expectedGreater + " is greater than value: " + expectedLess);
		} else {
			testLogAndContinue(false,"verifyGreater: " + verificationDescription + ": value: " + expectedGreater
							+ " is NOT greater value: " + expectedLess);
		}

	}


	public void verifyGreaterOrEqual(int expectedGreaterOrEqual, int expectedLessOrEqual,
			String verificationDescription) {
		if (expectedGreaterOrEqual >= expectedLessOrEqual) {
			testLog(true,"verifyGreaterOrEqual: " + verificationDescription + ": value '" + expectedGreaterOrEqual
							+ "' is equal to or greater than value '" + expectedLessOrEqual + "'");
		} else {
			testLogAndContinue(false,"verifyGreaterOrEqual: " + verificationDescription + ": value '" + expectedGreaterOrEqual
							+ "' is NOT equal or greater than value '" + expectedLessOrEqual + "'");
		}

	}



	public void verifyEqual(String valueA, String valueB, boolean enableCaseSensitiveVerification,
			String verificationDescription) {
		try {
			if (enableCaseSensitiveVerification) {
				if (valueA.equals(valueB)) {
					testLog(true, "verifyEqual: " + verificationDescription
							+ ": value '" + valueA + "' is equal to value '" + valueB + "'");
				} else {
					testLogAndContinue(false,"verifyIsEqual: " + verificationDescription + ": value '" + valueA
									+ "' is NOT equal to value '" + valueB + "'");
				}
			} else if (valueA.equalsIgnoreCase(valueB)) {
				testLog(true, "verifyEqual: " + verificationDescription
						+ ": value '" + valueA + "' is equal to value '" + valueB + "'");
			} else {
				testLogAndContinue(false, "verifyIsEqual: "
						+ verificationDescription + ": value '" + valueA + "' is NOT equal to value '" + valueB + "'");
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyIsNotEqual(int valueA, int valueB, String verificationDescription) {
		try {
			if (valueA != valueB) {
				testLog(true, "verifyIsNotEqual: " + verificationDescription
						+ ": value '" + valueA + "' is NOT equal to value '" + valueB + "'");
			} else {
				testLogAndContinue(false, "verifyIsNotEqual: "
						+ verificationDescription + ": value '" + valueA + "' is equal to value '" + valueB + "'");
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyIsNotEqual(String valueA, String valueB, boolean enableCaseSensitiveVerification,
			String verificationDescription) {
		try {
			if (enableCaseSensitiveVerification) {
				if (!valueA.equals(valueB)) {
					testLog(true,"verifyIsNotEqual: " + verificationDescription + ": value '" + valueA
									+ "' is NOT equal to value '" + valueB + "'");
				} else {
					testLogAndContinue(false, "verifyIsNotEqual: "
							+ verificationDescription + ": value '" + valueA + "' is equal to value '" + valueB + "'");
				}
			} else if (!valueA.equalsIgnoreCase(valueB)) {
				testLog(true, "verifyIsNotEqual: " + verificationDescription
						+ ": value '" + valueA + "' is NOT equal to value '" + valueB + "'");
			} else {
				testLogAndContinue(false, "verifyIsNotEqual: "
						+ verificationDescription + ": value '" + valueA + "' is equal to value '" + valueB + "'");
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyOptionDontExistsOnDropDown(WebElement webElement, String item) {
		int count = 0;

		try {
			this.highlightElement(webElement);
			Select select = new Select(webElement);
			List<WebElement> list = select.getOptions();
			Iterator<WebElement> iterator = list.iterator();

			while (iterator.hasNext()) {
				WebElement options = (WebElement) iterator.next();
				if (options.getText().equalsIgnoreCase(item)) {
					++count;
				}
			}

			if (count > 0) {
				testLogAndContinue(false, "verifyOptionDontExistsOnDropDown: '"
						+ item + "' does  exist in the drop-down list:-> " );
			} else {
				testLog(true, "verifyOptionDontExistsOnDropDown: '" + item
						+ "' dont exist in the drop-down list:-> " );
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyOptionExistsOnDropDown(WebElement webElement, String item) {
		int count = 0;

		try {
			this.highlightElement(webElement);
			Select select = new Select(webElement);
			List<WebElement> list = select.getOptions();
			Iterator<WebElement> iterator = list.iterator();

			while (iterator.hasNext()) {
				WebElement options = (WebElement) iterator.next();
				if (options.getText().trim().equalsIgnoreCase(item)) {
					++count;
				}
			}

			if (count > 0) {
				testLog(true, "verifyOptionExistsOnDropDown: '" + item
						+ "' exists in the drop-down list:-> " );
			} else {
				testLogAndContinue(false, "verifyOptionExistsOnDropDown: '"
						+ item + "' does NOT exist in the drop-down list:-> " );
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyPageTitle(String expectedTitle) {
		try {
			String actualTitle = getDriver().getTitle();
			if (actualTitle.equalsIgnoreCase(expectedTitle)) {
				testLog(true, "Title matches, Title = " + actualTitle);
			} else {
				testLogAndContinue(false,"Title doesn't match: Expected Title = " + expectedTitle + ", Actual Title = " + actualTitle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyRadioButtonIsNotSelected(WebElement webElement) {
		try {
			this.highlightElement(webElement);
			if (!webElement.isSelected()) {
				testLog(true,"verifyRadioButtonIsNotSelected: The radio button is NOT selected. Element-> "
						+ getLocatorValue(webElement));
			} else {
				testLogAndContinue(false,"verifyRadioButtonIsNotSelected: The radio button is selected. Element-> "
						+ getLocatorValue(webElement));
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyRadioButtonIsNotSelected(WebElement webElement, int index) {
		try {
			WebElement element = getIndexedWebElement(webElement, index);
			this.highlightElement(element);
			if (!element.isSelected()) {
				testLog(true,"verifyRadioButtonIsNotSelected: The radio button is NOT selected. Element-> "
						+ getLocatorValue(webElement) + ", Index: " + index);
			} else {
				testLogAndContinue(false,"verifyRadioButtonIsNotSelected: The radio button isselected. Element-> "
						+ getLocatorValue(webElement) + ", Index: " + index);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}


	public void verifyRadioButtonIsSelected(WebElement webElement) {
		try {
			this.highlightElement(webElement);
			if (webElement.isSelected()) {
				testLog(true,"verifyRadioButtonIsSelected: The radio button is selected. Element-> "
						+ getLocatorValue(webElement));
			} else {
				testLogAndContinue(false,"verifyRadioButtonIsSelected: The radio button is NOT selected. Element-> "
						+ getLocatorValue(webElement));
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyRadioButtonIsSelected(WebElement webElement, int index) {
		try {
			WebElement element = getIndexedWebElement(webElement, index);
			this.highlightElement(element);
			if (element.isSelected()) {
				testLog(true,"verifyRadioButtonIsSelected: The radio button is selected. Element-> "
						+ getLocatorValue(webElement) + ", Index: " + index);
			} else {
				testLogAndContinue(false,"verifyRadioButtonIsSelected: The radio button is NOT selected. Element-> "
						+ getLocatorValue(webElement) + ", Index: " + index);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyText(String actual, String expected) {

		try {
			if (actual != null && expected != null) {
				if (actual.trim().equalsIgnoreCase(expected.trim())) {
					testLog(true,"verifyText: The text matches the expected value. Text: " + expected);
				} else {
					testLogAndContinue(false,"verifyText: The text does NOT match the expected value. Actual: " + actual + ", Expected: "
									+ expected);
				}
			} else {
				testLog(false,"verifyText: The Actual/Expected value cannot be null. Actual: " + actual + ", Expected: "
								+ expected);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}


	public void verifyTextDoesNotExist(String parentString, String subString) {
		if (parentString.toLowerCase().contains(subString.toLowerCase())) {
			testLogAndContinue(false,subString + " was found in " + parentString);
		} else {
			testLog(true, subString + " was NOT found in " + parentString);
		}

	}

	public void verifyTextDoesNotExistOnAnyOccurenceOfElement(WebElement webElement, String verificationText) {
		boolean flag = false;
		String retrievedText = "";

		try {
			List<WebElement> elements = getDriver().findElements((By) webElement);
			if (elements.isEmpty()) {
				testLogAndContinue(false,"verifyTextDoesNotExistOnAnyOccurenceOfElement: The element does NOT exist on the page. Element-> "
						+ getLocatorValue(webElement));
				return;
			}

			for (int i = 0; i < elements.size(); ++i) {
				if (((WebElement) elements.get(i)).isDisplayed()) {
					retrievedText = ((WebElement) elements.get(i)).getText().trim();
					if (retrievedText.contains(verificationText.trim())) {
						this.highlightElement((WebElement) elements.get(i));
						testLogAndContinue(false,"verifyTextDoesNotExistOnAnyOccurenceOfElement: The text was found on the Element-> "
								+ getLocatorValue(webElement) + ", Parameter text: " + verificationText
										+ ". Text on locator:" + retrievedText);
						return;
					}

					flag = true;
				}
			}

			if (flag) {
				testLogAndContinue(true,"verifyTextDoesNotExistOnElement: The text was not found on the Element-> "
						+ getLocatorValue(webElement) + ", Parameter text:" + verificationText + ". Text on locator:"
								+ retrievedText);
			}

			if (elements.size() > 1) {
				testLog("Warning: The locator matches to more than 1 element(i.e. " + elements.size()
								+ "). Please either use the verification with index or make the locator specific.");
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyTextExist(String parentString, String subString) {
		if (parentString.toLowerCase().contains(subString.toLowerCase())) {
			testLog(true, subString + " was found in " + parentString);
		} else {
			testLogAndContinue(false,subString + " was NOT found in " + parentString);
		}

	}

	public void verifyTextExistsOnAnyOccurenceOfElement(WebElement webElement, String verificationText) {
		boolean found = false;
		String retrievedText = "";

		try {
			List<WebElement> texts = getDriver().findElements((By) webElement);
			if (texts.isEmpty()) {
				testLogAndContinue(false,"verifyTextExistsOnAnyOccurenceOfElement: The element does NOT exist on the page. Element-> "
						+ getLocatorValue(webElement) + " text: " + verificationText);
				return;
			}

			int i;
			for (i = 0; i < texts.size(); ++i) {
				retrievedText = ((WebElement) texts.get(i)).getText().trim();
				if (retrievedText.toLowerCase().contains(verificationText.toLowerCase().trim())
						& ((WebElement) texts.get(i)).isDisplayed()) {
					this.highlightElement((WebElement) texts.get(i));
					found = true;
					testLog(true,"verifyTextExistsOnAnyOccurenceOfElement: The text verified successfully. Element-> "
							+ getLocatorValue(webElement) + " text: " + verificationText);
					break;
				}
			}

			if (!found) {
				this.highlightElement((WebElement) texts.get(i));
				testLogAndContinue(false,"verifyTextExistsOnAnyOccurenceOfElement: Text verification. Element-> "
						+ getLocatorValue(webElement) + " Expected text: " + verificationText + ", Actual Text: "
								+ retrievedText);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyTextExistsOnElement(WebElement webElement, String verificationText) {
		

		try {
			String actualText = webElement.getText();
			if (actualText.contains(verificationText)) {
				testLogAndContinue(true,"verifyTextExistsOnElement: Element-> "  + " having text: "
								+ actualText + ", contains text:" + verificationText);
			} else {
				testLogAndContinue(false,"verifyTextExistsOnElement: Element-> "  + " having text: "
								+ actualText + ",does not contains text:" + verificationText);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyTextExistsOnElementRegularExp(WebElement webElement, String expectedTextExpression) {
		try {
			this.highlightElement(webElement);
			Pattern pattern = Pattern.compile(expectedTextExpression);
			String text1 = webElement.getText().trim();
			Matcher match = pattern.matcher(text1);
			if (match.find()) {
				testLogAndContinue(true,"verifyTextExistsOnElementRegularExp: Text Verification passed. Element-> "
						+ getLocatorValue(webElement) + ", Regular Expression: " + expectedTextExpression
								+ ", Text: " + text1);
			} else {
				testLogAndContinue(false,"verifyTextExistsOnElementRegularExp: Text Verification failed. Element-> "
						+ getLocatorValue(webElement) + ", Regular Expression: " + expectedTextExpression
								+ ", Text: " + text1);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyTextInPageSouce(String expectedText) {
		try {
			Boolean result = getDriver().getPageSource().matches(expectedText);
			if (result) {
				testLog(true,"verifyTextInPageSouce: Text found. Text-> " + expectedText);
			} else {
				testLogAndContinue(false,"verifyTextInPageSouce: Text NOT found. Text-> " + expectedText);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}


	public void verifyTextMatchesRegEx(String parentString, String RegExp) {
		try {
			Pattern pattern = Pattern.compile(RegExp);
			Matcher matcher = pattern.matcher(parentString);
			boolean matches = matcher.matches();
			if (matches) {
				testLogAndContinue(true,parentString + " matches with the RegEx " + RegExp);
			} else {
				testLogAndContinue(false,parentString + " does not match with the RegEx - " + RegExp);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyTextNotOnElement(WebElement webElement, String verificationText) {
		boolean flag = false;
		String retrievedText = "";

		try {
			List<WebElement> elements = getDriver().findElements((By) webElement);
			if (elements.isEmpty()) {
				testLogAndContinue(false,"verifyTextNotOnElement: The element does NOT exist on the page. Element-> "
						+ getLocatorValue(webElement));
				return;
			}

			for (int i = 0; i < elements.size(); ++i) {
				if (((WebElement) elements.get(i)).isDisplayed()) {
					retrievedText = ((WebElement) elements.get(i)).getText().trim();
					if (retrievedText.equals(verificationText.trim())) {
						this.highlightElement((WebElement) elements.get(i));
						testLogAndContinue(false,"verifyTextNotOnElement: The text was found on the Element-> "
								+ getLocatorValue(webElement)+ ", Parameter text: " + verificationText
										+ ". Text on locator:" + retrievedText);
						return;
					}

					flag = true;
				}
			}

			if (flag) {
				testLogAndContinue(true,"verifyTextNotOnElement: The text was not found on the Element-> " 
						+ getLocatorValue(webElement)+ ", Parameter text:" + verificationText + ". Text on locator:" + retrievedText);
			}

			if (elements.size() > 1) {
				testLog("Warning: The locator matches to more than 1 element(i.e. " + elements.size()
								+ "). Please either use the verification with index or make the locator specific.");
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}


	public void verifyTextOnAnyOccurenceOfElement(WebElement webElement, String verificationText) {
		String flag = "0";
		String retrievedText = "";

		try {
			List<WebElement> texts = getDriver().findElements((By) webElement);
			if (texts.isEmpty()) {
				testLogAndContinue(false,"verifyTextOnAnyOccurenceOfElement: The element does NOT exist on the page. Element-> "
						+ getLocatorValue(webElement) + " text: " + verificationText);
				return;
			}

			int i;
			for (i = 0; i < texts.size(); ++i) {
				retrievedText = ((WebElement) texts.get(i)).getText().trim();
				if (retrievedText.equalsIgnoreCase(verificationText.trim()) & ((WebElement) texts.get(i)).isDisplayed()) {
					flag = "1";
					this.highlightElement((WebElement) texts.get(i));
					testLog(true,"verifyTextOnAnyOccurenceOfElement: The text verified successfully. Element-> "
							+ getLocatorValue(webElement) + " text: " + verificationText);
					break;
				}
			}

			if (flag.contains("0")) {
				this.highlightElement((WebElement) texts.get(i));
				testLogAndContinue(false,"verifyTextOnAnyOccurenceOfElement: Text verification. Element-> " 
						+ getLocatorValue(webElement) + " Expected text: " + verificationText + ", Actual Text: " + retrievedText);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyTextOnElement(WebElement webElement, String verificationText) {
		

		try {
			String actualText = webElement.getText();
			if (actualText.equals(verificationText)) {
				testLogAndContinue(true,"verifyTextOnElement: Element-> "  + " having text: " + actualText
								+ ", matches text:" + verificationText);
			} else {
				testLogAndContinue(false,"verifyTextOnElement: Element-> "  + " having text: " + actualText
								+ ",does not match text:" + verificationText);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}
	
	public void verifyTextExistsOnElement(WebElement webElement, String verificationText, int index) {
		try {
			String actualText = (getIndexedWebElement(webElement, index)).getText();
			if (actualText.contains(verificationText)) {
				testLogAndContinue(true,"verifyIndexedTextExistsOnElement: Element-> " + getLocatorValue(webElement) + " having text: "
								+ actualText + ", contains text:" + verificationText);
			} else {
				testLogAndContinue(false,"verifyIndexedTextExistsOnElement: Element-> " + getLocatorValue(webElement) + " having text: "
								+ actualText + ",does not contains text:" + verificationText);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyTextOnPseudoElement(String querySelector, String propertyValue, String verificationText) {
		testLog("Start of 'verifyTextOnPseudoElement' method");
		String script = "return window.getComputedStyle(document.querySelector('" + querySelector
				+ "').getPropertyValue('" + propertyValue + "')";
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		String content = (String) js.executeScript(script, new Object[0]);
		this.verifyTextExist(content, verificationText);
		testLog("End of 'verifyTextOnPseudoElement' method");
	}

	public void verifyValuesInDropDownList(WebElement webElement, String[] valueToMatch) {
		boolean flag = false;
		Select se = new Select(webElement);
		List<WebElement> allSelectedOptions = se.getOptions();

		for (int i = 0; i < valueToMatch.length; ++i) {
			flag = false;
			Iterator<WebElement> iterator = allSelectedOptions.iterator();

			while (iterator.hasNext()) {
				WebElement we = (WebElement) iterator.next();
				if (we.getText().replace("\n", "").trim().equals(valueToMatch[i])) {
					flag = true;
				}
			}

			if (!flag) {
				testLogAndContinue(false, "verifyValuesInDropDownList: "
						+ valueToMatch[i] + " is not in list. Element-> " );
			}
		}

		if (flag) {
			testLog(true,"verifyValuesInDropDownList: All values are included in the Drop Down List. Element-> ");
		}

	}
}
