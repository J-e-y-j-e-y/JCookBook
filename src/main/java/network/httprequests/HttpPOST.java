package network.httprequests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class HttpPOST {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        Map<String, String> requestBody = Map.of("key1", "value1", "key2", "value2");

        ObjectMapper objectMapper = new ObjectMapper();

        HttpRequest req = HttpRequest.newBuilder(new URI("http://httpbin.org/post"))
                .POST(
                        HttpRequest.BodyPublishers.ofString(
                                objectMapper.writeValueAsString(requestBody)
                        )
                )
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }
}
