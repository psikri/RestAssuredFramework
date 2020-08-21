package com.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataExcel {

	public static void getData(String filePath, String sheetName) throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook =new XSSFWorkbook(file);
		int sheets=workbook.getNumberOfSheets();
		for(int i=0; i<sheets; i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet dataSheet = workbook.getSheetAt(i);
				Iterator<Row> rows=dataSheet.iterator();
				while(rows.hasNext()) {
					Iterator<Cell> cells=rows.next().cellIterator();
					while(cells.hasNext()) {
						String value=cells.next().getStringCellValue();
					}
				}
			}
		}
	}
	
}
