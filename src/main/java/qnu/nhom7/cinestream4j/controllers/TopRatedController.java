
    package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import qnu.nhom7.cinestream4j.services.tmdb.Discover;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class TopRatedController {

    @GetMapping("/toprated")
    public String showTopRatedPage(@CookieValue(value = "lang", defaultValue = "vi-VN") String lang, Model model) throws IOException, InterruptedException {
        // Lấy danh sách phim được đánh giá cao từ API
        ArrayList topRatedMovies = Discover.getTopRated(lang);

        // Đưa danh sách phim vào model để hiển thị trên view
        model.addAttribute("movies", topRatedMovies);

        return "toprated"; // Điều hướng tới toprated.html
    }
}
