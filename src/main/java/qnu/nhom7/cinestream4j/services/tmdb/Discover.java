package qnu.nhom7.cinestream4j.services.tmdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import qnu.nhom7.cinestream4j.services.Token;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;import java.util.List;
import java.util.Map;


public class Discover {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static ArrayList getPopulars() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.themoviedb.org/3/movie/popular?language="+ lang))
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
            .uri(URI.create("https://api.themoviedb.org/3/trending/movie/day?language="+ lang))
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
            .uri(URI.create("https://api.themoviedb.org/3/genre/movie/list?language=" + lang))
            .header("Content-Type", "application/json")
            .header("Authorization", Token.tmdb_token)
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nodes = mapper.readTree(response.body());

        var a = nodes.get("genres").toString();

        return mapper.readValue(a, ArrayList.class);
    }

    public static ArrayList getTopRated() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.themoviedb.org/3/movie/top_rated?language=" + lang))
            .header("Content-Type", "application/json")
            .header("Authorization", Token.tmdb_token)
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nodes = mapper.readTree(response.body());

        var a = nodes.get("results").toString();

        return mapper.readValue(a, ArrayList.class);
    }

    public static ArrayList getUpcoming() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.themoviedb.org/3/movie/upcoming?language=" + lang))
            .header("Content-Type", "application/json")
            .header("Authorization", Token.tmdb_token)
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nodes = mapper.readTree(response.body());

        var a = nodes.get("results").toString();

        return mapper.readValue(a, ArrayList.class);
    }

    public static ArrayList getNowPlaying() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.themoviedb.org/3/movie/now_playing?language=" + lang))
            .header("Content-Type", "application/json")
            .header("Authorization", Token.tmdb_token)
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nodes = mapper.readTree(response.body());

        var a = nodes.get("results").toString();

        return mapper.readValue(a, ArrayList.class);
    }

    public static ArrayList getMoviesByGenre(String genreId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.themoviedb.org/3/discover/movie?language=" + lang + "&with_genres=" + genreId))
            .header("Content-Type", "application/json")
            .header("Authorization", Token.tmdb_token)
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nodes = mapper.readTree(response.body());

        var a = nodes.get("results").toString();

        return mapper.readValue(a, ArrayList.class);
    }

    public static Map<String, Object> getCompanyDetails(String companyId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.themoviedb.org/3/company/" + companyId + "?language=" + lang))
            .header("Content-Type", "application/json")
            .header("Authorization", Token.tmdb_token)
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), Map.class);
    }

    public static ArrayList getMoviesByCompany(String companyId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.themoviedb.org/3/discover/movie?with_companies=" + companyId + "&language="+lang))
            .header("Content-Type", "application/json")
            .header("Authorization", Token.tmdb_token)
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nodes = mapper.readTree(response.body());
        var results = nodes.get("results").toString();

        return mapper.readValue(results, ArrayList.class);
    }

    public static ArrayList getPopularPeople(int page) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.themoviedb.org/3/person/popular?language=vi-VN&page=" + page))
            .header("Content-Type", "application/json")
            .header("Authorization", Token.tmdb_token)
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nodes = mapper.readTree(response.body());
        String results = nodes.get("results").toString();

        return mapper.readValue(results, ArrayList.class);
    }

    public static ArrayList searchPeople(String query, int page) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.themoviedb.org/3/search/person?language=vi-VN&query=" + query + "&page=" + page))
            .header("Content-Type", "application/json")
            .header("Authorization", Token.tmdb_token)
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode nodes = mapper.readTree(response.body());
        String results = nodes.get("results").toString();

        return mapper.readValue(results, ArrayList.class);
    }

    /////cast///
    public static Map<String, Object> getCastDetails(String personId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(String.format("https://api.themoviedb.org/3/person/%s?language=vi-VN", personId)))
            .header("Content-Type", "application/json")
            .header("Authorization", Token.tmdb_token)
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode node = mapper.readTree(response.body());

        return mapper.convertValue(node, Map.class);
    }

    public static List<Map<String, Object>> getMoviesByCast(String personId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(String.format("https://api.themoviedb.org/3/person/%s/movie_credits?language=vi-VN", personId)))
            .header("Content-Type", "application/json")
            .header("Authorization", Token.tmdb_token)
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode node = mapper.readTree(response.body());

        return mapper.convertValue(node.get("cast"), List.class);
    }

}
