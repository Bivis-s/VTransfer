package by.bivis.vkParser.JSONs;

import by.bivis.vkParser.JSONs.tools.ReadWrite;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

//work with JSON-String with GSON

public class JSONParser {
    private static void readJson(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(content, JsonNode.class);
        JsonNode childNode =  rootNode.get("response").get("items").get(0); // get object Place
        System.out.println(childNode);
    }

    //TODO Post-Obj generator
//    private static Post makePostObject(ObjectNode node) {
////        Post post = new Post();
//
////        return post;
//    }

    public static void main(String[] args) throws IOException {
        String jsonString = ReadWrite.read("./Machine/src/main/resources/parser_resources/musicPost.json");
        readJson(jsonString);
    }
}
