package org.Groupofcompanies.utills;

import java.io.IOException;

/**
 * Interface representing the testing functionalities for the Group of Companies application.
 * This interface defines methods required for setting up tests, navigating through application pages,
 * taking screenshots, and performing cleanup after tests.
 * @author Jayakumar
 */
public interface seleniuminterface {

    /**
     * Initializes the testing environment.
     * This includes setting up WebDriver configurations and preparing any necessary test reports.
     */
    void setUp();

    /**
     * Tests navigation through various pages of the Group of Companies application.
     * This method clicks on different navigation elements and validates the correct page displays.
     *
     * @throws IOException if an error occurs during page navigation, such as a network issue.
     * @throws InterruptedException if the thread is interrupted during sleep or waiting.
     */
    void testPageNavigation() throws IOException, InterruptedException;

    /**
     * Takes a screenshot of the current browser view and saves it to a specified location.
     *
     * @param fileName The name of the screenshot file (without the file extension).
     * @return The path of the saved screenshot as a String.
     * @throws IOException if an error occurs during file handling, such as an inability to write to the file.
     */
    String takeScreenshot(String fileName) throws IOException;

    /**
     * Cleans up after tests by closing the WebDriver and performing any necessary reporting operations.
     * This method ensures that resources are released and reports are properly finalized.
     */
    void tearDown();
}
