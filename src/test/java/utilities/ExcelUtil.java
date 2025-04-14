package utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

	public class ExcelUtil {

	   
	   public static FileInputStream fi;
	   public static FileOutputStream fo;
	   public static XSSFWorkbook wb;
	   public static XSSFSheet ws;
	   public static XSSFRow row;
	   public static XSSFCell cell;
	   public static CellStyle style;
	   String path;
	   
	   public  ExcelUtil(String path)
	   {
		   this.path=path;
	   }
	
	   public  int getRowCount(String xlsheet) throws IOException
	   {
		   fi=new FileInputStream(path);
		   wb=new XSSFWorkbook(fi);
		   ws=wb.getSheet(xlsheet);
		   int rowcount=ws.getLastRowNum();
		   wb.close();
		   fi.close();
		   return rowcount;   
	   }
	   public  int getCellcount(String xlsheet,int rownum) throws IOException
	   {
		   fi=new FileInputStream(path);
		   wb=new XSSFWorkbook(fi);
		   ws=wb.getSheet(xlsheet);
		   row=ws.getRow(rownum);
		   int Cellcount=row.getLastCellNum();
		   return Cellcount;
		   
	   }
		public  String getCelldata(String xlsheet,int rownum,int colnum) throws IOException
		{
			 fi=new FileInputStream(path);
			   wb=new XSSFWorkbook(fi);
			   ws=wb.getSheet(xlsheet);
			   row=ws.getRow(rownum);
			   cell=row.getCell(colnum);
			   String data;
			   
			try 
			{
				
				data=cell.toString();
			}
			 catch(Exception e)
			{
				   data="";
			 }
			wb.close();
			fi.close();
			return data;
		}
		
		public  void setCelldata(String xlsheet,int rownum,int colnum,String data) throws IOException
		{
			 fi= new FileInputStream(path);
			   wb=new XSSFWorkbook(fi);
			   ws=wb.getSheet(xlsheet);
			   row=ws.getRow(rownum);
			   cell=row.createCell(colnum);
			 cell.setCellValue(data);
			 fo=new FileOutputStream(path);
			 wb.write(fo);
			 wb.close();
			 fi.close();
			 fo.close();
			
		}
		public  void fillGreenColour(String xlsheet,int rownum,int colnum) throws IOException
		{

			  fi= new FileInputStream(path);
			   wb=new XSSFWorkbook(fi);
			   ws=wb.getSheet(xlsheet);
			   row=ws.getRow(rownum);
			   cell=row.getCell(colnum);
			   style=wb.createCellStyle();
			   
			   
			 
			  
			   style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			   
	          //  style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex()); 

			   style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			   cell.setCellStyle(style);
			   fo=new FileOutputStream(path);
			   wb.write(fo);
				 wb.close();
				 fi.close();
				 fo.close();
			   
		}
		public  void fillRedColour(String xlsheet,int rownum,int colnum) throws IOException
		{
			 fi= new FileInputStream(path);
			   wb=new XSSFWorkbook(fi);
			   ws=wb.getSheet(xlsheet);
			   row=ws.getRow(rownum);
			   cell=row.getCell(colnum);
			   style=wb.createCellStyle();
			   style.setFillForegroundColor(IndexedColors.RED.getIndex());
			   style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			   cell.setCellStyle(style);
			   fo=new FileOutputStream(path);
			   wb.write(fo);
				 wb.close();
				 fi.close();
				 fo.close();
		}
		  public  void highlightElement(WebDriver driver, WebElement element) {
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        // Apply a red border and yellow background for highlighting
		        js.executeScript("arguments[0].style.border='3px solid red'; arguments[0].style.backgroundColor='yellow';", element);
		    
	}
			
	}
	
	   
