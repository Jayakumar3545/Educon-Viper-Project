package org.Homeform.utils;

import java.io.IOException;

import org.openqa.selenium.WebElement;

public interface SeleniumInterface {

    /**
     * @author Jayakumar
     * Fills form fields with valid input data. 
     * This method is used to populate all required fields in the form with valid, expected data.
     * Implementers should interact with the relevant WebElements and submit the form if necessary.
     */
    void fillFormFieldsWithValidInput();

    /**
     * Fills form fields with invalid input data.
     * This method is used to populate the form with invalid data, 
     * simulating edge cases to test validation responses.
     * Implementers should interact with the relevant WebElements and submit the form if necessary.
     */
    void fillFormFieldsWithInvalidInput();

    /**
     * Captures a screenshot of the current browser view.
     * @param screenshotName The name of the screenshot file to be saved.
     * @return The file path where the screenshot is saved.
     * @throws IOException 
     */
    String captureScreenshot(String screenshotName) throws IOException;

    /**
     * Handles exceptions that occur during test execution.
     * Logs the exception and captures a screenshot of the current view when an error is encountered.
     * @param e The exception to be handled.
     */
    void handleException(Exception e);

    /**
     * Enters text into a specified WebElement.
     * Clears any existing text in the element before inputting the new text.
     * @param element The WebElement where the text should be entered.
     * @param text The text to be entered.
     */
    void enterText(WebElement element, String text);
}
