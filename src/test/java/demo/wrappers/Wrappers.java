package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    //private ChromeDriver driver;

    // Constructor that accepts a ChromeDriver object
    // public Wrappers(ChromeDriver driver) {
    //     this.driver = driver;
    
    public static void writeText(ChromeDriver driver, By selector, String textToSend) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        WebElement element = driver.findElement(selector);
        element.clear();
        element.sendKeys(textToSend);
        Thread.sleep(3000);
    }


    public static String calculateDateTimeToString(String formatString, long offsetInMs){
        LocalDateTime now = LocalDateTime.now();
       long seconds = offsetInMs/1000;
        long nanos = (offsetInMs%1000)*1000000;
        LocalDateTime newDateTime = now.minus(Duration.ofSeconds(seconds, nanos));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
        String formattedDateTime = newDateTime.format(formatter);

        return formattedDateTime;

    }


    public static String calculateEpochTimeToString(int offsetInMs){
        Instant now = Instant.now();
        Instant newInstant = now.plusMillis(offsetInMs);
        long epochMilli = newInstant.toEpochMilli();
        return String.valueOf(epochMilli);
    }

    public static void clickOnCheckBox(ChromeDriver driver,By locator, String checkBoxText){
        List<WebElement> checkboxes = driver.findElements(locator);
        
        for(WebElement checkbox: checkboxes){
            if(checkbox.getText().trim().equals(checkBoxText)){
                checkbox.findElement(By.xpath("./../../preceding-sibling::div")).click();
                break;
            }
        }


    }


    public static void selectFromDropdown(ChromeDriver driver, By locator, String textToSelect) throws Exception {
       List<WebElement> dropdownElements = driver.findElements(locator);
       
       for(WebElement dropdownElement: dropdownElements){
           if(dropdownElement.getText().trim().equals(textToSelect)){
            dropdownElement.click();
            break;
            }
        }
    }



    public static void handleAlert(ChromeDriver driver, Boolean confirm) throws InterruptedException{
        Alert alert = driver.switchTo().alert();
        Thread.sleep(3000);

        if(confirm){
            alert.accept();

        }else{
            alert.dismiss();
        }
    }

    public static void click(ChromeDriver driver, By locator) throws InterruptedException{

     driver.findElement(locator).click();
     Thread.sleep(3000);
    }


    public static String returnText(ChromeDriver driver, By locator){
        
        return driver.findElement(locator).getText();
    }
    
}

