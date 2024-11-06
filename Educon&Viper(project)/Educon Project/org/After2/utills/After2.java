package org.After2.utills;

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

public class After2 implements SeleniumInterface {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() {
        // ExtentReports setup
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\Downloads\\Newfolders\\After.html");
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

    private void initializeWebDriver() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get("https://educongroups.com/");
    }

    private void runBrowserTests(String browser) {
        test = extent.createTest("Run Tests on " + browser);
        test.info("Tests are running on " + browser);
    }

    @Test
    public void testPageNavigation() throws IOException, InterruptedException {
        test = extent.createTest("Test Page Navigation");

        try {
           
          
           
            

            
            WebElement After1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='After +12']")));
            After1.click();
            test.info("Clicked on the After link");
            
          
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebDriverWait time=new WebDriverWait(driver,Duration.ofSeconds(20));
            WebElement Engineer = time.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Engineering']")));
            
          
            // Try clicking with regular click first
            try {
            	Engineer.click();
                test.info("Clicked on the Engineer link");
            } catch (Exception e) {
                // If regular click fails, try JavaScript click
                js.executeScript("arguments[0].click();", Engineer);
                test.info("Clicked on the Engineer link using JavaScript Executor");
            }
            
           
           
            
            WebElement nextpages = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='probootstrap-text probootstrap-animate fadeInUp probootstrap-animated']")));
            js.executeScript("arguments[0].scrollIntoView(true);",nextpages ); // Ensure Gallery link is in view
            Thread.sleep(500); 
           
            js.executeScript("window.scrollBy(0, 500);"); 
            Thread.sleep(1000); 



            
            String EngineerScreenshotPath = takeScreenshot("Engineer_page");
            test.addScreenCaptureFromPath(EngineerScreenshotPath);
            test.log(Status.PASS, "Screenshot of Engineer page taken successfully");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }
    
        
    
    

    try {
       
        WebElement After = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='After +12']")));
        After.click();
        test.info("Clicked on the After link");

        
        WebElement Medical = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Medicine']")));
        
      
        // Try clicking with regular click first
        try {
        	Medical.click();
            test.info("Clicked on the Medical link");
        } catch (Exception e) {
            // If regular click fails, try JavaScript click
            js.executeScript("arguments[0].click();", Medical);
            test.info("Clicked on the Medical link using JavaScript Executor");
        }
        
       
       
        
        WebElement nextpages = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@class='probootstrap-section probootstrap-section-colored']")));
        js.executeScript("arguments[0].scrollIntoView(true);",nextpages ); // Ensure Gallery link is in view
        Thread.sleep(500); 
       
        js.executeScript("window.scrollBy(0, 500);"); 
        Thread.sleep(1000); 



        
        String MedicalScreenshotPath = takeScreenshot("Medical_page");
        test.addScreenCaptureFromPath(MedicalScreenshotPath);
        test.log(Status.PASS, "Screenshot of Medical page taken successfully");

    } catch (Exception e) {
        test.fail("Test failed due to exception: " + e.getMessage());
        e.printStackTrace();
    }

    
    
    
    try {
        
        WebElement After = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='After +12']")));
        After.click();
        test.info("Clicked on the After link");

        
        WebElement Arts = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Arts']")));
        
      
        // Try clicking with regular click first
        try {
        	Arts.click();
            test.info("Clicked on the Arts link");
        } catch (Exception e) {
            // If regular click fails, try JavaScript click
            js.executeScript("arguments[0].click();", Arts);
            test.info("Clicked on the Arts link using JavaScript Executor");
        }
        
       
       
        
        WebElement nextpages = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@class='probootstrap-section probootstrap-section-colored']")));
        js.executeScript("arguments[0].scrollIntoView(true);",nextpages ); // Ensure Gallery link is in view
        Thread.sleep(500); 
       
        js.executeScript("window.scrollBy(0, 500);"); 
        Thread.sleep(1000); 



        
        String ArtsScreenshotPath = takeScreenshot("Arts_page");
        test.addScreenCaptureFromPath(ArtsScreenshotPath);
        test.log(Status.PASS, "Screenshot of Arts page taken successfully");

    } catch (Exception e) {
        test.fail("Test failed due to exception: " + e.getMessage());
        e.printStackTrace();
    }
    
    
    
    
 try {
        
        WebElement After = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='After +12']")));
        After.click();
        test.info("Clicked on the After link");

        
        WebElement Management = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Management']")));
        
      
        // Try clicking with regular click first
        try {
        	Management.click();
            test.info("Clicked on the Management link");
        } catch (Exception e) {
            // If regular click fails, try JavaScript click
            js.executeScript("arguments[0].click();", Management);
            test.info("Clicked on the Management link using JavaScript Executor");
        }
        
        // Take screenshot of the error page
        String nextpageScreenshotPath = takeScreenshot("Management page");
        test.addScreenCaptureFromPath(nextpageScreenshotPath);
        test.log(Status.FAIL, "Error message displayed: 'Not open Expected page.' Screenshot taken.");

    } catch (Exception e) {
        test.fail("Test failed due to exception: " + e.getMessage());
        e.printStackTrace();
    }


    
    
    
try {
        
        WebElement After = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='After +12']")));
        After.click();
        test.info("Clicked on the After link");

        
        WebElement Reasearch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Reseach']")));
        
      
        // Try clicking with regular click first
        try {
        	Reasearch.click();
            test.info("Clicked on the Reasearch link");
        } catch (Exception e) {
            // If regular click fails, try JavaScript click
            js.executeScript("arguments[0].click();",Reasearch);
            test.info("Clicked on the  Reasearch link using JavaScript Executor");
        }
        
        // Take screenshot of the error page
        String nextpageScreenshotPath = takeScreenshot("Reasearch page");
        test.addScreenCaptureFromPath(nextpageScreenshotPath);
        test.log(Status.FAIL, "Error message displayed: 'Not open Expected page.' Screenshot taken.");

    } catch (Exception e) {
        test.fail("Test failed due to exception: " + e.getMessage());
        e.printStackTrace();
    }


      
 
try {
        
        WebElement After = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='After +12']")));
        After.click();
        test.info("Clicked on the After link");

        
        WebElement Science = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Science']")));
        
      
        // Try clicking with regular click first
        try {
        	Science.click();
            test.info("Clicked on the Science link");
        } catch (Exception e) {
            // If regular click fails, try JavaScript click
            js.executeScript("arguments[0].click();",Science);
            test.info("Clicked on the  Science link using JavaScript Executor");
        }
        
        // Take screenshot of the error page
        String nextpageScreenshotPath = takeScreenshot("Science page");
        test.addScreenCaptureFromPath(nextpageScreenshotPath);
        test.log(Status.FAIL, "Error message displayed: 'Not open Expected page.' Screenshot taken.");

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
         String reportPath = "C:\\Users\\ADMIN\\Downloads\\Newfolders\\After.html"; // Ensure this matches your actual report path
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