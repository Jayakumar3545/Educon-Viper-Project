package org.ViperHome.utills;

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

public class ViperHome implements SeleniumInterface {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setupReport() {
        // ExtentReports setup
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\Downloads\\Newfolders\\viperHomes.html");
        extent.attachReporter(sparkReporter);
    }

    @Test(priority = 1)
    public void runChromeTests() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver-win64\\chromedriver.exe");
        
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Open Chrome in maximized state
        runBrowserTests("Chrome");
    }

    @Test(priority = 2)
    public void runFirefoxTests() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\ADMIN\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
       
        driver = new FirefoxDriver();
        driver.manage().window().maximize(); // Open Firefox in maximized state
        runBrowserTests("Firefox");
    }
    
    @Test(priority = 3)
    public void runEdgeTests() {
        // Set the path for Edge WebDriver
        System.setProperty("webdriver.edge.driver", "C:\\Users\\ADMIN\\Downloads\\edgedriver_win64\\msedgedriver.exe");
       
        driver = new EdgeDriver();
        driver.manage().window().maximize(); // Open Edge in maximized state
        runBrowserTests("Edge");
    }

    public void runBrowserTests(String browserName) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        js = (JavascriptExecutor) driver;

        driver.get("https://theviber.in/index.html");

        try {
            testPageNavigation(browserName);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private void testPageNavigation(String browserName) throws IOException, InterruptedException {
        test = extent.createTest("Test Page Navigation on " + browserName);

        try {
            // Click on the Home link
            WebElement homeLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Home']")));
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", homeLink);
            homeLink.click();
            test.info("Clicked on the Home link");

            // Take a screenshot after clicking Home
            String homeScreenshot = takeScreenshot(browserName + "_Home");
            test.addScreenCaptureFromPath(homeScreenshot);
            test.log(Status.PASS, "Screenshot of Home link click taken successfully");

            // Scroll to the video section smoothly and click
            WebElement video = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='video-tour-item popup']")));
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", video);
            Thread.sleep(1000);
            video.click();
            test.info("Clicked on the video play button");

            // Take a screenshot after clicking the video
            String videoScreenshot = takeScreenshot(browserName + "_Video_Click");
            test.addScreenCaptureFromPath(videoScreenshot);
            test.log(Status.PASS, "Screenshot of video click taken successfully");

            try {
                WebElement videoFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
                driver.switchTo().frame(videoFrame);
                
                WebElement fullscreenButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("//button[@class='ytp-fullscreen-button ytp-button']")));
                fullscreenButton.click();
                test.info("Clicked on the YouTube fullscreen button");

                WebElement playButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Play']")));
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", playButton);
                playButton.click();
                test.info("Clicked on the YouTube play button within iframe");

                // Wait for the video to complete
                waitUntilVideoEnds();

                String videoCompleteScreenshot = takeScreenshot(browserName + "_Video_Complete");
                test.addScreenCaptureFromPath(videoCompleteScreenshot);
                test.log(Status.PASS, "Screenshot after video completion taken successfully");

                driver.switchTo().defaultContent();

                try {
                    WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='mfp-close']")));
                    closeButton.click();
                    test.info("Closed the video popup successfully.");
                } catch (NoSuchElementException | TimeoutException e) {
                    test.log(Status.FAIL, "Close button not found or not clickable on video popup: " + e.getMessage());
                }

                testFooterLinks(browserName);

            } catch (Exception e) {
                test.fail("Test failed due to exception: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void waitUntilVideoEnds() throws InterruptedException {
        // Wait for the full duration of the video
        long videoDuration = 152000; // Video duration in milliseconds (2 minutes and 32 seconds)
        Thread.sleep(videoDuration + 5000); // Adding 5 seconds buffer for loading
    }

    private void testFooterLinks(String browserName) throws IOException, InterruptedException {
        scrollToBottom();

        WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//footer")));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", footer);

        WebElement phoneNumberLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='+91 94892 31000 +91 94892 32000']")));
        phoneNumberLink.click();
        test.info("Clicked on the phone number link");

        String phoneNumberScreenshot = takeScreenshot(browserName + "_Phone_Number_Screenshot");
        test.addScreenCaptureFromPath(phoneNumberScreenshot);
        test.log(Status.FAIL, "Phone number link in footer is not working.");

        WebElement footerEmailLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='theviberyercaud@gmail.com']")));
        footerEmailLink.click();
        test.info("Clicked on the email link");

        String footerEmailScreenshot = takeScreenshot(browserName + "_Footer_Email_Screenshot");
        test.addScreenCaptureFromPath(footerEmailScreenshot);
        test.log(Status.FAIL, "Email link in footer is not working.");
    }

    private void scrollToBottom() {
        js.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        openReport();
    }

    private void openReport() {
        try {
            // Open the report in Chrome
            Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe C:\\Users\\ADMIN\\Downloads\\Newfolders\\viperHomes.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUp() {
        // TODO Auto-generated method stub
    }
}
