package testScript;

import java.lang.reflect.Method;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;

import configuration.KeyWordsAction;
import configuration.Constants;
import util.ExcelUtil;
import util.Log;

import org.testng.annotations.BeforeClass;;

public class TestSuiteByExcel {        // P360
    public static Method method[];
    public static String keyword;
    public static String locatorExpression;
    public static String value;
    public static KeyWordsAction keyWordsaction;
    public static int testStep;
    public static int testLastStep;
    public static String testCaseID;
    public static String testCaseRunFlag;
    public static boolean testResult;
    
    @Test
    public void testTestSuite() throws Exception {
    	keyWordsaction = new KeyWordsAction();
    	method = keyWordsaction.getClass().getMethods(); //java reflection machanism to get all methods of KeyWordsAction class
    	System.out.println(method.length); //Print the length of the method
    	String excelFilePath = Constants.Path_ExcelFile;
    	ExcelUtil.setExcelFile(excelFilePath);
    	int testCasesCount = ExcelUtil.getRowCount(Constants.Sheet_TestSuite);
    	System.out.println(testCasesCount);//Added by me.
    	
    	for(int testCaseNo = 1; testCaseNo <= testCasesCount; testCaseNo++) {
    		testCaseID = ExcelUtil.getCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_TestCaseID);
    		testCaseRunFlag = ExcelUtil.getCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_RunFlag);
    		
    		if(testCaseRunFlag.equalsIgnoreCase("y")) {
    			Log.startTestCase(testCaseID);
    			testResult = true;
    			testStep = ExcelUtil.getFirstRowContainsTestCaseID(Constants.Sheet_TestSteps, testCaseID, Constants.Col_TestCaseID);  			
    			testLastStep = ExcelUtil.getTestCaseLastStepRow(Constants.Sheet_TestSteps, testCaseID, testStep);
    			
    			for(; testStep < testLastStep; testStep++) {
    				keyword = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_KeyWordAction);
    				Log.info("The keyword read from Excel file is " + keyword);
    				locatorExpression = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_LocatorExpression);
    				value = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_ActionValue);
    				Log.info("The locator expression read from Excel file is " + value);
    				
    				execute_Action();
    				
    				if (testResult == false) {
    					ExcelUtil.setCellData("TestSuites", testCaseNo, Constants.Col_TestSuiteTestResult, "Test suite fails.");
    					Log.endTestCase(testCaseID);
    					break;
    				}	
    				if(testResult == true) {
    					ExcelUtil.setCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_TestSuiteTestResult, "Test suite succeeds.");
    				}
    			}
    		}
    	}
    } 
    
    //CORE: Manual tester adjusts the sequence of the test steps with different values to test the Automation tester's script.
    private static void execute_Action() {
    	try {
    		for(int i = 0; i < method.length; i++) {
    			if(method[i].getName().equals(keyword)) {
    				method[i].invoke(keyWordsaction, locatorExpression, value);
    				if(testResult == true) {
    					ExcelUtil.setCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_TestStepTestResult, "Test step succeeds.");
    					break;
    				} else {
    					ExcelUtil.setCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_TestStepTestResult, "Test step fails.");
    					KeyWordsAction.close_browser("", "");
    					break;
    				}
    			}
    		}
    	} catch (Exception e) {
    		Assert.fail("Execute, error occurs, test case execution falis.");
    	}
    }
    	
    @BeforeClass
    public void BeforeClass() {
    	DOMConfigurator.configure("log4j.xml");
    }
}
