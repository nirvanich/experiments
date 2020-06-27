import java.io.FileInputStream;
import java.io.IOException;

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
			
				for (int j = 0; j < sheet.getLastRowNum()+1; j++) {
					
					for (int l = 0; l < sheet.getRow(j).getLastCellNum(); l++) {
						String value = sheet.getRow(j).getCell(l).toString();
						System.out.print(value + "	|");
					}
					System.out.println();
				}
			}
		}
		workbook.close();
	}

}
