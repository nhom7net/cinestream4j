package qnu.nhom7.cinestream4j.services.tmdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import qnu.nhom7.cinestream4j.services.Token;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Discover {
    private final HttpClient httpClient;
    private final ObjectMapper mapper;


    public Discover() {
        this.httpClient = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public void getPopulars() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc"))
                .header("Content-Type", "application/json")
                .header("Authorization", Token.ACCESS_TOKEN)
                .build();

        HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nameNode = this.mapper.readTree(response.body());

    }
}
