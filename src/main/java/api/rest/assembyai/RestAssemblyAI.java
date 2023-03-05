package api.rest.assembyai;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestAssemblyAI {
    public static void main(String[] args) throws IOException, InterruptedException {
        Transcript transcript = new Transcript();
        transcript.setAudio_url(API_KEYS.AUDIO_LINK);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);
        System.out.println("transcript request: " + jsonRequest);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.assemblyai.com/v2/transcript"))
                .header("Authorization", API_KEYS.API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("post response : " + postResponse.body());

        transcript = gson.fromJson(postResponse.body(), Transcript.class);
        System.out.println("id = " + transcript.getId());

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.assemblyai.com/v2/transcript/" + transcript.getId()))
                .header("Authorization", API_KEYS.API_KEY)
                .GET()
                .build();

        while (true) {
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            //System.out.println(getResponse.body());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);

            System.out.println("status = " + transcript.getStatus());

            if("completed".equals(transcript.getStatus()) || "error".equals(transcript.getStatus())){
                break;
            }
            Thread.sleep(1000);
        }

        System.out.println("transcription has completed!");
        System.out.println("text: " + transcript.getText());
    }
}
