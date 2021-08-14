package com.zhenglei.redisson;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.api.RedissonClient;

public class Base {
    public static RedissonClient redissonClient;

    static {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://81.70.175.128:6379");
        redissonClient = Redisson.create(config);
    }
}
