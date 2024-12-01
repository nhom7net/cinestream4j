package qnu.nhom7.cinestream4j.controllers;

import com.skhanal5.models.Filter;
import com.skhanal5.models.SelectQuery;
import com.skhanal5.models.UpdateQuery;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qnu.nhom7.cinestream4j.supabase.Auth;
import qnu.nhom7.cinestream4j.supabase.Supabase;
import qnu.nhom7.cinestream4j.utils.CookieHelper;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
public class AuthController {
    private final Auth auth;
    private final Supabase client;

    public AuthController() {
        this.auth = new Auth();
        this.client = new Supabase();
    }

    @GetMapping("/login")
    public String login(@RequestParam(required=false, defaultValue = "") String error, Model model) {
        var e = "";
        if (Objects.equals(error, "invalid_credentials")) e = "Email hoặc mật khẩu bị sai!";
        model.addAttribute("error", e);
        return "login";
    }

    @GetMapping("/signup")
    public String signup(@RequestParam(required=false, defaultValue = "") String error, Model model) {
        var e = switch (error) {
            case "weak-password" -> "Mật khẩu phải từ 8 kí tự trở lên!";
            case "confirm-failed" -> "Mật khẩu không trùng khớp!";
            case "user_already_exists" -> "Email này đã được đăng ký!";
            default -> "";
        };
        model.addAttribute("error", e);
        return "signup";
    }


    @PostMapping("/login")
    public String loginProcessor(@RequestParam Map<String, String> body, HttpServletResponse response) throws IOException, InterruptedException {
        var data = this.auth.signIn(body.get("email"), body.get("password"));
        if (Objects.equals(data, "invalid_credentials")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/login?error=" + data;
        }

        response.addCookie(CookieHelper.setCookie("userid", data));
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String signUpProcessor(@RequestParam Map<String, String> body, HttpServletResponse response) throws IOException, InterruptedException {
        var password = body.get("password");
        var confirm = body.get("confirmpassword");

        if (password.length() < 8) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/signup?error=weak-password";
        }
        if (!Objects.equals(password, confirm)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/signup?error=confirm-failed";
        }

        var username = body.get("username");
        var displayName = body.get("displayName");

        var data = this.auth.signUp(body.get("email"), body.get("password"));

        if (Objects.equals(data, "user_already_exists")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/signup?error=" + data;
        }

        Filter filter = new Filter.FilterBuilder()
                .equals("id", data)
                .build();

        UpdateQuery query = new UpdateQuery.UpdateQueryBuilder()
                .from("profiles")
                .update(Map.of("username", username), Map.of("full_name", displayName))
                .filter(filter)
                .build();

        // TODO: Fix 400 Bad Request (haven't find out the cause yet)
        var sbResp = client.getClient().executeUpdate(query, String.class);

        System.out.println(sbResp);

        response.addCookie(CookieHelper.setCookie("userid", data));
        return "redirect:/";
    }
}
