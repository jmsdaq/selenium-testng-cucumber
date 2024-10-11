# Selenium TestNG Cucumber Project

## Overview
This project uses Cucumber, Selenium, and TestNG, focusing on Behavior-Driven Development (BDD) and the Page Object Model (POM). Cucumber allows for plain language specifications, promoting collaboration, while POM enhances code organization. TestNG facilitates streamlined test execution and reporting, creating an efficient testing framework.

## Supports
- Page Object Model
- Page Context (DI)
- Headless Run
- Data-Driven Testing (DDT)
- Tagging Tests
- Failed Screenshot
- Chaining Methods
- Parallel Test
- Allure Report


## Setups
- Install **Selenium WebDriver**.   
- Configure **TestNG** in `testng.xml`.
- Write feature files in **Cucumber**.
- Implement **Page Object Model (POM)**.
- Use **Hooks** for setup and teardown.

## Prerequisites
- Java JDK 8 or higher
- Maven
- IDE (IntelliJ IDEA, Eclipse)
- WebDriver (e.g., ChromeDriver)

## Page Object Model
The Page Object Model (POM) is implemented to create an object repository for web UI elements. Each page of the application is represented by a class, which contains methods that correspond to the actions that can be performed on that page.

For example, a login page class might look like this:
```
public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "username") private WebElement usernameField;
    @FindBy(id = "password") private WebElement passwordField;
    @FindBy(id = "loginButton") private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage enterCredentials(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        return this;
    }

    public HomePage clickLogin() {
        loginButton.click();
        return new HomePage(driver);
    }
}

// Usage
new LoginPage(driver)
    .enterCredentials("user1", "pass1")
    .clickLogin();
```
In this example, methods return the current instance, allowing for a smooth sequence of actions. POM enhances clarity and maintainability, making it easier to adapt to UI changes.

## Tagging Tests
Cucumber allows for tagging tests to organize and run specific scenarios. Tags can be added in feature files and can be executed with the command line using:
```
cucumber --tags @tagName
```

## Headless Run
Tests can be executed in headless mode using the Chrome or Firefox driver. To run tests in headless mode, configure the WebDriver options in your test runner setup:
```
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless");
WebDriver driver = new ChromeDriver(options);

```

## Allure Report
Allure framework is integrated for generating detailed reports. To view reports, follow these steps:

1. Ensure Allure dependencies are included in your `pom.xml`.
2. Run tests with the Allure listener.
3. Generate the report with:
```
mvn allure:report
mvn allure:serve
```
## Failed Screenshot
Screenshots can be captured on test failure for better debugging. Utilize WebDriver's `getScreenshotAs()` method in your `@AfterMethod` hook:
```
if (result.getStatus() == ITestResult.FAILURE) {
    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(src, new File("path/to/screenshot.png"));
}
```
## Chaining method
Method chaining is a programming technique where multiple method calls are made in a single statement. This is particularly useful in POM as it can lead to more readable and concise code. In this project, methods in page classes can return `this`, allowing for easy chaining of actions.

For instance, in a login page class, methods can be designed to return the current instance of the class, allowing the next method to be called directly on the same object:
```
public class LoginPage {
    public LoginPage enterUsername(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public HomePage clickLogin() {
        loginButton.click();
        return new HomePage();
    }
}
```
Using chaining in your test code makes it more fluent and easier to understand:
```
LoginPage loginPage = new LoginPage(driver);
loginPage.enterUsername("user1")
          .enterPassword("pass1")
          .clickLogin();
```
This technique enhances code readability and reduces boilerplate code, making it easier to write and maintain tests.

## Running Tests in Parallel with TestNG
TestNG supports parallel test execution, making your tests run faster. You can set this up in the testng.xml file by specifying how you want the tests to run.
Example: `testng.xml`
```
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="ParallelTests" parallel="methods" thread-count="5">
    <test name="Test1">
        <classes>
            <class name="com.example.tests.LoginTest"/>
        </classes>
    </test>
    <test name="Test2">
        <classes>
            <class name="com.example.tests.ProductTest"/>
        </classes>
    </test>
</suite>
```
In this configuration:

- `parallel="methods"` enables parallel execution of test methods within classes.
- `thread-count="5"` specifies that up to 5 threads can run simultaneously.
This setup allows for multiple tests to be executed concurrently, improving overall test execution time while maintaining test isolation.
### Clone the Repository
```bash
git clone https://github.com/username/repository.git
cd repository
```

## Installation
1. Navigate to the project directory.

2. Install project dependencies using Maven:
```bash
mvn clean install
```
3. Configure any required environment variables or settings in your `config.properties` file.

## Report
After running tests, you can generate and view Allure reports to analyze results and failures. To generate the report:

1. Run your tests using:
```bash
mvn test
```
2. Generate the Allure report:
```bash
mvn allure:report
```
3. Serve the report:
```bash
mvn allure:serve
```
The reports will provide insights into passed, failed, and skipped tests along with detailed information on each test case.