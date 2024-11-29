package qnu.nhom7.cinestream4j.utils;

import com.skhanal5.core.SpringSupabaseClient;
import com.skhanal5.models.SelectQuery;
import lombok.Getter;

@Getter
public class Supabase {
    private final SpringSupabaseClient client;
    public Supabase() {
        client = SpringSupabaseClient.newInstance("https://qmtfowdtlfcfgnihkyjf.supabase.co", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFtdGZvd2R0bGZjZmduaWhreWpmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mjk3NjI4OTksImV4cCI6MjA0NTMzODg5OX0.GGGYdcO5jcnp6qO_OAJA4PK_QnwX1bp-2dle0s0QhRw");
    }

    public void testClient() {
        System.out.println("Attempting to access profile table...");
        SelectQuery query = new SelectQuery
                .SelectQueryBuilder()
                .from("profiles")
                .select("*")
                .build();
        var res = client.executeSelect(query, String.class);
        System.out.println(res);
    }
}
