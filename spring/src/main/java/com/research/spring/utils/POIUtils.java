package com.research.spring.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * POI使用测试
 * @author wdmcygah
 *
 */
public class POIUtils {

	public static void read(){
		File file = new File("C:/Users/Administrator/Desktop/workbook.xls");
		try {
			Workbook wb = new HSSFWorkbook(new FileInputStream(file));
			Sheet sheet = wb.getSheetAt(0);
			for( Row row : sheet ){
				for( Cell cell : row ){
					if( cell.getCellType() == Cell.CELL_TYPE_STRING ){
						System.out.println(cell.getStringCellValue());
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static  void write(){
		Workbook wb = new HSSFWorkbook();
	    //Workbook wb = new XSSFWorkbook();
	    Sheet sheet = wb.createSheet("new sheet");
	 // Create a row and put some cells in it. Rows are 0 based.
	    
	    Row row = sheet.createRow(0);
	    Cell cell = row.createCell(0);
	    cell.setCellValue("用户名");
	    cell = row.createCell(1);
	    cell.setCellValue("密码");

	    row = sheet.createRow(1);
	    cell = row.createCell(0);
	    cell.setCellValue("db O");
	    cell = row.createCell(1);
	    cell.setCellValue("123456");
	    
	    // Write the output to a file
	    FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("C:/Users/Administrator/Desktop/workbook.xls");
			  wb.write(fileOut);
			    fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
	}
	
	public static void main(String[] args) {
		POIUtils.write();
		
		POIUtils.read();
	}
	
}
