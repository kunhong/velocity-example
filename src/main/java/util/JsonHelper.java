package util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonHelper {
    public static void printPretty(final String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object jsonObject = mapper.readValue(json, Object.class);
            String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            System.out.println(prettyJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
