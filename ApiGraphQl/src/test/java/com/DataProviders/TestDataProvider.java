package com.DataProviders;

import org.testng.annotations.DataProvider;

import com.Utilites.BaseUtilites;
import com.Utilites.ExcelUtility;

public class TestDataProvider extends BaseUtilites {

	
	@DataProvider(name = "ExcelFile")
	public Object[][] getExcelData() throws Exception {

		String path = "./ExcelData/Data.xlsx";
		String sheetName ="testData";
		ExcelUtility excelData = new ExcelUtility();
		Object[][] data = excelData.getData(path, sheetName);
		return data;

	}
	
	
	public Object[][] getData(String path,String sheetName) throws Exception {
		
		ExcelUtility excelData = new ExcelUtility();
		Object[][] data = excelData.getData(path, sheetName);
		return data;
	}
	
	
}
