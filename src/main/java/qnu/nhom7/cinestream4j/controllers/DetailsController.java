package qnu.nhom7.cinestream4j.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class DetailsController {

    private final String API_KEY = "5b3554c90c5c911c652788f8b65db269";
    private final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    @GetMapping("/details")
    public String movieDetails(@RequestParam("movieId") String movieId, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = BASE_URL + movieId + "?api_key=" + API_KEY + "&language=en-US";

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(apiUrl, Map.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> movieData = response.getBody();

                // In dữ liệu để kiểm tra
                System.out.println("Dữ liệu từ API: " + movieData);

                model.addAttribute("movie", movieData);
            } else {
                model.addAttribute("error", "Không thể tải thông tin phim.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi kết nối API.");
        }

        return "details";
    }
}
