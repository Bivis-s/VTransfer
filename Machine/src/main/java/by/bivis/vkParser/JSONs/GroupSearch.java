package by.bivis.vkParser.JSONs;

import by.bivis.settings.Settings;
import by.bivis.vkParser.JSONs.tools.SendGet;
import by.bivis.vkParser.sources.Source;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GroupSearch {
    private final String TOKEN = Settings.getSetting("vkToken");
    private final String API_VERSION = "5.124";
    //First argument - Method name, second - parameters
    private final String API_URL = "https://api.vk.com/method/%s?%s&access_token=" + TOKEN + "&v=" + API_VERSION;

    public GroupSearch() throws IOException {
    }

    public static void main(String[] args) throws Exception {
        GroupSearch groupSearch = new GroupSearch();
        System.out.println(groupSearch.makeSourceObjectList("Jjjjsjsjjjjjj", 10));
        System.out.println(groupSearch.makeSourceObject("лентач"));
    }

    private String searchGroup (String q, int count) throws Exception {
        String api_method = String.format(API_URL, "groups.search", "q=" + q.replaceAll(" ", "_") + "&count=" + count);
        System.out.println(api_method);
        SendGet sendGet = new SendGet();
        return sendGet.send(api_method);
    }

    private Source makeSourceObjectFromNode(JsonNode itemNode) {
        int id = itemNode.get("id").asInt();
        String screenName = itemNode.get("screen_name").asText();
        String name = itemNode.get("name").asText();
        String photoURL = itemNode.get("photo_200").asText();
        return new Source(id, screenName, name, photoURL);
    }

    public List<Source> makeSourceObjectList(String q, int count) throws Exception {
        List<Source> sourceList = new ArrayList<>();
        String searched = searchGroup(q, count);
        JsonNode groups = JSONParser.readJson(searched).get("response").get("items");

        for (int i = 0; i < groups.size(); i++) {
            sourceList.add(makeSourceObjectFromNode(groups.get(i)));
        }
        return sourceList;
    }
    // can return null if group has not been searched
    public Source makeSourceObject(String q) throws Exception {
        String searched = searchGroup(q, 1);
        JsonNode groups = JSONParser.readJson(searched).get("response").get("items");
        return makeSourceObjectFromNode(groups.get(0));
    }
}
