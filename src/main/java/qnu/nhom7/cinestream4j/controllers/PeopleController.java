package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;

import qnu.nhom7.cinestream4j.services.tmdb.Discover;

@Controller
public class PeopleController {

    // Hiển thị danh sách diễn viên nổi tiếng
    @GetMapping("/people")
    public String showPeoplePage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String query,
            Model model) throws IOException, InterruptedException {

        ArrayList people;

        // Kiểm tra nếu có từ khóa tìm kiếm
        if (query != null && !query.isEmpty()) {
            people = Discover.searchPeople(query, page);
        } else {
            people = Discover.getPopularPeople(page);
        }

        model.addAttribute("people", people);
        model.addAttribute("currentPage", page);
        model.addAttribute("query", query);

        return "people";
    }
}
