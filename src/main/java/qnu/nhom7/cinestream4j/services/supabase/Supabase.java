package qnu.nhom7.cinestream4j.services.supabase;

import com.skhanal5.core.SpringSupabaseClient;
import com.skhanal5.models.SelectQuery;
import lombok.Getter;
import qnu.nhom7.cinestream4j.services.Token;

@Getter
public class Supabase {
    private final SpringSupabaseClient client;

    public Supabase() {
        client = SpringSupabaseClient.newInstance(Token.supabaseUrl, Token.serviceKey);
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
