
    package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import qnu.nhom7.cinestream4j.services.tmdb.Discover;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class UpComingController {

    @GetMapping("/upcoming")
    public String showUpComingPage(@CookieValue(value = "lang", defaultValue = "vi-VN") String lang, Model model) throws IOException, InterruptedException {
        // Lấy danh sách phim sắp ra mắt từ API
        ArrayList upcomingMovies = Discover.getUpcoming(lang);

        // Đưa danh sách phim vào model để hiển thị trên view
        model.addAttribute("movies", upcomingMovies);

        return "upcoming"; // Điều hướng tới upcoming.html
    }
}
