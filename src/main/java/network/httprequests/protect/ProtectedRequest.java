package network.httprequests.protect;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProtectedRequest {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String username = "user";
        String password = "passwd";

        UsernamePasswordAuthenticator auth = new UsernamePasswordAuthenticator(username, password);

        HttpClient client = HttpClient.newBuilder()
                .authenticator(auth)
                .build();

        HttpRequest req = HttpRequest.newBuilder(new URI("http://httpbin.org/basic-auth/user/passwd"))
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }
}
