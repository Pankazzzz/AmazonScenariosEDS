package DataResources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestCaseDetails {

    public static Properties getTestCaseDetails() throws IOException {
        
    	FileInputStream fis = new FileInputStream(
                new File(System.getProperty("user.dir")
                        + "/src/main/java/DataResources/testcase.properties"));

        Properties prop = new Properties();
        prop.load(fis);
        return prop;
    }
}
