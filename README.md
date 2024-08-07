# Fetch Coding Exercise - SDET

## Overview

This project is a Java Maven-based Selenium automation framework designed to solve a balance scale challenge. It utilizes Selenium WebDriver for browser automation, JUnit for testing, and SLF4J with Logback for logging.

## Project Structure
```sh
sdetchallenge/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── fetch/
│   │               └── sdetchallenge/
│   │                   └── pageobjects/
│   │                       └── base/
│   │                       └── pages/
│   ├── test/
│   │   └── java/
│   │       └── com/
│   │           └── fetch/
│   │               └── sdetchallenge/
│   │                   └── tests/
│   │                       └── base/
│   │                       └── BalanceScaleTest.java
│   └── resources/
│       └── driver/
│           └── chromedriver.exe
│       └── logback.xml
└── pom.xml
```

## Maven Configuration

The project uses Maven for dependency management and build automation. Key dependencies include:

- **Selenium WebDriver**: For interacting with web browsers.
- **JUnit**: For writing and executing tests.
- **SLF4J**: For logging abstraction.
- **Logback**: For logging implementation.

**Key Properties:**

- Java Version: 17
- Selenium Version: 4.4.0
- JUnit Version: 5.10.2
- Logback Version: 1.4.6
- SLF4J Version: 2.0.7

For a detailed view of all dependencies and plugins, refer to the `pom.xml` file in the repository.

## Logging Configuration

Logging is managed using Logback. The logging configuration is set up to output logs to the console with the following settings:

- **Log Level**: Trace
- **Log Pattern**: Includes timestamp, log level, logger name, and message.

To view the complete logging configuration, check the `logback.xml` file in the repository.

## Page Objects

### BasePage

- **Purpose**: Provides common functionalities for interacting with web elements, such as waiting for elements to become invisible and handling browser alerts.
- **Key Methods**:
  - `waitForElementToDisappear(By selector)`: Waits for an element to become invisible.
  - `isAlertPresent(WebDriver driver)`: Checks if an alert is present.
  - `getAlertMessageAndAccept(WebDriver driver)`: Retrieves and accepts an alert message.

### SdetchallengePage

- **Purpose**: Represents the SDET Challenge page and provides methods to interact with its elements and perform actions related to the challenge.
- **Key Methods**:
  - `clickOnResetButton()`: Clicks the reset button on the page.
  - `clickOnWeighButton()`: Clicks the weigh button and waits for the result to appear.
  - `fillBowlsWithBarNumbers(List<Integer> leftBars, List<Integer> rightBars)`: Fills bowls with specified numbers of gold bars.
  - `findFakeBar()`: Finds the fake gold bar using measurements.

## Test Base

### TestBase

- **Purpose**: Sets up and tears down the WebDriver for test execution.
- **Key Methods**:
  - `setUp()`: Initializes the ChromeDriver and configures its options.
  - `tearDown()`: Closes the WebDriver after tests.
  - `openSdetchallengePage()`: Opens the SDET Challenge page and returns an instance of `SdetchallengePage`.

## Test Cases

### BalanceScaleTest

- **Purpose**: Verifies the functionality of the balance scale challenge.
- **Test Method**:
  - `findFakeGoldBar()`: Opens the challenge page, finds the fake gold bar, selects it, and verifies the alert message.

## Running the Tests

1. **Set Up**: Ensure that all dependencies are installed and configured as per the `pom.xml` file.
2. **Execute Tests**: Run the tests using Maven with the command:
   ```sh
   mvn test
## Documentation

[Documentation](https://github.com/valentinomilanov/sdetchallenge/raw/main/Documentation/Fetch_Coding_Exercise_SDET_Documentation.docx)

