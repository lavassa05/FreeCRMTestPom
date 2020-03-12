package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

	// waits
	// can also define in config.properties
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public void switchToMainFrame() {
		driver.switchTo().frame("mainpanel");

	}

	public void MouseHoverElement(WebElement target) {
		Actions action = new Actions(driver);
		action.moveToElement(target);
	}

	static Workbook book;
	static Sheet sheet;

	public static String Testdata_Sheet_Path = "C:\\Selenium projects\\feb2020\\"
			+ "FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRM_Contacts.xlsx";

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;

		try {
			file = new FileInputStream(Testdata_Sheet_Path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// poi-ooxml dependency
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);

		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);

			}
		}
		return data;
		// return 2d object array
		// iterate sheet based on row by row and col by col
		//like mr. then sandip then jadhav  then ibm
	}
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File("C:/Selenium projects/feb2020/FreeCRMTest/ScreenShotsForFailed/" + System.currentTimeMillis() + ".jpg"));
	}

	
	//		FileUtils.copyFile(file, new File("C:/Selenium projects/feb2020/XPath/ScreenShot/" + filename + ".jpg"));// String

	//C:/Selenium projects/feb2020/FreeCRMTest/ScreenShotsForFailed/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
