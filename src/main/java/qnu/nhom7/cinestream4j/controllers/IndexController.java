
    package qnu.nhom7.cinestream4j.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import qnu.nhom7.cinestream4j.services.supabase.Supabase;
import qnu.nhom7.cinestream4j.services.tmdb.Discover;
import qnu.nhom7.cinestream4j.services.tmdb.Movie;

import java.io.IOException;

@Controller
public class IndexController {
    private final Supabase client;
    private final ObjectMapper mapper;

    public IndexController() {
        this.client = new Supabase();
        this.mapper = new ObjectMapper();
    }

    @GetMapping("/")
    public String index(@CookieValue(value = "userid", required = false) String userid, @CookieValue(value = "lang", defaultValue = "vi-VN") String lang, Model model) throws IOException, InterruptedException {
        // temporary requires the user to login, for now.
        if (userid == null) {
            return "redirect:/login";
        }

        var popular = Discover.getPopulars(lang);
        var trending = Discover.getTrending(lang);
        var genre = Discover.getGenreList(lang);


        model.addAttribute("popular", popular);
        model.addAttribute("trending", trending);
        model.addAttribute("genres", genre);
        model.addAttribute("lang", lang);
        return "index";
    }
}
