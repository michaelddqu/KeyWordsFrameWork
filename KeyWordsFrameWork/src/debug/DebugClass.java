package debug;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DebugClass {
	
	//@Test
	public static void open_browser() throws InterruptedException {
    	System.setProperty("webdriver.chrome.driver", "D:\\eclipse\\eclipse-workspace\\KeyWordsFrameWork\\lib\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    
//    	System.setProperty("webdriver.ie.driver", "D:\\eclipse\\eclipse-workspace\\KeyWordsFrameWork\\lib\\IEDriverServer.exe");
//    	WebDriver driver = new InternetExplorerDriver();
		
//		System.setProperty("webdriver.firefox.bin", "D:\\Mozilla Firefox\\firefox.exe");
//		System.setProperty("webdriver.gecko.driver", "D:\\eclipse\\eclipse-workspace\\KeyWordsFrameWork\\lib\\geckodriver.exe");
//    	WebDriver driver = new FirefoxDriver();
		
//        driver.navigate().to("https://mail.126.com/");
//        Thread.sleep(3000);
//        
//        driver.findElement(By.className("loginFuncNormal")).click();
//        Thread.sleep(3000);
//        
//    	driver.findElement(By.id("auto-id-1520056627858")).clear();
//    	driver.findElement(By.id("auto-id-1520056627858")).sendKeys("zejunqu");
//    	driver.findElement(By.name("password")).clear();
//    	driver.findElement(By.name("password")).sendKeys("0123456qu");   
//    	driver.findElement(By.id("dologin")).click(); 
    	
      driver.navigate().to("http://mail.sina.com.cn/");
      //driver.findElement(By.linkText("��������")).click();
      Thread.sleep(1000);
      
      //driver.findElement(By.id("switcher_plogin")).click();
      Thread.sleep(3000);
      
  	driver.findElement(By.id("freename")).click();
  	driver.findElement(By.id("freename")).sendKeys("zejunqu@sina.com.cn");
  	driver.findElement(By.id("freepassword")).clear();
  	driver.findElement(By.id("freepassword")).sendKeys("0123456qu");   
  	driver.findElement(By.className("loginBtn")).click(); 
    	
    }
	
	@Test
	public static void addTestCaseInTestNG1() {
		Assert.assertTrue(true);
	}
	
	@Test
	public static void addTestCaseInTestNG2() {
		Assert.assertTrue(true);
	}
}
