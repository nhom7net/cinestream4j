package qnu.nhom7.cinestream4j.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import qnu.nhom7.cinestream4j.services.tmdb.Movie;

import java.io.IOException;
import java.util.Map;

@Controller
public class DetailsController {
    @GetMapping("/details")
    public String movieDetails(@RequestParam("movieId") String movieId, Model model) throws IOException, InterruptedException {
        var movie = Movie.getInfo(movieId);
        var cast = Movie.getAllCasts(movieId);
        model.addAttribute("movie", movie);
        model.addAttribute("casts", cast);
        return "details";
    }
}
