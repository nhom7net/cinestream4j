package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NowPlayingController {

    @GetMapping("/nowplaying")
    public String showNowPlayingPage() {
        return "nowplaying";  // Điều hướng tới nowplaying.html
    }
}
