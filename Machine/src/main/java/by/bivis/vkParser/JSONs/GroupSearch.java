package by.bivis.vkParser.JSONs;

import by.bivis.settings.Settings;
import by.bivis.vkParser.JSONs.tools.SendGet;
import by.bivis.vkParser.sources.Source;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class GroupSearch {
    private final String TOKEN = Settings.getSetting("vkToken");
    private final String API_VERSION = "5.124";
    //First argument - Method name, second - parameters
    private final String API_URL = "https://api.vk.com/method/%s?%s&access_token=" + TOKEN + "&v=" + API_VERSION;

    public GroupSearch() throws IOException {
    }

    public static void main(String[] args) throws Exception {
        GroupSearch groupSearch = new GroupSearch();
        String searched = groupSearch.searchGroup("sasamba");
        JsonNode group = JSONParser.readJson(searched).get("response").get("items").get(0);
        Source source = groupSearch.makeSourceObject(group);
        System.out.print(source.toString());
    }

    private String searchGroup (String q) throws Exception {
        String api_method = String.format(API_URL, "groups.search", "q=-" + q + "&count=5");
        System.out.println(api_method);
        SendGet sendGet = new SendGet();
        return sendGet.send(api_method);
    }

    private Source makeSourceObject(JsonNode itemNode) {
        int id = itemNode.get("id").asInt();
        String screenName = itemNode.get("screen_name").asText();
        String name = itemNode.get("name").asText();
        return new Source(id, screenName, name);
    }
}
