package by.bivis.vkParser.JSONs;

import by.bivis.settings.Settings;
import by.bivis.vkParser.JSONs.tools.ReadWrite;
import by.bivis.vkParser.JSONs.tools.SendGet;
import org.json.JSONObject;
import java.io.IOException;

// just parse simple get-request

public class Parser {
    private final String TOKEN = Settings.getSetting("vkToken");
    private final String API_VERSION = "5.124";
    //First argument - Method name, second - parameters
    private final String API_URL = "https://api.vk.com/method/%s?%s&access_token=" + TOKEN + "&v=" + API_VERSION;

    public Parser() throws IOException {
    }

    public String parseWall() throws Exception {
        String api_method = String.format(API_URL, "wall.get", "owner_id=-126771813&count=3");
        System.out.println(api_method);
        SendGet sendGet = new SendGet();
        return sendGet.send(api_method);
    }


    public static void main(String[] args) throws Exception {
        Parser parser = new Parser();
        String wall = parser.parseWall();

        ReadWrite.write("./settings/3postsSasamba.json", new String[] {wall});

        JSONObject jsonObject = new JSONObject(wall);
        Object toPrint = jsonObject.getJSONObject("response");
        System.out.println(toPrint);
        System.out.println(toPrint.getClass());

        //TODO ЕБИСЬ С ЭТИМ!
    }
}
