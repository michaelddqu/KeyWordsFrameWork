package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectMap {    // P311
    Properties properties;
	
	public ObjectMap (String propFile) {	
		properties = new Properties();
		try {
			FileInputStream in = new FileInputStream(propFile);
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Read object file, error occurs.");
			e.printStackTrace();
		}
	}
	
	public By getLocator(String ElementNameInpropFile) throws Exception {	
		String locator = properties.getProperty(ElementNameInpropFile);
		String locatorType = locator.split(">")[0];
		String locatorValue = locator.split(">")[1];
		
		locatorValue = new String(locatorValue.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("Get locator type: " + locatorType + "\t Get locator expression: " + locatorValue);
		
		if(locatorType.toLowerCase().equals("id"))
			return By.id(locatorValue);
		else if(locatorType.toLowerCase().equals("name"))
			return By.name(locatorValue);
		else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return By.className(locatorValue);
		else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return By.tagName(locatorValue);
		else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return By.linkText(locatorValue);
		else if(locatorType.toLowerCase().equals("partiallinktext"))
			return By.partialLinkText(locatorValue);	
		else if((locatorType.toLowerCase().equals("csselector")) || (locatorType.toLowerCase().equals("css")))
			return By.cssSelector(locatorValue);
		else if(locatorType.toLowerCase().equals("xpath"))
			return By.xpath(locatorValue);	
		else
			throw new Exception("The input locator type is not defined in the program: " + locatorType);	
	}
}
