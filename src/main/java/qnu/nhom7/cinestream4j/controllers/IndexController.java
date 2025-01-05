package qnu.nhom7.cinestream4j.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skhanal5.models.Filter;
import com.skhanal5.models.SelectQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import qnu.nhom7.cinestream4j.supabase.Supabase;

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
    public String index(@CookieValue("userid") String userid, Model model) throws IOException {
        // temporary requires the user to login, for now.
        if (userid == null) {
            return "redirect:/login";
        }

        SelectQuery query = new SelectQuery.SelectQueryBuilder()
                .from("profiles")
                .select("username", "full_name")
                .filter(new Filter.FilterBuilder()
                        .equals("id", userid)
                        .build())
                .build();

        ArrayList response = this.client.getClient().executeSelect(query, ArrayList.class);
        LinkedHashMap<String, String> a = (LinkedHashMap<String, String>) response.get(0);

        model.addAttribute("name", a.get("full_name"));
        model.addAttribute("username", a.get("username"));

        return "index";
    }
}
