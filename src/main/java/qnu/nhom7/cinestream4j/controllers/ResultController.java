package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qnu.nhom7.cinestream4j.services.tmdb.Discover;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class ResultController {

    @GetMapping("/result.html")
    public String searchMovies(@RequestParam("query") String query, Model model) throws IOException, InterruptedException {
        // Kiểm tra giá trị query
        System.out.println("Tìm kiếm với query: " + query);

        ArrayList movies = Discover.searchMovies(query);
        model.addAttribute("movies", movies);
        model.addAttribute("query", query);

        return "result";
    }

}
