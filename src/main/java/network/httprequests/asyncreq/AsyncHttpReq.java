package network.httprequests.asyncreq;

/*
* In an asynchronous request, we don't wait for the response; instead, we handle
* the response whenever it is received by the client. In jQuery, we will make
* an asynchronous request and provide a callback that takes care of
* processing the response, while in the case of Java, we get an instance of
* java.util.concurrent.CompletableFuture, and then we invoke the thenApply method
* to process the response.
* */

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class AsyncHttpReq {
    public static void main(String[] args) throws URISyntaxException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder(new URI("http://httpbin.org/get"))
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        CompletableFuture<HttpResponse<String>> futureResp = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        CompletableFuture<Void> processedRequest =
                futureResp.thenAccept(asyncResp -> {
                    System.out.println("Status code: " + asyncResp.statusCode());
                    System.out.println("Body: " + asyncResp.body());
                });

        CompletableFuture.allOf(processedRequest).join();
        System.out.println("hi!");
    }
}
