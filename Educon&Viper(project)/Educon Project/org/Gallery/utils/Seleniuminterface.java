package org.Gallery.utils;

import java.io.IOException;

/**
 * Interface representing the testing functionalities for the Gallery application.
 * This interface defines the methods required for setting up tests, running browser-specific tests,
 * navigating through pages, taking screenshots, and generating reports.
 */
public interface Seleniuminterface {

    /**
     * Initializes the WebDriver and sets up the test environment.
     * This includes setting up ExtentReports for reporting test results.
     */
    void setUp();

    /**
     * Runs tests specifically for the Chrome browser.
     * This method should configure the WebDriver for Chrome and execute the relevant test scenarios.
     * @throws InterruptedException 
     * @throws IOException 
     */
    void runChromeTests() throws IOException, InterruptedException;

    /**
     * Runs tests specifically for the Firefox browser.
     * This method should configure the WebDriver for Firefox and execute the relevant test scenarios.
     * @throws InterruptedException 
     * @throws IOException 
     */
    void runFirefoxTests() throws IOException, InterruptedException;

    /**
     * Runs tests specifically for the Edge browser.
     * This method should configure the WebDriver for Edge and execute the relevant test scenarios.
     * @throws InterruptedException 
     * @throws IOException 
     */
    void runEdgeTests() throws IOException, InterruptedException;

    /**
     * Initializes common WebDriver settings.
     * This includes maximizing the browser window, setting implicit wait times,
     * and navigating to the starting URL of the application.
     */
    void initializeWebDriver();

    /**
     * Logs the details of the browser tests being executed.
     *
     * @param browser The name of the browser being tested (e.g., "Chrome", "Firefox", "Edge").
     */
    void runBrowserTests(String browser);

    /**
     * Tests navigation through the Gallery and associated pages.
     * This method clicks on various navigation elements and validates
     * that the correct pages are displayed.
     *
     * @throws InterruptedException if the thread is interrupted during sleep.
     * @throws IOException 
     * @author Jayakumar
     */
    void testPageNavigation() throws InterruptedException, IOException;

    /**
     * Takes a screenshot of the current browser view and saves it to a specified location.
     *
     * @param fileName The name of the screenshot file (without extension).
     * @return The path of the saved screenshot as a String.
     * @throws IOException if an error occurs during file handling.
     */
    String takeScreenshot(String fileName) throws IOException;

    /**
     * Cleans up after tests by closing the WebDriver and flushing reports.
     * This method ensures that resources are released and the report is generated correctly.
     */
    void tearDown();

    /**
     * Opens the generated test report in the default web browser.
     * This method should construct the correct path to the report file
     * and invoke the browser to display the report.
     */
    void openReportInChrome();
}
