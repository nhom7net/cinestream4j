package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {

    @GetMapping("/company")
    public String showCompanyPage() {
        return "company";  // Điều hướng tới company.html
    }
}
