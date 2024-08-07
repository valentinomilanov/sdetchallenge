package com.fetch.sdetchallenge.pageobjects.base;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for all page objects. Provides common functionality for page interactions,
 * such as element waits and logger initialization.
 */
public class BasePage {
	
	/** Logger instance for logging events and messages. */
	protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
	
	/** WebDriver instance used for interacting with the web browser. */
	protected WebDriver driver;
	
	/** Default wait time for elements to appear or disappear. */
	protected static final Duration DEFAULT_WAIT_TIME_IN_MILLIS = Duration.ofMillis(15000);
	
	
	/**
     * Constructs an instance of BasePage.
     * 
     * @param driver the WebDriver instance used to interact with the web browser.
     */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
     * Waits for an element specified by the given selector to become invisible.
     * 
     * @param selector the By locator used to find the element to wait for.
     */
	public void waitFoElemetToDissapear(By selector) {
		 WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME_IN_MILLIS);
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
	}
	
	/**
     * Checks if an alert is present.
     * 
     * @param driver the WebDriver instance.
     * @return true if an alert is present, false otherwise.
     */
    public boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            logger.info("Alert is present");
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    
    public String getAlertMessageandAccept(WebDriver driver) {
    	Alert alert = driver.switchTo().alert();
    	String alertText = alert.getText();
    	logger.info("Alert text: " + alertText);
    	System.out.println("Alert text: " + alertText);
    	logger.info("Click on OK Alert button");
    	alert.accept();
    	return alertText;
    }

}
