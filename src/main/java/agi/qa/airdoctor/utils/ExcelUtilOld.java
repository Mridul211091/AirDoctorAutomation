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

public class ExcelUtilOld {

	private static String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/AirDoctorTestData.xlsx";

	private static Workbook book;
	private static Sheet sheet;
	private static Cell cell;
	private static String cellData;
	private static Row row;
	
	
	
	 public static void preparesheet(String sheetName, String[] addresses) throws InvalidFormatException {
	       
	        try {
	            // Load the existing Excel file
	            FileInputStream fileInputStream = new FileInputStream(TEST_DATA_SHEET_PATH);
	            Workbook workbook = WorkbookFactory.create(fileInputStream);
	            Sheet sheet = workbook.getSheet(sheetName);

	            if (sheet == null) {
	                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in '" + TEST_DATA_SHEET_PATH + "'");
	            }

				/*
				 * String[] addresses = {
				 * "1600 Pennsylvania Avenue NW | | Washington | District of Columbia | 20500",
				 * "100 State Street | | Boston | Massachusetts | 02110",
				 * "225 Broadway | | New York | New York | 10007",
				 * "501 S Spring Street | | Los Angeles | California | 90013",
				 * "600 South Capitol Way | | Olympia | Washington | 98501" };
				 */

	            // Map to store column index based on header name
	            Map<String, Integer> columnIndexMap = new HashMap<>();
	            Row headerRow = sheet.getRow(0); // Assuming headers are in the first row
	            for (int colNum = 0; colNum < headerRow.getLastCellNum(); colNum++) {
	                Cell headerCell = headerRow.getCell(colNum);
	                String header = headerCell.getStringCellValue().trim(); // Assuming headers are strings
	                columnIndexMap.put(header, colNum);
	            }

	            // Write addresses to the sheet based on header names
	            int currentRowNum = 1; // Start from the next available row
	            for (String address : addresses) {
	            	String addressWithoutNumbering = address.replaceFirst("^\\d+\\.\\s*", "");
	                String[] parts = addressWithoutNumbering.split("\\s*\\|\\s*"); // Split by pipe (|) and trim whitespace	                Row row = sheet.getRow(currentRowNum);
	                Row row = sheet.getRow(currentRowNum);
	                if (row == null) {
	                    row = sheet.createRow(currentRowNum);
	                }
	                for (int i = 0; i < parts.length; i++) {
	                    String header = getHeader(i); // Get header based on index (e.g., "Street Address 1", "Street Address 2", ...)
	                    if (columnIndexMap.containsKey(header)) {
	                        int colNum = columnIndexMap.get(header);
	                        Cell cell = row.getCell(colNum);
	                        if (cell == null) {
	                            cell = row.createCell(colNum);
	                        }
	                        
	                        if (parts[i].equalsIgnoreCase("N/A")) {
	                         //   cell.se
	                        } else {
	                        cell.setCellValue(parts[i].trim()); // Trim to remove extra spaces
	                    }
	                    }
	                }
	                currentRowNum++; // Move to the next row for the next address
	            }

	            // Write the modified workbook back to the file
	            FileOutputStream fileOutputStream = new FileOutputStream(TEST_DATA_SHEET_PATH);
	            workbook.write(fileOutputStream);
	            fileOutputStream.close();

	            System.out.println("Addresses added successfully to '" + sheetName + "' sheet in '" + TEST_DATA_SHEET_PATH + "'");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Example method to get header based on index
	    private static String getHeader(int index) {
	        switch (index) {
	            case 0:
	                return "Billing_Address_1";
	            case 1:
	                return "Billing_Address_2";
	            case 2:
	                return "City";
	            case 3:
	                return "State";
	            case 4:
	                return "zipcode";
	            default:
	                return "";
	        }
	    }
	
	
	
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
	
	public static void setdata(String sheetName, String subtotal,String shipping,String tax,String total,String orderId,int count) throws IOException, InvalidFormatException {
   

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
         
         cell = sheet.getRow(count+1).createCell(sheet.getRow(count+1).getLastCellNum());
         System.out.println("Row Number"+(count+1)+" Coloum Number "+(sheet.getRow(count+1).getLastCellNum()));
         cell.setCellValue(orderId);
         System.out.println(" Order Id On Row "+(count+1)+" and Coloum Number "+(sheet.getRow(count+1).getLastCellNum())+" is "+orderId);
         System.out.println("=========================================================================");
        
         FileOutputStream outputStream = new FileOutputStream(TEST_DATA_SHEET_PATH);
         book.write(outputStream);
         System.out.println("=========================================================================");
       

}
	
}
