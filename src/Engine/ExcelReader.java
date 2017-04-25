package Engine;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ExcelReader {
	public static String[][] readExcel(File x) throws IOException {

		int numrows;
		int numcols;

		FileInputStream stream = new FileInputStream(x);
		XSSFWorkbook y= new XSSFWorkbook(stream);
		XSSFWorkbook book = new XSSFWorkbook(stream);
		XSSFSheet sheet = book.getSheet("Sheet1");

		numrows = sheet.getLastRowNum() + 1;
		numcols = sheet.getRow(0).getLastCellNum();

		String[][] shit = new String[numrows][numcols];

		for (int a = 0; a < numrows; a++) {
			XSSFRow row = sheet.getRow(a);
			for (int b = 0; b < numcols; b++) {
				XSSFCell cell = row.getCell(b);
				String temp = cellToString(cell);
				shit[a][b] = temp;
			}

		}

		return shit;

	}

	public static String cellToString(XSSFCell x){
		int type;
		Object res;
		type=x.getCellType();
		switch(type){
		case 0:
			res = x.getStringCellValue();
			break;
		case 1:
			res = x.getStringCellValue();
			break;
		default:
			throw new RuntimeException("there has been an error");
		}
		return res.toString();
	}
	
}
