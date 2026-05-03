package ExcelUtilities;
import java.io.IOException;
import java.util.HashMap;

public class GetExcelData {
	
	public static HashMap<String, String> getDataForTheTestCase(String testcaseName) throws IOException
	{
		ExcelUtilities excelUtilties = new ExcelUtilities();
		return excelUtilties.getDataForTestCase(testcaseName);
	}
	
}
