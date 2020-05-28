package com.study.ybdk.client;

import com.google.common.collect.Maps;
import com.study.ybdk.entity.YibanUser;
import com.study.ybdk.utils.CookieUtils;
import com.study.ybdk.utils.RedisKeyUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "yiban.url") //需要注入的字段是通过set注入的，必须有set方法，不能是final
@Data
@Slf4j
public class YbClient {
    private final ChromeDriver browser;
    private final RestTemplate client;
    private final RedisTemplate<String, String> redisTemplate;
    private String exam;
    private String userInfo;
    private String test;
    private String answer;
    private String score;

    public Set<Cookie> login(YibanUser user) throws InterruptedException {
        browser.get("https://oauth.yiban.cn/code/html?client_id=8486f6909598db9a&redirect_uri=http://f.yiban.cn/iapp254007&state=STATE");
        WebElement userNameInput = browser.findElementByXPath("//*[@id=\"oauth_uname_w\"]");
        WebElement passwordInput = browser.findElementByXPath("//*[@id=\"oauth_upwd_w\"]");
        WebElement submitButton = browser.findElementByClassName("oauth_check_login");
        userNameInput.clear();
        userNameInput.sendKeys(user.getUsername());
        passwordInput.clear();
        passwordInput.sendKeys(user.getPassword());
        submitButton.click();
        Thread.sleep(2 * 1000);
        return browser.manage().getCookies();
    }

    public Map fetchUserInfo(YibanUser user){
        HttpHeaders httpHeaders = new HttpHeaders();
        String redisKey = RedisKeyUtils.generateYibanTokenRedisKey(user);
        String cookieStr = redisTemplate.opsForValue().get(redisKey);
        assert cookieStr != null : "yiban user cookie is null";
        List<String> cookieArray = CookieUtils.cookieStr2Array(cookieStr);
        httpHeaders.put("Cookie", cookieArray);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, httpHeaders);
        try{
            return  client.exchange(userInfo, HttpMethod.GET, requestEntity,Map.class).getBody();
        } catch (Exception e){
            // 如果发生了异常那么就是token出错
            log.warn("fetchUserInfo: yibanUser[username:{}, password: {}] token error", user.getUsername(), user.getPassword());
            HashMap<String, String> res = new HashMap<>(1);
            res.put("code", "-1");
            return res;
        }
    }
}
