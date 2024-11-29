package qnu.nhom7.cinestream4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qnu.nhom7.cinestream4j.utils.Supabase;

@SpringBootApplication
@RestController
public class Cinestream4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cinestream4jApplication.class, args);
        System.out.println("Truy cập tại http://localhost:8080");
    }

    @GetMapping("/test")
    public String test(@RequestParam(value = "name", defaultValue = "cinestream4j") String appName) {

        return String.format("%s da setup thanh cong!", appName);
    }
}
