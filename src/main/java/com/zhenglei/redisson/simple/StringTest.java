package com.zhenglei.redisson.simple;

import com.zhenglei.redisson.Base;
import org.redisson.api.RBucket;

public class StringTest extends Base {

    public static void main(String[] args) {
        RBucket<Object> bucket = redissonClient.getBucket("test:str");
        Object o = bucket.get();
        System.err.println(o);
        bucket.set(1);
        System.err.println(bucket.get());
    }
}
