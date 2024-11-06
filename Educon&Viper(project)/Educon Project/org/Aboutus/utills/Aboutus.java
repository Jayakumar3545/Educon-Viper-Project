package org.Aboutus.utills;

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

public class Aboutus implements SeleniumInterface {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    @Override
    public void setUp() {
        // Set up ExtentReports
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\Downloads\\Newfolders\\About.html");
        extent.attachReporter(sparkReporter);
    }

    @Test(priority = 1)
    public void runChromeTests() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get("https://educongroups.com/");
        runBrowserTests("Chrome");
        testPageNavigation(); // Click the About Us link
    }

    @Test(priority = 2)
    public void runFirefoxTests() throws IOException, InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\ADMIN\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get("https://educongroups.com/");
        runBrowserTests("Firefox");
        testPageNavigation(); // Click the About Us link
    }

    @Test(priority = 3)
    public void runEdgeTests() throws IOException, InterruptedException {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\ADMIN\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get("https://educongroups.com/");
        runBrowserTests("Edge");
        testPageNavigation(); // Click the About Us link
    }

    public void testPageNavigation() throws IOException, InterruptedException {
        test = extent.createTest("Test Page Navigation");

        try {
            // Wait and click on the "About Us" link
            WebElement aboutUsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='About Us']")));
            aboutUsLink.click();
            test.info("Clicked on the About Us link");

            // Take a screenshot after clicking the link
            String aboutUsScreenshot = takeScreenshot("Aboutus");
            test.addScreenCaptureFromPath(aboutUsScreenshot);
            test.log(Status.PASS, "Screenshot of About Us link click taken successfully");

            // Wait for the next page and scroll to an element
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebElement nextPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='probootstrap-section probootstrap-section-colored']")));
            js.executeScript("arguments[0].scrollIntoView(true);", nextPage); // Scroll to the element
            Thread.sleep(200);
            js.executeScript("window.scrollBy(0, 50);");
            Thread.sleep(500);

            // Take a screenshot of the about page
            String aboutPageScreenshot = takeScreenshot("About_page");
            test.addScreenCaptureFromPath(aboutPageScreenshot);
            test.log(Status.PASS, "Screenshot of About page taken successfully");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
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
    @Override
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
        openReportInChrome(); // Call to open the report in Chrome
    }

    @Override
    public void runBrowserTests(String browserName) {
        test = extent.createTest("Running tests on " + browserName);
        test.info("Executing tests for " + browserName);
        // Additional specific tests can be called here if needed.
    }

    @Override
    public void openReportInChrome() {
        try {
            // Construct the path to the report file
            String reportPath = "C:\\Users\\ADMIN\\Downloads\\Newfolders\\About.html";
            // Create a process to open the report in Chrome
            Runtime.getRuntime().exec(new String[] { "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", reportPath });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testPageNavigation(String browserName) throws IOException, InterruptedException {
        // Not used in this context, left as a placeholder
    }
}
