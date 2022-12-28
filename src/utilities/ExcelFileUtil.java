package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// Write all excel methods in this class

public class ExcelFileUtil {
//	Create a variable for Workbook
	
	XSSFWorkbook wb;
	
//	Create a constructor to read excel file path [Constructor name and Class name should be same]
	
	public void ExcelFileUtli (String excelpath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(excelpath);
//		Read excel workbook data
		
		wb = new XSSFWorkbook(fi);
	}
	
//	Create a method to get rowcount
//	To get row count we need to pass Sheet name argument
	
	public int rowcount (String Sheetname)
	{
		return wb.getSheet(Sheetname).getLastRowNum();
		
	}
	
//	Create a method to get cell data
//	To get cell data we need Sheet name, Row number and Column
//	Converting cell data into string type
	public String getcelldata (String Sheetname, int row, int column)
	{
		String data ="";
		if(wb.getSheet(Sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata =(int)wb.getSheet(Sheetname).getRow(row).getCell(column).getNumericCellValue();
			data =String.valueOf(celldata);
		}
		else
		{
			data =wb.getSheet(Sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	
//	Create method to write data into excel sheet
	public void setCelldata (String Sheetname, int row, int column, String status, String outputexcepath)
	throws Throwable
	{
		//get sheet from workbook
		XSSFSheet ws= wb.getSheet(Sheetname);
		//get row from sheet
		XSSFRow rowNum= ws.getRow(row);
		//create cell
		XSSFCell cell = rowNum.createCell(column);
		//write status 
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(outputexcepath);
		wb.write(fo);
	}
	

}
