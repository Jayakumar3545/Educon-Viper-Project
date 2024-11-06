package org.Aboutus.utills;

import java.io.IOException;

/**
 * Interface for defining methods related to Selenium tests,
 * including setup, running tests across different browsers,
 * taking screenshots, and handling reports.
 * 
 * Author: Jayakumar
 
 */
public interface SeleniumInterface {

    /**
     * Initializes the testing environment. This method sets up the necessary 
     * configurations and resources needed before executing any tests. 
     * It may include initializing logging, reports, and WebDriver instances.
     */
    void setUp();

    /**
     * Configures and executes the test cases specifically for the Edge browser.
     * This method is intended to set up the Edge WebDriver, open the application URL, 
     * perform specific interactions defined in the test cases, and capture test results.
     * @throws InterruptedException 
     * @throws IOException 
     * @author Jayakumar
     */
    void runEdgeTests() throws IOException, InterruptedException;

    /**
     * Configures and executes the test cases specifically for the Firefox browser.
     * This method is intended to set up the Firefox WebDriver, open the application URL,
     * perform specific interactions defined in the test cases, and capture test results.
     * @throws InterruptedException 
     * @throws IOException 
     */
    void runFirefoxTests() throws IOException, InterruptedException;

    /**
     * Configures and executes the test cases specifically for the Chrome browser.
     * This method is intended to set up the Chrome WebDriver, open the application URL,
     * perform specific interactions defined in the test cases, and capture test results.
     * @throws InterruptedException 
     * @throws IOException 
     */
    void runChromeTests() throws IOException, InterruptedException;

    /**
     * A common testing method that executes the core test steps within a specified browser.
     * This method handles navigating to the application URL, interacting with web elements,
     * and verifying expected conditions. It is called by the browser-specific test methods
     * to maintain DRY (Don't Repeat Yourself) principles.
     *
     * @param browserName the name of the browser (e.g., "Edge", "Firefox", "Chrome") for reporting purposes.
     */
    void runBrowserTests(String browserName);

    /**
     * Captures a screenshot of the current browser window during test execution. 
     * The screenshot is saved with a specific name and location, which can be useful 
     * for debugging and reporting failures in tests.
     *
     * @param screenshotName the name for the screenshot file, which will be used to save the image.
     * @return the file path of the saved screenshot, allowing retrieval or reference in reports.
     * @throws IOException if there is an error while saving the screenshot file, such as file access issues.
     */
    String takeScreenshot(String screenshotName) throws IOException;

    /**
     * Closes the WebDriver instance and releases any resources held by the test framework.
     * This method also ensures that any accumulated reports are flushed to the final output,
     * ensuring all test results are captured and saved properly.
     */
    void tearDown();

    /**
     * Opens the generated test report in the default Chrome browser. 
     * This method facilitates direct access to the HTML report file generated during the tests, 
     * allowing users to quickly review the test results.
     */
    void openReportInChrome();

    /**
     * Performs specific page navigation tests within a given browser. 
     * This method can include additional functionality for verifying that the application
     * behaves as expected when navigating between pages, such as checking URL changes 
     * or element visibility.
     *
     * @param browserName the name of the browser to perform the navigation tests on.
     * @throws IOException if there is an error during navigation testing.
     * @throws InterruptedException if the thread is interrupted while waiting for actions to complete.
     */
    void testPageNavigation(String browserName) throws IOException, InterruptedException;
}
