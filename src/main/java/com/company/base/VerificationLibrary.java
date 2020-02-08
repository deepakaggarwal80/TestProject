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
				testLog(true,getCurrentMethodName() + "The value in the attribute equals the expected value. Locator: "
								+ getLocatorValue(webElement) + ", index: " + index + ", attribute name: " + attributeName
								+ ", attribute value: " + actualValue);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "The value in the attribute does not equal the expected value. Locator: "
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
				testLog(true,getCurrentMethodName() + "The value in the attribute equals the expected value. Locator: "
						+ getLocatorValue(webElement) + ", attribute name: " + attributeName
								+ ", attribute value: " + actualValue);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "The value in the attribute does not equal the expected value. Locator: "
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
				testLog(true,getCurrentMethodName() + "The value in the attribute contains the expected text. Locator: "
						+ getLocatorValue(webElement) + ", attribute name: " + attributeName
								+ ", attribute value: " + actualValue);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "The value in the attribute does not contain the expected text. Locator: "
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
				testLog(true,getCurrentMethodName() + "The value in the attribute contains the expected text. Locator: "
						+ getLocatorValue(webElement) + ", attribute name: " + attributeName
								+ ", attribute value: " + actualValue);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "The value in the attribute does not contain the expected text. Locator: "
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
				testLog(true,getCurrentMethodName() + "The checkbox is checked. Locator: " + getLocatorValue(webElement));
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "The checkbox is NOT checked. Locator: " + getLocatorValue(webElement) );
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
				testLog(true,getCurrentMethodName() + "The checkbox is checked. Locator: " 
						+ getLocatorValue(webElement)	+ ", index: " + index);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "The checkbox is NOT checked. Locator: " 
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
				testLog(true,getCurrentMethodName() + "The checkbox is NOT checked. Locator: "+ getLocatorValue(webElement));
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "The checkbox is checked. Locator: "+ getLocatorValue(webElement) );
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
				testLog(true,getCurrentMethodName() + "The checkbox is NOT checked. Locator: " 
						+ getLocatorValue(webElement) + ", index: " + index);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "The checkbox is checked. Locator: " 
						+ getLocatorValue(webElement) + ", index: " + index);
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyCondition(boolean expression, String verificationDescription) {
		if (expression) {
			testLog(true,getCurrentMethodName() + "Passed " + verificationDescription);
		} else {
			testLogAndContinue(false,getCurrentMethodName() + "Failed" + verificationDescription);
		}

	}

	public void verifyCSSValue(WebElement webElement, String cssProp, String expectedValue) {
		String actualValue = null;

		try {
			this.highlightElement(webElement);
			actualValue = webElement.getCssValue(cssProp);
			if (actualValue.equals(expectedValue)) {
				testLog(true,getCurrentMethodName() + "The value in the CSS property equals the expected value. Locator: " + getLocatorValue(webElement)
								 + ", CSS Property name: " + cssProp + ", attribute value: "
								+ actualValue);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "The value in the CSS property does not equal the expected value. Locator: " + getLocatorValue(webElement)
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
				testLog(true,getCurrentMethodName() + "'" + selectedItem
						+ "' is selected. Locator: "+ getLocatorValue(webElement) );
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "'"
						+ selectedItem + "' is NOT selected. Locator: "+ getLocatorValue(webElement) );
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
				testLog(true,getCurrentMethodName() + "'" + selectedItem
						+ "' is selected. Locator: "+ getLocatorValue(webElement) );
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "'"
						+ selectedItem + "' is NOT selected. Locator: "+ getLocatorValue(webElement) );
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
				testLog(true,getCurrentMethodName() + "'" + selectedItem
						+ "' is selected. Locator: "+ getLocatorValue(webElement) );
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "'"
						+ selectedItem + "' is NOT selected. Locator: " + getLocatorValue(webElement));
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
				testLog(true,getCurrentMethodName() + "'" + selectedItem
						+ "' is selected. Locator: " + getLocatorValue(webElement));
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "'"
						+ selectedItem + "' is NOT selected. Locator: " + getLocatorValue(webElement));
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyElementAbsenceInsideParentElement(WebElement parentWebElement, WebElement childWebElement) {

		try {
			if (parentWebElement.findElement((By) childWebElement) != null) {
				testLog(true,getCurrentMethodName() + "Element "+getLocatorValue(childWebElement) + "absent inside parent element "+getLocatorValue(parentWebElement)); 
			}
			else
			testLogAndContinue(false,getCurrentMethodName() + "Element "+getLocatorValue(childWebElement) + "present inside parent element "+getLocatorValue(parentWebElement));
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}
	
	public void verifyElementPresenceInsideParentElement(WebElement parentWebElement, WebElement childWebElement) {
		try {
			if (parentWebElement.findElement((By) childWebElement) != null) {
				testLogAndContinue(true,getCurrentMethodName() + "Element "+getLocatorValue(childWebElement) + "present inside parent element "+getLocatorValue(parentWebElement));
			}
			else
				testLog(false,getCurrentMethodName() + "Element "+getLocatorValue(childWebElement) + "absent inside parent element "+getLocatorValue(parentWebElement)); 
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}
	}

	public void verifyElementIsNotDisplayed(WebElement webElement) {
		try {
			if (webElement.isDisplayed()) {
				this.highlightElement(webElement);
				testLogAndContinue(false,getCurrentMethodName() + "Element found on the page when not expected. Locator: "
						+ getLocatorValue(webElement));
			} else {
				testLog(true,getCurrentMethodName() + "Element found in the DOM but not displayed. Locator: "
						+ getLocatorValue(webElement));
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyElementIsNotDisplayed(WebElement webElement, int index) {
		try {
			WebElement element = getIndexedWebElement(webElement, index);
			if (element.isDisplayed()) {
				this.highlightElement(element);
				testLogAndContinue(false,getCurrentMethodName() + "Element found on the page when not expected. Locator: "
						+ getLocatorValue(webElement));
			} else {
				testLogAndContinue(true,getCurrentMethodName() + "Element found in the DOM but not displayed. Locator: "
						+ getLocatorValue(webElement));
			}
		
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyElementOccurenceCount(WebElement webElement, int count) {
		try {
			if (getElementCount(webElement)== count) {
				testLog(true,getCurrentMethodName() + "Element occurrence count is correct. Locator: "
						+ getLocatorValue(webElement) + " Count: " + count);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "Element occurrence count is INCORRECT. Locator: "
						+ getLocatorValue(webElement) + " Expected count: " + count + ", Actual count: "
								+ getElementCount(webElement));
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}


	public void verifyElementIsDisplayed(WebElement webElement) {
		try {
			if (webElement.isDisplayed()) {
				this.highlightElement(webElement);
				testLog(true,getCurrentMethodName() + "Element found. Locator: "+ getLocatorValue(webElement) );
			} else {
				testLogAndContinue(false,getCurrentMethodName()+"Element NOT found. Locator: "+ getLocatorValue(webElement) );
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyElementIsDisplayed(WebElement webElement, int index) {
		try {
			WebElement element = getIndexedWebElement(webElement, index);
			if (element.isDisplayed()) {
				this.highlightElement(element);
				testLog(true,getCurrentMethodName() + "Element found. Locator " + getLocatorValue(webElement));
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyEqual(double valueA, double valueB, String verificationDescription) {
		if (valueA == valueB) {
			testLog(true,getCurrentMethodName() + "" + verificationDescription
					+ ": value '" + valueA + "' is equal to value '" + valueB + "'");
		} else {
			testLogAndContinue(false,getCurrentMethodName() + "" + verificationDescription
					+ ": value '" + valueA + "' is NOT equal to value: " + valueB + "'");
		}

	}

	public void verifyEqual(int valueA, int valueB, String verificationDescription) {
		if (valueA == valueB) {
			testLog(true,getCurrentMethodName() + "" + verificationDescription
					+ ": value '" + valueA + "' is equal to value '" + valueB + "'");
		} else {
			testLogAndContinue(false,getCurrentMethodName() + "" + verificationDescription
					+ ": value '" + valueA + "' is NOT equal to value: " + valueB + "'");
		}

	}


	public void verifyGreater(int expectedGreater, int expectedLess, String verificationDescription) {
		if (expectedGreater > expectedLess) {
			testLog(true,getCurrentMethodName() + "" + verificationDescription
					+ ": value: " + expectedGreater + " is greater than value: " + expectedLess);
		} else {
			testLogAndContinue(false,getCurrentMethodName() + "" + verificationDescription + ": value: " + expectedGreater
							+ " is NOT greater value: " + expectedLess);
		}

	}


	public void verifyGreaterOrEqual(int expectedGreaterOrEqual, int expectedLessOrEqual,
			String verificationDescription) {
		if (expectedGreaterOrEqual >= expectedLessOrEqual) {
			testLog(true,getCurrentMethodName() + "" + verificationDescription + ": value '" + expectedGreaterOrEqual
							+ "' is equal to or greater than value '" + expectedLessOrEqual + "'");
		} else {
			testLogAndContinue(false,getCurrentMethodName() + "" + verificationDescription + ": value '" + expectedGreaterOrEqual
							+ "' is NOT equal or greater than value '" + expectedLessOrEqual + "'");
		}

	}



	public void verifyEqual(String valueA, String valueB, boolean enableCaseSensitiveVerification,
			String verificationDescription) {
		try {
			if (enableCaseSensitiveVerification) {
				if (valueA.equals(valueB)) {
					testLog(true,getCurrentMethodName() + "" + verificationDescription
							+ ": value '" + valueA + "' is equal to value '" + valueB + "'");
				} else {
					testLogAndContinue(false,getCurrentMethodName() + "" + verificationDescription + ": value '" + valueA
									+ "' is NOT equal to value '" + valueB + "'");
				}
			} else if (valueA.equalsIgnoreCase(valueB)) {
				testLog(true,getCurrentMethodName() + "" + verificationDescription
						+ ": value '" + valueA + "' is equal to value '" + valueB + "'");
			} else {
				testLogAndContinue(false,getCurrentMethodName() + ""
						+ verificationDescription + ": value '" + valueA + "' is NOT equal to value '" + valueB + "'");
			}
		} catch (Exception e) {
			testLogAndContinue(false,e.getStackTrace().toString());
		}

	}

	public void verifyIsNotEqual(int valueA, int valueB, String verificationDescription) {
		try {
			if (valueA != valueB) {
				testLog(true,getCurrentMethodName() + "" + verificationDescription
						+ ": value '" + valueA + "' is NOT equal to value '" + valueB + "'");
			} else {
				testLogAndContinue(false,getCurrentMethodName() + ""
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
					testLog(true,getCurrentMethodName() + "" + verificationDescription + ": value '" + valueA
									+ "' is NOT equal to value '" + valueB + "'");
				} else {
					testLogAndContinue(false,getCurrentMethodName() + ""
							+ verificationDescription + ": value '" + valueA + "' is equal to value '" + valueB + "'");
				}
			} else if (!valueA.equalsIgnoreCase(valueB)) {
				testLog(true,getCurrentMethodName() + "" + verificationDescription
						+ ": value '" + valueA + "' is NOT equal to value '" + valueB + "'");
			} else {
				testLogAndContinue(false,getCurrentMethodName() + ""
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
				testLogAndContinue(false,getCurrentMethodName() + "'"
						+ item + "' does  exist in the drop-down list:-> " );
			} else {
				testLog(true,getCurrentMethodName() + "'" + item
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
				testLog(true,getCurrentMethodName() + "'" + item
						+ "' exists in the drop-down list:-> " );
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "'"
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
				testLog(true, getCurrentMethodName() + "Title matches, Title = " + actualTitle);
			} else {
				testLogAndContinue(false,getCurrentMethodName()+"Title doesn't match: Expected Title = " + expectedTitle + ", Actual Title = " + actualTitle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyRadioButtonIsNotSelected(WebElement webElement) {
		try {
			this.highlightElement(webElement);
			if (!webElement.isSelected()) {
				testLog(true,getCurrentMethodName() + "The radio button is NOT selected. Locator: "
						+ getLocatorValue(webElement));
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "The radio button is selected. Locator: "
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
				testLog(true,getCurrentMethodName() + "The radio button is NOT selected. Locator: "
						+ getLocatorValue(webElement) + ", Index: " + index);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "The radio button isselected. Locator: "
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
				testLog(true,getCurrentMethodName() + "The radio button is selected. Locator: "
						+ getLocatorValue(webElement));
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "The radio button is NOT selected. Locator: "
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
				testLog(true,getCurrentMethodName() + "The radio button is selected. Locator: "
						+ getLocatorValue(webElement) + ", Index: " + index);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "The radio button is NOT selected. Locator: "
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
					testLog(true,getCurrentMethodName() + "The text matches the expected value. Text: " + expected);
				} else {
					testLogAndContinue(false,getCurrentMethodName() + "The text does NOT match the expected value. Actual: " + actual + ", Expected: "
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
				testLogAndContinue(false,getCurrentMethodName() + "The element does NOT exist on the page. Locator: "
						+ getLocatorValue(webElement));
				return;
			}

			for (int i = 0; i < elements.size(); ++i) {
				if (((WebElement) elements.get(i)).isDisplayed()) {
					retrievedText = ((WebElement) elements.get(i)).getText().trim();
					if (retrievedText.contains(verificationText.trim())) {
						this.highlightElement((WebElement) elements.get(i));
						testLogAndContinue(false,getCurrentMethodName() + "The text was found on the Locator: "
								+ getLocatorValue(webElement) + ", Parameter text: " + verificationText
										+ ". Text on locator:" + retrievedText);
						return;
					}

					flag = true;
				}
			}

			if (flag) {
				testLog(true,getCurrentMethodName() + "The text was not found on the Locator: "
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
				testLogAndContinue(false,getCurrentMethodName() + "The element does NOT exist on the page. Locator: "
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
					testLog(true,getCurrentMethodName() + "The text verified successfully. Locator: "
							+ getLocatorValue(webElement) + " text: " + verificationText);
					break;
				}
			}

			if (!found) {
				this.highlightElement((WebElement) texts.get(i));
				testLogAndContinue(false,getCurrentMethodName() + "Text verification. Locator: "
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
				testLog(true,getCurrentMethodName() + "Locator: "+ getLocatorValue(webElement)  + " having text: "
								+ actualText + ", contains text:" + verificationText);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "Locator: "  + " having text: "
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
				testLog(true,getCurrentMethodName() + "Text Verification passed. Locator: "
						+ getLocatorValue(webElement) + ", Regular Expression: " + expectedTextExpression
								+ ", Text: " + text1);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "Text Verification failed. Locator: "
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
				testLog(true,getCurrentMethodName() + "Text found. Text-> " + expectedText);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "Text NOT found. Text-> " + expectedText);
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
				testLogAndContinue(false,getCurrentMethodName() + "The element does NOT exist on the page. Locator: "
						+ getLocatorValue(webElement));
				return;
			}

			for (int i = 0; i < elements.size(); ++i) {
				if (((WebElement) elements.get(i)).isDisplayed()) {
					retrievedText = ((WebElement) elements.get(i)).getText().trim();
					if (retrievedText.equals(verificationText.trim())) {
						this.highlightElement((WebElement) elements.get(i));
						testLogAndContinue(false,getCurrentMethodName() + "The text was found on the Locator: "
								+ getLocatorValue(webElement)+ ", Parameter text: " + verificationText
										+ ". Text on locator:" + retrievedText);
						return;
					}

					flag = true;
				}
			}

			if (flag) {
				testLog(true,getCurrentMethodName() + "The text was not found on the Locator: " 
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
				testLogAndContinue(false,getCurrentMethodName() + "The element does NOT exist on the page. Locator: "
						+ getLocatorValue(webElement) + " text: " + verificationText);
				return;
			}

			int i;
			for (i = 0; i < texts.size(); ++i) {
				retrievedText = ((WebElement) texts.get(i)).getText().trim();
				if (retrievedText.equalsIgnoreCase(verificationText.trim()) & ((WebElement) texts.get(i)).isDisplayed()) {
					flag = "1";
					this.highlightElement((WebElement) texts.get(i));
					testLog(true,getCurrentMethodName() + "The text verified successfully. Locator: "
							+ getLocatorValue(webElement) + " text: " + verificationText);
					break;
				}
			}

			if (flag.contains("0")) {
				this.highlightElement((WebElement) texts.get(i));
				testLogAndContinue(false,getCurrentMethodName() + "Text verification. Locator: " 
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
				testLog(true,getCurrentMethodName() + "Locator: "  + " having text: " + actualText
								+ ", matches text:" + verificationText);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "Locator: "  + " having text: " + actualText
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
				testLog(true,getCurrentMethodName() + "Locator: " + getLocatorValue(webElement) + " having text: "
								+ actualText + ", contains text:" + verificationText);
			} else {
				testLogAndContinue(false,getCurrentMethodName() + "Locator: " + getLocatorValue(webElement) + " having text: "
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
				testLogAndContinue(false,getCurrentMethodName() + ""
						+ valueToMatch[i] + " is not in list. Locator: " );
			}
		}

		if (flag) {
			testLog(true,getCurrentMethodName() + "All values are included in the Drop Down List. Locator: ");
		}

	}
	public String getCurrentMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName() + ": ";
	}
}
