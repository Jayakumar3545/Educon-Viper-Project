package org.Document.utils; // Adjust according to your package structure

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap; // Ensure there is no duplicate import

public class Document implements Seliniuminterface { // Implement the interface

    private WebDriver driver;
    private String downloadFilepath = "C:\\Users\\ADMIN\\Downloads"; // Update as necessary
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() {
        // ExtentReports setup
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\Downloads\\Newfolders\\Document.html");
        extent.attachReporter(sparkReporter);
    }

    @Test(priority = 1)
    public void runEdgeTests() throws IOException, InterruptedException {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\ADMIN\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        
        // Configure Edge options
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        options.setExperimentalOption("prefs", new HashMap<String, Object>() {{
            put("download.default_directory", downloadFilepath);
            put("download.prompt_for_download", false);
            put("plugins.always_open_pdf_externally", true);
        }});
        
        driver = new EdgeDriver(options);
        
        runTests("Edge");
        testDocumentDownload();
        driver.quit(); // Quit Edge
    }

    @Test(priority = 2)
    public void runFirefoxTests() throws IOException, InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\ADMIN\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
        
        // Configure Firefox profile
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2); // Use for custom download path
        profile.setPreference("browser.download.dir", downloadFilepath);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf"); // Adjust MIME types as needed
        profile.setPreference("pdfjs.disabled", true); // Disable the built-in PDF viewer
        
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        
        driver = new FirefoxDriver(options);
        
        runTests("Firefox");
        testDocumentDownload();
        driver.quit(); // Quit Firefox
    }
    
    @Test(priority = 3)
    public void runChromeTests() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver-win64\\chromedriver.exe");
        
        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setExperimentalOption("prefs", new HashMap<String, Object>() {{
            put("download.default_directory", downloadFilepath);
            put("download.prompt_for_download", false);
            put("plugins.always_open_pdf_externally", true);
        }});
        
        driver = new ChromeDriver(options);
        
        runTests("Chrome");
        testDocumentDownload();
        driver.quit(); // Quit Chrome
    }

    @Override
    public void runTests(String browser) {
        test = extent.createTest("Run Tests on " + browser);
        test.info("Tests are running on " + browser);
    }

    @Override
    public void testDocumentDownload() {
        test = extent.createTest("Document Download Test"); // Start the test in the report
        try {
            // Step 1: Navigate to your target page
            driver.get("https://educongroups.com/"); // Replace with your actual URL
            
            // Step 2: Click on the "Document" icon (download icon)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement downloadIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='icon-download']"))); // XPath for the download icon
            downloadIcon.click();
            test.pass("Clicked on the Document download icon."); // Log the action in the report

            // Step 3: Take a screenshot after clicking the Document link
            String documentScreenshotPath = takeScreenshot(driver, "document_page");
            test.addScreenCaptureFromPath(documentScreenshotPath); // Add screenshot to report
            test.pass("Screenshot of Document page taken successfully.");

            // Step 4: Wait for the file to download completely
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            String downloadedFileName = "Document"; // The name of your file without extension
            waitForDownloadToComplete(downloadFilepath, downloadedFileName);
            test.pass("File downloaded successfully: " + downloadedFileName); // Log download success in the report

        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage()); // Log failure in the report
        }
    }

    @Override
    public String takeScreenshot(WebDriver driver, String filename) {
        // Capture screenshot and save it to the specified location
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filePath = downloadFilepath + "\\" + filename + ".png"; // Path to save the screenshot
        try {
            FileUtils.copyFile(screenshot, new File(filePath));
        } catch (IOException e) {
            System.out.println("Error saving screenshot: " + e.getMessage());
        }
        return filePath;
    }

    @Override
    public void waitForDownloadToComplete(String downloadPath, String fileName) {
        // Wait for the file to be downloaded
        File file = new File(downloadPath + "\\" + fileName + ".pdf"); // Adjust with the correct extension
        int attempts = 0;
        while (!file.exists() && attempts < 20) { // Wait for up to 20 attempts (20 seconds)
            try {
                Thread.sleep(1000); // Wait 1 second before checking again
                attempts++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
            }
        }
        
        if (file.exists()) {
            System.out.println("File downloaded successfully: " + file.getAbsolutePath());
        } else {
            System.out.println("File download failed or timed out.");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
        openReportInChrome(); // Call to open the report in Chrome
    }

    public void openReportInChrome() {
        try {
            // Construct the path to the report file
            String reportPath = "C:\\Users\\ADMIN\\Downloads\\Newfolders\\Document.html"; // Ensure this matches your actual report path
            // Create a process to open the report in Chrome
            Runtime.getRuntime().exec(new String[]{ 
                "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", 
                reportPath 
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   }

    
