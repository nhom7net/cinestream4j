package qnu.nhom7.cinestream4j.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skhanal5.models.DeleteQuery;
import com.skhanal5.models.Filter;
import com.skhanal5.models.InsertQuery;
import com.skhanal5.models.SelectQuery;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import qnu.nhom7.cinestream4j.services.supabase.Supabase;
import qnu.nhom7.cinestream4j.services.tmdb.Movie;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/watchlist")
public class WatchlistController {
    private final Supabase client;
    private final ObjectMapper mapper = new ObjectMapper();

    public WatchlistController() {
        this.client = new Supabase();
    }

    @GetMapping("")
    public String watchlist(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userid");

        // Kiểm tra nếu chưa đăng nhập
        if (userId == null) {
            model.addAttribute("errorMessage", "⚠️ Bạn cần đăng nhập để xem danh sách.");
            return "watchlist";
        }

        Filter filter = new Filter.FilterBuilder()
            .equals("userid", userId)
            .build();

        try {
            // Lấy danh sách từ Supabase
            SelectQuery query = new SelectQuery.SelectQueryBuilder()
                .from("watchlist")
                .select("*")
                .filter(filter)
                .build();

            ArrayList<LinkedHashMap<String, String>> response;
            response = client.getClient().executeSelect(query, ArrayList.class);

            if (response.isEmpty()) {
                model.addAttribute("infoMessage", "📭 Danh sách xem của bạn đang trống.");
            }
            else {
                ArrayList<Map<String, Object>> movies = new ArrayList<>();
                for (LinkedHashMap<String, String> movie: response) {
                    movies.add(Movie.getInfo(movie.get("movieId")));
                }
                model.addAttribute("watchlist", movies);
            }

            return "watchlist";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "❌ Không thể lấy dữ liệu từ Supabase. Vui lòng thử lại sau.");
            return "watchlist";
        }
    }

    @PostMapping("/add")
    public String addToWatchlist(@RequestBody Map<String, String> payload,
        HttpSession session,
        Model model) {

        String movieId = payload.get("movieId");
        String userId = (String) session.getAttribute("userid");

        if (userId == null) {
            model.addAttribute("errorMessage", "⚠️ Bạn cần đăng nhập để thêm vào danh sách!");
            return "watchlist";
        }

        if (movieId == null || movieId.isEmpty()) {
            model.addAttribute("errorMessage", "❗ Movie ID không hợp lệ.");
            return "watchlist";
        }

        try {
            InsertQuery query = new InsertQuery.InsertQueryBuilder()
                .from("watchlist")
                .insert(Map.of(
                    "userid", userId,
                    "movieId", movieId
                ))
                .build();

            client.getClient().executeInsert(query, String.class);

            model.addAttribute("successMessage", "✅ Đã thêm phim vào danh sách thành công!");
            return "watchlist";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "❌ Lỗi server. Vui lòng thử lại sau.");
            return "watchlist";
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeFormWatchList(@RequestBody Map<String, String> payload, HttpSession session) {
        String movieId = payload.get("movieId");
        String userId = (String) session.getAttribute("userid");

        if (userId == null) {
            return ResponseEntity.status(401).body(Map.of("message", "Bạn cần đăng nhập để thực hiện thao tác này."));
        }

        if (movieId == null || movieId.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Movie ID không hợp lệ"));
        }

        Filter filter = new Filter.FilterBuilder()
            .equals("userid", userId)
            .equals("movieId", movieId)
            .build();

        try {
            var deleteQuery = new DeleteQuery.DeleteQueryBuilder()
                .from("watchlist")
                .filter(filter)
                .build();

            client.getClient().executeDelete(deleteQuery, String.class);

            return ResponseEntity.ok(Map.of("message", "Đã xóa phim khỏi danh sách!"));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Lỗi server."));
        }
    }
}
