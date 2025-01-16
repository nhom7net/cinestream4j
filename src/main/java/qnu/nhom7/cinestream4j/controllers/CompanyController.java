package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;

import qnu.nhom7.cinestream4j.services.tmdb.Discover;

@Controller
public class CompanyController {

    @GetMapping("/company")
    public String showCompanyPage(@RequestParam String companyId, Model model) throws IOException, InterruptedException {
        // Lấy thông tin hãng phim
        Map<String, Object> companyInfo = Discover.getCompanyDetails(companyId);

        // Lấy danh sách phim của hãng
        var companyMovies = Discover.getMoviesByCompany(companyId);

        // Truyền dữ liệu sang view
        model.addAttribute("company", companyInfo);
        model.addAttribute("movies", companyMovies);
;
        return "company";
    }
}
