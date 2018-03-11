package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {   //P315
    public static void sleep(long millisecond) {
    	try {
    		Thread.sleep(millisecond);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    //Delete, different with book !!!
//    public static void waitWebElement(WebDriver driver, String xpathExpression) {
//    	WebDriverWait wait = new WebDriverWait(driver, 10);
//    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
//    }
    
    public static void waitWebElement(WebDriver driver, By by) {
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
}
