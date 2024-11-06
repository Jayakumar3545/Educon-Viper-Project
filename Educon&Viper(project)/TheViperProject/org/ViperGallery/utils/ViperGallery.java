package org.ViperGallery.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ViperGallery implements SeleniumInterface {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    @Override
    public void setupReport() {
        // ExtentReports setup
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\Downloads\\Newfolders\\ViperGallery.html");
        extent.attachReporter(sparkReporter);
    }

    @Test(priority = 1)
    public void runEdgeTests() {
        // Set up Edge WebDriver
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize(); // Maximize the window
        runBrowserTests("Edge");
    }

    @Test(priority = 2)
    public void runFirefoxTests() {
        // Set up Firefox WebDriver
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize(); // Maximize the window
        runBrowserTests("Firefox");
    }

    @Test(priority = 3)
    public void runChromeTests() {
        // Set up Chrome WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the window
        runBrowserTests("Chrome");
    }

    @Override
    public void runBrowserTests(String browserName) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait
        js = (JavascriptExecutor) driver;

        driver.get("https://theviber.in/index.html#0");
        try {
            testPageNavigation(browserName);
        } catch (IOException | InterruptedException e) {
            test.fail("Error during test execution: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Override
    public void testPageNavigation(String browserName) throws IOException, InterruptedException {
        test = extent.createTest("Test Page Navigation on " + browserName);

        try {
            // Click on the Gallery link
            WebElement gallery = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Gallery']")));
            gallery.click();
            test.info("Clicked on the Gallery link");

            // Take a screenshot after clicking on the gallery
            String galleryScreenshot = takeScreenshot("Viper_Gallery_" + browserName);
            test.addScreenCaptureFromPath(galleryScreenshot);
            test.log(Status.PASS, "Gallery screenshot taken successfully");

            // Scroll down to the photo section
            WebElement photoSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='about-section padding-top padding-bottom']")));
            js.executeScript("arguments[0].scrollIntoView(true);", photoSection);
            Thread.sleep(500); // Wait for the scroll to complete

            // Click on the first photo or any other interaction required
            WebElement firstPhoto = wait.until(ExpectedConditions.elementToBeClickable(photoSection.findElement(By.xpath(".//img[1]")))); // Adjust the XPath as necessary
            firstPhoto.click();
            test.info("Clicked on the first photo");

            // Take a screenshot of the photo
            String photoScreenshotPath = takeScreenshot("photo_page_" + browserName);
            test.addScreenCaptureFromPath(photoScreenshotPath);
            test.log(Status.PASS, "Screenshot of photo page taken successfully");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            String screenshotPath = takeScreenshot("error_page_" + browserName);
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }

    @AfterClass
    @Override
    public void tearDown() {
        // Flush the report
        extent.flush();
        openReportInChrome();
    }

    @Override
    public String takeScreenshot(String fileName) {
        try {
            File screenshotDir = new File("C:\\Users\\ADMIN\\eclipse-workspace\\SeleniumProject\\screenshots\\");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = screenshotDir + "\\" + fileName + ".png";
            FileHandler.copy(screenshot, new File(screenshotPath));
            System.out.println("Screenshot taken: " + fileName);
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void openReportInChrome() {
        try {
            // Path to the Chrome executable
            String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"; // Update this path if necessary
            String reportPath = "C:\\Users\\ADMIN\\Downloads\\Newfolders\\ViperGallery.html";

            // Open the report in Chrome
            new ProcessBuilder(chromePath, reportPath).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
