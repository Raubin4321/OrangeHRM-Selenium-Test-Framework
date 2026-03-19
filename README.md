# 🧪 OrangeHRM Selenium Test Automation Framework

A **Hybrid Test Automation Framework** built using Selenium WebDriver and Java to validate the **OrangeHRM web application**, focusing on login functionality with both positive and negative scenarios.

---

## 🚀 Tech Stack

* **Language:** Java
* **Automation Tool:** Selenium WebDriver
* **Test Framework:** TestNG
* **Design Pattern:** Page Object Model (POM)
* **Framework Type:** Hybrid (POM + Data-Driven)
* **CI/CD:** Jenkins
* **Reporting:** Extent Reports

---

## ✅ Key Features

* ✔️ Hybrid framework design (POM + Data-Driven Testing)
* ✔️ Automated login validation (valid & invalid credentials)
* ✔️ UI validation (Home page logo verification)
* ✔️ Externalized test data using Excel
* ✔️ Extent Reports with screenshots for failures
* ✔️ Retry mechanism for flaky tests
* ✔️ Integrated with Jenkins for CI/CD execution

---

## ▶️ How to Run the Project

```bash
# Clone the repository
git clone https://github.com/Raubin4321/OrangeHRM-Selenium-Test-Framework

# Navigate to project directory
cd OrangeHRM-Selenium-Test-Framework

# Run tests
mvn clean test
```

### Run Options

* Execute using **testng.xml**
* Run directly from **TestNG test classes**
* Trigger execution via **Jenkins pipeline**

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


