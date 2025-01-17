package qnu.nhom7.cinestream4j.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;

import qnu.nhom7.cinestream4j.services.tmdb.Discover;

@Controller
public class CompanyController {

    @GetMapping("/company")
    public String showCompanyPage(
        @RequestParam String companyId,
        @CookieValue(value = "lang", defaultValue = "vi-VN") String lang,
        Model model) throws IOException, InterruptedException {

        // Lấy thông tin hãng phim với tham số ngôn ngữ
        Map<String, Object> companyInfo = Discover.getCompanyDetails(companyId, lang);

        // Lấy danh sách phim của hãng với tham số ngôn ngữ
        var companyMovies = Discover.getMoviesByCompany(companyId, lang);

        // Truyền dữ liệu sang view
        model.addAttribute("company", companyInfo);
        model.addAttribute("movies", companyMovies);
        model.addAttribute("lang", lang); // Truyền ngôn ngữ hiện tại sang view

        return "company"; // Điều hướng tới company.html
    }
}
