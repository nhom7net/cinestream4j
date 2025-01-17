package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import qnu.nhom7.cinestream4j.services.tmdb.Discover;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class NowPlayingController {

    @GetMapping("/nowplaying")
    public String showNowPlayingPage(@CookieValue(value = "lang", defaultValue = "vi-VN") String lang,Model model) throws IOException, InterruptedException {
        // Lấy danh sách phim đang chiếu từ API
        ArrayList nowPlayingMovies = Discover.getNowPlaying();

        // Đưa danh sách phim vào model để hiển thị trên view
        model.addAttribute("movies", nowPlayingMovies);

        return "nowplaying";  // Điều hướng tới nowplaying.html
    }
}
