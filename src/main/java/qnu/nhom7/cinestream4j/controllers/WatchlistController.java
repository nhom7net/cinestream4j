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

import java.util.ArrayList;
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
            model.addAttribute("error", "Bạn cần đăng nhập để xem danh sách.");
            return "details";
        }

        Filter filter = new Filter.FilterBuilder()
                .equals("userid", userId)
                .build();

        try {
            // Tạo truy vấn để lấy data
            SelectQuery query = new SelectQuery.SelectQueryBuilder()
                    .from("watchlist")
                    .select("*")
                    .filter(filter)
                    .build();

            // Thực hiện truy vấn
            var response = client.getClient().executeSelect(query, ArrayList.class);
            String a = null;
            return "watchlist";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Không thể lấy dữ liệu từ Supabase.");
        }
        return "watchlist";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToWatchlist(@RequestBody Map<String, String> payload,
                                            HttpSession session) {
        String movieId = payload.get("movieId");
        String userId = (String) session.getAttribute("userid");

        if (userId == null) {
            return ResponseEntity.status(401).body(Map.of("message", "Bạn cần đăng nhập!"));
        }

        if (movieId == null || movieId.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Movie ID không hợp lệ,"));
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

            return ResponseEntity.ok(Map.of("message", "Đã thêm vào danh sách!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message", "Lỗi server."));
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
