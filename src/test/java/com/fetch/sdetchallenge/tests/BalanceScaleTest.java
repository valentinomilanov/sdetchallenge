package com.fetch.sdetchallenge.tests;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fetch.sdetchallenge.pageobjects.pages.SdetchallengePage;
import com.fetch.sdetchallenge.tests.base.TestBase;

/**
 * Test class for verifying the functionality of the balance scale challenge.
 * Extends the base test class to inherit common setup and teardown functionalities.
 */
public class BalanceScaleTest extends TestBase{
	
	private SdetchallengePage sdetchallengePage;
	private String fakeBarFoundMessage = "Yay! You find it!";
	private String fakeBarNotFoundMessage = "Oops! Try Again!";
	
	/**
     * Tests the functionality of finding a fake gold bar.
     * <p>
     * This test performs the following steps:
     * <ol>
     *     <li>Opens the SDET Challenge page.</li>
     *     <li>Finds the fake gold bar and verifies that it is found.</li>
     *     <li>Selects the fake gold bar and checks if the alert is present.</li>
     *     <li>Verifies that the alert message is correct.</li>
     *     <li>Prints out the list of weighings for review.</li>
     * </ol>
     * </p>
     */
	@Test
	public void findFakeGoldBar() {
		sdetchallengePage = openSdetchallengePage();
		int fakeBar = sdetchallengePage.findFakeBar();
        System.out.println("\033[1;32mFake bar found! It is #" + fakeBar + "! \033[0m");

        // Assert result
        assertNotEquals(-1, fakeBar, "Fake bar should be found");

        // Select the fake bar
        sdetchallengePage.selectGoldBar(fakeBar);
        
        logger.info("Verify if Alert is present");
        Assertions.assertTrue(sdetchallengePage.isAlertPresent(driver), "Alert is not present");
        
        logger.info("Verify if Alert message correct is");
        Assertions.assertEquals(fakeBarFoundMessage, sdetchallengePage.getAlertMessageandAccept(driver), "Alert message is not correct");
        
        StringBuilder result = new StringBuilder("Weighings:\n");
        for(String  weighing: sdetchallengePage.getWeighings()) {
            result.append(weighing).append("\n");
        }
        logger.info("Print Weighings:");
        System.out.println("\033[1;32m" + result.toString() + "\033[0m");
	}

}
