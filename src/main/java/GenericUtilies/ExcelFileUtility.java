package GenericUtilies;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author B.Nandini This class contains all the reusable methods for Excel file
 */
public class ExcelFileUtility {

	public Workbook wb;

	/**
	 * This method is used to fetch data from excel file
	 * 
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @return String
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String fetchDataFromExcelFile(String sheetname, int rowindex, int cellindex)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VTiger_TestData.xlsx");
		wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row r = sh.getRow(rowindex);
		Cell c = r.getCell(cellindex);
		String data = c.toString();
		return data;

	}

	/**
	 * This method is used to write back data to excel file in NEW ROW & NEW CELL
	 * 
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @param data
	 * @throws Exception
	 */
	public void writeBackDataToExcelFile(String sheetname, int rowindex, int cellindex, String data) throws Exception {
		FileInputStream fis = new FileInputStream("./src/test/resources/VTiger_TestData.xlsx");
		wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row r = sh.createRow(rowindex);
		Cell c = r.createCell(cellindex);
		c.setCellValue(data);

		FileOutputStream fos = new FileOutputStream("./src/test/resources/VTiger_TestData.xlsx");
		wb.write(fos);

	}

	/**
	 * This method is used to close excel workbook
	 * 
	 * @throws IOException
	 */
	public void closeExcelWorkbook() throws IOException {
		wb.close();
	}

	/**
	 * This method is used to fetch multiple data from excel sheet
	 * 
	 * @param sheetname
	 * @return String
	 * @throws Exception
	 */
	public String FetchMutipleDataFromExcelFile(String sheetname) throws Exception {
		String data = null;
		FileInputStream fis = new FileInputStream("./src/test/resources/VTiger_TestData.xlsx");
		wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);

		for (int i = 0; i < sh.getLastRowNum(); i++) {
			for (int j = 0; j <= sh.getRow(i).getLastCellNum(); j++) {
				data = sh.getRow(i).getCell(j).toString();
			}
		}

		return data;
	}

}
