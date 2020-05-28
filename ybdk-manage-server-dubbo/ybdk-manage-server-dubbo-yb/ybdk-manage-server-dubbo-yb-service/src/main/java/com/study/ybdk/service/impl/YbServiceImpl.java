package com.study.ybdk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.study.ybdk.client.YbClient;
import com.study.ybdk.entity.YibanUser;
import com.study.ybdk.mapper.YibanUserMapper;
import com.study.ybdk.service.YbService;
import com.study.ybdk.utils.CookieUtils;
import com.study.ybdk.utils.RedisKeyUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.Cookie;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DigestUtils;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service(version = "${dubbo.service.version}")
@RequiredArgsConstructor
public class YbServiceImpl implements YbService {
    private final YbClient client;
    private final RedisTemplate<String, String> redisTemplate;
    private final YibanUserMapper yibanUserMapper;

    @Override
    public int login(YibanUser yibanUser) throws InterruptedException {
        Set<Cookie> cookies = client.login(yibanUser);
        // 说明登陆失败
        if (cookies.size() < 2){
            return 0;
        }
        // 如果存在就是更新
        storageYibanToken2Redis(cookies, yibanUser);
        return 1;
    }

    @Override
    public int refreshUserInfo(YibanUser yibanUser) {

        // TODO: 考虑这种泛型该怎么写才优雅
        Map<String, Object> map = client.fetchUserInfo(yibanUser);
        if(!StringUtils.equals((String) map.get("code"), "200")){
            return 0;
        }
        Map<String, String> data = (Map) map.get("data");
        yibanUser.setSex(data.get("sex"));
        yibanUser.setStudentId(data.get("student_id"));
        yibanUser.setRealName(data.get("real_name"));
        yibanUser.setHeadUrl(data.get("head_url"));
        return yibanUserMapper.updateById(yibanUser);
    }

    @Override
    public YibanUser getYibanUserById(Integer id) {
        return yibanUserMapper.selectById(id);
    }

    @Override
    public List<YibanUser> listYibanUserByIds(List<Integer> ids) {
        return yibanUserMapper.selectBatchIds(ids);
    }


    private void storageYibanToken2Redis(Set<Cookie> cookies, YibanUser yibanUser) {
        String cookieStr = CookieUtils.Cookies2String(cookies);
        // 将用户的cookie存储到redis当中 3d599b2afbb59df5c2b00ef32ff732b7628b593c
        String redisKey = RedisKeyUtils.generateYibanTokenRedisKey(yibanUser);

        Date expiry = ((Cookie) cookies.toArray()[0]).getExpiry();
        if (expiry == null) {
            redisTemplate.opsForValue().set(redisKey, cookieStr, 7, TimeUnit.DAYS);
        } else {
            redisTemplate.opsForValue().set(redisKey, cookieStr, expiry.getTime(), TimeUnit.MILLISECONDS);
        }
    }
}
