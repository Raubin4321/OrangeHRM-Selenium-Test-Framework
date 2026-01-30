package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class LoginPage {
	
	private ActionDriver actionDriver;
	
	//Define locators using By class
	
	private By userNameField = By.name("username");
	private By passwordField = By.name("password");
	private By loginButton = By.cssSelector("button[type='submit']");
	private By errorMessage = By.xpath("//p[text()='Invalid credentials']");
	
	//Initialize the ActionDriver object by passing WedDriver instance
	/* public LoginPage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	} */
	
	public LoginPage(WebDriver driver) {
		this.actionDriver = BaseClass.getActionDriver();
	}
	
	//Method to perform login
	public void login(String userName, String password) {
		
		actionDriver.enterText(userNameField, userName);
		actionDriver.enterText(passwordField, password);
		actionDriver.click(loginButton);
	}
	
	//Methiod to check if error message is displayed
	public boolean isErrorMessageDisplayed() {
		
		return actionDriver.isDisplayed(errorMessage);
	}
	
	//Method to get the text from Error Message
	public void getErrorMessageText() {
		
		System.out.println(actionDriver.getText(errorMessage));
	}
	
	//Verify if error is correct or not
	public boolean verifyErrorMessage(String expectedError) {
		
		return actionDriver.compareText(errorMessage, expectedError);
	}

}
