package qnu.nhom7.cinestream4j.utils;

import jakarta.servlet.http.Cookie;

public class CookieHelper {
    public static Cookie setCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        return cookie;
    }
}
