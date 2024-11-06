package org.Homevideo.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Homevideo implements Seleniuminterface {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private static final Logger logger = LoggerFactory.getLogger(Homevideo.class);
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @BeforeClass
    public void setUp() {
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\Downloads\\Newfolders\\Homevideo.html");
        extent.attachReporter(sparkReporter);
    }

    @Test(priority = 1)
    public void runChromeTests() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        runTests("Chrome");
        driver.quit(); // Ensure Chrome driver quits after test
    }

    @Test(priority = 2)
    public void runFirefoxTests() throws IOException, InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\ADMIN\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        runTests("Firefox");
        driver.quit(); // Ensure Firefox driver quits after test
    }

    @Test(priority = 3)
    public void runEdgeTests() throws IOException, InterruptedException {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\ADMIN\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
        runTests("Edge");
        driver.quit(); // Ensure Edge driver quits after test
    }

    public void runTests(String browser) throws IOException, InterruptedException {
        initializeWebDriver();
        test = extent.createTest("Run Tests on " + browser);
        test.info("Tests are running on " + browser);
        navigateToHomePage();
        playVideo();
    }

    private void initializeWebDriver() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get("https://educongroups.com/");
    }

    public void navigateToHomePage() throws IOException {
        test.info("Navigating to the Home page and video section.");
        WebElement homeLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Home']")));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", homeLink);
        homeLink.click();

        String homeScreenshot = captureScreenshot("HomePage");
        test.addScreenCaptureFromPath(homeScreenshot);
        test.log(Status.PASS, "Screenshot taken successfully");
    }

    public void playVideo() throws IOException, InterruptedException {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            js.executeScript("window.scroll(0, 1000);");  // Corrected scrolling command
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src, 'youtube.com')]")));
            WebElement playButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.ytp-large-play-button")));
            playButton.click();
            test.log(Status.INFO, "Clicked the video play button.");

            String Homevideo = takeScreenshot("Home video");
            test.addScreenCaptureFromPath(Homevideo);
            test.log(Status.PASS, "Screenshot successfully taken");

            long timeout = System.currentTimeMillis() + 300000;  // 5 minutes timeout
            while (true) {
                Boolean isEnded = (Boolean) js.executeScript("return document.querySelector('video').ended;");
                if (isEnded) {
                    test.log(Status.PASS, "Video has finished playing successfully.");
                    captureScreenshot("VideoCompletion");
                    break;
                }
                if (System.currentTimeMillis() > timeout) {
                    test.log(Status.FAIL, "Video did not finish playing within the timeout period.");
                    break;
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            handleException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public void handleException(Exception e) {
        logger.error("An error occurred: ", e);
        try {
            String errorScreenshot = captureScreenshot("Error");
            test.addScreenCaptureFromPath(errorScreenshot);
            test.log(Status.FAIL, "An error occurred: " + e.getMessage());
        } catch (IOException ioException) {
            logger.error("Failed to capture screenshot on exception", ioException);
        }
    }

    public String captureScreenshot(String name) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filePath = "C:\\Users\\ADMIN\\Downloads\\Newfolders\\" + name + ".png";
        FileUtils.copyFile(screenshot, new File(filePath));
        return filePath;
    }

    public String takeScreenshot(String fileName) throws IOException {
        File screenshotDir = new File("C:\\Users\\ADMIN\\eclipse-workspace\\SeleniumProject\\screenshots\\");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = screenshotDir + "\\" + fileName + ".png";
        FileHandler.copy(screenshot, new File(screenshotPath));
        System.out.println("Screenshot taken: " + fileName);
        return screenshotPath;
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
        
        // Open the HTML report in Chrome after all browsers are done
        try {
            openHtmlReportInChrome();
        } catch (IOException e) {
            logger.error("Error opening HTML report: ", e);
        }
    }

    // Method to open the HTML report in Chrome
    private void openHtmlReportInChrome() throws IOException {
        File reportFile = new File("C:\\Users\\ADMIN\\Downloads\\Newfolders\\Homevideo.html");
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(reportFile);  // This will open the file in the default browser
        } else {
            logger.error("Desktop is not supported on this system.");
        }
    }

    @Override
    public void initializeWebDriver(WebDriver driver) {
        // Implement if needed, otherwise can be removed
    }
}
