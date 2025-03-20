package utility;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {
	
	public static Object[][] getSheetData(String filePath, String sheetName) throws Exception {
		File file = new File(filePath);

		FileInputStream fileInput = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fileInput);
		
		Sheet sheet = workbook.getSheet(sheetName);
        int totalRows = sheet.getLastRowNum();
        int totalCols = sheet.getRow(0).getLastCellNum();
         
        Object[][] data = new Object[totalRows][totalCols];
        
		for(int rowIndex=0;rowIndex<totalRows;rowIndex++) {
			for(int colIndex=0;colIndex<totalCols;colIndex++) {
				Cell cell = sheet.getRow(rowIndex+1).getCell(colIndex);
				if(cell == null) {
					data[rowIndex][colIndex] = "";
					System.err.println("RowIndex : " + rowIndex + ", ColIndex : "+ colIndex + " is blank");
				}
				else if(cell.getCellType() == CellType.STRING) {
					data[rowIndex][colIndex] = cell.getStringCellValue();
				}else if(cell.getCellType() == CellType.NUMERIC) {
					data[rowIndex][colIndex] = String.valueOf(cell.getNumericCellValue());
				}else if(cell.getCellType() == CellType.BOOLEAN) {
					data[rowIndex][colIndex] = cell.getBooleanCellValue();
				}
				System.out.print(data[rowIndex][colIndex] + " ");
			}
			System.out.println();
		}
		workbook.close();
		return data;
		
//		int lastRow = sheet.getLastRowNum() + 1;
//		int lastCell = sheet.getRow(0).getLastCellNum();
//
//		for (int row = 0; row < lastRow; row++) {
//			System.out.println("Row " + row + " Data Start");
//			for (int cell = 0; cell < lastCell; cell++) {
//				Cell currentCell = sheet.getRow(row).getCell(cell);
////				System.out.println(currentCell.getStringCellValue());
//
//				CellType cellType = currentCell.getCellType();
//				switch (cellType) {
//				case STRING:
//					System.out.println(currentCell.getStringCellValue());
//					break;
//
//				case NUMERIC:
//					System.out.println((int) currentCell.getNumericCellValue());
//					break;
//
//				case BOOLEAN:
//					System.out.println(currentCell.getBooleanCellValue());
//					break;
//
//				default:
//					break;
//				}
//
//			}
//			System.out.println("Row " + row + " Data end");
//			System.out.println("=============================");
//		}
	}

}
