package com.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataExcel {

	public static ArrayList<String> getData(String filePath, String sheetName, String testCaseName) throws IOException {
		ArrayList<String> data= new ArrayList<String>(); 
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook =new XSSFWorkbook(file);
		int sheets=workbook.getNumberOfSheets();
		Iterator<Cell> cells = null;
		sheetsLoop: for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {				
				XSSFSheet dataSheet = workbook.getSheetAt(i);
				i = sheets;
				Iterator<Row> rows = dataSheet.iterator();
				rowsLoop: while (rows.hasNext()) {
					cells = rows.next().cellIterator();
					while (cells.hasNext()) {
						Cell cell = cells.next();
						String value = "";
						if (cell.getCellType() == CellType.STRING) {
							value = cell.getStringCellValue();
						} else {
							value = NumberToTextConverter.toText(cell.getNumericCellValue());
						}
						if (value.equalsIgnoreCase(testCaseName)) {
							break sheetsLoop;
						}
					}
				}
			}
		}
		while (cells.hasNext()) {
			Cell cell = cells.next();
			if (cell.getCellType() == CellType.STRING) {
				data.add(cell.getStringCellValue());
			} else {
				data.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
			}

		}
		System.out.println(data);
		workbook.close();
		return data;
	}
	
}
