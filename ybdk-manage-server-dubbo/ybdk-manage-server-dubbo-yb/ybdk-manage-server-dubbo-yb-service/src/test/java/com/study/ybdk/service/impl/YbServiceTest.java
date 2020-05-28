package com.study.ybdk.service.impl;

import com.study.ybdk.entity.YibanUser;
import com.study.ybdk.service.YbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class YbServiceTest {
    @Autowired
    private YbService ybService;

    @Test
    public void login() throws InterruptedException {
        YibanUser yibanUser = YibanUser.builder()
                .username("17758963927")
                .password("taorui.357159")
                .build();
        System.out.println(ybService.login(yibanUser));
    }
}