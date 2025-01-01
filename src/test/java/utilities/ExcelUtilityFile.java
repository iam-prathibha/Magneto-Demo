package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilityFile {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	String path;
	
	public ExcelUtilityFile(String path) {
		this.path=path;
	}
	public int getRowCount(String xlsheet) throws IOException
	{
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		sh= wb.getSheet(xlsheet);
		int rowCount= sh.getLastRowNum();
		wb.close();
		fi.close();
		
		return rowCount;
	}
	
	public int getCellCount(String xlsheet,int rowNum) throws IOException
	{
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		sh= wb.getSheet(xlsheet);
		row= sh.getRow(rowNum);
		int cellCount= row.getLastCellNum();
		wb.close();
		fi.close();
		
		return cellCount;
	}
	
	public String getCellData(String xlsheet,int rowNum, int colNum) throws IOException
	{
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		sh= wb.getSheet(xlsheet);
		row= sh.getRow(rowNum);
		cell= row.getCell(colNum);
		
		String data;
		try
		{
			DataFormatter df= new DataFormatter();
			data= df.formatCellValue(cell);
			
		}
		catch(Exception e)
		{
			data="";
		}
		wb.close();
		fi.close();
		
		return data;
	}
	
	public void setCellData(String xlsheet,int rowNum,int colNum,String data) throws IOException
	{
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		sh= wb.getSheet(xlsheet);
		row= sh.getRow(rowNum);
		cell= row.createCell(colNum);
		cell.setCellValue(data);
		fo= new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fo.close();
		fi.close();
		
	}
	
	public void fillGreenColor(String xlsheet, int rowNum, int colNum) throws IOException
	{
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		sh= wb.getSheet(xlsheet);
		row= sh.getRow(rowNum);
		cell= row.getCell(colNum);
		style= wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo= new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fo.close();
		fi.close();
		
	}

	public void fillRedColor(String xlsheet, int rowNum, int colNum) throws IOException
	{
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		sh= wb.getSheet(xlsheet);
		row= sh.getRow(rowNum);
		cell= row.getCell(colNum);
		style= wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo= new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fo.close();
		fi.close();
		
	}

}
