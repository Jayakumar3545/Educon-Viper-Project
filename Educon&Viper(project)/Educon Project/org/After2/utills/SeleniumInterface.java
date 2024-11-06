package org.After2.utills;

import java.io.IOException;

/**
 * Interface for defining methods related to Selenium tests,
 * including setup, running tests across different browsers,
 * taking screenshots, and handling reports.
 * 
 * @author Jayakumar
 */

public interface SeleniumInterface {
    
    /**
     * Initializes the testing environment. This method sets up the necessary 
     * configurations and resources needed before executing any tests. 
     * It may include initializing logging, reports, and WebDriver instances.
     */
    void setUp();

    /**
     * Executes tests on the Chrome browser. This method configures the 
     * Chrome WebDriver, navigates to the application URL, and performs 
     * the defined test cases specific to Chrome. 
     * 
     * @throws IOException if there is an error during input/output operations.
     * @throws InterruptedException if the thread is interrupted while executing tests.
     */
    void runChromeTests() throws IOException, InterruptedException;

    /**
     * Executes tests on the Firefox browser. This method configures the 
     * Firefox WebDriver, navigates to the application URL, and performs 
     * the defined test cases specific to Firefox. 
     * 
     * @throws IOException if there is an error during input/output operations.
     * @throws InterruptedException if the thread is interrupted while executing tests.
     */
    void runFirefoxTests() throws IOException, InterruptedException;

    /**
     * Executes tests on the Edge browser. This method configures the 
     * Edge WebDriver, navigates to the application URL, and performs 
     * the defined test cases specific to Edge. 
     * 
     * @throws IOException if there is an error during input/output operations.
     * @throws InterruptedException if the thread is interrupted while executing tests.
     */
    void runEdgeTests() throws IOException, InterruptedException;

    /**
     * Tests page navigation across different links in the application. 
     * This method verifies that the application behaves as expected when 
     * navigating between pages, such as checking URL changes and element visibility.
     * 
     * @throws IOException if there is an error during navigation testing.
     * @throws InterruptedException if the thread is interrupted while waiting for actions to complete.
     */
    void testPageNavigation() throws IOException, InterruptedException;

    /**
     * Captures a screenshot of the current browser view during test execution. 
     * The screenshot is saved with a specific name and location, useful for 
     * debugging and reporting failures in tests.
     * 
     * @param fileName the name for the screenshot file, which will be used to save the image.
     * @return the file path of the saved screenshot, allowing retrieval or reference in reports.
     * @throws IOException if there is an error while saving the screenshot file.
     */
    String takeScreenshot(String fileName) throws IOException;

    /**
     * Cleans up resources after tests are completed. This method ensures 
     * that the WebDriver instance is properly closed and any accumulated 
     * reports are flushed to the final output.
     */
    void tearDown();

    /**
     * Opens the generated test report in the default Chrome browser. 
     * This method facilitates direct access to the HTML report file generated 
     * during the tests, allowing users to quickly review the test results.
     */
    void openReportInChrome();
}
