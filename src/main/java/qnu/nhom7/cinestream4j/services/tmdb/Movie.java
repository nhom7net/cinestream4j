package qnu.nhom7.cinestream4j.services.tmdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import qnu.nhom7.cinestream4j.services.Token;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Movie {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static LinkedHashMap getInfo(String movieID) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("https://api.themoviedb.org/3/movie/%s?language=vi-VN", movieID)))
                .header("Content-Type", "application/json")
                .header("Authorization", Token.tmdb_token)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nodes = mapper.readTree(response.body());

        var a = nodes.toString();

        return mapper.readValue(a, LinkedHashMap.class);
    }

}
