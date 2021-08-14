package com.zhenglei.redisson.simple;

import com.zhenglei.redisson.Base;
import org.redisson.api.RMap;
import org.redisson.client.codec.StringCodec;

import java.util.concurrent.TimeUnit;

public class MapTest extends Base {
    public static void main(String[] args) {
        RMap<String, Object> map = redissonClient.getMap("test:map",new StringCodec());
        map.put("name","zhangsan");
        map.put("age",12);

        Object age = map.addAndGet("age", 2);
        System.err.println(age);
        map.expire(10, TimeUnit.SECONDS);
    }
}
