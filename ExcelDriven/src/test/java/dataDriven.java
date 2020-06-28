import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FileInputStream fis = new FileInputStream("C:\\Gitstuff\\ExcelDriven\\src\\test\\resources\\workbook1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		int sheetCount = workbook.getNumberOfSheets();
		
		for (int i = 0; i < sheetCount; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				
					//Print the whole table
					  for (int j = 0; j < sheet.getLastRowNum()+1; j++) {
						  for (int l = 0; l < sheet.getRow(j).getLastCellNum(); l++) { 
							  String value = sheet.getRow(j).getCell(l).toString(); System.out.print(value + "	|"); 
						  }
						  System.out.println(); 
					  }
					 //---------------------
					  System.out.println();
				
				Iterator <Row> rows = sheet.iterator();
				Iterator <Cell> cells = sheet.iterator().next().cellIterator();
				
				ArrayList<String> rowName = new ArrayList<String>();
				ArrayList<String> CellName = new ArrayList<String>();
				int a = 0;		
				int b = 0;
						 
				
				while (rows.hasNext()) {
					Row row = rows.next();
					cells = row.cellIterator();
					String currenRowName;
					String currenColumnName;
					String currenCellValue;
					rowName.add(cells.next().getStringCellValue());
					currenRowName = rowName.get(b);
										
						while (cells.hasNext()) {
							Cell cell = cells.next();
							CellName.add(cell.getStringCellValue());
							currenColumnName = CellName.get(a);
							currenCellValue = cell.getStringCellValue();
																				
							if (currenCellValue.equals("4-4")) {
								System.out.println("Given cell value: " + currenCellValue);
								System.out.println("Given cell's row: " + currenRowName);
								System.out.println("Given cell's column: " + currenColumnName);
							}
							a++;
						}
					
					a = 0;
					b++;
				}
			}
		}
		workbook.close();
	}

}
