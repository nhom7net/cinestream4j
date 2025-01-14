package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class GenreController {

    private final String API_KEY = "5b3554c90c5c911c652788f8b65db269";
    private final String BASE_URL = "https://api.themoviedb.org/3/discover/movie";

    @GetMapping("/genres")
    public String genreMovies(@RequestParam("genreId") String genreId,
                              @RequestParam("genreName") String genreName,
                              Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = BASE_URL + "?api_key=" + API_KEY + "&with_genres=" + genreId + "&language=vi-VN";

        Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class);

        model.addAttribute("movies", response.get("results"));
        model.addAttribute("genreName", genreName);

        return "genre";
    }
}
