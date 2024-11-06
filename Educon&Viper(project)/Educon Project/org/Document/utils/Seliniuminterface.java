package org.Document.utils;

import org.openqa.selenium.WebDriver;

/**
 * @author Jayakumar
 * Interface defining methods for Selenium WebDriver testing.
 */ 
public interface Seliniuminterface {
    
    /**
     * Sets up the test environment, including WebDriver initialization and configuration.
     */
    void setUp();

    /**
     * Runs tests on a specified browser.
     *
     * @param browser the name of the browser to run tests on (e.g., "Chrome", "Firefox", "Edge").
     */
    void runTests(String browser);

    /**
     * Tests the document download functionality.
     * This method should contain the steps to navigate to the document page, initiate a download,
     * and verify that the file has been downloaded successfully.
     */
    void testDocumentDownload();

    /**
     * Takes a screenshot of the current browser window.
     *
     * @param driver the WebDriver instance used for the browser.
     * @param filename the name to save the screenshot as (without extension).
     * @return the file path of the saved screenshot.
     */
    String takeScreenshot(WebDriver driver, String filename);

    /**
     * Waits for a specified file to download completely.
     *
     * @param downloadPath the path to the directory where files are downloaded.
     * @param fileName the name of the file to wait for (without extension).
     */
    void waitForDownloadToComplete(String downloadPath, String fileName);
}
