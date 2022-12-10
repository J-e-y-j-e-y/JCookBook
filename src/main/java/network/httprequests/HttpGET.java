package network.httprequests;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpGET {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        //firstExample();
        secondExample();
    }

    static void firstExample() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                //.proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://javarush.ru"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    static void secondExample() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest
                .newBuilder(new URI("http://httpbin.org/get"))
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }
}
