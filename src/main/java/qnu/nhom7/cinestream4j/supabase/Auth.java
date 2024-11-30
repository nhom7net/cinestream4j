package qnu.nhom7.cinestream4j.supabase;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Auth {
    private final HttpClient httpClient;

    public Auth() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String signUp(String email, String password) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ConnectionString.getSupabaseUrl() + "/auth/v1/signup"))
                .header("Content-Type", "application/json")
                .header("apikey", ConnectionString.getServiceKey())
                .POST(HttpRequest.BodyPublishers.ofString("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}"))
                .build();

        HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String signIn(String email, String password) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ConnectionString.getSupabaseUrl() + "/auth/v1/token?grant_type=password"))
                .header("Content-Type", "application/json")
                .header("apikey", ConnectionString.getServiceKey())
                .POST(HttpRequest.BodyPublishers.ofString("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}"))
                .build();

        HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
