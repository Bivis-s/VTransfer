package by.bivis.vkParser.JSONs;

import by.bivis.settings.Settings;
import by.bivis.vkParser.JSONs.tools.SendGet;
import by.bivis.vkParser.sources.Source;
import com.fasterxml.jackson.databind.JsonNode;

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

    private Source getSource(JsonNode jsonNode) {
        int id = jsonNode.get("id").asInt();
        String screenName = jsonNode.get("screen_name").asText();
        String name = jsonNode.get("name").asText();
        String photoUrl = jsonNode.get("photo_200").asText();
        return new Source(id, screenName, name, photoUrl);
    }

    public Source makeSourceFromScreenName(String q) throws Exception { // screen_name or id
        String apiMethod = String.format(API_URL, "groups.getById", "group_id=" + q);
        SendGet sendGet = new SendGet();
        String got = sendGet.send(apiMethod);
        JsonNode jsonNode = JSONParser.readJson(got).get("response").get(0);
        return getSource(jsonNode);
    }

    private String searchGroup (String q, int count) throws Exception {
        String apiMethod = String.format(API_URL, "groups.search", "q=" + q.replaceAll(" ", "_") + "&count=" + count);
        System.out.println(apiMethod);
        SendGet sendGet = new SendGet();
        return sendGet.send(apiMethod);
    }

    public List<Source> makeSourceObjectList(String q, int count) throws Exception {
        List<Source> sourceList = new ArrayList<>();
        String searched = searchGroup(q, count);
        JsonNode groups = JSONParser.readJson(searched).get("response").get("items");

        for (int i = 0; i < groups.size(); i++) {
            sourceList.add(getSource(groups.get(i)));
        }
        return sourceList;
    }
    // can return null if group has not been searched
    public Source makeSourceObjectFromSearch(String q) throws Exception {
        String searched = searchGroup(q, 1);
        JsonNode groups = JSONParser.readJson(searched).get("response").get("items");
        return getSource(groups.get(0));
    }
}
