package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;

import configuration.Constants;
import testScript.TestSuiteByExcel;

public class ExcelUtil {                     // P338
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
    public static void setExcelFile(String Path) throws Exception {	//ADDED. seems this method is duplicate comparing the below one
    	FileInputStream ExcelFile;
    	try {   		
    		ExcelFile = new FileInputStream(Path);
    		ExcelWBook = new XSSFWorkbook(ExcelFile);
    	} catch (Exception e) {
    		TestSuiteByExcel.testResult = false;
    		System.out.println("Excel path set fails.");
    		e.printStackTrace();
    	}
    }
	
    public static void setExcelFile(String Path, String SheetName) throws Exception {  	
    	FileInputStream ExcelFile;
    	try {   		
    		ExcelFile = new FileInputStream(Path);
    		ExcelWBook = new XSSFWorkbook(ExcelFile);
    		ExcelWSheet = ExcelWBook.getSheet(SheetName);
    	} catch (Exception e) {
    		TestSuiteByExcel.testResult = false;
    		System.out.println("Excel path set fails.");
    		e.printStackTrace();
    	}
    }
    
    //get data from specific cell
	public static String getCellData(String sheetName, int RowNum, int ColNum) {
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		try {
		    Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		    String CellData = Cell.getCellType() == XSSFCell.CELL_TYPE_STRING ?
		    		Cell.getStringCellValue() + "" : String.valueOf(Math.round(Cell.getNumericCellValue()));
		    return CellData;
		} catch (Exception e) {
    		TestSuiteByExcel.testResult = false;
    		e.printStackTrace();
			return "";
		}
	}
	
	public static int getLastRowNum() {	  //ADDED compared with Data driven framework.
		return ExcelWSheet.getLastRowNum();
	}
	
	public static int getRowCount(String SheetName) {	
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int number = ExcelWSheet.getLastRowNum();
		return number;
	}
	
	public static int getFirstRowContainsTestCaseID(String sheetName, String testCaseName, int colNum) {  //ADDED
		int i;
		try {
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			int rowCount = ExcelUtil.getRowCount(sheetName);
			for(i = 0; i < rowCount; i++) {
				if(ExcelUtil.getCellData(sheetName, i, colNum).equalsIgnoreCase(testCaseName)) {
					break;
				}
			}
			return i;
		} catch (Exception e) {
			TestSuiteByExcel.testResult = false;
			return 0;
		}
	}
	
	public static int getTestCaseLastStepRow(String SheetName, String testCaseID, int testCaseStartRowNumber) { //Added
		try {
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			for(int i = testCaseStartRowNumber; i <= ExcelUtil.getRowCount(SheetName) - 1; i++) {
				if(!testCaseID.equals(ExcelUtil.getCellData(SheetName, i, Constants.Col_TestCaseID))) {
					int number = i;
					return number;
				}
			}
		    int number = ExcelWSheet.getLastRowNum() + 1;
		    return number;
		} catch(Exception e) {
			TestSuiteByExcel.testResult = false;
			return 0;
		}
	}
	
	public static void setCellData(String SheetName, int RowNum, int ColNum, String Result) throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try {
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, MissingCellPolicy.RETURN_BLANK_AS_NULL); //different with book
			if(Cell == null) {
				Cell = Row.createCell(ColNum);
			} else {
				Cell.setCellValue(Result);
			}
			FileOutputStream fileOut = new FileOutputStream(Constants.Path_ExcelFile);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			TestSuiteByExcel.testResult = false;
			e.printStackTrace();
		}
	}	
}
