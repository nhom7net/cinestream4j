package qnu.nhom7.cinestream4j.supabase;

public class ConnectionString {
    private final static String supabaseUrl = "https://vfplyrnitdcuwmkjctrn.supabase.co";
    private final static String serviceKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZmcGx5cm5pdGRjdXdta2pjdHJuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzY5NTg2MTYsImV4cCI6MjA1MjUzNDYxNn0.HzwfTVcMurHpo6YmydA6pB_AFTlb3GTe3qoucUzKGCU";

    public static String getServiceKey() {
        return serviceKey;
    }

    public static String getSupabaseUrl() {
        return supabaseUrl;
    }
}
