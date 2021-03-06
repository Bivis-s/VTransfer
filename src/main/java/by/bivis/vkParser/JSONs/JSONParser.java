package by.bivis.vkParser.JSONs;

import by.bivis.settings.Settings;
import by.bivis.vkParser.JSONs.tools.ReadWrite;
import by.bivis.vkParser.JSONs.tools.SendGet;
import by.bivis.vkParser.posts.AttachmentPost;
import by.bivis.vkParser.posts.Post;
import by.bivis.vkParser.posts.attachments.Attachment;
import by.bivis.vkParser.posts.attachments.NonPrintableAttachment;
import by.bivis.vkParser.posts.attachments.Photo;
import by.bivis.vkParser.posts.attachments.Video;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//work with JSON-String with GSON

public class JSONParser {

    private static String TOKEN = null;

    static {
        try {
            TOKEN = Settings.getSetting("vkToken");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final static String API_VERSION = "5.124";
    //First argument - Method name, second - parameters
    private final static String API_URL = "https://api.vk.com/method/%s?%s&access_token=" + TOKEN + "&v=" + API_VERSION;

    public static String parseWall(int ownerId, int count) throws Exception {
        String api_method = String.format(API_URL, "wall.get", "owner_id=-" + ownerId + "&count=" + count);
        System.out.println(api_method);
        SendGet sendGet = new SendGet();
        return sendGet.send(api_method);
    }

    //
    protected static JsonNode readJson(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, JsonNode.class);
    }

    // jsonNode here is JsonObject ARRAY (item["attachments"]["photo"]["sizes"])
    private static String findLargestPhotoUrl(JsonNode jsonNode) {
        int maxDimension = Integer.MIN_VALUE;
        String url = "Who stole my url???";
        for (JsonNode next : jsonNode) { // find for the url of the photo with the largest side
            int height = next.get("height").asInt();
            int width = next.get("width").asInt();
            if (height > maxDimension || width > maxDimension) {
                url = next.get("url").asText();
            }
        }
        return url;
    }

    // jsonNode here is JsonObject OBJECT (item["attachments"][index])
    private static Attachment makeAttachment(JsonNode jsonNode) {
        String type = jsonNode.get("type").asText();
        if (type.equals("photo")) { // if it is photo-attachment
            int id = jsonNode.get("photo").get("id").asInt();
            long date = jsonNode.get("photo").get("date").asLong();
            String url = findLargestPhotoUrl(jsonNode.get("photo").get("sizes"));
            return new Photo(type, id, date, url);

        } else if (type.equals("video")) { // if it is video-attachment
            int id = jsonNode.get("video").get("id").asInt();
            int ownerId = jsonNode.get("video").get("owner_id").asInt();
            long date = jsonNode.get("video").get("date").asLong();
            String title = jsonNode.get("video").get("title").asText();
            String description = jsonNode.get("video").get("description").asText();
            return new Video(type, id, ownerId, date, title, description);

        } else { // if it is other attachment (like poll or audio)
            return new NonPrintableAttachment(type);
        }
    }

    // jsonNode here is JsonObject ARRAY (item["attachments"])
    private static List<Attachment> makeAttachmentsList(JsonNode jsonNode) {
        List<Attachment> attachments = new ArrayList<>();
        for (JsonNode next : jsonNode) {
            attachments.add(makeAttachment(next));
        }
        return attachments;
    }

    // itemNode here is JSON-object of vk-post (item of ["response"]["items"] in api-response)
    private static Post makePostObject(JsonNode itemNode) {
        int id = itemNode.get("id").asInt();
        int fromId = itemNode.get("from_id").asInt();
        int ownerId = itemNode.get("owner_id").asInt();
        long date = itemNode.get("date").asLong();
        String postType = itemNode.get("post_type").asText();
        String text = itemNode.get("text").asText();

        //these parameters may not be in item
        int markedAsAds = 0;
        if (itemNode.has("marked_as_ads")) {
            markedAsAds = itemNode.get("marked_as_ads").asInt();
        }
        int isPinned = 0;
        if (itemNode.has("is_pinned")) {
            isPinned = itemNode.get("is_pinned").asInt();
        }

        // check attachments if any make AttachmentPost else just Post
        if (itemNode.has("attachments")) {
            List<Attachment> attachments = makeAttachmentsList(itemNode.get("attachments"));
            return new AttachmentPost(id, fromId, ownerId, date, postType, text, markedAsAds, isPinned, attachments);
        } else {
            return new Post(id, fromId, ownerId, date, postType, text, markedAsAds, isPinned);
        }
    }

    //
    public static List<Post> makePostObjectsList(int ownerId, int count) throws Exception {
        String jsonString = parseWall(ownerId, count);
        JsonNode jsonItemsNode = readJson(jsonString).get("response").get("items");
        List<Post> posts = new ArrayList<>();
        try {
            if (jsonItemsNode != null) {
                for (int i = 0; i < jsonItemsNode.size(); i++) {
                    posts.add(makePostObject(jsonItemsNode.get(i)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }
}
