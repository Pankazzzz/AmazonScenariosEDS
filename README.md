# Amazon Automation Framework

## Overview
This project is an automation framework designed using Selenium, Java, and TestNG to automate workflows on Amazon.

---

## 1. Framework Design

I have used selenium + java with testng to design a maven-based automation framework. The framework follows POM + Utility + Data-driven approach.

### Folder structure / Component details are as below :

#### src/main/java
- **CommonAppCheck**  
  contains reusable classes like assertions, soft assertions, and common validation methods  

- **DataResources**  
  classes related to data extraction and mapping from Excel/JSON based on method or test name  

- **ExcelUtilities**  
  utility classes to read/write Excel data  

- **PageObjectModels**  
  page classes representing UI behavior, all locators and actions are defined here. also created a common AmazonPage class to reduce redundant object creation  

- **Utilities**  
  reusable functions like waits, actions, javascript executor methods, scrolling, etc.  

---

#### src/main/resources
- **log4j2.xml**  
  used for logging execution details in test methods  

---

#### src/test/java
- **BasePackage**  
  contains baseclass for driver initialization, teardown, and common reusable methods like screenshots and window handling  

- **BaseUtilities**  
  contains listeners, extendt report, and retryanalazer for reporting and failure handling  

- **DataReaderFunctions**  
  used to read data from JSON, Excel, or DataProvider  

- **SmokeTest**  
  test classes – one for login and one for lowest/highest price scenario, have also kept one standalone class which i wrote first and took reference for POM  

---

#### Reports
- extent report is generated after execution  
- snapshot are captured on failure using listener logic  

---

## 2. OOP Concepts

I have applied all 4 OOP principles in my framework:

- **Abstraction**  
  User interacts only with methods (like searchProduct, login), actual implementation is hidden inside page classes  

- **Encapsulation**  
  All elements and methods are grouped inside POM classes, data is accessed only through methods  

- **Inheritance**  
  page classes extend AppUtilities to reuse common functions, test classes extend BaseClass for driver setup and teardown  

- **Polymorphism**  
  used runtime polymorphism by declaring webdriver as a reference and initializing different browser drivers (chrom driver, edge driver) at runtime, also overridden some utility methods where custom behavior was required  

---

## 3. Handling Dynamic Elements

I have handled dynamic elements using below approaches:

- used relative XPath and dynamic locators like contains() , translate() and starts-with()  
- applied explicit waits to handle dynamic loading of elements  
- used try-catch blocks to handle missing elements (like products without price)  
- implemented filtering logic to skip irrelevant items like sponsored products  
- used scrolling and actions to handle lazy-loaded elements  

---

## 4. Parallel Execution

I have enabled parallel execution using testng XML configuration with:
parallel="tests" thread-count="2"
Also implemented ThreadLocal WebDriver to ensure each test runs with its own driver instance so conflict between parallel threads  

---

## 5. Test Data Handling

I designed test data handling using:

- json for structured data  
- excel for tabular data  
- testng inbuilt dataprovider for parameterization  

Also, config file is used for environment-specific values  

Framework can be extended to use DB data  
