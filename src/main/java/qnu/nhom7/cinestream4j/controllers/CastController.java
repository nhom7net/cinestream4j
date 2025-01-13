package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CastController {

    @GetMapping("/cast")
    public String showCastPage() {
        return "cast";  // Điều hướng tới cast.html
    }
}
