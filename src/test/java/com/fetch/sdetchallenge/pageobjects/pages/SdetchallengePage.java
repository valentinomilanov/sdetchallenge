package com.fetch.sdetchallenge.pageobjects.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.fetch.sdetchallenge.pageobjects.base.BasePage;

/**
 * Page object representing the SDET Challenge page.
 * Provides methods to interact with the page elements and perform actions related to the challenge.
 */
public class SdetchallengePage extends BasePage {

	@FindBy(css = "button[id='reset']:not([disabled])")
	private WebElement resetButton;

	@FindBy(id = "weigh")
	private WebElement weighButton;

	@FindBy(css = ".result > button[id='reset']")
	private WebElement resultButton;

	@FindBy(css = "input[data-side='left']")
	private List<WebElement> leftBowlInputs;

	@FindBy(css = "input[data-side='right']")
	private List<WebElement> rightBowlInputs;

	@FindBy(css = ".coins>button")
	List<WebElement> goldBars;
	
	@FindBy(css = ".game-info li")
	List<WebElement> weighings;

	By emptyResult = By.xpath("//div[contains(@class, 'result')]//button[text()='?']");

	 /**
     * Constructs an instance of the SdetchallengePage.
     * 
     * @param driver the WebDriver instance used to interact with the web browser.
     */
	public SdetchallengePage(WebDriver driver) {
		super(driver);
	}

	/**
     * Clicks on the reset button to reset the challenge.
     * 
     * @return the current instance of SdetchallengePage for method chaining.
     */
	public SdetchallengePage clickOnResetButton() {
		logger.info("Click on reset button");
		resetButton.click();
		return new SdetchallengePage(driver);
	}

	/**
    * Clicks on the weigh button to measure the weights of the bowls.
    * 
    * @return the current instance of SdetchallengePage for method chaining.
    */
	public SdetchallengePage clickOnWeighButton() {
		logger.info("Click on weigh button");
		weighButton.click();
		waitFoElemetToDissapear(emptyResult);
		return new SdetchallengePage(driver);
	}

	/**
     * Retrieves the measurement result text from the result button.
     * 
     * @return the measurement result as a string.
     */
	public String getMeasurementResult() {
		String result = resultButton.getText();
		logger.info("The measurement result is: " + result);
		return result;
	}

	/**
     * Fills the bowls with the specified numbers of gold bars.
     * 
     * @param leftBars  the list of bar numbers to place in the left bowl.
     * @param rightBars the list of bar numbers to place in the right bowl.
     */
	public void fillBowlsWithBarNumbers(List<Integer> leftBars, List<Integer> rightBars) {
		logger.info("Click on Reset button");
		clickOnResetButton();

		// Fill left bowl inputs
		for (int i = 0; i < leftBars.size(); i++) {
			logger.info("Add " + leftBars.get(i).toString() + "to left bowl");
			leftBowlInputs.get(i).sendKeys(leftBars.get(i).toString());
		}

		// Fill right bowl inputs
		for (int i = 0; i < rightBars.size(); i++) {
			logger.info("Add " + rightBars.get(i).toString() + "to right bowl");
			rightBowlInputs.get(i).sendKeys(rightBars.get(i).toString());
		}
	}

	/**
     * Retrieves the texts of all gold bars.
     * 
     * @return a list of strings representing the texts of the gold bars.
     */
	public List<String> getGoldBarTexts() {
		return goldBars.stream().map(WebElement::getText).toList();
	}

	/**
     * Selects a specific gold bar by its number.
     * 
     * @param barNumber the index of the gold bar to select.
     */
	public void selectGoldBar(int barNumber) {
		logger.info("Click on Gold Bar #"+ barNumber);
		goldBars.get(barNumber).click();
	}

	/**
     * Finds the fake gold bar by performing measurements and analyzing results.
     * 
     * @return the index of the fake gold bar.
     */
	public int findFakeBar() {
		List<String> goldBarTexts = getGoldBarTexts();
		int numBars = goldBarTexts.size();
		int fakeBar = -1;

		// Prepare groups of 3
		List<Integer> leftGroup = List.of(0, 1, 2).stream().filter(index -> index <= numBars)
				.collect(Collectors.toList());

		List<Integer> rightGroup = List.of(3, 4, 5).stream().filter(index -> index <= numBars)
				.collect(Collectors.toList());

		fillBowlsWithBarNumbers(leftGroup, rightGroup);
		logger.info("Click on Weigh button");
		clickOnWeighButton();
		String result = getMeasurementResult();
		logger.info("Measurement result: " + result);

		if (result.equals(">")) {
			// Fake bar is in the left group
			fakeBar = findFakeBarInGroup(rightGroup);
		} else if (result.equals("<")) {
			// Fake bar is in the right group
			fakeBar = findFakeBarInGroup(leftGroup);
		} else if (result.equals("=")) {
			// Fake bar is in the group not weighed
			List<Integer> remainingGroup = List.of(6, 7, 8).stream().filter(index -> index <= numBars)
					.collect(Collectors.toList());
			fakeBar = findFakeBarInGroup(remainingGroup);
		}

		return fakeBar;
	}

	/**
     * Finds the fake bar within a given group of bars.
     * 
     * @param group the list of bar indices to check.
     * @return the index of the fake bar.
     */
	private int findFakeBarInGroup(List<Integer> group) {
		if (group.size() == 1) {
			return group.get(0);
		}

		// Weigh two bars to find the fake one
		List<Integer> leftGroup = List.of(group.get(0));
		List<Integer> rightGroup = List.of(group.get(1));

		fillBowlsWithBarNumbers(leftGroup, rightGroup);
		logger.info("Click on Weigh button");
		clickOnWeighButton();
		String result = getMeasurementResult();

		if (result.equals(">")) {
			return group.get(1);
		} else if (result.equals("<")) {
			return group.get(0);
		} else {
			return group.get(2);
		}
	}
	
	public List<String> getWeighings() {
		return weighings.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList()); 
	}
}