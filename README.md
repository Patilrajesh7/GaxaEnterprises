Selenium java POM Framework -Developed by Rajesh Patil

This is a Page Object Model (POM) based Selenium Test Automation Framework built using Java, TestNG, and Maven. The framework supports parallel test execution, screenshot capture, and is designed for scalability and maintainability.

HybridPOMFramework/ ├── src/ │ ├── main/ │ │ └── java/com/HybridPOMFramework/qa/ │ │ ├── base/ # TestBase class with WebDriver setup │ │ ├── pages/ # Page classes (LoginPage, HomePage) │ │ ├── config/ # config.properties │ │ └── utilities/ # Reusable utilities (timeouts, etc.) │ └── test/ │ └── java/com/HybridPOMFramework/qa/testcases/ │ └── LoginTest.java ├── screenshots/ # Stores captured screenshots ├── testng.xml # TestNG suite configuration └── README.md

-Technologies Used:-

Java 21
Selenium WebDriver
TestNG
Maven
Apache Commons IO
Log4j (optional)
-Features:

Page Object Model (POM) design pattern
Parallel test execution using TestNG
Automatic screenshots on test failure
Configurable via config.properties
Reusable utilities for waits and actions
Thread-safe WebDriver using ThreadLocal for parallel execution
Setup Instructions:-

Clone the repository 2.Install dependencies(mvn clean install) 3.Configure browser and test data
Run tests --Run with parallel execution:
-Best Practices Keep locators in Page classes, logic in tests Use ThreadLocal for parallel safety Use @BeforeMethod and @AfterMethod for test isolation Keep test data out of code (e.g., in .properties or external files)

-Author Developed by Rajesh Patil 📧 patilrajesh.official1@gmial.com
