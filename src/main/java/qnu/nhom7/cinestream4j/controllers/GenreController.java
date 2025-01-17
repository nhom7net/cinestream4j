
    package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qnu.nhom7.cinestream4j.services.tmdb.Discover;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class GenreController {

    @GetMapping("/genres")
    public String showGenresPage(
        @CookieValue(value = "lang", defaultValue = "vi-VN") String lang,
        @RequestParam("genreId") String genreId,
        @RequestParam("genreName") String genreName,
        Model model) throws IOException, InterruptedException {

        // Lấy danh sách phim theo thể loại với tham số ngôn ngữ
        ArrayList genreMovies = Discover.getMoviesByGenre(genreId, lang);

        // Đưa dữ liệu vào model để hiển thị trên giao diện
        model.addAttribute("movies", genreMovies);
        model.addAttribute("genreName", genreName);
        model.addAttribute("lang", lang); // Thêm ngôn ngữ hiện tại vào model

        return "genre"; // Điều hướng tới genre.html
    }
}
