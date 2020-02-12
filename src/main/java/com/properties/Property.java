package com.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Property
{
	public static String getProperty(String key) throws IOException 
    {
	      FileInputStream fin=new FileInputStream("C:\\Users\\sanjay.ravindra\\eclipse-workspace\\OrangeHRM\\src\\main\\resources\\Login.properties");    
	      Properties prop = new Properties();
	            prop.load(fin); 
	            return prop.getProperty(key);
	    }  

	public static String decode(String decodePass)
	{
	byte[] decode = Base64.decodeBase64(decodePass);
	return new String(decode);
	
	}
	

public void screenshot(WebDriver driver, String name) throws Throwable
{
TakesScreenshot t = (TakesScreenshot)driver;
File source = t.getScreenshotAs(OutputType.FILE);
String title = driver.getTitle();
File destination = new File("./Screenshot/"+title+"-"+name+".png");
FileUtils.copyFile(source, destination);		
}	


public static String file ="C:\\Users\\sanjay.ravindra\\eclipse-workspace\\OrangeHRM\\src\\main\\resources\\Excel\\OrangeHRM.xlsx";
public static FileInputStream fis = null;
public static FileOutputStream fos = null;
public static XSSFWorkbook workbook = null;
public static XSSFRow row = null;
public static XSSFCell cell = null;

public int getrow(String sheetname) throws Throwable
{
	 fis = new FileInputStream(file);
	  workbook = new XSSFWorkbook(fis);
	 XSSFSheet shet = workbook.getSheet(sheetname);
	 int rownumber = shet.getLastRowNum();
	 fis.close();
	return rownumber;
}


public void writeExcel(String sheetname, String colnam, int rownum, String value) throws Throwable
{
	fis =new FileInputStream(file);
	FileOutputStream fout = null;
    XSSFWorkbook book = new XSSFWorkbook(fis);
    XSSFSheet sheet = book.getSheet(sheetname);
    XSSFRow row = null;
    XSSFCell cell = null;
    int colnum = -1;
    row = sheet.getRow(0);
    for(int i=0 ; i<row.getLastCellNum(); i++)
	{
		if(row.getCell(i).getStringCellValue().trim().equals(colnam))
		{
			colnum = i;
		}
	}
    row = sheet.getRow(rownum);
    if(row==null)
    {
    	row = sheet.createRow(rownum);
    }
    
    cell = row.getCell(colnum);
    if(cell==null)
    {
    	cell = row.createCell(colnum);
    }
cell.setCellValue(value);

fout = new FileOutputStream(file);
	book.write(fout);
	fout.close();
	
}


public String getcelldata(String sname, String colname, int rownum) throws Throwable
{	
	try
	{
		this.getrow(sname);
int colnum = -1;
XSSFSheet sheet = workbook.getSheet(sname);
XSSFRow row = sheet.getRow(0);
for(int i=0 ; i<row.getLastCellNum(); i++)
{
	if(row.getCell(i).getStringCellValue().trim().equals(colname))
	{
		colnum = i;
	}
}
row = sheet.getRow(rownum);
		 XSSFCell cell = row.getCell(colnum);
if(cell.getCellType()==CellType.STRING)
{
			
	return cell.getStringCellValue();
}
else if(cell.getCellType()==CellType.NUMERIC)
{
	 Integer cellValue = Integer.valueOf((int) cell.getNumericCellValue());
	 
	return Integer.toString(cellValue);
}


}
	catch(NullPointerException e)
	{
	
	System.out.println("data not found");	
	}
	return " ";
}

}
