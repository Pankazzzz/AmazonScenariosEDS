package ExcelUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	
    private XSSFSheet sheet;
    private final String path =System.getProperty("user.dir")+"/src/main/java/DataResources/AmazonData.xlsx";
    private final String sheetName = "Data";
    
    public HashMap<String, String> getDataForTestCase(String testcaseName)
            throws FileNotFoundException, IOException {

        HashMap<String, String> data = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(new File(path));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            int sheetCount = workbook.getNumberOfSheets();

            for (int i = 0; i < sheetCount; i++) {
                if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
                    sheet = workbook.getSheetAt(i);

                    Iterator<Row> rows = sheet.iterator();
                    Row headerRow = rows.next();

                    int columnIndex = -1;
                    Iterator<Cell> headerCells = headerRow.cellIterator();
                    int k = 0;

                    while (headerCells.hasNext()) {
                        if (headerCells.next().getStringCellValue()
                                .equalsIgnoreCase("TestCaseName")) {
                            columnIndex = k;
                            break;
                        }
                        k++;
                    }

                    while (rows.hasNext()) {
                        Row currentRow = rows.next();

                        if (currentRow.getCell(columnIndex).getStringCellValue()
                                .equalsIgnoreCase(testcaseName)) {

                            List<String> headers = getHeaders();
                            Iterator<Cell> cellValues = currentRow.cellIterator();
                            int headCount = 0;

                            while (cellValues.hasNext() && headCount < headers.size()) {
                                Cell c = cellValues.next();

                                switch (c.getCellType()) {
                                    case STRING:
                                        data.put(headers.get(headCount++),
                                                c.getStringCellValue());
                                        break;
                                    case NUMERIC:
                                        data.put(headers.get(headCount++),
                                                NumberToTextConverter
                                                        .toText(c.getNumericCellValue()));
                                        break;
                                    case BOOLEAN:
                                        data.put(headers.get(headCount++),
                                                String.valueOf(c.getBooleanCellValue()));
                                        break;
                                    default:
                                        data.put(headers.get(headCount++), "");
                                }
                            }
                        }
                    }
                }
            }
        }
        return data;
    }

    public List<String> getHeaders() throws IOException {
        List<String> headers = new LinkedList<>();

        try (FileInputStream fis = new FileInputStream(new File(path));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(0);

            Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext()) {
                headers.add(cells.next().getStringCellValue());
            }
        }
        return headers;
    }

}
