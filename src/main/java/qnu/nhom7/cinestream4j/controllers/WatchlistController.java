package qnu.nhom7.cinestream4j.controllers;

import com.skhanal5.models.DeleteQuery;
import com.skhanal5.models.Filter;
import com.skhanal5.models.InsertQuery;
import com.skhanal5.models.SelectQuery;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qnu.nhom7.cinestream4j.supabase.Supabase;

import java.util.Map;

@Controller
@RequestMapping("/watchlist")
public class WatchlistController {
    private final Supabase client;

    public WatchlistController() {
        this.client = new Supabase();
    }

    @GetMapping
    public String watchlist(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");

        // Kiểm tra nếu chưa đăng nhập
        if (username == null) {
            model.addAttribute("error", "Bạn cần đăng nhập để xem danh sách.");
            return "details";
        }

        Filter filter = new Filter.FilterBuilder()
                .equals("username", username)
                .build();

        try {
            // Tạo truy vấn để lấy data
            SelectQuery query = new SelectQuery.SelectQueryBuilder()
                    .from("watchlist")
                    .select("*")
                    .filter(filter)
                    .build();

            // Thực hiện truy vấn
            var response = client.getClient().executeSelect(query, String.class);

            model.addAttribute("watchlist", response);

            return "watchlist";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Không thể lấy dữ liệu từ Supabase.");
        }
        return "watchlist";
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<?> addToWatchlist(@RequestBody Map<String, String> payload,
                                            HttpSession session) {
        String movieId = payload.get("movieId");
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return ResponseEntity.status(401).body(Map.of("message", "Bạn cần đăng nhập!"));
        }

        if (movieId == null || movieId.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Movie ID không hợp lệ,"));
        }

        try {
            InsertQuery query = new InsertQuery.InsertQueryBuilder()
                    .from("watchlist")
                    .insert(Map.of(
                            "username", username,
                            "movieId", movieId
                    ))
                    .build();

            client.getClient().executeInsert(query, String.class);

            return ResponseEntity.ok(Map.of("message", "Đã thêm vào danh sách!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message", "Lỗi server."));
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeFormWatchList(@RequestBody Map<String, String> payload, HttpSession session) {
        String movieId = payload.get("movieId");
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return ResponseEntity.status(401).body(Map.of("message", "Bạn cần đăng nhập để thực hiện thao tác này."));
        }

        if (movieId == null || movieId.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Movie ID không hợp lệ"));
        }

        Filter filter = new Filter.FilterBuilder()
                .equals("username", username)
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
