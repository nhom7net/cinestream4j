package qnu.nhom7.cinestream4j.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skhanal5.models.Filter;
import com.skhanal5.models.SelectQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import qnu.nhom7.cinestream4j.services.supabase.Supabase;
import qnu.nhom7.cinestream4j.services.tmdb.Discover;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Controller
public class IndexController {
    private final Supabase client;
    private final ObjectMapper mapper;

    public IndexController() {
        this.client = new Supabase();
        this.mapper = new ObjectMapper();
    }

    @GetMapping("/")
    public String index(@CookieValue("userid") String userid, Model model) throws IOException, InterruptedException {
        // temporary requires the user to login, for now.
        if (userid == null) {
            return "redirect:/login";
        }

        var popular = Discover.getPopulars();
        var trending = Discover.getTrending();
        model.addAttribute("popular", popular);
        model.addAttribute("trending", trending);

        return "index";
    }
}
