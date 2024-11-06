package org.ViperHome.utills;

import java.io.IOException;

/**
 * SeleniumInterface is designed to define the essential operations for 
 * executing Selenium WebDriver tests. This interface provides a contract 
 * for managing the setup, execution, and teardown of browser-based 
 * automation tests, promoting consistency and reusability across various 
 * implementations.
 * 
 * <p>Implementing classes must provide concrete implementations for all 
 * methods defined in this interface, ensuring that the required functionalities 
 * for web automation testing are met.</p>
 * 
 * @author Jayakumar
 */
public interface SeleniumInterface {
    /**
     * Sets up the WebDriver and other necessary configurations before executing tests.
     */
    void setUp(); 

    /**
     * Executes the test cases specific to the web browser identified by the 
     * specified browser name.
     * 
     * @param browserName The name of the browser (e.g., Chrome, Firefox, Edge) 
     *                    for which to run the tests.
     */
    void runBrowserTests(String browserName); 

    /**
     * Closes the browser and cleans up resources after test execution.
     */
    void tearDown(); 

    /**
     * Captures a screenshot of the current browser state and saves it to a 
     * specified location.
     * 
     * @param screenshotName The name to use for the saved screenshot file.
     * @return The file path of the saved screenshot.
     * @throws IOException If an I/O error occurs while taking the screenshot.
     * @author Jayakumar
     */
    String takeScreenshot(String screenshotName) throws IOException; 
}
