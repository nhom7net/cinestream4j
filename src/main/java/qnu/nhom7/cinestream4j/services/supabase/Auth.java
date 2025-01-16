package qnu.nhom7.cinestream4j.services.supabase;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import qnu.nhom7.cinestream4j.services.Token;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Auth {
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public Auth() {
        this.httpClient = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public String signUp(String email, String password) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Token.supabaseUrl + "/auth/v1/signup"))
                .header("Content-Type", "application/json")
                .header("apikey", Token.serviceKey)
                .POST(HttpRequest.BodyPublishers.ofString("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}"))
                .build();

        HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode nameNode = this.mapper.readTree(response.body());

        if (nameNode.get("access_token") == null) {
            return nameNode.get("error_code").asText();
        }

        return nameNode.get("user").get("id").asText();
    }

    public String signIn(String email, String password) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Token.supabaseUrl + "/auth/v1/token?grant_type=password"))
                .header("Content-Type", "application/json")
                .header("apikey", Token.serviceKey)
                .POST(HttpRequest.BodyPublishers.ofString("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}"))
                .build();
        HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        JsonNode nameNode = this.mapper.readTree(response.body());

        if (nameNode.get("access_token") == null) {
            return nameNode.get("error_code").asText();
        }

        return nameNode.get("user").get("id").asText();
    }
}
