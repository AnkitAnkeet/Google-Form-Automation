package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;


public class TestCases {
    ChromeDriver driver;
   
   

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
    
   

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        //wrappers = new Wrappers(driver);
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
    @Test
    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: Google Form");
        //get to google form
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
        Thread.sleep(9000);
        try{
           
            //fill the name textbox
            Wrappers.writeText(driver, By.xpath("//span[text()='Name']/../../..//following-sibling::div//input"),"Crio Learner");
            //fill the reason in textbox with ""I want to be the best QA Engineer! 1710572021(variable)"
            Wrappers.writeText(driver, By.xpath("//textarea[@class='KHxj8b tL9Q4c']"), "I want to be the best QA Engineer! " + Wrappers.calculateEpochTimeToString(0));
            //rate the testing experience via the radio button
            Wrappers.clickOnCheckBox(driver, By.xpath("//div[@class='SG0AAe']//span[contains(@class,'OIC90c')]"),"3 - 5");
            //select java
            Wrappers.clickOnCheckBox(driver, By.xpath("//span[@class='aDTYNe snByac n5vBHf OIC90c']"),"Java");
            //select Selenium
            Wrappers.clickOnCheckBox(driver, By.xpath("//span[@class='aDTYNe snByac n5vBHf OIC90c']"),"Selenium");
            //select TestNG
            Wrappers.clickOnCheckBox(driver, By.xpath("//span[@class='aDTYNe snByac n5vBHf OIC90c']"),"TestNG");
            //click on the dropdown
            Wrappers.click(driver, By.xpath("//div[@class='e2CuFe eU809d']"));
            //select from dropdown
            Wrappers.selectFromDropdown(driver, By.xpath("//div[contains(@class,'ncFHed')]/div//span[normalize-space(text())]"),"Mr");
            //give the seven date before current date in the field
            Wrappers.writeText(driver, By.xpath("//input[@type='date']"), Wrappers.calculateDateTimeToString("dd/MM/yyyy", (long) 6.048e+8 ));

            //provide the current time (hour)
            Wrappers.writeText(driver, By.xpath("//input[@aria-label='Hour']"), Wrappers.calculateDateTimeToString("HH",0));
             //provide the current time (minute)
             Wrappers.writeText(driver, By.xpath("//input[@aria-label='Minute']"), Wrappers.calculateDateTimeToString("mm",0));
             //click on submit button
             Wrappers.click(driver, By.xpath("//span[text()='Submit']"));
             
             //verify page 
             Thread.sleep(5000);
             System.out.println(Wrappers.returnText(driver, By.xpath("//div[@class='vHW8K']")));


            

        }catch (Exception e){
                e.printStackTrace();
                System.out.println("Failed");
        }
        System.out.println("End Test case = Google form");
        
    }



}