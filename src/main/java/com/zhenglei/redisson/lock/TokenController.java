package com.zhenglei.redisson.lock;

import org.apache.http.HttpStatus;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
public class TokenController {
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 初始化限流器
     */
    @RequestMapping("/init")
    public void init() {
        RRateLimiter limiter = redissonClient.getRateLimiter("token");
        //每秒产生5个令牌
        limiter.trySetRate(RateType.PER_CLIENT, 5, 1, RateIntervalUnit.SECONDS);
//        limiter.expire(1, TimeUnit.SECONDS);
    }

    /**
     * 获取令牌
     */
    @RequestMapping("/token")
    public int token() {
        RRateLimiter limiter = redissonClient.getRateLimiter("token");
        //尝试获取令牌
        if (limiter.tryAcquire()) {
            System.err.println(formatDate() + " " + Thread.currentThread().getName() + "获得锁");
        } else {
            System.err.println(formatDate() + " " + Thread.currentThread().getName() + "未获得锁");
        }
        return HttpStatus.SC_OK;
    }

    /**
     * 当前时间
     */
    private String formatDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
