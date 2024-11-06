package org.Homeform.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Homeform implements SeleniumInterface {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private static final Logger logger = LoggerFactory.getLogger(Homeform.class);

    @BeforeClass
    public void setUp() {
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\Downloads\\Newfolders\\Home.html");
        extent.attachReporter(sparkReporter);
    }

    @Test(priority = 1)
    public void runChromeTests() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        runTests("Chrome");
        driver.close();
    }

    @Test(priority = 2)
    public void runFirefoxTests() throws IOException, InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\ADMIN\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        runTests("Firefox");
        driver.close();
    }

    @Test(priority = 3)
    public void runEdgeTests() throws IOException, InterruptedException {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\ADMIN\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
        runTests("Edge");
        driver.close();
    }

    // Method to run the tests for each browser
    public void runTests(String browser) throws InterruptedException {
        test = extent.createTest("Run tests for " + browser);
        try {
            // Open the website
        	driver.manage().window().maximize();
            driver.get("https://educongroups.com/");
            
            System.out.println(browser + " browser opened.");

            // Example of a test: Scroll and click on the 'Admission Enquiry' button
          
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement admissionEnquiryButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Admission Enquiry']")));

            // Scroll the button into view
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", admissionEnquiryButton);
            Thread.sleep(500);

            // Wait for the button to be clickable
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(admissionEnquiryButton));
            admissionEnquiryButton.click();
            wait.until(ExpectedConditions.urlContains("admission.html"));
            System.out.println("Navigated to the next page successfully.");

            // Capture screenshot after navigating to the next page
            String nextPageScreenshotPath = captureScreenshot(browser + "_NextPage_Screenshot");
            test.addScreenCaptureFromPath(nextPageScreenshotPath);
            test.log(Status.PASS, browser + " test passed successfully.");

            // Run both valid and invalid input tests
            fillFormFieldsWithValidInput();
            fillFormFieldsWithInvalidInput();

        } catch (Exception e) {
            handleException(e);
        }
    }

    // Method for filling form with valid input
    public void fillFormFieldsWithValidInput() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Fill Name
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")));
        nameField.clear();
        nameField.sendKeys("John");
        test.log(Status.PASS, "fill the name filled");

        // Fill Course
        WebElement courseField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='course']")));
        courseField.clear();
        courseField.sendKeys("Computer Science");
        test.log(Status.PASS, "fill the Course filled");

        // Fill College
        WebElement collegeField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='college']")));
        collegeField.clear();
        collegeField.sendKeys("XYZ University");
        test.log(Status.PASS, "fill the College filled");

        // Fill Father's Name
        WebElement fatherNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Father Name']")));
        fatherNameField.clear();
        fatherNameField.sendKeys("Michael");
        test.log(Status.PASS, "fill the fathername filled");

        // Fill Community
        WebElement communityField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='community']")));
        communityField.clear();
        communityField.sendKeys("General");
        test.log(Status.PASS, "fill the General filled");

        // Fill Phone Number
        WebElement phoneNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Phone Number']")));
        phoneNumberField.clear();
        phoneNumberField.sendKeys("1234567890");
        test.log(Status.PASS, "fill the Phone number filled");

        // Fill Email
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='email']")));
        emailField.clear();
        emailField.sendKeys("john@example.com");
        test.log(Status.PASS, "fill the Email filled");

        // Fill Message
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name='message']")));
        messageField.clear();
        messageField.sendKeys("I would like more information about the courses.");
        test.log(Status.PASS, "fill the Message filled");

        // Capture screenshot before submitting the form
        String validFormScreenshotPath = captureScreenshot("ValidForm_Before_Submission_Screenshot");
        test.addScreenCaptureFromPath(validFormScreenshotPath);
        test.log(Status.PASS, "screenshot taken sucessfully");

        // Click Submit Button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Submit']")));
        submitButton.click();
    }

    // Method for filling form with invalid input
    public void fillFormFieldsWithInvalidInput() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Fill Name (Invalid Input)
            WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")));
            nameField.clear();
            nameField.sendKeys("!@#$12");
            test.log(Status.PASS,"fill the invalid name filled");

            // Fill Course
            WebElement courseField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='course']")));
            courseField.clear();
            courseField.sendKeys("BCS");
            test.log(Status.PASS,"fill the invalid course filled");


            // Fill College
            WebElement collegeField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='college']")));
            collegeField.clear();
            collegeField.sendKeys("1233");
            test.log(Status.PASS,"fill the invalid College filled");


            // Fill Father's Name (Invalid Input)
            WebElement fatherNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Father Name']")));
            fatherNameField.clear();
            fatherNameField.sendKeys("!2Dav");
            test.log(Status.PASS,"fill the invalid Father,s name filled");


            // Fill Community (Leave Empty for Invalid Input)
            WebElement communityField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='community']")));
            communityField.clear();
            communityField.sendKeys("BCS");
            test.log(Status.PASS,"fill the invalid Community name filled");

            // Fill Phone Number (Invalid Input)
            WebElement phoneNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Phone Number']")));
            phoneNumberField.clear();
            phoneNumberField.sendKeys("abc");
            test.log(Status.PASS,"fill the invalid Phone number filled");

            // Fill Email (Invalid Input)
            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='email']")));
            emailField.clear();
            emailField.sendKeys("johnemailcom");
            test.log(Status.PASS,"fill the invalid Email filled");

            // Fill Message
            WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name='message']")));
            messageField.clear();
            messageField.sendKeys("I want to know the course details.");
            test.log(Status.PASS,"fill the invalid Message filled");

            // Capture screenshot before submitting the form
            String invalidFormScreenshotPath = captureScreenshot("InvalidForm_Before_Submission_Screenshot");
            test.addScreenCaptureFromPath(invalidFormScreenshotPath);
            test.log(Status.PASS,"Screenshot taken successfully");
            test.log(Status.FAIL, " Invalid input was accepted. Expected validation error, but the input was processed successfully.");


            // Click Submit Button
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Submit']")));
            submitButton.click();

            // Wait for and check validation messages
            WebDriverWait errorWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            try {
                WebElement errorMessage = errorWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='error-message']")));
                System.out.println("Validation error message displayed: " + errorMessage.getText());
                test.log(Status.INFO, "Validation error message displayed: " + errorMessage.getText());

                // Capture screenshot of error message
                String errorScreenshotPath = captureScreenshot("InvalidForm_Error_Screenshot");
                test.addScreenCaptureFromPath(errorScreenshotPath);
            } catch (TimeoutException e) {
                System.out.println("No validation error message displayed.");
                test.log(Status.INFO, "No validation error message displayed.");
            }

        } catch (TimeoutException te) {
            logger.error("Element was not found for Invalid Input Test: " + te.getMessage());
            test.log(Status.FAIL, "Timeout while filling form with invalid input: " + te.getMessage());
        }
    }

    // Method for capturing screenshot
    public String captureScreenshot(String screenshotName) {
        String path = "C:\\Users\\ADMIN\\Downloads\\Newfolders\\screenshots\\" + screenshotName + ".png";
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(path);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    // Method to handle exceptions
    public void handleException(Exception e) {
        logger.error("An error occurred: " + e.getMessage());
        test.log(Status.FAIL, e.getMessage());
        captureScreenshot("Error_Screenshot");
    }

  

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
    
    public void openReportInChrome() {
        try {
            // Construct the path to the report file
            String reportPath = "C:\\Users\\ADMIN\\Downloads\\Newfolders\\home.html";
            // Create a process to open the report in Chrome
            Runtime.getRuntime().exec(new String[] { "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", reportPath });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void enterText(WebElement element, String text) {
		// TODO Auto-generated method stub
		
	}
}
