package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
import java.util.Map;
import qnu.nhom7.cinestream4j.services.tmdb.Discover;

@Controller
public class CastController {

    @GetMapping("/cast")
    public String showCastPage(@RequestParam("personId") String personId, Model model) throws IOException, InterruptedException {
        // Lấy thông tin diễn viên
        Map<String, Object> castDetails = Discover.getCastDetails(personId);

        // Lấy danh sách phim
        var movies = Discover.getMoviesByCast(personId);

        // Đưa thông tin vào model để hiển thị trên view
        model.addAttribute("cast", castDetails);
        model.addAttribute("movies", movies);

        return "cast"; // Điều hướng tới cast.html
    }
}
