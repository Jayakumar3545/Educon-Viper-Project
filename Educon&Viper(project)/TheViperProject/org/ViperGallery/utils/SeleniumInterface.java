package org.ViperGallery.utils;

import java.io.IOException;

/**
 * GalleryTestInterface defines the essential methods for conducting 
 * automated tests on the Viper Gallery web application. 
 * This interface establishes a contract for setting up reports, 
 * executing browser-specific tests, navigating through pages, 
 * taking screenshots, and tearing down the test environment.
 */
public interface SeleniumInterface  {
    
    /**
     * @author Jayakumar
     * Sets up the reporting mechanism using ExtentReports.
     * This method initializes the report and prepares it for logging 
     * test results and screenshots.
     */
    void setupReport();

    /**
     * Runs tests on the specified web browser.
     * This method initializes the WebDriver for the specified browser
     * and executes the testing process.
     *
     * @param browserName The name of the browser (e.g., "Chrome", "Firefox", "Edge").
     */
    void runBrowserTests(String browserName);

    /**
     * Tests the navigation functionality of the Viper Gallery application.
     * This method performs specific interactions on the application, 
     * such as clicking links and taking screenshots during the navigation process.
     *
     * @param browserName The name of the browser currently in use for testing.
     * @throws IOException If an input or output exception occurs during screenshot handling.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    void testPageNavigation(String browserName) throws IOException, InterruptedException;

    /**
     * Takes a screenshot and saves it to the specified directory.
     * This method captures the current state of the browser and 
     * returns the file path of the saved screenshot.
     *
     * @param fileName The name of the screenshot file (without the extension).
     * @return The file path of the saved screenshot.
     */
    String takeScreenshot(String fileName);

    /**
     * Tears down the test environment.
     * This method performs cleanup operations, such as closing the browser 
     * and flushing the report to ensure all results are saved properly.
     */
    void tearDown();
    
    
}
