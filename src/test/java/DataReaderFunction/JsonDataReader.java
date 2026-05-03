package DataReaderFunction;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataReader {

    public static List<HashMap<String, String>> getJsonData(String directory) throws Exception {

        String jsonContent;

        if (directory == null) {
            jsonContent = Files.readString(
                    Paths.get("/Users/pankajshukla/eclipse-workspace/AmazonEDSSmokeTest/src/main/java/DataResources/data.json"));
        } else {
            jsonContent = Files.readString(Paths.get(directory));
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {});
    }
}
