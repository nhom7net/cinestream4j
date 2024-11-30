package qnu.nhom7.cinestream4j.supabase;

public class ConnectionString {
    private final static String supabaseUrl = "https://qmtfowdtlfcfgnihkyjf.supabase.co";
    private final static String serviceKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFtdGZvd2R0bGZjZmduaWhreWpmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mjk3NjI4OTksImV4cCI6MjA0NTMzODg5OX0.GGGYdcO5jcnp6qO_OAJA4PK_QnwX1bp-2dle0s0QhRw";

    public static String getServiceKey() {
        return serviceKey;
    }

    public static String getSupabaseUrl() {
        return supabaseUrl;
    }
}
