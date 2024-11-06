package org.Contact.utils;

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

public class Contact implements Seleniuminterface {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() {
        // ExtentReports setup
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\Downloads\\Newfolders\\contact.html");
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
            // Step 1: Click on "Contact" link
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebElement contact = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact']")));
            contact.click();
            test.info("Clicked on the Contact link");

            // Wait for the Contact page to fully load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Contact')]")));
            test.info("Contact page is visible");

            // Take screenshot of the Contact page after clicking
            String contactScreenshotPath = takeScreenshot("contact_page");
            test.addScreenCaptureFromPath(contactScreenshotPath);
            test.log(Status.PASS, "Screenshot of Contact page taken successfully");

            // Step 2: Scroll to and click the "Home" link
            WebElement home = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Home']")));
            
            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView(true);", home);
            Thread.sleep(1000); // Ensure the element has time to come into view

            // Try clicking with regular click first
            try {
                home.click();
                test.info("Clicked on the Home link");
            } catch (Exception e) {
                // If regular click fails, try JavaScript click
                js.executeScript("arguments[0].click();", home);
                test.info("Clicked on the Home link using JavaScript Executor");
            }

            // Take screenshot of the Home page after clicking
            String homeScreenshotPath = takeScreenshot("home_page");
            test.addScreenCaptureFromPath(homeScreenshotPath);
            test.log(Status.PASS, "Screenshot of Home page taken successfully");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }

        
        try {
            // Step 1: Click on "Contact" link
            WebElement contact = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact']")));
            contact.click();
            test.info("Clicked on the Contact link");

            // Wait for the Contact page to fully load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Contact')]")));
            test.info("Contact page is visible");

            // Take screenshot of the Contact page after clicking
            String contactScreenshotPath = takeScreenshot("contact_page");
            test.addScreenCaptureFromPath(contactScreenshotPath);
            test.log(Status.PASS, "Screenshot of Contact page taken successfully");

            // Step 2: Scroll to and click the "Home" link
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebElement aboutus = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='About Us']")));
            
            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView(true);", aboutus);
            Thread.sleep(1000); // Ensure the element has time to come into view

            // Try clicking with regular click first
            try {
                aboutus.click();
                test.info("Clicked on the aboutus link");
            } catch (Exception e) {
                // If regular click fails, try JavaScript click
                js.executeScript("arguments[0].click();", aboutus);
                test.info("Clicked on the aboutus link using JavaScript Executor");
            }
            
            WebElement nextpage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Who We Are']")));
            
            js.executeScript("arguments[0].scrollIntoView(true);", nextpage); // Ensure Gallery link is in view
            Thread.sleep(500); // Short pause for smooth transition


            // Take screenshot of the Home page after clicking
            String AboutusScreenshotPath = takeScreenshot("about us_page");
            test.addScreenCaptureFromPath(AboutusScreenshotPath);
            test.log(Status.PASS, "Screenshot of Aboutus page taken successfully");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }

        
       
        try {
            // Step 1: Click on "Contact" link
            WebElement contact = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact']")));
            contact.click();
            test.info("Clicked on the Contact link");

            // Wait for the Contact page to fully load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Contact')]")));
            test.info("Contact page is visible");
            
            // Take screenshot of the Contact page after clicking
            String contactScreenshotPath = takeScreenshot("contact_page");
            test.addScreenCaptureFromPath(contactScreenshotPath);
            test.log(Status.PASS, "Screenshot of Contact page taken successfully");


            // Medium scroll on the Contact page
            js.executeScript("window.scrollBy(0, 500);"); // Adjust the scroll value as needed to reach the medium level
            Thread.sleep(1000); // Pause to allow the scroll to complete

            // Step 2: Click the Gallery link in the sidebar
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebElement gallery = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Gallery']")));
            js.executeScript("arguments[0].scrollIntoView(true);", gallery); // Ensure Gallery link is in view
            Thread.sleep(500); // Short pause for smooth transition

            try {
                gallery.click();
                test.info("Clicked on the Gallery link in the sidebar");
            } catch (Exception e) {
                // Fallback to JavaScript click if regular click fails
                js.executeScript("arguments[0].click();", gallery);
                test.info("Clicked on the Gallery link using JavaScript Executor");
            }

            // Wait for the Gallery page to load fully
            
           WebElement photos= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Photo']")));
           try {
               photos.click();
               test.info("Clicked on the Photo link in the sidebar");
           } catch (Exception e) {
               // Fallback to JavaScript click if regular click fails
               js.executeScript("arguments[0].click();", photos);
               test.info("Clicked on the photos link using JavaScript Executor");
           }
           
           // Medium scroll on the Contact page
           js.executeScript("window.scrollBy(0, 500);"); // Adjust the scroll value as needed to reach the medium level
           Thread.sleep(1000); // Pause to allow the scroll to complete

           // Step 2: Click the Gallery link in the sidebar
         WebElement photo=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='probootstrap-section probootstrap-bg-white']")));

           js.executeScript("arguments[0].scrollIntoView(true);", photo); // Ensure Gallery link is in view
           Thread.sleep(500); // Short pause for smooth transition

            
            // Take screenshot of the Gallery page
            String photoScreenshotPath = takeScreenshot("gallery_page");
            test.addScreenCaptureFromPath(photoScreenshotPath);
            test.log(Status.PASS, "Screenshot of photo page taken successfully");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }
    
     
        try {
            // Step 1: Click on "Contact" link
            WebElement contact = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact']")));
            contact.click();
            test.info("Clicked on the Contact link");

            // Wait for the Contact page to fully load
          WebElement fullname=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Contact')]")));
            test.info("Contact page is visible");
            js.executeScript("arguments[0].scrollIntoView(true);",fullname);
            Thread.sleep(500); // Short pause for smooth transition
            
            String contactpage=takeScreenshot("Contact_page");
            test.addScreenCaptureFromPath(contactpage);
            
            test.log(Status.PASS, "Screenshot of Contact page taken successfully");

            
            


            // Step 2: Scroll down to the "Full Name" field
            WebElement fullNameLabel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']")));
           
            fullNameLabel.clear();
            fullNameLabel.sendKeys("John Doe");
            test.info("Filled in the Full Name field with 'John Doe'");
            
            
            WebElement fullemail = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));
            
            fullemail.clear();
            fullemail.sendKeys("Johndoe123@gmail.com");
            test.info("Filled in the Full email field with 'Johndoe123@gmail.com'");
            
            
           WebElement fullsubject = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='subject']")));
            
           fullsubject.clear();
           fullsubject.sendKeys("Bsc");
            test.info("Filled in the Full subject field with 'subject'");
            
            WebElement fullmessage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name='message']")));
            
            
            fullmessage.clear();
            fullmessage.sendKeys("i Expect more courses");
             test.info("Filled in the Full message field with 'i Expect more courses'");
             
             String formpage=takeScreenshot("form_Page");
             test.addScreenCaptureFromPath(formpage);
            
             test.log(Status.PASS, "Screenshot of Form page taken successfully");
             
             WebElement submit=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='submit']")));
             submit.click();

            
            
            
            
            
            

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }
 
    

        
        
        
        
        
        
        
        
        
        
        
        
        
        try {
            // Step 1: Click on "Contact" link
            WebElement contact = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact']")));
            contact.click();
            test.info("Clicked on the Contact link");

            // Wait for the Contact page to fully load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Contact')]")));
            test.info("Contact page is visible");

            // Take screenshot of the Contact page after clicking
            String contactScreenshotPath = takeScreenshot("contact_page");
            test.addScreenCaptureFromPath(contactScreenshotPath);
            test.log(Status.PASS, "Screenshot of Contact page taken successfully");

            
            // Medium scroll on the Contact page
            js.executeScript("window.scrollBy(0, 500);"); // Adjust the scroll value as needed
            Thread.sleep(1000); // Pause to allow the scroll to complete

            // Step 2: Click the "Our Group Of Companies" link in the sidebar
            WebElement ourcompany = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Our Group Of Companies']")));
            js.executeScript("arguments[0].scrollIntoView(true);", ourcompany); // Ensure the link is in view
            Thread.sleep(500); // Short pause for smooth transition

            try {
                ourcompany.click();
                test.info("Clicked on the 'Our Group Of Companies' link in the sidebar");
            } catch (Exception e) {
                // Fallback to JavaScript click if regular click fails
                js.executeScript("arguments[0].click();", ourcompany);
                test.info("Clicked on the 'Our Group Of Companies' link using JavaScript Executor");
            }

            // Step 3: Check for the error message on the next page
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'The requested URL was not found on this server.')]")));
            js.executeScript("arguments[0].scrollIntoView(true);", errorMessage); // Ensure the error message is in view
            Thread.sleep(500); // Short pause for smooth transition

            // Take screenshot of the error page
            String nextpageScreenshotPath = takeScreenshot("ourcompany_error_page");
            test.addScreenCaptureFromPath(nextpageScreenshotPath);
            test.log(Status.FAIL, "Error message displayed: 'The requested URL was not found on this server.' Screenshot taken.");

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
            String reportPath = "C:\\Users\\ADMIN\\Downloads\\Newfolders\\contact.html"; // Ensure this matches your actual report path
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