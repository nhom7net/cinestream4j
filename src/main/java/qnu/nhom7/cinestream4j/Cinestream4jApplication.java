package qnu.nhom7.cinestream4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qnu.nhom7.cinestream4j.supabase.Auth;

import java.io.IOException;

@SpringBootApplication
@RestController
public class Cinestream4jApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(Cinestream4jApplication.class, args);
        System.out.println("Truy cập tại http://localhost:8080");

        // some test
        Auth auth = new Auth();

        String a = auth.signUp("bonniefoxy2009@gmail.net", "shionlove");
        System.out.println(a);
    }

    @GetMapping("/login")
    public String login() {
        return String.format("login page ");
    }
}
