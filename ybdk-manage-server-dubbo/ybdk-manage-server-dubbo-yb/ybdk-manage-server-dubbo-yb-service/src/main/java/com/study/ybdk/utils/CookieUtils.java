package com.study.ybdk.utils;

import org.openqa.selenium.Cookie;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public final class CookieUtils {
    public static String Cookies2String(Set<Cookie> cookies){
        StringBuilder cookieStr = new StringBuilder();
        for (Cookie cookie : cookies) {

            cookieStr
                    .append(cookie.getName())
                    .append("=")
                    .append(cookie.getValue())
                    .append(";");
        }
        return cookieStr.toString();
    }

    public static List<String> cookieStr2Array(String cookieStr){
        return Arrays.asList(cookieStr.split(";"));
    }
}
