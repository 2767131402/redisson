package com.zhenglei.redisson.scan;

import com.zhenglei.redisson.Base;
import org.redisson.api.RSet;
import org.redisson.client.codec.StringCodec;

import java.util.Random;

/**
 * 测试数据
 */
public class TestData extends Base {

    private static Integer totalKey = 500000;

    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < totalKey; i++) {
            int size = rand.nextInt(50) + 50;
            String key = "test:zhenglei:set:" + String.format("%06d", i);
            RSet<Object> set = redissonClient.getSet(key, new StringCodec());
            for(int j = 0; j < size; j++){
                set.add(rand.nextInt(size * 10));
            }
        }
        System.err.println("结束...");
    }

}
