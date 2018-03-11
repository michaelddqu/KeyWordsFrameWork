package configuration;

import java.util.List;
import java.util.Random;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import testScript.TestSuiteByExcel;
import util.KeyBoardUtil;
import util.Log;
import util.ObjectMap;
import util.WaitUtil;

public class KeyWordsAction {    // P355
    public static WebDriver driver;
    
    private static ObjectMap objectMap = new ObjectMap(Constants.Path_ConfigurationFile);
    static {
    	DOMConfigurator.configure("log4j.xml");
    }
    
    public static void open_browser(String string, String browserName) {
    	if (browserName.equals("ie")) {
    		System.setProperty("webdriver.ie.driver", "D:\\eclipse\\webdriver\\IEDriverServer.exe");
    		driver = new InternetExplorerDriver();
    		Log.info("IE isdeclared。");
    	} else if (browserName.equals("firefox")) {
    		System.setProperty("webdriver.firefox.bin", "D:\\Mozilla Firefox\\firefox.exe");
    		System.setProperty("webdriver.gecko.driver", "D:\\eclipse\\webdriver\\lib\\geckodriver.exe");
    		driver = new FirefoxDriver();
    		Log.info("Firefox is declared.");
    	} else {
    		System.setProperty("webdriver.chrome.driver", "D:\\eclipse\\webdriver\\chromedriver.exe");
    		ChromeOptions options = new ChromeOptions();
    		options.addArguments("user-data-dir=C:\\Users\\Michael\\AppData\\Local\\Google\\Chrome\\User Data");
    		//options.addArguments("user-data-dir=C:/Users/Michael/AppData/Local/Google/Chrome/User Data");
    		driver = new ChromeDriver(options);
    		//driver = new ChromeDriver();
    		Log.info("Chrome is declared.");
    	}
    }
    
    //Add navigate method to test
    public static void navigate(String string, String url) {
        driver.navigate().to(url);
        Log.info("Explorer visits" + url);
    }
    
    public static void input(String locatorExpression, String inputString) throws InterruptedException {
    	Thread.sleep(3000);
    	try {
    		driver.findElement(objectMap.getLocator(locatorExpression)).clear();           //Page object model is NOT USED in KeyWords driver framework.
    		Log.info("Remove " + locatorExpression + "'s content.");   		
    		driver.findElement(objectMap.getLocator(locatorExpression)).sendKeys(inputString);
    		Log.info("In " + locatorExpression + " input " + inputString);
    	} catch (Exception e) {
    		TestSuiteByExcel.testResult = false;
    		Log.info("In " + locatorExpression + " input " + inputString + " error occurs " + e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    public static void click(String locatorExpression, String string) {
    	try {
    		driver.findElement(objectMap.getLocator(locatorExpression)).click();
    		Log.info("Click " + locatorExpression + " element successfully");
    	} catch (Exception e)  {
    		TestSuiteByExcel.testResult = false;
    		Log.info("Click " + locatorExpression + " element not successfully " + e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    public static void WaitFor_Element(String locatorExpression, String string) {
    	try {
    		WaitUtil.waitWebElement(driver, objectMap.getLocator(locatorExpression));//different with book
    		Log.info("Explicitly wait for element successufully, the element is " + locatorExpression);
    	} catch (Exception e)  {
    		TestSuiteByExcel.testResult = false;
    		Log.info("Explicitly wait for element, error occurs, the message is " + e.getMessage());
    		e.printStackTrace();
    	}
    }

	public static void press_Tab(String string1, String string2) {
    	try {
    		Thread.sleep(2000);
    		KeyBoardUtil.pressTabKey();
    		Log.info("Click Tab key successfully.");
    	} catch (Exception e)  {
    		TestSuiteByExcel.testResult = false;
    		Log.info("Click Tab key, error occurs, the message is " + e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    public static void pasteString(String string, String pasteContent) {
    	try {
    		KeyBoardUtil.setAndCtrlVClipboardData(pasteContent);
    		Log.info("Paste main text successfully: " + pasteContent);
    	} catch (Exception e)  {
    		TestSuiteByExcel.testResult = false;
    		Log.info("Paste main text, error occurs, the message is " + e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    public static void press_enter(String string1, String string2) {
    	try {
    		Thread.sleep(2000);
    		KeyBoardUtil.pressEnterKey();
    		Log.info("Click Enter key successfully.");
    	} catch (Exception e)  {
    		TestSuiteByExcel.testResult = false;
    		Log.info("Click Enter key, error occurs, the message is " + e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    public static void sleep(String string, String sleepTime) {
    	try {
    		WaitUtil.sleep(Integer.parseInt(sleepTime));
    		Log.info("Sleep " + Integer.parseInt(sleepTime)/1000 + " second successfully.");
    	} catch (Exception e)  {
    		TestSuiteByExcel.testResult = false;
    		Log.info("Sleep, error occurs, the message is " + e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    public static void click_sendMailButton(String locatorExpression, String string) {
    	try {
    		List<WebElement> buttons = driver.findElements(objectMap.getLocator(locatorExpression));
    		buttons.get(0).click();
    		Log.info("Click to send email successfully.");
    		System.out.println("Send button is clicked.");
    	} catch (Exception e)  {
    		TestSuiteByExcel.testResult = false;
    		Log.info("Click to send email, error occurs, the message is  " + e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    public static void Assert_String(String string, String assertString) {
    	try {
    		Assert.assertTrue(driver.getPageSource().contains(assertString));
    		Log.info("Assert keyword successfully\"" + assertString + "\"");
    	} catch (Exception e)  {
    		TestSuiteByExcel.testResult = false;
    		Log.info("Assert, error occurs, the message is " + e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    public static void close_browser(String string1, String string2) {
    	try {
    		System.out.println("Explorer close method is executed.");
    		Log.info("Close explorer.");
    		driver.quit();
    	} catch (Exception e)  {
    		TestSuiteByExcel.testResult = false;
    		Log.info("Close explorer, error occurs, the message is " + e.getMessage());
    		e.printStackTrace();
    	}
    }
}
