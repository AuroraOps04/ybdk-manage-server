package com.study.ybdk.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void test(){
        redisTemplate.opsForValue().set("a", "1");
    }
}
