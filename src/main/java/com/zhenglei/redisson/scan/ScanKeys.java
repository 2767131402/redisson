package com.zhenglei.redisson.scan;

import com.zhenglei.redisson.Base;
import org.redisson.api.RKeys;
import org.redisson.api.RSet;
import org.redisson.client.codec.StringCodec;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class ScanKeys extends Base {
    public static void main(String[] args) {
        RKeys keys = redissonClient.getKeys();
        Iterable<String> iterable = keys.getKeysByPattern("test:zhenglei:set:*", 1000);
        Iterator<String> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            // 逻辑操作
            RSet<String> set = redissonClient.getSet(key, new StringCodec());
            Set<String> sets = set.readAll();
            set.clear();
            Set<String> newSet = sets.stream().map(i -> i + "-zhenglei").collect(Collectors.toSet());
            set.addAll(newSet);
        }
        System.err.println("结束....");
    }
}
