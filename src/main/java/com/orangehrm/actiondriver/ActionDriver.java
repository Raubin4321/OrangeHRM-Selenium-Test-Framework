package com.orangehrm.actiondriver;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtentManager;

public class ActionDriver {
	
	private WebDriver driver;
	private WebDriverWait wait;
	public static final Logger logger = BaseClass.logger;
	
	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicitWait"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
		logger.info("WebDriver Instance created");
	}
	
	//Method to click an element
	public void click(By by) {
		String elementDescription = getElementDescription(by);
		try {
			waitForElementToBeClickable(by);
	        WebElement element = driver.findElement(by);

	        applyBorder(by, "green");
	        element.click();

	        ExtentManager.logStep("Clicked an element : " + elementDescription);
	        logger.info("Clicked an element --> " + elementDescription);
		} catch (Exception e) {
			applyBorder(by, "red");
			ExtentManager.logFailure(BaseClass.getDriver(), "Unable to click element ", elementDescription + "_unable to click");
			logger.error("Unable to click element : " + e.getMessage());
		}		
	}
	
	//Method to enter text into an input field
	public void enterText(By by, String value) {
		
		try {
			waitForElementToBeVisible(by);
			//driver.findElement(by).clear();
			//driver.findElement(by).sendKeys(value);
			WebElement element = driver.findElement(by);
			applyBorder(by, "green");
			element.clear();
			element.sendKeys(value);
			logger.info("Entered Text on " + getElementDescription(by) + " --> " + value);
		} catch (Exception e) {
			applyBorder(by, "red");
			logger.error("Unable the enter the value : " + e.getMessage());
		}
	}
	
	//Method to get text from an input field
	public String getText(By by) {
		
		try {
			waitForElementToBeVisible(by);
	        WebElement element = driver.findElement(by);

	        applyBorder(by, "green");
	        return element.getText();
		} catch (Exception e) {
			applyBorder(by, "red");
			logger.error("Unable to get the text : " + e.getMessage());
			return "";
		}
	}
	
	// Method to compare two text
	public boolean compareText(By by, String expectedText) {

		try {
			waitForElementToBeVisible(by);
			WebElement element = driver.findElement(by);
			String actualText = element.getText();
			
			if (expectedText.equals(actualText)) {
				applyBorder(by, "green");
				logger.info("Text are Matching : " + actualText + " equals " + expectedText);
				ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Text are Matching! ",
						"Text Verified Successfully! " + actualText + " equals " + expectedText);
				return true;
			} else {
				applyBorder(by, "red");
				logger.error("Text are not Matching : " + actualText + " not equals " + expectedText);
				ExtentManager.logFailure(BaseClass.getDriver(), "Text Comparison Failed! ",
						"Text Comparison Failed! " + actualText + " not equals " + expectedText);
				return false;
			}
		} catch (Exception e) {
			applyBorder(by, "red");
			logger.error("Unable to compare texts : " + e.getMessage());
		}
		return false;
	}
	
	/* Method to check if an element is displayed
	public boolean isDisplayed(By by) {
		
		try {
			waitForElementToBeVisible(by);
			boolean isDisplayed = driver.findElement(by).isDisplayed();
			if(isDisplayed) {
				System.out.println("Element is visible");
				return isDisplayed;
			} else {
				return isDisplayed;
			}
		} catch (Exception e) {
			System.out.println("Element is not displayed : " + e.getMessage());
			return false;
		}
	} */
	
	public boolean isDisplayed(By by) {

		try {
			waitForElementToBeVisible(by);
			WebElement element = driver.findElement(by);
			
			applyBorder(by, "green");
			logger.info("Element is displayed : " + getElementDescription(by));
			ExtentManager.logStep("Element is displayed : " + getElementDescription(by));
			ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Element is displayed ",
					"Element is displayed : " + getElementDescription(by));
			return element.isDisplayed();
		} catch (Exception e) {
			applyBorder(by, "red");
			logger.error("Element is not displayed : " + e.getMessage());
			ExtentManager.logFailure(BaseClass.getDriver(), "Element is not displayed ",
					"Element is not displayed " + getElementDescription(by));
			return false;
		}
	}
	
	//Scroll to an element
	public void scrollToElement(By by) {
		
		try {
			waitForElementToBeVisible(by);
			applyBorder(by, "green");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			applyBorder(by, "red");
			logger.error("Unable to locate element : " + e.getMessage());
		}
	}
	
	//Wait for the Page to load
	public void waitForPageLoad(int timeOutInSec) {
		
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> (JavascriptExecutor) WebDriver)
			.executeScript("return document.readyState").equals("complete");
			logger.info("Page loaded successfully.");
		} catch (Exception e) {
			logger.error("Page did not load within " + timeOutInSec + "seconds. Excetption : " + e.getMessage());
		}
	}
	
	//Wait for element to be clickable
	private void waitForElementToBeClickable(By by) {
		
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			logger.error("element is not clickable: " + e.getMessage());
		}
	}
	
	//Wait for element to be visible
	private void waitForElementToBeVisible(By by) {
		
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			logger.error("Element is not visible:" + e.getMessage());
		}				
	}
	
	//Method to get the description of an element using By locator
	public String getElementDescription(By locator) {
		//Check for null driver or locator to avoid NullPointer Exception
		if(driver == null)
			return "driver is not initialized";
		if(locator == null)
			return "locator is null";
		
		try {
			//Find the element using the locator
			WebElement element = driver.findElement(locator);
			
			//Get Element Attributes
			String name = element.getDomProperty("name");
			String id = element.getDomProperty("id");
			String text = element.getText();
			String className = element.getDomProperty("class");
			String placeHolder = element.getDomProperty("placeholder");
			
			//Return the description based on element attributes
			if(isNotEmpty(name)) {
				return "Element with name : " +name;
			} else if(isNotEmpty(id)) {
				return "Element with id : " +id;
			} else if(isNotEmpty(text)) {
				return "Element with text : " + truncate(text, 50);
			} else if(isNotEmpty(className)) {
				return "Element with className : " +className;
			} else if(isNotEmpty(placeHolder)) {
				return "Element with placeHolder : " +placeHolder;
			} else {
				return "Element located using : " + locator.toString();
			}
		} catch (Exception e) {
			logger.error("Unable to describe the element "+e.getMessage());
		}
		return "Unable to describe the element";
	}
	
	//Utility method for getElementDescription() method to check a String is not null or empty
	private boolean isNotEmpty(String value) {
		return value!=null && !value.isEmpty();
	}
	
	//Utility method to truncate long String
	private String truncate(String value, int maxLength) {
		if(value == null || value.length()<=maxLength) {
			return value;
		}
		return value.substring(0, maxLength)+"...";
	}
	
	// Utility Method to Border an element
	public void applyBorder(By by, String color) {
		try {
			waitForElementToBeVisible(by);
			// Locate the element
			WebElement element = driver.findElement(by);
			// Apply the border
			String script = "arguments[0].style.border='3px solid " + color + "'";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(script, element);
			logger.info("Applied the border with color " + color + " to element : " + getElementDescription(by));
		} catch (Exception e) {
			logger.warn("Failed to apply the border to an element : " + getElementDescription(by), e);
		}
	}

}
