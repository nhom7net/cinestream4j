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

public class Discover {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static ArrayList getPopulars() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/popular?language=vi-VN"))
                .header("Content-Type", "application/json")
                .header("Authorization", Token.tmdb_token)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nodes = mapper.readTree(response.body());

        var a = nodes.get("results").toString();

        return mapper.readValue(a, ArrayList.class);
    }

    public static ArrayList getTrending() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/trending/movie/day?language=vi-VN"))
                .header("Content-Type", "application/json")
                .header("Authorization", Token.tmdb_token)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nodes = mapper.readTree(response.body());

        var a = nodes.get("results").toString();

        return mapper.readValue(a, ArrayList.class);
    }

    public static ArrayList getGenreList() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/genre/movie/list?language=vi-VN"))
                .header("Content-Type", "application/json")
                .header("Authorization", Token.tmdb_token)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nodes = mapper.readTree(response.body());

        var a = nodes.get("genres").toString();

        return mapper.readValue(a, ArrayList.class);
    }
}
