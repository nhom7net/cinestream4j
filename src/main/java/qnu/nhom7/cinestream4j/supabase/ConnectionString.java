package qnu.nhom7.cinestream4j.supabase;

public class ConnectionString {
    private final static String supabaseUrl = "https://qmtfowdtlfcfgnihkyjf.supabase.co";
    private final static String serviceKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFtdGZvd2R0bGZjZmduaWhreWpmIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTcyOTc2Mjg5OSwiZXhwIjoyMDQ1MzM4ODk5fQ.grIutHxyjT2S0kvhYv80tmX6zCoCIWLNKghDKrLCyDI";

    public static String getServiceKey() {
        return serviceKey;
    }

    public static String getSupabaseUrl() {
        return supabaseUrl;
    }
}
