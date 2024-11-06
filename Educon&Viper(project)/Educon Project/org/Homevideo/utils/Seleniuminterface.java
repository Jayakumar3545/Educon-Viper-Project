package org.Homevideo.utils;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

public interface Seleniuminterface {

    /**
     * Sets up the testing environment, including initializing
     * reports and configurations needed before running tests.
     * @author Jayakumar
     */
    void setUp();

    /**
     * Initializes WebDriver settings for a specific browser, including configurations
     * like timeouts, maximizing the window, and setting up JavaScript execution.
     *
     * @param driver The WebDriver instance to be initialized.
     */
    void initializeWebDriver(WebDriver driver);

    /**
     * Executes a sequence of tests on the specified browser.
     * This method contains the primary steps for loading the application,
     * navigating through pages, and performing actions as needed.
     *
     * @param browser The browser name to run tests on (e.g., Chrome, Firefox, Edge).
     * @throws IOException If an I/O error occurs (e.g., when saving screenshots).
     * @throws InterruptedException If the test is interrupted during execution.
     */
    void runTests(String browser) throws IOException, InterruptedException;

    /**
     * Navigates to the home page of the application and performs required actions
     * (like clicking specific elements or capturing screenshots). This method typically
     * includes any preliminary steps required before starting tests on specific features.
     *
     * @throws InterruptedException If the navigation is interrupted.
     * @throws IOException If an I/O error occurs (e.g., when saving screenshots).
     */
    void navigateToHomePage() throws InterruptedException, IOException;

    /**
     * Plays a video embedded on the home page or another section of the application.
     * This method waits until the video has finished playing and captures the completion status.
     *
     * @throws IOException If an I/O error occurs (e.g., when saving screenshots).
     * @throws InterruptedException If the video playback is interrupted.
     */
    void playVideo() throws IOException, InterruptedException;

    /**
     * Handles exceptions that occur during test execution, such as navigation issues
     * or element not found errors. This method logs the error and captures a screenshot
     * to help diagnose and resolve issues.
     *
     * @param e The exception encountered during test execution.
     */
    void handleException(Exception e);

    /**
     * Captures a screenshot of the current state of the application under test.
     * Useful for logging visual evidence of test results, especially for errors or
     * key milestones in the test case.
     *
     * @param name The name to be given to the screenshot file.
     * @return The file path of the saved screenshot.
     * @throws IOException If an I/O error occurs while saving the screenshot.
     */
    String captureScreenshot(String name) throws IOException;

    /**
     * Cleans up resources used during the test, such as closing the WebDriver session,
     * finalizing reports, and any other necessary teardown actions.
     * This method is essential for ensuring that each test suite runs independently
     * and doesn’t leave any resources open.
     */
    void tearDown();
}
