package DataReaderFunction;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider
    public Object[][] getData() throws Exception {

    	List<HashMap<String, String>> data = JsonDataReader.getJsonData(null);
    	
    	return new Object[][] {{data.get(0)}};
   
    }
}
