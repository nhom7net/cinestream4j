package qnu.nhom7.cinestream4j.services.tmdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import qnu.nhom7.cinestream4j.services.Token;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Movie {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> getInfo(String movieID) throws IOException, InterruptedException {
        String language = "en-US"; // Chỉnh sửa ngôn ngữ nếu cần
        String url = String.format("https://api.themoviedb.org/3/movie/%s?language=%s", movieID, language);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + Token.tmdb_token) // Sử dụng Bearer Token nếu có
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // Kiểm tra response
        if (response.statusCode() != 200) {
            throw new IOException("Error fetching movie data: " + response.body());
        }

        JsonNode nodes = mapper.readTree(response.body());

        return mapper.convertValue(nodes, Map.class); // Chuyển đổi thành Map<String, Object>
    }
    
    public static ArrayList getAllCasts(String movieID) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("https://api.themoviedb.org/3/movie/%s/credits?language=vi-VN", movieID)))
                .header("Content-Type", "application/json")
                .header("Authorization", Token.tmdb_token)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nodes = mapper.readTree(response.body());

        var a = nodes.get("cast").toString();

        return mapper.readValue(a, ArrayList.class);
    }
}
