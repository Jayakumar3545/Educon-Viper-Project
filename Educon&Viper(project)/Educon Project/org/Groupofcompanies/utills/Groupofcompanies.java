package org.Groupofcompanies.utills;

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

public class Groupofcompanies implements seleniuminterface  {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() {
        // ExtentReports setup
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\Downloads\\Newfolders\\Groupofcompanies.html");
        extent.attachReporter(sparkReporter);
    }

    @Test(priority = 1)
    public void runChromeTests() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        initializeWebDriver();
        runBrowserTests("Chrome");
        testPageNavigation();
    }

    @Test(priority = 2)
    public void runFirefoxTests() throws IOException, InterruptedException {
    	 System.setProperty("webdriver.gecko.driver", "C:\\Users\\ADMIN\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        initializeWebDriver();
        runBrowserTests("Firefox");
        testPageNavigation();
    }

    @Test(priority = 3)
    public void runEdgeTests() throws IOException, InterruptedException {
    	System.setProperty("webdriver.edge.driver", "C:\\Users\\ADMIN\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
        initializeWebDriver();
        runBrowserTests("Edge");
        testPageNavigation();
    }

    public void initializeWebDriver() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get("https://educongroups.com/");
    }

    public void runBrowserTests(String browser) {
        test = extent.createTest("Run Tests on " + browser);
        test.info("Tests are running on " + browser);
    }


    @Test
    public void testPageNavigation() throws IOException, InterruptedException {
        test = extent.createTest("Test Page Navigation");

        try {
           
            WebElement Groupofcompanies = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Group Of Companies']")));
            Groupofcompanies.click();
            test.info("Clicked on the Groupofcompanies link");
            
            String GroupOfCompanies=takeScreenshot("Group Of Companies");
            test.addScreenCaptureFromPath(GroupOfCompanies);
            test.log(Status.PASS,"Screenshot successfully taken");
            
            
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
           WebElement nextpages = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='probootstrap-section']")));
            js.executeScript("arguments[0].scrollIntoView(true);",nextpages ); // Ensure Gallery link is in view
            Thread.sleep(200); 
           
            js.executeScript("window.scrollBy(0, 50);"); 
            Thread.sleep(500); 



            
            String GroupOfCompaniesScreenshotPath = takeScreenshot("GroupOfCompanies_page");
            test.addScreenCaptureFromPath(GroupOfCompaniesScreenshotPath);
            test.log(Status.PASS, "Screenshot of GroupOfCompanies page taken successfully");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
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
            openReportInChrome(); // Call to open the report in Chrome
        }

        public void openReportInChrome() {
            try {
                // Construct the path to the report file
                String reportPath = "C:\\Users\\ADMIN\\Downloads\\Newfolders\\Groupofcompanies.html"; // Ensure this matches your actual report path
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

        
    
    
