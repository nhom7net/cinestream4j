
    package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import qnu.nhom7.cinestream4j.services.tmdb.Discover;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class GenreController {

    @GetMapping("/genres")
    public String showGenresPage(Model model, String genreId, String genreName) throws IOException, InterruptedException {
        // Lấy danh sách phim theo thể loại từ API
        ArrayList genreMovies = Discover.getMoviesByGenre(genreId);

        // Đưa vào model
        model.addAttribute("movies", genreMovies);
        model.addAttribute("genreName", genreName);

        return "genre"; // Điều hướng tới genre.html
    }
}
