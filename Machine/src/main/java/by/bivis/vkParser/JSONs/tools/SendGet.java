package by.bivis.vkParser.JSONs.tools;

import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SendGet {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private String url;

    public String send(String url) throws Exception {
        try {
            HttpGet request = new HttpGet(url);

            // Add request headers
            request.addHeader("custom-key", "mkyong");
            request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();

                // return it as a String
                return EntityUtils.toString(entity);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        } finally {
            httpClient.close();
        }
    }
}
