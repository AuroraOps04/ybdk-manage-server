package com.study.ybdk.utils;

import com.study.ybdk.entity.YibanUser;
import org.springframework.data.redis.core.script.DigestUtils;

public  final class RedisKeyUtils {
    /**
     * 根据易班帐号的用户名和密码生成redisKey
     *
     * @param yibanUser YibanUser
     * @return redisKey
     */
    public static String generateYibanTokenRedisKey(YibanUser yibanUser){
        return DigestUtils.sha1DigestAsHex(yibanUser.getUsername() + yibanUser.getPassword());
    }
}
