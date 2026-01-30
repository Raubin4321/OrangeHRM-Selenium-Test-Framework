package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class HomePage {

	private ActionDriver actionDriver;

	// Define locators using By class
	private By adminTab = By.xpath("//span[text()='Admin']");
	private By UserIdButton = By.className("oxd-userdropdown-name");
	private By logoutButton = By.xpath("//a[text()='Logout']");
	private By orangeHRMLogo = By.xpath("//div[@class='oxd-brand-banner']//img");

	private By pimTab = By.xpath("//span[text()='PIM']");
	private By employeeSearch = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div/div/div/input");
	private By searchButton = By.xpath("//button[@type='submit']");
	private By empFirstAndMiddleName = By.xpath("//div[@class='oxd-table-card']/div/div[3]");
	private By lastName = By.xpath("//div[@class='oxd-table-card']/div/div[4]");

	// Initialize the ActionDriver object by passing WedDriver instance
	/*
	 * public HomePage(WebDriver driver) { this.actionDriver = new
	 * ActionDriver(driver); }
	 */

	public HomePage(WebDriver driver) {
		this.actionDriver = BaseClass.getActionDriver();
	}

	// Method to verify if admin tab is visible
	public boolean isAdminTabVisible() {
		return actionDriver.isDisplayed(adminTab);
	}

	// Method to verify OrangeHRM logo
	public boolean verifyOrangeHRMLogo() {
		return actionDriver.isDisplayed(orangeHRMLogo);
	}

	// Method to navigate to PIM tab
	public void clickOnPIMTab() {
		actionDriver.click(pimTab);
	}

	// Method for Employee Search
	public void employeeSearch(String value) {
		actionDriver.enterText(employeeSearch, value);
		actionDriver.click(searchButton);
		actionDriver.scrollToElement(empFirstAndMiddleName);
	}

	// Verify first and middle name
	public boolean verifyEmployeeFirstAndMiddleName(String empFirstAndMiddleNameFromDB) {
		return actionDriver.compareText(empFirstAndMiddleName, empFirstAndMiddleNameFromDB);
	}

	// Verify last name
	public boolean verifyEmployeeLastName(String empLastNameFromDB) {
		return actionDriver.compareText(lastName, empLastNameFromDB);
	}

	// Method to perform logout operation
	public void logout() {
		actionDriver.click(UserIdButton);
		actionDriver.click(logoutButton);
	}

}
