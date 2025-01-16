package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PeopleController {

    @GetMapping("/people")
    public String showPeoplePage() {
        return "people";  // Điều hướng tới people.html
    }
}
