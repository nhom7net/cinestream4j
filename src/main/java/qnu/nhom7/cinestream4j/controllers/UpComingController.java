package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UpComingController {

    @GetMapping("/upcoming")
    public String showUpComingPage() {
        return "upcoming";  // Điều hướng tới upcoming.html
    }
}