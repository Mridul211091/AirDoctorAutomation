package agi.qa.airdoctor.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtillive {

	private static String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/AirDoctorTestData.xlsx";

	private static Workbook book;
	private static Sheet sheet;
	private static Cell cell;
	private static String cellData;
	private static Row row;

	public static Object[][] getTestData(String sheetName) {

		System.out.println("reading test data from sheet : " + sheetName);

		Object data[][] = null;

		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);

			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);

			data = new Object[sheet.getLastRowNum()][sheet.getRow(1).getLastCellNum()];
	
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(1).getLastCellNum(); j++) {
					//String cellData = sheet.getRow(i + 1).getCell(j).toString();
					//String cellData = cell.toString();
					cell = sheet.getRow(i + 1).getCell(j);
					if(cell ==null) {
						cellData="";
					}else {
						cellData = cell.toString();
					}
					data[i][j] = cellData;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;

	}
	
	public static void setdata(String sheetName, String subtotal,String shipping,String tax,String total,int count) throws IOException, InvalidFormatException {
   

		FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
        
		book = WorkbookFactory.create(ip);
		
        //creating a Sheet object
		sheet = book.getSheet(sheetName);
		System.out.println("=========================================================================");
        
        //get all rows in the sheet
        //int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
        
        	
            //create a new cell in the row at index 6
         cell = sheet.getRow(count+1).createCell(sheet.getRow(count+1).getLastCellNum());
         System.out.println("Row Number "+(count+1)+" Coloum "+sheet.getRow(count+1).getLastCellNum());
         cell.setCellValue(subtotal);
         System.out.println("Subtotal on row "+ (count+1) +" and Coloum "+(sheet.getRow(count+1).getLastCellNum())+" is "+subtotal);
         
         System.out.println("=========================================================================");
         cell = sheet.getRow(count+1).createCell(sheet.getRow(count+1).getLastCellNum());
         System.out.println("Row Number "+(count+1)+" Coloum Number "+(sheet.getRow(count+1).getLastCellNum()));
         cell.setCellValue(shipping);
         System.out.println("Shipping on row "+(count+1)+" and Coloum Number "+(sheet.getRow(count+1).getLastCellNum())+" is "+shipping);
         System.out.println("=========================================================================");
         
         cell = sheet.getRow(count+1).createCell(sheet.getRow(count+1).getLastCellNum());
         System.out.println("Row Number "+(count+1)+" Coloum Number "+(sheet.getRow(count+1).getLastCellNum()));
         cell.setCellValue(tax);
         System.out.println("Tax on row "+(count+1)+" and Coloum Number "+(sheet.getRow(count+1).getLastCellNum())+" is "+tax);
         System.out.println("=========================================================================");
         
         cell = sheet.getRow(count+1).createCell(sheet.getRow(count+1).getLastCellNum());
         System.out.println("Row Number"+(count+1)+" Coloum Number "+(sheet.getRow(count+1).getLastCellNum()));
         cell.setCellValue(total);
         System.out.println(" Total on row "+(count+1)+" and Coloum Number "+(sheet.getRow(count+1).getLastCellNum())+" is "+total);
         System.out.println("=========================================================================");
         
       
         FileOutputStream outputStream = new FileOutputStream(TEST_DATA_SHEET_PATH);
         book.write(outputStream);
         System.out.println("=========================================================================");
       

}
}
