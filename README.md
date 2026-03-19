**OrangeHRM Selenium Test Automation Framework**

This project is a Hybrid Test Automation Framework using Selenium WebDriver with Java for the OrangeHRM web application, focusing on login functionality validation using both positive and negative test scenarios.

Implemented Page Object Model (POM) and Data-Driven Testing to ensure scalability, maintainability, and reusability. Integrated Jenkins CI/CD for automated execution and Extent Reports for detailed reporting.

**Tech Stack**

Java, Selenium WebDriver
TestNG
Page Object Model (POM)
Data-Driven Testing (Excel)
Jenkins (CI/CD)
Extent Reports

**Key Features**

✔️ Hybrid Framework (POM + Data-Driven)
✔️ Login validation (valid & invalid credentials)
✔️ UI validation (Home page logo)
✔️ Reusable & scalable design
✔️ Extent Reports with screenshots
✔️ Jenkins integration for automated runs

**How to Run the Project**

1. Clone the repository:

git clone https://github.com/Raubin4321/OrangeHRM-Selenium-Test-Framework

2. Import the project into your IDE (Eclipse/IntelliJ)

3. Install dependencies :

mvn clean install

4. Run tests using:

TestNG XML file

Or directly from test classes

## 📂 Project Structure
```bash
OrangeHRM-Selenium-Test-Framework
│
├── src/main/java
│   ├── com/orangehrm/actiondriver
│   │   └── ActionDriver.java
│   │
│   ├── com/orangehrm/base
│   │   └── BaseClass.java
│   │
│   ├── com/orangehrm/listeners
│   │   └── TestListener.java
│   │
│   ├── com/orangehrm/pages
│   │   ├── HomePage.java
│   │   └── LoginPage.java
│   │
│   ├── com/orangehrm/utilities
│   │   ├── ApiUtility.java
│   │   ├── DataProviders.java
│   │   ├── DBConnection.java
│   │   ├── ExcelReaderUtility.java
│   │   ├── ExtentManager.java
│   │   ├── LoggerManager.java
│   │   └── RetryAnalyzer.java
│
├── src/main/resources
│   ├── config.properties
│   └── log4j2.xml
│
├── src/test/java
│   ├── com/orangehrm/test
│   │   ├── ApiTest.java
│   │   ├── DBVerificationTest.java
│   │   ├── HomePageTest.java
│   │   ├── LoginPageTest.java
│
├── src/test/resources
│   ├── ExtentReport
│   │   └── ExtentReport.html
│   ├── screenshots
│   ├── testdata
│   │   └── TestData.xlsx
│   └── testng.xml
```


