package orgs.Download.utills;

import java.io.IOException;

/**
 * Interface for Selenium-based testing across multiple browsers.
 * Defines methods for setting up the testing environment, running tests on different browsers, 
 * taking screenshots, and managing test reports.
 * @author Jayakumar
 */
public interface Seleniuminterface {

    /**
     * Initializes the ExtentReports instance and sets up the reporting framework
     * to capture and store test results. Should be called at the beginning of testing
     * to prepare the reporting system.
     */
    void setUp();

    /**
     * Configures and executes the test in the Edge browser. This method sets up the
     * Edge WebDriver, opens the application URL, performs necessary interactions,
     * and logs results for Edge.
     */
    void runEdgeTests();

    /**
     * Configures and executes the test in the Firefox browser. This method sets up the
     * Firefox WebDriver, navigates to the application URL, interacts with specified elements,
     * and logs results for Firefox.
     */
    void runFirefoxTests();

    /**
     * Configures and executes the test in the Chrome browser. This method sets up the
     * Chrome WebDriver, opens the application URL, performs interactions as required,
     * and logs results for Chrome.
     */
    void runChromeTests();

    /**
     * Runs the common testing steps across different browsers. This method navigates to the
     * required URL, interacts with page elements, and verifies test conditions. It’s a 
     * reusable approach called by specific browser test methods.
     *
     * @param browserName the name of the browser (e.g., "Edge", "Firefox", "Chrome") 
     *                    for logging and reporting purposes.
     */
    void runBrowserTests(String browserName);

    /**
     * Captures a screenshot of the current browser view. Saves the screenshot with a specific name
     * at a defined location for reporting or debugging purposes.
     *
     * @param screenshotName the name to save the screenshot file as.
     * @return the path of the saved screenshot file.
     * @throws IOException if there is an error during file handling.
     */
    String takeScreenshot(String screenshotName) throws IOException;

    /**
     * Closes the WebDriver instance and flushes the report, saving any gathered test
     * data to the final report file. This method should be called at the end of testing.
     */
    void tearDown();

    /**
     * Opens the generated test report in the Chrome browser. Useful for quickly viewing
     * the HTML report file upon test completion.
     */
    void openReportInChrome();
}
