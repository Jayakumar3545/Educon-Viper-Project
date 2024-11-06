

package org.Contact.utils;

/**
 * Interface for defining methods related to Selenium tests,
 * including setup, running tests across different browsers,
 * taking screenshots, and handling reports.
 * 
 * @author Jayakumar
 */


import java.io.IOException;

public interface Seleniuminterface {

    /**
     * Initializes the web driver for the specified browser. This method should set up the necessary configurations
     * and launch the browser instance for testing.
     * 
     * @throws IOException if there is an error during the initialization process.
     */
    void initializeWebDriver() throws IOException;

    /**
     * Runs the browser tests for the specified browser. This method should contain the logic for executing a series
     * of tests based on the browser type provided as an argument.
     * 
     * @param browser The name of the browser (e.g., "Chrome", "Firefox") in which the tests should be run.
     * @throws IOException if there is an error while running the tests.
     * @throws InterruptedException if the thread executing the test is interrupted.
     */
    void runBrowserTests(String browser) throws IOException, InterruptedException;

    /**
     * Takes a screenshot and saves it with the specified name. This method is useful for capturing the state of 
     * the application at a given point during testing, especially for debugging purposes.
     * 
     * @param screenshotName The name to be used for the saved screenshot file.
     * @return The path to the saved screenshot file.
     * @throws IOException if there is an error during the screenshot capturing process.
     */
    String takeScreenshot(String screenshotName) throws IOException;

    /**
     * Tests the page navigation within the contact management application. This method should verify that the 
     * navigation between different pages is functioning correctly, ensuring that all links and buttons work as expected.
     * 
     * @throws IOException if there is an error during the navigation testing.
     * @throws InterruptedException if the thread executing the navigation test is interrupted.
     */
    void testPageNavigation() throws IOException, InterruptedException;
}
