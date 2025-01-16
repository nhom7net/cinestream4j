package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopRatedController {

    @GetMapping("/toprated")
    public String showTopRatedPage() {
        return "toprated";  // Điều hướng tới toprated.html
    }
}
