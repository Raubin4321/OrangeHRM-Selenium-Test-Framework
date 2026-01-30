package com.orangehrm.test;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.DBConnection;
import com.orangehrm.utilities.DataProviders;
import com.orangehrm.utilities.ExtentManager;

public class DBVerificationTest extends BaseClass {
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}
	
	@Test(dataProvider = "empVerification", dataProviderClass = DataProviders.class)
	public void verifyEmployeeInformationFromDB(String empId, String empName) {
		
		SoftAssert softAssert = getSoftAssert();
		
		ExtentManager.logStep("Login with Admin credentials ");
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
		ExtentManager.logStep("Click on PIM Tab");
		homePage.clickOnPIMTab();
		
		ExtentManager.logStep("Search for Employee");
		homePage.employeeSearch(empName);
		
		ExtentManager.logStep("Get the Employee Name from DB");
		String employee_id = empId;
		
		// Fetch the data into map
		Map<String, String> employeeDetails = DBConnection.getEmployeeDetails(employee_id);
		String empFirstName = employeeDetails.get("firstName");
		String empMiddleName = employeeDetails.get("middleName");
		String empLastName = employeeDetails.get("lastName");
		
		String empFirstAndMiddleName = (empFirstName + " " + empMiddleName).trim();
		
		// Validation for first and middle name
		ExtentManager.logStep("Verify the employee first and middle name ");
		softAssert.assertTrue(homePage.verifyEmployeeFirstAndMiddleName(empFirstAndMiddleName),"First and Middle name are not matching");
		
		// Validation for last name
		ExtentManager.logStep("Verify the employee last name ");
		softAssert.assertTrue(homePage.verifyEmployeeLastName(empLastName));
		
		ExtentManager.logStep("DB Validation Completed! ");
		
		softAssert.assertAll();
	}

}
