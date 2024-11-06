package org.Vipercontacts.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
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

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ViperContact implements Seleniuminterface {
    private WebDriver driver; // WebDriver reference
    private WebDriverWait wait; // Wait instance for explicit waits
    private ExtentReports extent; // ExtentReports instance
    private ExtentTest test; // ExtentTest instance

    @BeforeClass
    public void setUp() {
        // Initialize ExtentReports
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\Downloads\\Newfolders\\vipercontact.html");
        extent.attachReporter(sparkReporter);
    }

    @Test(priority = 1)
    public void runChromeTests() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize window
        runBrowserTests("Chrome");
    }

    @Test(priority = 2)
    public void runFirefoxTests() {
        closePreviousDriver();
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\ADMIN\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize(); // Maximize window
        runBrowserTests("Firefox");
    }

    @Test(priority = 3)
    public void runEdgeTests() {
        closePreviousDriver();
        System.setProperty("webdriver.edge.driver", "C:\\Users\\ADMIN\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        
        // Set Edge options (if needed)
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized"); // Start in maximized mode
        driver = new EdgeDriver(options);
        runBrowserTests("Edge");
    }

    private void closePreviousDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void runBrowserTests(String browserName) {
    	
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open the target URL
            driver.get("https://theviber.in/index.html");
            test = extent.createTest("Test Contact Form Submission in " + browserName);

            // Click on the contact link
            WebElement contactLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='contact']")));
            contactLink.click();
            test.info("Clicked on the Contact link");

            // Take screenshot after clicking the contact link
            String contactScreenshot = takeScreenshot("ContactPage_" + browserName);
            test.addScreenCaptureFromPath(contactScreenshot);
            test.log(Status.PASS, "Screenshot taken after navigating to Contact page.");

            // Fill out the contact form
            fillContactForm();

        } catch (WebDriverException e) {
            handleException("WebDriverException: " + e.getMessage());
        } catch (Exception e) {
            handleException("An unexpected error occurred: " + e.getMessage());
        } finally {
            driver.quit(); // Ensure the driver is closed after each test
        }
    }

    private void fillContactForm() throws IOException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("John Doe");
        test.info("Filled in the name field.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys("9876543210");
        test.info("Filled in the phone number field.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("John123@gmail.com");
        test.info("Filled in the email field.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Enter Your Message']"))).sendKeys("Hello! I'm interested in your services. Can you provide more details?");
        test.info("Filled in the message field.");
        

        // Take screenshot after filling the form
        
     
        // Scroll and submit the form
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        
        
        // Use JavaScript to click if normal click fails
        try {
            submitButton.click();
        } catch (WebDriverException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        }
        test.info("Clicked the Submit button.");
        
    
    
    String fillFormScreenshot = takeScreenshot("FilledForm");
    test.addScreenCaptureFromPath(fillFormScreenshot);
    test.log(Status.PASS, "Screenshot taken after filling the contact form.");
    test.log(Status.FAIL, "Valid input not accepted,Displaying all fields required");
    
    }

    private void handleException(String message) {
        test.log(Status.FAIL, message);
        try {
            takeScreenshot("Error");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush(); // Flush the ExtentReports instance
    }

    public String takeScreenshot(String screenshotName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = "C:\\Users\\ADMIN\\Downloads\\Newfolders\\" + screenshotName + ".png";
        File finalDestination = new File(destination);
        FileHandler.copy(source, finalDestination);
        return destination;
    }

	@Override
	public void openReportInChrome() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testPageNavigation(String browserName) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
	}

    

  
}