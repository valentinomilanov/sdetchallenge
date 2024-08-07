package com.fetch.sdetchallenge.tests.base;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fetch.sdetchallenge.pageobjects.base.BasePage;
import com.fetch.sdetchallenge.pageobjects.pages.SdetchallengePage;

/**
 * Base class for test setup and teardown.
 * Provides methods for initializing and closing the WebDriver, and for opening specific pages.
 */
public class TestBase {
	
	/** WebDriver instance used for browser automation. */
	protected WebDriver driver;
	
	/** Logger instance for logging test setup and teardown events. */
	public static final Logger logger = LoggerFactory.getLogger(BasePage.class);

	/**
     * Sets up the WebDriver and initializes ChromeDriver before each test.
     * Configures ChromeDriver with necessary options.
     */
    @BeforeEach
    public void setUp() {
        logger.info("Setting up the WebDriver");
        // Set the path to the chromedriver executable
        String driverPath = new File("." + File.separator + "src" + File.separator + "test" + File.separator +"resources" + File.separator + "driver" + File.separator + "chromedriver.exe").getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", driverPath);
        
        // Set Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--start-maximized");

        // Initialize ChromeDriver
        driver = new ChromeDriver(options);
        logger.info("WebDriver initialized");
    }
    
    /**
     * Closes the WebDriver after each test.
     */
    @AfterEach
    public void tearDown() {
        logger.info("Tearing down the WebDriver");
        // Close the browser
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed");
        }
    }
    
    /**
     * Opens the SDET Challenge page and returns an instance of {@link SdetchallengePage}.
     * 
     * @return an instance of {@link SdetchallengePage} for interacting with the page.
     */
    public SdetchallengePage openSdetchallengePage() {
		driver.get("http://sdetchallenge.fetch.com/");
		logger.info("Opened http://sdetchallenge.fetch.com");
		return new SdetchallengePage(driver);
	}
    
    

}
