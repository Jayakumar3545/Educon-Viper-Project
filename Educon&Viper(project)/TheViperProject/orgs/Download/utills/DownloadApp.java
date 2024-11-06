package orgs.Download.utills;

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

public class DownloadApp implements Seleniuminterface  {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private ExtentReports extent;
    private ExtentTest test;

    @Override
    @BeforeClass
    public void setUp() {
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\Downloads\\Newfolders\\DownloadReport.html");
        extent.attachReporter(sparkReporter);
        test = extent.createTest("Download App Tests");
    }

    @Override
    @Test(priority = 1)
    public void runEdgeTests() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\ADMIN\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
        runBrowserTests("Edge");
    }

    @Override
    @Test(priority = 2)
    public void runFirefoxTests() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\ADMIN\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        runBrowserTests("Firefox");
    }

    @Override
    @Test(priority = 3)
    public void runChromeTests() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        runBrowserTests("Chrome");
    }

    @Override
    public void runBrowserTests(String browserName) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        js = (JavascriptExecutor) driver;

        driver.get("https://theviber.in/index.html");

        try {
            WebElement downloadLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Download App']")));
            downloadLink.click();
            test.log(Status.PASS, "Click on the download app link in " + browserName);

            String downloadLinkScreenshot = takeScreenshot("DownloadPage");
            test.addScreenCaptureFromPath(downloadLinkScreenshot);
            test.log(Status.PASS, "Screenshot after clicking download link in " + browserName);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("play-logo")));
            test.log(Status.PASS, "Google Play logo visible on the page in " + browserName);

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed in " + browserName + ": " + e.getMessage());
        }
    }

    @Override
    public String takeScreenshot(String screenshotName) throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = "C:\\Users\\ADMIN\\Downloads\\Newfolders\\Screenshots\\" + screenshotName + ".png";
        FileHandler.copy(source, new File(destination));
        return destination;
    }

    @Override
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
        openReportInChrome();
    }

    @Override
    public void openReportInChrome() {
        try {
            String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
            String reportPath = "C:\\Users\\ADMIN\\Downloads\\Newfolders\\DownloadReport.html";
            new ProcessBuilder(chromePath, reportPath).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
