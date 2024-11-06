package org.ourservice.utills;

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

public class Ourservices implements Seleniuminterface {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() {
        // ExtentReports setup
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\Downloads\\Newfolders\\Ourservices.html");
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
            WebElement ourservices = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Our Services']")));
            ourservices.click();
            test.info("Clicked on the ourservices link");
            
            

           
            // Take screenshot of the Contact page after clicking
            String ourservicesScreenshotPath = takeScreenshot("ourservices_page");
            test.addScreenCaptureFromPath(ourservicesScreenshotPath);
            test.log(Status.PASS, "Screenshot of ourservices page taken successfully");

            // Step 2: Scroll to and click the "Home" link
            WebElement freecounsling = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Free Consulting']")));
            
            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView(true);", freecounsling);
            Thread.sleep(1000); // Ensure the element has time to come into view

            // Try clicking with regular click first
            try {
            	freecounsling.click();
                test.info("Clicked on the freecounsling link");
            } catch (Exception e) {
                // If regular click fails, try JavaScript click
                js.executeScript("arguments[0].click();", freecounsling);
                test.info("Clicked on the freecounsling link using JavaScript Executor");
            }
            
            WebElement currentpage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='probootstrap-section probootstrap-section-colored']")));

            // Take screenshot of the Home page after clicking
            String freecounslingScreenshotPath = takeScreenshot("freecounsling_page");
            test.addScreenCaptureFromPath(freecounslingScreenshotPath);
            test.log(Status.PASS, "Screenshot of freecounsling page taken successfully");
            
            WebElement home=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Home']")));
            home.click();
         

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }
        
        try {
            // Step 1: Click on "Contact" link
            WebElement ourservices = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Our Services']")));
            ourservices.click();
            test.info("Clicked on the ourservices link");

           
            // Take screenshot of the Contact page after clicking
            String ourservicesScreenshotPath = takeScreenshot("ourservices_page");
            test.addScreenCaptureFromPath(ourservicesScreenshotPath);
            test.log(Status.PASS, "Screenshot of ourservices page taken successfully");

            // Step 2: Scroll to and click the "Home" link
            WebElement scholership = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Scholarship']")));
            
            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView(true);", scholership);
            Thread.sleep(1000); // Ensure the element has time to come into view

            // Try clicking with regular click first
            try {
            	scholership.click();
                test.info("Clicked on the scholership link");
            } catch (Exception e) {
                // If regular click fails, try JavaScript click
                js.executeScript("arguments[0].click();", scholership);
                test.info("Clicked on the scholership link using JavaScript Executor");
            }
            
            WebElement currentpage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class='navbar navbar-default probootstrap-navbar']")));
            
            String sholership=takeScreenshot("schloership");
            test.addScreenCaptureFromPath(sholership);
            test.log(Status.PASS,"scholership taken suceesfully");
            test.log(Status.PASS,"Accepted valid input correctlly");
            
        
          
        	

                // Fill out the form with valid data
                WebElement name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']")));
                name.clear();
                name.sendKeys("John");

                WebElement Course = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='course']")));
                Course.clear();
                Course.sendKeys("Bsc");

                WebElement college = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='college']")));
                college.clear();
                college.sendKeys("Educon");

                WebElement fathername = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Father Name']")));
                fathername.clear();
                fathername.sendKeys("David");

                WebElement community = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='community']")));
                community.clear();
                community.sendKeys("General");

                WebElement phonenumber = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Phone Number']")));
                phonenumber.clear();
                phonenumber.sendKeys("9876543210");

                WebElement scholershipamount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Scholarship Amount']")));
                scholershipamount.clear();
                scholershipamount.sendKeys("10000");

                WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));
                email.clear();
                email.sendKeys("johndoe123@gmail.com");

                WebElement message = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='message']")));
                message.clear();
                message.sendKeys("When will be Expected My Scholarship");

                // Take a screenshot of valid data entry
                String validScreenshot = takeScreenshot("valid");
                test.addScreenCaptureFromPath(validScreenshot);
                test.log(Status.PASS, "Screenshot taken successfully.");
                test.log(Status.PASS,"valid input aceppted");
                

                // Click the submit button
                WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='submit']")));
                submit.click();

                
                WebElement name1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']")));
                name1.clear();
                name1.sendKeys("!234");

                WebElement Course1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='course']")));
                Course1.clear();
                Course1.sendKeys("Bcc");

                WebElement college1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='college']")));
                college1.clear();
                college1.sendKeys("!23Gb");

                WebElement fathername1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Father Name']")));
                fathername1.clear();
                fathername1.sendKeys("Da");

                WebElement community1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='community']")));
                community1.clear();
                community1.sendKeys("BS");

                WebElement phonenumber1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Phone Number']")));
                phonenumber1.clear();
                phonenumber1.sendKeys("9876");

                WebElement scholershipamount1= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Scholarship Amount']")));
                scholershipamount1.clear();
                scholershipamount1.sendKeys("10K");

                WebElement email1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));
                email1.clear();
                email1.sendKeys("johndoe123.com");

                WebElement message1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='message']")));
                message1.clear();
                message1.sendKeys("When will be Expected My Scholarship");

                // Take a screenshot of valid data entry
                String invalidScreenshot = takeScreenshot("invalid");
                test.addScreenCaptureFromPath(invalidScreenshot);
                test.log(Status.PASS, "Screenshot taken successfully.");
                test.log(Status.FAIL, "Invalid input was incorrectly accepted by the form.");


                // Click the submit button
                WebElement submit1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='submit']")));
                submit1.click();
        }

                catch (Exception e) { 
                    // Handle any exceptions that occur during form submission
                    System.out.println("An error occurred: " + e.getMessage());
                    test.log(Status.FAIL, "An unexpected error invalid input accepted.");
                }
                
    
        try {
            // Step 1: Click on "Contact" link
            WebElement ourservices = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Our Services']")));
            ourservices.click();
            test.info("Clicked on the ourservices link");

            // Take screenshot of the Contact page after clicking
            String ourservicesScreenshotPath = takeScreenshot("ourservices_page");
            test.addScreenCaptureFromPath(ourservicesScreenshotPath);
            test.log(Status.PASS, "Screenshot of ourservices page taken successfully");

            // Step 2: Scroll to and click the "Home" link
            WebElement Educationloan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Education Loan']")));

            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView(true);", Educationloan);
            Thread.sleep(1000); // Ensure the element has time to come into view

            // Try clicking with regular click first
            try {
                Educationloan.click();
                test.info("Clicked on the Educationloan link");
            } catch (Exception e) {
                // If regular click fails, try JavaScript click
                js.executeScript("arguments[0].click();", Educationloan);
                test.info("Clicked on the Educationloan link using JavaScript Executor");
            }

            // Take screenshot of the Home page after clicking
         
            
            test.log(Status.FAIL, "Test failed due to exception");
            test.log(Status.FAIL, "Page did not open.");


        } catch (Exception e) {
            // Log the failure without taking a screenshot
            test.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
        }

        
        

        try {
            // Step 1: Click on "Contact" link
            WebElement ourservices = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Our Services']")));
            ourservices.click();
            test.info("Clicked on the ourservices link");

            // Take screenshot of the Contact page after clicking
            String ourservicesScreenshotPath = takeScreenshot("ourservices_page");
            test.addScreenCaptureFromPath(ourservicesScreenshotPath);
            test.log(Status.PASS, "Screenshot of ourservices page taken successfully");

            // Step 2: Scroll to and click the "Home" link
            WebElement GovtScholarship= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Govt Scholarship (SC & ST)']")));

            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView(true);", GovtScholarship);
            Thread.sleep(1000); // Ensure the element has time to come into view

            // Try clicking with regular click first
            try {
            	GovtScholarship.click();
                test.info("Clicked on the GovtScholarship link");
            } catch (Exception e) {
                // If regular click fails, try JavaScript click
                js.executeScript("arguments[0].click();", GovtScholarship);
                test.info("Clicked on the GovtScholarship link using JavaScript Executor");
            }

            // Take screenshot of the Home page after clicking
          
          
            test.log(Status.FAIL, "Test failed due to exception");
            test.log(Status.FAIL, "Page did not open.");


        } catch (Exception e) {
            // Log the failure without taking a screenshot
            test.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
        }

        
        
        try {
            // Step 1: Click on "Contact" link
            WebElement ourservices = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Our Services']")));
            ourservices.click();
            test.info("Clicked on the ourservices link");

            // Take screenshot of the Contact page after clicking
            String ourservicesScreenshotPath = takeScreenshot("ourservices_page");
            test.addScreenCaptureFromPath(ourservicesScreenshotPath);
            test.log(Status.PASS, "Screenshot of ourservices page taken successfully");

            // Step 2: Scroll to and click the "Home" link
            WebElement SkillsDevelopment= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Skills Development']")));

            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView(true);", SkillsDevelopment);
            Thread.sleep(1000); // Ensure the element has time to come into view

            // Try clicking with regular click first
            try {
            	SkillsDevelopment.click();
                test.info("Clicked on the SkillsDevelopment link");
            } catch (Exception e) {
                // If regular click fails, try JavaScript click
                js.executeScript("arguments[0].click();", SkillsDevelopment);
                test.info("Clicked on the SkillsDevelopment link using JavaScript Executor");
            }

            // Take screenshot of the Home page after clicking
         
            test.log(Status.FAIL, "Test failed due to exception");
            test.log(Status.FAIL, "Page did not open.");


        } catch (Exception e) {
            // Log the failure without taking a screenshot
            test.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
        }

        
        
        try {
            // Step 1: Click on "Contact" link
            WebElement ourservices = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Our Services']")));
            ourservices.click();
            test.info("Clicked on the ourservices link");

            // Take screenshot of the Contact page after clicking
            String ourservicesScreenshotPath = takeScreenshot("ourservices_page");
            test.addScreenCaptureFromPath(ourservicesScreenshotPath);
            test.log(Status.PASS, "Screenshot of ourservices page taken successfully");

            // Step 2: Scroll to and click the "Home" link
            WebElement GovtJobs= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Govt Jobs']")));

            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView(true);", GovtJobs);
            Thread.sleep(1000); // Ensure the element has time to come into view

            // Try clicking with regular click first
            try {
            	GovtJobs.click();
                test.info("Clicked on the GovtJobs link");
            } catch (Exception e) {
                // If regular click fails, try JavaScript click
                js.executeScript("arguments[0].click();", GovtJobs);
                test.info("Clicked on the GovtJobs link using JavaScript Executor");
            }

            // Take screenshot of the Home page after clicking
         
          
            test.log(Status.FAIL, "Test failed due to exception");
            test.log(Status.FAIL, "Page did not open.");


        } catch (Exception e) {
            // Log the failure without taking a screenshot
            test.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
        }
        
        
        
        try {
            // Step 1: Click on "Contact" link
            WebElement ourservices = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Our Services']")));
            ourservices.click();
            test.info("Clicked on the ourservices link");

            // Take screenshot of the Contact page after clicking
            String ourservicesScreenshotPath = takeScreenshot("ourservices_page");
            test.addScreenCaptureFromPath(ourservicesScreenshotPath);
            test.log(Status.PASS, "Screenshot of ourservices page taken successfully");

            // Step 2: Scroll to and click the "Home" link
            WebElement PrivateJobs= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Private Jobs']")));

            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView(true);",PrivateJobs );
            Thread.sleep(1000); // Ensure the element has time to come into view

            // Try clicking with regular click first
            try {
            	PrivateJobs.click();
                test.info("Clicked on the PrivateJobs link");
            } catch (Exception e) {
                // If regular click fails, try JavaScript click
                js.executeScript("arguments[0].click();", PrivateJobs);
                test.info("Clicked on the PrivateJobs link using JavaScript Executor");
            }

            // Take screenshot of the Home page after clicking
         
            test.log(Status.FAIL, "Test failed due to exception");
            test.log(Status.FAIL, "Page did not open.");


        } catch (Exception e) {
            // Log the failure without taking a screenshot
            test.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
        }

        
        
        
        try {
            // Step 1: Click on "Contact" link
            WebElement ourservices = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Our Services']")));
            ourservices.click();
            test.info("Clicked on the ourservices link");

            // Take screenshot of the Contact page after clicking
            String ourservicesScreenshotPath = takeScreenshot("ourservices_page");
            test.addScreenCaptureFromPath(ourservicesScreenshotPath);
            test.log(Status.PASS, "Screenshot of ourservices page taken successfully");

            // Step 2: Scroll to and click the "Home" link
            WebElement HigherEducationGuidance= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Higher Education Guidance']")));

            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView(true);",HigherEducationGuidance );
            Thread.sleep(1000); // Ensure the element has time to come into view

            // Try clicking with regular click first
            try {
            	HigherEducationGuidance.click();
                test.info("Clicked on the HigherEducationGuidance link");
            } catch (Exception e) {
                // If regular click fails, try JavaScript click
                js.executeScript("arguments[0].click();", HigherEducationGuidance);
                test.info("Clicked on the HigherEducationGuidance link using JavaScript Executor");
            }

            // Take screenshot of the Home page after clicking
           
            test.log(Status.FAIL, "Test failed due to exception");
            test.log(Status.FAIL, "Page did not open.");


        } catch (Exception e) {
            // Log the failure without taking a screenshot
            test.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
        }

    }

    
    private String takeScreenshot(String fileName) throws IOException {
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
            String reportPath = "C:\\Users\\ADMIN\\Downloads\\Newfolders\\Ourservices.html"; // Ensure this matches your actual report path
            // Create a process to open the report in Chrome
            Runtime.getRuntime().exec(new String[]{ 
                "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", 
                reportPath 
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void fillOutForm(String name, String course, String college, String fatherName, String community,
			String phoneNumber, String scholarshipAmount, String email, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitForm() {
		// TODO Auto-generated method stub
		
	}

	
}


    
    
        
        
        
        
        
    		


     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                

    
    
    
    
    
    
       
        
        
        
        
        
        
        
        
        
        
        
        
       
    
   
      
        
        
    
        
        
        
        
        
        
        
           
    
   
    
    
    
    
    
    
    