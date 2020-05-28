package com.study.ybdk.test;

import com.study.ybdk.client.YbClient;
import com.study.ybdk.entity.YibanUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientTest {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ChromeDriver browser;
    @Autowired
    private YbClient client;

    @Test
    public void testBaidu() {
        browser.get("http://www.baidu.com");
        System.out.println(browser.getPageSource());
    }
    @Test
    public void testRestTemplate(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://www.baidu.com", String.class);
        System.out.println(forEntity.getBody());
    }
    @Test
    public void testLogin() throws InterruptedException {
//        client.login("17758963927", "taorui.357159");
    }
    @Test
    public void testGetUSerInfo(){
        YibanUser user = YibanUser.builder().username("17758963927").password("taorui.357159").build();
        System.out.println(client.fetchUserInfo(user));
    }
}
